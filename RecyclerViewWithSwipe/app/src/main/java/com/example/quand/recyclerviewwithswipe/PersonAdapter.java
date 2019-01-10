package com.example.quand.recyclerviewwithswipe;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Person> persons;
    private ArrayList<Person> temp;

    public PersonAdapter(Context context, ArrayList<Person> persons) {
        this.context = context;
        this.persons = persons;
        temp = persons;
    }

    public void setPersons(ArrayList<Person> p){
        this.persons = p;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(persons.get(i).getName());
        viewHolder.tvAddress.setText(persons.get(i).getAddress());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public void removeItem(int pos)
    {
        persons.remove(pos);
        notifyItemRemoved(pos);
    }

    public void addItem(Person person, int pos){
        persons.add(pos, person);
        notifyItemInserted(pos);
    }

    public ArrayList<Person> filter(String s) {
        if(s.trim().length() == 0)
            return temp;
        ArrayList<Person> arr = new ArrayList<>();
        for (Person p : temp) {
            if(p.getName().toLowerCase().contains(s.toLowerCase()))
                arr.add(p);
        }
        return arr;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvAddress;
        LinearLayout foreground, background;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAddress = itemView.findViewById(R.id.tv_address);
            foreground = itemView.findViewById(R.id.item_row_foreground);
            background = itemView.findViewById(R.id.item_row_background);

        }
    }

}
