package de.mpaeschke.simplegallery.presentation.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import de.mpaeschke.simplegallery.R;
import de.mpaeschke.simplegallery.presentation.presenter.ImageDetailPresenter;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;
import de.mpaeschke.simplegallery.presentation.view.ImageDetailView;


public class ImageDetailActivity extends AppCompatActivity implements ImageDetailView {
    private MVPPresenter mvpPresenter;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        setPresenter(new ImageDetailPresenter(this));

        mImageView = (ImageView) findViewById(R.id.activity_image_detail_image);
        findViewById(R.id.reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mvpPresenter.resume();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.resume();
    }

    @Override
    public void showImage(Bitmap bitmap) {
        mImageView.setImageBitmap(bitmap);
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

    @Override
    public void setPresenter(MVPPresenter presenter) {
        mvpPresenter = presenter;
    }

    @Override
    public int getWindowWidth() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.widthPixels;
    }

    @Override
    public int getWindowHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        return displaymetrics.heightPixels;
    }
}
