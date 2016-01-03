package com.android.banuu.banuubasketballdata.entities;

import java.util.ArrayList;
import java.util.List;

public class Team {
  private int teamId;
  private String name;
  private List<Player> players;

  public Team() {
    name = "";
    players = new ArrayList<>();
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Player> getPlayers() {
    return players;
  }

  public void setPlayers(List<Player> players) {
    this.players = players;
  }
}
