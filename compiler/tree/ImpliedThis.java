package tc.compiler.tree;

import tc.compiler.Location;
import tc.compiler.tree.visit.TreeVisitor;
import tc.compiler.tree.type.*;

public final class ImpliedThis extends Expression
{
    public ImpliedThis(Location loc, ClassType ct)
    {
        super(loc);
        setType(ct);
    }

    public String toString ()
    {
        return "implied this";
    }

     /** Apply a visitor to the node.
    *
    *  @param visitor the visitor to apply.
    *
    *  @return the value generated by the visitor.
    */
    @Override public <T> T apply(TreeVisitor<T> visitor)
    {
        return visitor.visit(this);
    }
}