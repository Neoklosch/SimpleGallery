package de.mpaeschke.simplegallery.data.repository.datasources;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.local.ImageFolderLoaderImpl;
import rx.Observable;


public class LocalFolderImageDataStore implements ImageDataStore {
    public final static int LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE = 2;

    ImageFolderLoaderImpl mImageFolderLoader;

    public LocalFolderImageDataStore() {
        mImageFolderLoader = new ImageFolderLoaderImpl();
    }

    @Override
    public Observable<ArrayList<ImageEntity>> getImageEntityList() {
        return mImageFolderLoader.getImageList();
    }

    @Override
    public Observable<Bitmap> getImage(final ImageEntity imageEntity) {
        return mImageFolderLoader.getFullSizeImage(imageEntity);
    }

    @Override
    public Observable<Bitmap> getScaledImage(ImageEntity imageEntity, int height, int width) {
        return mImageFolderLoader.getScaledImage(imageEntity, height, width);
    }

    @Override
    public boolean imageExists(ImageEntity imageEntity) {
        return mImageFolderLoader.imageExists(imageEntity);
    }

    @Override
    public int getDataStoreType() {
        return LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE;
    }
}
