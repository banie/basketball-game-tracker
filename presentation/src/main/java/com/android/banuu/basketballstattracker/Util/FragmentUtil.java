package com.android.banuu.basketballstattracker.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

public final class FragmentUtil {
  private FragmentUtil() {
  }

  public static void showFragment(FragmentManager fragmentManager, int layoutId, Fragment fragment,
      String tag) {
    final FragmentTransaction transaction = fragmentManager.beginTransaction();

    if (!fragment.isAdded()) {
      transaction.add(layoutId, fragment, tag);
    }
    transaction.show(fragment);
    transaction.commit();
  }
}
