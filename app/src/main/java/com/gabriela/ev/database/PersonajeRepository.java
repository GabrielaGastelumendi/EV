package com.gabriela.ev.database;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.gabriela.ev.model.PersonajeEntity;

import java.util.List;

public class PersonajeRepository {

  private PersonajeDao dao;
  private DB db;

  public PersonajeRepository(Application application) {
    db = DB.getInstance(application);
    dao = db.personajeDao();
  }

  public void addPersonaje(PersonajeEntity personajeEntity){
    DB.dataBaseWriteExecutor.execute(() ->
            dao.addPersonaje(personajeEntity));
  }

  public LiveData<List<PersonajeEntity>> getAll(){
    return dao.getAll();
  }
}
