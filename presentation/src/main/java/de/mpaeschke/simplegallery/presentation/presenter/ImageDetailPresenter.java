package de.mpaeschke.simplegallery.presentation.presenter;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.Handler;

import de.mpaeschke.simplegallery.presentation.model.ImageModel;
import de.mpaeschke.simplegallery.presentation.model.MVPModel;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.view.ImageDetailView;
import de.mpaeschke.simplegallery.presentation.view.MVPView;
import rx.Subscriber;


public class ImageDetailPresenter implements MVPPresenter {
    private ImageDetailView mImageDetailView;
    private ImageModel mImageDetailModel;
    private boolean onGoing = false;

    public ImageDetailPresenter(MVPView mvpView) {
        setView(mvpView);
        setModel(new ImageModel(this));
    }

    @Override
    public void resume() {
        if (onGoing) {
            return;
        }
        onGoing = true;
        mImageDetailView.showLoading();
        ImageEntity imageEntity = new ImageEntity("IMG_20150928_113441.jpg",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera/IMG_20150928_113441.jpg");
        mImageDetailModel.getImage(imageEntity, new Subscriber<Bitmap>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mImageDetailView.showEmpty();
            }

            @Override
            public void onNext(Bitmap bitmap) {
                mImageDetailView.showImage(bitmap);
                // TODO: post delayed only to see the spinner
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mImageDetailView.hideLoading();
                        onGoing = false;
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
        mImageDetailView = (ImageDetailView) view;
    }

    @Override
    public void setModel(MVPModel model) {
        mImageDetailModel = (ImageModel) model;
    }
}
