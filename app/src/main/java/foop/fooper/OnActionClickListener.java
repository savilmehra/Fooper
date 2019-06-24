package foop.fooper;

import android.view.View;

/**
 * The OnActionClickListener will be notified if a Button is clicked on the Card.
 */
public interface OnActionClickListener {
    /**
     * An action view is clicked.
     *
     * @param view which was clicked.
     * @param card where the view was clicked on.
     */
    void onActionClicked(View view, Card card);


    void onMainActionClicked();
}
