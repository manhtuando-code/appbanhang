package edu.xda.testbtl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.model.san_pham;

public class TopsAdapter extends BaseAdapter {
    Context context;
    ArrayList<san_pham> arrayTops;

    public TopsAdapter(Context context, ArrayList<san_pham> arrayListsan_pham) {
        this.context = context;
        this.arrayTops = arrayListsan_pham;
    }

    @Override
    public int getCount() {
        return arrayTops.size();
    }

    //get đối tượng
    @Override
    public Object getItem(int i) {
        return arrayTops.get(i);
    }

    //get vị trí
    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder{
        public TextView texttensptops, textgiasptops;
        public ImageView imagesptops;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TopsAdapter.ViewHoder viewHoder = null;
        if (view == null) {
            viewHoder = new TopsAdapter.ViewHoder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_tops, null);
            viewHoder.texttensptops = (TextView) view.findViewById(R.id.textviewtentops);
            viewHoder.textgiasptops = (TextView) view.findViewById(R.id.textviewgiatops);
            viewHoder.imagesptops = (ImageView) view.findViewById(R.id.imageviewtops);
            view.setTag(viewHoder);
        }else {
            viewHoder = (TopsAdapter.ViewHoder) view.getTag();
        }
        san_pham sanPham = (san_pham) getItem(i);
        viewHoder.texttensptops.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoder.textgiasptops.setText(decimalFormat.format(sanPham.getDon_gia()) + "đ");
        Picasso.get().load(sanPham.getHinh_anh()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHoder.imagesptops);
        return view;
    }
}
