package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.OffensiveRebound;
import com.android.banuu.basketballstattracker.R;

public final class OffensiveReboundModel extends StatModel<OffensiveRebound> {

  public OffensiveReboundModel(Context contextParam, OffensiveRebound statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.offensive_rebound);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.OR);
  }
}
