package de.mpaeschke.simplegallery.data.repository.datasources;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public interface ImageDataStore {
    Observable<ArrayList<ImageEntity>> getImageEntityList();
    Observable<Bitmap> getImage(ImageEntity imageEntity);
    Observable<Bitmap> getScaledImage(ImageEntity imageEntity, int height, int width);
    boolean imageExists(ImageEntity imageEntity);

    int getDataStoreType();
}
