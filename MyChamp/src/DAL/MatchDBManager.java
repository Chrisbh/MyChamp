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
            ps.setInt(1, m.getMatchInt());
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
            String query = "SELECT match.*, team.* FROM Match, Team WHERE Team.id = GuestTeamID AND IsPlayed = 0 ORDER BY match.id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<MatchScheduling> schedule = new ArrayList<>();
            while (rs.next())
            {
                int matchID = rs.getInt("ID");
                int homeTeam = rs.getInt("HomeTeamID");
                int guestTeam = rs.getInt("GuestTeamID");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupID = rs.getInt("GroupID");
                int points = rs.getInt("points");
                
                Team h = new Team(homeTeam, school, teamCaptain, email, groupID, points);
                Team g = new Team(guestTeam, school, teamCaptain, email, groupID, points);
                
                MatchScheduling match = new MatchScheduling(matchID, h, g);
                
                schedule.add(match);
                
            }
            return schedule;
        }
    }
    
}
