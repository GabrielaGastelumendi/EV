package com.gabriela.ev.data.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHelper {
  public static Retrofit instance;
  public static PersonajeInterface personajeIntService;

  public RetrofitHelper() {
  }

  public static Retrofit getInstance() {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://thronesapi.com/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getLogginBuilder().build())
            .build();
    instance = retrofit;
    return instance;

  }

  ;

  public static PersonajeInterface getService() {
    if (personajeIntService == null) {
      personajeIntService = getInstance().create(PersonajeInterface.class);
    }
    return personajeIntService;
  }


  public static OkHttpClient.Builder getLogginBuilder() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.level(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder builder = new OkHttpClient.Builder();
    builder.addInterceptor(interceptor);
    return builder;
  }


}
