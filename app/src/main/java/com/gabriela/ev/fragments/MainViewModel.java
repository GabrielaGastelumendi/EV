package com.gabriela.ev.fragments;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.gabriela.ev.database.PersonajeRepository;
import com.gabriela.ev.model.Personaje;
import com.gabriela.ev.model.PersonajeEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
  private PersonajeRepository repository;
  private LiveData<List<PersonajeEntity>> allPersonajes;
  public LiveData<List<PersonajeEntity>> listLiveData = new MutableLiveData<List<PersonajeEntity>>();

  public MainViewModel(@NonNull Application application) {
    super(application);
    repository = new PersonajeRepository(application);
    allPersonajes = repository.getAll();
  }

  public void addPersonaje(Personaje personaje){
    PersonajeEntity personajeEntity = new PersonajeEntity();
    personajeEntity.setFullName(personaje.getFullName());
    personajeEntity.setImageUrl(personaje.getImageUrl());
    repository.addPersonaje(personajeEntity);
  }

  public LiveData<List<PersonajeEntity>> getAllPersonajes() {
    return allPersonajes;
  }

  public void getPersonajes(){
    listLiveData = repository.getAll();
  }

  public class MainViewModelFactory implements ViewModelProvider.Factory{
    private final Application application;
    public MainViewModelFactory(Application myApplication){
      application = myApplication;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      return (T)  new MainViewModel(application);
    }
  }
}
