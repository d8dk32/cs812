
package tc.compiler.tree.type;

/**
 *  Superclass for all reference types.
 *
 */
public abstract class ReferenceType extends Type
{
  /** This is a ReferenceType.
   *
   *  @return true
   */
  @Override public boolean isReferenceType()
  {
    return true;
  }
}

