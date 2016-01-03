package com.android.banuu.basketballstattracker.game.model;

import android.content.Context;
import com.android.banuu.banuubasketballdata.entities.stats.Block;
import com.android.banuu.basketballstattracker.R;

public final class BlockModel extends StatModel<Block> {

  public BlockModel(Context contextParam, Block statParam) {
    super(contextParam, statParam);
  }

  @Override
  public String getLabel() {
    return getContext().getString(R.string.block);
  }

  @Override
  public String getShortLabel() {
    return getContext().getString(R.string.B);
  }
}
