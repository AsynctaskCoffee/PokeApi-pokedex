package egolabsapps.basicodemine.pokemonmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonDetails {
    @SerializedName("abilities")
    @Expose
    public List<Ability> abilities = null;
    @SerializedName("base_experience")
    @Expose
    public Integer baseExperience;
    @SerializedName("forms")
    @Expose
    public List<Form> forms = null;
    @SerializedName("game_indices")
    @Expose
    public List<GameIndex> gameIndices = null;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("held_items")
    @Expose
    public List<HeldItem> heldItems = null;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("is_default")
    @Expose
    public Boolean isDefault;
    @SerializedName("location_area_encounters")
    @Expose
    public String locationAreaEncounters;
    @SerializedName("moves")
    @Expose
    public List<Move> moves = null;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("order")
    @Expose
    public Integer order;
    @SerializedName("species")
    @Expose
    public Species species;
    @SerializedName("sprites")
    @Expose
    public Sprites sprites;
    @SerializedName("stats")
    @Expose
    public List<Stat> stats = null;
    @SerializedName("types")
    @Expose
    public List<Type> types = null;
    @SerializedName("weight")
    @Expose
    public Integer weight;

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public Integer getBaseExperience() {
        return baseExperience;
    }

    public void setBaseExperience(Integer baseExperience) {
        this.baseExperience = baseExperience;
    }

    public List<Form> getForms() {
        return forms;
    }

    public void setForms(List<Form> forms) {
        this.forms = forms;
    }

    public List<GameIndex> getGameIndices() {
        return gameIndices;
    }

    public void setGameIndices(List<GameIndex> gameIndices) {
        this.gameIndices = gameIndices;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public List<HeldItem> getHeldItems() {
        return heldItems;
    }

    public void setHeldItems(List<HeldItem> heldItems) {
        this.heldItems = heldItems;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public String getLocationAreaEncounters() {
        return locationAreaEncounters;
    }

    public void setLocationAreaEncounters(String locationAreaEncounters) {
        this.locationAreaEncounters = locationAreaEncounters;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}
