package com.commit451.elasticdragdismisslayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * Defer all the things to this class, so that we can create the layouts easily. Use this
 * to create your own ViewGroups
 */
public class ElasticDragDismissDelegate {

    //copied from View in API 21
    private static final int SCROLL_AXIS_VERTICAL = 1 << 1;

    private static final long DRAG_DURATION_BUFFER = 150L;

    // configurable attribs
    private float dragDismissDistance = Float.MAX_VALUE;
    private float dragDismissFraction = -1f;
    private float dragDismissScale = 1f;
    private boolean shouldScale = false;
    private float dragElacticity = 0.8f;
    private long scrollStartTimestamp;

    private boolean enableScaleX = true;

    // state
    private float totalDrag;
    private boolean draggingDown = false;
    private boolean draggingUp = false;

    private List<ElasticDragDismissCallback> callbacks;
    private ViewGroup mViewGroup;

    public ElasticDragDismissDelegate(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    public void init(Context context, TypedArray a) {
        Util.checkParent(mViewGroup, a);

        if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissDistance)) {
            dragDismissDistance = a.getDimensionPixelSize(R.styleable
                    .ElasticDragDismissFrameLayout_dragDismissDistance, 0);
        } else if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissFraction)) {
            dragDismissFraction = a.getFloat(R.styleable
                    .ElasticDragDismissFrameLayout_dragDismissFraction, dragDismissFraction);
        }
        if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragDismissScale)) {
            dragDismissScale = a.getFloat(R.styleable
                    .ElasticDragDismissFrameLayout_dragDismissScale, dragDismissScale);
            shouldScale = dragDismissScale != 1f;
        }
        if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_dragElasticity)) {
            dragElacticity = a.getFloat(R.styleable.ElasticDragDismissFrameLayout_dragElasticity,
                    dragElacticity);
        }
        if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_enableScaleX)) {
            enableScaleX = a.getBoolean(R.styleable.ElasticDragDismissFrameLayout_enableScaleX, true);
        }
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        scrollStartTimestamp = System.currentTimeMillis();
        return (nestedScrollAxes & SCROLL_AXIS_VERTICAL) != 0;
    }

    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        // if we're in a drag gesture and the user reverses up the we should take those events
        if (draggingDown && dy > 0 || draggingUp && dy < 0) {
            dragScale(dy);
            consumed[1] = dy;
        }
    }

    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        dragScale(dyUnconsumed);
    }

    public void onStopNestedScroll(View child) {
        // if the drag is too fast it probably was not an intentional drag but a fling, don't dismiss
        final long dragTime = System.currentTimeMillis() - scrollStartTimestamp;
        if (Math.abs(totalDrag) >= dragDismissDistance && dragTime > DRAG_DURATION_BUFFER) {
            dispatchDismissCallback();
        } else { // settle back to natural position
            ViewPropertyAnimator animator = mViewGroup.animate()
                    .translationY(0f)
                    .scaleY(1f)
                    .setDuration(200L)
                    .setInterpolator(new FastOutSlowInInterpolator())
                    .setListener(null);
            if (enableScaleX) {
                animator.scaleX(1f);
            }

            animator.start();

            totalDrag = 0;
            draggingDown = draggingUp = false;
            dispatchDragCallback(0f, 0f, 0f, 0f);
        }
    }

    private void dragScale(int scroll) {
        if (scroll == 0) return;

        totalDrag += scroll;

        // track the direction & set the pivot point for scaling
        // don't double track i.e. if start dragging down and then reverse, keep tracking as
        // dragging down until they reach the 'natural' position
        if (scroll < 0 && !draggingUp && !draggingDown) {
            draggingDown = true;
            if (shouldScale) mViewGroup.setPivotY(mViewGroup.getHeight());
        } else if (scroll > 0 && !draggingDown && !draggingUp) {
            draggingUp = true;
            if (shouldScale) mViewGroup.setPivotY(0f);
        }
        // how far have we dragged relative to the distance to perform a dismiss
        // (0–1 where 1 = dismiss distance). Decreasing logarithmically as we approach the limit
        float dragFraction = (float) Math.log10(1 + (Math.abs(totalDrag) / dragDismissDistance));

        // calculate the desired translation given the drag fraction
        float dragTo = dragFraction * dragDismissDistance * dragElacticity;

        if (draggingUp) {
            // as we use the absolute magnitude when calculating the drag fraction, need to
            // re-apply the drag direction
            dragTo *= -1;
        }
        mViewGroup.setTranslationY(dragTo);

        if (shouldScale) {
            final float scale = 1 - ((1 - dragDismissScale) * dragFraction);
            if (enableScaleX) {
                mViewGroup.setScaleX(scale);
            }
            mViewGroup.setScaleY(scale);
        }

        // if we've reversed direction and gone past the settle point then clear the flags to
        // allow the list to get the scroll events & reset any transforms
        if ((draggingDown && totalDrag >= 0)
                || (draggingUp && totalDrag <= 0)) {
            totalDrag = dragTo = dragFraction = 0;
            draggingDown = draggingUp = false;
            mViewGroup.setTranslationY(0f);
            if (enableScaleX) {
                mViewGroup.setScaleX(1f);
            }
            mViewGroup.setScaleY(1f);
        }
        dispatchDragCallback(dragFraction, dragTo,
                Math.min(1f, Math.abs(totalDrag) / dragDismissDistance), totalDrag);
    }

    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (dragDismissFraction > 0f) {
            dragDismissDistance = h * dragDismissFraction;
        }
    }

    private void dispatchDragCallback(float elasticOffset, float elasticOffsetPixels,
                                      float rawOffset, float rawOffsetPixels) {
        if (callbacks != null && !callbacks.isEmpty()) {
            for (ElasticDragDismissCallback callback : callbacks) {
                callback.onDrag(elasticOffset, elasticOffsetPixels,
                        rawOffset, rawOffsetPixels);
            }
        }
    }

    private void dispatchDismissCallback() {
        if (callbacks != null && !callbacks.isEmpty()) {
            for (ElasticDragDismissCallback callback : callbacks) {
                callback.onDragDismissed();
            }
        }
    }

    public void addListener(ElasticDragDismissCallback listener) {
        if (callbacks == null) {
            callbacks = new ArrayList<>();
        }
        callbacks.add(listener);
    }

    public void removeListener(ElasticDragDismissCallback listener) {
        if (callbacks != null && callbacks.size() > 0) {
            callbacks.remove(listener);
        }
    }
}
