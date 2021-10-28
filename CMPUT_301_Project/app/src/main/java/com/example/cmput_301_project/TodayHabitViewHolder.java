package com.example.cmput_301_project;

import android.widget.LinearLayout;

public class TodayHabitViewHolder {
    private LinearLayout commentHolder;
    private LinearLayout iconLocationHolder;
    private LinearLayout eventHolder;
    private LinearLayout textHolder;
    private boolean completedButton = true;

    public TodayHabitViewHolder(LinearLayout commentHolder, LinearLayout iconLocationHolder , LinearLayout eventHolder, LinearLayout textHolder, boolean completedButton) {
        super();
        this.commentHolder = commentHolder;
        this.iconLocationHolder = iconLocationHolder;
        this.eventHolder = eventHolder;
        this.textHolder = textHolder;
        this.completedButton = completedButton;
    }

    public LinearLayout getCommentHolder() {
        return commentHolder;
    }

    public void setCommentHolder(LinearLayout commentHolder) {
        this.commentHolder = commentHolder;
    }

    public LinearLayout getIconLocationHolder() {
        return iconLocationHolder;
    }

    public void setIconLocationHolder(LinearLayout iconLocationHolder) {
        this.iconLocationHolder = iconLocationHolder;
    }

    public LinearLayout getEventHolder() {
        return eventHolder;
    }

    public void setEventHolder(LinearLayout eventHolder) {
        this.eventHolder = eventHolder;
    }

    public LinearLayout getTextHolder() {
        return textHolder;
    }

    public void setTextHolder(LinearLayout textHolder) {
        this.textHolder = textHolder;
    }

    public boolean getCompletedButton() {
        return completedButton;
    }

    public void setCompletedButton(boolean completedButton) {
        this.completedButton = completedButton;
    }

}
