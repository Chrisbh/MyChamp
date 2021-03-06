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

    /**
     *
     */
    public MainMenu()
    {
        super("MyChamp Menu", "Team Menu", "Match Menu", "Ranking Menu", "Reset Menu");
        EXIT_OPTION = EXIT_VALUE;
    }

    /**
     * Type number for entering menu.
     *
     * @param option
     */
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
                resetMenu();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    private void teamMenu()
    {
        new TeamMenu().run();
    }

    private void matchMenu()
    {
        new MatchMenu().run();
    }

    private void rankingMenu()
    {
        new RankingMenu().run();
    }

    private void resetMenu()
    {
        new ResetMenu().run();
    }

    private void doActionExit()
    {
        System.out.println("You have chosen to exit!");
    }
}