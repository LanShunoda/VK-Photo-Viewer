package com.plorial.vkphotoviewer;

/**
 * Created by plorial on 3/23/16.
 */
public class Album {

    private int id;
    private String title;
    private int size;
    private String thumbSrc;

    public Album(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setThumbSrc(String thumbSrc) {
        this.thumbSrc = thumbSrc;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getSize() {
        return size;
    }

    public String getThumbSrc() {
        return thumbSrc;
    }
}
