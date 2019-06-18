
package tc.compiler.tree;

import tc.compiler.Location;
import tc.compiler.tree.visit.TreeVisitor;
import tc.compiler.tree.Statement;

/**
 * AST break statement node.
 *
 */
public final class BreakStatement extends Statement
{

  /** Construct a Break Statement 
   *
   *  @param loc the source code location of the statement.
   */
  public BreakStatement(final Location loc)
  {
    super(loc);
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

