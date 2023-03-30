package com.example.realm_sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText courseName,courseDuration,courseDescription,courseTrack;
    Button Submit,display;
    private Realm realm;


    private String courseName1,courseDuration1,courseDescription1,courseTrack1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        display = findViewById(R.id.disp);
        courseName = findViewById(R.id.course_name);
        courseDescription = findViewById(R.id.cour_desc);
        courseDuration = findViewById(R.id.cour_dur);
        courseTrack = findViewById(R.id.cour_track);
        Submit = findViewById(R.id.button);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                courseName1 = courseName.getText().toString().trim();
                courseDescription1 = courseDescription.getText().toString().trim();
                courseTrack1 = courseTrack.getText().toString().trim();
                courseDuration1 = courseDuration.getText().toString().trim();

                if(TextUtils.isEmpty(courseTrack1)){
                    courseTrack.setError("enter course track");
                }else if(TextUtils.isEmpty(courseName1)){
                    courseTrack.setError("enter course name");
                }else if(TextUtils.isEmpty(courseDescription1)){
                    courseTrack.setError("enter course description");
                }else if(TextUtils.isEmpty(courseDuration1)){
                    courseTrack.setError("enter course duration");
                }else {
                    addDataToDatabase(courseName1,courseDescription1,courseDuration1,courseTrack1);
                    Toast.makeText(MainActivity.this, "Data Added to db", Toast.LENGTH_SHORT).show();
                    courseName.setText("");
                    courseDescription.setText("");
                    courseDuration.setText("");
                    courseTrack.setText("");
                }
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Display_activity.class));
            }
        });

    }

    private void addDataToDatabase(String courseName1, String courseDescription1, String courseDuration1, String courseTrack1) {
        DataModal dataModal = new DataModal();

        Number id = realm.where( DataModal.class ).max("id");

        long nextId;

        if(id == null){
            nextId = 1;
        }else{
            nextId = id.intValue() + 1;
        }

        dataModal.setId(nextId);
        dataModal.setCourseDescription(courseDescription1);
        dataModal.setCourseDuration(courseDuration1);
        dataModal.setCourseName(courseName1);
        dataModal.setCourseTracks(courseTrack1);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(dataModal);
            }
        });
    }
}