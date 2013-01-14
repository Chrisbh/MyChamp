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

/**
 *
 * @author Dennis
 */
public class GroupDBManager extends MyChampDBManager
{

    /**
     *
     * @throws Exception
     */
    public GroupDBManager() throws Exception
    {
        super();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
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
