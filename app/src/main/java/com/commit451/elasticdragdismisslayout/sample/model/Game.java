package com.commit451.elasticdragdismisslayout.sample.model;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;

import org.parceler.Parcel;

import java.util.List;

/**
 * A game!
 */
@Parcel
@JsonObject
public class Game {

    @JsonField(name = "deck")
    String mDeck;
    @JsonField(name = "description")
    String mDescription;
    @JsonField(name = "id")
    int mId;
    @JsonField(name = "image")
    Image mImage;
    @JsonField(name = "name")
    String mName;
    @JsonField(name = "images")
    List<Image> mImages;

    protected Game() {
        //for parceler
    }

    public String getDeck() {
        return mDeck;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getId() {
        return mId;
    }

    public Image getImage() {
        return mImage;
    }

    public String getName() {
        return mName;
    }

    public List<Image> getImages() {
        return mImages;
    }
}
