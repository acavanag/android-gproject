package com.example.andrewcavanagh.acproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    private RecyclerView mPostsRecyclerView;
    private RecyclerView.Adapter mPostsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    

}
