package de.mpaeschke.simplegallery.domain.interactor;

import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.repository.ImageRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Realises the communication between repository interface and interactor interface.
 */
public class GetImageUseCaseImpl extends UseCase {
    private final ImageRepository mImageRepository;
    private final ImageDomainEntity mImageDomainEntity;

    public GetImageUseCaseImpl(ImageDomainEntity imageDomainEntity,
                               Scheduler subscribeScheduler,
                               Scheduler observeScheduler,
                               ImageRepository imageRepository) {
        super(subscribeScheduler, observeScheduler);
        mImageDomainEntity = imageDomainEntity;
        mImageRepository = imageRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mImageRepository.getFullSizeImage(mImageDomainEntity);
    }
}
