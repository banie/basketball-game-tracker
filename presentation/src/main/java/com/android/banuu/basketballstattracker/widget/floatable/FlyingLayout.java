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

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.android.banuu.basketballstattracker.R;

public final class FlyingLayout extends FrameLayout {
  private WindowManager.LayoutParams params;
  private WindowManager windowManager;

  private float initialTouchX;
  private float initialTouchY;
  private int initialX;
  private int initialY;
  private AirTrafficControl airTrafficControl;
  private OnBubbleRemoveListener onBubbleRemoveListener;
  private OnBubbleClickListener onBubbleClickListener;
  private static final int TOUCH_TIME_THRESHOLD = 150;
  private long lastTouchDown;
  private MoveAnimator animator;
  private int width;
  private boolean shouldStickToWall = true;

  public FlyingLayout(Context context) {
    super(context);
    animator = new MoveAnimator();
    windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    initializeView();
  }

  public FlyingLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    animator = new MoveAnimator();
    windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    initializeView();
  }

  public FlyingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    animator = new MoveAnimator();
    windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    initializeView();
  }

  private void initializeView() {
    setClickable(true);
  }

  void setViewParams(WindowManager.LayoutParams params) {
    this.params = params;
  }

  WindowManager.LayoutParams getViewParams() {
    return this.params;
  }

  public void setAirTrafficControl(AirTrafficControl listener) {
    airTrafficControl = listener;
  }

  public void setOnBubbleRemoveListener(OnBubbleRemoveListener listener) {
    onBubbleRemoveListener = listener;
  }

  public void setOnBubbleClickListener(OnBubbleClickListener listener) {
    onBubbleClickListener = listener;
  }

  public void setShouldStickToWall(boolean shouldStick) {
    this.shouldStickToWall = shouldStick;
  }

  void notifyBubbleRemoved() {
    if (onBubbleRemoveListener != null) {
      onBubbleRemoveListener.onBubbleRemoved(this);
    }
  }

  @Override
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    playAnimation();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (event != null) {
      switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
          initialX = params.x;
          initialY = params.y;
          initialTouchX = event.getRawX();
          initialTouchY = event.getRawY();
          playAnimationClickDown();
          lastTouchDown = System.currentTimeMillis();
          updateSize();
          animator.stop();
          if (airTrafficControl != null) {
            airTrafficControl.onTouched(this, (int) event.getRawX(), (int) event.getRawX());
          }
          break;
        case MotionEvent.ACTION_MOVE:
          int x = initialX + (int) (event.getRawX() - initialTouchX);
          int y = initialY + (int) (event.getRawY() - initialTouchY);
          params.x = x;
          params.y = y;
          windowManager.updateViewLayout(this, params);
          if (airTrafficControl != null) {
            airTrafficControl.onFlyingPositionChanged(this, x, y);
          }
          break;
        case MotionEvent.ACTION_UP:
          goToWall();
          if (airTrafficControl != null) {
            airTrafficControl.onRelease(this);
            playAnimationClickUp();
          }
          if (System.currentTimeMillis() - lastTouchDown < TOUCH_TIME_THRESHOLD) {
            if (onBubbleClickListener != null) {
              onBubbleClickListener.onBubbleClick(this);
            }
          }
          break;
      }
    }
    return super.onTouchEvent(event);
  }

  private void playAnimation() {
    if (!isInEditMode()) {
      AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
          R.animator.bubble_shown_animator);
      animator.setTarget(this);
      animator.start();
    }
  }

  private void playAnimationClickDown() {
    if (!isInEditMode()) {
      AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
          R.animator.bubble_down_click_animator);
      animator.setTarget(this);
      animator.start();
    }
  }

  private void playAnimationClickUp() {
    if (!isInEditMode()) {
      AnimatorSet animator = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(),
          R.animator.bubble_up_click_animator);
      animator.setTarget(this);
      animator.start();
    }
  }

  private void updateSize() {
    DisplayMetrics metrics = new DisplayMetrics();
    windowManager.getDefaultDisplay().getMetrics(metrics);
    Display display = windowManager.getDefaultDisplay();
    Point size = new Point();
    display.getSize(size);
    width = (size.x - this.getWidth());
  }

  public interface OnBubbleRemoveListener {
    void onBubbleRemoved(FlyingLayout bubble);
  }

  public interface OnBubbleClickListener {
    void onBubbleClick(FlyingLayout bubble);
  }

  public void goToWall() {
    if (shouldStickToWall) {
      int middle = width / 2;
      float nearestXWall = params.x >= middle ? width : 0;
      animator.start(nearestXWall, params.y);
    }
  }

  private void move(float deltaX, float deltaY) {
    params.x += deltaX;
    params.y += deltaY;
    windowManager.updateViewLayout(this, params);
  }

  private class MoveAnimator implements Runnable {
    private Handler handler = new Handler(Looper.getMainLooper());
    private float destinationX;
    private float destinationY;
    private long startingTime;

    private void start(float x, float y) {
      this.destinationX = x;
      this.destinationY = y;
      startingTime = System.currentTimeMillis();
      handler.post(this);
    }

    @Override
    public void run() {
      if (getRootView() != null && getRootView().getParent() != null) {
        float progress = Math.min(1, (System.currentTimeMillis() - startingTime) / 400f);
        float deltaX = (destinationX - params.x) * progress;
        float deltaY = (destinationY - params.y) * progress;
        move(deltaX, deltaY);
        if (progress < 1) {
          handler.post(this);
        }
      }
    }

    private void stop() {
      handler.removeCallbacks(this);
    }
  }
}
