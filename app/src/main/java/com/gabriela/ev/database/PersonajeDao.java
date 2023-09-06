package com.gabriela.ev.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gabriela.ev.model.PersonajeEntity;

import java.util.List;
@Dao
public interface PersonajeDao {

  @Insert
  public void addPersonaje(PersonajeEntity personaje);

  @Query("SELECT * FROM personajes")
  public LiveData<List<PersonajeEntity>> getAll();
}
