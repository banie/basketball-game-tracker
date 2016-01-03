package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.Foul;
import com.android.banuu.basketballstattracker.R;

public final class FoulModel extends StatModel<Foul> {

  public FoulModel(Context contextParam, Foul statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.foul);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.F);
  }
}
