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

    private int matchRound;
    private Team HomeTeam;
    private Team GuestTeam;

    public MatchScheduling(int matchRound, Team homeTeam, Team guestTeam)
    {
        this.matchRound = matchRound;
        this.HomeTeam = homeTeam;
        this.GuestTeam = guestTeam;
    }

    /**
     * @return the HomeTeam
     */
    public Team getHomeTeam()
    {
        return HomeTeam;
    }

    /**
     * @param HomeTeam the HomeTeam to set
     */
    public void setHomeTeam(Team HomeTeam)
    {
        this.HomeTeam = HomeTeam;
    }

    /**
     * @return the GuestTeam
     */
    public Team getGuestTeam()
    {
        return GuestTeam;
    }

    /**
     * @param GuestTeam the GuestTeam to set
     */
    public void setGuestTeam(Team GuestTeam)
    {
        this.GuestTeam = GuestTeam;
    }

    /**
     * @return the matchRound
     */
    public int getMatchRound()
    {
        return matchRound;
    }

    /**
     * @param matchRound the matchRound to set
     */
    public void setMatchRound(int matchRound)
    {
        this.matchRound = matchRound;
    }
}
