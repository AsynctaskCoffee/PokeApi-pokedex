package egolabsapps.basicodemine.pokemonmaster.Service;

import egolabsapps.basicodemine.pokemonmaster.Model.AbilityPerfectDetail;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonDetails;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCaller {

    String POKEMON_LIST = "pokemon?";
    String POKEMON_DETAIL = "pokemon/{pokeName}";
    String POKEMON_ATACK_DETAIL = "ability/{abilityId}";

    @GET(POKEMON_LIST)
    Call<PokemonList> getPokemonList(@Query("offset") Integer offset, @Query("limit") Integer limit);

    @GET(POKEMON_ATACK_DETAIL)
    Call<AbilityPerfectDetail> getPokemonAttackDetails(@Path("abilityId") String abilityId);

    @GET(POKEMON_DETAIL)
    Call<PokemonDetails> getPokemonDetails(@Path("pokeName") String pokeName);

}
