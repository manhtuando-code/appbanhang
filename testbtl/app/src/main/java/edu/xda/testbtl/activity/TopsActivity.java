package edu.xda.testbtl.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.xda.testbtl.R;
import edu.xda.testbtl.adapter.TopsAdapter;
import edu.xda.testbtl.model.san_pham;
import edu.xda.testbtl.more.CheckConnection;
import edu.xda.testbtl.more.Server;

public class TopsActivity extends AppCompatActivity {
    Toolbar mToolbarTops;
    ListView mlistViewTops;
    TopsAdapter topsAdapter;
    ArrayList<san_pham> mangSPTops;
    String idtops1 = "rong";
    String idtops2 = "rong";
    int page = 1;
    View footerview;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tops);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            GetIdLoaiSP();
            ActionToolBar();
            GetData(page);
            LoadMoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_shoppingcart:
                Intent intent = new Intent(getApplicationContext(),ShoppingCartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void LoadMoreData() {
        mlistViewTops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("thongtinsanpham", mangSPTops.get(i));
                startActivity(intent);
            }
        });
        mlistViewTops.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FirstItem, int VisibleItem, int TotalItem) {
                if(FirstItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitData == false){
                    isLoading = true;
                    ThreadData threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetData(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String link = Server.LinkTopsActivity+String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int SP_id = 0;
                String SP_code = "";
                String tenSP = "";
                String hinh_anh = "";
                int so_luong_ton = 0;
                int don_gia = 0;
                String mo_ta = "";
                String loaiSP_id ="";
                if (response != null && response.length() != 2){
                    mlistViewTops.removeFooterView(footerview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                SP_id = jsonObject.getInt("SP_id");
                                SP_code = jsonObject.getString("SP_code");
                                tenSP = jsonObject.getString("tenSP");
                                hinh_anh = jsonObject.getString("hinh_anh");
                                so_luong_ton = jsonObject.getInt("so_luong_ton");
                                don_gia = jsonObject.getInt("don_gia");
                                mo_ta = jsonObject.getString("mo_ta");
                                loaiSP_id = jsonObject.getString("loaiSP_id");

                                mangSPTops.add(new san_pham(SP_id,SP_code,tenSP,hinh_anh,so_luong_ton,don_gia,mo_ta,loaiSP_id));
                                topsAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else {
                    limitData = true;
                    mlistViewTops.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("loaiSP_id1",idtops1.toString());
                param.put("loaiSP_id2",idtops2.toString());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(mToolbarTops);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarTops.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    
    private void Anhxa() {
        mToolbarTops = (Toolbar) findViewById(R.id.TopsToolbar);
        mlistViewTops = (ListView) findViewById(R.id.listviewTops);
        mangSPTops = new ArrayList<>();
        topsAdapter = new TopsAdapter(getApplicationContext(),mangSPTops);
        mlistViewTops.setAdapter(topsAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();
    }

    private void GetIdLoaiSP(){
        idtops1 = getIntent().getStringExtra("idtop1");
        idtops2 = getIntent().getStringExtra("idtop2");
    }
    public class mHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    mlistViewTops.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    // luồng hoạt động
    public class ThreadData extends Thread{
        @Override
        public void run() {
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
}