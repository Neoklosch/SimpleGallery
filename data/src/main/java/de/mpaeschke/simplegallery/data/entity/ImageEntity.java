package de.mpaeschke.simplegallery.data.entity;

/**
 * Created by markuspaeschke on 16.10.15.
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
