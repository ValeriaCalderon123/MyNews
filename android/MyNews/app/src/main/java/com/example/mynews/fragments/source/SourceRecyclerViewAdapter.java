package com.example.mynews.fragments.source;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynews.R;
import com.example.mynews.models.Source;

import java.util.ArrayList;
import java.util.List;

public class SourceRecyclerViewAdapter extends RecyclerView.Adapter<SourceRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Source> sources;

    public SourceRecyclerViewAdapter(ArrayList<Source> sources){
        this.sources = sources;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_source, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Source source = this.sources.get(position);
        holder.nameTextView.setText(source.getName());
        System.out.println(holder.calificationTextView + "++++++" + source.getCalification() +"------------------------------------------------------");
//        holder.calificationTextView.setText(source.getCalification());
        holder.calificationTextView.setText(source.getCalification() + "");
    }

    public void setDataSet(ArrayList<Source> sources){
        this.sources = sources;
        this.notifyDataSetChanged();
    }

    public ArrayList<Source> getDataSer(){
        return this.sources;
    }

    @Override
    public int getItemCount() {
        return this.sources.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView nameTextView;
        public final TextView calificationTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.nameTextView = (TextView) this.mView.findViewById(R.id.source_name);
            this.calificationTextView = (TextView) this.mView.findViewById(R.id.source_calification);
        }

    }
}