package com.codetouch.pautas.models;

public class Schedule {

    private int id;
    private String title;
    private String description;
    private String details;
    private int authorId;
    private boolean status;

    public Schedule(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Schedule(int id, String title, String description, String details, int authorId, boolean status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.details = details;
        this.authorId = authorId;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
