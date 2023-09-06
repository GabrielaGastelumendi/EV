package com.gabriela.ev;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.gabriela.ev.databinding.ActivityFavBinding;
import com.gabriela.ev.fragments.MainViewModel;
import com.gabriela.ev.model.PersonajeEntity;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class FavActivity extends AppCompatActivity {

  private ActivityFavBinding binding;
  private LinearLayout svPersonajes;
  private MainViewModel mainViewModel;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
    binding = ActivityFavBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());

    svPersonajes = findViewById(R.id.svPersonajesFav);


    mainViewModel.getAllPersonajes().observe(this, personajesEntities -> {
      svPersonajes.removeAllViews();

      for (PersonajeEntity pj : personajesEntities) {
        View movieView = getLayoutInflater().inflate(R.layout.fav_item_db, svPersonajes, false);
        ImageView imgFav = movieView.findViewById(R.id.imgFav);
        TextView txtTitleFav = movieView.findViewById(R.id.txtFullNameFav);

        txtTitleFav.setText(pj.getFullName());

        String imgURL = pj.getImageUrl();
        ImageLoader imageLoader = Coil.imageLoader(binding.getRoot().getContext());
        ImageRequest request = new ImageRequest.Builder(binding.getRoot().getContext()).data(imgURL).crossfade(true).target(imgFav).build();
        imageLoader.enqueue(request);



        svPersonajes.addView(movieView);
      }
    });

  }


}
