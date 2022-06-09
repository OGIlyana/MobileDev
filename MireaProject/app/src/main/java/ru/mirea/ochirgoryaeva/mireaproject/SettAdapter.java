package ru.mirea.ochirgoryaeva.mireaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SettAdapter extends RecyclerView.Adapter<SettAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<SettingsList> settingsLists;

    SettAdapter(Context context, List<SettingsList> settingsLists) {
        this.settingsLists = settingsLists;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public SettAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_sett, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SettingsList setts = settingsLists.get(position);
        holder.nameView.setText(setts.name);
       // holder.postView.setText(setts.post);
    }

    @Override
    public int getItemCount() {
        return settingsLists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        //final TextView postView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.name);
           // postView = view.findViewById(R.id.post);
        }
    }

}
