package de.mpaeschke.simplegallery.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.R;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;

public class ImageGridAdapter extends BaseAdapter {
    private ArrayList<ImageEntity> mImageEntityList;
    private Context mContext;

    public ImageGridAdapter(Context context, ArrayList<ImageEntity> imageEntityList) {
        mContext = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if(convertView == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.image_grid_entry, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.headline = (TextView) view.findViewById(R.id.headline);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageEntity imageEntity = mImageEntityList.get(position);
        viewHolder.headline.setText(imageEntity.getName());
        return view;
    }

    static class ViewHolder {
        TextView headline;
    }
}
