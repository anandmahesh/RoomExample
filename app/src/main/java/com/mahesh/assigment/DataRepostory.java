package com.mahesh.assigment;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mahesh.assigment.database.ArticlesPojo;
import com.mahesh.assigment.database.ArticlesPojoDao;
import com.mahesh.assigment.database.LocalDatabase;
import com.mahesh.assigment.networkcomm.CustomVolleyRequest;
import com.mahesh.assigment.utils.Common;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DataRepostory {

    private final String TAG_DATREPOSTORY = "Data Repository";

    private LocalDatabase localDatabase;
    private ArticlesPojoDao articlesPojoDao;
    private LiveData<List<ArticlesPojo>> dataPojo;
    private Application application;

    public DataRepostory(Application application) {
        this.application = application;
        localDatabase = LocalDatabase.getDatabase(application);
        articlesPojoDao = localDatabase.getdataPojoDao();
        dataPojo = articlesPojoDao.getAllArticles();
    }

    public LiveData<List<ArticlesPojo>> getAllArticles() {
        return dataPojo;
    }

    public void populateArticles() {
        fetchDataFromServer();
    }

    private void fetchDataFromServer() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, Common.URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        if (response == null) {
                            Log.i(TAG_DATREPOSTORY, "Response NULL");
                            return;
                        }
                        Log.i("TAG", response.toString());
                        insertArticles(parsedArticles(response));
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.i("onErrorResponse", error.getMessage());
                    }
                });
        CustomVolleyRequest.getInstance(application).addToRequestQueue(jsonObjectRequest);
    }

    private List<ArticlesPojo> parsedArticles(JSONObject response) {
        List<ArticlesPojo> articlesPojos = new ArrayList<>();
        try {
            JSONArray jsonArray = response.optJSONArray("articles");
            if (jsonArray == null) {
                Log.i(TAG_DATREPOSTORY, "No Articles Present");
                return articlesPojos;
            }
            for (int i = 0; i < jsonArray.length(); i++) {
                ArticlesPojo articlesPojo = ArticlesPojo.createArticle(jsonArray.getJSONObject(i));
                if (articlesPojo != null)
                    articlesPojos.add(articlesPojo);
            }
        } catch (Exception e) {
            Log.e(TAG_DATREPOSTORY, "parsedArticles", e);
        }
        return articlesPojos;
    }

    public void insertArticles(List<ArticlesPojo> articlesPojos) {
        new insertAsyncTask(articlesPojoDao, articlesPojos).execute();
    }

    private static class insertAsyncTask extends AsyncTask<Void, Void, Void> {
        private ArticlesPojoDao mAsyncTaskDao;
        private List<ArticlesPojo> articlesPojos;

        insertAsyncTask(ArticlesPojoDao dao, List<ArticlesPojo> articlesPojos) {
            mAsyncTaskDao = dao;
            this.articlesPojos = articlesPojos;
        }

        @Override
        protected Void doInBackground(Void... params) {
            mAsyncTaskDao.deleteAll();
            mAsyncTaskDao.insert(articlesPojos);
            return null;
        }
    }
}
