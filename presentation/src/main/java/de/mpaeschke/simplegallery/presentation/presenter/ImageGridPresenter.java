package de.mpaeschke.simplegallery.presentation.presenter;

import android.util.Log;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.presentation.model.ImageGridMVPModel;
import de.mpaeschke.simplegallery.presentation.model.ImageGridModel;
import de.mpaeschke.simplegallery.presentation.model.MVPModel;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.view.ImageGridView;
import de.mpaeschke.simplegallery.presentation.view.MVPView;
import rx.Subscriber;

public class ImageGridPresenter implements MVPPresenter {
    private ImageGridView mImageGridView;
    private ImageGridModel mImageGridModel;

    public ImageGridPresenter(MVPView view) {
        setView(view);
        setModel(new ImageGridModel(this));
    }

    @Override
    public void resume() {
        mImageGridModel.getImageList(new Subscriber<ArrayList<ImageEntity>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("ImageGridPresenter", e.getMessage());
            }

            @Override
            public void onNext(ArrayList<ImageEntity> imageEntityArrayList) {
                mImageGridView.renderImageGrid(imageEntityArrayList);
            }
        });
    }

    @Override
    public void pause() {

    }

    @Override
    public void setView(MVPView view) {
        mImageGridView = (ImageGridView) view;
    }

    @Override
    public void setModel(MVPModel model) {
        mImageGridModel = (ImageGridModel) model;
    }


}
