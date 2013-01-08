/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.Match;
import BE.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class MatchDBManager extends MyChampDBManager
{
    public MatchDBManager() throws Exception
    {
        super();
    }
        
    public ArrayList<Match> Scheduling(Team t) throws SQLException
    {
        try(Connection con = ds.getConnection())
        {
            String query = "SELECT School, GroupID, GroupName FROM Team, "
                    + "Group WHERE Team.GroupID = Group.ID AND GroupID = ?";
            
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, t.getGroupId());
            
            ResultSet rs = ps.executeQuery();
            
            ArrayList matches = new ArrayList();
            while (rs.next())
            {
                String school = rs.getString("School");
                int groupID = rs.getInt("groupID");
                String groupName = rs.getString("GroupName");
                  
                matches.add(school);
                matches.add(groupID);
                matches.add(groupName);
            }
            return matches;
        }
        
    }
}
