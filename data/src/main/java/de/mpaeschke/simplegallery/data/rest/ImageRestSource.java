package de.mpaeschke.simplegallery.data.rest;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by markuspaeschke on 23.10.15.
 */
public interface ImageRestSource {
    Observable<ArrayList<ImageEntity>> get();

    void put(ImageEntity imageEntity);
}
