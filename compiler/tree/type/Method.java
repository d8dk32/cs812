
package tc.compiler.tree.type;
import java.util.List;
import tc.compiler.tree.*;

/**
 *  A type representing Method
 *
 */
public final class Method extends Type
{

  String returnTypeString;
  int returnTypeDepth;
  Type returnType;
  ClassType containingClass = null;
  List<NameTypeDepth> paramList;
  String methodName;


  public Method(String returnType, int returnTypeDepth, String methodName, List<NameTypeDepth> params) {
    this.returnTypeString = returnType;
    this.returnTypeDepth = returnTypeDepth;
    this.methodName = methodName;
    this.paramList = params;
    this.returnType = Type.createType(returnType, returnTypeDepth);
  }

  /** Return string for LLVM representation of the type.
   *  @return "".
   */
  @Override public String encode()
  {
    return "";
  }

  /** Return string for LLVM representation of the runtime type.
   *  @return "@".
   */
  @Override public String encodeRuntimeType()
  {
    return "";
  }


  /** Return string to represent the type for debugging.
   *  @return "return type + name + params".
   */
  @Override public String toString()
  {
    String outString = "";
    if(this.returnType.isArrayType())
    {
      outString += ((ArrayType) this.returnType).toFancyString() + " ";
    }
    else
    {
      outString += this.returnType.toString() + " ";
    }

    outString += this.methodName + "(";
    for(NameTypeDepth ntd : this.paramList)
    {
      outString += ntd.toString() + ", ";
    }
    outString += ")";

    return outString;
  }

  //Get and set the class that directly contains this method
  public void setContainingClass(ClassType ct)
  {
    this.containingClass = ct;
  }

  public ClassType getConatiningClass()
  {
    return this.containingClass;
  }

  public String getEncodedName()
  {
    String encName = "@";
    encName += this.containingClass.getName() + "$";
    encName += this.methodName + "$";
    for(NameTypeDepth ntd : this.paramList)
    {
      encName += ntd.getType();
      if(ntd.getDepth() > 0)
        encName += "arr";
      encName += "$";
    }
    return encName;
  }

  public String getName()
  {
    return this.methodName;
  }

  public List<NameTypeDepth> getParams()
  {
    return this.paramList;
  }

}

