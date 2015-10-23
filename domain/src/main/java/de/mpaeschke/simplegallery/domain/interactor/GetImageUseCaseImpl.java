package de.mpaeschke.simplegallery.domain.interactor;

import de.mpaeschke.simplegallery.domain.repository.ImageRepository;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Realises the communication between repository interface and interactor interface.
 */
public class GetImageUseCaseImpl implements GetImageUseCase {
    private final ImageRepository mImageRepository;

    private Subscription mSubscription = Subscriptions.empty();

    public GetImageUseCaseImpl(ImageRepository imageRepository) {
        if (imageRepository == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!");
        }
        mImageRepository = imageRepository;
    }

    @Override
    public void execute(final Subscriber subscriber) {
        mSubscription = mImageRepository.getImageList().subscribe(subscriber);
    }

    @Override
    public void run() {

    }
}
