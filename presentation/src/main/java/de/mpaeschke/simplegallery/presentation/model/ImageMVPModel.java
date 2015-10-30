package de.mpaeschke.simplegallery.presentation.model;

import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import rx.Subscriber;

/**
 * Created by markuspaeschke on 29.10.15.
 */
public interface ImageMVPModel extends MVPModel {
    void getImage(ImageEntity imageEntity, Subscriber subscriber);
    void getImageList(final Subscriber subscriber);
    void getScaledImage(ImageEntity imageEntity, int height, int width, final Subscriber subscriber);
}
