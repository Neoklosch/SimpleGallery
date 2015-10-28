package de.mpaeschke.simplegallery.presentation.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
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
            viewHolder.image = (ImageView) view.findViewById(R.id.image_grid_entry_image);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        ImageEntity imageEntity = mImageEntityList.get(position);
        File imgFile = new  File(imageEntity.getPath());
        if (imgFile.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            viewHolder.image.setImageBitmap(bitmap);
        } else {
            viewHolder.image.setImageResource(R.drawable.placeholder_large);
        }
        return view;
    }

    static class ViewHolder {
        ImageView image;
    }
}
