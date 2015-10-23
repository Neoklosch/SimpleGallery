package de.mpaeschke.simplegallery.domain.interactor;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.domain.repository.ImageRepository;

/**
 * Realises the communication between repository interface and interactor interface.
 */
public class GetImageUseCaseImpl implements GetImageUseCase {
    private final ImageRepository mImageRepository;

    private GetImageUseCaseCallback mImageUseCaseCallback;

    private final ImageRepository.ImageRepositoryCallback mImageRepositoryCallback = new ImageRepository.ImageRepositoryCallback() {

        @Override
        public void onImageLoaded(de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity imageDomainEntity) {
            mImageUseCaseCallback.onImageLoaded(imageDomainEntity);
        }

        @Override
        public void onImageError(Exception exception) {
            mImageUseCaseCallback.onImageError(exception);
        }
    };

    public GetImageUseCaseImpl(ImageRepository imageRepository) {
        if (imageRepository == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!");
        }
        mImageRepository = imageRepository;
    }

    @Override
    public void execute(GetImageUseCaseCallback getImageUseCaseCallback) {
        if (getImageUseCaseCallback == null) {
            throw new IllegalArgumentException("Interactor callback cannot be null!");
        }
        mImageUseCaseCallback = getImageUseCaseCallback;
    }

    @Override
    public void execute(final GetImageListUseCaseCallback getImageListUseCaseCallback) {
        if (getImageListUseCaseCallback == null) {
            throw new IllegalArgumentException("Interactor callback cannot be null!");
        }
        mImageRepository.getImageList(new ImageRepository.ImageListRepositoryCallback() {

            @Override
            public void onImageListLoaded(ArrayList<de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity> imageList) {
                getImageListUseCaseCallback.onImageListLoaded(imageList);
            }

            @Override
            public void onImageListError(Exception exception) {
                getImageListUseCaseCallback.onImageListError(exception);
            }
        });
    }

    @Override
    public void run() {

    }
}
