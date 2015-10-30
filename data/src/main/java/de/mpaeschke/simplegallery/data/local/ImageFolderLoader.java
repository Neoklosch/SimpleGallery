package de.mpaeschke.simplegallery.data.local;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;

public interface ImageFolderLoader {
    Observable<ArrayList<ImageEntity>> getImageList();
    Observable<Bitmap> getFullSizeImage(ImageEntity imageEntity);
    Observable<Bitmap> getScaledImage(ImageEntity imageEntity, int height, int width);

    void putImageListEntry(ImageEntity imageEntity);
    void putImage(String key, Bitmap bitmap);

    boolean imageExists(ImageEntity imageEntity);
}
