package com.mahesh.assigment;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.mahesh.assigment.database.ArticlesPojo;

import java.util.List;


public class CustomViewModel extends AndroidViewModel {

    private Application application;
    private DataRepostory dataRepostory;
    private LiveData<List<ArticlesPojo>> data;

    public CustomViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        dataRepostory = new DataRepostory(application);
        data = dataRepostory.getAllArticles();
        dataRepostory.populateArticles();
    }

    public LiveData<List<ArticlesPojo>> getData() {
        return data;
    }
}
