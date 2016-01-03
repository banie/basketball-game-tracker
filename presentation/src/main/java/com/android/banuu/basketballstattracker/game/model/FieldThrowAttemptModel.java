package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.FieldThrowAttempt;
import com.android.banuu.basketballstattracker.R;

public final class FieldThrowAttemptModel extends StatModel<FieldThrowAttempt> {

  public FieldThrowAttemptModel(Context contextParam, FieldThrowAttempt statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.field_throw_attempt);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.FTA);
  }
}
