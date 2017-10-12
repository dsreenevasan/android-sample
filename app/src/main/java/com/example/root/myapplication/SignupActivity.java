package com.example.root.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.LinkedHashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void signUp(View view) throws JSONException, ParseException {
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp_edit = sharedPreferences.edit();
        JSONArray details = new JSONArray();
        JSONParser parser = new JSONParser();
        if(sharedPreferences.getString("userDetails", null) == null){
            sp_edit.putString("userDetails", details.toString());
        } else {
            details = (JSONArray) parser.parse(sharedPreferences.getString("userDetails", null));
        }

        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        JSONObject user_details = new JSONObject();
        user_details.put("name", name);
        user_details.put("email", email);
        user_details.put("password", password);
        details.add(user_details);

       /* Map<String, String> details = new LinkedHashMap<String, String>();

        if(sharedPreferences.getString("userDetails", null) == null){
            sp_edit.putString("userDetails", details.toString());
        } else {
            details = Splitter.on(" ").withKeyValueSeparator("=").split(sharedPreferences.getString("userDetails", null));
        }

        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();*/

       Log.d("Details ---- ", details.toString());

        sp_edit.putString("userDetails", details.toString());
        sp_edit.apply();
        Toast.makeText(SignupActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();


    }
}
