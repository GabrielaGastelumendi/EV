package com.gabriela.ev;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gabriela.ev.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {


  private ActivityLoginBinding binding;
  public static String SESSION_PREFERENCE = "SESSION_PREFERENCE";
  public static String SESSION_ACTIVATED = "SESSION_ACTIVATED";


  private SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    sharedPreferences = getSharedPreferences(SESSION_PREFERENCE, MODE_PRIVATE);
    binding.btnLogin.setOnClickListener(view -> {
      sharedPreferences.edit().putBoolean(SESSION_ACTIVATED, true).apply();
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();


    });


    binding.tilEmail.getEditText().addTextChangedListener(new TextWatcher() {

      @Override
      public void onTextChanged(CharSequence c, int start, int before, int cnt) {
        Boolean isOk = isCredentialsValidate(c.toString(), binding.tilPassword.getEditText().getText().toString());
        binding.btnLogin.setEnabled(isOk);
      }

      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });


    binding.tilPassword.getEditText().addTextChangedListener(new TextWatcher() {


      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        Boolean isOk = isCredentialsValidate(binding.tilEmail.getEditText().getText().toString(), charSequence.toString());
        binding.btnLogin.setEnabled(isOk);

      }

      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

      }

      @Override
      public void afterTextChanged(Editable editable) {

      }
    });
  }

  private Boolean isCredentialsValidate(String email, String password) {
    Boolean isPasswordOk = false;
    Boolean isEmailOk = false;
    if (email.equals("ejemploe@idat.edu.pe") && password.length() >= 8) {
      return true;
    }
   else {
      return false;
    }

  }
}
