/**
 * Class to hold views for each of today's habits
 */
package com.example.cmput_301_project;

import android.widget.LinearLayout;

/**
 * View holder for the Today's Habits page.  Holds every layout.
 */
public class TodayHabitViewHolder {
    private LinearLayout commentHolder;
    private LinearLayout iconLocationHolder;
    private LinearLayout eventHolder;
    private LinearLayout textHolder;
    private boolean completedButton = true;

    /**
     * Constructor
     * @param commentHolder
     * @param iconLocationHolder
     * @param eventHolder
     * @param textHolder
     * @param completedButton
     */
    public TodayHabitViewHolder(LinearLayout commentHolder, LinearLayout iconLocationHolder , LinearLayout eventHolder, LinearLayout textHolder, boolean completedButton) {
        super();
        this.commentHolder = commentHolder;
        this.iconLocationHolder = iconLocationHolder;
        this.eventHolder = eventHolder;
        this.textHolder = textHolder;
        this.completedButton = completedButton;
    }

    /**
     * Returns the layout containing the comment
     * @return Comment holder layout
     */
    public LinearLayout getCommentHolder() {
        return commentHolder;
    }

    /**
     * Set the layout containing the comment
     * @param commentHolder
     */
    public void setCommentHolder(LinearLayout commentHolder) {
        this.commentHolder = commentHolder;
    }

    /**
     * Returns the layout containing the icon and location buttons
     * @return Icon and location holder
     */
    public LinearLayout getIconLocationHolder() {
        return iconLocationHolder;
    }

    /**
     * Sets the layout containing the icon and location buttons
     * @param iconLocationHolder
     */
    public void setIconLocationHolder(LinearLayout iconLocationHolder) {
        this.iconLocationHolder = iconLocationHolder;
    }

    /**
     * Returns the layout containing the whole layout
     * @return Event holder
     */
    public LinearLayout getEventHolder() {
        return eventHolder;
    }

    /**
     * Sets the layout containing the whole layout
     * @param eventHolder
     */
    public void setEventHolder(LinearLayout eventHolder) {
        this.eventHolder = eventHolder;
    }

    /**
     * Returns the layout holding the text field
     * @return Text holder
     */
    public LinearLayout getTextHolder() {
        return textHolder;
    }

    /**
     * Sets the layout containing the text field
     * @param textHolder
     */
    public void setTextHolder(LinearLayout textHolder) {
        this.textHolder = textHolder;
    }

    /**
     * Returns if the button in the layout is set to complete
     * @return True if completed
     */
    public boolean getCompletedButton() {
        return completedButton;
    }

    /**
     * Sets the button completion
     * @param completedButton
     */
    public void setCompletedButton(boolean completedButton) {
        this.completedButton = completedButton;
    }
}
