package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.Steal;
import com.android.banuu.basketballstattracker.R;

public final class StealModel extends StatModel<Steal> {

  public StealModel(Context contextParam, Steal statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.steal);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.S);
  }
}
