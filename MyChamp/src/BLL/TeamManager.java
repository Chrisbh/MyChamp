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
     * Gets an instance of TeamManager.
     *
     * @return returns the instance.
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
     * Lists all the teams.
     *
     * @return returns an ArrayList of teams.
     * @throws SQLException
     */
    public ArrayList<Team> listAll() throws SQLException
    {
        return db.listAll();
    }

    /**
     * Lists all the teams, ranked after criterias.
     *
     * @return returns an ArrayList of teams.
     * @throws SQLException
     */
    public ArrayList<Team> listGroupRanked() throws SQLException
    {
        return db.listGroupRanked();
    }

    /**
     * Gets a team by a given id.
     *
     * @param id the given id.
     * @return returns the team with the given id.
     * @throws SQLException
     */
    public Team getById(int id) throws SQLException
    {
        return db.getById(id);
    }

    /**
     * Gets a team with a given group id.
     *
     * @param id the given group id.
     * @return returns the team.
     * @throws SQLException
     */
    public ArrayList getByGroupId(int id) throws SQLException
    {
        return db.getByGroupId(id);
    }

    /**
     * Adds a team.
     *
     * @param t the team to add.
     * @throws SQLException
     */
    public void add(Team t) throws SQLException
    {
        db.addTeam(t);
    }

    /**
     * Updates a team.
     *
     * @param team the team to update.
     * @throws SQLException
     */
    public void update(Team team) throws SQLException
    {
        db.update(team);
    }

    /**
     * clears the database.
     *
     * @throws SQLException
     */
    public void resetAll() throws SQLException
    {
        db.resetAll();
        System.out.println("Cleared!");
    }

    /**
     * Deletes an row in both team and the matches they are scheduled in. With a
     * given team id.
     *
     * @param id the given team id.
     * @throws SQLException
     */
    public void deleteFromTeamAndMatch(int id) throws SQLException
    {
        db.deleteFromTeamAndMatch(id);
    }

    /**
     * Shows the number of teams added.
     *
     * @return returns the number of teams.
     * @throws SQLException
     */
    public int showCount() throws SQLException
    {
        return db.count();
    }

    /**
     * Gives points to a team.
     * @param points the amount of points to update to.
     * @param team the team to give points.
     * @throws SQLException
     */
    public void setPoints(int points, Team team) throws SQLException
    {
        db.setPoints(points, team);
    }

    /**
     * Shuffles the list of teams, and assigns them groups like 1,2,3,4,1,2,3,4.
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
