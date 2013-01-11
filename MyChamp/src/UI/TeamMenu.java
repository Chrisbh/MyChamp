/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Team;
import BLL.MatchManager;
import BLL.TeamManager;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
class TeamUIMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;

    /**
     * Lists the different Menu options in Team UI Menu
     *
     * @param Team UI menu
     */
    public TeamUIMenu()
    {
        super("Team UI Menu", "List All Teams", "Add A Team", "Update A Team", "Delete A Team", "Assign Teams To A Group");
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
                ListAllTeams();
                break;
            case 2:
                AddTeam();
                break;
            case 3:
                UpdateTeam();
                break;
            case 4:
                DeleteTeam();
                break;
            case 5:
                AssignToGroups();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    /**
     *
     */
    private void ListAllTeams()
    {
        clear();
        System.out.println("SHOW ALL TEAMS");
        System.out.println();

        try
        {
            printshowHeader();
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

    /**
     *
     */
    private void AddTeam()
    {
        clear();
        System.out.println("ADD NEW TEAM");
        System.out.println();

        try
        {
            int counter = teammgr.showCount();
            ArrayList tm = teammgr.getByGroupID(1);
            if (tm == null)
            {
                if (counter <= 16)
                {
                    Scanner sc = new Scanner(System.in, "iso-8859-1");

                    System.out.println("School: ");
                    String school = sc.nextLine();

                    System.out.println("Team Captain: ");
                    String teamcaptain = sc.nextLine();

                    System.out.println("Email: ");
                    String email = sc.nextLine();

                    Team team = new Team(-1, school, teamcaptain, email, -1, -1);

                    teammgr.add(team);

                    System.out.println();
                    System.out.println("Team succesfully added");
                }
                else
                {
                    System.out.println("Teams are limited to a total of 16 teams! Limit Reached!");
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

    /**
     *
     */
    private void UpdateTeam()
    {
        clear();
        System.out.println("UPDATE TEAM INFORMATION:");
        System.out.println();

        try
        {
            TeamManager tManager = new TeamManager();
            ArrayList<Team> teams = tManager.listAll();

            printshowHeader();
            for (Team t : teams)
            {
                System.out.println(t);
            }

            System.out.println("Select Team id: ");
            int id = new Scanner(System.in).nextInt();
            Team team = teammgr.getByID(id);

            new UpdateTeamMenu(team).run();
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    /**
     *
     */
    private void DeleteTeam()
    {
        clear();
        System.out.println("DELETE TEAM:");
        System.out.println("");
        try
        {
            int matchCount = matchmgr.showCount();
            TeamManager tManager = new TeamManager();
            ArrayList<Team> teams = tManager.listAll();

            printshowHeader();
            for (Team t : teams)
            {
                System.out.println(t);
            }

            System.out.print("Select Team id: ");
            int id = new Scanner(System.in).nextInt();
            matchmgr.deleteFromTeamAndMatch(id);
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
    private void AssignToGroups()
    {
        clear();
        try
        {
            int counter = teammgr.showCount();
            int tm = teammgr.getByID(1).getGroupId();
            if (tm == 0)
            {
                if (counter >= 12 && counter <= 16)
                {
                    System.out.println("Assign Team To Group");

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
                    System.out.println("Too few teams to organize.");
                }
                else
                {
                    System.out.println("Too many teams to organize.");
                }
            }
            else
            {
                System.out.println("Groups have already been assigned!");
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getLocalizedMessage());
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
