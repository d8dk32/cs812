/**
 * main for the T compiler
 *
 */

package tc.compiler;

import tc.compiler.parse.*;
import tc.compiler.tree.CompilationUnit;
import tc.compiler.tree.visit.*;
import tc.compiler.tree.type.ClassType;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.Token;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Main class for the T compiler. Processes command-line
 *  arguments, initiates the parser and lexer, and then generates
 *  LLVM assembly code by traversing the AST built by the parser.
 *  <p>
 *
 *  The supported command line arguments are:
 *
 *  <ul>
 *  <li>-AST: dump the AST as HTML after semantic analysis
 *
 *  <li>-CT: dump the class table after semantic analysis
 *
 *  <li>-log: display internal translator log messages
 *
 *  <li>-noAnalyze: skip semantic analysis and code generation
 *  <p>
 *  </ul>
 *
 *  The filename for the program to be translated should be given last
 *  on the command line, and should have a ".t" extension. The output
 *  file produced will have the same filename except the extension will
 *  be "*.ll".
 */
public class Main
{
  /** Tracks compiler version. */
  public static final String version = "1.0";

  private static String determineBaseFileName(String sourceFile)
  {
    Path path = Paths.get(sourceFile);
    String fileName = path.getFileName().toString();
    return fileName.substring(0, fileName.length() - 2);
  }

  // open an output file, when the output file name is based
  // upon the source file name
  private static PrintWriter openOutputFile(String outputClassName,
    String fileExtension)
  {
    String outFile = outputClassName + fileExtension;
    Message.log("output file created: " + outFile);
    try {
      return new PrintWriter(outFile);
    }
    catch (Exception e)
    {
      Message.fatal("problem creating " + outFile + "(" + e.toString() +
        ")");
      // fatal terminates program so this cannot be reached
      return null;
    }
  }

  /** Execution starts here.
   *
   *  @param args commandline arguments
   *  @throws IOException if an input or output error occurs
   */
  public static void main(String[] args) throws IOException
  {
    String sourceFile = null;
    boolean doDumpAST = false;
    boolean doAnalyze = true;
    CharStream reader = null;

    // it dumps the class table after the class preprocess phase
    boolean doDumpClassTypes = false;

    // process the command-line arguments
    //   all options must be first
    //     -log for displaying internal implementation messages
    //     -AST for dumping the AST
    //     -CT for dumping the class types
    //     -noAnalyze for disabling the Analyze and Encode visits
    //   after the options the source file name must be given
    //     source file name must end in ".t"
    for (String arg: args)
    {
      // source file name must be given last
      if (sourceFile != null)
      {
        Message.usage();
      }

      // handle options, which can be given redundantly
      if (arg.equals("-AST"))
      {
        doDumpAST = true;
      }
      else if (arg.equals("-CT"))
      {
        doDumpClassTypes = true;
      }
      else if (arg.equals("-log"))
      {
        Message.enableLogging();
      }
      else if (arg.equals("-noAnalyze"))
      {
        doAnalyze = false;
      }
      // if not a valid option then it is assumed to be the source file name
      else
      {
        sourceFile = arg;
      }
    }
    // source file name must be given and it must end with ".t"
    if (sourceFile == null || !sourceFile.endsWith(".t"))
    {
      Message.usage();
    }
    Message.log(sourceFile + ": doDumpAST is " + doDumpAST);

    String baseFileName = determineBaseFileName(sourceFile);

    // open the source file
    try {
      reader = CharStreams.fromFileName(sourceFile);
    }
    catch (Exception e)
    {
      Message.fatal("problem opening " + sourceFile + "(" + e.toString() + ")");
    }

    // create and declare the Object class type
    ClassType.getInstance("Object").setWasDeclared();
    Message.log("Object class created");

    // create the lexer
    TLexer lexer = new TLexer(reader);
    CommonTokenStream tokens = new CommonTokenStream(lexer);

    // create the parser
    TParser parser = new TParser(tokens);

    // will build my own abstract syntax tree
    parser.setBuildParseTree(false);

    // quit with first syntax error
    parser.setErrorHandler(new BailWithNoMessageErrorStrategy());

    // start the parser
    //   note: the method name is the name of the start symbol in the grammar
    try {
      parser.compilationUnit();
    }
    catch (ParseCancellationException pce)
    {
      // underlying recognition exception is wrapped in the
      // parse cancellation exception that is thrown by the
      // bail error strategy. Use getCause inherited from
      // Throwable to get the recognition exception.
      RecognitionException re = (RecognitionException) pce.getCause();
      Token errorToken = re.getOffendingToken();
      Location loc = new Location(
        errorToken.getTokenSource().getSourceName(),
        errorToken.getLine(),
        errorToken.getCharPositionInLine()
      );
      Message.syntaxError(loc);   // exits the program
    }

    // parser produces an AST rooted by a CompilationUnit node
    // also will add classes to class table with incomplete types,
    // meaning that the superclass, fields, methods, constructors and
    // destructors will be added later by the preprocess visitor
    CompilationUnit root = parser.getSemanticValue();

    // preprocess class declarations
    // they need special handling because their scope is the whole file
    PreprocessClassDeclarations preprocessor =
      new PreprocessClassDeclarations();
    root.apply(preprocessor);
    Message.log("PreprocessClassDeclarations finished");

    // if requested, dump the class types
    if (doDumpClassTypes)
    {
      Message.log("class table dump started");
      ClassType.dumpClasses();
      Message.log("class table dump finished");
    }

    // do semantic analysis of the main block and the method bodies
    // but do not do it if errors were detected while processing
    // the global declarations
    if (doAnalyze && (Message.getErrorCount() == 0))
    {
      Analyze analyzer = new Analyze();
      Message.log("Analyze phase started");
      root.apply(analyzer);
      Message.log("Analyze phase finished");
    }

    // if requested, dump the AST
    if (doDumpAST)
    {
      Message.log("AST dump started");

      // determine dump file name (file.t --> file.html)
      //   note that the dump file will be written in the current directory
      PrintWriter dumper = openOutputFile(baseFileName, ".html");

      dumper.println("<head>");
      dumper.println("<title> AST for " + sourceFile + "</title>");
      dumper.println("</head>");
      dumper.println("<body>");
      dumper.println("<pre>");
      Dump treeDump = new Dump(dumper, 0, 2);
      root.apply(treeDump);
      dumper.println("</pre>");
      dumper.close();
      Message.log("AST dump finished");
    }

    if (!doAnalyze)
    {
      return;
    }

    // if semantic errors detected, cannot continue to code generation
    if (Message.getErrorCount() != 0)
    {
      Message.fatal("code generation skipped due to semantic errors");
    }

    // generate the LLVM assembly code

    Message.log("Encode phase started");

    // determine file name (file.t --> file.ll)
    // note that the LLVM code file will be written in the current directory
    PrintWriter gener = openOutputFile(baseFileName, ".ll");

    // create the code generator visitor
    Encode genCode = new Encode(gener);

    // output a header comment
    genCode.encodeHeaderComment(sourceFile);

    // output declarations for Object
    // TODO

    // generate code from the AST
    root.apply(genCode);
    gener.close();
    Message.log("Encode phase finished");
  }
}
