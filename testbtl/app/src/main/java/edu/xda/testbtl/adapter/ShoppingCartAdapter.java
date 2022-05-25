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
import edu.xda.testbtl.activity.ShoppingCartActivity;
import edu.xda.testbtl.model.gio_hang;

public class ShoppingCartAdapter extends BaseAdapter {
    Context context;
    ArrayList<gio_hang> arrayListgio_hang ;

    public ShoppingCartAdapter(Context context, ArrayList<gio_hang> arrayListgio_hang) {
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
        public TextView txttengiohang, txtsizegiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus, btnvalues, btnplus;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_shopping_cart, null);
            viewHolder.txttengiohang = (TextView) view.findViewById(R.id.txttengiohang);
            viewHolder.txtsizegiohang = (TextView) view.findViewById(R.id.txtsizegiohang);
            viewHolder.txtgiagiohang = (TextView) view.findViewById(R.id.txtgiagiohang);
            viewHolder.imggiohang = (ImageView) view.findViewById(R.id.imageviewgiohang);
            viewHolder.btnminus = (Button) view.findViewById(R.id.btnminus);
            viewHolder.btnvalues = (Button) view.findViewById(R.id.btnvalues);
            viewHolder.btnplus = (Button) view.findViewById(R.id.btnplus);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        gio_hang giohang = (gio_hang) getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTenSP());
        viewHolder.txtsizegiohang.setText(giohang.getSize(giohang.getSP_code()));
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getDon_gia()) + "đ");
        Picasso.get().load(giohang.getHinh_anh()).placeholder(R.drawable.noimage).error(R.drawable.error).into(viewHolder.imggiohang);
        viewHolder.btnvalues.setText(giohang.getSo_luong()+"");

        int sl = Integer.parseInt(viewHolder.btnvalues.getText().toString());
        if (sl >= 30) {
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        } else if (sl <=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        } else if (sl >= 1) {
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi;
                slmoi = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) + 1;
                int slhientai = MainActivity.arrayListGio_hang.get(i).getSo_luong();
                //long giahientai = MainActivity.arrayListGio_hang.get(i).getDon_gia();
                MainActivity.arrayListGio_hang.get(i).setSo_luong(slmoi);
                //long giamoinhat = (giahientai * slmoi) / slhientai;
                //MainActivity.arrayListGio_hang.get(i).setDon_gia(giamoinhat);
                //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                //finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "đ");
                ShoppingCartActivity.GetDataListView();
                if (slmoi > 29) {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                }

            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoi;
                slmoi = Integer.parseInt(finalViewHolder.btnvalues.getText().toString()) - 1;
                int slhientai = MainActivity.arrayListGio_hang.get(i).getSo_luong();
                //long giahientai = MainActivity.arrayListGio_hang.get(i).getDon_gia();
                MainActivity.arrayListGio_hang.get(i).setSo_luong(slmoi);
                //long giamoinhat = (giahientai * slmoi) / slhientai;
                //MainActivity.arrayListGio_hang.get(i).setDon_gia(giamoinhat);
                //DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                //finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giamoinhat) + "đ");
                ShoppingCartActivity.GetDataListView();
                if (slmoi < 2) {
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                } else {
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalues.setText(String.valueOf(slmoi));
                }

            }
        });
        return view;
    }

}
