/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Chris
 */
public class MatchScheduling
{

    private int matchInt;
    private Team homeTeam;
    private Team guestTeam;

    public MatchScheduling(int matchInt, Team homeTeam, Team guestTeam)
    {
        this.matchInt = matchInt;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
    }

    /**
     * @return the HomeTeam
     */
    public Team getHomeTeam()
    {
        return homeTeam;
    }

    /**
     * @param HomeTeam the HomeTeam to set
     */
    public void setHomeTeam(Team HomeTeam)
    {
        this.homeTeam = homeTeam;
    }

    /**
     * @return the GuestTeam
     */
    public Team getGuestTeam()
    {
        return guestTeam;
    }

    /**
     * @param GuestTeam the GuestTeam to set
     */
    public void setGuestTeam(Team GuestTeam)
    {
        this.guestTeam = guestTeam;
    }

    /**
     * @return the matchInt
     */
    public int getMatchInt()
    {
        return matchInt;
    }

    /**
     * @param matchInt the matchInt to set
     */
    public void setMatchInt(int matchInt)
    {
        this.matchInt = matchInt;
    }

    @Override
    public String toString()
    {
        return String.format("%3s %-6d %-10s %-10s", "", matchInt, homeTeam.getId(), guestTeam.getId());
    }
}
