package com.mahesh.assigment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.google.android.material.button.MaterialButton;
import com.mahesh.assigment.database.ArticlesPojo;
import com.mahesh.assigment.networkcomm.CustomVolleyRequest;
import com.mahesh.assigment.utils.Common;

import java.text.SimpleDateFormat;


public class ArticleDetailActivity extends AppCompatActivity {


    private AppCompatTextView authorTextView, dateTextView,
            titleTextView, fullDescriptionTextview, contentDescTextView;
    private ImageView urlImage;
    private MaterialButton materialButton;
    private ImageLoader imageLoader;

    private ArticlesPojo articlesPojo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_screen);

        imageLoader = CustomVolleyRequest.getInstance(getApplicationContext()).getImageLoader();

        authorTextView = findViewById(R.id.author);
        dateTextView = findViewById(R.id.date_textview);
        titleTextView = findViewById(R.id.title_textview);
        fullDescriptionTextview = findViewById(R.id.full_description_textview);
        contentDescTextView = findViewById(R.id.content_textview);
        urlImage = findViewById(R.id.url_image);
        materialButton = findViewById(R.id.link_open);

        articlesPojo = getIntent().getParcelableExtra("article");

        setArticle();
    }

    private void setArticle() {
        authorTextView.setText(articlesPojo.getAuthor());
        dateTextView.setText(Common.getFormateDate(articlesPojo.getDate(),
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"),
                new SimpleDateFormat("dd MMMM yyyy")));
        titleTextView.setText(articlesPojo.getTitle());
        fullDescriptionTextview.setText(articlesPojo.getDescription());
        contentDescTextView.setText(articlesPojo.getContent());
        imageLoader.get(articlesPojo.getUrlToImage(), ImageLoader.getImageListener(urlImage,
                R.drawable.sample_image, R.drawable.sample_image));
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(articlesPojo.getUrl()));
                startActivity(intent);
            }
        });
    }
}
