package com.example.project1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public adActivity activity;
    public List<MyModel> mList;


    public MyAdapter(adActivity activity, List<MyModel> mList){
        this.activity =activity;
        this.mList = mList;

    }

    public void setFilteredList(List<MyModel> filteredList){
        this.mList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(mList.get(position).getName());
        holder.email.setText(mList.get(position).getEmail());
        holder.museum.setText(mList.get(position).getMuseum());
        holder.date.setText(mList.get(position).getDate());
        holder.time.setText(mList.get(position).getTime());
        holder.person.setText(mList.get(position).getPerson());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email, museum, date, time, person;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            name = itemView.findViewById(R.id.name_text);
            email = itemView.findViewById(R.id.email_text);
            museum = itemView.findViewById(R.id.museum_text);
            date = itemView.findViewById(R.id.date_text);
            time = itemView.findViewById(R.id.time_text);
            person = itemView.findViewById(R.id.person_text);

        }
    }
}
