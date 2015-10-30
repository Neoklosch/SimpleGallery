package de.mpaeschke.simplegallery.domain.interactor;

import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.repository.ImageRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Realises the communication between repository interface and interactor interface.
 */
public class GetScaledImageUseCase extends UseCase {
    private final ImageRepository mImageRepository;
    private final ImageDomainEntity mImageDomainEntity;
    private final int mHeight;
    private final int mWidth;

    public GetScaledImageUseCase(ImageDomainEntity imageDomainEntity,
                                 int height,
                                 int width,
                                 Scheduler subscribeScheduler,
                                 Scheduler observeScheduler,
                                 ImageRepository imageRepository) {
        super(subscribeScheduler, observeScheduler);
        mHeight = height;
        mWidth = width;
        mImageDomainEntity = imageDomainEntity;
        mImageRepository = imageRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mImageRepository.getScaledImage(mImageDomainEntity, mHeight, mWidth);
    }
}
