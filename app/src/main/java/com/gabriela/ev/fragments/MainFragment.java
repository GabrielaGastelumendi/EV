package com.gabriela.ev.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabriela.ev.R;
import com.gabriela.ev.data.retrofit.RetrofitHelper;
import com.gabriela.ev.databinding.FragmentMainBinding;
import com.gabriela.ev.model.Personaje;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {

  private FragmentMainBinding binding;


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    binding = FragmentMainBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }


  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = view.findViewById(R.id.rvPersonajes);
    LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
    binding.rvPersonajes.setLayoutManager(layoutManager);


    RetrofitHelper.getService().getPersonajes().enqueue(new Callback<List<Personaje>>() {

      @Override
      public void onResponse(Call<List<Personaje>> call, Response<List<Personaje>> response) {

        if (response.isSuccessful()) {
          List<Personaje> pjs = response.body();
          RVPersonajesAdapter adapter = new RVPersonajesAdapter(pjs);
          recyclerView.setAdapter(adapter);

        }

      }
      @Override
      public void onFailure(Call<List<Personaje>> call, Throwable t) {

      }
    });
  }
}







