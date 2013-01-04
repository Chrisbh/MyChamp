/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BLL.MatchManager;
import BLL.TeamManager;

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
        super("Team UI Menu", "");
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
     * X
     */
    private void ListAllTeams()
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

    private void AddTeam()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void UpdateTeam()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void DeleteTeam()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void AssignToGroups()
    {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     *
     */
    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}
