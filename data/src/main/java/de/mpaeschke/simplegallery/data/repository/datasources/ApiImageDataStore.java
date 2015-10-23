package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.rest.ImageRestSource;
import de.mpaeschke.simplegallery.data.rest.ImageRestSourceImpl;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ApiImageDataStore implements ImageDataStore {
    public final static int API_IMAGE_DATA_STORE_TYPE = 1;

    @Override
    public void getImageEntityList(final ImageListDataStoreCallback imageListDataStoreCallback) {
        ImageRestSourceImpl imageRestSourceImpl = new ImageRestSourceImpl();
        imageRestSourceImpl.get(new ImageRestSource.ImageRestSourceCallback() {
            @Override
            public void onImageEntityListLoaded(ArrayList<ImageEntity> imageEntity) {
                imageListDataStoreCallback.onImageListLoaded(imageEntity);
            }

            @Override
            public void onImageEntityListError(Exception exception) {
                imageListDataStoreCallback.onError(exception);
            }
        });
    }

    @Override
    public int getDataStoreType() {
        return API_IMAGE_DATA_STORE_TYPE;
    }
}
