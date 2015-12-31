package com.android.banuu.banuubasketballdata.entities;

public class Score {
  private Team firstTeam;
  private Team secondTeam;
  private int firstTeamScore;
  private int secondTeamScore;

  public Score(Team teamA, Team teamB) {
    firstTeam = teamA;
    secondTeam = teamB;
    firstTeamScore = 0;
    secondTeamScore = 0;
  }

  public void increaseScore(Team team) {
    if(firstTeam == team) {
      firstTeamScore++;
    } else {
      secondTeamScore++;
    }
  }
}
