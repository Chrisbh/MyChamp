/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Team;
import DAL.TeamDBManager;
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
     * @return @throws Exception
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
     * @return @throws SQLException
     */
    public ArrayList<Team> listAll() throws SQLException
    {
        return db.listAll();
    }

    /**
     *
     * @return @throws SQLException
     */
    public ArrayList<Team> listGroupRanked() throws SQLException
    {
        return db.listGroupRanked();
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public Team getById(int id) throws SQLException
    {
        return db.getById(id);
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public ArrayList getByGroupId(int id) throws SQLException
    {
        return db.getByGroupId(id);
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
     * @param team
     * @throws SQLException
     */
    public void update(Team team) throws SQLException
    {
        db.update(team);
    }

    /**
     *
     * @throws SQLException
     */
    public void resetAll() throws SQLException
    {
        db.resetAll();
        System.out.println("Cleared!");
    }

    /**
     *
     * @param id
     * @throws SQLException
     */
    public void deleteFromTeamAndMatch(int id) throws SQLException
    {
        db.deleteFromTeamAndMatch(id);
    }

    /**
     *
     * @return @throws SQLException
     */
    public int showCount() throws SQLException
    {
        return db.count();
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
}
