/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Team;
import BLL.MatchManager;
import BLL.TeamManager;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis.
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
        System.out.println("Show all teams");
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
        System.out.println("X");
        System.out.println();
        try
        {
            System.out.println(teammgr.showNumber());

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
    private void UpdateTeam()
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
    private void DeleteTeam()
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
    private void AssignToGroups()
    {
        clear();
        System.out.println("Assign Team To Group");
        
        try
        {
            teammgr.getgroupRandomizer();
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
