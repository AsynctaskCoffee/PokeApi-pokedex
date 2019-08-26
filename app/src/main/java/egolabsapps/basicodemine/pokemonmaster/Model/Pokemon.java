
package egolabsapps.basicodemine.pokemonmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("is_hidden")
    @Expose
    public Boolean isHidden;
    @SerializedName("pokemon")
    @Expose
    public Pokemon_ pokemon;
    @SerializedName("slot")
    @Expose
    public Integer slot;

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Pokemon_ getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon_ pokemon) {
        this.pokemon = pokemon;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
