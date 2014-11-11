package ru.ifmo.md.colloquium2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class VoteAdapter extends BaseAdapter {

    List<String> candidates;
    List<Integer> votes;
    Context context;
    LayoutInflater inflater;

    public VoteAdapter(List<String> candidates, List<Integer> votes, Context context) {
        this.candidates = candidates;
        this.votes = votes;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return candidates.size();
    }

    @Override
    public Object getItem(int position) {
        return candidates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.candidate, null, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        String candidate = candidates.get(position);
        name.setText(candidate);


        TextView vote = (TextView) convertView.findViewById(R.id.number_of_votes);
        Integer numberOfVotes = votes.get(position);
        vote.setText("" + numberOfVotes);

        return convertView;
    }


}
