package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public interface ImageDataStore {
    Observable<ArrayList<ImageEntity>> getImageEntityList();

    int getDataStoreType();
}
