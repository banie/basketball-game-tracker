package com.android.banuu.banuubasketballdata.entities.stats.base;

public enum StatType {

  ASSIST(StatTypeValue.ASSIST_TYPE_VALUE),
  BLOCK(StatTypeValue.BLOCK_TYPE_VALUE),
  DEFENSIVE_REBOUND(StatTypeValue.DEFENSIVE_REBOUND_TYPE_VALUE),
  FIELD_GOAL_ATTEMPT(StatTypeValue.FIELD_GOAL_ATTEMPT_TYPE_VALUE),
  FIELD_GOAL_MADE(StatTypeValue.FIELD_GOAL_MADE_TYPE_VALUE),
  FIELD_THROW_ATTEMPT(StatTypeValue.FIELD_THROW_ATTEMPT_TYPE_VALUE),
  FIELD_THROW_MADE(StatTypeValue.FIELD_THROW_MADE_TYPE_VALUE),
  FOUL(StatTypeValue.FOUL_TYPE_VALUE),
  OFFENSIVE_REBOUND(StatTypeValue.OFFENSIVE_REBOUND_TYPE_VALUE),
  STEAL(StatTypeValue.STEAL_TYPE_VALUE),
  TURNOVER(StatTypeValue.TURNOVER_TYPE_VALUE);

  private int typeValue;

  StatType(int typeValueParam) {
    typeValue = typeValueParam;
  }

  public int getStatTypeValue() {
    return typeValue;
  }
}
