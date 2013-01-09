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
        System.out.println("X");
        System.out.println();
        try
        {
//            System.out.println("Select Group id: ");
//            int id = new Scanner(System.in).nextInt();
//            
//            ArrayList<Team> teams = teammgr.getByGroupID(id);
//            Group grp = groupmgr.getById(id);
//            
//            System.out.println(grp); 
//            
//            for(Team t : teams)
//            {
//                System.out.println(t);          
//            }
            ArrayList<MatchScheduling> matches = matchmgr.viewSchedule();
            
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
//    private void ScheduleMatch()
//    {
//        System.out.println("X");
//        System.out.println();
//        try
//        {
//            
//            for(int i = 1; i < 5; i++)
//            {
//                ArrayList<Team> teams = teammgr.getByGroupID(i);
//                Group grp = groupmgr.getById(i);
//                
//                ArrayList Groups = new ArrayList
//            }
//            
//        }
//        catch (Exception e)
//        {
//            System.out.println(" ERROR - " + e.getMessage());
//        }
//        matchStarted = true;
//        pause();
//    }
    private void ScheduleMatch()
    {
        clear();
        try
        {
//            matchmgr.schedule(teammgr.listAll());
            matchmgr.scheduleQuarterFinals(teammgr.orderByPoints());
            
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
