package com.gabriela.ev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import com.gabriela.ev.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {
  private static final int delay = 5000;

  private ActivitySplashBinding binding;
  private SharedPreferences sharedPreferences;



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivitySplashBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    sharedPreferences = getSharedPreferences(LoginActivity.SESSION_PREFERENCE, MODE_PRIVATE);
    setContentView(binding.getRoot());
    boolean isSessionActivated = sharedPreferences.getBoolean(LoginActivity.SESSION_ACTIVATED, false);

    if (isSessionActivated){
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();
    }
    else {
      new Handler().postDelayed(new Runnable() {
        @Override
        public void run() {
          Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
          startActivity(intent);
          finish();
        }
      }, delay);
    }



  }





}
