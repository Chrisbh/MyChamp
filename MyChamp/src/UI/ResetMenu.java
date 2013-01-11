/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BLL.TeamManager;

/**
 *
 * @author Dennis
 */
public class ResetMenu extends Menu
{
    private static final int EXIT_VALUE = 0;
    private TeamManager teammgr;

    public ResetMenu()
    {
        super("Reset Menu", "Reset Everything");
        EXIT_OPTION = EXIT_VALUE;
        try
        {
            teammgr = new TeamManager();
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
    }

    @Override
    protected void doAction(int option)
    {
        switch (option)
        {
            case 1:
                resetAll();
                break;
            case EXIT_VALUE:
                doActionExit();
        }
    }

    private void resetAll()
    {
        clear();
        try
        {
            teammgr.deleteAll();
        }
        catch(Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
        pause();
    }

    private void doActionExit()
    {
        System.out.println("YOU SELECTED EXIT !!");
    }
}
