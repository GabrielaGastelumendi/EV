package com.gabriela.ev.database;



import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


import com.gabriela.ev.model.PersonajeEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {PersonajeEntity.class}, version = 1)
public abstract class DB extends RoomDatabase{
  public abstract PersonajeDao personajeDao();
  private static volatile DB db;

  public static final ExecutorService dataBaseWriteExecutor = Executors.newFixedThreadPool(4);

  public static DB getInstance(Context context){
    if (db == null){
      synchronized(DB.class){
        if (db== null){
          db = Room.databaseBuilder(context.getApplicationContext(), DB.class, "db-game-of-thrones").build();

        }

      }
    }
    return db;
  }

}
