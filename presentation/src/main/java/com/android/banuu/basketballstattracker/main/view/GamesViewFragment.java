package com.android.banuu.basketballstattracker.main.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.banuu.basketballstattracker.R;
import com.android.banuu.basketballstattracker.base.LoaderManagerProvider;
import com.android.banuu.basketballstattracker.main.presenter.GamesViewPresenter;

public class GamesViewFragment extends Fragment implements LoaderManagerProvider {

  private Activity activity;
  private GamesViewPresenter gamesViewPresenter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.games_view, container, false);
    gamesViewPresenter = new GamesViewPresenter(activity, fragmentView, this);

    return fragmentView;
  }

  @Override
  public void onAttach(Activity activityParam) {
    super.onAttach(activityParam);
    this.activity = activityParam;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    gamesViewPresenter.destroy();
  }
}
