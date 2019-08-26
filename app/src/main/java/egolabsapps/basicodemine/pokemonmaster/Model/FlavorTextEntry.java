
package egolabsapps.basicodemine.pokemonmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlavorTextEntry {

    @SerializedName("flavor_text")
    @Expose
    public String flavorText;
    @SerializedName("language")
    @Expose
    public Language_ language;
    @SerializedName("version_group")
    @Expose
    public VersionGroup versionGroup;

    public String getFlavorText() {
        return flavorText;
    }

    public void setFlavorText(String flavorText) {
        this.flavorText = flavorText;
    }

    public Language_ getLanguage() {
        return language;
    }

    public void setLanguage(Language_ language) {
        this.language = language;
    }

    public VersionGroup getVersionGroup() {
        return versionGroup;
    }

    public void setVersionGroup(VersionGroup versionGroup) {
        this.versionGroup = versionGroup;
    }
}
