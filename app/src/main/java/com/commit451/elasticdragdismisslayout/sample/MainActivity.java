package com.commit451.elasticdragdismisslayout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    CheeseAdapter cheeseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("ElasticDragDismissLayout");
        toolbar.inflateMenu(R.menu.main);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
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
        cheeseAdapter = new CheeseAdapter(MainActivity.this, new CheeseAdapter.Listener() {
            @Override
            public void onItemClicked(Cheese cheese) {
                startActivity(DetailActivity.newIntent(MainActivity.this, cheese));
            }
        });
        recyclerView.setAdapter(cheeseAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        load();
    }

    private void load() {
        ArrayList<Cheese> cheeses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            cheeses.add(Cheeses.getRandomCheese());
        }
        cheeseAdapter.setData(cheeses);
    }
}
