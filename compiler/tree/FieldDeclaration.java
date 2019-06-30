
package tc.compiler.tree;


/**
 * Superclass for all AST statement nodes.
 *
 */
public class FieldDeclaration extends ClassBodyDeclaration
{
  /** Construct a statement tree node.
   *
   *  @param loc the source code location of the statement.
   */
  public FieldDeclaration(final Location loc)
  {
    super(loc);
  }
}

