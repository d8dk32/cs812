package tc.compiler.tree;

import javax.lang.model.util.ElementScanner6;

import tc.compiler.Location;
import tc.compiler.tree.visit.TreeVisitor;
import tc.compiler.tree.type.*;
import java.util.List;

/**
 * AST Cast node
 */
public final class Cast extends Expression
{
    public enum ConversionType {
        IDENTITY,
        WIDENING,
        NARROWING,
        INVALID
    }

    private ArrayType presetType = null;
    private Expression parenExpr = null;
    private Expression exprToCast;
    private ConversionType conversionType = ConversionType.INVALID;

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

    public Cast(Location loc, Type toType, Expression exprToCast)
    {
        super(loc);
        this.exprToCast = exprToCast;
        this.setType(toType);
        this.conversionType = ConversionType.WIDENING;
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

    public ConversionType getConversionType()
    {
        return this.conversionType;
    }

    public void setConversionType(ConversionType type)
    {
        this.conversionType = type;
    }

    //this method will be used during method invocation conversion
    public static ConversionType isMethodInvocationConversionPermitted(Type toType, Type fromType)
    {
        if (toType == fromType)
        {
            return ConversionType.IDENTITY;
        }
        else if (toType instanceof ClassType && fromType instanceof ClassType)
        {
            ClassType toCT = (ClassType) toType;
            ClassType fromCT = (ClassType) fromType;
            if(toCT.isSubclassOf(fromCT))
            {
                return ConversionType.NARROWING;
            }
            else if (fromCT.isSubclassOf(toCT))
            {
                return ConversionType.WIDENING;
            }
            else 
            {
                return ConversionType.INVALID;
            }

        }
        
        return ConversionType.INVALID;
    }

    public static boolean isMethodInvocationConversionPermitted(List<Type> toTypes, List<Type> fromTypes)
    {
        if(toTypes.size() != fromTypes.size())
        {
            return false;
        }

        boolean allow = true;
        for(int i = 0; i < toTypes.size(); i++)
        {
            ConversionType isMICP = isMethodInvocationConversionPermitted(toTypes.get(i), fromTypes.get(i));
            allow = (isMICP == ConversionType.WIDENING || isMICP == ConversionType.IDENTITY);
            if(!allow)
                break;
        }
        return allow;
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