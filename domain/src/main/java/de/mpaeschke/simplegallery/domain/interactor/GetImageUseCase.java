package de.mpaeschke.simplegallery.domain.interactor;

import java.util.ArrayList;

/**
 * This interface represents a execution unit for a use case to get a collection of {@link de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity}.
 * By convention this use case (Interactor) implementation will return the result using a Callback.
 * That callback should be executed in the UI thread.
 */
public interface GetImageUseCase extends Interactor {
    interface GetImageUseCaseCallback {
        void onImageLoaded(de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity imageDomainEntity);
        void onImageError(Exception exception);
    }

    interface GetImageListUseCaseCallback {
        void onImageListLoaded(ArrayList<de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity> imageList);
        void onImageListError(Exception exception);
    }

    void execute(GetImageUseCaseCallback getImageUseCaseCallback);

    void execute(GetImageListUseCaseCallback getImageListUseCaseCallback);
}
