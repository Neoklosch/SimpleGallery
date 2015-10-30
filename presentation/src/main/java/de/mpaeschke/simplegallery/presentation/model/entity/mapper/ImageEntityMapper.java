package de.mpaeschke.simplegallery.presentation.model.entity.mapper;

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
        return new ImageEntity(imageDomainEntity.getName(), imageDomainEntity.getPath());
    }

    public ImageDomainEntity transform(ImageEntity imageEntity) {
        if (imageEntity == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        return new ImageDomainEntity(imageEntity.getName(), imageEntity.getPath());
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
