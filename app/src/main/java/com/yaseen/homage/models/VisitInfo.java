package com.yaseen.homage.models;

/**
 * Created by macbookair on 3/25/17.
 */

public class VisitInfo {
    private String primaryText;
    private String secondaryText;
    private int imageID;

    public VisitInfo(){

    }

    public String getPrimaryText() {
        return primaryText;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
