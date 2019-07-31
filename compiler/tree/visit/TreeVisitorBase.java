
package tc.compiler.tree.visit;

import tc.compiler.tree.*;

import java.util.List;

/**
 * Base implementation for AST visitors. Performs complete traversal
 * but does nothing. Parameterized by return value.
 * <p>
 * The "visit" method is overloaded for every tree node type.
 */
public class TreeVisitorBase<T> implements TreeVisitor<T>
{
  /** Called for every node. Override to add pre- and/or post-processing,
   *  or to return a non-null value.
   *  @param node node to be visited.
   *  @return null.
   */
  protected T visitNode(final Tree node)
  {
    node.apply(this);
    return null;
  }

  /** Visit a list of ASTs.
   *  (Uses a wildcard to allow general use, with list of Statements, list
   *   of Expressions, etc.)
   *  @param nodes list to be visited.
   *  @return null. Override this method to return a list.
   */
  protected List<T> visitEach(final Iterable<?> nodes)
  {
    for (final Object node : nodes)
    {
      visitNode((Tree) node);
    }
    return null;
  }
 
  /** Visit a binary operator. Override to provide specific behavior. */
  @Override public T visit(final BinaryOperator binaryOperator)
  {
    visitNode(binaryOperator.getLeft());
    visitNode(binaryOperator.getRight());
    return null;
  }

  /** Visit a class declaration. Override to provide specific behavior. */
  @Override public T visit(final ClassDeclaration classDeclaration)
  {
    return null;
  }

  /** Visit a compilation unit (the root of the AST). Override to provide
      specific behavior. */
  @Override public T visit(final CompilationUnit compilationUnit)
  {
    visitEach(compilationUnit.getClasses());
    visitEach(compilationUnit.getMainBlock());
    return null;
  }

  /** Visit an integer literal. Override to provide specific behavior. */
  @Override public T visit(final IntegerLiteral integerLiteral)
  {
    return null;
  }

  /** Visit an output statement. Override to provide specific
   *  behavior.
   */
  @Override public T visit(final OutputStatement outputStatement)
  {
    visitNode(outputStatement.getExp());
    return null;
  }

  /** Visit a unary operator. Override to provide specific behavior. */
  @Override public T visit(final UnaryOperator unaryOperator)
  {
    visitNode(unaryOperator.getLeft());
    return null;
  }

  /** Visit a variable. Override to provide specific behavior. */
  @Override public T visit(final Identifier identifier)
  {
    return null;
  }

  /** Visit a delaration statement node*/
  @Override public T visit(final DeclarationStatement declStatement)
  {
    visitEach(declStatement.getDeclarations());
    return null;
  }

  /** Visit an assignment expression node node*/
  @Override public T visit(final Assignment assignment)
  {
    visitNode(assignment.getExpression());
    return null;
  }

  /** Visit an expression statement node*/
  @Override public T visit(final ExpressionStatement exprStatement)
  {
    visitNode(exprStatement.getExpression());
    return null;
  }

  /** Visit a block node*/
  @Override public T visit(final Block block)
  {
    visitEach(block.getStatementList());
    return null;
  }

  /** Visit an empty statement node*/
  @Override public T visit(final EmptyStatement empty)
  {
    return null;
  }

  /*visit if/else node
  */
  @Override public T visit(final IfElseStatement ifElse) {

    visitNode(ifElse.getCondition());
    visitNode(ifElse.getThen());
    visitNode(ifElse.getElse());
    
    return null;
  }

  /*visit if/else node
  */
  @Override public T visit(final WhileStatement whileStatement) {

    visitNode(whileStatement.getCondition());
    visitNode(whileStatement.getStatement());
    
    return null;
  }

  /** Visit a break statement node*/
  @Override public T visit(final BreakStatement br)
  {
    return null;
  }

  /** Visit a continue statement node*/
  @Override public T visit(final ContinueStatement c)
  {
    return null;
  }

  /** Visit an arrayCreationExpression. Override to provide specific behavior. */
  @Override public T visit(final ArrayCreationExpression arrayCreationExpr)
  {
    return null;
  }

  /** Visit an arrayCreationExpression. Override to provide specific behavior. */
  @Override public T visit(final ArrayAccess arrayAccess)
  {
    return null;
  }

  /**visit a cast node. override to provide specific behavior */
  @Override public T visit(final Cast cast)
  {
    return null;
  }

  /**visit a fieldAccess node. override to provide specific behavior */
  @Override public T visit(final FieldAccess fieldAccess)
  {
    return null;
  }

  /**visit a fieldDeclaration node. override to provide specific behavior */
  @Override public T visit(final FieldDeclaration fieldDeclaration)
  {
    return null;
  }
  
  /** visit a ClassInstanceCreationExpression. Override to provide specific behavior */
  @Override public T visit(final ClassInstanceCreationExpression cice)
  {
    return null;
  }

  /** visit a MethodDeclaration. Override to provide specific behavior */
  @Override public T visit(final MethodDeclaration md)
  {
    return null;
  }

  /** visit a ConstrucotrDeclaration. Override to provide specific behavior */
  @Override public T visit(final ConstructorDeclaration cd)
  {
    return null;
  }
}

