/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis.
 */
public class MainMenu extends Menu
{

    private static final int EXIT_VALUE = 0;

    public MainMenu()
    {
        super("MyChamp Menu", "Team Menu", "Match Menu", "Ranking Menu");
        EXIT_OPTION = EXIT_VALUE;
    }

    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                TeamUIMenu();
                break;
            case 2:
                MatchUIMenu();
                break;
            case 3:
                RankingUIMenu();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    /**
     * Option for Opening the Team UI Menu
     */
    private void TeamUIMenu()
    {
        new TeamUIMenu().run();
    }

    /**
     * Option for Opening the Match UI Menu.
     */
    private void MatchUIMenu()
    {
        new MatchUIMenu().run();
    }

    private void RankingUIMenu()
    {
        new RankingMenu().run();
    }

    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}