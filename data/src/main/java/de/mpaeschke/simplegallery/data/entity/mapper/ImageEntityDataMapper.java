package de.mpaeschke.simplegallery.data.entity.mapper;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.data.entity.ImageEntity;
import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;

/**
 * Created by markuspaeschke on 22.10.15.
 */
public class ImageEntityDataMapper {
    public ImageDomainEntity transform(ImageEntity imageEntity) {
        ImageDomainEntity imageDomainEntity = null;
        if (imageEntity != null) {
            imageDomainEntity = new ImageDomainEntity(imageEntity.getName());
            imageDomainEntity.setPath(imageEntity.getPath());
        }
        return imageDomainEntity;
    }

    public ArrayList<ImageDomainEntity> tranform(ArrayList<ImageEntity> imageEntityList) {
        ArrayList<ImageDomainEntity> imageDomainEntityList = new ArrayList<>();
        ImageDomainEntity imageDomainEntity;
        for (ImageEntity imageEntity : imageEntityList) {
            imageDomainEntity = transform(imageEntity);
            if (imageDomainEntity != null) {
                imageDomainEntityList.add(imageDomainEntity);
            }
        }
        return imageDomainEntityList;
    }
}
