package com.gabriela.ev.data.retrofit;



import com.gabriela.ev.model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PersonajeInterface {
  @GET("Characters")
  Call<List<Personaje>> getPersonajes();

  @GET("Characters/{characterId}")
  Call<Personaje> getPersonaje(@Path("characterId") String charId);
}
