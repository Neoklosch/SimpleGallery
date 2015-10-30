package de.mpaeschke.simplegallery.data.cache;

import android.graphics.Bitmap;

import rx.Observable;
import rx.Subscriber;


public class ImageCacheImpl implements ImageCache {
    private ImageLruCache mMemoryCache;

    public ImageCacheImpl() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        mMemoryCache = ImageLruCache.getInstance(cacheSize);
    }

    @Override
    public synchronized Observable<Bitmap> get(final String key) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {

            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = mMemoryCache.get(key);

                if (bitmap != null) {
                    subscriber.onNext(bitmap);
                } else {
                    subscriber.onError(new Exception("Bitmap not in cache"));
                }
            }
        });
    }

    @Override
    public synchronized void put(final String key, Bitmap image) {
        if (!hasKey(key)) {
            mMemoryCache.put(key, image);
        }
    }

    public boolean hasKey(String key) {
        return mMemoryCache.get(key) != null;
    }
}
