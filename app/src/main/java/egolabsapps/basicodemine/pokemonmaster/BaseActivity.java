package egolabsapps.basicodemine.pokemonmaster;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.WindowManager;


import egolabsapps.basicodemine.pokemonmaster.Service.ApiCaller;
import egolabsapps.basicodemine.pokemonmaster.Service.ApiClient;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.LoadingView;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.LoadingViewDialog;
import egolabsapps.basicodemine.pokemonmaster.Utils.PokemonTypefaceSpan;

public abstract class BaseActivity extends AppCompatActivity {
    private LoadingView loadingView;
    public BaseActivity activity;
    public ApiCaller pokemonApi;
    private LoadingViewDialog loadingViewDialog;

    @Override
    protected void onStart() {
        super.onStart();
        activity = this;
        loadingView = LoadingView.getInstance();
    }

    public void hideToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(setViewForActivityWithId());
        setToolbarFont();
        setToolbarImage();
        pokemonApi = ApiClient.getPokemonApi().create(ApiCaller.class);
    }


    protected abstract int setViewForActivityWithId();


    public void showLoadingDialog() {
        if (loadingViewDialog == null)
            loadingViewDialog = LoadingViewDialog.getInstance(this);
        loadingViewDialog.showIfAllowed();
    }

    public void hideLoadingDialog() {
        if (loadingViewDialog == null)
            loadingViewDialog = LoadingViewDialog.getInstance(this);
        loadingViewDialog.dismissDelay();
    }

    public void showLoadingView() {
        String TAG_LOADING = "loadingView";
        if (loadingView == null)
            loadingView = LoadingView.getInstance();
        loadingView.showIsAllowed(getSupportFragmentManager(), TAG_LOADING);
    }

    public void hideLoadingView() {
        if (loadingView == null)
            loadingView = LoadingView.getInstance();
        loadingView.dismissDelay();
    }

    private void setToolbarImage() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(getResources().getDrawable(R.drawable.pokeball_icon));
            getSupportActionBar().setDisplayUseLogoEnabled(true);
        }
    }

    private void setToolbarFont() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            setActionBarFont(actionBar);
    }

    public void changeActionBarText(String title) {
        SpannableString s = new SpannableString(" " + title);
        s.setSpan(new PokemonTypefaceSpan(this, "PokemonFontForTextViews"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(s);
    }

    private void setActionBarFont(@NonNull ActionBar actionBar) {
        SpannableString s = new SpannableString(" Pokemon App Assignment");
        s.setSpan(new PokemonTypefaceSpan(this, "PokemonFontForTextViews"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        s.setSpan(new ForegroundColorSpan(Color.WHITE), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        actionBar.setTitle(s);
    }
}
