package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.rest.ImageRestSource;
import de.mpaeschke.simplegallery.data.rest.ImageRestSourceImpl;
import rx.Observable;
import rx.Subscriber;

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
    public int getDataStoreType() {
        return API_IMAGE_DATA_STORE_TYPE;
    }
}
