package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.Turnover;
import com.android.banuu.basketballstattracker.R;

public final class TurnoverModel extends StatModel<Turnover> {

  public TurnoverModel(Context contextParam, Turnover statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.turnover);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.TO);
  }
}
