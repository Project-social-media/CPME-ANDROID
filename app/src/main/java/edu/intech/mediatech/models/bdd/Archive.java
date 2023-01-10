package edu.intech.mediatech.models.bdd;

import java.util.Date;

public class Archive {

    private String _id;
    private String message;
    private String media;
    private Date date;
    private boolean idMessageFacebook;
    private boolean idMessageTwitter;
    private boolean idMessageLinkedin;
    private boolean idMessageInstagram;



    public Archive(String _id, String message, String media, Date date, boolean idMessageFacebook, boolean idMessageTwitter, boolean idMessageLinkedin, boolean idMessageInstagram) {
        this._id = _id;
        this.message = message;
        this.media = media;
        this.date = date;
        this.idMessageFacebook = idMessageFacebook;
        this.idMessageTwitter = idMessageTwitter;
        this.idMessageLinkedin = idMessageLinkedin;
        this.idMessageInstagram = idMessageInstagram;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public boolean isIdMessageFacebook() {
        return idMessageFacebook;
    }

    public void setIdMessageFacebook(boolean idMessageFacebook) {
        this.idMessageFacebook = idMessageFacebook;
    }

    public boolean isIdMessageTwitter() {
        return idMessageTwitter;
    }

    public void setIdMessageTwitter(boolean idMessageTwitter) {
        this.idMessageTwitter = idMessageTwitter;
    }

    public boolean isIdMessageLinkedin() {
        return idMessageLinkedin;
    }

    public void setIdMessageLinkedin(boolean idMessageLinkedin) {
        this.idMessageLinkedin = idMessageLinkedin;
    }

    public boolean isIdMessageInstagram() {
        return idMessageInstagram;
    }

    public void setIdMessageInstagram(boolean idMessageInstagram) {
        this.idMessageInstagram = idMessageInstagram;
    }

    @Override
    public String toString() {
        return "Archive{" +
                "_id='" + _id + '\'' +
                ", message='" + message + '\'' +
                ", media='" + media + '\'' +
                ", date=" + date +
                ", idMessageFacebook=" + idMessageFacebook +
                ", idMessageTwitter=" + idMessageTwitter +
                ", idMessageLinkedin=" + idMessageLinkedin +
                ", idMessageInstagram=" + idMessageInstagram +
                '}';
    }
}
