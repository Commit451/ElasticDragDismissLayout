package com.commit451.elasticdragdismisslayout.sample;

import java.io.Serializable;

/**
 * A fake model to show usage
 * Created by John on 11/24/15.
 */
public class Cheese implements Serializable {

    int mDrawable;
    String mName;

    public Cheese(int drawable, String name) {
        mDrawable = drawable;
        mName = name;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }
}
