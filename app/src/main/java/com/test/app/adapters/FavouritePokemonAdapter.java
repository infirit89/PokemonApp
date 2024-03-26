package com.test.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.R;
import com.test.app.activities.FavouritePokemonFragment;
import com.test.app.db.entities.PokemonEntity;

import java.util.List;

public class FavouritePokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    public FavouritePokemonAdapter(List<PokemonEntity> pokemonEntities) {
        this.pokemonEntities = pokemonEntities;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_pokemon, parent, false);
        return new PokemonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonEntity entity = pokemonEntities.get(position);

        holder.setPokemonName(entity.getName());
        holder.setPokemonFrontSprite(entity.getFrontDefaultSprite());
    }

    @Override
    public int getItemCount() {
        return pokemonEntities.size();
    }

    private List<PokemonEntity> pokemonEntities;
}
