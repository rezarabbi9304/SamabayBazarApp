package com.example.samabaybazar.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.samabaybazar.ProductListModel;

@Database(entities = ProductListModel.class,version = 1)
public  abstract class AppDataBase extends RoomDatabase {

    public abstract ProductDao productDao();

    private static AppDataBase INSTANCE;

    public static AppDataBase getINSTANCE(Context context) {

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"DB_anim")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
