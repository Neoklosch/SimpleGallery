package de.mpaeschke.simplegallery.presentation.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.R;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.presenter.ImageGridPresenter;
import de.mpaeschke.simplegallery.presentation.presenter.MVPPresenter;
import de.mpaeschke.simplegallery.presentation.view.ImageGridView;
import de.mpaeschke.simplegallery.presentation.view.adapter.ImageGridAdapter;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Activity that shows a grid with images.
 */
public class ImageGridActivity extends AppCompatActivity implements ImageGridView {
    private ImageGridPresenter mImageGridPresenter;
    private GridView mGridView;
    private ImageGridAdapter mImageGridAdapter;
    private Observable<String> mSecondObservable = Observable.just("DO IT!");
    private final Observable<String> mObservable = Observable.create(new Observable.OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello World!");
            subscriber.onCompleted();
        }
    });
    private final Subscriber<String> mSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            mImageGridAdapter.addImage(new ImageEntity(s));
        }
    };

    private final Action1<String> mNextAction = new Action1<String>() {
        @Override
        public void call(String s) {
            mImageGridAdapter.addImage(new ImageEntity(s));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_grid);

        setPresenter(new ImageGridPresenter(this));

        mGridView = (GridView) findViewById(R.id.image_grid);
        mImageGridAdapter = new ImageGridAdapter(this, null);
        mGridView.setAdapter(mImageGridAdapter);

        findViewById(R.id.keks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mObservable.subscribe(mSubscriber);
            }
        });

        findViewById(R.id.zwei).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSecondObservable.subscribe(mNextAction);
            }
        });

        findViewById(R.id.drei).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Observable.just("Schizophren").subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        mImageGridAdapter.addImage(new ImageEntity(s));
                    }
                });
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
