package egolabsapps.basicodemine.pokemonmaster.UI.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import egolabsapps.basicodemine.pokemonmaster.BaseActivity;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.Utils.ImageUtils;
import egolabsapps.basicodemine.pokemonmaster.Utils.NetworkUtils;

public class WelcomeActivity extends BaseActivity {
    ImageView imgWelcome;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViews();
        hideToolBar();
        setGifForWelcome();
    }

    @Override
    protected int setViewForActivityWithId() {
        return R.layout.activity_welcome;
    }

    private void setViews() {
        imgWelcome = findViewById(R.id.img_welcome);
    }

    private void setGifForWelcome() {
        ImageUtils.LoadGifIntoImageView(imgWelcome, this, R.drawable.loading_gif);
    }

    public void goToMain(View view) {
        if (NetworkUtils.checkConnection(activity)) {
            startActivity(new Intent(activity, MainActivity.class));
            finish();
        } else Toast.makeText(activity, "Internet Connection Needed", Toast.LENGTH_SHORT).show();
    }
}
