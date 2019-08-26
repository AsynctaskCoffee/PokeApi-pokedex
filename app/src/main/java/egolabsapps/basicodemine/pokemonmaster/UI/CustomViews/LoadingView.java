package egolabsapps.basicodemine.pokemonmaster.UI.CustomViews;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Objects;

import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.Utils.ImageUtils;

public class LoadingView extends DialogFragment {


    @SuppressLint("StaticFieldLeak")
    private static LoadingView loadingView;
    private boolean isShowing = false;

    public static LoadingView getInstance() {
        if (loadingView == null)
            loadingView = new LoadingView();
        return loadingView;
    }

    ImageView loadingImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
    }


    /**
     * LoadingView dialog created no need transparent anymore
     * <p>
     * <p>
     * //    @Override
     * //    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
     * //        super.onActivityCreated(savedInstanceState);
     * //        try {
     * //            Objects.requireNonNull(getDialog().getWindow()).getAttributes().alpha = 0.7f;
     * //        } catch (Exception e) {
     * //            e.printStackTrace();
     * //        }
     * //    }
     */


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_loading_dialog, container, false);
        setupViews(v);
        setupGif();
        return v;
    }

    public void dismissDelay() {
        new Handler().postDelayed(() -> {
            dismiss();
            isShowing = false;
        }, 1000);
    }

    private void setupViews(View v) {
        loadingImageView = v.findViewById(R.id.loading_image_view);
    }

    private void setupGif() {
        ImageUtils.LoadGifIntoImageView(loadingImageView, getContext(), R.drawable.loading_gif);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            Objects.requireNonNull(dialog.getWindow()).setLayout(width, height);
        }
    }


    public void showIsAllowed(FragmentManager manager, String tag) {
        if (!isShowing) {
            show(manager, tag);
        }
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        isShowing = true;
        super.show(manager, tag);
    }
}
