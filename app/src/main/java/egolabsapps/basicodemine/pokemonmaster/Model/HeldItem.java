package egolabsapps.basicodemine.pokemonmaster.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeldItem {

    @SerializedName("item")
    @Expose
    public Item item;
    @SerializedName("version_details")
    @Expose
    public List<VersionDetail> versionDetails = null;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<VersionDetail> getVersionDetails() {
        return versionDetails;
    }

    public void setVersionDetails(List<VersionDetail> versionDetails) {
        this.versionDetails = versionDetails;
    }
}
