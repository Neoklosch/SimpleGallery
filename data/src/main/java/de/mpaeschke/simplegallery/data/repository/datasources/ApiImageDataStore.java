package de.mpaeschke.simplegallery.data.repository.datasources;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.rest.ImageRestSourceImpl;
import rx.Observable;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ApiImageDataStore implements ImageDataStore {
    public final static int API_IMAGE_DATA_STORE_TYPE = 1;

    @Override
    public Observable<ArrayList<ImageEntity>> getImageEntityList() {
        ImageRestSourceImpl imageRestSourceImpl = new ImageRestSourceImpl();
        return imageRestSourceImpl.get();
    }

    @Override
    public Observable<Bitmap> getImage(ImageEntity imageEntity) {
        return null;
    }

    @Override
    public Observable<Bitmap> getScaledImage(ImageEntity imageEntity, int height, int width) {
        return null;
    }

    @Override
    public boolean imageExists(ImageEntity imageEntity) {
        return false;
    }

    @Override
    public int getDataStoreType() {
        return API_IMAGE_DATA_STORE_TYPE;
    }
}
