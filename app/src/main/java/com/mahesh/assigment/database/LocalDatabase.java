package com.mahesh.assigment.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.mahesh.assigment.utils.Common;


@Database(entities = {ArticlesPojo.class},version = 1)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract ArticlesPojoDao getdataPojoDao();

    private static volatile LocalDatabase INSTANCE;

    public static LocalDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocalDatabase.class, Common.APP_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}

