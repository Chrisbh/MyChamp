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
class RankingUIMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;

    /**
     * Lists the different Menu options in Ranking UI Menu
     *
     * @param Team UI menu
     */
    public RankingUIMenu()
    {
        super("Ranking UI Menu", "Group Ranking", "Final Ranking");
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
                GroupRanking();
                break;
            case 2:
                FinalRanking();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    /**
     * X
     */
    private void GroupRanking()
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

    private void FinalRanking()
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