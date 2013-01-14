/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Match;
import BE.MatchScheduling;
import BE.Team;
import DAL.MatchDBManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class MatchManager
{

    private MatchDBManager db = null;
    private static MatchManager instance = null;
    private TeamManager teammgr;

    /**
     *
     * @throws Exception
     */
    public MatchManager() throws Exception
    {
        db = new MatchDBManager();
        teammgr = new TeamManager();
    }

    /**
     * Gets an instance of the MatchManager.
     *
     * @return returns the instance.
     * @throws Exception
     */
    public static MatchManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new MatchManager();
        }
        return instance;
    }

    /**
     * Gets a match by a given id.
     *
     * @param id the given id.
     * @return returns the Match from the DAL.
     * @throws SQLException
     */
    public Match getById(int id) throws SQLException
    {
        return db.getById(id);
    }

    /**
     * Creates an ArrayList of matches with a given MatchRound.
     *
     * @param matchRound the given MatchRound.
     * @return returns the ArrayList of matches.
     * @throws SQLException
     */
    public ArrayList<Match> listByMatchRound(int matchRound) throws SQLException
    {
        return db.listByMatchRound(matchRound);
    }

    /**
     * Shows the total schedule.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> viewSchedule() throws SQLException
    {
        return db.viewSchedule();
    }

    /**
     * Shows the matches played.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> viewSchedulePlayed() throws SQLException
    {
        return db.viewSchedulePlayed();
    }

    /**
     * Shows the schedule for a given group.
     *
     * @param groupID the given Group Id.
     * @return returns the schedule for a given group.
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> viewGroupSchedule(int groupID) throws SQLException
    {
        return db.viewGroupSchedule(groupID);
    }

    /**
     * Shows the schedule for a given team.
     *
     * @param teamID the given Team Id.
     * @return returns the schedule for a given team.
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> viewTeamSchedule(int teamID) throws SQLException
    {
        return db.viewTeamSchedule(teamID);
    }

    /**
     * Shows the Match results of a given Match.
     *
     * @param m the given Match.
     * @throws SQLException
     */
    public void matchResults(Match m) throws SQLException
    {
        db.matchResults(m);
    }

    /**
     * Shows whether a given Match has been played.
     *
     * @param id the given Match id.
     * @return returns 1 if the match has been played, 0 if not.
     * @throws SQLException
     */
    public int isPlayed(int id) throws SQLException
    {
        return db.isPlayed(id);
    }

    /**
     * Shows the total number of matches.
     *
     * @return the number of matches.
     * @throws SQLException
     */
    public int showCount() throws SQLException
    {
        return db.countMatches();
    }

    /**
     * Shows the number of Matches in Group plays with a given team id.
     *
     * @param id the given team id.
     * @return returns the number of matches.
     * @throws SQLException
     */
    public int showCountTeamGroup(int id) throws SQLException
    {
        return db.countTeamGroupMatchesByTeamId(id);
    }

    /**
     * Shows the highest Match Round.
     *
     * @return returns the highest Match Round.
     * @throws SQLException
     */
    public int maxMatchRound() throws SQLException
    {
        return db.maxRound();
    }

    /**
     * Shows the highest Match Id.
     *
     * @return returns the highest Match Id.
     * @throws SQLException
     */
    public int maxMatchId() throws SQLException
    {
        return db.maxId();
    }

    /**
     * Shows the Group matches for a team with a given team id.
     *
     * @param id the given team id.
     * @return returns the group matches for a team.
     * @throws SQLException
     */
    public ArrayList<Match> getGroupMatchesByTeamId(int id) throws SQLException
    {
        return db.getGroupMatchesByTeamId(id);
    }

    private ArrayList<Team> getGroup(int groupId, ArrayList<Team> teams)
    {
        ArrayList<Team> group = new ArrayList();

        for (Team t : teams)
        {
            if (t.getGroupId() == groupId)
            {
                group.add(t);
            }
        }
        return group.isEmpty() ? null : group;
    }

    /**
     * Schedules the group matches.
     *
     * @param teams an ArrayList of teams, scheduled to group matches.
     * @return
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> schedule(ArrayList<Team> teams) throws SQLException
    {
        ArrayList<MatchScheduling> matches = new ArrayList();
        int i = 1;


        while (getGroup(i, teams) != null)
        {
            ArrayList<Team> group = getGroup(i, teams);
            ArrayList<Team> group2 = getGroup(i + 1, teams);
            ArrayList<MatchScheduling> groupMatches = new ArrayList();

            if (group.size() == 4)
            {
                /*
                 * Round 1
                 */
                groupMatches.add(new MatchScheduling(1, group.get(0), group.get(1)));
                groupMatches.add(new MatchScheduling(1, group.get(2), group.get(3)));

                /*
                 * Round 2
                 */
                groupMatches.add(new MatchScheduling(2, group.get(0), group.get(2)));
                groupMatches.add(new MatchScheduling(2, group.get(1), group.get(3)));

                /*
                 * Round 3
                 */
                groupMatches.add(new MatchScheduling(3, group.get(0), group.get(3)));
                groupMatches.add(new MatchScheduling(3, group.get(1), group.get(2)));

                /*
                 * Round 4
                 */
                groupMatches.add(new MatchScheduling(4, group.get(1), group.get(0)));
                groupMatches.add(new MatchScheduling(4, group.get(3), group.get(2)));

                /*
                 * Round 5
                 */
                groupMatches.add(new MatchScheduling(5, group.get(2), group.get(0)));
                groupMatches.add(new MatchScheduling(5, group.get(3), group.get(1)));

                /*
                 * Round 6
                 */
                groupMatches.add(new MatchScheduling(6, group.get(3), group.get(0)));
                groupMatches.add(new MatchScheduling(6, group.get(2), group.get(1)));
            }
            else
            {
                /*
                 * Round 1
                 */
                groupMatches.add(new MatchScheduling(1, group.get(0), group.get(1)));

                /*
                 * Round 2
                 */
                groupMatches.add(new MatchScheduling(2, group.get(2), group.get(1)));

                /*
                 * Round 3
                 */
                groupMatches.add(new MatchScheduling(3, group.get(0), group.get(2)));

                /*
                 * Round 4
                 */
                groupMatches.add(new MatchScheduling(4, group.get(1), group.get(2)));

                /*  
                 * Round 5
                 */
                groupMatches.add(new MatchScheduling(5, group.get(1), group.get(0)));

                /*
                 * Round 6
                 */
                groupMatches.add(new MatchScheduling(6, group.get(2), group.get(0)));
            }
            matches.addAll(groupMatches);
            i++;
            for (MatchScheduling matchScheduling : groupMatches)
            {
                db.addMatches(matchScheduling);
            }

        }
        return null;
    }

    /**
     * Schedules the quarter finals.
     *
     * @param teams an ArrayList of teams, already ranked.
     * @return
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> scheduleQuarterFinals(ArrayList<Team> teams) throws SQLException
    {
        ArrayList<MatchScheduling> matches = new ArrayList();
        int i = 1;


        while (getGroup(i + 1, teams) != null)
        {
            ArrayList<Team> group = getGroup(i, teams);
            ArrayList<Team> group2 = getGroup(i + 1, teams);
            ArrayList<MatchScheduling> quarterFinalMatches = new ArrayList();

            /*
             * Round 7 (Quarter finals)
             */
            quarterFinalMatches.add(new MatchScheduling(7, group.get(0), group2.get(1)));
            quarterFinalMatches.add(new MatchScheduling(7, group2.get(0), group.get(1)));

            matches.addAll(quarterFinalMatches);
            i += 2;
            for (MatchScheduling matchScheduling : quarterFinalMatches)
            {
                db.addMatches(matchScheduling);
            }

        }
        return null;
    }

    /**
     * First creates an ArrayList of winners of the quarter finals. Schedules
     * the semi finals
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> scheduleSemiFinals() throws SQLException
    {
        ArrayList<MatchScheduling> matches = new ArrayList();

        ArrayList<Team> s1Teams = new ArrayList();
        ArrayList<Team> s2Teams = new ArrayList();




        for (int k = 0; k <= 1; k++)
        {
            int homeTeamId = getById(listByMatchRound(7).get(k).getId()).getHomeTeamId();
            int guestTeamId = getById(listByMatchRound(7).get(k).getId()).getGuestTeamId();

            if (getById(listByMatchRound(7).get(k).getId()).getHomeGoals() > getById(listByMatchRound(7).get(k).getId()).getGuestGoals())
            {
                Team hw = teammgr.getById(homeTeamId);
                s1Teams.add(hw);
            }
            else
            {
                Team gw = teammgr.getById(guestTeamId);
                s1Teams.add(gw);
            }
        }

        for (int k = 2; k <= 3; k++)
        {
            int homeTeamId = getById(listByMatchRound(7).get(k).getId()).getHomeTeamId();
            int guestTeamId = getById(listByMatchRound(7).get(k).getId()).getGuestTeamId();

            if (getById(listByMatchRound(7).get(k).getId()).getHomeGoals() > getById(listByMatchRound(7).get(k).getId()).getGuestGoals())
            {
                Team hw = teammgr.getById(homeTeamId);
                s2Teams.add(hw);
            }
            else
            {
                Team gw = teammgr.getById(guestTeamId);
                s2Teams.add(gw);
            }
        }

        ArrayList<MatchScheduling> semiFinalMatches = new ArrayList();

        /*
         * Round 8 (Semi finals)
         */
        semiFinalMatches.add(new MatchScheduling(8, s1Teams.get(0), s1Teams.get(1)));
        semiFinalMatches.add(new MatchScheduling(8, s2Teams.get(0), s2Teams.get(1)));

        matches.addAll(semiFinalMatches);

        for (MatchScheduling m : semiFinalMatches)
        {
            db.addMatches(m);
        }
        return null;
    }

    /**
     * First creates an ArrayList with winning teams of semi finals Schedules
     * the final.
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<MatchScheduling> scheduleFinal() throws SQLException
    {
        ArrayList<MatchScheduling> matches = new ArrayList();

        ArrayList<Team> f1Teams = new ArrayList();

        for (int k = 0; k <= 1; k++)
        {
            int homeTeamId = getById(listByMatchRound(8).get(k).getId()).getHomeTeamId();
            int guestTeamId = getById(listByMatchRound(8).get(k).getId()).getGuestTeamId();

            if (getById(listByMatchRound(8).get(k).getId()).getHomeGoals() > getById(listByMatchRound(8).get(k).getId()).getGuestGoals())
            {
                Team hw = teammgr.getById(homeTeamId);
                f1Teams.add(hw);
            }
            else
            {
                Team gw = teammgr.getById(guestTeamId);
                f1Teams.add(gw);
            }
        }

        ArrayList<MatchScheduling> finalMatch = new ArrayList();

        /*
         * Round 9 (Final)
         */
        finalMatch.add(new MatchScheduling(9, f1Teams.get(0), f1Teams.get(1)));

        matches.addAll(finalMatch);

        for (MatchScheduling m : finalMatch)
        {
            db.addMatches(m);
        }
        return null;
    }
     
    public ArrayList<Match> listAll() throws SQLException
    {
        return db.listAll();
    }
}
