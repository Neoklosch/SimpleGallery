package de.mpaeschke.simplegallery.presentation.view;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;

/**
 * Created by markuspaeschke on 16.10.15.
 */
public interface ImageGridView extends LoadingMVPView {
    void renderImageGrid(ArrayList<ImageEntity> imageEntityList);
}
