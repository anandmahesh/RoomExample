package com.mahesh.assigment.dataadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.mahesh.assigment.R;
import com.mahesh.assigment.database.ArticlesPojo;
import com.mahesh.assigment.networkcomm.CustomVolleyRequest;
import com.mahesh.assigment.utils.Common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public static final String TAG_CUSTOM_ADAPTER = "CustomAdapter";

    private List<ArticlesPojo> dataPojo;
    private Context context;
    private ImageLoader imageLoader;
    private DateFormat oldDateFormate, newDateFormate;
    private final LayoutInflater mInflater;
    private OnClickEvent onClickEvent;

    public CustomAdapter(Context context) {
        this.context = context;
        onClickEvent = (OnClickEvent)context;
        mInflater = LayoutInflater.from(context);
        imageLoader = CustomVolleyRequest.getInstance(context.getApplicationContext()).getImageLoader();
        oldDateFormate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        newDateFormate = new SimpleDateFormat("dd MMMM yyyy");

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView authorTextView, dateTextView, titleTextView, fullDescriptionTextview;
        private ImageView urlImage;
        private ArticlesPojo articlesPojo;

        public void setArticlesPojo(ArticlesPojo articlesPojo) {
            this.articlesPojo = articlesPojo;
        }

        public MyViewHolder(View v) {
            super(v);
            authorTextView = v.findViewById(R.id.author);
            dateTextView = v.findViewById(R.id.date_textview);
            titleTextView = v.findViewById(R.id.title_textview);
            fullDescriptionTextview = v.findViewById(R.id.full_description_textview);
            urlImage = v.findViewById(R.id.url_image);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickEvent.recycleItemClicked(articlesPojo);
                }
            });
        }
    }

    public void setArticlesData(List<ArticlesPojo> dataPojo) {
        this.dataPojo = dataPojo;
        notifyDataSetChanged();
    }

    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.article_card, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ArticlesPojo articlesPojo = dataPojo.get(position);
        holder.setArticlesPojo(articlesPojo);
        holder.authorTextView.setText(articlesPojo.getAuthor());
        holder.dateTextView.setText(Common.getFormateDate(articlesPojo.getDate(),oldDateFormate,newDateFormate));
        holder.titleTextView.setText(articlesPojo.getTitle());
        holder.fullDescriptionTextview.setText(articlesPojo.getDescription());
        imageLoader.get(articlesPojo.getUrlToImage(), ImageLoader.getImageListener(holder.urlImage,
                R.drawable.sample_image, R.drawable.sample_image));

    }



    @Override
    public int getItemCount() {
        if (dataPojo != null) {
            return dataPojo.size();
        } else {
            return 0;
        }
    }

    public interface OnClickEvent{
        void recycleItemClicked(ArticlesPojo articlesPojo);
    }
}