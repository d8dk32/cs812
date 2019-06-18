
package tc.compiler.tree;

import tc.compiler.Location;
import tc.compiler.tree.type.Type;

/**
 * Superclass for all AST expression nodes.
 *
 */
public abstract class Expression extends Node
{
  // all expressions have a type
  private Type type;

  /** Construct an expression node. The type field is set to null.
   *
   *  @param loc source code location of the node.
   */
  public Expression(final Location loc)
  {
    super(loc);
    type = null;
  }

  /** Set the type.
   *
   *  @param type the type to put into the expression node.
   */
  public void setType(Type type)
  {
    this.type = type;
  }

  /** Get the type of the expression node.
   *
   *  @return the type of the expression node.
   */
  public Type getType()
  {
    return type;
  }

}

