package com.example.esc.moviestwopane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import Beans.Review;
import Beans.Trailer;
import Controller.Globals;
import Holders.TrailerHolder;

/**
 * Created by ESC on 9/16/2016.
 */
public class DetailsAdapter extends ArrayAdapter<Object> {

    public List<Object> objects;
    private Context context;
    public static final int viewType_trailer = 0;
    public static final int viewType_review = 1;
    public static final int viewType_count = 2;

    public DetailsAdapter(Context context, int resource, List<Object> objects) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {

        return (objects.get(position).getClass() == Trailer.class)?viewType_trailer : viewType_review;
    }

    @Override
    public int getViewTypeCount() {

        return  viewType_count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //TrailerHolder holder = null;
        View view = null;
        int viewType = getItemViewType(position);
        int layoutId = -1;

        if(viewType == viewType_trailer)
        {
            layoutId = R.layout.trailer;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,parent,false);
            TextView name = (TextView)view.findViewById(R.id.trailerName);
            Trailer trailer  =  (Trailer)objects.get(position);
            name.setText(trailer.getName());
        }
        else if(viewType == viewType_review)
        {
            layoutId = R.layout.review;
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,parent,false);
            TextView name = (TextView)view.findViewById(R.id.reviewer);
            TextView content = (TextView)view.findViewById(R.id.reviewe);
            Review review =  (Review)objects.get(position);
            name.setText(review.getAuthor());
            content.setText(review.getContent());
        }

       /* if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutId,parent,false);
            holder = new TrailerHolder(view);
            view.setTag(holder);
        }else{
            holder = (TrailerHolder)view.getTag();
        }

        Trailer t =  (Trailer)objects.get(position);
        holder.getName().setText(t.getName()); */
        return view;
    }
}
