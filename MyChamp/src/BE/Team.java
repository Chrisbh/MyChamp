/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class Team
{
    
    private final int id;
    private String school;
    private String teamCaptain;
    private String email;
    private int groupId;
    private int points;
    
    public Team(int id, String school, String teamCaptain, String email, int groupId, int points)
    {
        this.id = id;
        this.school = school;
        this.teamCaptain = teamCaptain;
        this.email = email;
        this.groupId = groupId;
        this.points = points;
    }
    
    public Team(String school, String teamCaptain, String email, int groupId, int points)
    {
        this(-1, school, teamCaptain, email, groupId, points);
    }
    
    public Team(int id, Team t)
    {
        this(id, t.school, t.teamCaptain, t.email, t.groupId, t.points);
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the school
     */
    public String getSchool()
    {
        return school;
    }

    /**
     * @param school the school to set
     */
    public void setSchool(String school)
    {
        this.school = school;
    }

    /**
     * @return the teamCaptain
     */
    public String getTeamCaptain()
    {
        return teamCaptain;
    }

    /**
     * @param teamCaptain the teamCaptain to set
     */
    public void setTeamCaptain(String teamCaptain)
    {
        this.teamCaptain = teamCaptain;
    }

    /**
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return the groupId
     */
    public int getGroupId()
    {
        return groupId;
    }

    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(int groupId)
    {
        this.groupId = groupId;
    }
    
    @Override
    public String toString()
    {
        return String.format("%3s %-6d %-25s %-25s %-30s %-12d %-10d","",id, school, teamCaptain, email, groupId, points);
    }

    /**
     * @return the points
     */
    public int getPoints()
    {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points)
    {
        this.points = points;
    }
}
