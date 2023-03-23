package com.unicon.mini;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    Context context;
    List<String> names;
    List<String> description;
    List<String> images;
    LayoutInflater  layoutInflater;
    public CustomListAdapter(Context context, ArrayList<String> names, ArrayList<String> description,ArrayList<String> images){
        this.context = context;
        this.names = names;
        this.description = description;
        this.images = images;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.remedy_item,null);
        TextView TV_name= view.findViewById(R.id.tv_plant_name);
        TextView TV_description= view.findViewById(R.id.tv_plant_description);
        ImageView IV_leaf_image= view.findViewById(R.id.iv_plant_image);
        TV_name.setText(names.get(i));
        TV_description.setText(description.get(i));
//        IV_leaf_image.setImageURI(null);
//        IV_leaf_image.setImageURI(Uri.parse(images.get(i)));
        Glide.with(context)
                .load(images.get(i))
                .into(IV_leaf_image);
        return view;
    }
}
