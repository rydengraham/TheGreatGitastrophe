/**
 * Class to represent the habit event
 */
package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.UUID;

public class HabitEvent implements Serializable {
    private String id;
    private String date;
    private Bitmap image;
    private String comment;
    private String title;
    private TodayHabitViewHolder holder;
    private boolean isExpanded;
    private boolean completed;

    public HabitEvent(String date, String title) {
        this.date = date;
        this.id = UUID.randomUUID().toString();
        this.completed = false;
        this.title = title;
    }

    public HabitEvent(HabitEvent otherHabitEvent) {
        this.date = otherHabitEvent.getDate();
        this.id = otherHabitEvent.getId();
        this.completed = otherHabitEvent.isCompleted();
        this.title = otherHabitEvent.getTitle();
        this.image = otherHabitEvent.getImage();
        this.comment = otherHabitEvent.getComment();
        this.holder = otherHabitEvent.getHolder();
    }

    public HabitEvent() {
        /* required empty constructor */
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TodayHabitViewHolder getHolder() {
        return holder;
    }

    public void setHolder(TodayHabitViewHolder holder) {
        this.holder = holder;
    }
}
