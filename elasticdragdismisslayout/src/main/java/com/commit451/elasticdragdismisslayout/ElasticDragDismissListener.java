package com.commit451.elasticdragdismisslayout;

/**
 * Listener for listening for drag events on ElasticLayouts
 */
public interface ElasticDragDismissListener {

    /**
     * Called for each drag event.
     *
     * @param elasticOffset       Indicating the drag offset with elasticity applied i.e. may
     *                            exceed 1.
     * @param elasticOffsetPixels The elastically scaled drag distance in pixels.
     * @param rawOffset           Value from [0, 1] indicating the raw drag offset i.e.
     *                            without elasticity applied. A value of 1 indicates that the
     *                            dismiss distance has been reached.
     * @param rawOffsetPixels     The raw distance the user has dragged
     */
    void onDrag(float elasticOffset, float elasticOffsetPixels,
                float rawOffset, float rawOffsetPixels);

    /**
     * Called when dragging is released and has exceeded the threshold dismiss distance.
     */
    void onDragDismissed();

}