/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Match;
import BE.MatchScheduling;
import BLL.GroupManager;
import BLL.MatchManager;
import BLL.TeamManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
class MatchUIMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;
    private GroupManager groupmgr;
    private Match match;
    private MatchScheduling m;

    /**
     * Lists the different Menu options in MatchUI Menu
     *
     * @param Match menu
     */
    public MatchUIMenu()
    {
        super("Match UI Menu", "View Schedule", "Set Match Results", "Schedule Matches");
        try
        {
            teammgr = new TeamManager();
            matchmgr = new MatchManager();
            groupmgr = new GroupManager();

        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        EXIT_OPTION = EXIT_VALUE;
    }

    /**
     * Enter A Number, to move to submenu on...
     *
     * @param option
     */
    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                ViewSchedule();
                break;
            case 2:
                MatchResults();
                break;
            case 3:
                ScheduleMatch();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    /**
     *
     */
    private void ViewSchedule()
    {
        new ViewScheduleMenu().run();
    }

    private void MatchResults()
    {
        clear();
        System.out.println("Insert match results.");
        System.out.println();

        try
        {
            ArrayList<MatchScheduling> matches = matchmgr.viewSchedule();
            printShowScheduleHeader();
            for (MatchScheduling m : matches)
            { 
                
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n","", m.getMatchInt(),":", teammgr.getByID
                        (m.getHomeTeam().getId()).getSchool(), " VS ", 
                        teammgr.getByID(m.getGuestTeam().getId()).getSchool());           
            }

            System.out.println("Select Match id: ");
            int id = new Scanner(System.in).nextInt();
            Match results = matchmgr.getByID(id);
            
            if (matchmgr.isPlayed(id) == 0)
            {
            /*
             * HomeTeam Goals
             */
            System.out.println("Enter Goals scored for: " + teammgr.getByID(results.getHomeTeamID()).getSchool());
            int homeGoals = new Scanner(System.in).nextInt();
            results.setHomeGoals(homeGoals);
            
            /*
             * GuestTeam Goals
             */
            System.out.println("Enter Goals scored for: " + teammgr.getByID(results.getGuestTeamID()).getSchool());
            int guestGoals = new Scanner(System.in).nextInt();
            results.setGuestGoals(guestGoals);
            
            MatchManager.getInstance().matchResults(results);
            
            System.out.println("Saved!!");
            
            if (homeGoals > guestGoals)
            {
                
            }
            else if (homeGoals < guestGoals)
            {
                
            }
            else
            {
                
            }
            }
            else
            {
                System.out.println("Match has already been played!");
            }
            
            

        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();

    }

    /**
     *
     */
    private void ScheduleMatch()
    {
        clear();
        try
        {
            matchmgr.schedule(teammgr.listAll());
//            matchmgr.scheduleQuarterFinals(teammgr.orderByPoints());

        }
        catch (SQLException ex)
        {
            System.out.println("ERROR : " + ex.getMessage());
        }
        pause();
    }

    /**
     *
     */
    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}
