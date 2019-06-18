package tc.compiler.tree.visit;

import tc.compiler.Message;
import tc.compiler.Main;
import tc.compiler.tree.*;
import tc.compiler.tree.type.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Stack;

/**
 * Does a traversal of the AST to generate LLVM code to execute the program
 * represented by the AST.
 * <p>
 * Uses a String for the type parameter, which usually will be the name
 * of the temporary variable that will contain (at runtime) the result
 * of executing the code for a subtree.
 */
public final class Encode extends TreeVisitorBase<String>
{
  //symbol table, doesn't seem useful yet
  HashMap<String, TypeDepthPair> symbolTable = new HashMap<>(); 
  Stack<String[]> whileStack = new Stack<>();

  // where to write the code
  private PrintWriter writer;

  // current indentation amount
  private int indentation = 0;

  // how much to increment the indentation by at each level
  // using an increment of zero would mean no indentation
  private int increment = 2;

  // simple counter for expression temps
  private int nextTemp = 0;

  //simple counter for if/else label names
  private int nextLabel = 0;

  /** Create a visit to do code generation.
   *
   *  @param writer where to write the generated code.
   */
  public Encode(PrintWriter writer)
  {
    this.writer = writer;
  }

  // generate a string of spaces for current indentation level
  private void indent()
  {
    for (int i = 0; i < indentation; i++)
    {
      writer.print(" ");
    }
  }

  // emit an indented line
  private void emit(final String line)
  {
    indent();
    writer.println(line);
  }

  // return string for name of next expression temp
  private String getTemp()
  {
    String ret = "%temp" + nextTemp;
    nextTemp += 1;
    return ret;
  }

  // return string for name of next label temp
  private String getLabel()
  {
    String ret = "label" + nextLabel;
    nextLabel += 1;
    return ret;
  }

  /** Generate a header comment at the top of the LLVM code file.
   *
   *  @param sourceFileName the source file name.
   */
  public void encodeHeaderComment(String sourceFileName)
  {
    Date date = new Date();
    emit("; source file: " + sourceFileName);
    emit("; T version: " + Main.version);
    emit("; compiled: " +  date);
    emit("");
  }

  /** Visit a node and return the result of the visit.
   */
  @Override protected String visitNode(final Tree node)
  {
    return node.apply(this);
  }

  /** Visit a list of ASTs and generate code for each of them in order.
   *  Uses a wildcard for generality: list of Statements, list of Expressions,
   *  etc. Overrides the method in TreeVisitorBase in order to return a
   *  list.
   *
   *  @param nodes list of nodes to be visited.
   *  @return list of Strings containing the names of the result operands.
   */
  @Override public List<String> visitEach(final Iterable<?> nodes)
  {
    List<String> ret = new ArrayList<String>();
    for (final Object node : nodes)
    {
      ret.add(visitNode((Tree) node));
    }
    return ret;
  }
 
  /** Generate code for a binary operator. */
  @Override public String visit(final BinaryOperator binaryOperator)
  {
    // recurse to both subtrees
    String leftResult = visitNode(binaryOperator.getLeft());
    String rightResult = visitNode(binaryOperator.getRight());

    // get temp for the result
    String result = getTemp();

    switch (binaryOperator.getOp())
    {
      case ADD:
        emit("; add");
        emit(result + " = " + "add " + "i32" + " " +
          leftResult + ", " + rightResult);
        break;
      case SUBTRACT:
        emit("; subtract");
        emit(result + " = " + "sub " + "i32" + " " +
          leftResult + ", " + rightResult);
        break;
      case MULTIPLY:
        emit("; multiply");
        emit(result + " = " + "mul " + "i32" + " " +
          leftResult + ", " + rightResult);
        break;
      case DIVIDE:
        String divTemp1 = getTemp();
        String divLabel1 = getLabel();
        String divLabel2 = getLabel();
        emit("; divide");
        emit("; check for divide by 0");
        emit(divTemp1 + " = icmp eq i32 " + rightResult + ", 0");
        emit("br i1 " + divTemp1 + ", label %" + divLabel1 + ", label %" + divLabel2);
        emit(divLabel1 + ":");
        emit("call void @t_rt_print_divide_by_zero_error(i32 " + binaryOperator.getLoc().getLine() + ")");
        //emit("br label %" + divLabel2);
        emit("ret void");
        emit(divLabel2 + ":");
        emit(result + " = " + "sdiv " + "i32" + " " +
          leftResult + ", " + rightResult);
        break;
      case EQUALITY:
        String tempEq = getTemp();
        emit("; equals");
        emit(tempEq + " = icmp eq i32 " + leftResult + ", " + rightResult);
        emit(result + " = zext i1 " + tempEq + " to i32");
        break;
      case LESSTHAN: 
        String tempLt = getTemp();
        emit("; less than");
        emit(tempLt + " = icmp slt i32 " + leftResult + ", " + rightResult);
        emit(result + " = zext i1 " + tempLt + " to i32");
        break;
      case GREATERTHAN: 
        String tempGt = getTemp();
        emit("; greater than");
        emit(tempGt + " = icmp sgt i32 " + leftResult + ", " + rightResult);
        emit(result + " = zext i1 " + tempGt + " to i32");
        break;
      default:
        Message.bug(binaryOperator.getLoc(), "unknown binary operator" +
          binaryOperator.getOpString());
        break;
    }
    return result;
  }
 
  /** Generate code for a compilation unit. */
  @Override public String visit(final CompilationUnit compilationUnit)
  {
    // generate code for the runtime support functions
    emit("; declarations for the runtime support functions");
    emit("declare void @t_rt_alloc_init()");
    emit("declare i8* @t_rt_alloc(i64, i32)");
    emit("declare void @t_rt_dealloc(i8*)");

    emit("declare void @t_rt_print_int(i32)");
    emit("declare void @t_rt_print_divide_by_zero_error(i32)");
    emit("declare void @t_rt_new_array(i32, i8*, i32, i32, ...)");
    emit("");

    // get all the class names
    Set<String> classNames = ClassType.getClassNames();

    // generate VMTs
    emit("; VMTs");
    emit("@arrayVMT = global [1 x i8*] " +
      "[i8* bitcast ([1 x i8*]* @Object$VMT to i8*)]");
    emit("@intVMT = global [1 x i8*] [i8* null]");
    for (String name : classNames)
    {
      ClassType classType = ClassType.getInstance(name);
      ClassType superClass = classType.getSuperClass();
      String superClassLLVM = null;
      if (superClass == null)
      {
        superClassLLVM = "null";
      }
      else
      {
        superClassLLVM =  "bitcast [1 x i8*]* " +
          superClass.encodeRuntimeType() + " to i8*]";
      }
      emit("@" + name + "$VMT = global [1 x i8*] " +
        "[i8* " + superClassLLVM + "]");
    }
    emit("");

    emit(";array type");
    emit("%array$int = type { i8*, i8* i32, i32, [0 x i32] }");
    emit("%array$ref = type { i8*, i8* i32, i32, [0 x i8*] }");


    // generate LLVM class types
    emit("; class object types");
    for (String name : classNames)
    {
      String s = "%class$" + name + " =  type { i8*";
      // fields will go here!
      s +=  " }";
      emit(s);
    }
    emit("");

    // generate code for all classes
    visitEach(compilationUnit.getClasses());

    // generate code for the main block
    emit("; main block");
    emit("define void @main() {");
    indentation += increment;
    emit("call void @t_rt_alloc_init()");
    visitEach(compilationUnit.getMainBlock());
    emit("ret void");
    indentation -= increment;
    emit("}");

    return null;
  }

  /** Generate code for a integer literal. */
  @Override public String visit(final IntegerLiteral integerLiteral)
  {
    // assuming here that all legal T integer literals have the same
    // value in LLVM
    return integerLiteral.getValue();
  }

  /** Generate code for an output statement. */
  @Override public String visit(final OutputStatement
    outputStatement)
  {
    String result = visitNode(outputStatement.getExp());
    emit("; call to runtime function to output a value");
    emit("call void @t_rt_print_int(i32 " + result + ")");
    return null;
  }

  /** Generate code for a unary operator. */
  @Override public String visit(final UnaryOperator unaryOperator)
  {
    String leftResult = visitNode(unaryOperator.getLeft());
    String result = getTemp();
    switch (unaryOperator.getOp())
    {
     case NEGATE:
        emit("; negate");
        emit(result + " = " + "sub " + "i32" + " " + "0" + ", " + leftResult);
        break;
     case NOT:
        String temp1 = getTemp();
        emit("; not");
        emit(temp1 + " = icmp eq i32 " + leftResult + ", 0");
        emit(result + " = zext i1 " + temp1 + " to i32");  
        break;
      default:
        Message.bug(unaryOperator.getLoc(), "unknown unary operator " +
          unaryOperator.getOpString());
        break;
    }
    return result;
  }

  /** Generate code for an identifier node
   */
  @Override public String visit(final Identifier identifier)
  {
    String id = identifier.getName();
    //String varType = variable.getType().encode();
    String pointer = "%" + id;

    //if the identifier has not been defined, bug out.
    if (symbolTable.get(identifier.getName()) == null)
    {
      Message.fatal("undefined variable " + identifier.getName());
    }

    //If the identifier is not the lefthand side of an assignment, 
    //actually return a var that holds the real value contained in this variable
    if (!identifier.isLeftSide())
    {
      String temp = getTemp();
      emit("; load value from variable");
      emit(temp + " = load " + identifier.getType().encode() + ", " + identifier.getType().encode() + "* " + pointer);
      return temp;
    }

    return pointer;
  }

  /*visit declaration statement node
  */
  @Override public String visit(final DeclarationStatement declStatement) {

    //visit its child nodes
    for(int x = 0; x < declStatement.getDeclarations().size(); x++)
    {
      Identifier i = declStatement.getDeclarations().get(x);
      //store entity in symbol table? 
      if (symbolTable.get(i.getName()) == null)
      {
        //if its not already been declared, add it to the symbol table
        symbolTable.put(i.getName(),
              new TypeDepthPair(declStatement.getType(), declStatement.getDimensionList().get(x)));     

        //get variable name and type
        final String declPointer = visitNode(i);
        final String type = declStatement.getType().encode();

        if(symbolTable.get(i.getName()).getDepth() == 0)
        {
          //declare the variable in llvm code
          emit("; declaration statement");
          emit(declPointer + " = alloca " + type);
          emit("; store 0 on declaration");
          emit("store " + type + " " + '0' + ", " + type + "* " + declPointer);  
        } else {
          //declare the array in the llvm code
          //ONLY SUPPORTS INT ARRAYS ATM
          emit("; array declaration statement");
          emit(declPointer + "  = alloca %array$int*");
          emit("store %array$int* null, %array$int** " + declPointer);
        }
      }  
    }

    return null;
  }

  /** visit assignment node */
  @Override public String visit(final Assignment assignment) {
    final String result = visitNode(assignment.getExpression());
    //final String assignmentType = assignment.getExpression().getType().encode();
    final String id = visitNode((Identifier) assignment.getIdentifier());

    emit("; store assigned value");
    emit("store " + "i32" + " " + result + ", " + "i32" + "* " + id);

    return result;
  }

  /*visit if/else node
  */
  @Override public String visit(final IfElseStatement ifElse) {

    //Evaluate condition
    String condition = visitNode(ifElse.getCondition());
    String truthfulness = getTemp(); //the "boolean" value of the condition ends up stored in here
    String thenLabel = getLabel();
    String elseLabel = getLabel();
    String exitLabel = getLabel();

    emit("; check condition for IF statement");
    emit(truthfulness + " = icmp ne i32 " + condition + ", 0");
    emit("br i1 " + truthfulness +", label %" + thenLabel + ", label %" + elseLabel);
    emit("; THEN branch");
    emit(thenLabel + ":");
    visitNode(ifElse.getThen());
    emit("br label %" + exitLabel);
    emit("; ELSE branch");
    emit(elseLabel + ":");
    visitNode(ifElse.getElse());
    emit("br label %" + exitLabel);
    emit(exitLabel + ":");
    
    return null;
  }

  /*visit while node
  */
  @Override public String visit(final WhileStatement whileStatement) {
    String continueLabel = getLabel();
    String breakLabel = getLabel();
    String bodyLabel = getLabel();

    //push loop labels onto stack
    String[] whileLabels = new String[2];
    whileLabels[0] = continueLabel;
    whileLabels[1] = breakLabel;
    whileStack.push(whileLabels);

    //loop begins
    emit("; start while loop");
    emit("br label %" + continueLabel);
    emit(continueLabel +":");
    String condition = visitNode(whileStatement.getCondition());
    String temp = getTemp();
    emit("; evaluate while condition");
    emit(temp + " = icmp ne i32 " + condition + ", 0");
    emit("br i1 " + temp + ", label %" + bodyLabel + ", label %" + breakLabel);
    emit("; while statement body");
    emit(bodyLabel + ":");
    visitNode(whileStatement.getStatement());
    emit("br label %" + continueLabel);
    emit("; end while statement body");
    emit(breakLabel + ":");

    //pop stack afterencoding the body
    whileStack.pop();
    return null;
  }

  /** Visit a break statement node*/
  @Override public String visit(final BreakStatement br)
  {
    //ensure break is inside a while
    if(whileStack.empty())
    {
      Message.bug(br.getLoc(), "Break must be enclosed in a While statement");
    }
    //get exit label of current while loop
    String[] curLabels = whileStack.peek();
    emit(";break");
    emit("br label %" + curLabels[1]);
    return null;
  }

  /** Visit a continue statement node*/
  @Override public String visit(final ContinueStatement c)
  {
    //ensure continue is inside a while
    if(whileStack.empty())
    {
      Message.bug(c.getLoc(), "Continue must be enclosed in a While statement");
    }

    //get top label of current while loop
    String[] curLabels = whileStack.peek();
    emit(";continue");
    emit("br label %" + curLabels[0]);
    return null;
  }
  
  /** Visit an ArrayCreationExpression node */
  @Override public String visit(final ArrayCreationExpression ace)
  {
    String dimExpr = visitNode(ace.getDimExpr());
    String arrayDepth = ace.getDepth();
    String elemType = ace.getType().encodeRuntimeType();
    String retval = getTemp();
    String temp1 = getTemp();
    String temp2 = getTemp();

    emit("; array creation");
    emit(temp1 + " = bitcast [1 x i8*]* " + elemType() + " to i8*");
    emit(temp2 + " = call i8* (i32, i8*, i32, i32, ...) @t_rt_new_array( i32 " +
          ace.getLoc().getLine() + ", i8* " + temp1 + ", i32 " + arrayDepth + ", i32 1, i32 " + dimExpr);
    emit(retval + " = bitcast i8* " + temp2 + " to %array$int*");
    return retval;
  }

}
