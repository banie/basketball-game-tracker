package com.android.banuu.banuubasketballdata.entities.stats;

import com.android.banuu.banuubasketballdata.entities.Player;
import com.android.banuu.banuubasketballdata.entities.stats.base.Stat;
import com.android.banuu.banuubasketballdata.entities.stats.base.StatType;

public class Assist extends Stat {
  public Assist(long creationTime, Player player) {
    super(creationTime, player);
  }

  @Override
  public StatType getType() {
    return StatType.ASSIST;
  }
}
