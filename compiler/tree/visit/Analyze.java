
package tc.compiler.tree.visit;

import tc.compiler.Message;
import tc.compiler.Location;
import tc.compiler.tree.*;
import tc.compiler.tree.type.*;
import java.util.HashMap;
import java.util.List;

/**
 * Analyze an AST to determine the semantics of the program. Information
 * is left in the AST, and the AST may possibly be changed in order to
 * fully describe the semantics.
 * <p>
 * Using Tree for the type parameter to allow subtrees to be altered
 * by visiting them. The return type is assigned to the fields
 * of a node as the traversal returns back to it.
 * <p>
 * The "visit" method is overloaded for each tree node type.
 */
public final class Analyze extends TreeVisitorBase<Tree>
{

  HashMap<String, TypeDepthPair> symbolTable = new HashMap<>(); 
  int whileDepth = 0;
  /** Create an AST semantic analyzer.
   *
   */
  public Analyze()
  {
  }

  /** Called for every node.
   *
   *  @param node node to be visited.
   *  @return value generated by visitor.
   */
  protected Tree visitNode(final Tree node)
  {
    return node.apply(this);
  }

  /** Analyze a binary operator. */
  @Override public Tree visit(final BinaryOperator binaryOperator)
  {
    // recurse to the children
    binaryOperator.setLeft((Expression) visitNode(binaryOperator.getLeft()));
    binaryOperator.setRight((Expression) visitNode(binaryOperator.getRight()));

    Expression left = binaryOperator.getLeft();
    Expression right = binaryOperator.getRight();

     // avoid a cascade of error messages
    if (left.getType().isErrorType() ||
        right.getType().isErrorType())
    {
      // propagate error type up the tree
      binaryOperator.setType(ErrorType.getInstance());

      // do no further analysis
      return binaryOperator;
    }

    // both operands must be integer type
    // and the result type will be integer type

    if (!(left.getType().isIntegerType() &&
        right.getType().isIntegerType()))
    {
      // display error message
      Message.error(binaryOperator.getLoc(), "integer operands expected");

      // set error type in the node
      binaryOperator.setType(ErrorType.getInstance());
    }
    else
    {
      binaryOperator.setType(IntegerType.getInstance());
    }

    return binaryOperator;
  }

  /** Analyze a compilation unit. */
  @Override public Tree visit(final CompilationUnit compilationUnit)
  {
    //check for cyclical inheritiance before analyzing 
    for(ClassDeclaration cd : compilationUnit.getClasses())
    {
      HashMap<String, Boolean> supers = new HashMap<>();
      ClassType classType = ClassType.getInstance(cd.getClassName());
      ClassType nextSuper = classType.getSuperClass();
      boolean cycleFound = false;
      while (nextSuper != null && !cycleFound)
      {
        cycleFound = supers.containsKey(nextSuper.getName());
        supers.put(nextSuper.getName(), true);
        nextSuper = nextSuper.getSuperClass();
      }
      if (cycleFound)
      {
        Message.fatal("Cyclical inheritance detected starting with class " + classType.getName());
      }
    }    

    visitEach(compilationUnit.getClasses());
    visitEach(compilationUnit.getMainBlock());

    return compilationUnit;
  }

  /** Analyze an integer literal. */
  @Override public Tree visit(final IntegerLiteral integerLiteral)
  {
    long v = 0;
    Type type = IntegerType.getInstance();
    Location loc = integerLiteral.getLoc();

    try
    {
      v = Long.parseLong(integerLiteral.getValue());
    }
    catch(NumberFormatException nfe)
    {
      Message.error(loc, "illegal integer literal");
      type = ErrorType.getInstance();
    }

    // need to worry about -2147483648
    if (v == 2147483648L)
    {
      if (!integerLiteral.isParentUnaryMinus())
      {
        Message.error(loc, "illegal integer literal");
        type = ErrorType.getInstance();
      }
    }
    else if (v > Integer.MAX_VALUE)
    {
      Message.error(loc, "illegal integer literal");
      type = ErrorType.getInstance();
    }

    integerLiteral.setType(type);

    // return the node so that it can be re-assigned by its parent
    return integerLiteral;
  }

  /** Analyze an output statement. */
  @Override public Tree visit(final OutputStatement outputStatement)
  {
    // visit the expression subtree
    outputStatement.setExp((Expression) visitNode(outputStatement.getExp()));

    // avoid cascade of errors
      if (!outputStatement.getExp().getType().isErrorType())
      {
        // check that the expression is int type
        if (!outputStatement.getExp().getType().isIntegerType())
        {
          Message.error(outputStatement.getLoc(), "integer type expected");
        }
      }

    // return the node so that it can be re-assigned by its parent
    return outputStatement;
  }

  /** Analyze a unary operator. */
  @Override public Tree visit(final UnaryOperator unaryOperator)
  {
    // recurse to the child
    unaryOperator.setLeft((Expression) visitNode(unaryOperator.getLeft()));

    Expression left = unaryOperator.getLeft();
    Type leftType = left.getType();

    // avoid a cascade of errors
    if (leftType.isErrorType())
    {
      unaryOperator.setType(leftType);
      return unaryOperator;
    }

    // subtree should be integer type
    // result will be integer type

    if (!(leftType.isIntegerType()))
    {
      Message.error(unaryOperator.getLoc(), "integer operand expected");
      unaryOperator.setType(ErrorType.getInstance());
    }
    else
    {
      unaryOperator.setType(IntegerType.getInstance());
    }

    return unaryOperator;
  }

  //visit variable node
  @Override public Tree visit(final Identifier identifier) {
    //if the identifier has not been defined, bug out.
    if (symbolTable.get(identifier.getName()) == null)
    {
      Message.error(identifier.getLoc(), "undefined variable " + identifier.getName());
      identifier.setType(ErrorType.getInstance());
    }
    else{
      Type type = symbolTable.get(identifier.getName()).getType();
      identifier.setType(type);
    }
    return identifier;
  }

  //visit declaration statement node
  @Override public Tree visit(final DeclarationStatement declStatement) {

    //visit its child nodes
    for(int x = 0; x < declStatement.getDeclarations().size(); x++)
    {
      Identifier i = declStatement.getDeclarations().get(x);
      Integer depth = declStatement.getDimensionList().get(x);
      //put variable in symbol table
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
          varType = new ArrayType(declStatement.getType(), depth);
        }
        else
        {
          varType = ClassType.getInstance(declStatement.getType());
        }
      }
      if (symbolTable.get(i.getName()) == null)
      {
        symbolTable.put(i.getName(), 
              new TypeDepthPair(varType, depth)); 
      }
      else
      {
          Message.error(declStatement.getLoc(), "Duplicate declaration of main block variable " + i.getName());
      }

      visitNode(i);
      
    }

    return declStatement;
  }

  //visit assignment node
  @Override public Tree visit(final Assignment assignment) {

    //visit its child nodes
    if(assignment.getIdentifier() instanceof Identifier)
      { visitNode((Identifier) assignment.getIdentifier()); }
    else if (assignment.getIdentifier() instanceof ArrayAccess)
      { visitNode((ArrayAccess) assignment.getIdentifier()); }
    
    visitNode(assignment.getExpression());    
  

    return assignment;
  }

  /*visit if/else node
  */
  @Override public Tree visit(final IfElseStatement ifElse) {

    visitNode(ifElse.getCondition());
    if(ifElse.getCondition().getType() != IntegerType.getInstance())
    {
      Message.error(ifElse.getLoc(), "IF condition must be of Integer type");
    }
    
    visitNode(ifElse.getThen());
    visitNode(ifElse.getElse());
    
    return null;
  }

  /*visit while node
  */
  @Override public Tree visit(final WhileStatement whileStatement) {

    visitNode(whileStatement.getCondition());
    if(whileStatement.getCondition().getType() != IntegerType.getInstance())
    {
      Message.error(whileStatement.getLoc(), "WHILE condition must be of Integer type");
    }
    whileDepth ++;
    visitNode(whileStatement.getStatement());
    whileDepth --;
    
    return null;
  }

  /** Visit a break statement node*/
  @Override public Tree visit(final BreakStatement br)
  {
    if(whileDepth <= 0)
    {
      Message.error(br.getLoc(), "Break must be enclosed in a While statement");
    }
    return null;
  }

  /** Visit a continue statement node*/
  @Override public Tree visit(final ContinueStatement c)
  {
    if(whileDepth <= 0)
    {
      Message.error(c.getLoc(), "Continue must be enclosed in a While statement");
    }
    return null;
  }

  /** Visit an ArrayCreationExpression node */
  @Override public Tree visit(final ArrayCreationExpression ace)
  {
    visitNode(ace.getDimExpr());
    if (!ace.getDimExpr().getType().isIntegerType())
    {
      Message.error(ace.getLoc(), "integer expression expected for array length");
    }
    return ace;
  }

  /** visit an ArrayAccess node */
  @Override public Tree visit(final ArrayAccess aa)
  {
    Expression theArray = aa.getTheArray();
    visitNode(theArray);
    Type arrayComponentType = ErrorType.getInstance();
    int line = aa.getLoc().getLine();
    if(theArray instanceof Identifier)
    {
      TypeDepthPair idTDP = symbolTable.get(((Identifier) theArray).getName());
      aa.setBaseType((ArrayType) idTDP.getType());
      aa.setLayer(idTDP.getDepth());
      if(idTDP.getDepth() < 1)
      { 
        Message.error(aa.getLoc(), "Variable not defined as an array"); 
      }
      else if (idTDP.getType().isArrayType())
      {
        if( ((ArrayType) idTDP.getType()).getComponentType() == "int")
        {
          arrayComponentType = IntegerType.getInstance();
        }
        //ELSE IT'S PROBABLY A CLASS TYPE, but I'm not supporting that yet 
      }
      
    }
    else 
    {
      //if it's not an identifier of some kind, it's another arrayAccess
      //just match the type, the inner array access is floating the type
      //up to the out ones
      arrayComponentType = theArray.getType();
      aa.setBaseType( ((ArrayAccess) theArray).getBaseType());
      aa.setLayer( ((ArrayAccess) theArray).getLayer() - 1 );
    }
    aa.setType(arrayComponentType);
    Message.log("aa.type: " + aa.getType().encode() + ", " + line);
    Message.log("theArray.type: " + theArray.getType().encode() + ", " + line);
    visitNode(aa.getDimExpr());
    if(!aa.getDimExpr().getType().isIntegerType())
    {
      Message.error(aa.getLoc(), "Array index must be of integer type");
    }

    return aa;
  }

  /** visit a Cast node */  
  @Override public Tree visit(final Cast c)
  {
    Expression toBeCast = c.getToBeCastExpression();
    visitNode(toBeCast);
    if (c.getPresetType() != null)
    {
      if(!toBeCast.getType().isArrayType())
      {
        Message.error(c.getLoc(), "Incompatible cast types");
        c.setConversionType(Cast.ConversionType.INVALID);
      } else if (!c.getPresetType().compareArrayTypes((ArrayType) toBeCast.getType()))
      {
        Message.error(c.getLoc(), "Incompatible cast types");
        c.setConversionType(Cast.ConversionType.INVALID);
      }
      c.setType(c.getPresetType());
      c.setConversionType(Cast.ConversionType.IDENTITY);
    }
    else if (c.getParenExpression() != null)
    {
      //it should be an identifier in the parens. if it's not I think there's a problem
      
      String typeName = null;
      if(c.getParenExpression() instanceof Identifier) //if there is an identifier in the parens
      {
        //use the identifier string as the type to cast to
        typeName = ((Identifier) c.getParenExpression()).getName();
      }
      else
      { //if it's not an Identifier in the parens
        //just bail out cause something is wrong
        c.setConversionType(Cast.ConversionType.INVALID);
        c.setType(ErrorType.getInstance());
        Message.error(c.getLoc(), "Incompatible cast types");
        return c;
      }

      //if "int" is in the parens, the only legaltype to cast from is int
      if(typeName == "int")
      {
        if(toBeCast.getType() == IntegerType.getInstance())
        {
          c.setConversionType(Cast.ConversionType.IDENTITY);
          c.setType(IntegerType.getInstance());
          return c;
        }
        else
        {
          //cant cast to int from anything but int (except null?)
          c.setConversionType(Cast.ConversionType.INVALID);
          c.setType(ErrorType.getInstance());
          Message.error(c.getLoc(), "Incompatible cast types");
          return c;
        }
      }

      //if there is a class name in parens
      if(typeName != null 
        && ClassType.getInstance(typeName).wasDeclared() 
        && toBeCast.getType() instanceof ClassType)
      { 
        //check for identity conversion
        if(toBeCast.getType() == ClassType.getInstance(typeName))
        {
          c.setConversionType(Cast.ConversionType.IDENTITY);
          c.setType(ClassType.getInstance(typeName));
        }
        else
        { //determine if it's a widening, narrowing or invalid (neither type is a subtype of the other)
          ClassType toClass = ClassType.getInstance(typeName);
          ClassType fromClass = (ClassType) toBeCast.getType();
          c.setType(toClass);
          if(fromClass.isSubclassOf(toClass))
          {
            c.setConversionType(Cast.ConversionType.WIDENING);
          }
          else if (toClass.isSubclassOf(fromClass))
          {
            c.setConversionType(Cast.ConversionType.NARROWING);
          }
          else
          {
            c.setConversionType(Cast.ConversionType.INVALID);
            Message.error(c.getLoc(), "Incompatible cast types");
          }
        }
      }
      else
      {   //something is wrong so error out
          c.setConversionType(Cast.ConversionType.INVALID);
          c.setType(ErrorType.getInstance());
          Message.error(c.getLoc(), "Attempting to cast to undeclared class type.");
          return c;
      }
    }
    Message.log("Conversion type: " + c.getConversionType());
    return c; 
  }

  /** vuisit a fieldaccess node */
  @Override public Tree visit(final FieldAccess fa)
  {
    //first jjust handle array length
    visitNode(fa.getObj());
    if(fa.getObj().getType().isArrayType()) 
    {
      //don't actually visit the length identifier because it won't have been defined, but that's ok
      if(fa.getField().getName().equals("length"))
      {
        fa.setType(IntegerType.getInstance());
      }
      else
      {
        Message.error(fa.getLoc(), "Field not valid for array types");
        fa.setType(ErrorType.getInstance());
      }
    }
    else if (fa.getObj().getType().isClassType())
    {
      //TODO
      //What to do here?
      ClassType ct = (ClassType) fa.getObj().getType();
      if(!ct.wasDeclared() || ct.containsField(fa.getField().getName()))
      {
        Message.error(fa.getLoc(), "Invalid field access");
        fa.setType(ErrorType.getInstance());
      }
      else
      {
        fa.setType(ct);
      }
    }
    else
    {
      Message.error(fa.getLoc(), "Field Access not supported for this type");
      fa.setType(ErrorType.getInstance());
    }
    return fa;
  }

  @Override public Tree visit(final ClassDeclaration cd)
  {
    ClassType cdType = ClassType.getInstance(cd.getClassName());
    //visit each ClassBodyDeclaratuion. If it's a Field, add it to this thing's ClassType's list of fields
    //remember to start from the end of the list so that you add the base type's fields before the supertype's
    List<ClassBodyDeclaration> cbdList = cdType.getClassBodyDecls(true);
    for(int i = cbdList.size()-1; i >= 0; i--) //for each class body declaration
    {
      ClassBodyDeclaration cbd = cbdList.get(i);
      if(cbd instanceof FieldDeclaration)
      {
        // if it's a field...
        FieldDeclaration fd = (FieldDeclaration) cbd;
        visitNode(fd); //visit the field declaration node
        List<Identifier> fdIds = fd.getDeclarations();
        List<Integer> fdDims = fd.getDimensionList();
        for(int j = 0; j < fdIds.size(); j++) //then for each variable declared on this declaration node
        {
          //add it to the list of Name/type/Depth tuples maintained by the ClassType
          NameTypeDepth ntd = new NameTypeDepth(fdIds.get(j).getName(), fd.getType(), fdDims.get(j));
          cdType.addToFields(ntd);
        }
      }
      else
      {
        // TODO
        //if it's a method...
        //we're not equipped to handle that yet
        Message.error(cd.getLoc(), "Methods not supported yet");
      }
    }
    return cd;
  }

  @Override public Tree visit(final FieldDeclaration fd)
  {
    // I don't think I need to do much here actually 
    // all the heavy lifting is being done by the class declaration node above it
    // the related DeclarationStatement (main variable decl) is adding things
    // to the symbol table, nad since these Identifiers won't be in the symbol
    // table I don't think we even need to visit those nodes
    // just make sure if it's a field of class type that the class has been declared

    if(fd.getType() != "int" && !ClassType.getInstance(fd.getType()).wasDeclared())
    {
      Message.error(fd.getLoc(), "Undeclared class type " + fd.getType());
    }

    return fd;
  }

  @Override public Tree visit(final ClassInstanceCreationExpression cice)
  {    
    //set type of CICExpression
    ClassType ct = ClassType.getInstance(cice.getClassName());
    if(!ct.wasDeclared())
    {
      Message.error(cice.getLoc(), "Undeclared class type " + cice.getClassName());
      cice.setType(ErrorType.getInstance());
    }
    else{
      cice.setType(ct);
    }

    //TODO
    //handle constructor args at some point
    //
    return cice;
  }
}

