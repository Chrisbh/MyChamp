/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Group;
import BE.Match;
import BE.MatchScheduling;
import BE.Team;
import BLL.GroupManager;
import BLL.MatchManager;
import BLL.TeamManager;
import java.sql.SQLException;
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

    /**
     *
     */
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
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
    }

    /**
     * Type a number for entering menu.
     *
     * @param option
     */
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
        System.out.println("Showing the total schedule and scores:");
        System.out.println();
        try
        {

            ArrayList<MatchScheduling> matches = matchmgr.viewSchedule();
            ArrayList<MatchScheduling> matchesPlayed = matchmgr.viewSchedulePlayed();
            printShowIsPlayedHeader();

            for (MatchScheduling m : matchesPlayed)
            {
                System.out.printf("%3s %-2d %-6s %-20s %-8s %-21s %-3d %-3s %-3d\n", "", m.getMatchInt(), ":",
                        teammgr.getById(m.getHomeTeam().getId()).getSchool(),
                        " VS ", teammgr.getById(m.getGuestTeam().getId()).getSchool(),
                        matchmgr.getById(m.getMatchInt()).getHomeGoals(), "-",
                        matchmgr.getById(m.getMatchInt()).getGuestGoals());
            }

            printShowScheduleHeader();
            for (MatchScheduling m : matches)
            {

                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", m.getMatchInt(), ":",
                        teammgr.getById(m.getHomeTeam().getId()).getSchool(), " VS ",
                        teammgr.getById(m.getGuestTeam().getId()).getSchool());
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
            System.out.println("Select group id: ");
            int id = new Scanner(System.in).nextInt();

            Group gm = groupmgr.getById(id);
            if (gm != null)
            {
                ArrayList<MatchScheduling> groupPlay = matchmgr.viewGroupSchedule(id);
                printShowGroupScheduleHeader();

                for (MatchScheduling g : groupPlay)
                {
                    System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", g.getMatchInt(), ":",
                            teammgr.getById(g.getHomeTeam().getId()).getSchool(), " VS ",
                            teammgr.getById(g.getGuestTeam().getId()).getSchool());
                }
            }
            else
            {
                System.out.println("Group does not exist!");
            }
        }
        catch (Exception e)
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
            ArrayList<Team> teams = teammgr.listAll();

            printShowHeader();
            for (Team t : teams)
            {
                System.out.println(t);
            }

            System.out.println("Select team id: ");
            int id = new Scanner(System.in).nextInt();

            Team tm = teammgr.getById(id);
            if (tm != null)
            {
                ArrayList<MatchScheduling> teamPlay = matchmgr.viewTeamSchedule(id);
                printShowGroupScheduleHeader();
                for (MatchScheduling t : teamPlay)
                {
                    System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", t.getMatchInt(), ":",
                            teammgr.getById(t.getHomeTeam().getId()).getSchool(), " VS ",
                            teammgr.getById(t.getGuestTeam().getId()).getSchool());
                }
            }
            else
            {
                System.out.println("Team does not exist!");
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void finals()
    {
        clear();
        try
        {
            ArrayList<Match> finals = matchmgr.listByMatchRound(9);
            ArrayList<Match> sFinals = matchmgr.listByMatchRound(8);
            ArrayList<Match> qFinals = matchmgr.listByMatchRound(7);

            int maxRound = matchmgr.maxMatchRound();

            printFinals(maxRound, finals, sFinals, qFinals);
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void printFinals(int maxRound, ArrayList<Match> finals, ArrayList<Match> sFinals, ArrayList<Match> qFinals) throws SQLException
    {
        /*
         * Quarter Finals
         */
        if (maxRound >= 7)
        {
            printShowQFinalHeader();
            for (Match m : qFinals)
            {

                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", m.getId(), ":",
                        teammgr.getById(m.getHomeTeamId()).getSchool(), " VS ",
                        teammgr.getById(m.getGuestTeamId()).getSchool());
            }
        }
        else
        {
            printShowQFinalHeader();
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "Winner of Group A", " VS ", "Second of Group B");
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "Winner of Group B", " VS ", "Second of Group A");
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "Winner of Group C", " VS ", "Second of Group D");
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "Winner of Group D", " VS ", "Second of Group C");
        }
        /*
         * Semi Finals
         */
        if (maxRound >= 8)
        {
            printShowSemiFinalHeader();
            for (Match m : sFinals)
            {

                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", m.getId(), ":",
                        teammgr.getById(m.getHomeTeamId()).getSchool(), " VS ",
                        teammgr.getById(m.getGuestTeamId()).getSchool());
            }
        }
        else
        {
            printShowSemiFinalHeader();
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "QuarterFinalWinner 1", " VS ", "QuarterFinalWinner 2");
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "QuarterFinalWinner 3", " VS ", "QuarterFinalWinner 4");
        }
        /*
         * Final
         */
        if (maxRound == 9)
        {
            printShowFinalHeader();
            for (Match m : finals)
            {

                System.out.printf("%3s %-2d %-6s %-20s %-8s %-20s\n", "", m.getId(), ":",
                        teammgr.getById(m.getHomeTeamId()).getSchool(), " VS ",
                        teammgr.getById(m.getGuestTeamId()).getSchool());
            }
        }
        else
        {
            printShowFinalHeader();
            System.out.printf("%3s %-2s %-6s %-20s %-8s %-20s\n", "", "-", ":",
                    "SemiFinalWinner 1", " VS ", "SemiFinalWinner 2");
        }
    }

    private void doActionExit()
    {
        System.out.println("You have chosen to exit!");
    }
}
