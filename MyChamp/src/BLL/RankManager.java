package BLL;

import BE.Match;
import BE.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Rasmus, Chris, Lasse, Dennis
 */
public class RankManager {

    private static final int WIN_POINTS = 3, LOSS_POINTS = 0, DRAW_POINTS = 1;

    public static Team[] rankTeams( List<Team> teams, List<Match> matches ){
       
        Team[] ranked = new Team[teams.size()];
       
        boolean done = false;
        int index = 0;
        while( !done ){
            for( Team t : teams ){
                if( ranked[index] == null )
                {
                    ranked[index] = t;
                }
                else
                {
                    ranked[index] = compareTeams( ranked[index], t, matches );
                }
            }

        }
        return ranked;
    }

    
    public static int calculatePoints( Team team, List<Match> matches ){
        int points = 0;
        points += WIN_POINTS * countWins( team, matches );
        points += LOSS_POINTS * countLosses( team, matches );
        points += DRAW_POINTS * countDraws( team, matches );
        return points;
    }

    
    public static int countWins( Team team, List<Match> matches ){
        int wins = 0;
        for( Match m : matches ){
            if( team.getId() == m.getHomeTeamId() && m.getHomeGoals() > m.getGuestGoals() )
            {
                wins++ ;
            }
            else if( team.getId() == m.getGuestTeamId() && m.getGuestGoals() > m.getHomeGoals() )
            {
                wins++ ;
            }
        }
        return wins;
    }

   
    public static int countLosses( Team team, List<Match> matches ){
        int losses = 0;
        for( Match m : matches ){
            if( team.getId() == m.getHomeTeamId() && m.getHomeGoals() < m.getGuestGoals() )
            {
                losses++ ;
            }
            else if( team.getId() == m.getGuestTeamId() && m.getGuestGoals() < m.getHomeGoals() )
            {
                losses++ ;
            }
        }
        return losses;
    }

    
    public static int countDraws( Team team, List<Match> matches ){
        int draws = 0;
        for( Match m : matches ){
            boolean playedMatch = (team.getId() == m.getHomeTeamId() || team.getId() == m.getGuestTeamId());
            if( playedMatch && m.getHomeGoals() == m.getGuestGoals() )
            {
                draws++ ;
            }
        }
        return draws;
    }

   
    public static int countGoalsScored( Team team, Match match ){
        if( !(team.getId() == match.getHomeTeamId() || team.getId() == match.getGuestTeamId()) )
        {
            return 0;
        }
        boolean homeTeam = team.getId() == match.getHomeTeamId();
        return homeTeam ? match.getHomeGoals() : match.getGuestGoals();
    }

    
    public static int countGoalsAgainst( Team team, Match match ){
        if( !(team.getId() == match.getHomeTeamId() || team.getId() == match.getGuestTeamId()) )
        {
            return 0;
        }
        boolean homeTeam = team.getId() == match.getHomeTeamId();
        return homeTeam ? match.getGuestGoals() : match.getHomeGoals();
    }

  
    public static int countTotalGoalsScored( Team team, List<Match> matches ){
        int goals = 0;
        for( Match m : matches )
        {
            goals += countGoalsScored( team, m );
        }
        return goals;
    }

    public static int countTotalGoalsAgainst( Team team, List<Match> matches ){
        int goals = 0;
        for( Match m : matches )
        {
            goals += countGoalsAgainst( team, m );
        }
        return goals;
    }

    public static int[] mutualMatchResults( Team t1, Team t2, List<Match> matches ){
        
        int[] wld = new int[3]; 
        ArrayList<Match> commonMatches = new ArrayList<>();
        for( Match m : matches ){
            if( t1.getId() == m.getHomeTeamId() && t2.getId() == m.getGuestTeamId() )
            {
                commonMatches.add( m );
            }
            else if( t2.getId() == m.getHomeTeamId() && t1.getId() == m.getGuestTeamId() )
            {
                commonMatches.add( m );
            }
        }
        
        wld[0] = countWins( t1, commonMatches );
        wld[1] = countLosses( t1, commonMatches );
        wld[2] = countDraws( t1, commonMatches );
        
        return wld;
    }

    
    public static Team compareTeams(Team t1, Team t2, List<Match> matches){
        
        int team1Pts = calculatePoints( t1, matches );
        int team2Pts = calculatePoints( t2, matches );
        if( team1Pts != team2Pts )
        {
            return team1Pts > team2Pts ? t1 : t2;
        }

        
        int team1Goals = countTotalGoalsScored( t1, matches );
        int team2Goals = countTotalGoalsScored( t2, matches );
        int team1GoalDiff = team1Goals - countTotalGoalsAgainst( t1, matches );
        int team2GoalDiff = team2Goals - countTotalGoalsAgainst( t2, matches );
        if( team1GoalDiff != team2GoalDiff )
        {
            return team1GoalDiff > team2GoalDiff ? t1 : t2;
        }

        
        if( team1Goals != team2Goals )
        {
            return team1Goals > team2Goals ? t1 : t2;
        }

        
        int[] mmr = mutualMatchResults( t1, t2, matches );
        if( mmr[0] != mmr[1] )
        {
            return mmr[0] > mmr[1] ? t1 : t2;
        }

        
        return new Random( 2335 ).nextInt( 2 ) > 0 ? t1 : t2;
    }
}

