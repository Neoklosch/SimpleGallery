package de.mpaeschke.simplegallery.presentation.model;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.repository.ImageListDataRepository;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStoreFactory;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.interactor.GetImageUseCase;
import de.mpaeschke.simplegallery.domain.interactor.GetImageUseCaseImpl;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.model.mapper.ImageEntityMapper;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by markuspaeschke on 23.10.15.
 */
public class ImageGridModel implements ImageGridMVPModel {
    private MVPPresenter mPresenter;
    private GetImageUseCase mImageUseCase;

    public ImageGridModel(MVPPresenter presenter) {
        setPresenter(presenter);
        setUseCase();
    }

    @Override
    public void setPresenter(MVPPresenter presenter) {
        mPresenter = presenter;
    }

    private void setUseCase() {
        ImageDataStoreFactory imageDataStoreFactory = new ImageDataStoreFactory();
        ImageListDataRepository imageListDataRepository = ImageListDataRepository.getInstance(imageDataStoreFactory);
        mImageUseCase = new GetImageUseCaseImpl(imageListDataRepository);
    }

    public void getImageList(final Subscriber subscriber) {
        mImageUseCase.execute(new Subscriber<ArrayList<ImageDomainEntity>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Observable.just(e).subscribe(subscriber);
            }

            @Override
            public void onNext(ArrayList<ImageDomainEntity> imageDomainEntityArrayList) {
                Observable.just(imageDomainEntityArrayList)
                        .map(new Func1<ArrayList<ImageDomainEntity>, ArrayList<ImageEntity>>() {
                    @Override
                    public ArrayList<ImageEntity> call(ArrayList<ImageDomainEntity> imageDomainEntityArrayList) {
                        ImageEntityMapper imageEntityMapper = new ImageEntityMapper();
                        return imageEntityMapper.transform(imageDomainEntityArrayList);
                    }
                }).subscribe(subscriber);
            }
        });
    }
}
