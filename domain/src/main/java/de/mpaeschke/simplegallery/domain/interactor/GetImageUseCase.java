package de.mpaeschke.simplegallery.domain.interactor;

import rx.Subscriber;

/**
 * This interface represents a execution unit for a use case to get a collection of {@link de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity}.
 * By convention this use case (Interactor) implementation will return the result using a Callback.
 * That callback should be executed in the UI thread.
 */
public interface GetImageUseCase extends Interactor {
    void execute(final Subscriber subscriber);
}
