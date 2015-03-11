package com.example.andrewcavanagh.acproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.getbase.floatingactionbutton.AddFloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

interface DownloadTaskDelegate {
    void didCompleteDownloadTask(String jsonString);
}

public class MainActivity extends Activity implements DownloadTaskDelegate {

    private RecyclerView mPostsRecyclerView;
    private RecyclerView.Adapter mPostsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private AddFloatingActionButton mAddPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAddPostButton = (AddFloatingActionButton)findViewById(R.id.post_item_button);

        mAddPostButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        mPostsRecyclerView = (RecyclerView) findViewById(R.id.posts_list_view); // grab recycler view from activity_main layout
        mPostsRecyclerView.setHasFixedSize(true); // increase performance

        mLayoutManager = new LinearLayoutManager(this);
        mPostsRecyclerView.setLayoutManager(mLayoutManager);

        String[] data = { //data!
            "hello",
            "world",
        };

        mPostsAdapter = new PostsAdapter(data);
        mPostsRecyclerView.setAdapter(mPostsAdapter); // configure adapter to update UI

        NetworkManager.doThings(new CompletionHandler() {
            @Override
            public void handleResponse(JSONObject jsonObject) {
             Log.d("RESPONSE", jsonObject.toString());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void didCompleteDownloadTask(String jsonString) {

    }

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            //mImageView.setImageBitmap(imageBitmap);
        }
    }


}
