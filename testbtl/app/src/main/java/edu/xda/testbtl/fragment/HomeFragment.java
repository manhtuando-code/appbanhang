package edu.xda.testbtl.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.xda.testbtl.R;
import edu.xda.testbtl.activity.AboutAppActivity;
import edu.xda.testbtl.activity.BottomsActivity;
import edu.xda.testbtl.activity.MainActivity;
import edu.xda.testbtl.activity.NewArrivalsActivity;
import edu.xda.testbtl.activity.OuterwearActivity;
import edu.xda.testbtl.activity.SizeChartActivity;
import edu.xda.testbtl.activity.TopsActivity;
import edu.xda.testbtl.adapter.HomeSanphamAdapter;
import edu.xda.testbtl.adapter.NavigationViewAdapter;
import edu.xda.testbtl.model.san_pham;
import edu.xda.testbtl.model.thanhphanNavigationview;
import edu.xda.testbtl.more.CheckConnection;
import edu.xda.testbtl.more.Server;

public class HomeFragment extends Fragment {
    private View mView;
    private Toolbar mToolbar;
    private RecyclerView mRecyclerviewNewArrivals;
    private MainActivity mMainActivity;
    private NavigationView mNavigationView;
    private ListView mListViewtrangchu;
    private DrawerLayout mDrawerLayout;
    private ArrayList<thanhphanNavigationview> mangthanhphanNV;
    private NavigationViewAdapter mNavigationViewAdapter;
    private String LuaChon = "";

    private ArrayList<san_pham> mangsanpham;
    private HomeSanphamAdapter homesanphamAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        mMainActivity = (MainActivity) getActivity();
        Anhxa();
        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
            ActionBar();
            GetLuaChon();
            GetNewArrivals();
            CatchOnItemListView();
        }else {
            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
        return mView;
    }

    //điều hướng khi chọn 1 trong các thành phần của navigationview
    private void CatchOnItemListView() {
        mListViewtrangchu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
                            Intent intent = new Intent(getActivity(), NewArrivalsActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra kết nối internet");
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
                            Intent intent = new Intent(getActivity(), TopsActivity.class);
                            intent.putExtra("idtop1","TEE");
                            intent.putExtra("idtop2","HOD");
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra kết nối internet");
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
                            Intent intent = new Intent(getActivity(), OuterwearActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra kết nối internet");
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
                            Intent intent = new Intent(getActivity(), BottomsActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra kết nối internet");
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
                            Intent intent = new Intent(getActivity(), SizeChartActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra kết nối internet");
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 5:
                        if (CheckConnection.haveNetworkConnection(mMainActivity.getApplicationContext())){
                            Intent intent = new Intent(getActivity(), AboutAppActivity.class);
                            startActivity(intent);
                        }else {
                            CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(), "Bạn hãy kiểm tra kết nối internet");
                        }
                        mDrawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
    }

    //lấy dữ liệu newarrivals
    private void GetNewArrivals() {
        RequestQueue requestQueue = Volley.newRequestQueue(mMainActivity.getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.LinkNewArrivalsHome, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
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

                            mangsanpham.add(new san_pham(SP_id,SP_code,tenSP,hinh_anh,so_luong_ton,don_gia,mo_ta,loaiSP_id));
                            homesanphamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    // hàm lấy dữ liệu cho thành phần của navigationview
    private void GetLuaChon() {
        RequestQueue requestQueue = Volley.newRequestQueue(mMainActivity.getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.DuongdanNavigationview, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response != null){
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            LuaChon = jsonObject.getString("LuaChon");
                            mangthanhphanNV.add(new thanhphanNavigationview(LuaChon));
                            mNavigationViewAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    //mangloaisp.add(7,new loaiSP("SZC","SIZE CHART"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(mMainActivity.getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //set hiển thị toolbar và hành động
    private void ActionBar() {
        mMainActivity.setSupportActionBar(mToolbar);
        mMainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void Anhxa() {
        mToolbar =(Toolbar) mView.findViewById(R.id.toolbartrangchu);
        mRecyclerviewNewArrivals = (RecyclerView) mView.findViewById(R.id.recyclerviewNewArrivals);

        mNavigationView = (NavigationView) mView.findViewById(R.id.navigationview);
        mListViewtrangchu = (ListView) mView.findViewById(R.id.listviewtrangchu);
        mDrawerLayout = (DrawerLayout) mView.findViewById(R.id.drawerlayout);
        mangthanhphanNV = new ArrayList<>();
        mNavigationViewAdapter = new NavigationViewAdapter(mangthanhphanNV, mMainActivity.getApplicationContext());
        mListViewtrangchu.setAdapter(mNavigationViewAdapter);

        mangsanpham = new ArrayList<>();
        homesanphamAdapter = new HomeSanphamAdapter(mMainActivity.getApplicationContext(),mangsanpham);
        mRecyclerviewNewArrivals.setHasFixedSize(true);
        mRecyclerviewNewArrivals.setLayoutManager(new GridLayoutManager(mMainActivity.getApplicationContext(), 2));
        mRecyclerviewNewArrivals.setAdapter(homesanphamAdapter);
    }
}