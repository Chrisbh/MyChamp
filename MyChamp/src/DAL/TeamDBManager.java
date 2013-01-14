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
     * Extends the logininformation from the MyChampDBManager, so we won't need
     * to correct the logininformation in more than one place.
     *
     * @throws Exception
     */
    public TeamDBManager() throws Exception
    {
        super();
    }

    /**
     * List all teams.
     *
     * @return returns the teams in a ArrayList.
     * @throws SQLException
     */
    public ArrayList<Team> listAll() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Team";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<Team> allTeams = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");

                Team team = new Team(id, school, teamCaptain, email, groupId, points);
                allTeams.add(team);
            }
            return allTeams;
        }
    }

    /**
     * shows a specific team with an choosen id.
     *
     * @param id - the given id
     * @return returns the specific group with the id
     * @throws SQLException
     */
    public Team getById(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Team WHERE Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");


                Team team = new Team(id, school, teamCaptain, email, groupId, points);
                return team;
            }
        }
        return null;
    }

    /**
     * Gets a team with a given groupid.
     *
     * @param id the given id.
     * @return returns the team with the given groupid.
     * @throws SQLException
     */
    public ArrayList<Team> getByGroupId(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Team WHERE GroupId = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ArrayList<Team> teams = new ArrayList();
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                int teamId = rs.getInt("Id");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                id = rs.getInt("GroupId");
                int points = rs.getInt("Points");


                Team team = new Team(teamId, school, teamCaptain, email, id, points);
                teams.add(team);

            }
            return teams;
        }
    }

    /**
     * Adds a team into the database.
     *
     * @param t - Contains the data that is getting inserted into the database
     * fields.
     * @return return new team
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
     * Updates a specific team
     *
     * @param t - the chosen team.
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
     * Delete team and matches with the given team.
     *
     * @param id - the team id.
     * @throws SQLException
     */
    public void deleteFromTeamAndMatch(int id) throws SQLException
    {
        String sql = "DELETE FROM Match WHERE HomeTeamId = ? OR GuestTeamId = ?";
        String sql1 = "DELETE FROM Team WHERE Id = ?";
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps.setInt(1, id);
        ps.setInt(2, id);


        ps1.setInt(1, id);
        int affectedRows = ps.executeUpdate();
        int affectedRows1 = ps1.executeUpdate();
    }

    /**
     * Reset all table columns.
     *
     * @throws SQLException
     */
    public void resetAll() throws SQLException
    {
        String sql = "DELETE FROM Match; DELETE FROM Team; DBCC CHECKIDENT (Match, RESEED, 0); DBCC CHECKIDENT (Team, RESEED, 0)";
        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);

        ps.executeUpdate();
    }

    /**
     * Assign teams to the four groups
     *
     * @param t - the chosen team
     * @param g - the chosen group
     * @throws SQLException
     */
    public void assign(Team t, int groupId) throws SQLException
    {
        String sql = "UPDATE Team SET School = ?, TeamCaptain = ?, Email = ?, GroupId = ? WHERE Id = ?";

        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, t.getSchool());
        ps.setString(2, t.getTeamCaptain());
        ps.setString(3, t.getEmail());
        ps.setInt(4, groupId);
        ps.setInt(5, t.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to insert Team into group");
        }
    }

    /**
     * Count the number of teams.
     *
     * @return - The number of teams.
     * @throws SQLException
     */
    public int count() throws SQLException
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
     * List teams ranked in specific requirements.
     *
     * @return - all the teams.
     * @throws SQLException
     */
    public ArrayList<Team> listGroupRanked() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Team, Match WHERE Team.Id = HomeTeamId ORDER BY Points DESC, (HomeGoals - GuestGoals) DESC, HomeGoals DESC";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<Team> allTeams = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");

                Team team = new Team(id, school, teamCaptain, email, groupId, points);
                allTeams.add(team);
            }
            return allTeams;
        }
    }

    /**
     * Set points for the team, when winning or having draw match.
     *
     * @param points - the point for the chosen team.
     * @param t - the chosen team.
     * @throws SQLException
     */
    public void setPoints(int points, Team t) throws SQLException
    {
        String sql = "UPDATE Team SET Points = ? WHERE Id = ?";

        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, points);
        ps.setInt(2, t.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Points not inserted for Match!");
        }
    }
}
