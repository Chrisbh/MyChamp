/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Team;
import DAL.TeamDBManager;
import java.security.acl.Group;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class TeamManager
{

    private TeamDBManager db = null;
    private static TeamManager instance = null;
    private Team team;
    private Group group;
    private int i = 1;
    private int given = 1;

    public TeamManager() throws Exception
    {
        db = new TeamDBManager();
    }

    public static TeamManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new TeamManager();
        }
        return instance;
    }

    public Team getByID(int id) throws SQLException
    {
        return db.getByID(id);
    }

    public ArrayList<Team> listAll() throws SQLException
    {
        return db.ListAll();
    }

    public void update(Team team) throws SQLException
    {
        db.update(team);
    }

    public void add(Team t) throws SQLException
    {
        db.addTeam(t);
    }

    public int showCount() throws SQLException
    {
        return db.Count();
    }

    public void assignRandomGroups() throws SQLException
    {
        int x = 4;
        int MaxGroups = 4;
        int currentGroup = 1;

        ArrayList<Team> temp = listAll();
        Collections.shuffle(temp);
        ArrayList<ArrayList<Team>> Groups = new ArrayList();
        for (int i = 0; i < x; i++)
        {
            Groups.add(new ArrayList());
        }

        for (Team t : temp)
        {
            db.assign(t, currentGroup++);

            if (currentGroup > MaxGroups)
            {
                currentGroup = 1;
            }
        }
        System.out.println("Groups have been assigned !!");
        
        /*
         * Quarter final dummy teams
         */
        Team q1 = new Team("Quarter Final 1 Winner", "", "", 0, 0);
        Team q2 = new Team("Quarter Final 2 Winner", "", "", 0, 0);
        Team q3 = new Team("Quarter Final 3 Winner", "", "", 0, 0);
        Team q4 = new Team("Quarter Final 4 Winner", "", "", 0, 0);
        
        /*
         * Semi final dummy teams
         */
        Team s1 = new Team("Semi Final 1 Winner", "", "", 0, 0);
        Team s2 = new Team("Semi Final 2 Winner", "", "", 0, 0);
        
        /*
         * Adding the dummy teams to the database
         */
        db.addTeam(q1);
        db.addTeam(q2);
        db.addTeam(q3);
        db.addTeam(q4);
        db.addTeam(s1);
        db.addTeam(s2);
    }
    
    public ArrayList getByGroupID(int id) throws SQLException
    {
        return db.getByGroupID(id);
    }
    public ArrayList<Team> orderByPoints() throws SQLException
    {
        return db.orderedByPoints();
    }
    public void givePoints(int points, Team team) throws SQLException
    {
        db.givePoints(points, team);
    }
}
