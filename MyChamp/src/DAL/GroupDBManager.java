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
    
//     public ArrayList<Group> getByID(int grpid) throws SQLException
//     {
//        try (Connection con = ds.getConnection())
//        {
//            String sql = "SELECT * FROM [Group], Team WHERE [Group].ID = Team.GroupID AND [Group].id = ?";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, grpid);
//
//            ResultSet rs = ps.executeQuery();
//            while (rs.next())
//            {
//                int id = rs.getInt("Id");
//                String name = rs.getString("GroupName");
//                String school = rs.getString("School");
//
//                ArrayList list = new ArrayList();
//
//                list.add(id);
//                list.add(name);
//                list.add(school);
//                return list;
//            }
//        }
//        return null;
//     }
     
     public Group getById(int id) throws SQLException
     {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM [Group] WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                id = rs.getInt("Id");
                String name = rs.getString("GroupName");
               
                Group grp = new Group(id, name);
                return grp;
            }
        }
        return null;
     }
}
