package com.example.root.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DBActivity extends AppCompatActivity {

    dbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        db = new dbHelper(this);
    }

    public void add(View view){
        EditText edt = (EditText)findViewById(R.id.msg);
        String msg = edt.getText().toString();
        db.insert_album(msg);
        edt.setText("");
        Toast.makeText(DBActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
    }

    public void list(View view){
        TextView txt = (TextView)findViewById(R.id.txt);
        txt.setText("");
        Cursor cr;
        cr = db.list_album();
        while(cr.moveToNext())
        {
            txt.append(cr.getString(0)+"\n");
        }
    }
}
