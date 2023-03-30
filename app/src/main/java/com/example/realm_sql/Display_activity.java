package com.example.realm_sql;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class Display_activity extends AppCompatActivity {

    List<DataModal> dataModalList;

    Realm realm;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        realm = Realm.getDefaultInstance();
        recyclerView = findViewById(R.id.rc_Course);


        dataModalList = new ArrayList<>();
        dataModalList = realm.where(DataModal.class).findAll();

        recyclerViewAdapter = new RecyclerViewAdapter(dataModalList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(recyclerViewAdapter);
    }
}