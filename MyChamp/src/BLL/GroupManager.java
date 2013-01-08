/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.GroupDBManager;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public ArrayList getById(int grpId) throws SQLException
    {
        return db.getByID(grpId);
    }
}
