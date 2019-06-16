package com.example.newsapp;
/*
Title :- News Bazaar Application
Version :- 1.0.0
Usage :- This activity is the starting point for the application.
Creator :- Veerabhadrarao kona
Date :- 16-06-2019
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.newsapp.adapter.NewsListAdapter;
import com.example.newsapp.models.Articles;
import com.example.newsapp.models.NewsResponse;
import com.example.newsapp.retrofit_connection.Connection;
import com.example.newsapp.retrofit_connection.ConnectionInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.newsapp.Global.DETAILEDNEWS;
import static com.example.newsapp.Global.EVERYNEWS;
import static com.example.newsapp.Global.TOPRATED;


public class MainActivity extends AppCompatActivity implements NewsListAdapter.newsItemClickListener {
    public static final String APIKEY = null;

    RecyclerView newsList_rv;
    List<Articles> news = new ArrayList<Articles>();
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsList_rv = (RecyclerView) findViewById(R.id.news_list_rv);
        loadNewsList(TOPRATED);
    }

    private void loadNewsList(String category) {
        ConnectionInterface apiService = Connection.getClient().create(ConnectionInterface.class);
        Call<NewsResponse> call = null;
        if (category.equalsIgnoreCase(TOPRATED)) {
            call = apiService.getTopHeadlines("us", APIKEY);
        } else {
            call = apiService.getEveryNews(category, APIKEY);
        }

        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response != null) {
                    news = response.body().getArticles();
                    Log.d("Number of News: ", Integer.toString(news.size()));
                    if (news != null && news.size() > 0) {
                        onSuccess(news, response.code());
                    }
                } else {
                    Log.d("resp is not received", "null");
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e("exception", t.toString());
            }
        });

    }

    private void onSuccess(List<Articles> Article, int code) {
        NewsListAdapter mAdapter = new NewsListAdapter(this, Article);
        //   GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        newsList_rv.setHasFixedSize(true);
        newsList_rv.setLayoutManager(layoutManager);
        newsList_rv.setAdapter(mAdapter);

    }

    @Override
    public void onNewsItemClicked(int iPosition) {

        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(this, "News Name you clicked is " + news.get(iPosition).getTitle(), Toast.LENGTH_SHORT);
        mToast.show();
        Intent intent = new Intent(MainActivity.this, NewsDetailsActivity.class);
        Bundle bundle = new Bundle();
        Articles selectedNews = news.get(iPosition);
        intent.putExtra(DETAILEDNEWS, (Serializable) selectedNews);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.sort, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.everynews:
                showOptionsDialog();
                loadNewsList(EVERYNEWS);
                break;
            case R.id.toprated:
                loadNewsList(TOPRATED);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
/*This dialog box will give an oppurtunity to reader for selecting his intreseted topic and search accordingly*/
    private void showOptionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Enter your topic :-");
        final EditText topicInput = new EditText(this);
        topicInput.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(topicInput);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String enteredTpoic = topicInput.getText().toString();
                loadNewsList(enteredTpoic);
            }
        });
        builder.show();



    }
}
