
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
    // TODO
    ClassType type = ClassType.getInstance(classDeclaration.getClassName());
    ClassType superClass = ClassType.getInstance(classDeclaration.getSuperClassName());
    type.setSuperClass(superClass);
    type.setWasDeclared();
    type.setClassBodyDecls(classDeclaration.getClassBody());


    return null;
  }

}

