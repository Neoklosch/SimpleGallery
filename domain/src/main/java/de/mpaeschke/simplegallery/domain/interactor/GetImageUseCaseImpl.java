package de.mpaeschke.simplegallery.domain.interactor;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.domain.repository.ImageRepository;

/**
 * Realises the communication between repository interface and interactor interface.
 */
public class GetImageUseCaseImpl implements GetImageUseCase {
    private final ImageRepository mImageRepository;

    private GetImageUseCaseCallback mImageUseCaseCallback;
    private GetImageListUseCaseCallback mImageListUseCaseCallback;

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

    private final ImageRepository.ImageListRepositoryCallback mImageListRepositoryCallback = new ImageRepository.ImageListRepositoryCallback() {

        @Override
        public void onImageListLoaded(ArrayList<de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity> imageList) {
            mImageListUseCaseCallback.onImageListLoaded(imageList);
        }

        @Override
        public void onImageListError(Exception exception) {
            mImageListUseCaseCallback.onImageListError(exception);
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
    public void execute(GetImageListUseCaseCallback getImageListUseCaseCallback) {
        if (getImageListUseCaseCallback == null) {
            throw new IllegalArgumentException("Interactor callback cannot be null!");
        }
        mImageListUseCaseCallback = getImageListUseCaseCallback;
        run();
    }

    @Override
    public void run() {
        mImageRepository.getImageList(mImageListRepositoryCallback);
    }
}
