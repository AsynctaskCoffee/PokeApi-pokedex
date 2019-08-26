package egolabsapps.basicodemine.pokemonmaster.UI.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import egolabsapps.basicodemine.pokemonmaster.BaseActivity;
import egolabsapps.basicodemine.pokemonmaster.Interfaces.RecyclerViewClickListener;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonHolder;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.RingView;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView pokemonName;
        ImageView pokemonImage;
        RingView pokemonMarker;

        ViewHolder(View view) {
            super(view);
            pokemonName = view.findViewById(R.id.item_pokemon_name_text);
            pokemonImage = view.findViewById(R.id.item_pokemon_image);
            pokemonMarker = view.findViewById(R.id.item_pokemon_marker);
        }
    }

    private List<PokemonHolder> listPokemon;
    private RecyclerViewClickListener listener;
    private BaseActivity baseActivity;

    public PokemonListAdapter(List<PokemonHolder> listPokemon, BaseActivity baseActivity, RecyclerViewClickListener listener) {
        this.listPokemon = listPokemon;
        this.listener = listener;
        this.baseActivity = baseActivity;
    }


    @NonNull
    @Override
    public PokemonListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon_cell, parent, false);
        final ViewHolder view_holder = new ViewHolder(v);
        v.setOnClickListener(v1 -> listener.onItemClick(v1, view_holder.pokemonMarker, view_holder.getPosition()));
        return view_holder;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PokemonHolder pokemonHolder = listPokemon.get(position);
        holder.pokemonName.setText(pokemonHolder.getName());
        String transitionFrame = baseActivity.getResources().getString(R.string.transition_frame);
        holder.pokemonImage.setTransitionName(transitionFrame + pokemonHolder.getName());
        Glide.with(baseActivity).load(pokemonHolder.getImageString()).into(holder.pokemonImage);
    }

    @Override
    public int getItemCount() {
        return listPokemon.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
