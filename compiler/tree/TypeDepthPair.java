package tc.compiler.tree;

import tc.compiler.tree.type.Type;

/** 
* a class for pairing a name and a type. used to track identifiers
*/

public final class TypeDepthPair
{
    private Integer depth;
    private Type type;

    /**
    * create a type-depth pair
    * @param depth the depth
    * @param type the type
    */
    public TypeDepthPair(Type type, Integer depth)
    {
        this.depth = depth;
        this.type = type;
    }

    //getters
    public Type getType()
    {
        return this.type;
    }

    public Integer getDepth()
    {
        return this.depth;
    }

    //setters
    public void setType(Type t)
    {
        this.type = t;
    }
    
    public void setDepth(int i)
    {
        this.depth = new Integer(i);
    }
}