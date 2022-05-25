package edu.xda.testbtl.model;

import java.io.Serializable;

public class san_pham implements Serializable {
    public int SP_id;
    public String SP_code;
    public String tenSP;
    public String hinh_anh;
    public int so_luong_ton;
    public int don_gia;
    public String mo_ta;
    public String loaiSP_id;

    public san_pham(int SP_id, String SP_code, String tenSP, String hinh_anh, int so_luong_ton, int don_gia, String mo_ta, String loaiSP_id) {
        this.SP_id = SP_id;
        this.SP_code = SP_code;
        this.tenSP = tenSP;
        this.hinh_anh = hinh_anh;
        this.so_luong_ton = so_luong_ton;
        this.don_gia = don_gia;
        this.mo_ta = mo_ta;
        this.loaiSP_id = loaiSP_id;
    }

    public int getSP_id() {
        return SP_id;
    }

    public void setSP_id(int SP_id) {
        this.SP_id = SP_id;
    }

    public String getSP_code() {
        return SP_code;
    }

    public void setSP_code(String SP_code) {
        this.SP_code = SP_code;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinh_anh() {
        return hinh_anh;
    }

    public void setHinh_anh(String hinh_anh) {
        this.hinh_anh = hinh_anh;
    }

    public int getSo_luong_ton() {
        return so_luong_ton;
    }

    public void setSo_luong_ton(int so_luong_ton) {
        this.so_luong_ton = so_luong_ton;
    }

    public int getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }

    public String getMo_ta() {
        return mo_ta;
    }

    public void setMo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String getLoaiSP_id() {
        return loaiSP_id;
    }

    public void setLoaiSP_id(String loaiSP_id) {
        this.loaiSP_id = loaiSP_id;
    }
}
