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
    private int homeTeamId;
    private int guestTeamId;
    private int isPlayed;
    private int homeGoals;
    private int guestGoals;

    /**
     *
     * @param id
     * @param matchRound
     * @param homeTeamId
     * @param guestTeamId
     * @param isPlayed
     * @param homeGoals
     * @param guestGoals
     */
    public Match(int id, int matchRound, int homeTeamId, int guestTeamId, int isPlayed, int homeGoals, int guestGoals)
    {
        this.id = id;
        this.matchRound = matchRound;
        this.homeTeamId = homeTeamId;
        this.guestTeamId = guestTeamId;
        this.isPlayed = isPlayed;
        this.homeGoals = homeGoals;
        this.guestGoals = guestGoals;
    }

    /**
     * @return the ID
     */
    public int getId()
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
     * @return the homeTeamId
     */
    public int getHomeTeamId()
    {
        return homeTeamId;
    }

    /**
     * @return the guestTeamId
     */
    public int getGuestTeamId()
    {
        return guestTeamId;
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
        return String.format("%3s %-6d %-15d %-15d %-15d %-10d %-15d %-15d", "", id, matchRound, homeTeamId, guestTeamId, isPlayed, homeGoals, guestGoals);
    }
}
