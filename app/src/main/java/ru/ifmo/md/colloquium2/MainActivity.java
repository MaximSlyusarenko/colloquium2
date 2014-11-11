package ru.ifmo.md.colloquium2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> candidates = new ArrayList<String>();
    ArrayList<Integer> votes = new ArrayList<Integer>();
    SQLiteDatabase db;
    SQLiteHelper helper = new SQLiteHelper(this);
    ContentValues cv = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onAdd(View view) {
        db = helper.getWritableDatabase();
        EditText candidate = (EditText) findViewById(R.id.newChannelLink);
        String name = candidate.getText().toString();
        candidate.setText("");
        cv.put("candidate", name);
        cv.put("votes", 0);
        long rowID = db.insert("mytable", null, cv);
        candidates.add(name);
        votes.add(0);
        helper.close();
    }

    public void onStart(View view) {
        Intent intent = new Intent(MainActivity.this, VoteActivity.class);
        startActivity(intent);
    }

    public void onDelete(View view) {
        db = helper.getWritableDatabase();
        EditText candidate = (EditText) findViewById(R.id.deleteCandidate);
        String name = candidate.getText().toString();
        int clearCount = db.delete("mytable", "candidate LIKE '" + name + "'", null);
        candidate.setText("");
        helper.close();
    }
}
