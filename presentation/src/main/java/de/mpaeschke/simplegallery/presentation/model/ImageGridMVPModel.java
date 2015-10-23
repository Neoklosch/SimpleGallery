package de.mpaeschke.simplegallery.presentation.model;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;

/**
 * Created by markuspaeschke on 23.10.15.
 */
public interface ImageGridMVPModel extends MVPModel {
    interface ImageGridModelCallback {
        void onImageListLoaded(ArrayList<ImageEntity> imageList);
        void onImageListError(Exception exception);
    }
}
