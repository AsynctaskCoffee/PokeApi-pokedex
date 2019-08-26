package egolabsapps.basicodemine.pokemonmaster.UI.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Objects;

import egolabsapps.basicodemine.pokemonmaster.BaseActivity;
import egolabsapps.basicodemine.pokemonmaster.Interfaces.DialogOpenCloseListener;
import egolabsapps.basicodemine.pokemonmaster.Model.Ability;
import egolabsapps.basicodemine.pokemonmaster.Model.AbilityPerfectDetail;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonDetails;
import egolabsapps.basicodemine.pokemonmaster.Model.Stat;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.PokemonAbilityViewDialog;
import egolabsapps.basicodemine.pokemonmaster.Utils.ImageUtils;
import egolabsapps.basicodemine.pokemonmaster.Utils.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PokemonDetailActivity extends BaseActivity implements DialogOpenCloseListener {

    TextView specialAttackTv, specialDefenceTv, attackTv, defenseTv, speedTv;
    ImageView pokemonImage;
    LinearLayout llAttackContainerLayout, llStatContainerLayout;
    CircularProgressBar specialAttackPb, specialDefencePb, attackPb, defensePb, speedPb;
    PokemonAbilityViewDialog abilityViewDialog;
    boolean isDialogAllowed = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        bundleCheckAndSet();
    }

    private void bundleCheckAndSet() {
        if (getIntent().hasExtra(StringUtils.EXTRA_NAME) && getIntent().hasExtra(StringUtils.EXTRA_ID)) {
            ImageUtils.LoadPokemonImageIntoImageViewById(getIntent().getStringExtra(StringUtils.EXTRA_ID), PokemonDetailActivity.this, pokemonImage);
            changeActionBarText(getIntent().getStringExtra(StringUtils.EXTRA_NAME));
            setPokemonData(getIntent().getStringExtra(StringUtils.EXTRA_ID));
        }
    }

    private void setPokemonData(String pokeId) {
        Call<PokemonDetails> pokemonDetailsCall = pokemonApi.getPokemonDetails(pokeId);
        pokemonDetailsCall.enqueue(new Callback<PokemonDetails>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onResponse(@NonNull Call<PokemonDetails> call, @NonNull Response<PokemonDetails> response) {
                if (response.body() != null) {
                    PokemonDetails pokemonDetails = response.body();

                    llAttackContainerLayout.removeAllViews();
                    for (Ability ability : pokemonDetails.getAbilities()) {
                        @SuppressLint("InflateParams") View v = LayoutInflater.from(activity).inflate(R.layout.item_attack_cell, null);
                        TextView tvAbility = v.findViewById(R.id.ability_name_text);
                        tvAbility.setAllCaps(true);
                        tvAbility.setText(ability.getAbility().getName());
                        v.setOnClickListener(v1 -> pushNewAbilityDetailsDialog(ability.getAbility().getName(), pokemonDetails, pokeId));
                        llAttackContainerLayout.addView(v);
                    }

                    for (Stat stats : pokemonDetails.getStats()) {
                        switch (stats.getStat().getName()) {
                            case "special-attack":
                                specialAttackPb.setProgressWithAnimation(stats.getBaseStat(), 500);
                                specialAttackTv.setText(String.format("%d", stats.getBaseStat()));
                                break;
                            case "special-defense":
                                specialDefencePb.setProgressWithAnimation(stats.getBaseStat(), 500);
                                specialDefenceTv.setText(String.format("%d", stats.getBaseStat()));
                                break;
                            case "defense":
                                defensePb.setProgressWithAnimation(stats.getBaseStat(), 500);
                                defenseTv.setText(String.format("%d", stats.getBaseStat()));
                                break;
                            case "attack":
                                attackPb.setProgressWithAnimation(stats.getBaseStat(), 500);
                                attackTv.setText(String.format("%d", stats.getBaseStat()));
                                break;
                            case "speed":
                                speedPb.setProgressWithAnimation(stats.getBaseStat(), 500);
                                speedTv.setText(String.format("%d", stats.getBaseStat()));
                                break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokemonDetails> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    protected int setViewForActivityWithId() {
        return R.layout.activity_pokemon_detail;
    }

    private void setViews() {
        pokemonImage = findViewById(R.id.item_pokemon_image);
        llAttackContainerLayout = findViewById(R.id.ll_attack_container_layout);
        llStatContainerLayout = findViewById(R.id.ll_stat_container_layout);
        specialDefencePb = findViewById(R.id.special_defence_pb);
        specialAttackPb = findViewById(R.id.special_attack_pb);
        specialAttackTv = findViewById(R.id.special_attack_tv);
        specialDefenceTv = findViewById(R.id.special_defence_tv);
        attackPb = findViewById(R.id.attack_pb);
        defensePb = findViewById(R.id.defense_pb);
        speedPb = findViewById(R.id.speed_pb);
        attackTv = findViewById(R.id.attack_tv);
        defenseTv = findViewById(R.id.defense_tv);
        speedTv = findViewById(R.id.speed_tv);
    }

    /**
     * I should disabled button when press it for dialog
     */
    void pushNewAbilityDetailsDialog(String abilityId, PokemonDetails pokemonDetails, String pokeId) {
        Call<AbilityPerfectDetail> perfectDetailCall = pokemonApi.getPokemonAttackDetails(abilityId);
        perfectDetailCall.enqueue(new Callback<AbilityPerfectDetail>() {
            @Override
            public void onResponse(@NonNull Call<AbilityPerfectDetail> call, @NonNull Response<AbilityPerfectDetail> response) {
                if (response.body() != null) {
                    abilityViewDialog = new PokemonAbilityViewDialog(activity, pokemonDetails, response.body(), pokeId, PokemonDetailActivity.this);
                    if (isDialogAllowed)
                        abilityViewDialog.showIfAllowed();
                }
            }

            @Override
            public void onFailure(@NonNull Call<AbilityPerfectDetail> call, @NonNull Throwable t) {

            }
        });
    }

    @Override
    public void onClose() {
        isDialogAllowed = true;
    }

    @Override
    public void onOpen() {
        isDialogAllowed = false;
    }
}
