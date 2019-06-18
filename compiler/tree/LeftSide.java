package tc.compiler.tree;

import tc.compiler.tree.Identifier;

/* An interface to help out when variables or whatever are on the left hand side,
 * like of an assignment expression
**/
public interface LeftSide {

    /** 
     * @return true if the variable is a left hand side
     */
    public boolean isLeftSide();

    /** sets whether or not this is a left hand side variable
     * @param isLs true if this is a left hand side var
     */
    public void setLeftSide(boolean isLs);

    public String getName();
}