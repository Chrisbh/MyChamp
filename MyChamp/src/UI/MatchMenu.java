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
        new ViewScheduleMenu().run();
    }

    private void MatchResults()
    {
        clear();
        System.out.println("SHOW ALL MATCHES");
        System.out.println();

        try
        {
            printshowMatchHeader();
            ArrayList<Match> matches = matchmgr.listAll();

            for (Match m : matches)
            {
                System.out.println(m);
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
