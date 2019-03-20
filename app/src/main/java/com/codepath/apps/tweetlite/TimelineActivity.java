package com.codepath.apps.tweetlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.tweetlite.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity {

private final int REQUEST_CODE = 20;

private TwitterClient client;
private RecyclerView rvTweets;
private TweetsAdapter adapter;
private List<Tweet> tweets;
private SwipeRefreshLayout swipeContainer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        client = TwitterApp.getRestClient(this);

        swipeContainer = findViewById(R.id.swipeContainer);
        //configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
                );

        //Find the recycler view
        rvTweets = findViewById(R.id.rvTweets);

        //Initialize the list of tweets and adapter from the data source
        tweets = new ArrayList<>();
        adapter = new TweetsAdapter(this, tweets);
        //Recycler View setup: layout manager and setting the adapter
        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        rvTweets.setAdapter(adapter);
        populateHomeTimeline();

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Twitter Client", "content is being refreshed");
                populateHomeTimeline();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    if(item.getItemId()==R.id.compose){
        //Tap on compose
        //Toast.makeText(this, "Compose", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, ComposeActivity.class);
        //Navigate to a new activity.
        //this.startActivity(i);
        startActivityForResult(i, REQUEST_CODE);
        return true;
    }
    return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //REQUEST_CODE is defined above.
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            //pull info out of the data intent (tweet)
            Tweet tweet = Parcels.unwrap(data.getParcelableExtra("tweet"));
            //update recycler view with this tweet
            tweets.add(0, tweet);
            adapter.notifyItemInserted(0);
            rvTweets.smoothScrollToPosition(0);

        }
    }

    private void populateHomeTimeline() {
        client.getHomeTimeline(new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                //Log.d("TwitterClient", response.toString());
                //iterate through list of tweets
                List<Tweet> tweetsToAdd = new ArrayList<>();
                for(int i=0; i< response.length();i++){
                    try {
                        JSONObject jsonTweetObject = response.getJSONObject(i);
                        //convert each json object into tweet object
                        Tweet tweet = Tweet.fromJson(jsonTweetObject);
                        //add the tweet into our data source
                        tweetsToAdd.add(tweet);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.clear();//clear existing data
                adapter.addTweets(tweetsToAdd);//show data received
                swipeContainer.setRefreshing(false);//signal that refresh has finished.

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("TwitterClient", responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.e("TwitterClient", errorResponse.toString());
            }

        });

    }
}
