package edu.xda.testbtl.adapter;

import edu.xda.testbtl.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.xda.testbtl.model.san_pham;

public class NewArrivalsAdapter extends BaseAdapter {
    Context context;
    ArrayList<san_pham> arrayListsan_pham;

    public NewArrivalsAdapter(Context context, ArrayList<san_pham> arrayListsan_pham) {
        this.context = context;
        this.arrayListsan_pham = arrayListsan_pham;
    }

    @Override
    public int getCount() {
        return arrayListsan_pham.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListsan_pham.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHoder{
        public TextView texttensp, textgiasp;
        public ImageView imagesp;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder viewHoder = null;
        if (view == null) {
             viewHoder = new ViewHoder();
             LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
             view = inflater.inflate(R.layout.dong_sanpham, null);
             viewHoder.texttensp = (TextView) view.findViewById(R.id.textviewten);
             viewHoder.textgiasp = (TextView) view.findViewById(R.id.textviewgia);
             viewHoder.imagesp = (ImageView) view.findViewById(R.id.imageviewsan_pham);
             view.setTag(viewHoder);
        }else {
            viewHoder = (ViewHoder) view.getTag();
        }
        san_pham sanPham = (san_pham) getItem(i);
        viewHoder.texttensp.setText(sanPham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHoder.textgiasp.setText(decimalFormat.format(sanPham.getDon_gia()) + "đ");
        Picasso.get().load(sanPham.getHinh_anh()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHoder.imagesp);
        return view;
    }
}
