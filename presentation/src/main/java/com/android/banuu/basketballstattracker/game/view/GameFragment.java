package com.android.banuu.basketballstattracker.game.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.android.banuu.basketballstattracker.R;
import com.android.banuu.basketballstattracker.widget.floatable.FloatablesPresenter;
import com.android.banuu.basketballstattracker.widget.floatable.FloatingLayout;
import com.github.clans.fab.FloatingActionButton;

public class GameFragment extends Fragment implements IOnFocusListenable {

  @Bind(R.id.fa)
  FloatingActionButton faButton;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_game, container, false);
    ButterKnife.bind(this, view);

    return view;
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    FloatingLayout floatingLayout =
        (FloatingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.floating_stat, null);
    FloatablesPresenter floatablesPresenter = new FloatablesPresenter(getActivity());
    int[] location = new int[2];
    faButton.getLocationOnScreen(location);
    floatablesPresenter.addFloatable(floatingLayout, location[0], location[1]);
  }
}
