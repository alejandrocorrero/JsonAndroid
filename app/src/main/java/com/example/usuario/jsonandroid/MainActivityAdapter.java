package com.example.usuario.jsonandroid;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivityAdapter extends ArrayAdapter<User> {

    private List<User> users;
    private final LayoutInflater layoutInflater;


    public MainActivityAdapter(Context contexto, List<User> users) {
        super(contexto, R.layout.activity_main_item, users);
        this.users = users;
        layoutInflater = LayoutInflater.from(contexto);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_main_item, parent, false);
            viewHolder = onCreateViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        onBindViewHolder(viewHolder, position);
        return convertView;
    }

    private ViewHolder onCreateViewHolder(View convertView) {
        return new ViewHolder(convertView);
    }

    private void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    static class ViewHolder {

        private final ImageView imgPhoto;
        private final TextView lblName;
        private final TextView lblGrade;
        private final TextView lblAge;


        public ViewHolder(View itemView) {
            imgPhoto = itemView.findViewById(R.id.ivUser);
            lblName = itemView.findViewById(R.id.lblName);
            lblGrade = itemView.findViewById(R.id.lblGrade);
            lblAge = itemView.findViewById(R.id.lblAge);

        }

        @SuppressLint("SetTextI18n")
        public void bind(User user) {
            lblName.setText(user.getName());
            lblGrade.setText(user.getGrade());
            lblAge.setText(user.getAge().toString());
            Picasso.with(imgPhoto.getContext()).load(user.getPhoto()).placeholder(
                    R.drawable.placeholder).error(R.drawable.placeholder).into(imgPhoto);


        }

    }

    @Override
    public int getCount() {
        return users == null ? 0 : users.size();
    }

    public void setData(List<User> students) {
        this.users = students;
        notifyDataSetChanged();
    }

}