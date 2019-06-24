package com.commit451.elasticdragdismisslayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent;

/**
 * Like {@link ElasticDragDismissFrameLayout} but its parent is a {@link android.widget.LinearLayout}
 */
public class ElasticDragDismissLinearLayout extends LinearLayout implements NestedScrollingParent {

    private ElasticDragDismissDelegate delegate;

    public ElasticDragDismissLinearLayout(Context context) {
        this(context, null, 0);
    }

    public ElasticDragDismissLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElasticDragDismissLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public ElasticDragDismissLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticDragDismissFrameLayout, 0, 0);
        delegate = new ElasticDragDismissDelegate(this);
        delegate.init(context, a);
        a.recycle();
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
        return delegate.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {
        delegate.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        delegate.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(@NonNull View child) {
        delegate.onStopNestedScroll(child);
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int nestedScrollAxes) {
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        delegate.onSizeChanged(w, h, oldw, oldh);
    }

    public void addListener(ElasticDragDismissCallback listener) {
        delegate.addListener(listener);
    }

    public void removeListener(ElasticDragDismissCallback listener) {
        delegate.removeListener(listener);
    }
}
