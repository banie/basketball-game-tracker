/*
 * Copyright Txus Ballesteros 2015 (@txusballesteros)
 *
 * This file is part of some open source application.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * Contact: Txus Ballesteros <txus.ballesteros@gmail.com>
 */
package com.android.banuu.basketballstattracker.widget.floatable;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.banuu.basketballstattracker.R;
import java.util.ArrayList;
import java.util.List;

public final class FlyingItemsPresenter implements AirTrafficControl {
  private Context context;
  private List<FlyingLayout> fliers;
  private List<LandingLayout> landings;
  private WindowManager windowManager;

  public FlyingItemsPresenter(Context contextParam) {
    context = contextParam;
    fliers = new ArrayList<>();
    landings = new ArrayList<>();
  }

  @Override
  public void onTouched(FlyingLayout flier, int x, int y) {
    for (LandingLayout landing : landings) {
      landing.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void onFlyingPositionChanged(FlyingLayout bubble, int x, int y) {
    for (LandingLayout landing : landings) {
      if (checkIfFlyierIsOnLanding(bubble, landing)) {
        landing.applyMagnetism();
        landing.vibrate();
        landFlyierDownRunway(bubble, landing);
        break;
      } else {
        landing.releaseMagnetism();
      }
    }
  }

  private void landFlyierDownRunway(FlyingLayout flier, LandingLayout landing) {
    View runway = landing.getRunway();
    int runwayCenterX = (landing.getOriginParams().x + (runway.getRight() / 2));
    int runwayCenterY = (landing.getOriginParams().y + (runway.getBottom() / 2));
    int x = (runwayCenterX - (flier.getMeasuredWidth() / 2));
    int y = (runwayCenterY - (flier.getMeasuredHeight() / 2));
    flier.getViewParams().x = x;
    flier.getViewParams().y = y;
    windowManager.updateViewLayout(flier, flier.getViewParams());
  }

  private boolean checkIfFlyierIsOnLanding(FlyingLayout flier) {
    boolean result = false;
    for (LandingLayout landing : landings) {
      if (checkIfFlyierIsOnLanding(flier, landing)) {
        result = true;
        break;
      }
    }
    return result;
  }

  private boolean checkIfFlyierIsOnLanding(FlyingLayout flier, LandingLayout landing) {
    boolean result = false;
    if (landing.getVisibility() == View.VISIBLE) {
      View runway = landing.getRunway();
      System.out.println("XXXX landing.getMeasuredHeight(): " + landing.getMeasuredHeight());
      System.out.println("XXXX landing.getMeasuredWidth(): " + landing.getMeasuredWidth());
      System.out.println("XXXX landing.getBottom(): " + landing.getBottom());
      System.out.println("XXXX landing.getRight(): " + landing.getRight());
      System.out.println("XXXX runway.getMeasuredHeight(): " + runway.getMeasuredHeight());
      System.out.println("XXXX runway.getMeasuredWidth(): " + runway.getMeasuredWidth());
      System.out.println("XXXX runway.getBottom(): " + runway.getBottom());
      System.out.println("XXXX runway.getRight(): " + runway.getRight());
      int runwayLeft = landing.getOriginParams().x;
      int runwayRight = runwayLeft + runway.getMeasuredWidth();
      int runwayTop = landing.getOriginParams().y;
      int runwayBottom = runwayTop + runway.getMeasuredHeight();
      int flierWidth = flier.getMeasuredWidth();
      int flierHeight = flier.getMeasuredHeight();
      int flierLeft = flier.getViewParams().x;
      int flierRight = flierLeft + flierWidth;
      int flierTop = flier.getViewParams().y;
      int flierBottom = flierTop + flierHeight;
      if (flierLeft >= runwayLeft && flierRight <= runwayRight) {
        if (flierTop >= runwayTop && flierBottom <= runwayBottom) {
          result = true;
        }
      }
    }
    return result;
  }

  public void onRelease(FlyingLayout flier) {
    if (checkIfFlyierIsOnLanding(flier)) {
      flier.goBackToOrigin();
      Toast toast = Toast.makeText(context, R.string.flyier_landed, Toast.LENGTH_LONG);
      toast.show();
    } else {
      flier.goBackToOrigin();
    }

    for (LandingLayout landing : landings) {
      landing.setVisibility(View.GONE);
    }
  }

  private void recycleBubble(final FlyingLayout flier) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        getWindowManager().removeView(flier);
        flier.notifyBubbleRemoved();
        fliers.remove(flier);
      }
    });
  }

  public void clearLandings() {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        for(LandingLayout landing : landings) {
          getWindowManager().removeView(landing);
        }
        landings.clear();
      }
    });
  }


  private WindowManager getWindowManager() {
    if (windowManager == null) {
      windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }
    return windowManager;
  }

  public void addFlyier(FlyingLayout flier, int x, int y) {
    WindowManager.LayoutParams layoutParams = buildLayoutParamsForBubble(x, y);
    flier.setViewParams(layoutParams);
    flier.setShouldStickToWall(false);
    flier.setAirTrafficControl(this);
    fliers.add(flier);
    addViewToWindow(flier, flier.getViewParams());
  }

  public void addDestination(int x, int y) {
    WindowManager.LayoutParams layoutParams = buildLayoutParamsForTrash(x, y);
    LandingLayout landing = new LandingLayout(context);
    landing.setOriginParams(layoutParams);
    landing.setVisibility(View.GONE);
    LayoutInflater.from(context).inflate(R.layout.bubble_trash_layout, landing, true);
    addViewToWindow(landing, layoutParams);
    landings.add(landing);
  }

  private void addViewToWindow(final View view, final WindowManager.LayoutParams params) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        getWindowManager().addView(view, params);
      }
    });
  }

  public static WindowManager.LayoutParams buildLayoutParamsForBubble(int x, int y) {
    WindowManager.LayoutParams params =
        new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
    params.gravity = Gravity.TOP | Gravity.START;
    params.x = x;
    params.y = y;
    return params;
  }

  private WindowManager.LayoutParams buildLayoutParamsForTrash(int x, int y) {
    WindowManager.LayoutParams params =
        new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
    params.gravity = Gravity.TOP | Gravity.START;
    params.x = x;
    params.y = y;
    return params;
  }

  public void removeFlyier(FlyingLayout flier) {
    recycleBubble(flier);
  }
}
