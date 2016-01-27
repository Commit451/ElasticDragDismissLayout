/*
 * Copyright 2015 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.commit451.elasticdragdismisslayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;



/**
 * A {@link FrameLayout} which responds to nested scrolls to create drag-dismissable layouts.
 * Applies an elasticity factor to reduce movement as you approach the given dismiss distance.
 * Optionally also scales down content during drag.
 */
public class ElasticDragDismissFrameLayout extends FrameLayout implements NestedScrollingParent {

    private ElasticDragDismissDelegate mDelegate;

    public ElasticDragDismissFrameLayout(Context context) {
        this(context, null, 0);
    }

    public ElasticDragDismissFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ElasticDragDismissFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public ElasticDragDismissFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticDragDismissFrameLayout, 0, 0);

        boolean checkParent = true;
        if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_ignoreNestedScrollWarnings)) {
            checkParent = a.getBoolean(R.styleable.ElasticDragDismissFrameLayout_ignoreNestedScrollWarnings, false);
        }
        if (checkParent) {
            checkParent();
        }
        mDelegate = new ElasticDragDismissDelegate(this);
        mDelegate.init(context, a);
        a.recycle();
    }

    private void checkParent() {
        if (getParent() instanceof NestedScrollView) {
            if (((NestedScrollView) getParent()).isNestedScrollingEnabled()) {
                throw new IllegalStateException("You need to set nestedScrollingEnabled on the NestedScrollView");
            }
        } else if (getParent() instanceof ScrollView) {
            if (Build.VERSION.SDK_INT >= 21) {
                if (!((ScrollView) getParent()).isNestedScrollingEnabled()) {
                    throw new IllegalStateException("You need to set nestedScrollingEnabled on the ScrollView");

                }
            }
        }
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return mDelegate.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        mDelegate.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        mDelegate.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(View child) {
        mDelegate.onStopNestedScroll(child);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) { }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
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

    public void addListener(ElasticDragDismissListener listener) {
        mDelegate.addListener(listener);
    }

    public void removeListener(ElasticDragDismissListener listener) {
        mDelegate.removeListener(listener);
    }
}