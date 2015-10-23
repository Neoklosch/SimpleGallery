package de.mpaeschke.simplegallery.presentation.model;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.repository.ImageListDataRepository;
import de.mpaeschke.simplegallery.data.repository.datasources.ImageDataStoreFactory;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.domain.interactor.GetImageUseCase;
import de.mpaeschke.simplegallery.domain.interactor.GetImageUseCaseImpl;
import de.mpaeschke.simplegallery.presentation.model.mapper.ImageEntityMapper;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;

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

    public void getImageList(final ImageGridMVPModel.ImageGridModelCallback imageGridMVPModel) {
        mImageUseCase.execute(new GetImageUseCase.GetImageListUseCaseCallback() {

            @Override
            public void onImageListLoaded(ArrayList<ImageDomainEntity> imageList) {
                ImageEntityMapper imageEntityMapper = new ImageEntityMapper();
                imageGridMVPModel.onImageListLoaded(imageEntityMapper.transform(imageList));
            }

            @Override
            public void onImageListError(Exception exception) {
                imageGridMVPModel.onImageListError(exception);
            }
        });
    }
}
