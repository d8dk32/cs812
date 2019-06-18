
package tc.compiler.tree.type;

/**
 *  A type implementing the set of all signed 32-bit integers.
 *
 */
public final class ArrayType extends ReferenceType
{
  private String componentType; //string representing the type of the components of the array
  private int depth;

  // constructor
  public ArrayType(String componentType, int depth) {
    this.componentType = componentType;
    this.depth = depth;
  }

  /** Return string for LLVM representation of the type.
   *  @return the string representing the component type.
   */
  @Override public String encode()
  {
    return this.componentType;
  }

  /** get the array nesting depth 
  * @return the depth
  */
  public int getDepth()
  {
    return this.depth;
  }

  /** set the array nesting depth 
  * @param newDepth the new depth
  */
  public void setDepth(int newDepth)
  {
    this.depth = newDepth;
  }

  /** Return string for LLVM representation of the runtime type.
   *  @return "@intVMT".
   */
  @Override public String encodeRuntimeType()
  {
    return "@" + this.componentType + "VMT";
  }

  /** This is an ArrayType.
   *
   *  @return true
   */
  public boolean isArrayType()
  {
    return true;
  }

  /** Return string to represent the type for debugging.
   *  @return "int".
   */
  @Override public String toString()
  {
    return this.componentType;
  }
}

