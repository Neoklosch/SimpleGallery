package de.mpaeschke.simplegallery.domain.repository;

import java.util.ArrayList;

/**
 * The repository interface is used to handle the communication between the domain layer and the data layer.
 */
public interface ImageRepository {
    interface ImageRepositoryCallback {
        void onImageLoaded(de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity imageDomainEntity);
        void onImageError(Exception exception);
    }

    /**
     * Interface to notify the result of the user data request.
     */
    interface ImageListRepositoryCallback {
        void onImageListLoaded(ArrayList<de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity> imageList);
        void onImageListError(Exception exception);
    }

    void getImage(ImageRepositoryCallback imageRepositoryCallback);

    void getImageList(ImageListRepositoryCallback imageListRepositoryCallback);
}
