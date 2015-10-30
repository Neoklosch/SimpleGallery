package de.mpaeschke.simplegallery.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.Subscriptions;


public abstract class UseCase {
    private final Scheduler mObserveScheduler;
    private final Scheduler mSubscribeScheduler;

    private Subscription mSubscription = Subscriptions.empty();

    protected UseCase(Scheduler subscribeScheduler, Scheduler observeScheduler) {
        mSubscribeScheduler = subscribeScheduler;
        mObserveScheduler = observeScheduler;
    }

    protected abstract Observable buildUseCaseObservable();

    @SuppressWarnings("unchecked")
    public void execute(Subscriber useCaseSubscriber) {
        mSubscription = buildUseCaseObservable()
                .subscribeOn(mSubscribeScheduler)
                .observeOn(mObserveScheduler)
                .subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
