package de.mpaeschke.simplegallery.domain.interactor;

import de.mpaeschke.simplegallery.domain.repository.ImageRepository;
import rx.Observable;
import rx.Scheduler;

/**
 * Realises the communication between repository interface and interactor interface.
 */
public class GetImageListUseCase extends UseCase {
    private final ImageRepository mImageRepository;

    public GetImageListUseCase(Scheduler subscribeScheduler,
                               Scheduler observeScheduler,
                               ImageRepository imageRepository) {
        super(subscribeScheduler, observeScheduler);
        mImageRepository = imageRepository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return mImageRepository.getImageList();
    }
}
