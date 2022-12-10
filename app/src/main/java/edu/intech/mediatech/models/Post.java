package edu.intech.mediatech.models;

import java.util.Date;
import java.util.List;

public class Post {
    private String body;
    private List<String> medias;
    private Date date;
    private boolean facebook;
    private boolean twitter;
    private boolean instagram;
    private boolean linkedin;

    public Post(String body, List<String> medias, Date date, boolean facebook, boolean instagram, boolean linkedin, boolean twitter) {
        this.body = body;
        this.medias = medias;
        this.date = date;
        this.facebook = facebook;
        this.twitter = twitter;
        this.instagram = instagram;
        this.linkedin = linkedin;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getMedias() {
        return medias;
    }

    public void setMedias(List<String> medias) {
        this.medias = medias;
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

    public boolean isInstagram() {
        return instagram;
    }

    public void setInstagram(boolean instagram) {
        this.instagram = instagram;
    }

    public boolean isLinkedin() {
        return linkedin;
    }

    public void setLinkedin(boolean linkedin) {
        this.linkedin = linkedin;
    }

    @Override
    public String toString() {
        return "Post{" +
                "body='" + body + '\'' +
                ", medias=" + medias +
                ", date=" + date +
                ", facebook=" + facebook +
                ", twitter=" + twitter +
                ", instagram=" + instagram +
                ", linkedin=" + linkedin +
                '}';
    }
}
