package com.example.newsapp.retrofit_connection;



import com.example.newsapp.models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConnectionInterface {
    @GET("top-headlines")
    Call<NewsResponse> getTopHeadlines(@Query("country")String country, @Query("apiKey")String apiKey);

    @GET("everything")
    Call<NewsResponse> getEveryNews(@Query("q") String topic,@Query("apiKey") String apiKey);


    @GET("sources")
    Call<NewsResponse> getSmallNews(@Query("language") String language,@Query("country") String country, @Query("api_key") String apiKey);
}

