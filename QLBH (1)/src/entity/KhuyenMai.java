/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
public class KhuyenMai {
    private int makm;
    private String tenkm;
    private double giatrigiam;
    private String mota;
    private String masp;
    private boolean hinhthucgiam;

    public KhuyenMai() {
    }

    public KhuyenMai(int makm, String tenkm, double giatrigiam, String mota, String masp, boolean hinhthucgiam) {
        this.makm = makm;
        this.tenkm = tenkm;
        this.giatrigiam = giatrigiam;
        this.mota = mota;
        this.masp = masp;
        this.hinhthucgiam = hinhthucgiam;
    }

    public int getMakm() {
        return makm;
    }

    public void setMakm(int makm) {
        this.makm = makm;
    }

    public String getTenkm() {
        return tenkm;
    }

    public void setTenkm(String tenkm) {
        this.tenkm = tenkm;
    }

    public double getGiatrigiam() {
        return giatrigiam;
    }

    public void setGiatrigiam(double giatrigiam) {
        this.giatrigiam = giatrigiam;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public boolean isHinhthucgiam() {
        return hinhthucgiam;
    }

    public void setHinhthucgiam(boolean hinhthucgiam) {
        this.hinhthucgiam = hinhthucgiam;
    }

   public String gethinhthuc(boolean Trangthai) {
        if (Trangthai == true) {
            return "Giảm Theo Tiền";
        }
        return "Phần Trăm";
    }

}
