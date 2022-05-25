package edu.xda.testbtl.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.util.HashMap;
import java.util.Map;

import edu.xda.testbtl.R;
import edu.xda.testbtl.more.CheckConnection;
import edu.xda.testbtl.more.Server;

public class OrderActivity extends AppCompatActivity {
    Toolbar mToolbarorder;
    EditText edthoten, edtemail, edtsdt, edtdiachi;
    Button btndathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Anhxa();
        ActionToolBar();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())) {
            EventButton();
        } else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }

    }

    private void EventButton() {
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ho_tenKH = edthoten.getText().toString().trim();
                final String email = edtemail.getText().toString().trim();
                final String sdt = edtsdt.getText().toString().trim();
                final String dia_chi = edtdiachi.getText().toString().trim();
                if (ho_tenKH.length() > 0  && email.length() > 0 && sdt.length() > 0 && dia_chi.length() > 0) {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.LinkKhachHangInfo, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String DH_id) {
                            Log.d("DH_id",DH_id);
                            if (Integer.parseInt(DH_id) > 0) {
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.LinkChiTietDonHangInfo, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")) {
                                            MainActivity.arrayListGio_hang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(), "Bạn đã đặt hàng thành công.");
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng.");
                                        } else {
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng đã bị lỗi");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Nullable
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0 ; i < MainActivity.arrayListGio_hang.size() ; i++) {
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("DH_id",DH_id);
                                                jsonObject.put("SP_code",MainActivity.arrayListGio_hang.get(i).getSP_code());
//                                                jsonObject.put("don_gia",MainActivity.arrayListGio_hang.get(i).getDon_gia());
                                                jsonObject.put("so_luong",MainActivity.arrayListGio_hang.get(i).getSo_luong());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(jsonObject);
                                        }
                                        HashMap<String,String> hashMap = new HashMap<String,String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("ho_tenKH",ho_tenKH);
                            hashMap.put("email",email);
                            hashMap.put("sdt",sdt);
                            hashMap.put("dia_chi",dia_chi);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                } else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Hãy kiểm tra lại dữ liệu.");
                }
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(mToolbarorder);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbarorder.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        mToolbarorder = (Toolbar) findViewById(R.id.toolbarorder);
        edthoten = (EditText) findViewById(R.id.txtInputHoten);
        edtemail = (EditText) findViewById(R.id.txtInputEmail);
        edtsdt = (EditText) findViewById(R.id.txtInputSodienthoai);
        edtdiachi = (EditText) findViewById(R.id.txtInputDiachi);
        btndathang = (Button) findViewById(R.id.btndathang);
    }
}