package com.codetouch.pautas.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Schedule implements Parcelable {

    private int id;
    private String title;
    private String description;
    private String details;
    private int authorId;
    private boolean status;

    public Schedule(String title, String description, String details, int authorId) {
        this.title = title;
        this.description = description;
        this.details = details;
        this.authorId = authorId;
    }

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.details);
        dest.writeInt(this.authorId);
        dest.writeByte(this.status ? (byte) 1 : (byte) 0);
    }

    protected Schedule(Parcel in) {
        this.id = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
        this.details = in.readString();
        this.authorId = in.readInt();
        this.status = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Schedule> CREATOR = new Parcelable.Creator<Schedule>() {
        @Override
        public Schedule createFromParcel(Parcel source) {
            return new Schedule(source);
        }

        @Override
        public Schedule[] newArray(int size) {
            return new Schedule[size];
        }
    };
}
