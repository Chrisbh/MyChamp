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

    public MatchManager() throws Exception
    {
        db = new MatchDBManager();
        teammgr = new TeamManager();
    }

    public static MatchManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new MatchManager();
        }
        return instance;
    }

    private ArrayList<Team> getGroup(int groupID, ArrayList<Team> teams)
    {
        ArrayList<Team> group = new ArrayList();

        for (Team t : teams)
        {
            if (t.getGroupId() == groupID)
            {
                group.add(t);
            }
        }
        return group.isEmpty() ? null : group;
    }

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

    public ArrayList<Match> listByMatchRound(int matchRound) throws SQLException
    {
        return db.listByMatchRound(matchRound);
    }

    public ArrayList<MatchScheduling> scheduleSemiFinals() throws SQLException
    {
        ArrayList<MatchScheduling> matches = new ArrayList();

        ArrayList<Team> s1Teams = new ArrayList();
        ArrayList<Team> s2Teams = new ArrayList();


        for (int k = 0; k <= 1; k++)
        {
            if (getByID(listByMatchRound(7).get(k).getID()).getHomeGoals() > getByID(listByMatchRound(7).get(k).getID()).getGuestGoals())
            {
                Team hw = teammgr.getByID(getByID(listByMatchRound(7).get(k).getID()).getHomeTeamID());
                s1Teams.add(hw);
            }
            else
            {
                Team gw = teammgr.getByID(getByID(listByMatchRound(7).get(k).getID()).getGuestTeamID());
                s1Teams.add(gw);
            }
        }

        for (int k = 2; k <= 3; k++)
        {
            if (getByID(listByMatchRound(7).get(k).getID()).getHomeGoals() > getByID(listByMatchRound(7).get(k).getID()).getGuestGoals())
            {
                Team hw = teammgr.getByID(getByID(listByMatchRound(7).get(k).getID()).getHomeTeamID());
                s2Teams.add(hw);
            }
            else
            {
                Team gw = teammgr.getByID(getByID(listByMatchRound(7).get(k).getID()).getGuestTeamID());
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

    public ArrayList<MatchScheduling> scheduleFinal() throws SQLException
    {
        ArrayList<MatchScheduling> matches = new ArrayList();

        ArrayList<Team> f1Teams = new ArrayList();

        for (int k = 0; k <= 1; k++)
        {
            if (getByID(listByMatchRound(8).get(k).getID()).getHomeGoals() > getByID(listByMatchRound(8).get(k).getID()).getGuestGoals())
            {
                Team hw = teammgr.getByID(getByID(listByMatchRound(8).get(k).getID()).getHomeTeamID());
                f1Teams.add(hw);
            }
            else
            {
                Team gw = teammgr.getByID(getByID(listByMatchRound(8).get(k).getID()).getGuestTeamID());
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

    public ArrayList<MatchScheduling> viewSchedule() throws SQLException
    {
        return db.viewSchedule();
    }

    public ArrayList<MatchScheduling> viewSchedulePlayed() throws SQLException
    {
        return db.viewSchedulePlayed();
    }

    public Match getByID(int id) throws SQLException
    {
        return db.getByID(id);
    }

    public ArrayList<MatchScheduling> viewGroupSchedule(int groupID) throws SQLException
    {
        return db.viewGroupSchedule(groupID);
    }

    public ArrayList<MatchScheduling> viewTeamSchedule(int teamID) throws SQLException
    {
        return db.viewTeamSchedule(teamID);
    }

    public void matchResults(Match m) throws SQLException
    {
        db.matchResults(m);
    }

    public int isPlayed(int id) throws SQLException
    {
        return db.isPlayed(id);
    }

    public int showCount() throws SQLException
    {
        return db.countMatches();
    }

    public int maxMatchID() throws SQLException
    {
        return db.maxID();
    }

    public void deleteFromTeamAndMatch(int id) throws SQLException
    {
        db.deleteFromTeamAndMatch(id);
    }

    public int maxMatchRound() throws SQLException
    {
        return db.maxRound();
    }

    public ArrayList<Match> getGroupMatchesByTeamID(int id) throws SQLException
    {
        return db.getGroupMatchesByTeamID(id);
    }

    public int showCountTeamGroup(int id) throws SQLException
    {
        return db.countTeamGroupMatchesByTeamID(id);
    }
//    public ArrayList<Match> sortTeamsByRank(int m1, int m2) throws SQLException
//    {
//        return db.sortTeamsByRank(m1, m2);
//    }
}
