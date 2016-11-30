package com.commit451.elasticdragdismisslayout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.root)
    ViewGroup mRoot;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    CheeseAdapter mCheeseAdapter;

    private CheeseAdapter.Listener mCheeseAdapterListener = new CheeseAdapter.Listener() {
        @Override
        public void onItemClicked(Cheese cheese) {
            startActivity(DetailActivity.newIntent(MainActivity.this, cheese));
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
        mCheeseAdapter = new CheeseAdapter(MainActivity.this, mCheeseAdapterListener);
        mRecyclerView.setAdapter(mCheeseAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        load();
    }

    private void load() {
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            cheeses.add(Cheeses.getRandomCheese());
        }
        mCheeseAdapter.setData(cheeses);
    }
}
