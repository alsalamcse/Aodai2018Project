package com.badran.aodai.aodai2018project.data;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by aodai on 12/03/2018.
 */

public class PlaceInfo {

    private String name;
    private String adress;
    private String PhoneNumber;
    private String Id;
    private Uri websiteUri;
    private LatLng latLng;
    private float rating;
    private String attributions;


    public PlaceInfo(String name, String adress, String phoneNumber, String id, Uri websiteUri, LatLng latLng, float rating, String attributions) {
        this.name = name;
        this.adress = adress;
        PhoneNumber = phoneNumber;
        Id = id;
        this.websiteUri = websiteUri;
        this.latLng = latLng;
        this.rating = rating;
        this.attributions = attributions;
    }

    public PlaceInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Uri getWebsiteUri() {
        return websiteUri;
    }

    public void setWebsiteUri(Uri websiteUri) {
        this.websiteUri = websiteUri;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getAttributions() {
        return attributions;
    }

    public void setAttributions(String attributions) {
        this.attributions = attributions;
    }

    @Override
    public String toString() {
        return "PlaceInfo{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Id='" + Id + '\'' +
                ", websiteUri=" + websiteUri +
                ", latLng=" + latLng +
                ", rating=" + rating +
                ", attributions='" + attributions + '\'' +
                '}';
    }
}


