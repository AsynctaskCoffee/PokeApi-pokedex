package egolabsapps.basicodemine.pokemonmaster.Utils;

public class StringUtils {
    public static final int PAGE_COUNT = 40;
    public static final String EXTRA_NAME = "extra.name";
    public static final String EXTRA_ID = "extra.id";

    public static String getPokemonImageStringFromId(String id) {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + id + ".png";
    }
}
