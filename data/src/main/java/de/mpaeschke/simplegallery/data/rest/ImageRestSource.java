package de.mpaeschke.simplegallery.data.rest;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;

/**
 * Created by markuspaeschke on 23.10.15.
 */
public interface ImageRestSource {
    interface ImageRestSourceCallback {
        void onImageEntityListLoaded(ArrayList<ImageEntity> imageEntity);
        void onImageEntityListError(Exception exception);
    }

    void get(final ImageRestSourceCallback imageCacheCallback);

    void put(ImageEntity imageEntity);
}
