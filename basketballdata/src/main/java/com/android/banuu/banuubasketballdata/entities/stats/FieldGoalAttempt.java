package com.android.banuu.banuubasketballdata.entities.stats;

import com.android.banuu.banuubasketballdata.entities.Player;
import com.android.banuu.banuubasketballdata.entities.stats.base.Stat;
import com.android.banuu.banuubasketballdata.entities.stats.base.StatType;

public final class FieldGoalAttempt extends Stat {
  public FieldGoalAttempt(long creationTime, Player player) {
    super(creationTime, player);
  }

  @Override
  public StatType getType() {
    return StatType.FIELD_GOAL_ATTEMPT;
  }
}
