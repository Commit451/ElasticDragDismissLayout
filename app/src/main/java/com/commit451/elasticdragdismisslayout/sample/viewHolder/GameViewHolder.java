package com.commit451.elasticdragdismisslayout.sample.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.commit451.elasticdragdismisslayout.sample.R;
import com.commit451.elasticdragdismisslayout.sample.model.Game;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Shows the games
 */
public class GameViewHolder extends RecyclerView.ViewHolder {

    public static GameViewHolder inflate(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_game, parent, false);
        return new GameViewHolder(view);
    }

    @Bind(R.id.image)
    ImageView mImage;
    @Bind(R.id.text)
    TextView mText;

    public GameViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

    public void bind(Game game) {
        if (game.getImage() != null) {
            Glide.with(itemView.getContext())
                    .load(game.getImage().getSuperUrl())
                    .into(mImage);
        } else {
            mImage.setImageBitmap(null);
        }
        mText.setText(game.getName());
    }
}
