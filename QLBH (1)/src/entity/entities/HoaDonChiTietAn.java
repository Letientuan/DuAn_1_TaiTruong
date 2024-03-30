/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity.entities;

import entity.SanPham;
import entity.entities.HoaDonAn;

/**
 *
 * @author thong
 */
public class HoaDonChiTietAn {

    private Long maHDCTTT;
    private HoaDonAn maHD;
    private SanPham maSP;
    private Integer soLuong;
    private Double donGia;
    private String tenSP;
    private Double thanhtien;
    private Integer hoaDonBan;
    private Integer soLuongBanRa;
    private Double tongTienNe;

    public HoaDonChiTietAn() {
    }

    public Integer getHoaDonBan() {
        return hoaDonBan;
    }

    public void setHoaDonBan(Integer hoaDonBan) {
        this.hoaDonBan = hoaDonBan;
    }

    public Integer getSoLuongBanRa() {
        return soLuongBanRa;
    }

    public void setSoLuongBanRa(Integer soLuongBanRa) {
        this.soLuongBanRa = soLuongBanRa;
    }

    public Double getTongTienNe() {
        return tongTienNe;
    }

    public void setTongTienNe(Double tongTienNe) {
        this.tongTienNe = tongTienNe;
    }
    

    public HoaDonChiTietAn(Long maHDCTTT, HoaDonAn maHD, SanPham maSP, Integer soLuong, Double donGia, String tenSP, Double thanhtien) {
        this.maHDCTTT = maHDCTTT;
        this.maHD = maHD;
        this.maSP = maSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tenSP = tenSP;
        this.thanhtien = thanhtien;
    }

    public HoaDonChiTietAn(Long maHDCTTT, HoaDonAn maHDCT, SanPham maCTSPCT, Integer soLuong, Double donGia) {
        this.maHDCTTT = maHDCTTT;
        this.maHD = maHDCT;
        this.maSP = maCTSPCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    
    public HoaDonChiTietAn(SanPham maCTSPCT, Integer soLuongBanRa, String tenSP, Integer hoaDonBan, Double tongTien) {
        this.maSP = maCTSPCT;
        this.soLuongBanRa = soLuongBanRa;
        this.tenSP = tenSP;
        this.hoaDonBan = hoaDonBan;
        this.thanhtien = tongTien;
    }

    public Long getMaHDCTTT() {
        return maHDCTTT;
    }

    public void setMaHDCTTT(Long maHDCTTT) {
        this.maHDCTTT = maHDCTTT;
    }

    public HoaDonAn getMaHD() {
        return maHD;
    }

    public void setMaHD(HoaDonAn maHD) {
        this.maHD = maHD;
    }

    public SanPham getMaSP() {
        return maSP;
    }

    public void setMaSP(SanPham maSP) {
        this.maSP = maSP;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public Double getThanhtien() {
        return thanhtien;
    }

    public void setThanhtien(Double thanhtien) {
        this.thanhtien = thanhtien;
    }

}
