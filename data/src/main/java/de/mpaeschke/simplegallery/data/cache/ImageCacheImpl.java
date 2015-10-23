package de.mpaeschke.simplegallery.data.cache;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ImageCacheImpl implements ImageCache {
    @Override
    public synchronized Observable<ArrayList<ImageEntity>> get() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<ImageEntity>>() {

            @Override
            public void call(Subscriber<? super ArrayList<ImageEntity>> subscriber) {
                ArrayList<ImageEntity> imageEntityList = new ArrayList<ImageEntity>();
                imageEntityList.add(new ImageEntity("Cache A"));
                imageEntityList.add(new ImageEntity("Cache B"));
                imageEntityList.add(new ImageEntity("Cache C"));
                imageEntityList.add(new ImageEntity("Cache D"));
                imageEntityList.add(new ImageEntity("Cache E"));
                imageEntityList.add(new ImageEntity("Cache F"));
                imageEntityList.add(new ImageEntity("Cache G"));
                imageEntityList.add(new ImageEntity("Cache H"));
                imageEntityList.add(new ImageEntity("Cache I"));
                imageEntityList.add(new ImageEntity("Cache J"));
                imageEntityList.add(new ImageEntity("Cache K"));
                imageEntityList.add(new ImageEntity("Cache L"));
                imageEntityList.add(new ImageEntity("Cache M"));
                imageEntityList.add(new ImageEntity("Cache N"));
                imageEntityList.add(new ImageEntity("Cache O"));
                imageEntityList.add(new ImageEntity("Cache P"));

                if (imageEntityList != null) {
                    subscriber.onNext(imageEntityList);
                } else {
                    subscriber.onError(new Exception("List is empty"));
                }
            }
        });
    }

    @Override
    public synchronized void put(ImageEntity imageEntity) {
        // todo: implement put
    }
}
