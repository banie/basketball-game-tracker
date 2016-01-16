package com.android.banuu.basketballstattracker.widget.floatable;

public interface FloatingMotionListener {
  void onDraggablePositionChanged(FloatingLayout bubble, int x, int y);
  void onDraggableMotionUp(FloatingLayout bubble);
}
