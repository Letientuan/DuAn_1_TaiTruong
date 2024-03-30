/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class HoaDonChiTiet {

   private long maHDCT;
    private String masanPham;
    private int mahd;
    private int soLuong;
    private double donGia;
    private double chietkhau;
    private String tenSP;
  

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(long maHDCT, String masanPham, int mahd, int soLuong, double donGia, double chietkhau, String tenSP) {
        this.maHDCT = maHDCT;
        this.masanPham = masanPham;
        this.mahd = mahd;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.chietkhau = chietkhau;
        this.tenSP = tenSP;
    }

    public long getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(long maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMasanPham() {
        return masanPham;
    }

    public void setMasanPham(String masanPham) {
        this.masanPham = masanPham;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(double chietkhau) {
        this.chietkhau = chietkhau;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

   
}
