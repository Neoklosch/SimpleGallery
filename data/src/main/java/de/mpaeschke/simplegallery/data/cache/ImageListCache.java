package de.mpaeschke.simplegallery.data.cache;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;

/**
 * Created by markuspaeschke on 29.10.15.
 */
public interface ImageListCache {
    Observable<ArrayList<ImageEntity>> get();

    void put(ImageEntity imageEntity);
}
