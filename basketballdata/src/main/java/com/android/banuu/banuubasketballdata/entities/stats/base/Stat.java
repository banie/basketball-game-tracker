package com.android.banuu.banuubasketballdata.entities.stats.base;

import android.util.Pair;
import com.android.banuu.banuubasketballdata.entities.Player;

public abstract class Stat extends Pair<Long, Player> {
  public Stat(long creationTime, Player player) {
    super(creationTime, player);
  }

  public final long getCreationTime() {
    return first;
  }

  public final Player getWhoDidIt() {
    return second;
  }

  public abstract StatType getType();
}
