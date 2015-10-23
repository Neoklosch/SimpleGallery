package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.cache.ImageCache;
import de.mpaeschke.simplegallery.data.cache.ImageCacheImpl;
import de.mpaeschke.simplegallery.data.entity.ImageEntity;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class CacheImageDataStore implements ImageDataStore {
    public final static int CACHE_IMAGE_DATA_STORE_TYPE = 0;

    @Override
    public void getImageEntityList(final ImageListDataStoreCallback imageListDataStoreCallback) {
        ImageCacheImpl imageCache = new ImageCacheImpl();
        imageCache.get(new ImageCache.ImageCacheCallback() {
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
        return CACHE_IMAGE_DATA_STORE_TYPE;
    }
}
