/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Team;
import BLL.MatchManager;
import BLL.TeamManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
class TeamMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;

    /**
     * Lists the different Menu options in Team Menu
     *
     * @param Team menu
     */
    public TeamMenu()
    {
        super("Team Menu", "List All Teams", "Add A Team", "Update A Team", "Delete A Team", "Assign Teams To Groups");
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
                listAllTeams();
                break;
            case 2:
                addTeam();
                break;
            case 3:
                updateTeam();
                break;
            case 4:
                deleteTeam();
                break;
            case 5:
                assignToGroups();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    private void listAllTeams()
    {
        clear();
        System.out.println("Show all teams:");
        System.out.println();

        try
        {
            printShowHeader();
            ArrayList<Team> teams = teammgr.listAll();

            for (Team t : teams)
            {
                System.out.println(t);
            }
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    private void addTeam()
    {
        clear();
        System.out.println("Add a new team:");
        System.out.println();

        try
        {
            int counter = teammgr.showCount();
            int maxId = teammgr.maxTeamId();
            Team tm = teammgr.getById(maxId);

            if (tm == null || tm.getGroupId() == 0)
            {
                if (counter <= 16)
                {
                    Scanner sc = new Scanner(System.in, "iso-8859-1");

                    System.out.println("School: ");
                    String school = sc.nextLine();

                    System.out.println("Team Captain: ");
                    String teamCaptain = sc.nextLine();

                    System.out.println("Email: ");
                    String email = sc.nextLine();

                    Team team = new Team(-1, school, teamCaptain, email, -1, -1);

                    teammgr.add(team);

                    System.out.println();
                    System.out.println("Team succesfully added!");
                }
                else
                {
                    System.out.println("Teams are limited to a total of 16 teams! Limit reached!");
                }
            }
            else
            {
                System.out.println("Groups have been assigned, therefore it's not possible to add more teams!");
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void updateTeam()
    {
        clear();
        System.out.println("Update team information:");
        System.out.println();

        try
        {
            ArrayList<Team> teams = teammgr.listAll();

            printShowHeader();
            for (Team t : teams)
            {
                System.out.println(t);
            }

            System.out.println("Select Team id: ");
            int id = new Scanner(System.in).nextInt();

            Team team = teammgr.getById(id);
            if (team != null)
            {
                new UpdateTeamMenu(team).run();
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

    private void deleteTeam()
    {
        clear();
        System.out.println("Delete Team:");
        System.out.println();
        try
        {
            int matchCount = matchmgr.showCount();
            int teamCount = teammgr.showCount();
            int maxMatchID = matchmgr.maxMatchId();

            ArrayList<Team> teams = teammgr.listAll();

            printShowHeader();
            for (Team t : teams)
            {
                System.out.println(t);
            }

            System.out.print("Select Team id: ");
            int id = new Scanner(System.in).nextInt();

            Team team = teammgr.getById(id);
            if (team != null)
            {
                removePoints(id);
                teammgr.deleteFromTeamAndMatch(id);
            }
            else
            {
                System.out.println("Team does not exist!");
            }
        }
        catch (Exception e)
        {
            System.out.println(" ERROR - " + e.getMessage());
        }
        pause();
    }

    private void assignToGroups()
    {
        clear();
        try
        {
            int counter = teammgr.showCount();
            int maxId = teammgr.maxTeamId();

            if (counter > 0)
            {
                int tm = teammgr.getById(maxId).getGroupId();
                if (tm == 0)
                {

                    System.out.println("CAUTION!");
                    System.out.println("If continuing with assigning groups, it wont be "
                            + "possible to add teams or assign groups again!");
                    System.out.println("Do you want to continue? Y/N");

                    Scanner sc = new Scanner(System.in, "iso-8859-1");
                    String further = sc.nextLine();

                    switch (further)
                    {
                        case "Y":
                            assignGroups(counter);
                            break;
                        case "y":
                            assignGroups(counter);
                            break;
                        case "N":
                            System.out.println("You have selected no.");
                            break;
                        case "n":
                            System.out.println("You have selected no.");
                            break;
                        default:
                            System.out.println("Y or N Required");
                            break;
                    }
                }
                else
                {
                    System.out.println("Groups have already been assigned!");
                }
            }
            else
            {
                System.out.println("You need teams to assign groups");
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getLocalizedMessage());
        }
        pause();
    }

    private void assignGroups(int counter)
    {
        if (counter >= 12 && counter <= 16)
        {
            System.out.println("Assigning teams to groups!....");

            try
            {
                teammgr.assignRandomGroups();
            }
            catch (Exception e)
            {
                System.out.println(" ERROR - " + e.getMessage());
            }
        }
        else if (counter < 12)
        {
            System.out.println("Too few teams to assign.");
        }
        else
        {
            System.out.println("Too many teams to assign.");
        }

    }

    private void removePoints(int id) throws SQLException
    {
        /*
         * Gets the number of group matches where the team id is playing or has played. 
         * The -1 is because the arraylist is not starting on 1 but 0, so 1 lower.
         */
        int matches = matchmgr.showCountTeamGroup(id) - 1;
        /*
         * The i in the for loop symbolises the place in the arraylist.
         */
        for (int i = 0; i <= matches; i++)
        {
            int guestTeamId = matchmgr.getGroupMatchesByTeamId(id).get(i).getGuestTeamId();
            int homeTeamId = matchmgr.getGroupMatchesByTeamId(id).get(i).getHomeTeamId();
            /*
             * Checking whether the selected match is played, if 1 continue
             */
            if (matchmgr.getGroupMatchesByTeamId(id).get(i).getIsPlayed() == 1)
            {
                /*
                 * Checks if the given team id is the same the hometeams id in the match.
                 */
                if (id == homeTeamId)
                {
                    /*
                     * Checks if the chosen team has lost or were tied
                     * if they lost, the winning team looses 3 points
                     * if tied the other team looses 1 point.
                     * if they wont we wont remove any points, since the team is getting deleted.
                     */
                    if (matchmgr.getGroupMatchesByTeamId(id).get(i).getHomeGoals() < matchmgr.getGroupMatchesByTeamId(id).get(i).getGuestGoals())
                    {
                        int currentPoints = teammgr.getById(guestTeamId).getPoints();
                        teammgr.setPoints(currentPoints - 3, teammgr.getById(guestTeamId));
                    }
                    else if (matchmgr.getGroupMatchesByTeamId(id).get(i).getHomeGoals() == matchmgr.getGroupMatchesByTeamId(id).get(i).getGuestGoals())
                    {
                        int currentPoints = teammgr.getById(guestTeamId).getPoints();
                        teammgr.setPoints(currentPoints - 1, teammgr.getById(guestTeamId));
                    }
                }
                /*
                 * Checks if the given team id is the same the guestteams id in the match.
                 */
                else if (id == guestTeamId)
                {
                    /*
                     * Checks if the chosen team has lost or were tied
                     * if they lost, the winning team looses 3 points
                     * if tied the other team looses 1 point.
                     * if they wont we wont remove any points, since the team is getting deleted.
                     */
                    if (matchmgr.getGroupMatchesByTeamId(id).get(i).getHomeGoals() > matchmgr.getGroupMatchesByTeamId(id).get(i).getGuestGoals())
                    {
                        int currentPoints = teammgr.getById(homeTeamId).getPoints();
                        teammgr.setPoints(currentPoints - 3, teammgr.getById(homeTeamId));
                    }
                    else if (matchmgr.getGroupMatchesByTeamId(id).get(i).getHomeGoals() == matchmgr.getGroupMatchesByTeamId(id).get(i).getGuestGoals())
                    {
                        int currentPoints = teammgr.getById(homeTeamId).getPoints();
                        teammgr.setPoints(currentPoints - 1, teammgr.getById(homeTeamId));
                    }
                }
            }
        }
    }

    private void doActionExit()
    {
        System.out.println("You have chosen to exit!");
    }
}
