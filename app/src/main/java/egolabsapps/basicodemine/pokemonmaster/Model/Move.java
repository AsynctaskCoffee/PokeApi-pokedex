package egolabsapps.basicodemine.pokemonmaster.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Move {

    @SerializedName("move")
    @Expose
    public Move_ move;
    @SerializedName("version_group_details")
    @Expose
    public List<VersionGroupDetail> versionGroupDetails = null;

    public Move_ getMove() {
        return move;
    }

    public void setMove(Move_ move) {
        this.move = move;
    }

    public List<VersionGroupDetail> getVersionGroupDetails() {
        return versionGroupDetails;
    }

    public void setVersionGroupDetails(List<VersionGroupDetail> versionGroupDetails) {
        this.versionGroupDetails = versionGroupDetails;
    }
}
