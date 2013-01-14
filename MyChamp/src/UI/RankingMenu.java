/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Team;
import BLL.MatchManager;
import BLL.RankManager;
import BLL.TeamManager;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
class RankingMenu extends Menu
{

    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;
    private MatchManager matchmgr;
    private RankManager rankmgr;

    /**
     * Lists the different Menu options in Ranking Menu
     *
     * @param Ranking menu
     */
    public RankingMenu()
    {
        super("Ranking UI Menu", "Group Ranking", "Final Ranking");
        try
        {
            teammgr = new TeamManager();
            matchmgr = new MatchManager();
            rankmgr = new RankManager();

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
                groupRanking();
                break;
            case 2:
                finalRanking();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    private void groupRanking()
    {
        System.out.println("Not yet implemented...");
        System.out.println();
        try
        {
            printShowHeader();
            
            Team[] teams = rankmgr.rankTeams(teammgr.listAll(), matchmgr.listAll());
            
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

    private void finalRanking()
    {
        System.out.println("Not yet implemented...");
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

    private void doActionExit()
    {
        System.out.println("You have chosen to exit!");
    }
}
