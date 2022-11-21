package com.uniajc.wikibird;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private ImageView profileIv, btnBack;
    private TextView bioTv, nameTv, countryTv, familyTv;

    private String recordID;

    private DBCRUD dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_card);

        btnBack = findViewById(R.id.btnBack);
        getSupportActionBar().hide();
        Intent intent = getIntent();
        recordID = intent.getStringExtra("RECORD_ID");

        dbHelper = new DBCRUD(this);

        profileIv = findViewById(R.id.profileIv);
        bioTv = findViewById(R.id.bioTv);
        nameTv = findViewById(R.id.nameTv);
        countryTv = findViewById(R.id.countryTv);
        familyTv = findViewById(R.id.familyTv);

        showRecordDetails();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(InfoActivity.this, Explorer.class);
                try {startActivity(intent);} catch (Exception e){
                    System.out.println("Error -> "+e);
                }
            }
        });


    }

    private void showRecordDetails() {
        String selectQuery = " SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.C_ID +" =\""+ recordID+"\"";
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {

                String id = ""+ cursor.getInt(cursor.getColumnIndex(Constants.C_ID));
                String name = ""+ cursor.getString(cursor.getColumnIndex(Constants.C_NAME));
                String image = ""+ cursor.getString(cursor.getColumnIndex(Constants.C_IMAGE));
                String bio = ""+ cursor.getString(cursor.getColumnIndex(Constants.C_BIO));
                String country = ""+ cursor.getString(cursor.getColumnIndex(Constants.C_COUNTRY));
                String family = ""+ cursor.getString(cursor.getColumnIndex(Constants.C_FAMILY));

                nameTv.setText(name);
                bioTv.setText(bio);
                countryTv.setText(country);
                familyTv.setText(family);

                if (image.equals("null")){
                    profileIv.setImageResource(R.drawable.ic_person_black);
                }
                else {
                    profileIv.setImageURI(Uri.parse(image));
                }


            }while(cursor.moveToNext());
        }
        db.close();
    }

}
