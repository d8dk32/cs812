
package tc.compiler.tree;

import tc.compiler.Location;
import tc.compiler.tree.visit.TreeVisitor;
import tc.compiler.tree.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 * AST block node.
 *
 */
public final class Block extends Statement
{
  // liost of statements in this block
  private List<Statement> list;

  /** Construct aa block.
   *
   *  @param loc the source code location of the statement.
   *  @param statementList the list of stements in this block
   */
  public Block(final Location loc, final List<Statement> statementList)
  {
    super(loc);
    this.list = statementList;
  }

  /** Get statement list from the node.
   *
   *  @return the list of statements in this block.
   */
  public List<Statement> getStatementList()
  {
    return list;
  }

  /** Set the statement list for the node.
   *
   *  @param list new statement lsit
   */
  public void setStatementList(List<Statement> list)
  {
    this.list = list;
  }

  /** Apply a visitor to the node.
   *
   *  @param visitor the visitor to apply.
   *
   *  @return the value generated by the visitor.
   */
  @Override public <T> T apply(TreeVisitor<T> visitor)
  {
    return visitor.visit(this);
  }
}

