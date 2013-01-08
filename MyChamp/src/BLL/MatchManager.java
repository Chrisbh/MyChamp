/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

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
}