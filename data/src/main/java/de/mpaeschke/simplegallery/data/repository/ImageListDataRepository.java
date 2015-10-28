package de.mpaeschke.simplegallery.data.repository;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.entity.mapper.ImageEntityDataMapper;
import de.mpaeschke.simplegallery.data.repository.datasources.ApiImageDataStore;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStore;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStoreFactory;
import de.mpaeschke.simplegallery.data.repository.datasources.LocalFolderImageDataStore;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.repository.ImageRepository;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ImageListDataRepository implements ImageRepository {
    private static ImageListDataRepository INSTANCE;

    public static synchronized ImageListDataRepository getInstance(ImageDataStoreFactory imageDataStoreFactory) {
        if (INSTANCE == null) {
            INSTANCE = new ImageListDataRepository(imageDataStoreFactory);
        }
        return INSTANCE;
    }

    private final ImageDataStoreFactory mImageDataStoreFactory;

    protected ImageListDataRepository(ImageDataStoreFactory imageDataStoreFactory) {
        if (imageDataStoreFactory == null) {
            throw new IllegalArgumentException("Invalid null parameters in constructor!");
        }

        mImageDataStoreFactory = imageDataStoreFactory;
    }

    @Override
    public Observable<ArrayList<ImageDomainEntity>> getImageList() {
        final ImageDataStore imageDataStore = mImageDataStoreFactory.create(LocalFolderImageDataStore.LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE);
        return imageDataStore.getImageEntityList()
                .map(new Func1<ArrayList<ImageEntity>, ArrayList<ImageDomainEntity>>() {
            @Override
            public ArrayList<ImageDomainEntity> call(ArrayList<ImageEntity> imageEntityArrayList) {
                ImageEntityDataMapper imageEntityDataMapper = new ImageEntityDataMapper();
                return imageEntityDataMapper.tranform(imageEntityArrayList);
            }
        });
    }
}
