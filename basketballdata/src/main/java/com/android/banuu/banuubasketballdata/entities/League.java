package com.android.banuu.banuubasketballdata.entities;

import java.util.ArrayList;
import java.util.List;

public final class League {
  private String name;
  private List<Team> teams;

  public League() {
    name = "";
    teams = new ArrayList<>();
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void addTeam(Team team) {
    teams.add(team);
  }

  public void removeTeam() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
