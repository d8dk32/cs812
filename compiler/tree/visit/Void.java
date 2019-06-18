
package tc.compiler.tree.visit;

/**
 * A class to represent "void". The class is never instantiated and
 * its variables are only assigned null. This is used as the return
 * type for visitors that return nothing.
 *
 * Not using java.lang.Void because it exists for a different reason: "to
 * hold a reference to the Class object representing the Java keyword void."
 *
 */
public final class Void
{
  // noninstantiable
  private Void() {}
}

