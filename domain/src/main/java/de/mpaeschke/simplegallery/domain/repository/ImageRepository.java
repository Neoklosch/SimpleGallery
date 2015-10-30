package de.mpaeschke.simplegallery.domain.repository;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import rx.Observable;

/**
 * The repository interface is used to handle the communication between the domain layer and the data layer.
 */
public interface ImageRepository {
    Observable<ArrayList<ImageDomainEntity>> getImageList();
    Observable<Object> getFullSizeImage(ImageDomainEntity imageDomainEntity);
    Observable<Object> getScaledImage(ImageDomainEntity imageDomainEntity, int height, int width);
}
