package com.commit451.elasticdragdismisslayout.sample;

import android.annotation.TargetApi;
import android.view.Window;

import com.commit451.elasticdragdismisslayout.ElasticDragDismissListener;
import com.commit451.elasticdragdismisslayout.sample.util.ColorUtils;

/**
 * An {@link com.commit451.elasticdragdismisslayout.ElasticDragDismissListener} which fades system chrome (i.e. status bar and
 * navigation bar) when elastic drags are performed. Consuming classes must provide the
 * implementation for {@link com.commit451.elasticdragdismisslayout.ElasticDragDismissListener#onDragDismissed()}.
 */
@TargetApi(21)
public class SystemChromeFader implements ElasticDragDismissListener {

    private Window window;

    public SystemChromeFader(Window window) {
        this.window = window;
    }

    @Override
    public void onDrag(float elasticOffset, float elasticOffsetPixels,
                       float rawOffset, float rawOffsetPixels) {
        if (elasticOffsetPixels < 0) {
            // dragging upward, fade the navigation bar in proportion
            // TODO don't fade nav bar on landscape phones?
            window.setNavigationBarColor(ColorUtils.modifyAlpha(window.getNavigationBarColor(),
                    1f - rawOffset));
        } else if (elasticOffsetPixels == 0) {
            // reset
            window.setStatusBarColor(ColorUtils.modifyAlpha(window.getStatusBarColor(), 1f));
            window.setNavigationBarColor(
                    ColorUtils.modifyAlpha(window.getNavigationBarColor(), 1f));
        } else {
            // dragging downward, fade the status bar in proportion
            window.setStatusBarColor(ColorUtils.modifyAlpha(window
                    .getStatusBarColor(), 1f - rawOffset));
        }
    }

    public void onDragDismissed(){};
}