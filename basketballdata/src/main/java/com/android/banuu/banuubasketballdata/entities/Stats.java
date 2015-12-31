package com.android.banuu.banuubasketballdata.entities;

public class Stats {
  private int gamePlayeds;
  private int fieldGoalAttempts;
  private int fieldGoalMades;
  private int offensiveRebounds;
  private int defensiveRebounds;
  private int assists;
  private int steals;
  private int blocks;
  private int turnovers;
  private int fouls;

  public Stats() {
    gamePlayeds = 0;
    fieldGoalAttempts = 0;
    fieldGoalMades = 0;
    offensiveRebounds = 0;
    defensiveRebounds = 0;
    assists = 0;
    steals = 0;
    blocks = 0;
    turnovers = 0;
    fouls = 0;
  }

  public final int getGamePlayeds() {
    return gamePlayeds;
  }

  public final void setGamePlayeds(int gamePlayeds) {
    this.gamePlayeds = gamePlayeds;
  }

  public final int getFieldGoalAttempts() {
    return fieldGoalAttempts;
  }

  public final void setFieldGoalAttempts(int fieldGoalAttempts) {
    this.fieldGoalAttempts = fieldGoalAttempts;
  }

  public final int getFieldGoalMades() {
    return fieldGoalMades;
  }

  public void setFieldGoalMades(int fieldGoalMades) {
    this.fieldGoalMades = fieldGoalMades;
  }

  public final int getOffensiveRebounds() {
    return offensiveRebounds;
  }

  public final void setOffensiveRebounds(int offensiveRebounds) {
    this.offensiveRebounds = offensiveRebounds;
  }

  public final int getDefensiveRebounds() {
    return defensiveRebounds;
  }

  public final void setDefensiveRebounds(int defensiveRebounds) {
    this.defensiveRebounds = defensiveRebounds;
  }

  public final int getAssists() {
    return assists;
  }

  public final void setAssists(int assists) {
    this.assists = assists;
  }

  public final int getSteals() {
    return steals;
  }

  public final void setSteals(int steals) {
    this.steals = steals;
  }

  public final int getBlocks() {
    return blocks;
  }

  public final void setBlocks(int blocks) {
    this.blocks = blocks;
  }

  public final int getTurnovers() {
    return turnovers;
  }

  public final void setTurnovers(int turnovers) {
    this.turnovers = turnovers;
  }

  public final int getFouls() {
    return fouls;
  }

  public final void setFouls(int fouls) {
    this.fouls = fouls;
  }
}
