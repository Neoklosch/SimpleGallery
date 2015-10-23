package de.mpaeschke.simplegallery.presentation.model;


import rx.Subscriber;

/**
 * Created by markuspaeschke on 23.10.15.
 */
public interface ImageGridMVPModel extends MVPModel {
    void getImageList(final Subscriber subscriber);
}
