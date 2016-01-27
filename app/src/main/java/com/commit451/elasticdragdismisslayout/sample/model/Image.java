package com.commit451.elasticdragdismisslayout.sample.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

/**
 * Image
 */
@Parcel
@JsonObject
public class Image {

    @JsonField(name = "icon_url")
    String mIconUrl;
    @JsonField(name = "medium_url")
    String mMediumUrl;
    @JsonField(name = "screen_url")
    String mScreenUrl;
    @JsonField(name = "small_url")
    String mSmallUrl;
    @JsonField(name = "super_url")
    String mSuperUrl;
    @JsonField(name = "thumb_url")
    String mThumbUrl;
    @JsonField(name = "tiny_url")
    String mTinyUrl;
    @JsonField(name = "tags")
    String mTags;

    protected Image() {
        //for parceler
    }

    public String getSuperUrl() {
        return mSuperUrl;
    }
}
