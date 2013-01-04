/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Team;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rasmus
 */
public class TeamDBManager extends MyChampDBManager
{
    public TeamDBManager() throws Exception
    {
        super();
    }
    
    public ArrayList<Team> ListAll() throws SQLException
    {
        try (Connection con = dataSource.getConnection())
        {
            String query = "SELECT * FROM Team, Group WHERE Group.ID = GroupID";
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            ArrayList<Team> allTeams = new ArrayList<>();
            while (rs.next())
            {
                int ID = rs.getInt("ID");
                String School = rs.getString("School");
                String TeamCaptain = rs.getString("Team Captain");
                String Email = rs.getString("Email");
                int GroupID = rs.getInt("Group ID");
                
                Team team = new Team(ID, School, TeamCaptain, Email, GroupID);
                allTeams.add(team);
            }
            return allTeams;
        }
    }
    
}
