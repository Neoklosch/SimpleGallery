package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public interface ImageDataStore {
    interface ImageListDataStoreCallback {
        void onImageListLoaded(ArrayList<ImageEntity> imageList);
        void onError(Exception exception);
    }

    void getImageEntityList(ImageListDataStoreCallback imageListDataStoreCallback);

    int getDataStoreType();
}
