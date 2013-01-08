/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Match;
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
    
    public ArrayList<Match> schedule(ArrayList<Team> teams)
    {
        ArrayList<Match> matches = new ArrayList();
        int i = 1;
        
        while(getGroup(i, teams) != null)
        {
            ArrayList<Team> group = getGroup(i, teams);
            ArrayList<Match> groupMatches = new ArrayList();
            
            if (group.size() == 4)
            {
                int t = 0;
            }
        }
    }
}