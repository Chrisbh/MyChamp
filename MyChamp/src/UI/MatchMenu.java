/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Group;
import BE.Team;
import BLL.GroupManager;
import BLL.MatchManager;
import BLL.TeamManager;
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

    /**
     * Lists the different Menu options in MatchUI Menu
     *
     * @param Match menu
     */
    public MatchUIMenu()
    {
        super("Match UI Menu", "View Schedule", "Match Results", "Schedule Matchs");
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
        System.out.println("X");
        System.out.println();
        try
        {
            System.out.println("Select Group id: ");
            int id = new Scanner(System.in).nextInt();
            
            ArrayList<Team> teams = teammgr.getByGroupID(id);
            Group grp = groupmgr.getById(id);
            
            System.out.println(grp); 
            
            for(Team t : teams)
            {
                System.out.println(t);          
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    private void MatchResults()
    {
        System.out.println("X");
        System.out.println();
        try
        {
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
        System.out.println("X");
        System.out.println();
        try
        {
            
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
    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}
