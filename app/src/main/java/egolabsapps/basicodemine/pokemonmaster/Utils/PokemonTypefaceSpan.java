package egolabsapps.basicodemine.pokemonmaster.Utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

import egolabsapps.basicodemine.pokemonmaster.R;

public class PokemonTypefaceSpan extends MetricAffectingSpan {
    private static LruCache<String, Typeface> sTypefaceCache = new LruCache<>(12);
    private Typeface mTypeface;

    public PokemonTypefaceSpan(Context context, String typefaceName) {
        mTypeface = sTypefaceCache.get(typefaceName);
        if (mTypeface == null) {
            mTypeface = ResourcesCompat.getFont(context, R.font.pokemon_solid);
            sTypefaceCache.put(typefaceName, mTypeface);
        }
    }

    @Override
    public void updateMeasureState(@NonNull TextPaint p) {
        p.setTypeface(mTypeface);
        p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);
        tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
    }
}