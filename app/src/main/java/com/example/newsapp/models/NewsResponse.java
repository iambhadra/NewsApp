package com.example.newsapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewsResponse implements Serializable {
    @SerializedName("status")
    public String status;
    @SerializedName("totalResults")
    public Integer TotalResults;

    @SerializedName("articles")
    public List<Articles> Articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(Integer totalResults) {
        TotalResults = totalResults;
    }

    public List<Articles> getArticles() {
        return Articles;
    }

    public void setArticles(List<Articles> articles) {
        Articles = articles;
    }


}
