package com.commit451.elasticdragdismisslayout.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar mToolbar;

    @OnClick(R.id.nestedscrollview)
    void onClickText() {
        Intent intent = DetailActivity.newIntent(MainActivity.this);
        startActivity(intent);
    }

    @OnClick(R.id.recyclerview)
    void onRecyclerViewExampleClick() {
        Intent intent = DetailRecyclerViewActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar.setTitle("ElasticDragDismissLayout");
    }
}
