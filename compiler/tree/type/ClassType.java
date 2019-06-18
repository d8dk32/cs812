package tc.compiler.tree.type;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 *  A type for implementing class types.
 *
 */
public final class ClassType extends ReferenceType
{
  // map all class names to class types
  private static Map<String, ClassType> map =
    new HashMap<String, ClassType>();

  // name of class
  private String name;

  // type of the superclass
  private ClassType superClass;

  // was this class type declared?
  private boolean wasDeclared;

  /** Create/retrieve a class type. If class already exists with the
   *  given name, then return that type, else return a new type.
   *  @param name name of the class type.
   *  @return the type.
   */
  static public ClassType getInstance(String name)
  {
    // has class type for this name already been created?
    ClassType ret = map.get(name);
    if (ret != null)
    {
      // if so, just return it
      return ret;
    }
    // otherwise create the new type, put it in the map, and return it
    ret = new ClassType(name);
    map.put(name, ret);
    return ret;
  }

  /** Get the class name.
   *  @return the name of the class.
   */
  public String getName()
  {
    return name;
  }

  /** Get the super class type.
   *  @return the type of the super class.
   */
  public ClassType getSuperClass()
  {
    return superClass;
  }

  /** Was the class declared?
   *  @return whether the class has been declared or not.
   */
  public boolean wasDeclared()
  {
    return wasDeclared;
  }
  
  /** Set the super class type.
   *  @param superClass the type of the super class.
   */
  public void setSuperClass(ClassType superClass)
  {
    this.superClass = superClass;
  }

  /** Set the wasDeclared flag.
   */
  public void setWasDeclared()
  {
    this.wasDeclared = true;
  }

  /** Dump all the class types to stderr for debugging purposes.
   */
  static public void dumpClasses()
  {
    System.err.println("Dumping the class types:");
    for (String name : map.keySet())
    {
      System.err.println("  " + name);
      ClassType clss = map.get(name);
      ClassType superClass = clss.getSuperClass();
      String superName = "<null>";
      if (superClass != null) superName = superClass.getName();
      System.err.println("    " + clss + " (super is " +
        superName + ")");
    }
    System.err.println("End of dump of the class types.");
  }

  /** Return the set of class names.
   *  @return the set of class names.
   */
  static public Set<String> getClassNames()
  {
    return map.keySet();
  }

  // private constructor
  private ClassType(String name)
  {
    this.name = name;
    this.superClass = null;
    this.wasDeclared = false;
  }

  /** Return string for LLVM representation of the type.
   *  @return "%class$NAME*".
   */
  @Override public String encode()
  {
    return "%class$" + name + "*";
  }

  /** Return string for LLVM representation of the runtime type.
   *  @return "@class$VMT".
   */
  @Override public String encodeRuntimeType()
  {
    return "@" + name + "$VMT";
  }

  /** Return string for LLVM representation of the zero value.
   *  @return "null".
   */
  @Override public String encodeZeroValue()
  {
    return "null";
  }

  /** Return string to represent the type for debugging.
   *  @return the class name
   */
  @Override public String toString()
  {
    return name;
  }
}

