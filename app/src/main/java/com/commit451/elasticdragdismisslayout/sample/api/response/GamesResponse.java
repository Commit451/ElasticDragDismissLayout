package com.commit451.elasticdragdismisslayout.sample.api.response;

import com.bluelinelabs.logansquare.annotation.JsonField;
import com.bluelinelabs.logansquare.annotation.JsonObject;
import com.commit451.elasticdragdismisslayout.sample.model.Game;

import java.util.List;

/**
 * A response when requesting a list of games
 */
@JsonObject
public class GamesResponse {

    @JsonField(name = "error")
    String mError;
    @JsonField(name = "results")
    List<Game> mGames;

    public List<Game> getGames() {
        return mGames;
    }
}
