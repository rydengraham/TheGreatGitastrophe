package com.example.cmput_301_project;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.UUID;

public class HabitEvent {
    private String id;
    private Date date;
    private Bitmap image;
    private String comment;

    public HabitEvent(Date date, Bitmap image, String comment) {
        this.date = date;
        this.image = image;
        this.comment = comment;
        this.id = UUID.randomUUID().toString();
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
}
