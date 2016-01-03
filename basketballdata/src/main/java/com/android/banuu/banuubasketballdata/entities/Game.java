package com.android.banuu.banuubasketballdata.entities;

import android.util.Pair;
import com.android.banuu.banuubasketballdata.entities.stats.base.Stat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Game {
  private int duration;
  private long timeStarted;
  private Map<Team, ArrayList<Stat>> teamStats;

  public Game(Team teamA, Team teamB) {
    teamStats = new HashMap<>();
    teamStats.put(teamA, new ArrayList<Stat>());
    teamStats.put(teamB, new ArrayList<Stat>());
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public void setTimeStarted(long timeStarted) {
    this.timeStarted = timeStarted;
  }

  public Pair<Pair<Team, Integer>, Pair<Team, Integer>> getScore() {
    Team[] teams = new Team[2];
    teamStats.keySet().toArray(teams);

    return new Pair<>(new Pair<>(teams[0], 0), new Pair<>(teams[1], 0));
  }

  public ArrayList<Stat> getStats(Team team) {
    return teamStats.get(team);
  }
}
