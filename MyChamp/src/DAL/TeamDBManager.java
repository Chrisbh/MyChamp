/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Group;
import BE.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

    public ArrayList<Team> ListAll() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Team";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<Team> allTeams = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String School = rs.getString("School");
                String TeamCaptain = rs.getString("TeamCaptain");
                String Email = rs.getString("Email");
                int GroupID = rs.getInt("GroupID");

                Team team = new Team(ID, School, TeamCaptain, Email, GroupID);
                allTeams.add(team);
            }
            return allTeams;
        }
    }

    public void update(Team t) throws SQLException
    {
        String sql = "UPDATE Team SET School = ?, TeamCaptain = ?, Email = ?, GroupId = ? WHERE Id = ?";

        Connection con = ds.getConnection();

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

    public Team addTeam(Team t) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "INSERT INTO Team(School, TeamCaptain, Email)"
                    + "VALUES(?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, t.getSchool());
            ps.setString(2, t.getTeamCaptain());
            ps.setString(3, t.getEmail());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0)
            {
                throw new SQLException("Unabe to add team.");
            }
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            return new Team(id, t);
        }
    }

    public void delete(int id) throws SQLException
    {
        String sql = "DELETE TEAM WHERE TEAM.ID = ?";
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to delete Team");
        }
    }

    public void assign(Team t, int g) throws SQLException
    {
        String sql = "UPDATE Team SET School = ?, TeamCaptain = ?, Email = ?, GroupId = ? WHERE Id = ?";

        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getSchool());
        ps.setString(2, t.getTeamCaptain());
        ps.setString(3, t.getEmail());
        ps.setInt(4, g);
        ps.setInt(5, t.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to insert Team into group");
        }
    }

    public int Count() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT COUNT(*) as NumberOfTeams FROM Team";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int count = rs.getInt("NumberOfTeams");

                return count;
            }
            return 0;
        }
    }

    public Team getID(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            Statement st = con.createStatement();
            String sql = "SELECT * FROM Team WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                String School = rs.getString("School");
                String TeamCaptain = rs.getString("TeamCaptain");
                String Email = rs.getString("Email");
                int GroupId = rs.getInt("GroupId");


                Team gid = new Team(id, School, TeamCaptain, Email, GroupId);
                return gid;
            }
        }
        return null;
    }

    public Team getBySchoolName(String School) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM TEAM WHERE School Like ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, School);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int Id = rs.getInt("ID");
                School = rs.getString("School");
                String TeamCaptain = rs.getString("TeamCaptain");
                String Email = rs.getString("Email");
                int GroupId = rs.getInt("GroupId");


                Team gid = new Team(Id, School, TeamCaptain, Email, GroupId);
                return gid;
            }
            return null;
        }
    }

    public int maxId() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT Max(ID) as HighestID FROM Team";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int maxID = rs.getInt("HighestID");

                return maxID;
            }
            return 0;
        }
    }
}
