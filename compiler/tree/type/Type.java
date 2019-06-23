
package tc.compiler.tree.type;

import tc.compiler.Message;

/**
 *  Superclass for all types.
 *
 */
public abstract class Type
{
  /** Returns false so need to override this in ReferenceType.
   *  @return false.
   */
  public boolean isReferenceType()
  {
    return false;
  }

  /** Returns false so need to override this in IntegerType.
   *  @return false.
   */
  public boolean isIntegerType()
  {
    return false;
  }

  /** Returns false so need to override this in ErrorType.
   *  @return false.
   */
  public boolean isErrorType()
  {
    return false;
  }

  /** Returns a string for the LLVM type to represent this type. Need to
   *  override this method for types that have simple LLVM types.
   *  @return a string for the LLVM type to represent this type.
   */
  public String encode()
  {
    Message.bug("cannot encode type");
    return null;
  }

  /** Return string for LLVM representation of the runtime type.
   *  @return a string to represent the runtime type.
   */
  public String encodeRuntimeType()
  {
    Message.bug("cannot encode the runtime type");
    return null;
  }

  /** Returns a string for the LLVM zero value of this type. Need to
   *  override this method for types that have simple LLVM types.
   *  @return a string for the LLVM nil value for this type.
   */
  public String encodeZeroValue()
  {
    Message.bug("cannot encode zero value");
    return null;
  }

  public boolean isArrayType()
  {
    return false;
  }

  public int getDepth()
  {
    return 0;
  }

}

