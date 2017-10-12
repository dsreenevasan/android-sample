package com.example.root.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoActivity(View view){

        Intent intent = null;
        switch(view.getId()){
            case R.id.login : {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                break;
            }

            case R.id.signup : {
                intent = new Intent(MainActivity.this, SignupActivity.class);
                break;
            }

            case R.id.camera : {
                intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                break;
            }

            case R.id.service : {
                intent = new Intent(MainActivity.this, ServiceActivity.class);
                break;
            }

            case R.id.apiCall : {
                intent = new Intent(MainActivity.this, APICallActivity.class);
                break;
            }

            case R.id.db : {
                intent = new Intent(MainActivity.this, DBActivity.class);
                break;
            }
        }

        startActivity(intent);
    }
}
