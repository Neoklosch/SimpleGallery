package de.mpaeschke.simplegallery.data.repository.datasources;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.cache.ImageCacheImpl;
import de.mpaeschke.simplegallery.data.cache.ImageListCacheImpl;
import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class CacheImageDataStore implements ImageDataStore {
    public final static int CACHE_IMAGE_DATA_STORE_TYPE = 0;

    @Override
    public Observable<ArrayList<ImageEntity>> getImageEntityList() {
        return new ImageListCacheImpl().get();
    }

    @Override
    public Observable<Bitmap> getImage(ImageEntity imageEntity) {
        return new ImageCacheImpl().get(imageEntity.getPath());
    }

    @Override
    public Observable<Bitmap> getScaledImage(ImageEntity imageEntity, int height, int width) {
        return new ImageCacheImpl().get(imageEntity.getPath());
    }

    @Override
    public boolean imageExists(ImageEntity imageEntity) {
        return new ImageCacheImpl().hasKey(imageEntity.getPath());
    }

    @Override
    public int getDataStoreType() {
        return CACHE_IMAGE_DATA_STORE_TYPE;
    }
}
