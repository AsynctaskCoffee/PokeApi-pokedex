package egolabsapps.basicodemine.pokemonmaster.UI.CustomViews;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import egolabsapps.basicodemine.pokemonmaster.BaseActivity;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.Utils.ImageUtils;

public class LoadingViewDialog extends Dialog {


    @SuppressLint("StaticFieldLeak")
    private static LoadingViewDialog loadingView;
    private boolean isShowing = false;

    public static LoadingViewDialog getInstance(BaseActivity baseActivity) {
        if (loadingView == null)
            loadingView = new LoadingViewDialog(baseActivity);
        return loadingView;
    }

    private ImageView loadingImageView;

    public LoadingViewDialog(BaseActivity activity) {
        super(activity);
        Window window = getWindow();
        assert window != null;
        window.requestFeature(Window.FEATURE_NO_TITLE);
        try {
            setContentView(activity.getLayoutInflater().inflate(R.layout.fragment_loading_dialog_native, null));
            setupViews();
            setupGif();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setCanceledOnTouchOutside(false);
            setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void dismissDelay() {
        new Handler().postDelayed(() -> {
            dismiss();
            isShowing = false;
        }, 1000);
    }

    private void setupViews() {
        loadingImageView = findViewById(R.id.loading_image_view);
    }

    private void setupGif() {
        ImageUtils.LoadGifIntoImageView(loadingImageView, getContext(), R.drawable.pickhu_loading);
    }

    public void showIfAllowed() {
        if (!isShowing) {
            show();
        }
    }

    @Override
    public void show() {
        isShowing = true;
        super.show();
    }
}
