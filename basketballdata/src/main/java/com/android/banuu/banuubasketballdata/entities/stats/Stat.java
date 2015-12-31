package com.android.banuu.banuubasketballdata.entities.stats;

import android.util.Pair;
import com.android.banuu.banuubasketballdata.entities.Player;

public class Stat extends Pair<Long, Player> {
  public Stat(long creationTime, Player player) {
    super(creationTime, player);
  }

  public final long getCreationTime() {
    return first;
  }

  public final Player getWhoDidIt() {
    return second;
  }
}
