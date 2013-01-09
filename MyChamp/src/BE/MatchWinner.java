/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Dennis
 */
public class MatchWinner
{
    private Team winner;
    
    public MatchWinner(Team winner)
    {
        this.winner = winner;
    }

    /**
     * @return the winner
     */
    public Team getWinner()
    {
        return winner;
    }

    /**
     * @param winner the winner to set
     */
    public void setWinner(Team winner)
    {
        this.winner = winner;
    }
    
    @Override
    public String toString()
    {
        return String.format("%3s %-300s", "", winner.getSchool() );
    }
}
