
package tc.compiler.tree.type;

/**
 *  An error type to be using when semantic errors are detected
 *  in expressions. When checks are made above the node labeled
 *  with the error type, then no further error messages should
 *  be emitted.
 *
 */
public final class ErrorType extends Type
{
  // singleton
  private static ErrorType INSTANCE = new ErrorType();

  /** Get the singleton instance.
   *  @return singleton instance.
   */
  public static ErrorType getInstance()
  {
    return INSTANCE;
  }

  // private constructor
  private ErrorType() {}

  /** Always returns true.
   *  @return true;
   */
  @Override public boolean isErrorType()
  {
    return true;
  }

  /** Return string to represent the type for debugging.
   *  @return "[error]".
   */
  @Override public String toString()
  {
    return "[error]";
  }
}

