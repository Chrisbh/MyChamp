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
class MatchMenu extends Menu
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
    public MatchMenu()
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
                viewSchedule();
                break;
            case 2:
                matchResults();
                break;
            case 3:
                scheduleMatch();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    /**
     *
     */
    private void viewSchedule()
    {
        new ViewScheduleMenu().run();
    }

    /**
     *
     */
    private void matchResults()
    {

        clear();
        System.out.println("Insert match results:");
        System.out.println();

        try
        {
            int matchCount = matchmgr.showCount();
            int teamCount = teammgr.showCount();
            int maxMatchId = matchmgr.maxMatchId();
            int teams = 12;
            ArrayList<MatchScheduling> matches = matchmgr.viewSchedule();
            printShowScheduleHeader();

            for (MatchScheduling m : matches)
            {
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", m.getMatchInt(),
                        ":", teammgr.getById(m.getHomeTeam().getId()).getSchool(), " VS ",
                        teammgr.getById(m.getGuestTeam().getId()).getSchool());
            }

            System.out.println("Select Match id: ");
            int id = new Scanner(System.in).nextInt();

            Match results = matchmgr.getById(id);
            if (results != null)
            {
                if (matchmgr.isPlayed(id) == 0)
                {
                    /*
                     * Set HomeTeam Goals
                     */
                    System.out.println("Enter goals scored for: "
                            + teammgr.getById(results.getHomeTeamId()).getSchool());
                    int homeGoals = new Scanner(System.in).nextInt();
                    results.setHomeGoals(homeGoals);

                    /*
                     * Set GuestTeam Goals
                     */
                    System.out.println("Enter goals scored for: "
                            + teammgr.getById(results.getGuestTeamId()).getSchool());
                    int guestGoals = new Scanner(System.in).nextInt();
                    results.setGuestGoals(guestGoals);

                    MatchManager.getInstance().matchResults(results);

                    System.out.println("Saved!!");
                    System.out.println();

                    if (results.getMatchRound() <= 6)
                    {
                        givePoints(homeGoals, guestGoals, results);
                    }
                    /*
                     * Checks for the next possiblity to schedule finals.
                     */
                    createSchedule(maxMatchId, matchCount, teamCount, teams);

                }
                /*
                 * If the match has been played:
                 */
                else
                {
                    System.out.println("Match has already been played!");
                }
                /*
                 * If the match already exist.
                 */
            }
            else
            {
                System.out.println("Match does not exist!");
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
    private void scheduleMatch()
    {

        clear();
        try
        {
            int matchCount = matchmgr.showCount();
            int teamCount = teammgr.showCount();
            if (teamCount > 0)
            {
                int tm = teammgr.getById(1).getGroupId();
                if (tm != 0)
                {
                    /*
                     * If no matches, schedules group plays.
                     */
                    if (matchCount < 1)
                    {
                        matchmgr.schedule(teammgr.listAll());
                        System.out.println("Group plays have been scheduled!");
                    }
                    else
                    {
                        System.out.println("Matches has already been scheduled!");
                    }
                }
                /*
                 * If groups is not assigned, returns an error.
                 */
                else
                {
                    System.out.println("You need to assign to groups before scheduling matches!");
                }
            }
            /*
             * If no teams, returns an error.
             */
            else
            {
                System.out.println("You need teams to schedule matches!");
            }
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
    private void givePoints(int homeGoals, int guestGoals, Match results) throws SQLException
    {
        int homeTeamID = results.getHomeTeamId();
        int guestTeamID = results.getGuestTeamId();
        /*
         * Point giving for HomeTeam.
         */
        if (homeGoals > guestGoals)
        {
            int currentPoints = teammgr.getById(homeTeamID).getPoints();
            teammgr.setPoints(currentPoints + 3, teammgr.getById(homeTeamID));
        }
        /*
         * Point giving for GuestTeam.
         */
        else if (homeGoals < guestGoals)
        {
            int currentPoints = teammgr.getById(guestTeamID).getPoints();
            teammgr.setPoints(currentPoints + 3, teammgr.getById(guestTeamID));
        }
        /*
         * If tied give both 1 point.
         */
        else
        {
            int currentPointsHome = teammgr.getById(homeTeamID).getPoints();
            int currentPointsGuest = teammgr.getById(guestTeamID).getPoints();
            teammgr.setPoints(currentPointsHome + 1, teammgr.getById(homeTeamID));
            teammgr.setPoints(currentPointsGuest + 1, teammgr.getById(guestTeamID));
        }
    }

    private void createSchedule(int maxMatchId, int matchCount, int teamCount, int teams) throws SQLException
    {
        /*
         * Int i equals the matches required for the number of teams.
         */
        for (int i = 24; i <= 48; i += 6)
        {
            int isPlayed = matchmgr.isPlayed(maxMatchId);
            if (matchCount == i && teamCount == teams && isPlayed == 1)
            {
                matchmgr.scheduleQuarterFinals(teammgr.listGroupRanked());
                System.out.println("Quarter Finals have been scheduled!");

            }
            else if (matchCount == i + 4 && teamCount == teams && isPlayed == 1)
            {
                matchmgr.scheduleSemiFinals();
                System.out.println("Semi Finals have been scheduled!");
            }
            else if (matchCount == i + 6 && teamCount == teams && isPlayed == 1)
            {
                matchmgr.scheduleFinal();
                System.out.println("Final has been scheduled!");
            }
            teams++;
        }
    }

    /**
     *
     */
    private void doActionExit()
    {
        System.out.println("You have chosen to exit!");
    }
}
