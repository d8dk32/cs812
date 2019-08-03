
package tc.compiler.tree.visit;

import tc.compiler.Message;
import tc.compiler.tree.*;
import tc.compiler.tree.type.*;

/**
 * PreprocessClassDeclarations is a "prepass" over the AST to handle forward
 * reference of classes. Only ClassDeclaration tree nodes and their subtrees
 * need to be processed. Method bodies are skipped, but field and method
 * declarations must be processed and then inserted into the class types.
 * <p>
 * Using Void for the type parameter because there is no return value
 * from these methods. They only return null.
 */
public final class PreprocessClassDeclarations extends TreeVisitorBase<Void>
{
  /** Create the class declarations preprocessor.
   *
   */
  public PreprocessClassDeclarations()
  {
    ClassType objClass = ClassType.getInstance("Object");
    objClass.setSuperClass(null);
    objClass.setWasDeclared();
  }

  /** Process a class declaration. */
  @Override public Void visit(ClassDeclaration classDeclaration)
  {
    ClassType cdType = ClassType.getInstance(classDeclaration.getClassName());
    ClassType superClass = ClassType.getInstance(classDeclaration.getSuperClassName());
    
    if(cdType.wasDeclared())
    {
      Message.error(classDeclaration.getLoc(), "Duplicate class declaration " + classDeclaration.getClassName());
    }
    cdType.setWasDeclared();
    cdType.setSuperClass(superClass);
    cdType.setClassBodyDecls(classDeclaration.getClassBody());

    //add the class member declarations from the class body to their respective lists in the classtype
    for(ClassBodyDeclaration cbd : cdType.getClassBodyDecls(false))
    {
      //this method "sorts" the fields/methods/constructors into their respectve lists
      cdType.addClassMemberDeclaration(cbd);
    }

    return null;
  }

}

