package edu.xda.testbtl.activity;import androidx.annotation.NonNull;import androidx.appcompat.app.AppCompatActivity;import androidx.appcompat.widget.Toolbar;import android.content.Intent;import android.os.Bundle;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.ImageView;import android.widget.TextView;import android.widget.Toast;import com.google.android.material.chip.Chip;import com.google.android.material.chip.ChipGroup;import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;import com.squareup.picasso.Picasso;import java.text.DecimalFormat;import java.util.ArrayList;import edu.xda.testbtl.R;import edu.xda.testbtl.model.gio_hang;import edu.xda.testbtl.model.san_pham;public class ProductDetailActivity extends AppCompatActivity {    Toolbar mToolbarCTSP;    ImageView mImageViewCTSP;    TextView mTextViewTen, mTextViewGia, mTextViewMota;    ChipGroup mChipGroupSize;    ExtendedFloatingActionButton mBtnAddToCard;    int SP_id = 0;    String SP_code = "";    String tenSP = "";    String hinh_anh = "";    int so_luong_ton = 0;    int don_gia = 0;    String mo_ta = "";    String loaiSP_id ="";    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_product_detail);        Anhxa();        ActionToolbar();        GetDetail();        ChechSize();        EventButtonAddToCard();    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        getMenuInflater().inflate(R.menu.search_menu,menu);        return true;    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        switch (item.getItemId()){            case R.id.menu_shoppingcart:                Intent intent = new Intent(getApplicationContext(),ShoppingCartActivity.class);                startActivity(intent);        }        return super.onOptionsItemSelected(item);    }    private void ChechSize() {        //mChipGroupSize.clearCheck();        mChipGroupSize.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {            @Override            public void onCheckedChanged(ChipGroup group, int checkedId) {                Chip chip = (Chip) group.findViewById(checkedId);                if (checkedId == -1){                    Toast.makeText(getApplicationContext(),"Bạn phải chọn size", Toast.LENGTH_SHORT).show();                }            }        });    }    private void EventButtonAddToCard() {        mBtnAddToCard.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                //ArrayList<san_pham> san_phamselected = new ArrayList<>() ;                int selectedId = mChipGroupSize.getCheckedChipId();                if (selectedId == -1) {                    Toast.makeText(getApplicationContext(),"No answer has been selected", Toast.LENGTH_SHORT).show();                } else {                    Chip chip = (Chip) mChipGroupSize.findViewById(selectedId);                    SP_code = "";                    san_pham sanPham = (san_pham) getIntent().getSerializableExtra("thongtinsanpham");                    SP_code = sanPham.getSP_code();                    String[] parts =  SP_code.split("-");                    SP_code = parts[0]+"-"+parts[1]+"-"+parts[2]+"-"+chip.getText();                    Toast.makeText(getApplicationContext(),SP_code, Toast.LENGTH_SHORT).show();                }                if (MainActivity.arrayListGio_hang.size() > 0){                    int sl=1;                    boolean exists = false;                    for (int i = 0  ; i<MainActivity.arrayListGio_hang.size() ; i++){                        if (MainActivity.arrayListGio_hang.get(i).getSP_code().equals(SP_code)){                            MainActivity.arrayListGio_hang.get(i).setSo_luong(MainActivity.arrayListGio_hang.get(i).getSo_luong() + sl);                            MainActivity.arrayListGio_hang.get(i).setDon_gia(don_gia * MainActivity.arrayListGio_hang.get(i).getSo_luong());                            exists = true;                        }                    }                    if (exists == false){                        int soluong = 1;                        long giamoi = soluong * don_gia;                        MainActivity.arrayListGio_hang.add(new gio_hang(SP_code,tenSP,hinh_anh,giamoi,soluong));                    }                }                else {                    int soluong = 1 ;                    long giamoi = soluong * don_gia;                    MainActivity.arrayListGio_hang.add(new gio_hang(SP_code,tenSP,hinh_anh,giamoi,soluong));                }                Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);                startActivity(intent);            }        });    }    private void GetDetail() {        san_pham sanPham = (san_pham) getIntent().getSerializableExtra("thongtinsanpham");        SP_id = sanPham.getSP_id();        SP_code = sanPham.getSP_code();        tenSP = sanPham.getTenSP();        hinh_anh = sanPham.getHinh_anh();        so_luong_ton = sanPham.getSo_luong_ton();        don_gia = sanPham.getDon_gia();        mo_ta = sanPham.getMo_ta();        loaiSP_id = sanPham.getLoaiSP_id();        mTextViewTen.setText(tenSP);        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");        mTextViewGia.setText(decimalFormat.format(don_gia) + "đ");        Picasso.get().load(hinh_anh).placeholder(R.drawable.noimage).error(R.drawable.error).into(mImageViewCTSP);        mTextViewMota.setText(mo_ta);    }    private void ActionToolbar() {        setSupportActionBar(mToolbarCTSP);        getSupportActionBar().setDisplayHomeAsUpEnabled(true);        mToolbarCTSP.setNavigationOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                finish();            }        });    }    private void Anhxa() {        mToolbarCTSP = (Toolbar) findViewById(R.id.toolbarchitietsanpham);        mImageViewCTSP = (ImageView) findViewById(R.id.imageviewchitietsanpham);        mTextViewTen = (TextView) findViewById(R.id.txttensanpham);        mTextViewGia = (TextView) findViewById(R.id.txtgiasanpham);        mTextViewMota = (TextView) findViewById(R.id.txtmotasanpham);        mBtnAddToCard = (ExtendedFloatingActionButton) findViewById(R.id.floatingactiontbtnthemgiohang);        mChipGroupSize = (ChipGroup) findViewById(R.id.chipgroupsize);    }}