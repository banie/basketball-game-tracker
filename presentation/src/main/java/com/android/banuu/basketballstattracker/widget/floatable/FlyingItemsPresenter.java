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
    // nothing so far
  }

  @Override
  public void onFlyingPositionChanged(FlyingLayout bubble, int x, int y) {
    for (LandingLayout landing : landings) {
      landing.setVisibility(View.VISIBLE);
      if (checkIfFlyierIsOnLanding(bubble)) {
        landing.applyMagnetism();
        landing.vibrate();
        landFlyierDownRunway(bubble, landing);
      } else {
        landing.releaseMagnetism();
      }
    }
  }

  private void landFlyierDownRunway(FlyingLayout flier, LandingLayout landing) {
    View runway = landing.getRunway();
    int trashCenterX = (runway.getLeft() + (runway.getMeasuredWidth() / 2));
    int trashCenterY = (runway.getTop() + (runway.getMeasuredHeight() / 2));
    int x = (trashCenterX - (flier.getMeasuredWidth() / 2));
    int y = (trashCenterY - (flier.getMeasuredHeight() / 2));
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
      int trashWidth = runway.getMeasuredWidth();
      int trashHeight = runway.getMeasuredHeight();
      int trashLeft = (runway.getLeft() - (trashWidth / 2));
      int trashRight = (runway.getLeft() + trashWidth + (trashWidth / 2));
      int trashTop = (runway.getTop() - (trashHeight / 2));
      int trashBottom = (runway.getTop() + trashHeight + (trashHeight / 2));
      int bubbleWidth = flier.getMeasuredWidth();
      int bubbleHeight = flier.getMeasuredHeight();
      int bubbleLeft = flier.getViewParams().x;
      int bubbleRight = bubbleLeft + bubbleWidth;
      int bubbleTop = flier.getViewParams().y;
      int bubbleBottom = bubbleTop + bubbleHeight;
      if (bubbleLeft >= trashLeft && bubbleRight <= trashRight) {
        if (bubbleTop >= trashTop && bubbleBottom <= trashBottom) {
          result = true;
        }
      }
    }
    return result;
  }

  public void onRelease(FlyingLayout flier) {
    if (checkIfFlyierIsOnLanding(flier)) {
      removeFlyier(flier);
    } else {
      flier.goBackToOrigin();
    }

    for (LandingLayout landing : landings) {
      landing.setVisibility(View.GONE);
    }
  }

  private void recycleBubble(final FlyingLayout bubble) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        getWindowManager().removeView(bubble);
        for (FlyingLayout cachedBubble : fliers) {
          if (cachedBubble == bubble) {
            bubble.notifyBubbleRemoved();
            fliers.remove(cachedBubble);
            break;
          }
        }
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
    LandingLayout landing = new LandingLayout(context);
    landing.setVisibility(View.GONE);
    LayoutInflater.from(context).inflate(R.layout.bubble_trash_layout, landing, true);
    addViewToWindow(landing, buildLayoutParamsForTrash(x, y));
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
