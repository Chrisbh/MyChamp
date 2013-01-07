/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Dennis
 */
public class GroupDBManager extends MyChampDBManager
{
    public GroupDBManager()throws Exception
    {
        super();
    }
    
     public Group getByID(int grpid) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM [Group] WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, grpid);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("GroupName");



                Group gid = new Group(id, name);
                return gid;
            }
        }
        return null;
    }
}
