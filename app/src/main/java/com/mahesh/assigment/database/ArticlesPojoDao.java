package com.mahesh.assigment.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticlesPojoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<ArticlesPojo> articlesPojo);

    @Query("SELECT * FROM articlesPojo")
    LiveData<List<ArticlesPojo>> getAllArticles();

    @Query("DELETE FROM articlesPojo")
    void deleteAll();
}
