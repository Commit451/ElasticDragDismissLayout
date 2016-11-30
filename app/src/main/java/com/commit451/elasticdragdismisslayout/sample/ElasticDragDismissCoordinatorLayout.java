package com.commit451.elasticdragdismisslayout.sample;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.NestedScrollingParent;
import android.util.AttributeSet;
import android.view.View;

import com.commit451.elasticdragdismisslayout.ElasticDragDismissDelegate;
import com.commit451.elasticdragdismisslayout.ElasticDragDismissCallback;

public class ElasticDragDismissCoordinatorLayout extends CoordinatorLayout implements NestedScrollingParent {

    private ElasticDragDismissDelegate mDelegate;

    public ElasticDragDismissCoordinatorLayout(Context context) {
        this(context, null, 0);
    }

    public ElasticDragDismissCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElasticDragDismissCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticDragDismissFrameLayout, 0, 0);
        mDelegate = new ElasticDragDismissDelegate(this);
        mDelegate.init(context, a);
        a.recycle();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        super.onStartNestedScroll(child, target, nestedScrollAxes);
        return mDelegate.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        mDelegate.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        mDelegate.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
        mDelegate.onStopNestedScroll(child);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {
        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        super.onNestedPreFling(target, velocityX, velocityY);
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mDelegate.onSizeChanged(w, h, oldw, oldh);
    }

    public void addListener(ElasticDragDismissCallback listener) {
        mDelegate.addListener(listener);
    }

    public void removeListener(ElasticDragDismissCallback listener) {
        mDelegate.removeListener(listener);
    }
}