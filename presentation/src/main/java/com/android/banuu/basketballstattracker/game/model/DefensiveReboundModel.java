package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.DefensiveRebound;
import com.android.banuu.basketballstattracker.R;

public final class DefensiveReboundModel extends StatModel<DefensiveRebound> {

  public DefensiveReboundModel(Context contextParam, DefensiveRebound statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.defensive_rebound);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.DR);
  }
}
