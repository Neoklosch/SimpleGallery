package de.mpaeschke.simplegallery.presentation.utils;

import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by markuspaeschke on 30.10.15.
 */
public class ScreenUtils {
    public static int getScreenWidth(final WindowManager windowManager) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }

    public static int getScreenHeight(final WindowManager windowManager) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }
}
