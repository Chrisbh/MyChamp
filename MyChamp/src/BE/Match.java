/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class Match
{

    private int id;
    private int matchRound;
    private int homeTeamID;
    private int guestTeamID;
    private int isPlayed;
    private int homeGoals;
    private int guestGoals;

    /**
     *
     * @param id
     * @param matchRound
     * @param homeTeamID
     * @param guestTeamID
     * @param isPlayed
     * @param homeGoals
     * @param guestGoals
     */
    public Match(int id, int matchRound, int homeTeamID, int guestTeamID, int isPlayed, int homeGoals, int guestGoals)
    {
        this.id = id;
        this.matchRound = matchRound;
        this.homeTeamID = homeTeamID;
        this.guestTeamID = guestTeamID;
        this.isPlayed = isPlayed;
        this.homeGoals = homeGoals;
        this.guestGoals = guestGoals;
    }

    /**
     * @return the ID
     */
    public int getID()
    {
        return id;
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

    /**
     * @return the homeTeamID
     */
    public int getHomeTeamID()
    {
        return homeTeamID;
    }

    /**
     * @return the guestTeamID
     */
    public int getGuestTeamID()
    {
        return guestTeamID;
    }

    /**
     * @return the isPlayed
     */
    public int getIsPlayed()
    {
        return isPlayed;
    }

    /**
     * @param isPlayed the isPlayed to set
     */
    public void setIsPlayed(int isPlayed)
    {
        this.isPlayed = isPlayed;
    }

    /**
     * @return the homeGoals
     */
    public int getHomeGoals()
    {
        return homeGoals;
    }

    /**
     * @param homeGoals the homeGoals to set
     */
    public void setHomeGoals(int homeGoals)
    {
        this.homeGoals = homeGoals;
    }

    /**
     * @return the guestGoals
     */
    public int getGuestGoals()
    {
        return guestGoals;
    }

    /**
     * @param guestGoals the guestGoals to set
     */
    public void setGuestGoals(int guestGoals)
    {
        this.guestGoals = guestGoals;
    }

    @Override
    public String toString()
    {
        return String.format("%3s %-6d %-15d %-15d %-15d %-10d %-15d %-15d", "", id, matchRound, homeTeamID, guestTeamID, isPlayed, homeGoals, guestGoals);
    }
}
