package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.UUID;

public class HabitEvent {
    private String id;
    private Date date;
    private Bitmap image;
    private String comment;
    private String title;
    private TodayHabitViewHolder holder;

    public HabitEvent(Date date, Bitmap image, String comment, String title) {
        this.date = date;
        this.image = image;
        this.comment = comment;
        this.id = UUID.randomUUID().toString();
        this.title = title;
    }

    public HabitEvent(String comment, String title) {
        this.comment = comment;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
