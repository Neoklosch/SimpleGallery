package de.mpaeschke.simplegallery.data.repository.datasources;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ImageDataStoreFactory {
    private static ImageDataStoreFactory INSTANCE;

    public static synchronized ImageDataStoreFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageDataStoreFactory();
        }
        return INSTANCE;
    }

    protected ImageDataStoreFactory() {

    }

    public ImageDataStore create(int imageDataStoreType) {
        ImageDataStore imageDataStore;

        if (imageDataStoreType == ApiImageDataStore.API_IMAGE_DATA_STORE_TYPE) {
            imageDataStore = new ApiImageDataStore();
        } else if (imageDataStoreType == CacheImageDataStore.CACHE_IMAGE_DATA_STORE_TYPE) {
            imageDataStore = new CacheImageDataStore();
        } else if (imageDataStoreType == LocalFolderImageDataStore.LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE) {
            imageDataStore = new LocalFolderImageDataStore();
        } else {
            imageDataStore = null;
        }

        return imageDataStore;
    }
}
