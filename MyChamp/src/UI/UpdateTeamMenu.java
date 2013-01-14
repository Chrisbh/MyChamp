/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import BE.Team;
import BLL.TeamManager;
import java.util.Scanner;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class UpdateTeamMenu extends Menu
{

    private Team team;
    private TeamManager teammgr;

    /**
     * Lists the different Menu options in UpdateTeamMenu
     *
     * @param team 
     */
    public UpdateTeamMenu(Team team)
    {
        super("Update Team", "Update School", "Update Team Captain", "Update E-mail");
        try
        {
            this.team = team;
            teammgr = new TeamManager();
        }
        catch (Exception e)
        {
            System.out.println("ERROR - " + e.getMessage());
        }
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
                updateSchool();
                break;
            case 2:
                updateTeamCaptain();
                break;
            case 3:
                updateEmail();
                break;
            case 0:
                saveChanges();
                break;

        }

    }

    /**
     * Update School Name.
     *
     */
    private void updateSchool()
    {
        System.out.println();
        System.out.println("Update school:");
        String school = new Scanner(System.in, "iso-8859-1").nextLine();

        team.setSchool(school);
    }

    /**
     * Update Team Captain Name.
     *
     */
    private void updateTeamCaptain()
    {
        System.out.println();
        System.out.println("Update team captain:");
        String teamCaptain = new Scanner(System.in, "iso-8859-1").nextLine();

        team.setTeamCaptain(teamCaptain);
    }

    /**
     * Update Email.
     *
     */
    private void updateEmail()
    {
        System.out.println();
        System.out.println("Update email:");
        String email = new Scanner(System.in, "iso-8859-1").nextLine();

        team.setEmail(email);
    }

    /**
     * Exits the UpdateTeamMenu and save changes.
     *
     */
    private void saveChanges()
    {
        System.out.println();
        System.out.println("Saving changes......");
        try
        {
            TeamManager.getInstance().update(team);
        }
        catch (Exception e)
        {
            System.out.println("Save could not be completed!");
            System.out.println("ERROR - " + e.getMessage());
            pause();
        }
        System.out.println();
        System.out.println("You have chosen to exit!");
        System.out.println("You will return to the Team Menu");
    }
}
