package de.mpaeschke.simplegallery.presentation.model;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.concurrent.Executors;

import de.mpaeschke.simplegallery.data.repository.ImageDataRepository;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.interactor.GetImageListUseCase;
import de.mpaeschke.simplegallery.domain.interactor.GetImageUseCaseImpl;
import de.mpaeschke.simplegallery.domain.interactor.GetScaledImageUseCase;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.model.entity.mapper.ImageEntityMapper;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ImageModel implements ImageMVPModel {
    private MVPPresenter mPresenter;
    private GetImageUseCaseImpl mImageUseCase;

    private Scheduler mScheduler = Schedulers.from(Executors.newFixedThreadPool(8));

    public ImageModel(MVPPresenter presenter) {
        setPresenter(presenter);
    }

    @Override
    public void setPresenter(MVPPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void getImage(final ImageEntity imageEntity, final Subscriber subscriber) {
        ImageEntityMapper imageEntityMapper = new ImageEntityMapper();
        ImageDataRepository imageListDataRepository = ImageDataRepository.getInstance();
        mImageUseCase = new GetImageUseCaseImpl(imageEntityMapper.transform(imageEntity), mScheduler, mScheduler, imageListDataRepository);
        mImageUseCase.execute(new Subscriber<Object>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Observable.error(e)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }

            @Override
            public void onNext(Object bitmap) {
                Observable.just(bitmap)
                        .map(new Func1<Object, Bitmap>() {
                            @Override
                            public Bitmap call(Object bitmap) {
                                return (Bitmap) bitmap;
                            }
                        })
                        .subscribeOn(mScheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }

    public void getImageList(final Subscriber subscriber) {
        ImageDataRepository imageListDataRepository = ImageDataRepository.getInstance();
        GetImageListUseCase mImageUseCase = new GetImageListUseCase(mScheduler, mScheduler, imageListDataRepository);
        mImageUseCase.execute(new Subscriber<ArrayList<ImageDomainEntity>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Observable.error(e)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }

            @Override
            public void onNext(ArrayList<ImageDomainEntity> imageDomainEntityArrayList) {
                Observable.just(imageDomainEntityArrayList)
                        .map(new Func1<ArrayList<ImageDomainEntity>, ArrayList<ImageEntity>>() {
                            @Override
                            public ArrayList<ImageEntity> call(ArrayList<ImageDomainEntity> imageDomainEntityArrayList) {
                                return new ImageEntityMapper().transform(imageDomainEntityArrayList);
                            }
                        })
                        .subscribeOn(mScheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }

    @Override
    public void getScaledImage(ImageEntity imageEntity, int height, int width, final Subscriber subscriber) {
        ImageEntityMapper imageEntityMapper = new ImageEntityMapper();
        ImageDataRepository imageListDataRepository = ImageDataRepository.getInstance();
        GetScaledImageUseCase mImageUseCase = new GetScaledImageUseCase(imageEntityMapper.transform(imageEntity), height, width, mScheduler, mScheduler, imageListDataRepository);
        mImageUseCase.execute(new Subscriber<Object>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Observable.error(e)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }

            @Override
            public void onNext(Object bitmap) {
                Observable.just(bitmap)
                        .map(new Func1<Object, Bitmap>() {
                            @Override
                            public Bitmap call(Object bitmap) {
                                return (Bitmap) bitmap;
                            }
                        })
                        .subscribeOn(mScheduler)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }
}
