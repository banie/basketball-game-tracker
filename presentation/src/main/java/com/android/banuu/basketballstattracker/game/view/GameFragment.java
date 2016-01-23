package com.android.banuu.basketballstattracker.game.view;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.android.banuu.basketballstattracker.R;
import com.android.banuu.basketballstattracker.widget.floatable.FlyingItemsPresenter;
import com.android.banuu.basketballstattracker.widget.floatable.FlyingLayout;
import java.util.ArrayList;

public class GameFragment extends Fragment implements IOnFocusListenable {

  @Bind(R.id.stat1)
  TextView stat1;
  @Bind(R.id.stat2)
  TextView stat2;
  @Bind(R.id.stat3)
  TextView stat3;
  @Bind(R.id.stat4)
  TextView stat4;
  @Bind(R.id.stat5)
  TextView stat5;
  @Bind(R.id.stat6)
  TextView stat6;
  @Bind(R.id.stat7)
  TextView stat7;
  @Bind(R.id.stat8)
  TextView stat8;

  @Bind(R.id.landing1)
  ViewGroup landing1;
  @Bind(R.id.landing2)
  ViewGroup landing2;
  @Bind(R.id.landing3)
  ViewGroup landing3;
  @Bind(R.id.landing4)
  ViewGroup landing4;
  @Bind(R.id.landing5)
  ViewGroup landing5;
  @Bind(R.id.landing6)
  ViewGroup landing6;
  @Bind(R.id.landing7)
  ViewGroup landing7;
  @Bind(R.id.landing8)
  ViewGroup landing8;

  private FlyingItemsPresenter flyingItemsPresenter;
  private ArrayList<Pair<View, FlyingLayout>> statFlierPairs;
  private ArrayList<ViewGroup> landingViews;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_game, container, false);
    ButterKnife.bind(this, view);
    flyingItemsPresenter = new FlyingItemsPresenter(getActivity());
    statFlierPairs = new ArrayList<>();
    landingViews = new ArrayList<>();

    populateFlyiers(statFlierPairs);
    bindFliers(statFlierPairs);
    populateLandings(landingViews);

    return view;
  }

  private void populateFlyiers(ArrayList<Pair<View, FlyingLayout>> statFlierPairsParam) {
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat1,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat2,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat3,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat4,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat5,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat6,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat7,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
    statFlierPairsParam.add(new Pair<View, FlyingLayout>(stat8,
        (FlyingLayout) LayoutInflater.from(getActivity()).inflate(R.layout.flying_stat, null)));
  }

  private void bindFliers(ArrayList<Pair<View, FlyingLayout>> statFlierPairsParam) {
    TextView stat1 = ButterKnife.findById(statFlierPairsParam.get(0).second, R.id.stat);
    TextView stat2 = ButterKnife.findById(statFlierPairsParam.get(1).second, R.id.stat);
    TextView stat3 = ButterKnife.findById(statFlierPairsParam.get(2).second, R.id.stat);
    TextView stat4 = ButterKnife.findById(statFlierPairsParam.get(3).second, R.id.stat);
    TextView stat5 = ButterKnife.findById(statFlierPairsParam.get(4).second, R.id.stat);
    TextView stat6 = ButterKnife.findById(statFlierPairsParam.get(5).second, R.id.stat);
    TextView stat7 = ButterKnife.findById(statFlierPairsParam.get(6).second, R.id.stat);
    TextView stat8 = ButterKnife.findById(statFlierPairsParam.get(7).second, R.id.stat);

    stat1.setText(R.string.FGA);
    stat2.setText(R.string.FGM);
    stat3.setText(R.string.OR);
    stat4.setText(R.string.DR);
    stat5.setText(R.string.A);
    stat6.setText(R.string.TO);
    stat7.setText(R.string.S);
    stat8.setText(R.string.F);
  }

  private void populateLandings(ArrayList<ViewGroup> landingViewParams) {
    landingViews.add(landing1);
    landingViews.add(landing2);
    landingViews.add(landing3);
    landingViews.add(landing4);
    landingViews.add(landing5);
    landingViews.add(landing6);
    landingViews.add(landing7);
    landingViews.add(landing8);
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    if(hasFocus) {
      int[] location = new int[2];
      int locationX;
      int locationY;

      for (Pair<View, FlyingLayout> statFlyier : statFlierPairs) {
        statFlyier.first.getLocationOnScreen(location);
        locationX = location[0];
        locationY = location[1] - (statFlyier.first.getHeight() / 2);
        flyingItemsPresenter.addFlyier(statFlyier.second, locationX, locationY);
      }

      for(ViewGroup landingView : landingViews) {
        landingView.getLocationOnScreen(location);
        locationX = location[0];
        locationY = location[1] - (landingView.getHeight() / 2);
        flyingItemsPresenter.addDestination(locationX, locationY);
      }
    } else {
      for (Pair<View, FlyingLayout> statFlyier : statFlierPairs) {
        flyingItemsPresenter.removeFlyier(statFlyier.second);
      }
    }
  }
}
