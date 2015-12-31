package com.android.banuu.banuubasketballdata.entities;

public class Score {
  private Team team;
  private Team secondTeam;
  private int points;
  private int secondTeamScore;

  public Score(Team teamA, Team teamB) {
    team = teamA;
    secondTeam = teamB;
    points = 0;
    secondTeamScore = 0;
  }

  public void increaseScore(Team team) {
    if(this.team == team) {
      points++;
    } else {
      secondTeamScore++;
    }
  }
}
