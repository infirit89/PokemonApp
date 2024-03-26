package com.test.app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.app.R;
import com.test.app.activities.MainActivity;
import com.test.app.callbacks.OnItemClickListener;
import com.test.app.callbacks.PokemonRequestSuccessCallback;
import com.test.app.db.dao.PokemonDao;
import com.test.app.db.entities.PokemonEntity;
import com.test.app.globals.Globals;
import com.test.app.models.Pokemon;

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
        holder.setFavouriteButtonText("Unfavourite");
        holder.FavouriteButton.setOnClickListener(v -> {
            pokemonEntities.remove(position);
            PokemonDao pokemonDao = Globals.AppDatabase.pokemonDao();
            pokemonDao.deletePokemon(entity.getPokemonId());
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, pokemonEntities.size());
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null) {
                    MainActivity.getPokemonManager().getPokemon(entity.getPokemonId(), new PokemonRequestSuccessCallback() {
                        @Override
                        public void call(Pokemon pokemon) {
                            if(holder.getAdapterPosition() < 0)
                                return;

                            onItemClickListener.onItemClick(holder.getAdapterPosition(), pokemon);
                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonEntities.size();
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onItemClickListener = onClickListener;
    }

    private List<PokemonEntity> pokemonEntities;
    private OnItemClickListener onItemClickListener;
}
