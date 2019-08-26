package egolabsapps.basicodemine.pokemonmaster.Utils;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import egolabsapps.basicodemine.pokemonmaster.R;


public class TextColorTransitions extends Transition {
    private static final String PROPNAME_TEXT_COLOR = "poke:textColorTransition:textColor";
    private static final String[] TRANSITION_PROPERTIES = {PROPNAME_TEXT_COLOR};

    public TextColorTransitions() {
    }

    public TextColorTransitions(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Nullable
    @Override
    public String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }

    @Override
    public void captureEndValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureStartValues(@NonNull TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(@NonNull TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            Bundle extraData = (Bundle) transitionValues.view.getTag(R.id.tag_transition_extra_properties);

            int color;
            if (extraData != null && extraData.containsKey(PROPNAME_TEXT_COLOR)) {
                color = extraData.getInt(PROPNAME_TEXT_COLOR);
            } else {
                color = ((TextView) transitionValues.view).getCurrentTextColor();
            }

            transitionValues.values.put(PROPNAME_TEXT_COLOR, color);
        }
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues,
                                   @Nullable TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        final Integer startTextColor = (Integer) startValues.values.get(PROPNAME_TEXT_COLOR);
        final Integer endTextColor = (Integer) endValues.values.get(PROPNAME_TEXT_COLOR);
        final TextView textView = (TextView) endValues.view;
        final ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        ValueAnimator animator = ValueAnimator.ofFloat(0, 1.0f);
        animator.addUpdateListener(animation -> {
            int color = (Integer) argbEvaluator.evaluate(animation.getAnimatedFraction(), startTextColor, endTextColor);
            textView.setTextColor(color);
        });

        return animator;
    }

    static void addExtraProperties(TextView view, Bundle extra) {
        extra.putInt(PROPNAME_TEXT_COLOR, view.getCurrentTextColor());
    }
}
