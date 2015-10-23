package de.mpaeschke.simplegallery.data.cache;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public interface ImageCache {
    interface ImageCacheCallback {
        void onImageEntityListLoaded(ArrayList<ImageEntity> imageEntity);
        void onImageEntityListError(Exception exception);
    }

    void get(final ImageCacheCallback imageCacheCallback);

    void put(ImageEntity imageEntity);
}
