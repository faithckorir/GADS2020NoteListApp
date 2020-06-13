package com.finixtore.notelistapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final int POSITION_NOT_SET = -1;
    private Spinner spinner;
    public static final String NOTE_POSITION ="com.finixtore.notelistapp.NOTE_POSITION";
    private NoteInfo mNote;
    private boolean mIsNewNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spinner=findViewById(R.id.spinner_courses);
        List<CourseInfo> courses=DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item
                ,courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterCourses);
        readDisplayStateValues();
        EditText noteTitle=findViewById(R.id.note_title);
        EditText noteDescription=findViewById(R.id.note_description);
        if(!mIsNewNote)
        displayNote(courses,noteTitle,noteDescription);


    }

    private void displayNote(List<CourseInfo> courses, EditText noteTitle, EditText noteDescription) {

        List<CourseInfo> coursesinfo=DataManager.getInstance().getCourses();
        int courseIndex=coursesinfo.indexOf(mNote.getCourse());
        courses.set(courseIndex,mNote.getCourse());

        noteTitle.setText(mNote.getTitle());
        noteDescription.setText(mNote.getText());
    }


    private void readDisplayStateValues() {
        Intent intent=getIntent();
      int  position = intent.getIntExtra(NoteActivity.NOTE_POSITION, POSITION_NOT_SET);
        mIsNewNote = position==POSITION_NOT_SET;
        if (!mIsNewNote){
            mNote=DataManager.getInstance().getNotes().get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
