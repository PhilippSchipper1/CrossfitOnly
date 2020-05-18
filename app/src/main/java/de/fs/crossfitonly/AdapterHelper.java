package de.fs.crossfitonly;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AdapterHelper extends ArrayAdapter<Sets>{
    private static class ViewHolder {
        private TextView sets;
        private TextView reps;
        private TextView weight;
    }

    public AdapterHelper(Context context, int textViewResourceId, ArrayList<Sets> items) {
        super(context, textViewResourceId, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.lv_complex_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.sets = (TextView) convertView.findViewById(R.id.textView);
            viewHolder.reps = (TextView) convertView.findViewById(R.id.textView3);
            viewHolder.weight = (TextView) convertView.findViewById(R.id.textView2);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Sets item = getItem(position);


        if(item != null) {
            viewHolder.sets.setText(item.getSets() +" times");
            viewHolder.reps.setText(item.getReps()+ " reps");
            viewHolder.weight.setText(item.getWeight()+" kg");


            Log.d(TAG,"TEST:"+item.getReps()+item.getSets() + item.getReps());
        }

        return convertView;
    }
}
