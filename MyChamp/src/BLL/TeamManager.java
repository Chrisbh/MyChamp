/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Group;
import BE.Team;
import DAL.TeamDBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Chris
 */
public class TeamManager
{

    private TeamDBManager db = null;
    private static TeamManager instance = null;
    private Team team;
    private Group group;
    private GroupManager grpmngr;
    private int i = 1;
    private int given = 1;

    public TeamManager() throws Exception
    {
        db = new TeamDBManager();
        grpmngr = new GroupManager();

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
        return db.getID(id);
    }
    
    public Team getSchoolName(String School) throws SQLException
    {
        return db.getBySchoolName(School);
    }

    public ArrayList<Team> listAll() throws SQLException
    {
        return db.ListAll();
    }

    public void update(Team team) throws SQLException
    {
        db.update(team);
    }

    public void delete(int ID) throws SQLException
    {
        db.delete(ID);
    }

    public void add(Team t) throws SQLException
    {
        db.addTeam(t);
    }

    public int showNumber() throws SQLException
    {
        return db.Count();
    }

    public void getgroupRandomizer() throws SQLException
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
    }
}
