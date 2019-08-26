package egolabsapps.basicodemine.pokemonmaster.UI.Activities;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import egolabsapps.basicodemine.pokemonmaster.BaseActivity;
import egolabsapps.basicodemine.pokemonmaster.Interfaces.EndLessRecyclerViewScrollListener;
import egolabsapps.basicodemine.pokemonmaster.Interfaces.RecyclerViewClickListener;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonHolder;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonList;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.UI.Adapters.PokemonListAdapter;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.RingView;
import egolabsapps.basicodemine.pokemonmaster.Utils.StringUtils;
import egolabsapps.basicodemine.pokemonmaster.Utils.TransitionsUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements RecyclerViewClickListener {

    private RecyclerView pokemonRecyclerView;
    private List<PokemonHolder> pokemonHolders = new ArrayList<>();
    private PokemonListAdapter pokemonListAdapter;
    private boolean isAllowedToLaodMore = true;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        arrangeRecycler();
        setupAndFillDataPager(0);
    }

    private void arrangeRecycler() {
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        pokemonRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        pokemonListAdapter = new PokemonListAdapter(pokemonHolders, MainActivity.this, MainActivity.this);
        pokemonRecyclerView.setAdapter(pokemonListAdapter);
        setupEndlessRecyclerView();
    }

    private void setupEndlessRecyclerView() {
        pokemonRecyclerView.addOnScrollListener(new EndLessRecyclerViewScrollListener(staggeredGridLayoutManager) {
            @Override
            protected void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if (totalItemsCount % 20 == 0)
                    setupAndFillDataPager(page);
            }
        });
    }

    private void setupAndFillDataPager(int page) {
        if (isAllowedToLaodMore) {
            isAllowedToLaodMore = false;
            showDialogForMainActivity(true);
            Call<PokemonList> pokemonListCall = pokemonApi.getPokemonList(page * StringUtils.PAGE_COUNT, StringUtils.PAGE_COUNT);
            pokemonListCall.enqueue(new Callback<PokemonList>() {
                @Override
                public void onResponse(@NonNull Call<PokemonList> call, @NonNull Response<PokemonList> response) {
                    showDialogForMainActivity(false);
                    assert response.body() != null;
                    pokemonHolders.addAll(response.body().getResults());
                    pokemonListAdapter.notifyDataSetChanged();
                    isAllowedToLaodMore = true;
                }

                @Override
                public void onFailure(@NonNull Call<PokemonList> call, @NonNull Throwable t) {
                    showDialogForMainActivity(false);
                }
            });
        }
    }

    @Override
    protected int setViewForActivityWithId() {
        return R.layout.activity_main;
    }

    private void setViews() {
        pokemonRecyclerView = findViewById(R.id.main_menu_pokemon_recycler_view);
    }

    @Override
    public void onItemClick(View v, RingView pokeDot, int position) {
        TransitionsUtils.startAnimatedActivity(this, pokeDot, pokemonHolders.get(position));
    }


    void showDialogForMainActivity(boolean show) {
        if (show)
            if (pokemonHolders.size() == 0) showLoadingView();
            else showLoadingDialog();
        if (!show)
            if (pokemonHolders.size() == 0) hideLoadingView();
            else hideLoadingDialog();
    }
}
