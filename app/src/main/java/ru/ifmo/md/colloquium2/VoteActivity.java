package ru.ifmo.md.colloquium2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class VoteActivity extends ActionBarActivity {

    Intent intent;
    SQLiteHelper helper = new SQLiteHelper(this);
    SQLiteDatabase db;
    ArrayList<String> candidates = new ArrayList<String>();
    ArrayList<Integer> votes = new ArrayList<Integer>();
    int all_votes = 0;
    public static ListView view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vote_layout);
        //intent = new Intent(this, MainActivity.class);
        //candidates = getIntent().getStringArrayListExtra("candidates");
        db = helper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int nameIndex = c.getColumnIndex("candidate");
            int votesIndex = c.getColumnIndex("votes");
            do {
                candidates.add(c.getString(nameIndex));
                votes.add(c.getInt(votesIndex));
            } while (c.moveToNext());
        }
        view = (ListView) findViewById(R.id.listView);
        view.setAdapter(new VoteAdapter(candidates, votes, this));
        view.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VoteAdapter adapter = (VoteAdapter) parent.getAdapter();
                Integer tmp = votes.get(position);
                tmp++;
                all_votes++;
                votes.set(position, tmp);
                adapter.notifyDataSetChanged();
            }
        });
        helper.close();
    }
}
