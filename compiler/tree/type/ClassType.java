package tc.compiler.tree.type;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import tc.compiler.tree.*;
import java.util.List;
import java.util.ArrayList;
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

  //list of class member declarations
  private List<ClassBodyDeclaration> classBodyDecls = new ArrayList<>();
  private List<FieldDeclaration> fieldDeclarations = new ArrayList<>();
  private List<MethodDeclaration> methodDeclarations = new ArrayList<>();
  private List<ConstructorDeclaration> constructorDeclarations = new ArrayList<>();

  //list of Fields. will be added in type->supertype order, i.e. reverse order of what
  //getClassBodyDecls() returns
  private List<NameTypeDepth> fields = new ArrayList<NameTypeDepth>();

  private List<Method> methods = new ArrayList<Method>();

  private List<ConstructorDeclaration> constructors = new ArrayList<ConstructorDeclaration>();

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

  /* class body declarations setter */
  public void setClassBodyDecls(List<ClassBodyDeclaration> declList)
  {
    this.classBodyDecls = declList;
  }

  /* class body declarations getter */
  public List<ClassBodyDeclaration> getClassBodyDecls(boolean includeSupers)
  {
    if(!includeSupers)
    {
      return this.classBodyDecls;
    }

    //make sure to return the super class(es) body decls too
    List<ClassBodyDeclaration> superDecls = new ArrayList<ClassBodyDeclaration>();
    if(this.superClass != null)
    {
      superDecls = superClass.getClassBodyDecls(true);
    }
  
    superDecls.addAll(this.classBodyDecls);
    return superDecls;
  }

  //get list of field declarations
  public List<FieldDeclaration> getFieldDecls(boolean includeSupers)
  {
    if(!includeSupers)
    {
      return this.fieldDeclarations;
    }

    //make sure to return the super class(es) body decls too
    List<FieldDeclaration> superDecls = new ArrayList<FieldDeclaration>();
    if(this.superClass != null)
    {
      superDecls = superClass.getFieldDecls(true);
    }
  
    superDecls.addAll(this.fieldDeclarations);
    return superDecls;
  }

  //get list of method declarations
  public List<MethodDeclaration> getMethodDecls(boolean includeSupers)
  {
    if(!includeSupers)
    {
      return this.methodDeclarations;
    }

    //make sure to return the super class(es) body decls too
    List<MethodDeclaration> superDecls = new ArrayList<MethodDeclaration>();
    if(this.superClass != null)
    {
      superDecls = superClass.getMethodDecls(true);
    }
  
    superDecls.addAll(this.methodDeclarations);
    return superDecls;
  }

  //get list of constructor declarations
  public List<ConstructorDeclaration> getConstructorDecls(boolean includeSupers)
  {
    if(!includeSupers)
    {
      return this.constructorDeclarations;
    }

    //make sure to return the super class(es) body decls too
    List<ConstructorDeclaration> superDecls = new ArrayList<ConstructorDeclaration>();
    if(this.superClass != null)
    {
      superDecls = superClass.getConstructorDecls(true);
    }
  
    superDecls.addAll(this.constructorDeclarations);
    return superDecls;
  }

  //utility methods to add class memeber declarations to their respective lists
  public void addClassMemberDeclaration(ClassBodyDeclaration cbd)
  {
    if (cbd instanceof FieldDeclaration)
      this.fieldDeclarations.add((FieldDeclaration) cbd);
    else if (cbd instanceof MethodDeclaration)
    {
      MethodDeclaration md = (MethodDeclaration) cbd;
      md.setEnclosingClass(this);
      this.methodDeclarations.add(md);
    }
    else if (cbd instanceof ConstructorDeclaration)
      this.constructorDeclarations.add((ConstructorDeclaration) cbd);
  }

  public List<NameTypeDepth> getFields()
  {
    return this.fields;
  }

  public void addToFields(NameTypeDepth ntd)
  {
    this.fields.add(ntd);
  }

  public List<Method> getMethods()
  {
    return this.methods;
  }

  public void addToMethods(Method meth)
  {
    this.methods.add(meth);
  }

  public void addToConstructors(ConstructorDeclaration cd)
  {
    this.constructors.add(cd);
  }

  public List<ConstructorDeclaration> getConstructors()
  {
    return this.constructors;
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
      for (ClassBodyDeclaration cbd : clss.getClassBodyDecls(false))
      {
        if (cbd instanceof FieldDeclaration)
        {
          FieldDeclaration fd = (FieldDeclaration) cbd;
          for(int x = 0; x < fd.getDeclarations().size(); x++)
          {
            Identifier i = fd.getDeclarations().get(x);
            String dims = "";
            for(int d = 0; d < fd.getDimensionList().get(x); d++)
            {
              dims += "[]";
            }
            System.err.println("      Field: " + fd.getType() + dims + " " + i.getName());
          }
        }
        else if (cbd instanceof MethodDeclaration)
        {
          MethodDeclaration md = (MethodDeclaration) cbd;
          System.err.println("      Method: " + md.getMethod().toString());
        }
        else if (cbd instanceof ConstructorDeclaration)
        {
          ConstructorDeclaration cd = (ConstructorDeclaration) cbd;
          System.err.println("      Constructor: " + cd.toString());
        }
      }
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
    return "%class$" + name + "*" ;
  }

  public String encodeType()
  {
    return "%class$" + name; //just encode() without the asterisk
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

  @Override public boolean isClassType()
  {
    return true;
  }

  //returns true if this class type has a field of the provided name
  public int getFieldIndex(String name)
  {
    for(int i = this.fields.size()-1; i >= 0; i--)
    {
      //field indices will be 1-indexed instead of 0-indexed
      //because every class starts with an i8* that isn't one of these fields
      //but you need to get the i-1th entry in the list
      if (this.fields.get(i).getName().equals(name))
        return i+1;
    }
    return -1;
  }

  public String getFieldType(String name)
  {
    for(NameTypeDepth ntd : this.fields)
    {
      if (ntd.getName().equals(name))
        return ntd.getType();
    }
    return null;
  }

  public Type fieldToType(String fieldName)
  {
    for(NameTypeDepth ntd : this.fields)
    {
      if (ntd.getName().equals(fieldName))
        return ntd.toType();
    }
    return ErrorType.getInstance();
  }

  public int getFieldDepth(String name)
  {
    for(NameTypeDepth ntd : this.fields)
    {
      if (ntd.getName().equals(name))
        return ntd.getDepth();
    }
    return -1;
  }

  public String fieldsToString()
  {
    String fieldStr = "";
    for(NameTypeDepth ntd : this.fields)
    {
      fieldStr += ntd.getName() + " (" + ntd.getType() + "), ";
    }
    return fieldStr;
  }

  //returns true if this ClassType eventually inherits from the given ClassType
  public boolean isSubclassOf(ClassType otherType)
  {
    if(otherType == null)
      return false;
    
    if(this.superClass == null)
      return false;

    if(this.superClass == otherType)
      return true;    
    
    return this.superClass.isSubclassOf(otherType);
  }

  public int getClassSize()
  {
    return this.fields.size();
  }
}

