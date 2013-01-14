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

    /**
     *
     * @throws Exception
     */
    public MatchDBManager() throws Exception
    {
        super();
    }

    /**
     * Gets a Match with a given id.
     *
     * @param id the id of a Match.
     * @return returns the Match with the given id.
     * @throws SQLException
     */
    public Match getById(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Match WHERE Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                int matchRound = rs.getInt("MatchRound");
                int homeTeamId = rs.getInt("HomeTeamId");
                int guestTeamId = rs.getInt("GuestTeamId");
                int isPlayed = rs.getInt("IsPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");


                Match mid = new Match(id, matchRound, homeTeamId, guestTeamId, isPlayed, homeGoals, guestGoals);
                return mid;
            }
        }
        return null;
    }

    /**
     * Adds matches to the database.
     *
     * @param m the match.
     * @return returns a new match.
     * @throws SQLException
     */
    public MatchScheduling addMatches(MatchScheduling m) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "INSERT INTO Match(MatchRound, HomeTeamId, GuestTeamId) VALUES(?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, m.getMatchInt());
            ps.setInt(2, m.getHomeTeam().getId());
            ps.setInt(3, m.getGuestTeam().getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0)
            {
                throw new SQLException("Unable to add matches.");
            }
            ResultSet keys = ps.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            return new MatchScheduling(id, m.getHomeTeam(), m.getGuestTeam());
        }
    }

    /**
     * Shows schedule for matches not played yet.
     *
     * @return returns an ArrayList of matches not played.
     * @throws SQLException
     */
    public ArrayList viewSchedule() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT Match.*, Team.* FROM Match, Team WHERE Team.Id = GuestTeamId AND IsPlayed = 0 ORDER BY Match.Id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<MatchScheduling> schedule = new ArrayList<>();
            while (rs.next())
            {
                int matchId = rs.getInt("Id");
                int homeTeam = rs.getInt("HomeTeamId");
                int guestTeam = rs.getInt("GuestTeamId");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");

                Team h = new Team(homeTeam, school, teamCaptain, email, groupId, points);
                Team g = new Team(guestTeam, school, teamCaptain, email, groupId, points);

                MatchScheduling match = new MatchScheduling(matchId, h, g);

                schedule.add(match);

            }
            return schedule;
        }
    }

    /**
     * Shows a schedule for matches already played.
     *
     * @return an ArrayList of matches already played.
     * @throws SQLException
     */
    public ArrayList viewSchedulePlayed() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT Match.*, Team.* FROM Match, Team WHERE Team.Id = GuestTeamId AND IsPlayed = 1 ORDER BY Match.Id";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<MatchScheduling> schedule = new ArrayList<>();
            while (rs.next())
            {
                int matchId = rs.getInt("Id");
                int homeTeam = rs.getInt("HomeTeamId");
                int guestTeam = rs.getInt("GuestTeamId");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");

                Team h = new Team(homeTeam, school, teamCaptain, email, groupId, points);
                Team g = new Team(guestTeam, school, teamCaptain, email, groupId, points);

                MatchScheduling match = new MatchScheduling(matchId, h, g);

                schedule.add(match);

            }
            return schedule;
        }
    }

    /**
     * Shows a schedule for a given group.
     *
     * @param groupId the group id.
     * @return an ArrayList of group matches.
     * @throws SQLException
     */
    public ArrayList viewGroupSchedule(int groupId) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT Match.*, Team.* FROM Match, Team WHERE Team.Id = GuestTeamId AND GroupId = ? AND MatchRound < 7";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, groupId);

            ResultSet rs = ps.executeQuery();

            ArrayList<MatchScheduling> schedule = new ArrayList<>();
            while (rs.next())
            {
                int matchId = rs.getInt("Id");
                int homeTeam = rs.getInt("HomeTeamId");
                int guestTeam = rs.getInt("GuestTeamId");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");

                Team h = new Team(homeTeam, school, teamCaptain, email, groupId, points);
                Team g = new Team(guestTeam, school, teamCaptain, email, groupId, points);

                MatchScheduling match = new MatchScheduling(matchId, h, g);

                schedule.add(match);

            }
            return schedule;
        }
    }

    /**
     * Show the schedule for a given team.
     *
     * @param teamId the team id.
     * @return an ArrayList of team matches.
     * @throws SQLException
     */
    public ArrayList viewTeamSchedule(int teamId) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT Match.*, Team.* FROM Match INNER JOIN Team ON Team.Id = GuestTeamId OR Team.Id = HomeTeamId WHERE Team.Id = ? ORDER BY Match.Id";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, teamId);

            ResultSet rs = ps.executeQuery();

            ArrayList<MatchScheduling> schedule = new ArrayList<>();
            while (rs.next())
            {
                int matchId = rs.getInt("Id");
                int homeTeam = rs.getInt("HomeTeamId");
                int guestTeam = rs.getInt("GuestTeamId");
                String school = rs.getString("School");
                String teamCaptain = rs.getString("TeamCaptain");
                String email = rs.getString("Email");
                int groupId = rs.getInt("GroupId");
                int points = rs.getInt("Points");


                Team h = new Team(homeTeam, school, teamCaptain, email, groupId, points);
                Team g = new Team(guestTeam, school, teamCaptain, email, groupId, points);

                MatchScheduling match = new MatchScheduling(matchId, h, g);

                schedule.add(match);

            }
            return schedule;
        }
    }

    /**
     * Updates match results for a given match.
     *
     * @param m a match.
     * @throws SQLException
     */
    public void matchResults(Match m) throws SQLException
    {
        String sql = "UPDATE Match SET HomeGoals = ?, GuestGoals = ?, IsPlayed = 1 WHERE Id = ?";

        Connection con = ds.getConnection();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, m.getHomeGoals());
        ps.setInt(2, m.getGuestGoals());
        ps.setInt(3, m.getId());

        int affectedRows = ps.executeUpdate();
        if (affectedRows == 0)
        {
            throw new SQLException("Unable to update MatchResults");
        }
    }

    /**
     * Checks whether a given match has been played.
     *
     * @param id a match id.
     * @return whether the match has been played.
     * @throws SQLException
     */
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

    /**
     * Counts matches.
     *
     * @return the count of matches.
     * @throws SQLException
     */
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

    /**
     * Counts team matches in a group by team id.
     *
     * @param id a team id.
     * @return the match count or 0.
     * @throws SQLException
     */
    public int countTeamGroupMatchesByTeamId(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT COUNT(*) as NumberOfMatches FROM Match WHERE MatchRound < 7 AND HomeTeamId = ? OR GuestTeamId = ? ";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                int count = rs.getInt("NumberOfMatches");

                return count;
            }
            return 0;
        }
    }

    /**
     * Gets the max match id.
     *
     * @return max match id or 0.
     * @throws SQLException
     */
    public int maxId() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT MAX(id) as maxId FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int maxId = rs.getInt("maxId");

                return maxId;
            }
            return 0;
        }
    }

    /**
     * Gets the max match round.
     *
     * @return the max round or 0.
     * @throws SQLException
     */
    public int maxRound() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT MAX(MatchRound) as MaxRound FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next())
            {
                int maxRound = rs.getInt("MaxRound");

                return maxRound;
            }
            return 0;
        }
    }

    /**
     * Shows an ArrayList of matches.
     *
     * @param matchRound a matchround.
     * @return an ArrayList of matches in a given match round.
     * @throws SQLException
     */
    public ArrayList<Match> listByMatchRound(int matchRound) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Match WHERE MatchRound = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matchRound);

            ResultSet rs = ps.executeQuery();
            ArrayList<Match> quarterFinalMatches = new ArrayList();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                matchRound = rs.getInt("MatchRound");
                int homeTeamId = rs.getInt("HomeTeamId");
                int guestTeamId = rs.getInt("GuestTeamId");
                int isPlayed = rs.getInt("IsPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");


                Match m = new Match(id, matchRound, homeTeamId, guestTeamId, isPlayed, homeGoals, guestGoals);
                quarterFinalMatches.add(m);
            }
            return quarterFinalMatches;
        }
    }

    /**
     * Shows an ArrayList of team matches, where the team plays as either home
     * team or guest team, in a group with a given team id.
     *
     * @param id a team id.
     * @return an ArrayList of matches with a given team id.
     * @throws SQLException
     */
    public ArrayList<Match> getGroupMatchesByTeamId(int id) throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String sql = "SELECT * FROM Match WHERE HomeTeamId = ? OR GuestTeamId = ? AND MatchRound < 7";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, id);

            ResultSet rs = ps.executeQuery();

            ArrayList<Match> byTeamId = new ArrayList();
            while (rs.next())
            {
                int matchId = rs.getInt("Id");
                int matchRound = rs.getInt("MatchRound");
                int homeTeamId = rs.getInt("HomeTeamId");
                int guestTeamId = rs.getInt("GuestTeamId");
                int isPlayed = rs.getInt("IsPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");


                Match m = new Match(matchId, matchRound, homeTeamId, guestTeamId, isPlayed, homeGoals, guestGoals);
                byTeamId.add(m);
            }
            return byTeamId;
        }
    }

    public ArrayList<Match> listAll() throws SQLException
    {
        try (Connection con = ds.getConnection())
        {
            String query = "SELECT * FROM Match";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            ArrayList<Match> allMatches = new ArrayList<>();
            while (rs.next())
            {
                int matchId = rs.getInt("Id");
                int matchRound = rs.getInt("MatchRound");
                int homeTeamId = rs.getInt("HomeTeamId");
                int guestTeamId = rs.getInt("GuestTeamId");
                int isPlayed = rs.getInt("IsPlayed");
                int homeGoals = rs.getInt("HomeGoals");
                int guestGoals = rs.getInt("GuestGoals");

                Match match = new Match(matchId, matchRound, homeTeamId, guestTeamId, isPlayed, homeGoals, guestGoals);
                allMatches.add(match);
            }
            return allMatches;
        }
    }
}
