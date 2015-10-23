package de.mpaeschke.simplegallery.data.rest;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;

/**
 * Created by markuspaeschke on 21.10.15.
 */
public class ImageRestSourceImpl implements ImageRestSource {
    private ArrayList<ImageEntity> mImageEntityList;

    public ImageRestSourceImpl() {
        mImageEntityList = new ArrayList<ImageEntity>();
        mImageEntityList.add(new ImageEntity("Rest A"));
        mImageEntityList.add(new ImageEntity("Rest B"));
        mImageEntityList.add(new ImageEntity("Rest C"));
        mImageEntityList.add(new ImageEntity("Rest D"));
        mImageEntityList.add(new ImageEntity("Rest E"));
        mImageEntityList.add(new ImageEntity("Rest F"));
        mImageEntityList.add(new ImageEntity("Rest G"));
        mImageEntityList.add(new ImageEntity("Rest H"));
        mImageEntityList.add(new ImageEntity("Rest I"));
        mImageEntityList.add(new ImageEntity("Rest J"));
        mImageEntityList.add(new ImageEntity("Rest K"));
        mImageEntityList.add(new ImageEntity("Rest L"));
        mImageEntityList.add(new ImageEntity("Rest M"));
        mImageEntityList.add(new ImageEntity("Rest N"));
        mImageEntityList.add(new ImageEntity("Rest O"));
        mImageEntityList.add(new ImageEntity("Rest P"));
    }

    @Override
    public void get(ImageRestSourceCallback imageCacheCallback) {
        if (mImageEntityList != null) {
            imageCacheCallback.onImageEntityListLoaded(mImageEntityList);
        } else {
            imageCacheCallback.onImageEntityListError(new Exception("List is empty"));
        }
    }

    @Override
    public void put(ImageEntity imageEntity) {

    }
}
