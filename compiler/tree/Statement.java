
package tc.compiler.tree;

import tc.compiler.Location;

/**
 * Superclass for all AST statement nodes.
 *
 */
public abstract class Statement extends Node
{
  /** Construct a statement tree node.
   *
   *  @param loc the source code location of the statement.
   */
  public Statement(final Location loc)
  {
    super(loc);
  }
}

