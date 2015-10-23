package de.mpaeschke.simplegallery.presentation.model.mapper;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import de.mpaeschke.simplegallery.presentation.model.entity.ImageEntity;

/**
 * Created by markuspaeschke on 23.10.15.
 */
public class ImageEntityMapper {
    public ImageEntity transform(ImageDomainEntity imageDomainEntity) {
        if (imageDomainEntity == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }

        ImageEntity imageEntity = new ImageEntity(imageDomainEntity.getName());
        imageEntity.setPath(imageDomainEntity.getPath());

        return imageEntity;
    }

    public ArrayList<ImageEntity> transform(ArrayList<ImageDomainEntity> imageDomainEntityList) {
        ArrayList<ImageEntity> imageEntityList = new ArrayList<>();

        if (imageDomainEntityList != null && !imageDomainEntityList.isEmpty()) {
            for (ImageDomainEntity imageDomainEntity : imageDomainEntityList) {
                imageEntityList.add(transform(imageDomainEntity));
            }
        }

        return imageEntityList;
    }
}
