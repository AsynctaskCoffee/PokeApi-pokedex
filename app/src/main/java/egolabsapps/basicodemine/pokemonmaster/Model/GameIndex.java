package egolabsapps.basicodemine.pokemonmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GameIndex {
    @SerializedName("game_index")
    @Expose
    public Integer gameIndex;
    @SerializedName("version")
    @Expose
    public Version version;

    public Integer getGameIndex() {
        return gameIndex;
    }

    public void setGameIndex(Integer gameIndex) {
        this.gameIndex = gameIndex;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

}
