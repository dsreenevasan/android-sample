package com.example.root.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.simple.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) throws ParseException, JSONException {
        String email = ((EditText)findViewById(R.id.email)).getText().toString();
        String password = ((EditText)findViewById(R.id.password)).getText().toString();

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        JSONParser parser = new JSONParser();
        JSONArray details = new JSONArray();

        if(sharedPreferences.getString("userDetails", null) != null){
            details = (JSONArray) parser.parse(sharedPreferences.getString("userDetails", null));
        } else {
            Log.d("Tag" , "Outside -aa");
            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
        }

        if(details != null){
            for(int i=0; i < details.size(); i++){
                JSONObject each = (JSONObject) details.get(i);
                if (java.util.Objects.equals(each.get("password"), password) && java.util.Objects.equals(each.get("email"), email)) {
                        Log.d("Tag", "Inside");
                        Toast.makeText(LoginActivity.this, "Logged in Successfully", Toast.LENGTH_LONG).show();
                        break;
                    }

                if(i == details.size()-1){
                    Log.d("Tag" , "Inside else");
                    Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            Log.d("Tag" , "Outside - else");
            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_LONG).show();
        }

        Log.d("Tag" , "Outside");
    }

    public void signUp(View view){
        intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
    }
}
