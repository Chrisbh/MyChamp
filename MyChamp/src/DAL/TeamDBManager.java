/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Rasmus
 */
public class TeamDBManager extends MyChampDBManager
{
    public TeamDBManager() throws Exception
    {
        super();
    }
    public void update(Team t) throws SQLException
    {
        String sql = "UPDATE Team SET School = ?, TeamCaptain = ?, Email = ?, GroupId = ? WHERE Id = ?";

        Connection con = dataSource.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getSchool());
        ps.setString(2, t.getTeamCaptain());
        ps.setString(3, t.getEmail());
        ps.setInt(4, t.getGroupId());
        ps.setInt(5, t.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to update Team");
        }
    }
}
