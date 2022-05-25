package edu.xda.testbtl.model;

public class gio_hang {
    public String SP_code;
    public String tenSP;
    public String hinh_anh;
    public long don_gia;
    public int so_luong;

    public gio_hang(String SP_code, String tenSP, String hinh_anh, long don_gia, int so_luong) {
        this.SP_code = SP_code;
        this.tenSP = tenSP;
        this.hinh_anh = hinh_anh;
        this.don_gia = don_gia;
        this.so_luong = so_luong;
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

    public long getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(long don_gia) {
        this.don_gia = don_gia;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public String getSize(String SP_code){
        String s = "";
        String[] parts =  SP_code.split("-");
        return s=parts[3];
    }
}
