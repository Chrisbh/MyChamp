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

    /**
     *
     * @throws Exception
     */
    public TeamManager() throws Exception
    {
        db = new TeamDBManager();
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static TeamManager getInstance() throws Exception
    {
        if (instance == null)
        {
            instance = new TeamManager();
        }
        return instance;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Team getByID(int id) throws SQLException
    {
        return db.getByID(id);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Team> listAll() throws SQLException
    {
        return db.ListAll();
    }

    /**
     *
     * @param team
     * @throws SQLException
     */
    public void update(Team team) throws SQLException
    {
        db.update(team);
    }

    /**
     *
     * @param t
     * @throws SQLException
     */
    public void add(Team t) throws SQLException
    {
        db.addTeam(t);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public int showCount() throws SQLException
    {
        return db.Count();
    }

    /**
     *
     * @throws SQLException
     */
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
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList getByGroupID(int id) throws SQLException
    {
        return db.getByGroupID(id);
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Team> listGroupRanked() throws SQLException
    {
        return db.listGroupRanked();
    }

    /**
     *
     * @param points
     * @param team
     * @throws SQLException
     */
    public void setPoints(int points, Team team) throws SQLException
    {
        db.setPoints(points, team);
    }

    /**
     *
     * @throws SQLException
     */
    public void deleteAll() throws SQLException
    {
        db.deleteAll();
        System.out.println("Deleted!");
    }
}
