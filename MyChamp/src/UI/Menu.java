/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public abstract class Menu
{

    protected int EXIT_OPTION = 0;
    private final String header;
    private final String[] menuItems;
    
    /**
     * Creater Menu, Header and Menu items.
     *
     * @param header
     * @param menuItems
     */
    public Menu(String header, String... menuItems)
    {
        this.header = header;
        this.menuItems = menuItems;
    }

    /**
     * create the doaction options.
     *
     * @param run
     */
    public void run()
    {
        boolean done = false;
        while (!done)
        {
            showMenu();
            int option = getOption();
            doAction(option);
            if (option == EXIT_OPTION)
            {
                done = true;
            }
        }
    }

    /**
     * Prints the header.
     */
    protected void printshowHeader()
    {
        clear();
        System.out.printf("%3s %-6s %-25s %-25s %-30s %-12s %-10s \n", "", "Id", "School", "Team Captain", "Email", "Group Id", "Points");
        System.out.println("");
    }

    protected void printshowMatchHeader()
    {
        clear();
        System.out.printf("%3s %-6s %-15s %-15s %-15s %-10s %-15s %-15s", "", "Id", "MatchRound", "HomeTeamID", "GuestTeamID", "IsPlayed", "HomeGoals", "GuestGoals");
        System.out.println("");
    }

    /**
     *
     */
    private void showMenu()
    {
        clear();
        System.out.println();
        System.out.println(header.toUpperCase());
        System.out.println();

        for (int i = 0; i < menuItems.length; i++)
        {
            System.out.println(String.format("%2d)  %s", (i + 1), menuItems[i]));
        }
        System.out.println(String.format("%2d)  %s", EXIT_OPTION, "Exit"));
    }

    private int getOption()
    {
        while (true)
        {
            try
            {
                System.out.print("\nEnter option: ");
                int option = new Scanner(System.in).nextInt();
                if (option >= 1 && option <= menuItems.length
                        || option == EXIT_OPTION)
                {
                    return option;
                }
                else
                {
                    System.out.println("\nERROR - Invalid option.");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("ERROR - Not a number.");
            }
        }
    }

    /**
     * clear 50 rows.
     */
    protected void clear()
    {
        for (int i = 0; i < 50; i++)
        {
            System.out.println();
        }
    }

    /**
     * Pause function, where keypress is required to continue.
     */
    protected void pause()
    {
        System.out.println("\nPress ENTER to continue...");
        new Scanner(System.in).nextLine();
    }

    abstract protected void doAction(int option);
}
