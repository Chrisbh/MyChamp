/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Match;
import BE.MatchScheduling;
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
public class MatchDBManager extends MyChampDBManager
{

    public MatchDBManager() throws Exception
    {
        super();
    }

    public ArrayList<Match> Scheduling(Team t) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT School, GroupID, GroupName FROM Team, "
                    + "[Group] WHERE Team.GroupID = [Group].ID AND GroupID = ?";

            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getGroupId());

            ResultSet rs = ps.executeQuery();

            ArrayList matches = new ArrayList();
            while (rs.next())
            {
                String school = rs.getString("School");
                int groupID = rs.getInt("groupID");
                String groupName = rs.getString("GroupName");

                matches.add(school);
                matches.add(groupID);
                matches.add(groupName);
            }
            return matches;
        }

    }

    public MatchScheduling addMatches(MatchScheduling m) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "INSERT INTO Match(MatchRound, HomeTeamID, GuestTeamID) VALUES(?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getMatchRound());
            ps.setInt(2, m.getHomeTeam().getId());
            ps.setInt(3, m.getGuestTeam().getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0)
            {
                throw new SQLException("Unabe to add matches.");
            }
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            return new MatchScheduling(id, m.getHomeTeam(), m.getGuestTeam());
        }
    }

    public ArrayList listAll() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<Match> allMatches = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("ID");
                int matchRound = rs.getInt("MatchRound");
                int homeTeamID = rs.getInt("HomeTeamID");
                int guestTeamID = rs.getInt("GuestTeamID");
                int isPlayed = rs.getInt("isPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");

                Match match = new Match(id, matchRound, homeTeamID, guestTeamID, isPlayed, homeGoals, guestGoals);
                allMatches.add(match);
            }
            return allMatches;
        }
    }

    public ArrayList viewSchedule() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<Match> schedule = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("ID");
                int homeTeamID = rs.getInt("HomeTeamID");
                int guestTeamID = rs.getInt("GuestTeamID");
                
                Match match = new Match(id, homeTeamID, guestTeamID);
                schedule.add(match);
                
            }
            return schedule;
        }
    }
}
