package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.cache.ImageCache;
import de.mpaeschke.simplegallery.data.cache.ImageCacheImpl;
import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class CacheImageDataStore implements ImageDataStore {
    public final static int CACHE_IMAGE_DATA_STORE_TYPE = 0;

    @Override
    public Observable<ArrayList<ImageEntity>> getImageEntityList() {
        ImageCacheImpl imageCache = new ImageCacheImpl();
        return imageCache.get();
    }

    @Override
    public int getDataStoreType() {
        return CACHE_IMAGE_DATA_STORE_TYPE;
    }
}
