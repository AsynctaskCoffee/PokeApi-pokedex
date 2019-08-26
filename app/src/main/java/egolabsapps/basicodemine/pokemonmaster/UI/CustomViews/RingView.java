package egolabsapps.basicodemine.pokemonmaster.UI.CustomViews;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;

import egolabsapps.basicodemine.pokemonmaster.R;


public class RingView extends View {
    private Paint circlePaint = new Paint();

    private float outerCircleRadius = 0f;
    private float innerCircleRadius = 0f;
    private float radius = 0;

    public RingView(Context context) {
        super(context);
        init(context, null);
    }

    public RingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public RingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RingView);
            try {
                innerCircleRadius = array.getDimension(R.styleable.RingView_inner_radius, 0);
                outerCircleRadius = array.getDimension(R.styleable.RingView_outer_radius, 0);
                int color = array.getColor(R.styleable.RingView_color, Color.BLACK);
                circlePaint.setColor(color);
            } finally {
                array.recycle();
            }
        }

        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);

        recalculate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, circlePaint);
    }

    public void setInnerCircleRadius(float innerCircleRadiusProgress) {
        this.innerCircleRadius = innerCircleRadiusProgress;
        recalculate();
        invalidate();
    }

    public float getInnerCircleRadius() {
        return innerCircleRadius;
    }

    public void setOuterCircleRadius(float outerCircleRadiusProgress) {
        this.outerCircleRadius = outerCircleRadiusProgress;
        recalculate();
        invalidate();
    }

    public float getOuterCircleRadius() {
        return outerCircleRadius;
    }

    public int getColor() {
        return circlePaint.getColor();
    }

    public void setColor(int color) {
        circlePaint.setColor(color);
        invalidate();
    }

    private void recalculate() {
        circlePaint.setStrokeWidth(outerCircleRadius - innerCircleRadius);
        radius = innerCircleRadius + (outerCircleRadius - innerCircleRadius) / 2;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        return new SavedState(superState, circlePaint.getColor(), innerCircleRadius, outerCircleRadius);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());

        innerCircleRadius = savedState.innerRadius;
        outerCircleRadius = savedState.outerRadius;
        circlePaint.setColor(savedState.color);
        recalculate();
    }


    public static final Property<RingView, Float> INNER_CIRCLE_RADIUS =
            new Property<RingView, Float>(Float.class, "innerCircleRadius") {
                @Override
                public Float get(RingView object) {
                    return object.getInnerCircleRadius();
                }

                @Override
                public void set(RingView object, Float value) {
                    object.setInnerCircleRadius(value);
                }
            };

    public static final Property<RingView, Float> OUTER_CIRCLE_RADIUS =
            new Property<RingView, Float>(Float.class, "outerCircleRadius") {
                @Override
                public Float get(RingView object) {
                    return object.getOuterCircleRadius();
                }

                @Override
                public void set(RingView object, Float value) {
                    object.setOuterCircleRadius(value);
                }
            };

    public static final Property<RingView, Integer> COLOR =
            new Property<RingView, Integer>(Integer.class, "color") {
                @Override
                public Integer get(RingView object) {
                    return object.getColor();
                }

                @Override
                public void set(RingView object, Integer value) {
                    object.setColor(value);
                }
            };

    protected static class SavedState extends BaseSavedState {
        private final int color;
        private final float innerRadius;
        private final float outerRadius;

        private SavedState(Parcelable superState, int color, float innerRadius, float outerRadius) {
            super(superState);
            this.color = color;
            this.innerRadius = innerRadius;
            this.outerRadius = outerRadius;
        }

        private SavedState(Parcel in) {
            super(in);
            color = in.readInt();
            innerRadius = in.readFloat();
            outerRadius = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel destination, int flags) {
            super.writeToParcel(destination, flags);
            destination.writeInt(color);
            destination.writeFloat(innerRadius);
            destination.writeFloat(outerRadius);
        }

        public static final Parcelable.Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
