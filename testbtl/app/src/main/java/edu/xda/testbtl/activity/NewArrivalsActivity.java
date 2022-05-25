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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.adapter.NewArrivalsAdapter;
import edu.xda.testbtl.model.san_pham;
import edu.xda.testbtl.more.CheckConnection;
import edu.xda.testbtl.more.Server;

public class NewArrivalsActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ListView mlistViewSP;
    NewArrivalsAdapter newArrivalsAdapter;
    ArrayList<san_pham> mangSP;
    int page = 1;
    View footerview;
    boolean isLoading = false;
    boolean limitData = false;
    mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_arrivals);
        Anhxa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
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
        mlistViewSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                intent.putExtra("thongtinsanpham", mangSP.get(i));
                startActivity(intent);
            }
        });
        mlistViewSP.setOnScrollListener(new AbsListView.OnScrollListener() {
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
        String link = Server.LinkNewArrivalsActivity+String.valueOf(Page);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(link, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.toString().length() != 2){             // khác 2 có nghĩa là khác hai cái dấu ngoặc này '[]'
                    mlistViewSP.removeFooterView(footerview);
                    int SP_id = 0;
                    String SP_code = "";
                    String tenSP = "";
                    String hinh_anh = "";
                    int so_luong_ton = 0;
                    int don_gia = 0;
                    String mo_ta = "";
                    String loaiSP_id ="";
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            SP_id = jsonObject.getInt("SP_id");
                            SP_code = jsonObject.getString("SP_code");
                            tenSP = jsonObject.getString("tenSP");
                            hinh_anh = jsonObject.getString("hinh_anh");
                            so_luong_ton = jsonObject.getInt("so_luong_ton");
                            don_gia = jsonObject.getInt("don_gia");
                            mo_ta = jsonObject.getString("mo_ta");
                            loaiSP_id = jsonObject.getString("loaiSP_id");

                            mangSP.add(new san_pham(SP_id,SP_code,tenSP,hinh_anh,so_luong_ton,don_gia,mo_ta,loaiSP_id));
                            newArrivalsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }else {
                    limitData = true;
                    mlistViewSP.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void ActionToolBar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        mToolbar = (Toolbar) findViewById(R.id.NewArrivalsToolbar);
        mlistViewSP = (ListView) findViewById(R.id.listviewNewArrivals);
        mangSP = new ArrayList<>();
        newArrivalsAdapter = new NewArrivalsAdapter(getApplicationContext(), mangSP);
        mlistViewSP.setAdapter(newArrivalsAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview = inflater.inflate(R.layout.progressbar, null);
        mHandler = new mHandler();
    }

    public class mHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    mlistViewSP.addFooterView(footerview);
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