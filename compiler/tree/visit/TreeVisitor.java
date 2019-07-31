
package tc.compiler.tree.visit;

import tc.compiler.tree.*;

/**
 * All visitor classes for ASTs will implement this interface, which
 *   is parameterized by return type.
 *
 */
public interface TreeVisitor<T>
{
  T visit(BinaryOperator binaryOperator);

  T visit(ClassDeclaration classDeclaration);

  T visit(CompilationUnit compilationUnit);

  T visit(IntegerLiteral integerLiteral);

  T visit(OutputStatement outputStatement);

  T visit(UnaryOperator unaryOperator);
  
  T visit(Identifier identifier);

  T visit(DeclarationStatement declStatement);

  T visit(Assignment assignment);

  T visit(ExpressionStatement exprStatement);

  T visit(Block block);

  T visit(EmptyStatement empty);

  T visit(IfElseStatement ifElse);

  T visit(WhileStatement whileStatement);

  T visit(BreakStatement breakStatement);

  T visit(ContinueStatement continueStatement);

  T visit(ArrayCreationExpression arrayCreationExpr);

  T visit(ArrayAccess arrayAccess);

  T visit(Cast cast);

  T visit(FieldAccess fieldAccess);

  T visit(FieldDeclaration fieldDecl);

  T visit(ClassInstanceCreationExpression cice);

  T visit(MethodDeclaration md);

  T visit(ConstructorDeclaration cd);

}

