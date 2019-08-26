package egolabsapps.basicodemine.pokemonmaster.Utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

import egolabsapps.basicodemine.pokemonmaster.Model.PokemonHolder;
import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.UI.Activities.PokemonDetailActivity;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.RingView;

import static android.support.v4.app.ActivityOptionsCompat.makeSceneTransitionAnimation;
import static egolabsapps.basicodemine.pokemonmaster.Utils.StringUtils.EXTRA_ID;
import static egolabsapps.basicodemine.pokemonmaster.Utils.StringUtils.EXTRA_NAME;

public class TransitionsUtils {

    private static void putExtras(Intent intent, PokemonHolder pokemonHolder) {
        intent.putExtra(EXTRA_NAME, pokemonHolder.getName());
        intent.putExtra(EXTRA_ID, pokemonHolder.getId());
    }

    public static void startAnimatedActivity(Activity context, RingView frame, PokemonHolder pokemonHolder) {
        Intent intent = new Intent(context, PokemonDetailActivity.class);
        putExtras(intent, pokemonHolder);
        String transitionFrame = context.getString(R.string.transition_frame);
        Pair<View, String> pair = Pair.create(frame, transitionFrame);
        Bundle options = makeSceneTransitionAnimation(context, pair).toBundle();
        Bundle frameExtra = new Bundle();
        CircleViewTransitions.addExtraProperties(frame, frameExtra);
        intent.putExtra(transitionFrame, frameExtra);
        context.startActivity(intent, options);
    }
}
