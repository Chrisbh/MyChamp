/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Counter;
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
    private Counter count;

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
        int counter = count.getNumberOfTeams();
//        ArrayList<id> teamid :int id = team.getId();
//        
//        for (Array<id> teamid : id)
//        {
//            if (id == 12)
//            {
//                System.out.println("12");
//            }
//
//            if (id == 16)
//            {
//                System.out.println("16");
//            }
        
        if(counter == 16)
        {
            Random rand;
        rand = new Random();
        int min = 1;
        int max = 4;
        
        int randomNum = rand.nextInt(max - min + 1) + min;
        }
        db.assign(team);
    }
    
    public Counter showNumber() throws SQLException
    {
        return db.Count();
    }
}
