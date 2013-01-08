/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

/**
 *
 * @author Chris
 */
public class NewClass
{

    public ArrayList<Match> schedule(ArrayList<Team> teams)
    {
        ArrayList<Match> matches = new ArrayList();
        int i = 0;
        while (getGroup(i, teams) != null)
        {
            ArrayList<Team> group = getGroup(i, teams);
            ArrayList<Match> groupMatches = new ArrayList();
            if (group.size() == 4)
            {
                int t = 0;
                groupMatches.add(new Match(1, group.get(0), group.get(2)));
                groupMatches.add(new Match(1, group.get(1), group.get(3)));
                for (int j = 2; j <= 5; ++j)
                {
                    groupMatches.add(new Match(j, group.get(t % 4), group.get((t + 1) % 4)));
                    groupMatches.add(new Match(j, group.get((t + 2) % 4), group.get((t + 3) % 4)));
                    ++t;
                }
                groupMatches.add(new Match(6, group.get(2), group.get(0)));
                groupMatches.add(new Match(6, group.get(3), group.get(1)));
            }
            else
            {
                int t = 0;
                for (int j = 1; j <= 3; ++j)
                {
                    groupMatches.add(new Match(j, group.get(t % 3), group.get((t + 1) % 3)));
                    ++t;
                }
                for (int j = 4; j <= 6; ++j)
                {
                    groupMatches.add(new Match(j, group.get((t + 1) % 3), group.get(t % 3)));
                }
            }
            matches.addAll(groupMatches);
            ++i;
        }
        return null;
    }
}
