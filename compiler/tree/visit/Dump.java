
package tc.compiler.tree.visit;

import tc.compiler.tree.*;
import tc.compiler.tree.type.*;

import java.io.PrintWriter;
import java.util.List;

/**
 * Dump an AST to a stream. Uses prefix order with indentation.
 * <p>
 * Using Void for the type parameter because there is no return type.
 */
public final class Dump extends TreeVisitorBase<Void>
{
  // where to write the dump to
  private PrintWriter writer;

  // current indentation amount
  private int indentation;

  // how much to increment the indentation by at each level
  // using an increment of zero would mean no indentation
  private int increment;

  /** Initiate the dump even to the left margin and set the increment
   *  indentation amount to two spaces.
   *
   *  @param writer where to write the dump to.
   */
  public Dump(final PrintWriter writer)
  {
    this(writer, 0, 2);
  }

  /** Initiate the dump at a specific distance from the left margin and
   *  set the increment indentation amount to a specific value.
   *
   *  @param writer      where to write the dump to.
   *  @param indentation initial indentation amount.
   *  @param increment   increment indentation amount.
   */
  public Dump(final PrintWriter writer, final int indentation,
    final int increment)
  {
    this.writer = writer;
    this.indentation = indentation;
    this.increment = increment;
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

  /** Visit a list of ASTs and dump them in order. Uses a wildcard for
   *  generality: list of Statements, list of Expressions, etc.
   *
   *  @param nodes list of nodes to be visited.
   *  @return always returns null.
   */
  @Override public List<Void> visitEach(final Iterable<?> nodes)
  {
    for (final Object node : nodes)
    {
      visitNode((Tree) node);
    }
    return null;
  }
 
  /** Dump a binary operator. */
  @Override public Void visit(final BinaryOperator binaryOperator)
  {
    emit(binaryOperator.getOpString() + " (" + binaryOperator.getType() + ") ");
    visitNode(binaryOperator.getLeft());
    visitNode(binaryOperator.getRight());
    return null;
  }

  /** Dump a compilation unit. */
  @Override public Void visit(final CompilationUnit compilationUnit)
  {
    emit("CompilationUnit");
    emit("[classes]");
    indentation += increment;
    visitEach(compilationUnit.getClasses());
    indentation -= increment;
    emit("[main block]");
    indentation += increment;
    visitEach(compilationUnit.getMainBlock());
    indentation -= increment;
    return null;
  }

  /** Dump an integer literal. */
  @Override public Void visit(final IntegerLiteral integerLiteral)
  {
    emit("IntegerLiteral (" + integerLiteral.getType() + ") " +
       integerLiteral.getValue());
    return null;
  }

  /** Dump an output statement. */
  @Override public Void visit(final OutputStatement outputStatement)
  {
    emit("OutputStatement");
    indentation += increment;
    visitNode(outputStatement.getExp());
    indentation -= increment;
    return null;
  }

  /** Dump a unary operator. */
  @Override public Void visit(final UnaryOperator unaryOperator)
  {
    emit(unaryOperator.getOpString() + " (" + unaryOperator.getType() + ") ");
    visitNode(unaryOperator.getLeft());
    return null;
  }

  //visit variable node
  @Override public Void visit(final Identifier identifier)
  {
    emit("Identifier " + identifier.getName());
    return null;
  }

  /*visit declaration statement node
  */
  @Override public Void visit(final DeclarationStatement declStatement) {

    //visit its child nodes
    //for(Identifier i : declStatement.getDeclarations())
    for(int x = 0; x < declStatement.getDeclarations().size(); x++)
    {
      Identifier i = declStatement.getDeclarations().get(x);
      String dims = "";
      for(int d = 0; d < declStatement.getDimensionList().get(x); d++)
      {
        dims += "[]";
      }
      emit("Declaration: " + declStatement.getType().toString() + dims);
      indentation += increment;
      visitNode(i);
      indentation -= increment;
    }

    return null;
  }

  /*visit expression statement node
  */
  @Override public Void visit(final ExpressionStatement exprStatement) {

      emit("ExpressionStatement: ");
      indentation += increment;
      visitNode(exprStatement.getExpression());
      indentation -= increment;

      return null;
  }


  /*visit assignment node
  */
  @Override public Void visit(final Assignment assignment) {

    //visit its child nodes
    emit("Assignment: ");
    indentation += increment;
    //visit its child nodes
    if(assignment.getIdentifier() instanceof Identifier)
      { visitNode((Identifier) assignment.getIdentifier()); }
    else if (assignment.getIdentifier() instanceof ArrayAccess)
      { visitNode((ArrayAccess) assignment.getIdentifier()); }
    visitNode(assignment.getExpression());
    indentation -= increment;

    return null;
  }

  /*visit block node
  */
  @Override public Void visit(final Block block) {

    //visit its child nodes
    emit("Start Block -> ");
    indentation += increment;
    visitEach(block.getStatementList());
    indentation -= increment;
    emit("End Block <- ");

    return null;
  }

  /*visit emptyStatement node
  */
  @Override public Void visit(final EmptyStatement empty) {

    emit("Empty Statement...");

    return null;
  }

  /*visit if/else node
  */
  @Override public Void visit(final IfElseStatement ifElse) {

    emit("IF:");
    indentation += increment;
    visitNode(ifElse.getCondition());
    indentation -= increment;
    emit("THEN:");
    indentation += increment;
    visitNode(ifElse.getThen());
    indentation -= increment;
    emit("ELSE:");
    indentation += increment;
    visitNode(ifElse.getElse());
    indentation -= increment;

    return null;
  }

  /*visit While node
  */
  @Override public Void visit(final WhileStatement whileStatement) {

    emit("While:");
    visitNode(whileStatement.getCondition());
    indentation += increment;
    visitNode(whileStatement.getStatement());
    indentation -= increment;
  
    return null;
  }

  /** Visit a break statement node*/
  @Override public Void visit(final BreakStatement br)
  {
    emit("Break");
    return null;
  }

  /** Visit a continue statement node*/
  @Override public Void visit(final ContinueStatement c)
  {
    emit("Continue");
    return null;
  }

  /** Visit an ArrayCreationExpression node */
  @Override public Void visit(final ArrayCreationExpression ace)
  {
    int numDims = ace.getNumDims()-1;
    String dimString = "";
    while(numDims > 0)
    {
      dimString += "[]";
      numDims--;
    }
    emit("Array Creation: [");
    indentation += increment;
    visitNode(ace.getDimExpr());
    emit("]" + dimString);
    indentation -= increment;
    return null;
  }

  /** visit and array access node */
  @Override public Void visit(final ArrayAccess aa)
  {
    emit("ArrayAccess: ");
    indentation += increment;
    emit("array: ");
    visitNode(aa.getTheArray());
    emit("address: ");
    visitNode(aa.getDimExpr());
    indentation -= increment;
    return null;
  }

  /** visit a cast node */
  @Override public Void visit(final Cast c)
  {
    emit("Cast:");
    indentation += increment;
    visitNode(c.getToBeCastExpression());
    emit("to");
    if(c.getPresetType() != null)
    {
      emit(c.getPresetType().toFancyString());
    }
    else if (c.getParenExpression() != null)
    {
      visitNode(c.getParenExpression());
      emit(c.getParenExpression().getType().toString());
    }
    indentation -= increment;
    return null;
  }

  /** visit a field access node */
  @Override public Void visit(final FieldAccess fa)
  {
    emit("Field Access: ");
    indentation += increment;
    visitNode(fa.getObj());
    emit(".");
    visitNode(fa.getField());
    indentation -= increment;
    return null;
  }

}

