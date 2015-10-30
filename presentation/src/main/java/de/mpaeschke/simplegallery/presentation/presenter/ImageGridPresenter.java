package de.mpaeschke.simplegallery.presentation.presenter;

import android.os.Handler;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.presentation.model.ImageModel;
import de.mpaeschke.simplegallery.presentation.model.MVPModel;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.view.ImageGridView;
import de.mpaeschke.simplegallery.presentation.view.MVPView;
import rx.Subscriber;

public class ImageGridPresenter implements MVPPresenter {
    private ImageGridView mImageGridView;
    private ImageModel mImageGridModel;

    public ImageGridPresenter(MVPView view) {
        setView(view);
        setModel(new ImageModel(this));
    }

    @Override
    public void resume() {
        mImageGridView.showLoading();
        mImageGridModel.getImageList(new Subscriber<ArrayList<ImageEntity>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mImageGridView.showEmpty();
            }

            @Override
            public void onNext(ArrayList<ImageEntity> imageEntityArrayList) {
                mImageGridView.renderImageGrid(imageEntityArrayList);
                // TODO: post delayed only to see the spinner
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mImageGridView.hideLoading();
                    }
                }, 2000);
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
        mImageGridModel = (ImageModel) model;
    }

    public void getImage(ImageEntity imageEntity, int height, int width, Subscriber subscriber) {
        mImageGridModel.getScaledImage(imageEntity, height, width, subscriber);
    }
}
