package com.commit451.elasticdragdismisslayout.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.commit451.elasticdragdismisslayout.sample.model.Game;
import com.commit451.elasticdragdismisslayout.sample.viewHolder.GameViewHolder;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Adapter for the games
 */
public class GameAdapter extends RecyclerView.Adapter<GameViewHolder> {

    public interface Listener {
        void onGameClicked(Game game);
    }
    private Listener mListener;
    private ArrayList<Game> mGames;

    private final View.OnClickListener mItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mListener.onGameClicked((Game) v.getTag());
        }
    };

    public GameAdapter(Listener listener) {
        mGames = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GameViewHolder holder = GameViewHolder.inflate(parent);
        holder.itemView.setOnClickListener(mItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game game = mGames.get(position);
        holder.bind(game);
        holder.itemView.setTag(game);
    }

    @Override
    public int getItemCount() {
        return mGames.size();
    }

    public void setGames(Collection<Game> games) {
        mGames.clear();
        if (games != null) {
            mGames.addAll(games);
        }
        notifyDataSetChanged();
    }
}
