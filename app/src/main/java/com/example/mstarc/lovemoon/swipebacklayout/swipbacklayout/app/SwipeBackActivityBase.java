package com.example.mstarc.lovemoon.swipebacklayout.swipbacklayout.app;

import com.example.mstarc.lovemoon.swipebacklayout.swipbacklayout.SwipeBackLayout;
/**
 * @author Yrom
 */
public interface SwipeBackActivityBase {
    /**
     * @return the SwipeBackLayout associated with this activity.
     */
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    /**
     * Scroll out contentView and finish the activity
     */
    public abstract void scrollToFinishActivity();

}
