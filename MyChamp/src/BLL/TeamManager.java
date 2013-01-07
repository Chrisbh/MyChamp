/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Team;
import DAL.TeamDBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Chris
 */
public class TeamManager
{

    private TeamDBManager db = null;
    private static TeamManager instance = null;
    private Team team;
    private Random rand;
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

    public void AssignGroups(Team team) throws SQLException
    {
        int counter = db.Count();
        rand = new Random();
        int min = 1;
        int max = 4;
        int randomNum = rand.nextInt(max - min + 1) + min;

        if (counter > 12 && counter <= 16)
        {

            System.out.println(randomNum);
        }
        else if (counter == 12)
        {
            while (i <= db.maxId() && given <= counter)
            {
                Team tm = db.getID(i);
                while (tm != null && i <= db.maxId())
                {
                    i++;
                    if (tm.getGroupId() == 0)
                {

//                db.assign(tm);
                    System.out.println(tm);
                    given++;
                }
                }        
                
                i++;
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
//        db.assign(team);
    }

    public int showNumber() throws SQLException
    {
        return db.Count();
    }
}
