package de.mpaeschke.simplegallery.data.local;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import rx.Observable;
import rx.Subscriber;

public class ImageFolderLoaderImpl implements ImageFolderLoader {
    @Override
    public synchronized Observable<ArrayList<ImageEntity>> get() {
        return Observable.create(new Observable.OnSubscribe<ArrayList<ImageEntity>>() {

            @Override
            public void call(Subscriber<? super ArrayList<ImageEntity>> subscriber) {
                String cameraFolderPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera/";
                File cameraFolder = new File(cameraFolderPath);
                Log.v("#### Keks ####", cameraFolder.isDirectory() + "");
                Log.v("#### Keks ####", cameraFolder.getAbsolutePath());
                ArrayList<ImageEntity> filePathList = new ArrayList<>();
                String[] fileNameList = cameraFolder.list();
                if (fileNameList != null) {
                    for (String fileName : fileNameList) {
                        filePathList.add(new ImageEntity("", cameraFolder + "/" + fileName));
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
    public void put(ImageEntity imageEntity) {

    }
}
