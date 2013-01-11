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

    public ArrayList viewSchedulePlayed() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT match.*, team.* FROM Match, Team WHERE Team.id = GuestTeamID AND IsPlayed = 1 ORDER BY match.id";

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

    public Match getByID(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Match WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int matchRound = rs.getInt("MatchRound");
                int homeTeamID = rs.getInt("HomeTeamID");
                int guestTeamID = rs.getInt("GuestTeamID");
                int isPlayed = rs.getInt("isPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");


                Match mid = new Match(id, matchRound, homeTeamID, guestTeamID, isPlayed, homeGoals, guestGoals);
                return mid;
            }
        }
        return null;
    }

    public ArrayList viewGroupSchedule(int groupID) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT Match.*, Team.* FROM Match, Team WHERE Team.ID = GuestTeamID AND GroupID = ? AND MatchRound < 7";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, groupID);

            ResultSet rs = ps.executeQuery();

            ArrayList<MatchScheduling> schedule = new ArrayList<>();
            while (rs.next())
            {
                int matchID = rs.getInt("ID");
                int homeTeam = rs.getInt("HomeTeamID");
                int guestTeam = rs.getInt("GuestTeamID");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                groupID = rs.getInt("GroupID");
                int points = rs.getInt("points");

                Team h = new Team(homeTeam, school, teamCaptain, email, groupID, points);
                Team g = new Team(guestTeam, school, teamCaptain, email, groupID, points);

                MatchScheduling match = new MatchScheduling(matchID, h, g);

                schedule.add(match);

            }
            return schedule;
        }
    }

    public ArrayList viewTeamSchedule(int teamID) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT Match.*, Team.* FROM Match INNER JOIN Team ON Team.id = GuestTeamID OR Team.id = HomeTeamID WHERE Team.id = ? ORDER BY Match.ID";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teamID);

            ResultSet rs = ps.executeQuery();

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

    public void matchResults(Match m) throws SQLException
    {
        String sql = "UPDATE Match SET HomeGoals = ?, GuestGoals = ?, IsPlayed = 1 WHERE Id = ?";

        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, m.getHomeGoals());
        ps.setInt(2, m.getGuestGoals());
        ps.setInt(3, m.getID());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to update MatchResults");
        }
    }

    public int isPlayed(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT IsPlayed FROM Match WHERE Id = ?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int isPlayed = rs.getInt("IsPlayed");

                return isPlayed;
            }
        }
        return 0;
    }

    public int countMatches() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT COUNT(*) as NumberOfMatches FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int count = rs.getInt("NumberOfMatches");

                return count;
            }
            return 0;
        }
    }

    public int maxID() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT MAX(id) as maxID FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int maxID = rs.getInt("maxID");

                return maxID;
            }
            return 0;
        }
    }

    public ArrayList<Match> listByMatchRound(int matchRound) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Match WHERE matchRound = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matchRound);

            ResultSet rs = ps.executeQuery();
            ArrayList<Match> quarterFinalMatches = new ArrayList();
            while (rs.next())
            {
                int id = rs.getInt("ID");
                matchRound = rs.getInt("MatchRound");
                int homeTeamID = rs.getInt("HomeTeamID");
                int guestTeamID = rs.getInt("GuestTeamID");
                int isPlayed = rs.getInt("IsPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");


                Match m = new Match(id, matchRound, homeTeamID, guestTeamID, isPlayed, homeGoals, guestGoals);
                quarterFinalMatches.add(m);
            }
            return quarterFinalMatches;
        }
    }

    public void deleteFromTeamAndMatch(int id) throws SQLException
    {
        String sql = "DELETE FROM Match WHERE HomeTeamID = ? OR GuestTeamID = ?";
        String sql1 = "DELETE FROM Team WHERE ID = ?";
        Connection con = ds.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        PreparedStatement ps1 = con.prepareStatement(sql1);
        ps.setInt(1, id);
        ps.setInt(2, id);


        ps1.setInt(1, id);
        int affectedRows = ps.executeUpdate();
        int affectedRows1 = ps1.executeUpdate();
    }

    public int maxRound() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT MAX(MatchRound) as maxRound FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int maxRound = rs.getInt("maxRound");

                return maxRound;
            }
            return 0;
        }
    }

//    public ArrayList<Match> sortTeamsByRank(int m1, int m2) throws SQLException
//    {
//        try (Connection con = ds.getConnection())
//        {
//            String sql = "SELECT * FROM Team, Match m1, Match m2, Team WHERE groupid = 1 AND Team.ID = m1.HomeTeamID AND Team.ID = m2.GuestTeamID AND m1.GuestTeamID = m2.HomeTeamID AND m1.MatchRound = ? AND m2.MatchRound = ? ORDER BY Points DESC, (m1.HomeGoals - m1.GuestGoals) DESC, m1.HomeGoals DESC,((m1.HomeGoals + m2.guestGoals) - (m2.HomeGoals + m1.GuestGoals)) DESC";
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, m1);
//            ps.setInt(2, m2);
//
//            ResultSet rs = ps.executeQuery();
//            ArrayList<Match> teamsByRank = new ArrayList();
//            while (rs.next())
//            {
//                int ID = rs.getInt("ID");
//                m1 = rs.getInt("m1.MatchRound");
//                m2 = rs.getInt("m2.MatchRound");
//                int homeTeamID = rs.getInt("HomeTeamID");
//                int guestTeamID = rs.getInt("GuestTeamID");
//                int isPlayed = rs.getInt("IsPlayed");
//                int homeGoals = rs.getInt("HomeGoals");
//                int guestGoals = rs.getInt("GuestGoals");
//
//
//                Match match1 = new Match(ID, m1, homeTeamID, guestTeamID, isPlayed, homeGoals, guestGoals);
//                Match match2 = new Match(ID, m2, homeTeamID, guestTeamID, isPlayed, homeGoals, guestGoals);
//                teamsByRank.add(match1);
//                teamsByRank.add(match2);
//            }
//            return teamsByRank;
//        }
//    }
}
