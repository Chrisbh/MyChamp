/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BLL.MatchManager;
import BLL.TeamManager;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
class MatchUIMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;

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
        }
        catch (Exception e)
        {
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
