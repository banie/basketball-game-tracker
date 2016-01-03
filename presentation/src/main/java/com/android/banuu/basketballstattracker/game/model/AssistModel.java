package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.Assist;
import com.android.banuu.basketballstattracker.R;

public final class AssistModel extends StatModel<Assist> {

  public AssistModel(Context contextParam, Assist statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.assist);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.A);
  }
}
