package com.example.andrewcavanagh.acproject;

import android.os.AsyncTask;
import android.os.Debug;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by andrewcavanagh on 3/7/15.
 */

class CompletionHandler {
    public void handleResponse(JSONObject jsonObject) {}
}

public class NetworkManager {

    private static NetworkManager instance = null;

    public NetworkManager() {}
    public static NetworkManager sharedInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    public static void doThings(CompletionHandler completionHandler) {
        new NetworkManager.DownloadTask(completionHandler).execute("http://ip.jsontest.com");
    }

    public static final class DownloadTask extends AsyncTask<String, Void, String> {

        private static final String TAG = "DownloadTask";

        private CompletionHandler handler;
        private JSONObject jsonObject;

        public DownloadTask(CompletionHandler handler) {
            this.handler = handler;
        }

        @Override
        protected String doInBackground(String... urls) {
            try {
                dataForURL(urls[0]);
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) { //called on main thread.
            super.onPostExecute(s);
            this.handler.handleResponse(this.jsonObject);
        }

        public void dataForURL(String urlString) throws IOException {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            try {
                InputStream input  = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder stringBuilder = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }

                this.jsonObject = new JSONObject(stringBuilder.toString());

                input.close();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }
        }
    }
}
