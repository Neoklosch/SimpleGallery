package de.mpaeschke.simplegallery.presentation.presenter;

import android.util.Log;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.presentation.model.ImageGridMVPModel;
import de.mpaeschke.simplegallery.presentation.model.ImageGridModel;
import de.mpaeschke.simplegallery.presentation.model.MVPModel;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.view.ImageGridView;
import de.mpaeschke.simplegallery.presentation.view.MVPView;

public class ImageGridPresenter implements MVPPresenter {
    private ImageGridView mImageGridView;
    private ImageGridModel mImageGridModel;

    private final ImageGridMVPModel.ImageGridModelCallback mImageGridModelCallback = new ImageGridMVPModel.ImageGridModelCallback() {

        @Override
        public void onImageListLoaded(ArrayList<ImageEntity> imageList) {
            mImageGridView.renderImageGrid(imageList);
        }

        @Override
        public void onImageListError(Exception exception) {
            Log.e("ImageGridPresenter", exception.getMessage());
        }
    };

    public ImageGridPresenter(MVPView view) {
        setView(view);
        setModel(new ImageGridModel(this));
    }

    @Override
    public void resume() {
        mImageGridModel.getImageList(mImageGridModelCallback);
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
