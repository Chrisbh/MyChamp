/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class MainMenu extends Menu
{

    private static final int EXIT_VALUE = 0;

    public MainMenu()
    {
        super("MyChamp Menu", "Team Menu", "Match Menu", "Ranking Menu", "Reset Menu");
        EXIT_OPTION = EXIT_VALUE;
    }

    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                teamMenu();
                break;
            case 2:
                matchMenu();
                break;
            case 3:
                rankingMenu();
                break;
            case 4:
                resetEverythingMenu();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    /**
     * Option for Opening the Team Menu
     */
    private void teamMenu()
    {
        new TeamUIMenu().run();
    }

    /**
     * Option for Opening the Match Menu.
     */
    private void matchMenu()
    {
        new MatchMenu().run();
    }
    /**
     * Option for opening the Ranking Menu.
     */
    private void rankingMenu()
    {
        new RankingMenu().run();
    }
    
    /**
     * Option for opening the Reset Menu
     */
    private void resetEverythingMenu()
    {
        new ResetMenu().run();
    }
    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}