package tc.compiler.tree;

import javax.lang.model.util.ElementScanner6;

import tc.compiler.tree.type.ClassType;
import tc.compiler.tree.type.*;

//A class that holds a string representing the name of a field, it's base type, and it's depth (0 if not an array)
//going to try just using a String to represent the type for this. i think it will be ok.
public final class NameTypeDepth
{
    private String name;
    private String type;
    private int depth;

    public NameTypeDepth(String n, String t, int d)
    {
        this.name = n;
        this.type = t;
        this.depth = d;
    }

    public String getName()
    {
        return this.name;
    }

    public String getType()
    {
        return this.type;
    } 
    
    public int getDepth()
    {
        return this.depth;
    }

    public String toString()
    {
        String ntdString = this.type;
        int i = 0;
        while(i < this.depth)
        {
            ntdString += "[]";
            i++;
        }
        ntdString += " " + this.name;
        return ntdString;
    }

    //gets just a type-depth pair from this NameTypeDepth, for interfacing with 
    //the original implmentation of the Symbol Table
    public TypeDepthPair getTypeDepthPair()
    {
        return new TypeDepthPair(toType(), this.depth);
    }

    //Returns a real Type represented by this NameTypeDepth
    public Type toType()
    {
        if(this.depth > 0)
        {
            return new ArrayType(this.type, this.depth);
        }
        else if (this.type.equals("int"))
        {
            return IntegerType.getInstance();
        }
        else
        {
            return ClassType.getInstance(this.type);
        }
    }

}