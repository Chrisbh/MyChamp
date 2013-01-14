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

    private final int id;
    private String groupName;

    /**
     *
     * @param id
     * @param groupName
     */
    public Group(int id, String groupName)
    {
        this.id = id;
        this.groupName = groupName;
    }

    /**
     * Returns the id from Group.
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * Return name from Group.
     *
     * @return the name
     */
    public String getGroupName()
    {
        return groupName;
    }

    /**
     * Sets the name for an Group in the database.
     *
     * @param GroupName
     */
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    /**
     * Return Id and GroupName from Group.
     *
     * @return
     */
    @Override
    public String toString()
    {
        return String.format("%3s %-6d %-30s", "", id, groupName);
    }
}
