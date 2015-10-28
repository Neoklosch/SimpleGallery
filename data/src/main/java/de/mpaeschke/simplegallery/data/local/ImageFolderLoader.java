package de.mpaeschke.simplegallery.data.local;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;

public interface ImageFolderLoader {
    Observable<ArrayList<ImageEntity>> get();

    void put(ImageEntity imageEntity);
}
