package com.android.banuu.basketballstattracker.widget.floatable;

public interface AirTrafficControl {
  void onTouched(FlyingLayout flier, int x, int y);

  void onFlyingPositionChanged(FlyingLayout flier, int x, int y);

  void onRelease(FlyingLayout flier);
}
