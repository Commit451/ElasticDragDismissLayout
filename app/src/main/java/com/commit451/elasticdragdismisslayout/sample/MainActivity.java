package com.commit451.elasticdragdismisslayout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.commit451.elasticdragdismisslayout.sample.adapter.GameAdapter;
import com.commit451.elasticdragdismisslayout.sample.api.ApiClient;
import com.commit451.elasticdragdismisslayout.sample.api.response.GamesResponse;
import com.commit451.elasticdragdismisslayout.sample.model.Game;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.root)
    ViewGroup mRoot;
    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;
    GameAdapter mGameAdapter;

    private GameAdapter.Listener mGameListener = new GameAdapter.Listener() {
        @Override
        public void onGameClicked(Game game) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle("ElasticDragDismissLayout");
        mToolbar.inflateMenu(R.menu.main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recyclerview:
                        Intent intent = DetailRecyclerViewActivity.newIntent(MainActivity.this);
                        startActivity(intent);
                        return true;
                }
                return false;
            }
        });
        mGameAdapter = new GameAdapter(mGameListener);
        mRecyclerView.setAdapter(mGameAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        load();
    }

    private void load() {
        ApiClient.instance().getGames().enqueue(new Callback<GamesResponse>() {
            @Override
            public void onResponse(Response<GamesResponse> response) {
                if (!response.isSuccess()) {
                    Snackbar.make(mRoot, "Something went wrong", Snackbar.LENGTH_SHORT)
                            .show();
                    return;
                }
                mGameAdapter.setGames(response.body().getGames());
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("MainActivity", t.toString());
                Snackbar.make(mRoot, "Something went wrong", Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
    }
}
