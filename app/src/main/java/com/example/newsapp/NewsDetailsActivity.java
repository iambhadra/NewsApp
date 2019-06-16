package com.example.newsapp;
/*
Title :- News Bazaar Application
Version :- 1.0.0
Usage :- This activity will show the detailed news of the selected topic.
Creator :- Veerabhadrarao kona
Date :- 16-06-2019
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.models.Articles;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import static com.example.newsapp.Global.DETAILEDNEWS;

public class NewsDetailsActivity extends AppCompatActivity {

    TextView tv_headline, tv_description, tv_content, tv_referenceLink, tv_publishDate, tv_source;
    ImageView news_image_imv;
    String headLine, description, content, referenceLink, publishDate, source;
    Articles newsDetails = new Articles();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        tv_description = (TextView) findViewById(R.id.description_tv);
        tv_headline = (TextView) findViewById(R.id.detail_headline_tv);
        tv_content = (TextView) findViewById(R.id.content_tv);
        tv_referenceLink = (TextView) findViewById(R.id.reference_link_tv);
        tv_publishDate = (TextView) findViewById(R.id.publish_date_tv);
        tv_source = (TextView) findViewById(R.id.source_tv);
        news_image_imv = (ImageView) findViewById(R.id.news_image_imv);
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        //This the another way to pass the data through bundles

      /*  if(bundle.getSerializable(DETAILEDNEWS)== null){
            Toast.makeText(NewsDetailsActivity.this,"Empty response",Toast.LENGTH_LONG).show();
        }else {
            newsDetails = (Articles) bundle.getSerializable(DETAILEDNEWS);
        }*/

        if (intent.getSerializableExtra(DETAILEDNEWS) == null) {
            Toast.makeText(NewsDetailsActivity.this, "Empty response", Toast.LENGTH_LONG).show();
        } else {
            newsDetails = (Articles) intent.getSerializableExtra(DETAILEDNEWS);
        }
        String Title = bundle.getString("data");

        Picasso.get()
                .load(newsDetails.getUrlToImage())
                .into(news_image_imv);

        headLine = newsDetails.getTitle();
        description = newsDetails.getDescription();
        content = newsDetails.getContent();
        referenceLink = newsDetails.getUrl();
        publishDate = newsDetails.getPublishedAt();
        source = newsDetails.getSource().getName();
        tv_headline.setText("NA");
        tv_description.setText("NA");
        tv_content.setText("NA");
        tv_referenceLink.setText("NA");
        tv_publishDate.setText("NA");
        tv_source.setText("NA");
        if (!headLine.isEmpty()) {
            tv_headline.setText("Head Line :-" + headLine);
        }
        if (!description.isEmpty()) {
            tv_description.setText("Description :-" + description);
        }
        if (!content.isEmpty()) {
            tv_content.setText("Content :-" + content);
        }
        if (!referenceLink.isEmpty()) {
            tv_referenceLink.setText("Reference :- " + referenceLink);
        }
        if (!publishDate.isEmpty()) {
            tv_publishDate.setText("Publish Date :- " + publishDate);
        }
        if (!source.isEmpty()) {
            tv_source.setText("Source Name :- " + source);
        }
        //This is another of doing in simlpe way(but not standard one)
        /*tv_description.setText((newsDetails.getDescription()==null)?"NA":newsDetails.getDescription());
        tv_content.setText((newsDetails.getContent()==null)?"NA ":newsDetails.getContent());
        tv_referenceLink.setText((newsDetails.getUrl()==null)?"NA ":newsDetails.getUrl());
        tv_publishDate.setText((newsDetails.getPublishedAt()==null)?"NA ":newsDetails.getPublishedAt());
        tv_source.setText((newsDetails.getSource().getName()==null)?"NA ":newsDetails.getSource().getName());
*/

    }
}
