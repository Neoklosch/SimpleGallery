package de.mpaeschke.simplegallery.data.cache;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public interface ImageCache {
    Observable<ArrayList<ImageEntity>> get();

    void put(ImageEntity imageEntity);
}
