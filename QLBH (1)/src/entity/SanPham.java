/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class SanPham {

    private String maSP;
    private int nsx;
    private int mamauSac;
    private int dongSP;
    private int chatLieu;
    private String TenSP;
    private String moTa;
    private Integer soLuongTon;
    private Double giaNhap;
    private Double giaBan;
    private String anhSp;
    private String size;
    private int trangthai;

    public SanPham() {
    }

    public SanPham(String maSP) {
        this.maSP = maSP;
    }

    public SanPham(String maSP, int nsx, int mamauSac, int dongSP, int chatLieu, String TenSP, String moTa, Integer soLuongTon, Double giaNhap, Double giaBan, String anhSp, String size, int trangthai) {
        this.maSP = maSP;
        this.nsx = nsx;
        this.mamauSac = mamauSac;
        this.dongSP = dongSP;
        this.chatLieu = chatLieu;
        this.TenSP = TenSP;
        this.moTa = moTa;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
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

    public int getNsx() {
        return nsx;
    }

    public void setNsx(int nsx) {
        this.nsx = nsx;
    }

    public int getMamauSac() {
        return mamauSac;
    }

    public void setMamauSac(int mamauSac) {
        this.mamauSac = mamauSac;
    }

    public int getDongSP() {
        return dongSP;
    }

    public void setDongSP(int dongSP) {
        this.dongSP = dongSP;
    }

    public int getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(int chatLieu) {
        this.chatLieu = chatLieu;
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

    public String gettrangthaisp(Integer trangthai) {
        if (trangthai == 1) {
            return "còn hàng";
        }
        return "hết hàng";
    }

    public SanPham(Double giaNhap, Double giaBan) {
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
    }

}
