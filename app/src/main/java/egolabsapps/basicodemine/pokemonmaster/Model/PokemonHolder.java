package egolabsapps.basicodemine.pokemonmaster.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PokemonHolder {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;

    public PokemonHolder() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageString() {
        try {
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + getUrl().split("v2/pokemon/")[1].replaceAll("/", "") + ".png";
        } catch (Exception e) {
            e.printStackTrace();
            return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/0.png";
        }
    }

    public String getId() {
        try {
            return getUrl().split("v2/pokemon/")[1].replaceAll("/", "");
        } catch (Exception e) {
            return "-1";
        }
    }
}
