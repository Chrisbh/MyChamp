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

    public MatchManager() throws Exception
    {
        db = new MatchDBManager();
    }

    public static MatchManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new MatchManager();
        }
        return instance;
    }

    public ArrayList showGroups(Team t) throws SQLException
    {
        return db.Scheduling(t);
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
            System.out.println("Done!");
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
        int round = 7;


        while (getGroup(i+1, teams) != null)
        {
            ArrayList<Team> group = getGroup(i, teams);
            ArrayList<Team> group2 = getGroup(i+1, teams);
            ArrayList<MatchScheduling> quarterFinalMatches = new ArrayList();
       
            /*
             * Round 7
             */
            quarterFinalMatches.add(new MatchScheduling(round, group.get(0), group2.get(1)));
            round++;
            
            /*
             * Round 8
             */
            quarterFinalMatches.add(new MatchScheduling(round, group.get(1), group2.get(0)));
            
            matches.addAll(quarterFinalMatches);
            System.out.println("Done!");
            i+=2;
            round++;
            for (MatchScheduling matchScheduling : quarterFinalMatches)
            {
                db.addMatches(matchScheduling);
            }

        }
        return null;
    }

    public ArrayList<Match> listAll() throws SQLException
    {
        return db.listAll();
    }

    public ArrayList<MatchScheduling> viewSchedule() throws SQLException
    {
        return db.viewSchedule();
    }
}