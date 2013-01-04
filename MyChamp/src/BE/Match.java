/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Chris
 */
public class Match
{
    private int ID;
    private int MatchRound;
    private int HomeTeamID;
    private int GuestTeamID;
    private int IsPlayed = 0;
    private int HomeGoals;
    private int GuestGoals;
    
    public Match( int ID, int matchRound, int homeTeamID, int guestTeamID, int isPlayed, int homeGoals, int guestGoals)
    {
        this.ID = ID;
        this.MatchRound = matchRound;
        this.HomeTeamID = homeTeamID;
        this.GuestTeamID = guestTeamID;
        this.IsPlayed = isPlayed;
        this.HomeGoals = homeGoals;
        this.GuestGoals = guestGoals;
    }

    /**
     * @return the ID
     */
    public int getID()
    {
        return ID;
    }

    /**
     * @return the matchRound
     */
    public int getMatchRound()
    {
        return MatchRound;
    }

    /**
     * @param matchRound the matchRound to set
     */
    public void setMatchRound(int matchRound)
    {
        this.MatchRound = matchRound;
    }

    /**
     * @return the homeTeamID
     */
    public int getHomeTeamID()
    {
        return HomeTeamID;
    }

    /**
     * @return the guestTeamID
     */
    public int getGuestTeamID()
    {
        return GuestTeamID;
    }

    /**
     * @return the isPlayed
     */
    public int getIsPlayed()
    {
        return IsPlayed;
    }

    /**
     * @param isPlayed the isPlayed to set
     */
    public void setIsPlayed(int isPlayed)
    {
        this.IsPlayed = isPlayed;
    }

    /**
     * @return the homeGoals
     */
    public int getHomeGoals()
    {
        return HomeGoals;
    }

    /**
     * @param homeGoals the homeGoals to set
     */
    public void setHomeGoals(int homeGoals)
    {
        this.HomeGoals = homeGoals;
    }

    /**
     * @return the guestGoals
     */
    public int getGuestGoals()
    {
        return GuestGoals;
    }

    /**
     * @param guestGoals the guestGoals to set
     */
    public void setGuestGoals(int guestGoals)
    {
        this.GuestGoals = guestGoals;
    }
    
    @Override
    public String toString()
    {
        return String.format("%3d, %-3d, %-3d, %-3d, %-3d, %-3d, %-3d, %-3d, %3d", ID, MatchRound, HomeTeamID, GuestTeamID, IsPlayed, HomeGoals, GuestGoals );
    }
}
