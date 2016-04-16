package com.rottygames.framework.animation;

/**
 * Created by stephen on 08/01/16.
 */
import android.graphics.Bitmap;

public class Frame {
    private Bitmap image;
    private double duration;

    public Frame(Bitmap image, double duration) {
        this.image = image;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public Bitmap getImage() {
        return image;
    }
}
