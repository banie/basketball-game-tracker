package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.FieldThrowMade;
import com.android.banuu.basketballstattracker.R;

public final class FieldThrowMadeModel extends StatModel<FieldThrowMade>{
  public FieldThrowMadeModel(Context contextParam, FieldThrowMade statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.field_throw_made);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.FTM);
  }
}
