package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.base.Stat;

public abstract class StatModel<T extends Stat> {

  private Context context;
  private T stat;

  public StatModel(Context contextParam, T statParam) {
    context = contextParam;
    stat = statParam;
  }

  protected Context getContext() {
    return context;
  }

  protected T getStat() {
    return stat;
  }

  public abstract String getLabel();

  public abstract String getShortLabel();
}
