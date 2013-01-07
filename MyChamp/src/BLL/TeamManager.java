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

    public void add(Team team) throws SQLException
    {
        db.addTeam(team);
    }

    public void AssignGroups() throws SQLException
    {
        int counter = db.Count();


        if (counter > 12 && counter <= 16)
        {
            while (i <= db.maxId() && given <= 16)
            {
            }
        }
        else if (counter == 12)
        {
            while (i <= db.maxId() && given <= 12)
            {
            }
        }
        else if (counter < 12)
        {
            System.out.println("Not enough teams.");
        }
        else
        {
            System.out.println("Too many teams.");
        }
    }

    public int showNumber() throws SQLException
    {
        return db.Count();
    }

    private void groupRandomizer() throws SQLException
    {
        int x = 4;
        int currentGroup = 0;
        
        ArrayList<Team> temp = listAll();
        Collections.shuffle(temp);
        ArrayList<ArrayList<Team>> Groups = new ArrayList();
        for (int i = 0; i < x; i++)
        {
            Groups.add(new ArrayList());
        }

        for (Team t : temp)
        {
            Groups.get(currentGroup).add(t);
            
            currentGroup ++;
            
            if (currentGroup == x)
            {
                currentGroup = 0;
            }
        }


    }
//    private void countID() throws SQLException
//    {
//        Team tm = db.getID(i);
//
//        if (tm == null)
//        {
//            i++;
//        }
//        else if (tm.getGroupId() == 0)
//        {
//            given++;
//            i++;
//        }
//    }
}
