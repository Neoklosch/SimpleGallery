package de.mpaeschke.simplegallery.data.rest;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

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
    public Observable<ArrayList<ImageEntity>> get() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<ImageEntity>>() {

            @Override
            public void call(Subscriber<? super ArrayList<ImageEntity>> subscriber) {
                if (mImageEntityList != null) {
                    subscriber.onNext(mImageEntityList);
                } else {
                    subscriber.onError(new Exception("List is empty"));
                }
            }
        });
    }

    @Override
    public void put(ImageEntity imageEntity) {

    }
}
