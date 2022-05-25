package edu.xda.testbtl.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;

import edu.xda.testbtl.R;
import edu.xda.testbtl.activity.MainActivity;
import edu.xda.testbtl.activity.OrderActivity;
import edu.xda.testbtl.adapter.Frag_ShoppingCartAdapter;
import edu.xda.testbtl.more.CheckConnection;


public class ShoppingcartFragment extends Fragment {
    static TextView mFragTextViewtamtinh;
    View mView;
    MainActivity mMainActivity;
    ListView mFraglistViewgiohang;
    TextView mFragTextViewthongbao;
    Button mFragButtondathang;
    Toolbar mFragToolbargiohang;
    Frag_ShoppingCartAdapter mFragShoppingCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_shoppingcart, container, false);
        mMainActivity = (MainActivity) getActivity();
        Anhxa();
        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
            CheckDaTa();
            GetDataListView();
            CatchOnItemListView();
            EventButtonOrder();
        } else {
            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra lại kết nối");
        }

        return mView;
    }
    public static void GetDataListView() {
        long tamtinh = 0;
        for (int i = 0; i < MainActivity.arrayListGio_hang.size() ; i++) {
            tamtinh += MainActivity.arrayListGio_hang.get(i).getDon_gia();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        mFragTextViewtamtinh.setText(decimalFormat.format(tamtinh) + "đ");
    }

    private void EventButtonOrder() {
        mFragButtondathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.arrayListGio_hang.size() > 0 ) {
                    Intent intent = new Intent(mMainActivity.getApplicationContext(), OrderActivity.class);
                    startActivity(intent);
                } else {
                    CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Giỏ hàng của bạn còn trống!");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        mFraglistViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.arrayListGio_hang.size() <= 0 ) {
                            mFragTextViewthongbao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.arrayListGio_hang.remove(position);
                            mFragShoppingCartAdapter.notifyDataSetChanged();
                            GetDataListView();
                            if (MainActivity.arrayListGio_hang.size() <= 0) {
                                mFragTextViewthongbao.setVisibility(View.VISIBLE);
                            } else {
                                mFragTextViewthongbao.setVisibility(View.INVISIBLE);
                                mFragShoppingCartAdapter.notifyDataSetChanged();
                                GetDataListView();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mFragShoppingCartAdapter.notifyDataSetChanged();
                        GetDataListView();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void CheckDaTa() {
        if (MainActivity.arrayListGio_hang.size() <= 0 ) {
            mFragShoppingCartAdapter.notifyDataSetChanged();
            mFragTextViewthongbao.setVisibility(View.VISIBLE);
            mFraglistViewgiohang.setVisibility(View.INVISIBLE);
        } else {
            mFragShoppingCartAdapter.notifyDataSetChanged();
            mFragTextViewthongbao.setVisibility(View.INVISIBLE);
            mFraglistViewgiohang.setVisibility(View.VISIBLE);
        }
    }


    private void Anhxa() {
        mFraglistViewgiohang = (ListView) mView.findViewById(R.id.frag_listviewshoppingcart);
        mFragTextViewthongbao = (TextView) mView.findViewById(R.id.frag_textviewthongbao);
        mFragTextViewtamtinh = (TextView) mView.findViewById(R.id.frag_textviewgiatamtinh);
        mFragButtondathang = (Button) mView.findViewById(R.id.frag_btnorder);
        mFragToolbargiohang = (Toolbar) mView.findViewById(R.id.frag_toolbarshoppingcart);
        mFragShoppingCartAdapter = new Frag_ShoppingCartAdapter(mMainActivity.getApplicationContext(),MainActivity.arrayListGio_hang);
        mFraglistViewgiohang.setAdapter(mFragShoppingCartAdapter);
    }
}