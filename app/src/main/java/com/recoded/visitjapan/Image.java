package com.recoded.visitjapan;

/**
 * Created by wisam on Oct 17 17.
 */

public class Image {

    private int res;
    private int id;
    private String tag;

    public Image(String id, int src, String tag) {
        this.res = src;
        this.tag = tag;
        this.id = Integer.parseInt(id);
    }

    public int getResId(){
        return res;
    }

    public String getTag(){
        return tag;
    }

    public int getId() {
        return id;
    }
}
