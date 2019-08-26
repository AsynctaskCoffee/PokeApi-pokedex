package egolabsapps.basicodemine.pokemonmaster.UI.CustomViews;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import egolabsapps.basicodemine.pokemonmaster.BaseActivity;
import egolabsapps.basicodemine.pokemonmaster.Interfaces.DialogOpenCloseListener;
import egolabsapps.basicodemine.pokemonmaster.Model.AbilityPerfectDetail;
import egolabsapps.basicodemine.pokemonmaster.Model.PokemonDetails;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.Utils.ImageUtils;

public class PokemonAbilityViewDialog extends Dialog {

    private boolean isShowing = false;
    private ImageView pokemonImageView;
    private TextView abilityDialogTitle, abilityDialogDesc;
    private DialogOpenCloseListener dialogOpenCloseListener;

    public PokemonAbilityViewDialog(BaseActivity activity, PokemonDetails pokemonDetails, AbilityPerfectDetail detail, String pokemonId, DialogOpenCloseListener dialogOpenCloseListener) {
        super(activity);
        Window window = getWindow();
        assert window != null;
        window.requestFeature(Window.FEATURE_NO_TITLE);
        this.dialogOpenCloseListener = dialogOpenCloseListener;
        try {
            setContentView(activity.getLayoutInflater().inflate(R.layout.fragment_ability_dialog, null));
            setupViews();
            setupImg(pokemonId);
            setupDatas(detail, pokemonDetails.getName());
            window.getAttributes().windowAnimations = R.style.DialogAnimation;
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            window.setGravity(Gravity.CENTER);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setCanceledOnTouchOutside(false);
            setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dismiss() {
        isShowing = false;
        dialogOpenCloseListener.onClose();
        super.dismiss();
    }

    private void setupViews() {
        abilityDialogTitle = findViewById(R.id.ability_dialog_title);
        abilityDialogDesc = findViewById(R.id.ability_dialog_desc);
        pokemonImageView = findViewById(R.id.ability_dialog_image);
        Button abilityDialogClose = findViewById(R.id.ability_dialog_close);
        abilityDialogClose.setOnClickListener(v -> dismiss());
    }

    private void setupDatas(AbilityPerfectDetail detail, String pokeName) {
        abilityDialogTitle.setText(String.format("%s's %s", pokeName, detail.getName()));
        abilityDialogDesc.setText(detail.getEffectEntries().get(0).getEffect());
    }

    private void setupImg(String pokeImg) {
        ImageUtils.LoadPokemonImageIntoImageViewById(pokeImg, getContext(), pokemonImageView);
    }

    public void showIfAllowed() {
        if (!isShowing) {
            show();
        }
    }

    @Override
    public void show() {
        isShowing = true;
        dialogOpenCloseListener.onOpen();
        super.show();
    }
}
