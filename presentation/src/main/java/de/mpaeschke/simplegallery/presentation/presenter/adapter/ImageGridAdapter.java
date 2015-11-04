package de.mpaeschke.simplegallery.presentation.presenter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.R;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;
import de.mpaeschke.simplegallery.presentation.presenter.ImageGridPresenter;
import rx.Subscriber;

public class ImageGridAdapter extends BaseAdapter {
    private ArrayList<ImageEntity> mImageEntityList;
    private Context mContext;
    private ImageGridPresenter mPresenter;

    public ImageGridAdapter(Context context, ImageGridPresenter presenter, ArrayList<ImageEntity> imageEntityList) {
        mContext = context;
        mPresenter = presenter;
        mImageEntityList = imageEntityList;
    }

    public void setImageModelList(ArrayList<ImageEntity> imageEntityList) {
        mImageEntityList = imageEntityList;
        notifyDataSetChanged();
    }

    public void addImage(ImageEntity imageEntity) {
        if (imageEntity == null) {
            throw new IllegalArgumentException("imageEntity is necessary!");
        }
        if (mImageEntityList == null) {
            mImageEntityList = new ArrayList<>();
        }
        mImageEntityList.add(imageEntity);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mImageEntityList != null ? mImageEntityList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mImageEntityList != null ? mImageEntityList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.image_grid_entry, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.image = (ImageView) view.findViewById(R.id.image_grid_entry_image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.image.setImageResource(R.drawable.placeholder);
        final ImageEntity imageEntity = mImageEntityList.get(position);
        viewHolder.image.setTag(position);
        mPresenter.getImage(imageEntity, 80, 80, new Subscriber<Bitmap>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                viewHolder.image.setImageResource(R.drawable.placeholder_error);
            }

            @Override
            public void onNext(Bitmap bitmap) {
                if ((int) viewHolder.image.getTag() == position) {
                    viewHolder.image.setImageBitmap(bitmap);
                }
            }
        });

        return view;
    }

    static class ViewHolder {
        ImageView image;
    }
}
