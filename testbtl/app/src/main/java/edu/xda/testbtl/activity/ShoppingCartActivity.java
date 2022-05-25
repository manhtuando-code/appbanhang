package edu.xda.testbtl.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

import edu.xda.testbtl.R;
import edu.xda.testbtl.adapter.ShoppingCartAdapter;
import edu.xda.testbtl.more.CheckConnection;

public class ShoppingCartActivity extends AppCompatActivity {
    ListView mlistViewgiohang;
    TextView mTextViewthongbao;
    static TextView mTextViewtamtinh;
    Button mButtondathang;
    Toolbar mToolbargiohang;
    ShoppingCartAdapter mShoppingCartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        Anhxa();
        ActionToolbar();
        CheckDaTa();
        GetDataListView();
        CatchOnItemListView();
        EventButtonOrder();
    }

    private void EventButtonOrder() {
        mButtondathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.arrayListGio_hang.size() > 0 ) {
                    Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                    startActivity(intent);
                } else {
                    CheckConnection.ShowToast_Short(getApplicationContext(), "Giỏ hàng của bạn còn trống!");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        mlistViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShoppingCartActivity.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có chắc muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (MainActivity.arrayListGio_hang.size() <= 0 ) {
                            mTextViewthongbao.setVisibility(View.VISIBLE);
                        } else {
                            MainActivity.arrayListGio_hang.remove(position);
                            mShoppingCartAdapter.notifyDataSetChanged();
                            GetDataListView();
                            if (MainActivity.arrayListGio_hang.size() <= 0) {
                                mTextViewthongbao.setVisibility(View.VISIBLE);
                            } else {
                                mTextViewthongbao.setVisibility(View.INVISIBLE);
                                mShoppingCartAdapter.notifyDataSetChanged();
                                GetDataListView();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mShoppingCartAdapter.notifyDataSetChanged();
                        GetDataListView();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void GetDataListView() {
        long tamtinh = 0;
        for (int i = 0; i < MainActivity.arrayListGio_hang.size() ; i++) {
            tamtinh += MainActivity.arrayListGio_hang.get(i).getDon_gia() * MainActivity.arrayListGio_hang.get(i).getSo_luong();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        mTextViewtamtinh.setText(decimalFormat.format(tamtinh) + "đ");
    }

    private void CheckDaTa() {
        if (MainActivity.arrayListGio_hang.size() <= 0 ) {
            mShoppingCartAdapter.notifyDataSetChanged();
            mTextViewthongbao.setVisibility(View.VISIBLE);
            mlistViewgiohang.setVisibility(View.INVISIBLE);
        } else {
            mShoppingCartAdapter.notifyDataSetChanged();
            mTextViewthongbao.setVisibility(View.INVISIBLE);
            mlistViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        setSupportActionBar(mToolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        mlistViewgiohang = (ListView) findViewById(R.id.listviewshoppingcart);
        mTextViewthongbao = (TextView) findViewById(R.id.textviewthongbao);
        mTextViewtamtinh = (TextView) findViewById(R.id.textviewgiatamtinh);
        mButtondathang = (Button) findViewById(R.id.btnorder);
        mToolbargiohang = (Toolbar) findViewById(R.id.toolbarshoppingcart);
        mShoppingCartAdapter = new ShoppingCartAdapter(ShoppingCartActivity.this,MainActivity.arrayListGio_hang);
        mlistViewgiohang.setAdapter(mShoppingCartAdapter);
    }
}