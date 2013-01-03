/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Chris
 */
public class Team
{
    
    private final int id;
    private String school;
    private String teamCaptain;
    private String email;
    private int groupId;
    
    public Team(int id, String school, String teamCaptain, String email, int groupId)
    {
        this.id = id;
        this.school = school;
        this.teamCaptain = teamCaptain;
        this.email = email;
        this.groupId = groupId;
    }
    
    public Team(String school, String teamCaptain, String email, int groupId)
    {
        this(-1, school, teamCaptain, email, groupId);
    }
    
    public Team(int id, Team t)
    {
        this(id, t.school, t.teamCaptain, t.email, t.groupId);
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
        return String.format("%6s %3s %3s %3s %3s", id, school, teamCaptain, email, groupId);
    }
}
