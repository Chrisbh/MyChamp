/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BLL.TeamManager;
import java.util.Scanner;

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
             System.out.println("CAUTION!");
                System.out.println("If continuing with ressetting, all groups and matches will be deleted!");
                System.out.println("Do you want to continue? Y/N");
                Scanner sc = new Scanner(System.in, "iso-8859-1");
                String further = sc.nextLine();
                switch (further)
                {
                    case "Y":
                        teammgr.deleteAll();
                        break;
                    case "y":
                        teammgr.deleteAll();
                        break;
                    case "N":
                        System.out.println("You have selected no.");
                        break;
                    case "n":
                        System.out.println("You have selected no.");
                        break;
                    default:
                        System.out.println("Y or N Required");
                        break;
                }
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
