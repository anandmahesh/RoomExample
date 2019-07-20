package com.mahesh.assigment.database;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.mahesh.assigment.utils.Common;
import org.json.JSONObject;


@Entity(tableName = "articlesPojo")
public class ArticlesPojo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "rowid")
    private long id;

    private String author;

    private String title;

    private String date;

    private String description;

    private String content;

    private String publishedAt;

    private String url;

    private String urlToImage;

    public ArticlesPojo() {

    }

    protected ArticlesPojo(Parcel in) {
        id = in.readLong();
        author = in.readString();
        title = in.readString();
        date = in.readString();
        description = in.readString();
        content = in.readString();
        publishedAt = in.readString();
        url = in.readString();
        urlToImage = in.readString();
    }

    /*public ArticlesPojo(String author, String title, String date, String description, String content, String publishedAt, String url, String urlToImage) {
        this.author = author;
        this.title = title;
        this.date = date;
        this.description = description;
        this.content = content;
        this.publishedAt = publishedAt;
        this.url = url;
        this.urlToImage = urlToImage;
    }*/

    public static ArticlesPojo createArticle(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        ArticlesPojo articlesPojo = new ArticlesPojo();
        articlesPojo.setAuthor(jsonObject.optString("author", ""));
        articlesPojo.setTitle(jsonObject.optString("title", ""));
        articlesPojo.setDescription(jsonObject.optString("description", ""));
        articlesPojo.setUrl(jsonObject.optString("url", ""));
        articlesPojo.setUrlToImage(jsonObject.optString("urlToImage", ""));
        articlesPojo.setDate(jsonObject.optString("publishedAt", ""));
        articlesPojo.setContent(jsonObject.optString("content", ""));
        return articlesPojo;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        Common.writeMessage(message, "ArticlesPojo");
        Common.writeMessage(message, "id: " + id);
        Common.writeMessage(message, "author: " + author);
        Common.writeMessage(message, "title: " + title);
        Common.writeMessage(message, "description: " + description);
        Common.writeMessage(message, "content: " + content);
        Common.writeMessage(message, "publishedAt: " + publishedAt);
        Common.writeMessage(message, "url: " + url);
        Common.writeMessage(message, "urlToImage: " + urlToImage);
        return message.toString();
    }

    public static final Creator<ArticlesPojo> CREATOR = new Creator<ArticlesPojo>() {
        @Override
        public ArticlesPojo createFromParcel(Parcel in) {
            return new ArticlesPojo(in);
        }

        @Override
        public ArticlesPojo[] newArray(int size) {
            return new ArticlesPojo[size];
        }
    };

    public static Creator<ArticlesPojo> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(date);
        parcel.writeString(description);
        parcel.writeString(content);
        parcel.writeString(publishedAt);
        parcel.writeString(url);
        parcel.writeString(urlToImage);
    }

}
