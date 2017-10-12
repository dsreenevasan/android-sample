package com.example.root.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class APICallActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apicall);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);

        assert batteryStatus != null;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level / (float)scale * 100;

        Toast.makeText(APICallActivity.this, String.valueOf(batteryPct) + "%", Toast.LENGTH_SHORT).show();

    }

    public void apiCall(View view) throws IOException, JSONException {
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        JSONObject jsonObject = null;
        InputStream inputStream = null;

        try{
            url = new URL("https://api.myjson.com/bins/d8klt");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp, response = "";

            while((temp = bReader.readLine()) != null){
                response += temp;
            }

            jsonObject = (JSONObject) new JSONTokener(response).nextValue();

            TextView txtView = (TextView)findViewById(R.id.textView);
            txtView.setText(jsonObject.toString());
        } catch (Exception e) {
            /*this.mException = e;*/
            Log.d("exception ----", e.toString());
        } finally {
            if (inputStream != null) {
                try {
                    // this will close the bReader as well
                    inputStream.close();
                } catch (IOException ignored) {
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
