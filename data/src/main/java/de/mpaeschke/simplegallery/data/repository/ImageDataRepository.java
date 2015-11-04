package de.mpaeschke.simplegallery.data.repository;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.data.entity.mapper.ImageEntityDataMapper;
import de.mpaeschke.simplegallery.data.repository.datasources.CacheImageDataStore;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStore;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStoreFactory;
import de.mpaeschke.simplegallery.data.repository.datasources.LocalFolderImageDataStore;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.repository.ImageRepository;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ImageDataRepository implements ImageRepository {
    private static ImageDataRepository INSTANCE;

    public static synchronized ImageDataRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ImageDataRepository();
        }
        return INSTANCE;
    }

    protected ImageDataRepository() {
    }

    @Override
    public Observable<ArrayList<ImageDomainEntity>> getImageList() {
        final ImageDataStore imageDataStore = ImageDataStoreFactory.getInstance().create(LocalFolderImageDataStore.LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE);
        return imageDataStore.getImageEntityList()
                .map(new Func1<ArrayList<ImageEntity>, ArrayList<ImageDomainEntity>>() {
            @Override
            public ArrayList<ImageDomainEntity> call(ArrayList<ImageEntity> imageEntityArrayList) {
                return new ImageEntityDataMapper().tranform(imageEntityArrayList);
            }
        });
    }

    @Override
    public Observable<Object> getFullSizeImage(ImageDomainEntity imageDomainEntity) {
        ImageEntity imageEntity = new ImageEntityDataMapper().transform(imageDomainEntity);
        final ImageDataStore dataStoreToUse = chooseDataStore(imageEntity);
        return dataStoreToUse.getImage(imageEntity)
                .map(new Func1<Bitmap, Object>() {
                    @Override
                    public Object call(Bitmap imageBitmap) {
                        return imageBitmap;
                    }
                });
    }

    @Override
    public Observable<Object> getScaledImage(ImageDomainEntity imageDomainEntity, int height, int width) {
        ImageEntity imageEntity = new ImageEntityDataMapper().transform(imageDomainEntity);
        final ImageDataStore dataStoreToUse = chooseDataStore(imageEntity);
        return dataStoreToUse.getScaledImage(imageEntity, height, width)
                .map(new Func1<Bitmap, Object>() {
                    @Override
                    public Object call(Bitmap imageBitmap) {
                        return imageBitmap;
                    }
                });
    }

    private ImageDataStore chooseDataStore(ImageEntity imageEntity) {
        final ImageDataStore cacheImageDataStore = ImageDataStoreFactory.getInstance().create(CacheImageDataStore.CACHE_IMAGE_DATA_STORE_TYPE);
        final ImageDataStore localFolderImageDataStore = ImageDataStoreFactory.getInstance().create(LocalFolderImageDataStore.LOCAL_FOLDER_IMAGE_DATA_STORE_TYPE);
        if (cacheImageDataStore.imageExists(imageEntity)) {
            return cacheImageDataStore;
        }
        return localFolderImageDataStore;
    }
}
