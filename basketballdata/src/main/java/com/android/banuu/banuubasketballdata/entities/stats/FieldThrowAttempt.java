package com.android.banuu.banuubasketballdata.entities.stats;

import com.android.banuu.banuubasketballdata.entities.Player;
import com.android.banuu.banuubasketballdata.entities.stats.base.Stat;
import com.android.banuu.banuubasketballdata.entities.stats.base.StatType;

public final class FieldThrowAttempt extends Stat {
  public FieldThrowAttempt(long creationTime, Player player) {
    super(creationTime, player);
  }

  @Override
  public StatType getType() {
    return StatType.FIELD_THROW_ATTEMPT;
  }
}
