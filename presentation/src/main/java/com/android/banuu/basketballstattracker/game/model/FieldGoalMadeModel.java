package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.FieldGoalMade;
import com.android.banuu.basketballstattracker.R;

public final class FieldGoalMadeModel extends StatModel<FieldGoalMade> {
  public FieldGoalMadeModel(Context contextParam, FieldGoalMade statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.field_goal_made);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.FGM);
  }
}
