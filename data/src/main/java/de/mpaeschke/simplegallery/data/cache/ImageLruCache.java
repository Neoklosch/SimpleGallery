package de.mpaeschke.simplegallery.data.cache;

import android.graphics.Bitmap;
import android.util.LruCache;


public class ImageLruCache extends LruCache<String, Bitmap> {
    private static ImageLruCache INSTANCE;

    private ImageLruCache(int maxSize) {
        super(maxSize);
    }

    public static ImageLruCache getInstance(int maxSize) {
        if (INSTANCE == null) {
            INSTANCE = new ImageLruCache(maxSize);
        }
        return INSTANCE;
    }

    @Override
    protected int sizeOf(String key, Bitmap bitmap) {
        return bitmap.getByteCount() / 1024;
    }
}
