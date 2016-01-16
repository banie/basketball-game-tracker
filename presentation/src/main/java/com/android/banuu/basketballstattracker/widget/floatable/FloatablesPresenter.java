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
import java.util.ArrayList;
import java.util.List;

public final class FloatablesPresenter implements FloatingMotionListener {
  private Context context;
  private LandingLayout trashView;
  private List<FloatingLayout> bubbles = new ArrayList<>();
  private LandingLayout bubblesTrash;
  private WindowManager windowManager;

  public FloatablesPresenter(Context contextParam) {
    context = contextParam;
  }

  public void onDraggablePositionChanged(FloatingLayout bubble, int x, int y) {
    if (trashView != null) {
      trashView.setVisibility(View.VISIBLE);
      if (checkIfBubbleIsOverTrash(bubble)) {
        trashView.applyMagnetism();
        trashView.vibrate();
        applyTrashMagnetismToBubble(bubble);
      } else {
        trashView.releaseMagnetism();
      }
    }
  }

  private void applyTrashMagnetismToBubble(FloatingLayout bubble) {
    View trashContentView = getTrashContent();
    int trashCenterX = (trashContentView.getLeft() + (trashContentView.getMeasuredWidth() / 2));
    int trashCenterY = (trashContentView.getTop() + (trashContentView.getMeasuredHeight() / 2));
    int x = (trashCenterX - (bubble.getMeasuredWidth() / 2));
    int y = (trashCenterY - (bubble.getMeasuredHeight() / 2));
    bubble.getViewParams().x = x;
    bubble.getViewParams().y = y;
    windowManager.updateViewLayout(bubble, bubble.getViewParams());
  }

  private boolean checkIfBubbleIsOverTrash(FloatingLayout bubble) {
    boolean result = false;
    if (trashView.getVisibility() == View.VISIBLE) {
      View trashContentView = getTrashContent();
      int trashWidth = trashContentView.getMeasuredWidth();
      int trashHeight = trashContentView.getMeasuredHeight();
      int trashLeft = (trashContentView.getLeft() - (trashWidth / 2));
      int trashRight = (trashContentView.getLeft() + trashWidth + (trashWidth / 2));
      int trashTop = (trashContentView.getTop() - (trashHeight / 2));
      int trashBottom = (trashContentView.getTop() + trashHeight + (trashHeight / 2));
      int bubbleWidth = bubble.getMeasuredWidth();
      int bubbleHeight = bubble.getMeasuredHeight();
      int bubbleLeft = bubble.getViewParams().x;
      int bubbleRight = bubbleLeft + bubbleWidth;
      int bubbleTop = bubble.getViewParams().y;
      int bubbleBottom = bubbleTop + bubbleHeight;
      if (bubbleLeft >= trashLeft && bubbleRight <= trashRight) {
        if (bubbleTop >= trashTop && bubbleBottom <= trashBottom) {
          result = true;
        }
      }
    }
    return result;
  }

  public void onDraggableMotionUp(FloatingLayout bubble) {
    if (trashView != null) {
      if (checkIfBubbleIsOverTrash(bubble)) {
        removeBubble(bubble);
      }
      trashView.setVisibility(View.GONE);
    }
  }

  public void setTrashView(LandingLayout trashViewParam) {
    trashView = trashViewParam;
  }

  private View getTrashContent() {
    return trashView.getChildAt(0);
  }

  private void recycleBubble(final FloatingLayout bubble) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        getWindowManager().removeView(bubble);
        for (FloatingLayout cachedBubble : bubbles) {
          if (cachedBubble == bubble) {
            bubble.notifyBubbleRemoved();
            bubbles.remove(cachedBubble);
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

  public void addFloatable(FloatingLayout bubble, int x, int y) {
    WindowManager.LayoutParams layoutParams = buildLayoutParamsForBubble(x, y);
    bubble.setViewParams(layoutParams);
    bubble.setFloatingMotionListener(this);
    bubbles.add(bubble);
    addViewToWindow(bubble, bubble.getViewParams());
  }

  void setLanding(int trashLayoutResourceId) {
    if (trashLayoutResourceId != 0) {
      bubblesTrash = new LandingLayout(context);
      bubblesTrash.setVisibility(View.GONE);
      LayoutInflater.from(context).inflate(trashLayoutResourceId, bubblesTrash, true);
      addViewToWindow(bubblesTrash, buildLayoutParamsForTrash());
    }
  }

  private void addViewToWindow(final View view, final WindowManager.LayoutParams params) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
      @Override
      public void run() {
        getWindowManager().addView(view, params);
      }
    });
  }

  private WindowManager.LayoutParams buildLayoutParamsForBubble(int x, int y) {
    WindowManager.LayoutParams params =
        new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
    params.gravity = Gravity.TOP | Gravity.START;
    params.x = x;
    params.y = y;
    return params;
  }

  private WindowManager.LayoutParams buildLayoutParamsForTrash() {
    int x = 0;
    int y = 0;
    WindowManager.LayoutParams params =
        new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);
    params.x = x;
    params.y = y;
    return params;
  }

  public void removeBubble(FloatingLayout bubble) {
    recycleBubble(bubble);
  }
}
