package com.commit451.elasticdragdismisslayout;

import android.content.res.TypedArray;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.view.ViewGroup;
import android.widget.ScrollView;

/**
 * Checks the parent of the elastic layout to make sure it is a proper parent
 */
public class ElasticDragDismissLayoutParentChecker {

    public static void checkParent(ViewGroup viewGroup, TypedArray a) {
        boolean checkParent = true;
        if (a.hasValue(R.styleable.ElasticDragDismissFrameLayout_ignoreNestedScrollWarnings)) {
            checkParent = a.getBoolean(R.styleable.ElasticDragDismissFrameLayout_ignoreNestedScrollWarnings, false);
        }
        if (!checkParent) {
            return;
        }
        if (viewGroup.getParent() instanceof NestedScrollView) {
            if (((NestedScrollView) viewGroup.getParent()).isNestedScrollingEnabled()) {
                throw new IllegalStateException("You need to set nestedScrollingEnabled on the NestedScrollView");
            }
        } else if (viewGroup.getParent() instanceof ScrollView) {
            if (Build.VERSION.SDK_INT >= 21) {
                if (!((ScrollView) viewGroup.getParent()).isNestedScrollingEnabled()) {
                    throw new IllegalStateException("You need to set nestedScrollingEnabled on the ScrollView");

                }
            }
        }
    }
}
