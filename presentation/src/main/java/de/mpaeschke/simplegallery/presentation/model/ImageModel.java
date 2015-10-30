package de.mpaeschke.simplegallery.presentation.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.repository.ImageDataRepository;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStoreFactory;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.interactor.GetImageListUseCase;
import de.mpaeschke.simplegallery.domain.interactor.GetImageUseCaseImpl;
import de.mpaeschke.simplegallery.domain.interactor.GetScaledImageUseCase;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.model.entity.mapper.ImageEntityMapper;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


public class ImageModel implements ImageMVPModel {
    private MVPPresenter mPresenter;
    private GetImageUseCaseImpl mImageUseCase;

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
        ImageDataRepository imageListDataRepository = ImageDataRepository.getInstance(new ImageDataStoreFactory());
        mImageUseCase = new GetImageUseCaseImpl(imageEntityMapper.transform(imageEntity), Schedulers.io(), Schedulers.io(), imageListDataRepository);
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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }

    public void getImageList(final Subscriber subscriber) {
        ImageDataRepository imageListDataRepository = ImageDataRepository.getInstance(new ImageDataStoreFactory());
        GetImageListUseCase mImageUseCase = new GetImageListUseCase(Schedulers.io(), Schedulers.io(), imageListDataRepository);
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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }

    @Override
    public void getScaledImage(ImageEntity imageEntity, int height, int width, final Subscriber subscriber) {
        ImageEntityMapper imageEntityMapper = new ImageEntityMapper();
        ImageDataRepository imageListDataRepository = ImageDataRepository.getInstance(new ImageDataStoreFactory());
        GetScaledImageUseCase mImageUseCase = new GetScaledImageUseCase(imageEntityMapper.transform(imageEntity), height, width, Schedulers.io(), Schedulers.io(), imageListDataRepository);
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
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
            }
        });
    }
}