package de.mpaeschke.simplegallery.presentation.model.entity;

/**
 * Class that represents an image in the presentation layer.
 */
public class ImageEntity {
    private String name;
    private String path;

    public ImageEntity(String name) {
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
