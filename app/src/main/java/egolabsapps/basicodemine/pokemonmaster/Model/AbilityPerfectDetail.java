
package egolabsapps.basicodemine.pokemonmaster.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AbilityPerfectDetail {

    @SerializedName("effect_changes")
    @Expose
    public List<Object> effectChanges = null;
    @SerializedName("effect_entries")
    @Expose
    public List<EffectEntry> effectEntries = null;
    @SerializedName("flavor_text_entries")
    @Expose
    public List<FlavorTextEntry> flavorTextEntries = null;
    @SerializedName("generation")
    @Expose
    public Generation generation;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("is_main_series")
    @Expose
    public Boolean isMainSeries;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("names")
    @Expose
    public List<Name> names = null;
    @SerializedName("pokemon")
    @Expose
    public List<Pokemon> pokemon = null;

    public List<Object> getEffectChanges() {
        return effectChanges;
    }

    public void setEffectChanges(List<Object> effectChanges) {
        this.effectChanges = effectChanges;
    }

    public List<EffectEntry> getEffectEntries() {
        return effectEntries;
    }

    public void setEffectEntries(List<EffectEntry> effectEntries) {
        this.effectEntries = effectEntries;
    }

    public List<FlavorTextEntry> getFlavorTextEntries() {
        return flavorTextEntries;
    }

    public void setFlavorTextEntries(List<FlavorTextEntry> flavorTextEntries) {
        this.flavorTextEntries = flavorTextEntries;
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getMainSeries() {
        return isMainSeries;
    }

    public void setMainSeries(Boolean mainSeries) {
        isMainSeries = mainSeries;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }
}
