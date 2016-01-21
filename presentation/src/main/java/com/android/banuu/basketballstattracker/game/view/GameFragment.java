package com.android.banuu.basketballstattracker.game.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.android.banuu.basketballstattracker.R;
import com.android.banuu.basketballstattracker.widget.floatable.FlyingItemsPresenter;
import com.android.banuu.basketballstattracker.widget.floatable.FlyingLayout;

public class GameFragment extends Fragment implements IOnFocusListenable {

  @Bind(R.id.fga_stat)
  TextView fmStat;

  private FlyingItemsPresenter flyingItemsPresenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_game, container, false);
    ButterKnife.bind(this, view);
    flyingItemsPresenter = new FlyingItemsPresenter(getActivity());
    return view;
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    FlyingLayout flyingLayout =
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null);

    int[] location = new int[2];
    fmStat.getLocationOnScreen(location);
    int locationX = location[0];
    int locationY = location[1] - (fmStat.getHeight() / 2);
    flyingItemsPresenter.addFlyier(flyingLayout, locationX, locationY);
  }
}
