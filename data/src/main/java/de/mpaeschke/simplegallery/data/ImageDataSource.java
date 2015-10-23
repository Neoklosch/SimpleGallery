package de.mpaeschke.simplegallery.data;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;

/**
 * Created by markuspaeschke on 21.10.15.
 */
public interface ImageDataSource {
    ImageEntity getImage(int index);
    ArrayList<ImageEntity> getImageList();
}
