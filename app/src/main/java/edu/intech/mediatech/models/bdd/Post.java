package edu.intech.mediatech.models.bdd;

import java.util.Date;

public class Post {

    private int id;
    private String message;
    private String media;
    private Date date;
    private boolean facebook;
    private boolean twitter;
    private boolean linkedin;
    private boolean instagram;

    public Post(int id, String message, String media, Date date, boolean facebook, boolean twitter, boolean linkedin, boolean instagram) {
        this.id = id;
        this.message = message;
        this.media = media;
        this.date = date;
        this.facebook = facebook;
        this.twitter = twitter;
        this.linkedin = linkedin;
        this.instagram = instagram;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isFacebook() {
        return facebook;
    }

    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
    }

    public boolean isTwitter() {
        return twitter;
    }

    public void setTwitter(boolean twitter) {
        this.twitter = twitter;
    }

    public boolean isLinkedin() {
        return linkedin;
    }

    public void setLinkedin(boolean linkedin) {
        this.linkedin = linkedin;
    }

    public boolean isInstagram() {
        return instagram;
    }

    public void setInstagram(boolean instagram) {
        this.instagram = instagram;
    }
}
