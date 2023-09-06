package com.gabriela.ev.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriela.ev.DetallePersonajeActivity;
import com.gabriela.ev.data.retrofit.PersonajeInterface;
import com.gabriela.ev.data.retrofit.RetrofitHelper;
import com.gabriela.ev.databinding.FragmentPersonajervBinding;
import com.gabriela.ev.model.Personaje;

import java.util.List;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RVPersonajesAdapter extends RecyclerView.Adapter<RVPersonajesAdapter.ResumeVH> {


  private List<Personaje> personajes;

  public RVPersonajesAdapter(List<Personaje> personajes) {
    this.personajes = personajes;
  }


  @NonNull
  @Override
  public ResumeVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    FragmentPersonajervBinding binding = FragmentPersonajervBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

    return new ResumeVH(binding);
  }


  @Override
  public void onBindViewHolder(@NonNull ResumeVH holder, @SuppressLint("RecyclerView") int position) {
    holder.bind(personajes.get(position));

    holder.itemView.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View view) {

        PersonajeInterface PersonajeService = RetrofitHelper.getService();

        String pjId = personajes.get(position).getId(); // 1
        String pjFirstName = personajes.get(position).getFirstName(); // Daenerys
        String pjLastName = personajes.get(position).getLastName(); //  Targe
        String pjFullName = personajes.get(position).getFullName(); // Dnae
        String pjTitle = personajes.get(position).getTitle();
        String pjFamily = personajes.get(position).getFamily();
        String pjImage = personajes.get(position).getImageUrl();

        PersonajeService.getPersonaje(pjId).enqueue(new Callback<Personaje>() {
          @Override
          public void onResponse(Call<Personaje> call, Response<Personaje> response) {
            if (response.isSuccessful()) {
              Intent intent = new Intent(view.getContext(), DetallePersonajeActivity.class);
              intent.putExtra(DetallePersonajeActivity.FIRSTNAME, pjFirstName);
              intent.putExtra(DetallePersonajeActivity.LASTNAME, pjLastName);
              intent.putExtra(DetallePersonajeActivity.FULLNAME, pjFullName);
              intent.putExtra(DetallePersonajeActivity.TITLE, pjTitle);
              intent.putExtra(DetallePersonajeActivity.FAMILY, pjFamily);
              intent.putExtra(DetallePersonajeActivity.IMAGE, pjImage);
              view.getContext().startActivity(intent);
            }
          }

          @Override
          public void onFailure(Call<Personaje> call, Throwable t) {

          }
        });
      }


    });
  }


  @Override
  public int getItemCount() {
    return personajes.size();
  }

  class ResumeVH extends RecyclerView.ViewHolder {
    private FragmentPersonajervBinding binding;

    public ResumeVH(FragmentPersonajervBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    public void bind(Personaje personaje) {

      binding.txtNombre.setText(personaje.getFirstName());
      ImageLoader imageLoader = Coil.imageLoader(binding.getRoot().getContext());
      ImageRequest request = new ImageRequest.Builder(binding.getRoot().getContext()).data(personaje.getImageUrl()).crossfade(true).target(binding.igMainFg).build();
      imageLoader.enqueue(request);
    }
  }
}
