package com.yaseen.homage.models;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.yaseen.homage.fragments.SettingsFragment;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by macbookair on 3/25/17.
 */

public class Visit implements Serializable{

    private String username;
    private String profilePhoto;
    private String date;
    private String address;
    private String instructions;
    private String billingEstimation;

    public Visit(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getBillingEstimation() {
        return billingEstimation;
    }

    public void setBillingEstimation(String billingEstimation) {
        this.billingEstimation = billingEstimation;
    }
}
