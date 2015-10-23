package de.mpaeschke.simplegallery.data.repository;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.entity.mapper.ImageEntityDataMapper;
import de.mpaeschke.simplegallery.data.repository.datasources.ApiImageDataStore;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStore;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStoreFactory;
import de.mpaeschke.simplegallery.domain.repository.ImageRepository;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ImageListDataRepository implements ImageRepository {
    private static ImageListDataRepository INSTANCE;

    public static synchronized ImageListDataRepository getInstance(ImageDataStoreFactory imageDataStoreFactory, ImageEntityDataMapper imageEntityDataMapper) {
        if (INSTANCE == null) {
            INSTANCE = new ImageListDataRepository(imageDataStoreFactory, imageEntityDataMapper);
        }
        return INSTANCE;
    }


    private final ImageDataStoreFactory mImageDataStoreFactory;
    private final ImageEntityDataMapper mImageEntityDataMapper;

    protected ImageListDataRepository(ImageDataStoreFactory imageDataStoreFactory, ImageEntityDataMapper imageEntityDataMapper) {
        if (imageDataStoreFactory == null || imageEntityDataMapper == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!");
        }

        mImageDataStoreFactory = imageDataStoreFactory;
        mImageEntityDataMapper = imageEntityDataMapper;
    }

    @Override
    public void getImage(ImageRepositoryCallback imageRepositoryCallback) {
        // todo: implement it
    }

    @Override
    public void getImageList(final ImageListRepositoryCallback imageListRepositoryCallback) {
        final ImageDataStore imageDataStore = mImageDataStoreFactory.create(ApiImageDataStore.API_IMAGE_DATA_STORE_TYPE);
        imageDataStore.getImageEntityList(new ImageDataStore.ImageListDataStoreCallback() {

            @Override
            public void onImageListLoaded(ArrayList<ImageEntity> imageList) {
                // transform and release
                imageListRepositoryCallback.onImageListLoaded(mImageEntityDataMapper.tranform(imageList));
            }

            @Override
            public void onError(Exception exception) {
                imageListRepositoryCallback.onImageListError(exception);
            }
        });
    }
}
