/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Chris
 */
public class Group
{

    private final int ID;
    private String GroupName;

    public Group(int ID, String GroupName)
    {
        this.ID = ID;
        this.GroupName = GroupName;
    }

    /**
     * Returns the id from Group.
     *
     * @return the id
     */
    public int getGroupID()
    {
        return ID;
    }

    /**
     * Return name from Group.
     *
     * @return the name
     */
    public String getGroupName()
    {
        return GroupName;
    }

    /**
     * Sets the name for an Group in the database.
     *
     * @param name - Sets the name of the Group.
     */
    public void setGroupName(String GroupName)
    {
        this.GroupName = GroupName;
    }
    
        /**
     * Return Id and GroupName from Group.
     *
     * @return
     */
    @Override
    public String toString()
    {
        return String.format("%-6s %-30s ", ID, GroupName);
    }
}
