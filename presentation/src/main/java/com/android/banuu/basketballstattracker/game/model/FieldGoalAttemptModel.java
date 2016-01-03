package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.FieldGoalAttempt;
import com.android.banuu.basketballstattracker.R;

public final class FieldGoalAttemptModel extends StatModel<FieldGoalAttempt> {
  public FieldGoalAttemptModel(Context contextParam, FieldGoalAttempt statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.field_goal_attempt);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.FGA);
  }
}
