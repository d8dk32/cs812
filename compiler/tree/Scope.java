//A class to ease the handling of variable scope
//Scope wraps a stack of symbol tables (HashMap<String, TypeDepthPair>)
//when you enter a new scope block (constructor, method def/invocation, class def, or main block)
//you need to push a new HashMap onto the stack, then put vars that are in that scope into that hashmap
//when you leave a scope block, popp it off the stack
package tc.compiler.tree;

import tc.compiler.tree.*;
import tc.compiler.tree.type.*;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Scope
{
    Stack<HashMap<String, TypeDepthPair>> symbolTable = new Stack<>();
    Stack<ClassType> classAccess = new Stack<>();

    public Scope(){}

    //utility methods-----------------------------------------
    public HashMap<String, TypeDepthPair> popScope()
    {
        return symbolTable.pop();
    }

    public HashMap<String, TypeDepthPair> peekScope()
    {
        return symbolTable.peek();
    }
    
    //put a new empty synmbol table on the stack 
    public void pushScope()
    {
        symbolTable.push(new HashMap<String, TypeDepthPair>());
    }

    //adds to the hashtable at the top of the stack (i.e. the most "local" scope)
    public void putSymbol(String s, TypeDepthPair tdp)
    {
        symbolTable.peek().put(s, tdp);
    }

    public void putAllSymbols(List<NameTypeDepth> ntdList)
    {
        for(NameTypeDepth ntd : ntdList)
        {
            putSymbol(ntd.getName(), ntd.getTypeDepthPair());
        }
    }

    //finds the variable by name (basically wraps HashTable.get) if it is in scope. starts at most "local" scope and
    //then widens search towards global scope to support shadowing
    //returns null if not found
    public TypeDepthPair getSymbol(String name)
    {
        for(int i = symbolTable.size()-1; i >= 0; i--)
        {
            HashMap<String, TypeDepthPair> curScope = symbolTable.get(i);
            TypeDepthPair tdp = curScope.get(name);
            if(tdp != null)
            {
                return tdp;
            }
        }
        //if it reaches here, the identifier wasn't in scope
        return null;
    }

    public ClassType popClassAccess() 
    {
        return classAccess.pop();
    }

    public ClassType peekClassAccess()
    {
        if(!classAccess.empty())
        {
            return classAccess.peek();
        }
        return null;
    }

    public void pushClassAccess(ClassType ct)
    {
        classAccess.push(ct);
    }

}