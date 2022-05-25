package edu.xda.testbtl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.activity.MainActivity;
import edu.xda.testbtl.fragment.ShoppingcartFragment;
import edu.xda.testbtl.model.gio_hang;

public class Frag_ShoppingCartAdapter extends BaseAdapter {
    Context context;
    ArrayList<gio_hang> arrayListgio_hang ;

    public Frag_ShoppingCartAdapter(Context context, ArrayList<gio_hang> arrayListgio_hang) {
        this.context = context;
        this.arrayListgio_hang = arrayListgio_hang;
    }

    @Override
    public int getCount() {
        return arrayListgio_hang.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayListgio_hang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder {
        public TextView frag_txttengiohang, frag_txtsizegiohang, frag_txtgiagiohang;
        public ImageView frag_imggiohang;
        public Button frag_btnminus, frag_btnvalues,frag_btnplus;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Frag_ShoppingCartAdapter.ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new Frag_ShoppingCartAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_frag_shopping_cart, null);
            viewHolder.frag_txttengiohang = (TextView) view.findViewById(R.id.frag_txttengiohang);
            viewHolder.frag_txtsizegiohang = (TextView) view.findViewById(R.id.frag_txtsizegiohang);
            viewHolder.frag_txtgiagiohang = (TextView) view.findViewById(R.id.frag_txtgiagiohang);
            viewHolder.frag_imggiohang = (ImageView) view.findViewById(R.id.frag_imageviewgiohang);
            viewHolder.frag_btnminus = (Button) view.findViewById(R.id.frag_btnminus);
            viewHolder.frag_btnvalues = (Button) view.findViewById(R.id.frag_btnvalues);
            viewHolder.frag_btnplus = (Button) view.findViewById(R.id.frag_btnplus);
            view.setTag(viewHolder);
        } else {
            viewHolder = (Frag_ShoppingCartAdapter.ViewHolder) view.getTag();
        }
        gio_hang giohang = (gio_hang) getItem(i);
        viewHolder.frag_txttengiohang.setText(giohang.getTenSP());
        viewHolder.frag_txtsizegiohang.setText(giohang.getSize(giohang.getSP_code()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.frag_txtgiagiohang.setText(decimalFormat.format(giohang.getDon_gia()) + "đ");
        Picasso.get().load(giohang.getHinh_anh()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.frag_imggiohang);
        viewHolder.frag_btnvalues.setText(giohang.getSo_luong()+"");

        int sl = Integer.parseInt(viewHolder.frag_btnvalues.getText().toString());
        if (sl >= 30) {
            viewHolder.frag_btnplus.setVisibility(View.INVISIBLE);
            viewHolder.frag_btnminus.setVisibility(View.VISIBLE);
        } else if (sl <=1){
            viewHolder.frag_btnminus.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            viewHolder.frag_btnplus.setVisibility(View.VISIBLE);
            viewHolder.frag_btnminus.setVisibility(View.VISIBLE);
        }
        Frag_ShoppingCartAdapter.ViewHolder finalViewHolder = viewHolder;
        viewHolder.frag_btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi;
                slmoi = Integer.parseInt(finalViewHolder.frag_btnvalues.getText().toString()) + 1;
                int slhientai = MainActivity.arrayListGio_hang.get(i).getSo_luong();
                long giahientai = MainActivity.arrayListGio_hang.get(i).getDon_gia();
                MainActivity.arrayListGio_hang.get(i).setSo_luong(slmoi);
                long giamoinhat = (giahientai * slmoi) / slhientai;
                MainActivity.arrayListGio_hang.get(i).setDon_gia(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.frag_txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "đ");
                ShoppingcartFragment.GetDataListView();
                if (slmoi > 29) {
                    finalViewHolder.frag_btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.frag_btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.frag_btnvalues.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.frag_btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.frag_btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.frag_btnvalues.setText(String.valueOf(slmoi));
                }

            }
        });
        viewHolder.frag_btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi;
                slmoi = Integer.parseInt(finalViewHolder.frag_btnvalues.getText().toString()) - 1;
                int slhientai = MainActivity.arrayListGio_hang.get(i).getSo_luong();
                long giahientai = MainActivity.arrayListGio_hang.get(i).getDon_gia();
                MainActivity.arrayListGio_hang.get(i).setSo_luong(slmoi);
                long giamoinhat = (giahientai * slmoi) / slhientai;
                MainActivity.arrayListGio_hang.get(i).setDon_gia(giamoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.frag_txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "đ");
                ShoppingcartFragment.GetDataListView();
                if (slmoi < 2) {
                    finalViewHolder.frag_btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.frag_btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.frag_btnvalues.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.frag_btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.frag_btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.frag_btnvalues.setText(String.valueOf(slmoi));
                }

            }
        });
        return view;
    }
}
