
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
    if(componentType == "int" && depth == 1)
    {
      return "%array$int";
    }
    else
    {
      return "%array$ref";
    }
  }

  /** get the array nesting depth 
  * @return the depth
  */
  @Override public int getDepth()
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
    return "@arrayVMT";
  }

  public String encodeComponentRuntimeType()
  {
    return "@" + this.componentType + "VMT";
  }

  public String encodeComponentType()
  {
    if(this.componentType == "int")
      return "i32";
    else 
    {
      //it must be some kind of reference type like a class
      return null;
    }
  }

  /** This is an ArrayType.
   *
   *  @return true
   */
  @Override public boolean isArrayType()
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

  public String getComponentType()
  {
    return this.componentType;
  }

  /* utility method for determing if 2 array types are 'equal' */
  public boolean compareArrayTypes(ArrayType aa)
  {
    boolean result = false;
    if(aa.getComponentType() == this.componentType && aa.getDepth() == this.depth)
    { result = true; }
    return result;
  }
}

