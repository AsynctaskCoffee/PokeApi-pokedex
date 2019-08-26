package egolabsapps.basicodemine.pokemonmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ability {

    @SerializedName("ability")
    @Expose
    public Ability_ ability;
    @SerializedName("is_hidden")
    @Expose
    public Boolean isHidden;
    @SerializedName("slot")
    @Expose
    public Integer slot;

    public Ability_ getAbility() {
        return ability;
    }

    public void setAbility(Ability_ ability) {
        this.ability = ability;
    }

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }
}
