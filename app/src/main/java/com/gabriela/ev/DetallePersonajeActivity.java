package com.gabriela.ev;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gabriela.ev.database.PersonajeRepository;
import com.gabriela.ev.databinding.ActivityDetalleBinding;
import com.gabriela.ev.fragments.MainViewModel;
import com.gabriela.ev.model.Personaje;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class DetallePersonajeActivity extends AppCompatActivity {

  private ActivityDetalleBinding binding;
  private MainViewModel mainViewModel;
  private PersonajeRepository repository;

  public static final String FIRSTNAME = "firstname";
  public static final String LASTNAME = "lastname";
  public static final String FULLNAME = "fullname";
  public static final String TITLE = "title";
  public static final String FAMILY = "family";
  public static final String IMAGE = "image";

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    binding = ActivityDetalleBinding.inflate(getLayoutInflater());
    mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
    setContentView(binding.getRoot());

    String nombre = getIntent().getStringExtra(FIRSTNAME);
    String apellido = getIntent().getStringExtra(LASTNAME);
    String nombreCompleto = getIntent().getStringExtra(FULLNAME);
    String titulo = getIntent().getStringExtra(TITLE);
    String familia = getIntent().getStringExtra(FAMILY);
    String image = getIntent().getStringExtra(IMAGE);



    binding.txtFirstName.setText(nombre);
    binding.txtLastName.setText(apellido);
    binding.txtFullName.setText(nombreCompleto);
    binding.txtTitle.setText(titulo);
    binding.txtFamily.setText(familia);

    ImageLoader imageLoader = Coil.imageLoader(binding.getRoot().getContext());
    ImageRequest request = new ImageRequest.Builder(binding.getRoot().getContext()).data(image).crossfade(true).target(binding.imgPersonajeDetalle).build();
    imageLoader.enqueue(request);

    Personaje personaje = new Personaje();
    personaje.setFullName(nombreCompleto);
    personaje.setImageUrl(image);

    binding.favButton.setOnClickListener(view -> {
      mainViewModel.addPersonaje(personaje);
      Toast.makeText(this, "¡Agregaste una personaje a favoritos!", Toast.LENGTH_SHORT).show();
    });




  }

}
