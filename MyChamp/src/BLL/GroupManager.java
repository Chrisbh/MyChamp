/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Group;
import DAL.GroupDBManager;
import java.sql.SQLException;

/**
 *
 * @author Dennis
 */
public class GroupManager
{

    private GroupDBManager db = null;

    public GroupManager() throws Exception
    {
        db = new GroupDBManager();
    }

    /*
     * Gets the group by id.
     */
    public Group getById(int Id) throws SQLException
    {
        return db.getById(Id);
    }
}
