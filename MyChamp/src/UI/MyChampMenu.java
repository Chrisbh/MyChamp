/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis.
 */
public class MyChampMenu extends Menu
{
    private static final int EXIT_VALUE = 0;

    public MyChampMenu()
    {
        super("MyChamp Menu", "TeamMenu", "MatchMenu");
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
            case EXIT_VALUE: doActionExit();
        }
    }
    /**
     * Option for Opening the Song Menu.
     */
    private void TeamUIMenu()
    {
        new TeamUIMenu().run();
    }
    /**
     * Option for Opening the PlayList Menu.
     */
    private void MatchUIMenu()
    {
        new MatchUIMenu().run();
    }

    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}