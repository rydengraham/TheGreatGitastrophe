package com.example.cmput_301_project;

import android.widget.LinearLayout;
import android.widget.TextView;

public class TodayHabitViewHolder {
    private LinearLayout commentHolder;
    private LinearLayout iconLocationHolder;

    public TodayHabitViewHolder(LinearLayout commentHolder, LinearLayout iconLocationHolder) {
        super();
        this.commentHolder = commentHolder;
        this.iconLocationHolder = iconLocationHolder;
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
}
