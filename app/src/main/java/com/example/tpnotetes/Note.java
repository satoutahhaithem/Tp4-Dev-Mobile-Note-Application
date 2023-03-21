package com.example.tpnotetes;

import java.util.Date;

public class Note {
    private String note;
    private Date date;

    public Note(String note, Date date) {
        this.note = note;
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
