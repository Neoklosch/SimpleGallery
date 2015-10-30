package de.mpaeschke.simplegallery.data.local;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

public class ImageFolderLoaderImpl implements ImageFolderLoader {
    private final static String CAMERA_FOLDER_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera/";

    @Override
    public synchronized Observable<ArrayList<ImageEntity>> getImageList() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<ImageEntity>>() {

            @Override
            public void call(Subscriber<? super ArrayList<ImageEntity>> subscriber) {
                File cameraFolder = new File(CAMERA_FOLDER_PATH);
                ArrayList<ImageEntity> filePathList = new ArrayList<>();
                String[] fileNameList = cameraFolder.list();
                if (fileNameList != null) {
                    for (String fileName : fileNameList) {
                        final String filePath = CAMERA_FOLDER_PATH + fileName;
                        filePathList.add(new ImageEntity(fileName, filePath));
                    }
                }
                if (filePathList.size() > 0) {
                    subscriber.onNext(filePathList);
                } else {
                    subscriber.onError(new Exception("List is empty"));
                }
            }
        });
    }

    @Override
    public synchronized Observable<Bitmap> getFullSizeImage(final ImageEntity imageEntity) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {

            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                if (imageExists(imageEntity)) {
                    try {
                        File imgFile = new File(imageEntity.getPath());
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(imgFile));
                    } catch (FileNotFoundException fnfe) {
                        subscriber.onError(new Exception("file not found"));
                    } catch (OutOfMemoryError oome) {
                        subscriber.onError(new Exception("out of memory"));
                    }
                }
                if (bitmap != null) {
                    subscriber.onNext(bitmap);
                } else {
                    subscriber.onError(new Exception("file not found"));
                }
            }
        });
    }

    @Override
    public Observable<Bitmap> getScaledImage(final ImageEntity imageEntity, final int height, final int width) {
        return Observable.create(new Observable.OnSubscribe<Bitmap>() {

            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = null;
                if (imageExists(imageEntity)) {
                    try {
                        File imgFile = new File(imageEntity.getPath());
                        final int scale = calculateOptimalScale(imageEntity, height, width);
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inSampleSize = scale;
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(imgFile), null, options);
                    } catch (FileNotFoundException fnfe) {
                        subscriber.onError(new Exception("file not found"));
                    } catch (OutOfMemoryError oome) {
                        subscriber.onError(new Exception("out of memory"));
                    }
                }
                if (bitmap != null) {
                    subscriber.onNext(bitmap);
                } else {
                    subscriber.onError(new Exception("file not found"));
                }
            }
        });
    }

    @Override
    public void putImageListEntry(ImageEntity imageEntity) {

    }

    @Override
    public void putImage(String key, Bitmap bitmap) {

    }

    @Override
    public boolean imageExists(ImageEntity imageEntity) {
        File cameraFolder = new File(CAMERA_FOLDER_PATH);
        String[] fileNameList = cameraFolder.list();
        if (fileNameList != null) {
            for (String fileName : fileNameList) {
                final String filePath = CAMERA_FOLDER_PATH + fileName;
                if (filePath.equals(imageEntity.getPath())) {
                    return true;
                }
            }
        }
        return false;
    }

    private int calculateOptimalScale(final ImageEntity imageEntity, int optimalHeight, int optimalWidth) throws FileNotFoundException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(imageEntity.getPath()), null, options);

        int scale = 1;
        while (options.outWidth / scale / 2 >= optimalWidth &&
                options.outHeight / scale / 2 >= optimalHeight) {
            scale *= 2;
        }
        return scale;
    }
}
