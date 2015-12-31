package com.android.banuu.banuubasketballdata.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bsetijoso on 15-12-26.
 */
public class Team {
  private int teamId;
  private String name;
  private List<Player> players;

  public Team() {
    name = "";
    players = new ArrayList<>();
  }

  public final int getTeamId() {
    return teamId;
  }

  public final void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public final String getName() {
    return name;
  }

  public final void setName(String name) {
    this.name = name;
  }

  public final List<Player> getPlayers() {
    return players;
  }

  public final void setPlayers(List<Player> players) {
    this.players = players;
  }
}
