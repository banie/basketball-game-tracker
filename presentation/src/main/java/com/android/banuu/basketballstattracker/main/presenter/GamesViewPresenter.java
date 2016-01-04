package com.android.banuu.basketballstattracker.main.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.android.banuu.basketballstattracker.R;
import com.android.banuu.basketballstattracker.base.LoaderManagerProvider;
import com.android.banuu.basketballstattracker.base.Presenter;
import com.android.banuu.basketballstattracker.game.GameActivity;
import com.github.clans.fab.FloatingActionButton;

public class GamesViewPresenter implements Presenter {

  @Bind(R.id.recyclerview)
  RecyclerView recyclerView;
  @Bind(R.id.empty_placeholder)
  LinearLayout emptyPlaceholder;
  @Bind(R.id.main_fab)
  FloatingActionButton fab;

  private Context context;

  public GamesViewPresenter(Context contextParam, View rootView,
      LoaderManagerProvider loaderManagerProvider) {
    ButterKnife.bind(this, rootView);
    context = contextParam;
    recyclerView.setLayoutManager(new LinearLayoutManager(context));

    recyclerView.setVisibility(View.GONE);
    emptyPlaceholder.setVisibility(View.VISIBLE);

    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //    .setAction("Action", null)
        //    .show();
        context.startActivity(new Intent(context, GameActivity.class));
      }
    });
  }

  @Override
  public void resume() {

  }

  @Override
  public void pause() {

  }

  @Override
  public void destroy() {
    ButterKnife.unbind(this);
  }
}
