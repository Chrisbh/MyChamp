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
     * @param updateteammenu
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
     * @param doAction
     */
    @Override
    protected void doAction(int option)
    {

        switch (option)
        {
            case 1:
                UpdateSchool();
                break;
            case 2:
                UpdateTeamCaptain();
                break;
            case 3:
                UpdateEmail();
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
    private void UpdateSchool()
    {
        System.out.println();
        System.out.println("Update School");
        String School = new Scanner(System.in, "iso-8859-1").nextLine();

        team.setSchool(School);
    }

    /**
     * Update Team Captain Name.
     *
     */
    private void UpdateTeamCaptain()
    {
        System.out.println();
        System.out.println("Update Team Captain");
        String teamcaptain = new Scanner(System.in, "iso-8859-1").nextLine();

        team.setTeamCaptain(teamcaptain);
    }

    /**
     * Update Email.
     *
     */
    private void UpdateEmail()
    {
        System.out.println();
        System.out.println("Update Email");
        String email = new Scanner(System.in, "iso-8859-1").nextLine();

        team.setEmail(email);
    }

    /**
     * Exits the UpdateTeamMenu and save changes.
     *
     */
    private void saveChanges()
    {
        System.out.println("");
        System.out.println("SAVING CHANGES");
        try
        {
            TeamManager.getInstance().update(team);
        }
        catch (Exception e)
        {
            System.out.println("SAVE COULD NOT BE COMPLETED");
            System.out.println("ERROR - " + e.getMessage());
            pause();
        }
        System.out.println("");
        System.out.println("YOU SELECTED EXIT !!");
        System.out.println("YOU WILL RETURN TO TEAM MENU");
    }
}
