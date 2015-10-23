package de.mpaeschke.simplegallery.domain.entity;

/**
 * Created by markuspaeschke on 16.10.15.
 */
public class ImageDomainEntity {
    private String name;
    private String path;

    public ImageDomainEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
