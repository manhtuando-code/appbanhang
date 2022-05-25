package edu.xda.testbtl.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.activity.ProductDetailActivity;
import edu.xda.testbtl.model.san_pham;
import edu.xda.testbtl.more.CheckConnection;

public class HomeSanphamAdapter extends RecyclerView.Adapter<HomeSanphamAdapter.ItemHoder> {
    Context context;
    ArrayList<san_pham> arraysanpham;

    public HomeSanphamAdapter(Context context, ArrayList<san_pham> arraysanpham) {
        this.context = context;
        this.arraysanpham = arraysanpham;
    }


    @NonNull
    @Override
    public ItemHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_newarrivals, null);
        ItemHoder itemHoder = new ItemHoder(v);
        return itemHoder;
    }

    //xác định vị trí, đổ dữ liệu
    @Override
    public void onBindViewHolder(@NonNull ItemHoder holder, int position) {
        san_pham sanpham = arraysanpham.get(position);
        holder.txttensanpham.setText(sanpham.getTenSP());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgiasanpham.setText(decimalFormat.format(sanpham.getDon_gia()) + "đ");
        Picasso.get().load(sanpham.getHinh_anh()).placeholder(R.drawable.noimage).error(R.drawable.error).into(holder.imghinhsanpham);
    }


    @Override
    public int getItemCount() {
        return arraysanpham.size();
    }

    public class ItemHoder extends RecyclerView.ViewHolder{
        public ImageView imghinhsanpham;
        public TextView txttensanpham, txtgiasanpham;

        public ItemHoder(@NonNull View itemView) {
            super(itemView);
            imghinhsanpham = itemView.findViewById(R.id.imgviewsanpham);
            txtgiasanpham = itemView.findViewById(R.id.textviewgiasanpham);
            txttensanpham= itemView.findViewById(R.id.textviewtensanpham);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("thongtinsanpham",arraysanpham.get(getPosition()));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context,arraysanpham.get(getPosition()).getTenSP());
                    context.startActivity(intent);
                }
            });
        }
    }
}
