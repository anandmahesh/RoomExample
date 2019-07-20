package com.mahesh.assigment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.android.volley.toolbox.ImageLoader;
import com.mahesh.assigment.dataadapter.CustomAdapter;
import com.mahesh.assigment.database.ArticlesPojo;
import com.mahesh.assigment.networkcomm.CustomVolleyRequest;
import java.util.List;


public class MainActivity extends AppCompatActivity implements CustomAdapter.OnClickEvent{

    private ImageLoader imageLoader;
    private CustomViewModel customViewModel;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageLoader = CustomVolleyRequest.getInstance(this.getApplicationContext()).getImageLoader();
        customViewModel = ViewModelProviders.of(this).get(CustomViewModel.class);

        customAdapter = new CustomAdapter(this);
        layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView = findViewById(R.id.recycle_container);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(customAdapter);

        customViewModel.getData().observe(this, new Observer<List<ArticlesPojo>>() {
            @Override
            public void onChanged(@Nullable final List<ArticlesPojo> words) {
                customAdapter.setArticlesData(words);
            }
        });
    }

    @Override
    public void recycleItemClicked(ArticlesPojo articlesPojo) {
        Intent intent = new Intent(this, ArticleDetailActivity.class);
        intent.putExtra("article",articlesPojo);
        startActivity(intent);
    }
}
