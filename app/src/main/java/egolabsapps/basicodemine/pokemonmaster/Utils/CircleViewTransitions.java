package egolabsapps.basicodemine.pokemonmaster.Utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import egolabsapps.basicodemine.pokemonmaster.R;
import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.RingView;

public class CircleViewTransitions extends Transition {
    private static final String PROPNAME_COLOR = "poke:CircleViewTransitions:color";
    private static final String PROPNAME_INNER_RADIUS = "poke:CircleViewTransitions:innerRadius";
    private static final String PROPNAME_OUTER_RADIUS = "poke:CircleViewTransitions:outerRadius";
    private static final String[] TRANSITION_PROPERTIES = {PROPNAME_COLOR, PROPNAME_INNER_RADIUS, PROPNAME_OUTER_RADIUS};

    public CircleViewTransitions() {
    }

    public CircleViewTransitions(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Nullable
    @Override
    public String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof RingView) {
            captureValues((RingView) transitionValues.view, transitionValues);
        }
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof RingView) {
            captureValues((RingView) transitionValues.view, transitionValues);
        }
    }

    private void captureValues(RingView view, TransitionValues transitionValues) {
        Bundle extraData = (Bundle) transitionValues.view.getTag(R.id.tag_transition_extra_properties);
        if (extraData != null) {
            transitionValues.values.put(PROPNAME_COLOR, extraData.getInt(PROPNAME_COLOR, view.getColor()));
            transitionValues.values.put(PROPNAME_INNER_RADIUS, extraData.getFloat(PROPNAME_INNER_RADIUS, view.getInnerCircleRadius()));
            transitionValues.values.put(PROPNAME_OUTER_RADIUS, extraData.getFloat(PROPNAME_OUTER_RADIUS, view.getOuterCircleRadius()));
        } else {
            transitionValues.values.put(PROPNAME_COLOR, view.getColor());
            transitionValues.values.put(PROPNAME_INNER_RADIUS, view.getInnerCircleRadius());
            transitionValues.values.put(PROPNAME_OUTER_RADIUS, view.getOuterCircleRadius());
        }
    }

    @Nullable
    @Override
    public Animator createAnimator(@NonNull ViewGroup sceneRoot, @Nullable TransitionValues startValues,
                                   @Nullable TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }

        List<Animator> animators = new ArrayList<>(3);
        final RingView ringView = (RingView) endValues.view;

        int startColor = (int) startValues.values.get(PROPNAME_COLOR);
        int endColor = (int) endValues.values.get(PROPNAME_COLOR);
        if (startColor != endColor) {
            final ArgbEvaluator argbEvaluator = new ArgbEvaluator();
            ValueAnimator animator = ValueAnimator.ofFloat(0, 1.0f);
            animator.addUpdateListener(animation -> {
                int color = (Integer) argbEvaluator.evaluate(animation.getAnimatedFraction(), startColor, endColor);
                ringView.setColor(color);
            });
            animators.add(animator);
        }

        float startInnerRadius = (float) startValues.values.get(PROPNAME_INNER_RADIUS);
        float endInnerRadius = (float) endValues.values.get(PROPNAME_INNER_RADIUS);
        if (startInnerRadius != endInnerRadius) {
            ringView.setInnerCircleRadius(startInnerRadius);
            animators.add(ObjectAnimator.ofFloat(ringView, RingView.INNER_CIRCLE_RADIUS, endInnerRadius));
        }
        float startOuterRadius = (float) startValues.values.get(PROPNAME_OUTER_RADIUS);
        float endOuterRadius = (float) endValues.values.get(PROPNAME_OUTER_RADIUS);
        if (startOuterRadius != endOuterRadius) {
            ringView.setOuterCircleRadius(startOuterRadius);
            animators.add(ObjectAnimator.ofFloat(ringView, RingView.OUTER_CIRCLE_RADIUS, endOuterRadius));
        }

        AnimatorSet set = new AnimatorSet();
        set.playTogether(animators);
        return set;
    }

    static void addExtraProperties(RingView view, Bundle extra) {
        extra.putInt(PROPNAME_COLOR, view.getColor());
        extra.putFloat(PROPNAME_INNER_RADIUS, view.getInnerCircleRadius());
        extra.putFloat(PROPNAME_OUTER_RADIUS, view.getOuterCircleRadius());
    }
}