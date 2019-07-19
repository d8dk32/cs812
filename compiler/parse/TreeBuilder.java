package tc.compiler.parse;

import tc.compiler.Location;
import tc.compiler.Message;
import tc.compiler.tree.*;
import tc.compiler.tree.type.*;

import java.util.List;

/**
 * Provides static methods for building AST nodes.
 */
public final class TreeBuilder
{
  // do not allow instances to be constructed
  private TreeBuilder() { }

 /** Build a binary operator.
   *
   *  @param  loc    location in source code (file, line, column).
   *  @param  op     the unary operator.
   *  @param  left   the left subtree of the operator.
   *  @param  right  the right subtree of the operator.
   *  @return tree node for the binary operator.
   */
  public static BinaryOperator buildBinaryOperator(
    final Location loc, final Binop op, final Expression left,
    final Expression right)
  {
    Message.log("TreeBuilder: BinaryOperator " + op);
    return new BinaryOperator(loc, op, left, right);
  }

  /** Build the root node of the AST.
   *
   *  @param  loc location in source code (file, line, column).
   *  @param  mainBlock list of statements for the main block.
   *  @param  classes list of class declarations in the program.
   *  @return tree node for the root of the AST.
   */
  public static CompilationUnit buildCompilationUnit(final Location loc,
    final List<Statement> mainBlock, final List<ClassDeclaration> classes)
  {
    Message.log("TreeBuilder: CompilationUnit");
    return new CompilationUnit(loc, mainBlock, classes);
  }

  /** Build an integer literal node from a decimal string.
   *
   *  @param  loc location in source code (file, line, column).
   *  @param  value value of the literal as a String.
   *  @return tree node for an integer literal.
   */
  public static IntegerLiteral buildIntegerLiteral(final Location loc,
    final String value)
  {
    Message.log("TreeBuilder: IntegerLiteral " + value);
    return new IntegerLiteral(loc, value);
  }

  /** Build an output statement AST node.
   *
   *  @param  loc location in source code (file, line, column).
   *  @param  exp expression subtree.
   *  @return tree node for an output statement.
   */
  public static OutputStatement buildOutputStatement(final Location loc,
    final Expression exp)
  {
    Message.log("TreeBuilder: OutputStatement");
    return new OutputStatement(loc, exp);
  }

  /** Build a unary operator.
   *
   *  @param  loc   location in source code (file, line, column).
   *  @param  op    the unary operator.
   *  @param  left  the subtree of the operator.
   *  @return tree node for the unary operator.
   */
  public static UnaryOperator buildUnaryOperator(
    final Location loc, final Unop op, final Expression left)
  {
    Message.log("TreeBuilder: UnaryOperator " + op);

    // check if child is an integer literal (to handle -2147483648)
    if (left instanceof IntegerLiteral)
    {
      ((IntegerLiteral) left).setParentIsUnaryMinus();
    } else if (left instanceof Identifier)
    {
      ((Identifier) left).setParentIsUnaryMinus();
    } else if (left instanceof ArrayAccess)
    {
      ((ArrayAccess) left).setParentIsUnaryMinus();
    }
    return new UnaryOperator(loc, op, left);
  }

  /** build identifier node 
   * @param loc location in source code
   * @param id variable name (identifier)
   * @return tree node for the variable
   */
   public static Identifier buildIdentifier(final Location loc, final String id)
   {
     return new Identifier(loc, id);
   }

   /** build  declaration statement node
    * @param loc location in source code
    * @param decList a list of names being declared
    * @param type the type of the entitity being declared
    * @param addDims the number of additional dimensions being tacked on (i.e int[][][] means addDims = 3)
    * @param dimList a list of dimensions (array depth) for each variable declared. 
    * @return tree node for declaration statement
   */
   public static DeclarationStatement buildDeclarationStatement(final Location loc, String type, int addDims, List<Identifier> decList, List<Integer> dimList)
   {
     //return declarationStatemtnes node
     for(Identifier i : decList)
     {
       i.setLeftSide(true);
     }
     return new DeclarationStatement(loc, type, addDims, decList, dimList);
   }

   /** Build assignment expression node
    * @param loc location in source code
    * @param leftSide identifier being assigned to
    * @param expr the value going into the left side
    * @return tree node for declaration statement
    */
  public static Assignment buildAssignment(final Location loc, final LeftSide leftSide, final Expression expr)
  {
    return new Assignment(loc, leftSide, expr); 
  }

  /** Build expression statement node
    * @param loc location in source code
    * @param expr the value going into the left side
    * @return tree node for declaration statement
    */
  public static ExpressionStatement buildExpressionStatement(final Location loc, final Expression expr)
  {
    return new ExpressionStatement(loc, expr); 
  }

  /** Build block node
    * @param loc location in source code
    * @param statementList the list of statements inside this bloc
    * @return tree node for block
    */
  public static Block buildBlock(final Location loc, final List<Statement> statementList)
  {
    return new Block(loc, statementList); 
  }

  /** Build empty statement node
    * @param loc location in source code
    * @return tree node for empty statement
    */
  public static EmptyStatement buildEmptyStatement(final Location loc)
  {
    return new EmptyStatement(loc); 
  }

  /** build new IfElseStatement
    * @param loc location in source code
    * @param condition the condition on which the IF hinges
    * @param thenStatement the statement to evaulate IF true
    * @param elseStatement the statement to evaluate IF false
    * @return tree node for IfELseStatement
   */
  public static IfElseStatement buildIfElseStatement(final Location loc, final Expression condition, 
                                                      final Statement thenStatement, final Statement elseStatement)
  {
    return new IfElseStatement(loc, condition, thenStatement, elseStatement);
  }

  /** build new WhileStatement
    * @param loc location in source code
    * @param condition the condition on which the while hinges
    * @param statement the statement to execute while the condition is true
    * @return tree node for WhileStatement
   */
  public static WhileStatement buildWhileStatement(final Location loc, final Expression condition, 
                                                      final Statement statement)
  {
    return new WhileStatement(loc, condition, statement);
  }

  /**build new Break statement
    * @param loc the location in the source code
    * @return a Break statement tree node
   */
  public static BreakStatement buildBreakStatement(final Location loc)
  {
    return new BreakStatement(loc);
  }

  /**build new Continue statement
    * @param loc the location in the source code
    * @return a Continue statement tree node
   */
  public static ContinueStatement buildContinueStatement(final Location loc)
  {
    return new ContinueStatement(loc);
  }

  /**build new array creation expression
    * @param loc the location in the source code
    *  @param type the type of the array getting created
    *  @param dimExpr the expression inside the first dimension
    *  @param numDims the number of dimensions on the array being craeted
    * @return a ArrayCreationExpression tree node
   */
  public static ArrayCreationExpression buildArrayCreationExpression(final Location loc, final Type type, Expression dimExpr, Integer numDims)
  {
    return new ArrayCreationExpression(loc, type, dimExpr, numDims);
  }

  /**build new array access expression
    * @param loc the location in the source code
    *  @param arr the array being accessed
    *  @param dimExpr the address into this array
    * @return an ArrayAccess expression tree node
   */
  public static ArrayAccess buildArrayAccess(final Location loc, final Expression arr, Expression dimExpr)
  {
    return new ArrayAccess(loc, arr, dimExpr);
  }

  /**build new cast expression
    * @param loc the location in the source code
    *  @param at the array type in parens if there is one
    *  @param pe the paren expression if there's not an array type
    *  @param toBeCast the expression intended to be cast
    * @return a cast expression tree node
   */
  public static Cast buildCast(final Location loc, final ArrayType at, Expression pe, Expression toBeCast)
  {
    if(at == null)
    {
      return new Cast(loc, pe, toBeCast);
    }
    else
    {
      return new Cast(loc, at, toBeCast);
    }

  }

  /**build new field access expression
    * @param loc the location in the source code
    *  @param obj the object containg the field
    *  @param field the field itself
    * 
    * @return a fieldAccess expression tree node
   */
   public static FieldAccess buildFieldAccess(final Location loc, Expression obj, Identifier field)
   {
     return new FieldAccess(loc, obj, field);
   }
   
  
   /* build new class declaration node */
   public static ClassDeclaration buildClassDeclaration(final Location loc, Identifier i, Identifier superI, List<ClassBodyDeclaration> cb)
   {
     return new ClassDeclaration(loc, i, superI, cb);
   }


   /* build new field declaration node, very similar to a main variable declaration */
   public static FieldDeclaration buildFieldDeclaration(final Location loc, String type, int addDims, List<Identifier> decList, List<Integer> dimList)
   {
     return new FieldDeclaration(loc, type, addDims, decList, dimList);
   }

   /* build new class instance creation expression */
   public static ClassInstanceCreationExpression buildClassInstanceCreationExpression(final Location loc, String className, List<Expression> args)
   {
     return new ClassInstanceCreationExpression(loc, className, args);
   }

   /* build new method declaration instance */
   public static MethodDeclaration buildMethodDeclaration(final Location loc, String type, int typeDims String name, List<NameTypeDepth> params, Block body)
   {
     return new MethodDeclaration(loc, type, typeDims, name, params, body);
   }

}
