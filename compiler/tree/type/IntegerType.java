
package tc.compiler.tree.type;

/**
 *  A type implementing the set of all signed 32-bit integers.
 *
 */
public final class IntegerType extends Type
{
  // singleton
  private static final IntegerType INSTANCE = new IntegerType();

  /** Get the singleton instance.
   *  @return singleton instance.
   */
  public static IntegerType getInstance()
  {
    return INSTANCE;
  }

  // private constructor
  private IntegerType() {}

  /** Return string for LLVM representation of the type.
   *  @return "i32".
   */
  @Override public String encode()
  {
    return "i32";
  }

  /** Return string for LLVM representation of the runtime type.
   *  @return "@intVMT".
   */
  @Override public String encodeRuntimeType()
  {
    return "@intVMT";
  }

  /** Return string for LLVM representation of the zero value.
   *  @return "0".
   */
  @Override public String encodeZeroValue()
  {
    return "0";
  }

  /** This is an IntegerType.
   *
   *  @return true
   */
  @Override public boolean isIntegerType()
  {
    return true;
  }

  /** Return string to represent the type for debugging.
   *  @return "int".
   */
  @Override public String toString()
  {
    return "int";
  }

}

