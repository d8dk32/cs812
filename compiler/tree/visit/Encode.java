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
    emit("declare void @t_rt_print_array_index_out_of_bounds_error(i32)");
    emit("declare void @t_rt_print_null_ref_error(i32)");
    emit("declare void @t_rt_print_logging(i32)");
    emit("declare i8* @t_rt_new_intarray(i32, i8*, i32, i32)");
    emit("declare i8* @t_rt_new_refarray(i32, i8*, i32, i32)");
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
        superClassLLVM =  "bitcast ([1 x i8*]* " +
          superClass.encodeRuntimeType() + " to i8*)";
      }
      emit("@" + name + "$VMT = global [1 x i8*] " +
        "[i8* " + superClassLLVM + "]");
    }
    emit("");

    emit(";array type");
    emit("%array$int = type { i8*, i8*, i32, i32, [0 x i32] }");
    emit("%array$ref = type { i8*, i8*, i32, i32, [0 x i8*] }");


    // generate LLVM class types
    emit("; class object types");
    for (String name : classNames)
    {
      String s = "%class$" + name + " =  type { i8*";
      // fields will go here!
      for(NameTypeDepth ntd : ClassType.getInstance(name).getFields())
      {
        s += ", ";
        if(ntd.getDepth() == 0)
        {
          if(ntd.getType() == "int")
            s += "i32";
          else
            s += "%class$" + ntd.getType() + "*";
        }
        else if (ntd.getType() == "int" && ntd.getDepth() == 1)
          s += "%array$int*";
        else
          s += "%array$ref*";
      }
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

    //If the identifier is not the lefthand side of an assignment, 
    //actually return a var that holds the real value contained in this variable
    if (!identifier.isLeftSide())
    {
      String temp = getTemp();
      emit("; load value from variable");
      if (symbolTable.get(id).getDepth() == 0)
        emit(temp + " = load " + identifier.getType().encode() + ", " + identifier.getType().encode() + "* " + pointer);
      else if (symbolTable.get(id).getDepth() > 0) 
        emit(temp + " = load " + identifier.getType().encode() + "*, " + identifier.getType().encode() + "** " + pointer);
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
      Integer depth = declStatement.getDimensionList().get(x);
      
      Type varType = null;
      if (declStatement.getType() == "int")
      {
        if(depth > 0)
        {
          varType = new ArrayType("int", depth);
        }
        else
        {
          varType = IntegerType.getInstance();
        }
      }
      else
      {
          //must be some kind of class type
          if(depth > 0)
          {
            varType = new ArrayType(i.getName(), depth);
          }
          else
          {
            varType = ClassType.getInstance(declStatement.getType());
          }
      }
      symbolTable.put(i.getName(),
            new TypeDepthPair(varType, depth));     

      //get variable name and type
      final String declPointer = visitNode(i);
      final String type = varType.encode();

      if(depth == 0)
      {
        String emptyValue = (type == "i32" ? "0" : "null");
        //declare the variable in llvm code
        emit("; declaration statement");
        emit(declPointer + " = alloca " + type);
        emit("; store empty value on declaration");
        emit("store " + type + " " + emptyValue + ", " + type + "* " + declPointer);  
      } else if (depth == 1 && declStatement.getType() == "int") {
        //declare the array in the llvm code
        //ONLY SUPPORTS INT ARRAYS ATM
        emit("; array declaration statement");
        emit(declPointer + "  = alloca %array$int*");
        emit("store %array$int* null, %array$int** " + declPointer);
      }
      else {
        //declare the array in the llvm code
        //multidimensional int arrays are refs?
        emit("; array declaration statement");
        emit(declPointer + "  = alloca %array$ref*");
        emit("store %array$ref* null, %array$ref** " + declPointer);
      }
        
    }

    return null;
  }

  /** visit assignment node */
  @Override public String visit(final Assignment assignment) {
    final String result = visitNode(assignment.getExpression());
    String assignmentType = assignment.getExpression().getType().encode();
    if(assignment.getExpression().getType().isArrayType())
    {
      assignmentType = assignmentType + "*"; //arrays need an extra asterisk
    }

    //cast LeftSide to its real type
    String id = null; 

    if(assignment.getIdentifier() instanceof Identifier)
    { 
      id = visitNode((Identifier) assignment.getIdentifier());
    }
    else if (assignment.getIdentifier() instanceof ArrayAccess)
    { 
      id = visitNode((ArrayAccess) assignment.getIdentifier()); 
      if( ((ArrayAccess) assignment.getIdentifier()).getBaseType().getDepth() > 1 )
      {
        String temp = id;
        id = getTemp();
        emit(id + " = bitcast i8** " + temp + " to " + assignmentType + "*");
      }
    }
    else if (assignment.getIdentifier() instanceof FieldAccess)
    {
      id = visitNode((FieldAccess) assignment.getIdentifier());
    }
    

    emit("; store assigned value");
    emit("store " + assignmentType + " " + result + ", " + assignmentType + "* " + id);

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
    String arrayDepth = ace.getNumDims().toString();
    String elemType = ((ArrayType) ace.getType()).encodeComponentRuntimeType();
    String varType = ace.getType().encode();
    String retval = getTemp();
    String temp1 = getTemp();
    String temp2 = getTemp();
    String arrLenPtr = getTemp();
    String arrDimsPtr = getTemp();
    String arrTypePtr = getTemp();

    emit("; array creation");
    emit(temp1 + " = bitcast [1 x i8*]* " + elemType + " to i8*");
    
    if(ace.getNumDims() == 1)
    {
      emit(temp2 + " = call i8* (i32, i8*, i32, i32) @t_rt_new_intarray( i32 " +
          ace.getLoc().getLine() + ", i8* " + temp1 + ", i32 " + arrayDepth + ", i32 " + dimExpr + ")");
    } else {
      emit(temp2 + " = call i8* (i32, i8*, i32, i32) @t_rt_new_refarray( i32 " +
          ace.getLoc().getLine() + ", i8* " + temp1 + ", i32 " + arrayDepth + ", i32 " + dimExpr + ")");
    }
    
    emit(retval + " = bitcast i8* " + temp2 + " to " + ace.getType().encode() + "*");
    //c runtime func doesn't seem to be storing struct entries properly so I'll do it myself
    emit("; manually store len/dims/type because the new_array func isn't doing it like I expected");
    emit(arrLenPtr + " = getelementptr " + varType + ", " + varType + "* " + retval + ", i32 0, i32 3");
    emit(arrDimsPtr + " = getelementptr " + varType + ", " + varType + "* " + retval + ", i32 0, i32 2");
    emit(arrTypePtr + " = getelementptr " + varType + ", " + varType + "* " + retval + ", i32 0, i32 1");
    emit("store i32 " + dimExpr + ", i32* " + arrLenPtr);
    emit("store i32 " + arrayDepth + ", i32* " + arrDimsPtr);
    emit("store i8* " + temp1 + ", i8** " + arrTypePtr);

    return retval;
  }

  /** visit an ArrayAccess node */
  @Override public String visit(final ArrayAccess aa)
  {
    String theArray = visitNode(aa.getTheArray());
    //String varType = aa.getTheArray().getType().encode();
    String varType = aa.getBaseType().encode();
    String arrIndex = visitNode(aa.getDimExpr());

    //necessary temps
    String arrayPtr = getTemp();
    String elementPtr = getTemp();
    String nullCheckTemp = getTemp();
    String arrLenPtr = getTemp();
    String arrLen = getTemp();
    String greaterThanZero = getTemp();
    String lessThanLen = getTemp();
    String theElement = getTemp();

    //necessary labels
    String nullLabel = getLabel();
    String greaterThanZeroLabel = getLabel();
    String lessThanLenLabel = getLabel();
    String accessLabel = getLabel();
    String outOfBoundsLabel = getLabel();

    emit("; array access");

    emit("; check for null reference");
    emit(nullCheckTemp + " = icmp eq " + varType + "* " + theArray + ", null");
    emit("br i1 " + nullCheckTemp + ", label %" + nullLabel + ", label %" + greaterThanZeroLabel);
    emit(nullLabel + ":");
    emit("call void @t_rt_print_null_ref_error(i32 " + aa.getLoc().getLine() + ")");
    emit("br label %" + greaterThanZeroLabel);
    emit("; check that index is greater than or equal to 0");
    emit(greaterThanZeroLabel + ":");
    emit(arrLenPtr + " = getelementptr " + varType + ", " + varType + "* " + theArray + ", i32 0, i32 3");
    emit(arrLen + " = load i32, i32* " + arrLenPtr);
    emit(greaterThanZero + " = icmp sge i32 " + arrIndex + ", 0");
    emit("br i1 " + greaterThanZero + ", label %" + lessThanLenLabel + ", label %" + outOfBoundsLabel);
    emit("; check index is less than array length");
    emit(lessThanLenLabel + ":");
    emit(lessThanLen + " = icmp slt i32 " + arrIndex + ", " + arrLen);
    emit("br i1 " + lessThanLen + ", label %" + accessLabel + ", label %" + outOfBoundsLabel);
    emit(outOfBoundsLabel + ":"); 
    emit("call void @t_rt_print_array_index_out_of_bounds_error(i32 " + aa.getLoc().getLine() + ")");
    emit("br label %" + accessLabel);

    emit("; perform actual array access");
    emit(accessLabel + ":");
    emit(arrayPtr + " = getelementptr " + varType + ", " + varType + "* " + theArray + ", i32 0, i32 4");


    ArrayType baseType = aa.getBaseType();
    int arrDepth = baseType.getDepth();

    if(arrDepth == 1)
      emit(elementPtr + " = getelementptr [0 x i32], [0 x i32]* " + arrayPtr + ", i32 0, i32 " + arrIndex);
    else if (arrDepth > 1)
      emit(elementPtr + " = getelementptr [0 x i8*], [0 x i8*]* " + arrayPtr + ", i32 0, i32 " + arrIndex);
  
    if(aa.isLeftSide())
      return elementPtr;
    
    if (arrDepth == 1)
    {
      emit(theElement + " = load i32, i32* " + elementPtr);
      return theElement;
    }
    else if (arrDepth > 1)
    {
      String retval = getTemp();
      emit(theElement + " = load i8*, i8** " + elementPtr);

      if(aa.getLayer() > 1)
        emit(retval + " = bitcast i8* " + theElement + " to " + baseType.encode() + "*");
      else
      {
        String retTemp = getTemp();
        emit(retTemp + " = bitcast i8* " + theElement + " to " + aa.getType().encode()+ "*" );
        emit(retval + " = load " + aa.getType().encode() + ", " + aa.getType().encode()+ "*" + retTemp);
      }
      return retval;
    }

    //if it hits here and returns null there is a problem, like
    //it thinks its an array but the depth is 0
    Message.bug(aa.getLoc(), "Trying to index into a variable that isn't an array");
    return null;
  }

  @Override public String visit(final Cast c)
  {
    //for now casts aren't really doing much. If they made it this far, it's basically a NO_OP
    return visitNode(c.getToBeCastExpression());
  }

  @Override public String visit(final FieldAccess fa)
  {
    String objPtr = visitNode(fa.getObj());
    String nullCheckTemp = getTemp();
    String varType = fa.getObj().getType().encode();
    String fieldPtr = getTemp();
    String retval = getTemp();
    String arrLen = getTemp();

    String nullLabel = getLabel();
    String proceedLabel = getLabel();

    if(fa.getObj().getType().isArrayType()) 
    {
      //don't actually visit the length identifier because it won't have been defined, but that's ok
      if(fa.getField().getName().equals("length"))
      {
        
        //check to make sure the array has actually been defined
        emit("; check for null reference");
        emit(nullCheckTemp + " = icmp eq " + varType + "* " + objPtr + ", null");
        emit("br i1 " + nullCheckTemp + ", label %" + nullLabel + ", label %" + proceedLabel);
        emit(nullLabel + ":");
        emit("call void @t_rt_print_null_ref_error(i32 " + fa.getLoc().getLine() + ")");
        emit("br label %" + proceedLabel);
        emit(proceedLabel + ":");
        emit("; array is not null, proceed");
        emit(fieldPtr + " = getelementptr " + varType + ", " + varType + "* " + objPtr + ", i32 0, i32 3");

        if(fa.isLeftSide())
          //return fieldPtr;
          Message.bug(fa.getLoc(), "array length cannot be modified");

        emit(arrLen + " = load i32, i32* " + fieldPtr);
        return arrLen;
      }
      else
      {
        Message.bug(fa.getLoc(), "Field not valid for array types");
      }
    }
    else
    {
      //TODO
      //field access for class types
      
      //get field index on class
      ClassType ct = (ClassType) fa.getObj().getType();
      int fieldIndex = ct.getFieldIndex(fa.getField().getName());

      String actualVarType = ct.encodeType(); //class type encoding without the asterisk

      // Null ref check:
      emit("; Null reference check");
      emit(nullCheckTemp + " = icmp eq " + varType + " " + objPtr + ", null");
      emit("br i1 " + nullCheckTemp + ", label %" + nullLabel + ", label %" + proceedLabel);
      emit(nullLabel + ":");
      emit("call void @t_rt_print_null_ref_error(i32 " + fa.getLoc().getLine() + ")");
      emit("br label %" + proceedLabel);
      emit(proceedLabel + ":");
      //get the field at it's index in the field list
      emit(retval + " = getelementptr " + actualVarType + ", " + varType + " " + actualVarType + ", i32 0, i32 " + fieldIndex);

      if (fa.isLeftSide())
          return retval;

      String temp2 = getTemp();
      String fieldTypeEncode = fa.getType().encode();

      emit(temp2 + " = load " + fieldTypeEncode + ", " + fieldTypeEncode + "* " + retval);

       return temp2;
      
    }

    //gonna need to do more here in the future
    return retval;
  }

  @Override public String visit(final ClassInstanceCreationExpression cice)
  {
    ClassType ct = ClassType.getInstance(cice.getClassName());
    String retval = getTemp();
    String classType = ct.encode();
    String classVMT = ct.encodeRuntimeType();
    String sizeTemp1 = getTemp();
    String sizeTemp2 = getTemp();
    String objectTemp = getTemp();
    String vmtTemp = getTemp();

    int classSize = ct.getClassSize()+1; //plus one is beacuse of the extra i8* that all classes have

    emit("; class instance creation");
    
    emit(sizeTemp1 + " = getelementptr " + classType + ", " + classType + "* null, i32 " + classSize);
    emit(sizeTemp2 + " = ptrtoint " + classType + "* " + sizeTemp1 + " to i64");
    //actually allocate object
    emit(objectTemp + " = call i8* @t_rt_alloc(i64 " + sizeTemp2 + ", i32 " + cice.getLineNumber() + ")");
    //do some funky stuff I'm pretty sure relates to the size of the vmt
    emit(vmtTemp + " = bitcast i8* " + objectTemp + " to [1 x i8*]**");
    emit("store [1 x i8*]* " + ct.encodeRuntimeType() + ", [1 x i8*]** " + vmtTemp);
    //finally, bitcast that i8* back to the class type
    emit(retval + " = bitcast i8* " + objectTemp + " to " + classType);

    return retval;
  }

  //@Override public String visit()


}
