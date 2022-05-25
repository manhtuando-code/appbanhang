package edu.xda.testbtl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.model.thanhphanNavigationview;

public class NavigationViewAdapter extends BaseAdapter {
    ArrayList<thanhphanNavigationview> arrayListTPNA;
    Context context;

    public NavigationViewAdapter(ArrayList<thanhphanNavigationview> arrayListTPNA, Context context) {
        this.arrayListTPNA = arrayListTPNA;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayListTPNA.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListTPNA.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        TextView txttenloaisp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_listview_loaisp, null);
            viewHolder.txttenloaisp = (TextView) view.findViewById(R.id.textviewloaisp);
            view.setTag(viewHolder);
        }else {
            viewHolder =(ViewHolder) view.getTag();
        }
        thanhphanNavigationview tpna =(thanhphanNavigationview) getItem(i);
        viewHolder.txttenloaisp.setText(tpna.getName());
        return view;
    }
}
