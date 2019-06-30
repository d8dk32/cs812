
package tc.compiler.tree;


/**
 * Superclass for all AST statement nodes.
 *
 */
public abstract class ClassBodyDeclaration extends Statement
{
  /** Construct a statement tree node.
   *
   *  @param loc the source code location of the statement.
   */
  public ClassBodyDeclaration(final Location loc)
  {
    super(loc);
  }
}

