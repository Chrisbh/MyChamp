/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class TeamDBManager extends MyChampDBManager
{

    /**
     *
     * @throws Exception
     */
    public TeamDBManager() throws Exception
    {
        super();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
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
                int points = rs.getInt("points");

                Team team = new Team(ID, School, TeamCaptain, Email, GroupID, points);
                allTeams.add(team);
            }
            return allTeams;
        }
    }

    /**
     *
     * @param t
     * @throws SQLException
     */
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

    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
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
                throw new SQLException("Unable to add team.");
            }
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            return new Team(id, t);
        }
    }

    /**
     *
     * @param t
     * @param g
     * @throws SQLException
     */
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

    /**
     *
     * @return
     * @throws SQLException
     */
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

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Team getByID(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
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
                int points = rs.getInt("points");


                Team gid = new Team(id, School, TeamCaptain, Email, GroupId, points);
                return gid;
            }
        }
        return null;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList<Team> getByGroupID(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Team WHERE groupID = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ArrayList<Team> teams = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int tId = rs.getInt("Id");
                String School = rs.getString("School");
                String TeamCaptain = rs.getString("TeamCaptain");
                String Email = rs.getString("Email");
                int GroupId = rs.getInt("GroupId");
                int points = rs.getInt("points");


                Team gid = new Team(tId, School, TeamCaptain, Email, GroupId, points);
                teams.add(gid);

            }
            return teams;
        }
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Team> listGroupRanked() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Team, Match WHERE Team.ID = HomeTeamID ORDER BY Points DESC, (HomeGoals - GuestGoals) DESC, HomeGoals DESC";

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
                int points = rs.getInt("points");

                Team team = new Team(ID, School, TeamCaptain, Email, GroupID, points);
                allTeams.add(team);
            }
            return allTeams;
        }
    }

    /**
     *
     * @param points
     * @param t
     * @throws SQLException
     */
    public void setPoints(int points, Team t) throws SQLException
    {
        String sql = "UPDATE Team SET Points = ? WHERE ID = ?";

        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, points);
        ps.setInt(2, t.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("WARNING - POINTS NOT INSERTED FOR MATCH!");
        }
    }

    /**
     *
     * @throws SQLException
     */
    public void deleteAll() throws SQLException
    {
        String sql = "DELETE FROM Match; DELETE FROM Team; DBCC CHECKIDENT (Match, RESEED, 0); DBCC CHECKIDENT (Team, RESEED, 0)";
        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.executeUpdate();
    }
}
