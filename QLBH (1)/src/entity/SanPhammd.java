/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import entity.ChatLieu;
import entity.DongSanPham;
import entity.MauSac;
import entity.NhaSanXuat;

/**
 *
 * @author thong
 */
public class SanPhammd {

    private String maSP;
    private String tennsx;
    private String tenmauSac;
    private String tendongSP;
    private String tenchatLieu;
    private String TenSP;
    private String moTa;
    private Integer soLuongTon;
    private Double giaNhap;
    private Double giaBan;

    private String anhSp;
    private String size;
    private int trangthai;
    private Double chietkhau;

    public SanPhammd() {
    }

    public SanPhammd(String maSP, String tennsx, String tenmauSac, String tendongSP, String tenchatLieu, String TenSP, String moTa, Integer soLuongTon, Double giaNhap, Double giaBan, Double chietkhau, String anhSp, String size, int trangthai) {
        this.maSP = maSP;
        this.tennsx = tennsx;
        this.tenmauSac = tenmauSac;
        this.tendongSP = tendongSP;
        this.tenchatLieu = tenchatLieu;
        this.TenSP = TenSP;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.chietkhau = chietkhau;
        this.anhSp = anhSp;
        this.size = size;
        this.trangthai = trangthai;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTennsx() {
        return tennsx;
    }

    public void setTennsx(String tennsx) {
        this.tennsx = tennsx;
    }

    public String getTenmauSac() {
        return tenmauSac;
    }

    public void setTenmauSac(String tenmauSac) {
        this.tenmauSac = tenmauSac;
    }

    public String getTendongSP() {
        return tendongSP;
    }

    public void setTendongSP(String tendongSP) {
        this.tendongSP = tendongSP;
    }

    public String getTenchatLieu() {
        return tenchatLieu;
    }

    public void setTenchatLieu(String tenchatLieu) {
        this.tenchatLieu = tenchatLieu;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Double getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(Double chietkhau) {
        this.chietkhau = chietkhau;
    }

    public String getAnhSp() {
        return anhSp;
    }

    public void setAnhSp(String anhSp) {
        this.anhSp = anhSp;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public String gettrangthaisp(Integer Trangthai) {
        if (trangthai == 1) {
            return "còn hàng";
        }
        else if(trangthai==2){
            return "ngừng kinh doanh";
        }
        return "hết hàng";
    }

}
