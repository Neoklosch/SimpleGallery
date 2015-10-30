package de.mpaeschke.simplegallery.data.cache;

import android.graphics.Bitmap;

import rx.Observable;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public interface ImageCache {
    Observable<Bitmap> get(String key);

    void put(String key, Bitmap image);
}
