/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.Team;
import DAL.TeamDBManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class TeamManager
{
    private TeamDBManager db = null;
    private static TeamManager instance = null;
    
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
        db.assign(team);
    }
}
