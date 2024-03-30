/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.entities;

import entity.KhachHang;
import entity.NhanVien;

/**
 *
 * @author thong
 */
public class HoaDonAn {
    
    private Long maHD;
    private KhachHang makhachHang;
    private NhanVien manhanVien;
    private String ngayTao;
    private String ngayThanhToan;
    private Integer tinhTrang;
    private String ghiChu;
    private Double chietKhau;
    private Double tongTien;
    private Double thanhToan;
    private String phuongThucThanhToan;

    public HoaDonAn() {
    }

    public HoaDonAn(Long maHD) {
        this.maHD = maHD;
    }
    

    public HoaDonAn(Long maHD, KhachHang makhachHang, NhanVien manhanVien, String ngayTao, String ngayThanhToan, Integer tinhTrang, String ghiChu, Double chietKhau, Double tongTien, Double thanhToan, String phuongThucThanhToan) {
        this.maHD = maHD;
        this.makhachHang = makhachHang;
        this.manhanVien = manhanVien;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.ghiChu = ghiChu;
        this.chietKhau = chietKhau;
        this.tongTien = tongTien;
        this.thanhToan = thanhToan;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public Long getMaHD() {
        return maHD;
    }

    public void setMaHD(Long maHD) {
        this.maHD = maHD;
    }

    public KhachHang getMakhachHang() {
        return makhachHang;
    }

    public void setMakhachHang(KhachHang makhachHang) {
        this.makhachHang = makhachHang;
    }

    public NhanVien getManhanVien() {
        return manhanVien;
    }

    public void setManhanVien(NhanVien manhanVien) {
        this.manhanVien = manhanVien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(String ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(Double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(Double thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }
public String gettrangthaiHD(Integer tinhTrang){
       if(tinhTrang == 1){
           return "đã Thanh Toán";
       }
       else if(tinhTrang==2){
           return "Đã Hủy";
       }
       return "Chờ Thanh Toán";
   }
    
}
