/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Group;
import BE.MatchScheduling;
import BE.Team;
import BLL.GroupManager;
import BLL.MatchManager;
import BLL.TeamManager;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Dennis
 */
public class ViewScheduleMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;
    private GroupManager groupmgr;

    public ViewScheduleMenu()
    {
        super("Tournament Schedule", "Total Schedule", "Group Matches", "Team Matches", "Finals");
        EXIT_OPTION = EXIT_VALUE;
        
        try
        {
            teammgr = new TeamManager();
            matchmgr = new MatchManager();
            groupmgr = new GroupManager();
        }
        catch(Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
    }

    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                totalSchedule();
                break;
            case 2:
                groupMatches();
                break;
            case 3:
                teamMatches();
                break;
            case 4:
                finals();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    private void totalSchedule()
    {
        System.out.println("Showing the total schedule and scores.");
        System.out.println();
        try
        {
            
            ArrayList<MatchScheduling> matches = matchmgr.viewSchedule();
            ArrayList<MatchScheduling> matchesPlayed = matchmgr.viewSchedulePlayed();
            printShowIsPlayedHeader();
            for (MatchScheduling m : matchesPlayed)
            {
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-21s %-3d %-3s %-3d\n","", m.getMatchInt(), ":" ,
                        teammgr.getByID(m.getHomeTeam().getId()).getSchool(),
                        " VS ", teammgr.getByID(m.getGuestTeam().getId()).getSchool(),
                        matchmgr.getByID(m.getMatchInt()).getHomeGoals(),"-", 
                        matchmgr.getByID(m.getMatchInt()).getGuestGoals());
            }
            
            printShowScheduleHeader();
            for (MatchScheduling m : matches)
            { 
                
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n","", m.getMatchInt(),":", teammgr.getByID
                        (m.getHomeTeam().getId()).getSchool(), " VS ", 
                        teammgr.getByID(m.getGuestTeam().getId()).getSchool());           
            }
            
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    private void groupMatches()
    {
        clear();
        try
        {
            System.out.println("Select Group id: ");
            int id = new Scanner(System.in).nextInt();
            
            Group gm = groupmgr.getById(id);
            if (gm != null)
            {
            ArrayList<MatchScheduling> groupPlay = matchmgr.viewGroupSchedule(id);
            printShowGroupScheduleHeader();
            for(MatchScheduling g : groupPlay)
            {
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n","",g.getMatchInt(), ":", teammgr.getByID
                        (g.getHomeTeam().getId()).getSchool(), " VS ", 
                        teammgr.getByID(g.getGuestTeam().getId()).getSchool());          
            }
            }
            else
            {
                System.out.println("Group does not exist!");
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void teamMatches()
    {
        clear();
        try
        {
            System.out.println("Select Team id: ");
            int id = new Scanner(System.in).nextInt();
            Team tm = teammgr.getByID(id);
            if(tm != null)
            {
            ArrayList<MatchScheduling> teamPlay = matchmgr.viewTeamSchedule(id);
            printShowGroupScheduleHeader();
            for(MatchScheduling t : teamPlay)
            {
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n","",t.getMatchInt(), ":", teammgr.getByID
                        (t.getHomeTeam().getId()).getSchool(), " VS ", 
                        teammgr.getByID(t.getGuestTeam().getId()).getSchool());          
            }
            }
            else
            {
                System.out.println("Team does not exist!");
            }
        }
        catch(Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void finals()
    {
        clear();
        
        pause();
    }

    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}
