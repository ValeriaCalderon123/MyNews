package com.example.mynews.fragments.usersAdmin;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynews.R;
import com.example.mynews.fragments.dummy.DummyContent.DummyItem;
import com.example.mynews.models.User;

import java.util.ArrayList;


public class UserRecyclerViewAdapter extends RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder> {

    private ArrayList<User> users;


    public UserRecyclerViewAdapter() {
        this.users = new ArrayList<User>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        User user = this.users.get(position);
        holder.usernameTextView.setText(user.getUsername());
        holder.fullNameTextView.setText(user.getLast_name() + ", " + user.getFirst_name());
    }

    public void setDataset(ArrayList<User> admins){
        this.users = admins;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView usernameTextView;
        public final TextView fullNameTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            this.usernameTextView = (TextView) view.findViewById(R.id.username_text_view);
            this.fullNameTextView = (TextView) view.findViewById(R.id.full_name_textview);
        }
    }
}