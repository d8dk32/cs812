//A class that holds a string representing the name of a field, it's base type, and it's depth (0 if not an array)
//going to try just using a String to represent the type for this. i think it will be ok.
public class NameTypeDepth
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
}