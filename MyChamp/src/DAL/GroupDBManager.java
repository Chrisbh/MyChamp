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
import java.util.ArrayList;

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
    
     public ArrayList getByID(int grpid) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM [Group], Team WHERE [Group].id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, grpid);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int id = rs.getInt("Id");
                String name = rs.getString("GroupName");
                String school = rs.getString("School");

                ArrayList list = new ArrayList();

                list.add(id);
                list.add(name);
                list.add(school);
                return list;
            }
        }
        return null;
    }
}
