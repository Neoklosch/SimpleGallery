package de.mpaeschke.simplegallery.presentation.view;

import android.graphics.Bitmap;

/**
 * Created by markuspaeschke on 29.10.15.
 */
public interface ImageDetailView extends LoadingMVPView {
    void showImage(Bitmap bitmap);
    int getWindowWidth();
    int getWindowHeight();
}
