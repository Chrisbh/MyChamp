/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Dennis
 */
public class Counter
{
    private int numberOfTeams;
    
    public Counter (int numberOfTeams)
    {
        this.numberOfTeams = numberOfTeams;
    }

    /**
     * @return the numberOfTeams
     */
    public int getNumberOfTeams()
    {
        return numberOfTeams;
    }

    /**
     * @param numberOfTeams the numberOfTeams to set
     */
    public void setNumberOfTeams(int numberOfTeams)
    {
        this.numberOfTeams = numberOfTeams;
    }
    
    @Override
    public String toString()
    {
        return String.format("%3d", numberOfTeams);
    }
}
