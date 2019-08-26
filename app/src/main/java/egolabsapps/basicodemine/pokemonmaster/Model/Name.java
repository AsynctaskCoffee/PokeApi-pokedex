
package egolabsapps.basicodemine.pokemonmaster.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("language")
    @Expose
    public Language__ language;
    @SerializedName("name")
    @Expose
    public String name;

    public Language__ getLanguage() {
        return language;
    }

    public void setLanguage(Language__ language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
