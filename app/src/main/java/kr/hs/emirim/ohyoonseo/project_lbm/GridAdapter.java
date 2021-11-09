package kr.hs.emirim.ohyoonseo.project_lbm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    private ArrayList<testDate> td;

    public GridAdapter(Context context, ArrayList<testDate> td){
        this.context = context;
        this.td = td;
    }
    @Override
    public int getCount() {
        return td.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each list item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.sub2_grid_items, parent, false);

        // Get the TextView and ImageView from CustomView for displaying item
        TextView txtview = (TextView) convertView.findViewById(R.id.sub2_letter_text);
        ImageView imgview = (ImageView) convertView.findViewById(R.id.sub2_letter_img);

        // Set the text and image for current item using data from map list
        txtview.setText(td.get(position).date);
        return convertView;
    }

}
