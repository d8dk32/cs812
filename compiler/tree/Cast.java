package tc.compiler.tree;

import tc.compiler.Location;
import tc.compiler.tree.visit.TreeVisitor;
import tc.compiler.tree.type.*;

/**
 * AST Cast node
 */
public final class Cast extends Expression
{
    private ArrayType presetType = null;
    private Expression parenExpr = null;
    private Expression exprToCast;

    public Cast(Location loc, ArrayType at, Expression exprToCast)
    {
        super(loc);
        this.presetType = at;
        this.exprToCast = exprToCast;
        this.parenExpr = null;
    }

    public Cast(Location loc, Expression pe, Expression exprToCast)
    {
        super(loc);
        this.presetType = null;
        this.exprToCast = exprToCast;
        this.parenExpr = pe;
    }

    public ArrayType getPresetType()
    {
        return this.presetType;
    }

    public Expression getParenExpression()
    {
        return this.parenExpr;
    }

    public Expression getToBeCastExpression()
    {
        return this.exprToCast;
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