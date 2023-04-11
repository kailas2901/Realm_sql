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

public class Update_course extends AppCompatActivity {

    private EditText courseNameEdt, courseDurationEdt, courseDescriptionEdt, courseTracksEdt;

    private String courseName, courseDuration, courseDescription, courseTracks;
    private Realm realm;
    private long id;
    private Button Update,Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        realm = Realm.getDefaultInstance();
        Update = findViewById(R.id.b_update);
        Delete = findViewById(R.id.delete);



        courseNameEdt = findViewById(R.id.up_name);
        courseDurationEdt = findViewById(R.id.up_duration);
        courseTracksEdt = findViewById(R.id.up_tracks);
        courseDescriptionEdt = findViewById(R.id.up_desc);

        courseName = getIntent().getStringExtra("coursename");
        courseDuration = getIntent().getStringExtra("courseduration");
        courseTracks = getIntent().getStringExtra("coursetrack");
        courseDescription = getIntent().getStringExtra("coursedescription");
        id = getIntent().getLongExtra("id", 0);

        courseNameEdt.setText(courseName);
        courseDurationEdt.setText(courseDuration);
        courseTracksEdt.setText(courseTracks);
        courseDescriptionEdt.setText(courseDescription);

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCourse(id);
                startActivity(new Intent(Update_course.this,Display_activity.class));
                finish();

            }
        });


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String courseName = courseNameEdt.getText().toString();
                String courseDescription = courseDescriptionEdt.getText().toString();
                String courseDuration = courseDurationEdt.getText().toString();
                String courseTracks = courseTracksEdt.getText().toString();

                if(TextUtils.isEmpty(courseName)){
                    courseNameEdt.setError("invalid text");
                }else if(TextUtils.isEmpty(courseDuration)){
                    courseDurationEdt.setError("enter text");
                }else if(TextUtils.isEmpty(courseTracks)){
                    courseTracksEdt.setError("enter text");
                }else if(TextUtils.isEmpty(courseDescription)){
                    courseDescriptionEdt.setError("enter text");
                }else {
                    final DataModal modal = realm.where(DataModal.class).equalTo("id", id).findFirst();
                    updateCourse(modal, courseTracks, courseDuration, courseDescription, courseName);
                }
                Toast.makeText(Update_course.this, "data updated", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Update_course.this,Display_activity.class));
                finish();



            }
        });




    }

    private void deleteCourse(long id) {
        DataModal modal = realm.where(DataModal.class).equalTo("id",id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modal.deleteFromRealm();
            }
        });

    }


    private void updateCourse(DataModal modal, String courseTracks, String courseDuration, String courseDescription, String courseName) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                modal.setCourseDuration(courseDuration);
                modal.setCourseName(courseName);
                modal.setCourseDescription(courseDescription);
                modal.setCourseTracks(courseTracks);

                realm.copyToRealmOrUpdate(modal);

            }
        });

    }


}