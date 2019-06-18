
package tc.compiler.tree;

import tc.compiler.Location;
import tc.compiler.tree.visit.TreeVisitor;
import tc.compiler.tree.type.*;

/**
 * AST integer literal expression leaf node.
 *
 */
public final class IntegerLiteral extends Expression
{
  // value of the literal as a String
  private String value;

  // may be set to true in TreeBuilder
  // used to detect that -2147483648 is not an error
  private boolean parentIsUnaryMinus = false;

  /** Construct a integer literal tree node.
   *
   *  @param loc the source code location of the integer literal.
   *  @param value the value of the integer literal.
   */
  public IntegerLiteral(final Location loc, final String value)
  {
    super(loc);
    this.value = value;
    this.setType(IntegerType.getInstance());
  }

  /** Get the value of the integer literal.
   *
   *  @return the value of the integer literal.
   */
  public String getValue()
  {
    return value;
  }

  /** Is the parent a unary minus?
   *
   *  @return true if so, and false otherwise
   */
  public boolean isParentUnaryMinus()
  {
    return parentIsUnaryMinus;
  }

  /** Remember that the parent is a unary minus.
   */
  public void setParentIsUnaryMinus()
  {
    this.parentIsUnaryMinus = true;
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

