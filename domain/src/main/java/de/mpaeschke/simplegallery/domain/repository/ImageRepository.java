package de.mpaeschke.simplegallery.domain.repository;

import java.util.ArrayList;

import de.mpaeschke.simplegallery.domain.entity.ImageDomainEntity;
import rx.Observable;
import rx.Subscriber;

/**
 * The repository interface is used to handle the communication between the domain layer and the data layer.
 */
public interface ImageRepository {
    Observable<ArrayList<ImageDomainEntity>> getImageList();
}
