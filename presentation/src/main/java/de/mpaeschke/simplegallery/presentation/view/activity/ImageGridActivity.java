package de.mpaeschke.simplegallery.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.R;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.presenter.ImageGridPresenter;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;
import de.mpaeschke.simplegallery.presentation.view.ImageGridView;
import de.mpaeschke.simplegallery.presentation.view.adapter.ImageGridAdapter;

/**
 * Activity that shows a grid with images.
 */
public class ImageGridActivity extends AppCompatActivity implements ImageGridView {
    private ImageGridPresenter mImageGridPresenter;
    private GridView mGridView;
    private ImageGridAdapter mImageGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);

        setPresenter(new ImageGridPresenter(this));

        mGridView = (GridView) findViewById(R.id.image_grid);
        mImageGridAdapter = new ImageGridAdapter(this, null);
        mGridView.setAdapter(mImageGridAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mImageGridPresenter.resume();
    }

    public void renderImageGrid(ArrayList<ImageEntity> imageEntityList) {
        mImageGridAdapter.setImageModelList(imageEntityList);
    }

    public void setPresenter(MVPPresenter presenter) {
        mImageGridPresenter = (ImageGridPresenter) presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showEmpty() {

    }

    @Override
    public void hideEmpty() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }
}
