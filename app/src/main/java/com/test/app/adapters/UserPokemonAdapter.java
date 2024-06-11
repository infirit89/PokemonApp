package com.test.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.R;
import com.test.app.db.dao.PokemonDao;
import com.test.app.db.entities.PokemonEntity;
import com.test.app.globals.Globals;

import java.util.List;

public class UserPokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder>{

    public UserPokemonAdapter(List<PokemonEntity> pokemonEntities) {
        this.pokemonEntities = pokemonEntities;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view_user_pokemon, parent, false);
        return new PokemonViewHolder(itemView, R.id.btnDelete);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonEntity entity = pokemonEntities.get(position);

        holder.setPokemonName(entity.getName());
        holder.setPokemonFrontSprite(entity.getFrontDefaultSprite());
        holder.FavouriteButton.setOnClickListener(v -> {
            pokemonEntities.remove(position);
            PokemonDao pokemonDao = Globals.AppDatabase.pokemonDao();
            pokemonDao.deletePokemon(entity.getPokemonId());
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, pokemonEntities.size());
        });
    }

    @Override
    public int getItemCount() {
        return pokemonEntities.size();
    }

    private final List<PokemonEntity> pokemonEntities;
}
