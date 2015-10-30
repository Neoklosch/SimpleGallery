package de.mpaeschke.simplegallery.presentation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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
    private LinearLayout mContentWrapper;
    private RelativeLayout mEmptyWrapper;
    private RelativeLayout mLoadingWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);

        setPresenter(new ImageGridPresenter(this));

        mContentWrapper = (LinearLayout) findViewById(R.id.activity_image_grid_content_wrapper);
        mEmptyWrapper = (RelativeLayout) findViewById(R.id.activity_image_grid_empty_wrapper);
        mLoadingWrapper = (RelativeLayout) findViewById(R.id.activity_image_grid_loading_wrapper);
        mGridView = (GridView) findViewById(R.id.image_grid);
        mImageGridAdapter = new ImageGridAdapter(this, mImageGridPresenter, null);
        mGridView.setAdapter(mImageGridAdapter);

        findViewById(R.id.activity_image_grid_detailpage).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(ImageGridActivity.this, ImageDetailActivity.class));
            }
        });
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
        mContentWrapper.setVisibility(View.GONE);
        mEmptyWrapper.setVisibility(View.GONE);
        mLoadingWrapper.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mContentWrapper.setVisibility(View.VISIBLE);
        mEmptyWrapper.setVisibility(View.GONE);
        mLoadingWrapper.setVisibility(View.GONE);
    }

    @Override
    public void showEmpty() {
        mContentWrapper.setVisibility(View.GONE);
        mEmptyWrapper.setVisibility(View.VISIBLE);
        mLoadingWrapper.setVisibility(View.GONE);
    }

    @Override
    public void hideEmpty() {
        mContentWrapper.setVisibility(View.VISIBLE);
        mEmptyWrapper.setVisibility(View.GONE);
        mLoadingWrapper.setVisibility(View.GONE);
    }

    @Override
    public void showError() {

    }

    @Override
    public void hideError() {

    }
}
