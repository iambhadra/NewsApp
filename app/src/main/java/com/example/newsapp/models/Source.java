package com.example.newsapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Source implements Serializable {
    @SerializedName("id")
    public String ID;
    @SerializedName("name")
    public String name;
    @SerializedName("description")
    public String description;
    @SerializedName("url")
    public String url;
    @SerializedName("category")
    public String category;
    @SerializedName("language")
    public String language;
    @SerializedName("country")
    public String country;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
