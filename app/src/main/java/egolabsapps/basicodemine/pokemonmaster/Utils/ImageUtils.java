package egolabsapps.basicodemine.pokemonmaster.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class ImageUtils {
    public static void LoadGifIntoImageView(ImageView imageView, Context context, Integer resId) {
        Glide.with(context).asGif().load(resId).into(imageView);
    }

    public static void LoadPokemonImageIntoImageViewById(String id, Context context, ImageView imageView) {
        Glide.with(context).load(StringUtils.getPokemonImageStringFromId(id)).into(imageView);
    }
}
