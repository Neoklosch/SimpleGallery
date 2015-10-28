package de.mpaeschke.simplegallery.data.repository.datasources;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.local.ImageFolderLoaderImpl;
import de.mpaeschke.simplegallery.data.rest.ImageRestSourceImpl;
import rx.Observable;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class LocalFolderImageDataStore implements ImageDataStore {
    public final static int LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE = 2;

    @Override
    public Observable<ArrayList<ImageEntity>> getImageEntityList() {
        ImageFolderLoaderImpl imageFolderLoader = new ImageFolderLoaderImpl();
        return imageFolderLoader.get();
    }

    @Override
    public int getDataStoreType() {
        return LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE;
    }
}
