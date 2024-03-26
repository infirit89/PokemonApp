package com.test.app.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.app.MainActivity;
import com.test.app.R;
import com.test.app.callbacks.OnItemClickListener;
import com.test.app.callbacks.PokemonRequestSuccessCallback;
import com.test.app.globals.Globals;
import com.test.app.models.Pokemon;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    public PokemonAdapter()
    {
        this.pokemonArray = new Pokemon[Globals.getPageLimit()];
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
        Pokemon pokemon = this.pokemonArray[position];

        holder.itemView.setBackgroundColor(selectedPosition == position ? Color.GRAY : Color.WHITE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onClickListener != null)
                    onClickListener.onItemClick(holder.getAdapterPosition(), pokemonArray[holder.getAdapterPosition()]);

                if(selectedPosition > -1)
                    notifyItemChanged(selectedPosition);

                selectedPosition = holder.getAdapterPosition();
                notifyItemChanged(selectedPosition);
            }
        });

        if(pokemon != null)
        {
            setViewHolder(holder, pokemon);
            return;
        }

        String[] pathParts = MainActivity.getPokemonManager().getPkResult(position).getUrl().split("/");
        MainActivity.getPokemonManager().getPokemon(Integer.parseInt(pathParts[pathParts.length - 1]), new PokemonRequestSuccessCallback() {
            @Override
            public void call(Pokemon pokemon) {
                if(holder.getAdapterPosition() < 0)
                    return;
                pokemonArray[holder.getAdapterPosition()] = pokemon;
                setViewHolder(holder, pokemon);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonArray.length;
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    private void setViewHolder(@NonNull PokemonViewHolder holder, Pokemon pokemon) {
        holder.setPokemonName(pokemon.getName());
        holder.setPokemonFrontSprite(pokemon.getSprites().getFrontDefault());
    }

    private Pokemon[] pokemonArray;
    private OnItemClickListener onClickListener;
    private int selectedPosition = -1;
}
