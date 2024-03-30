/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class HoaDon {
    
    private Long maHD;
    private String makhachHang;
    private String manhanVien;
    private String ngayTao;
    private String ngayThanhToan;
    private Integer tinhTrang;
    private String ghichu;
    private Double tongTien;
    private Double chietkhau;
    private Double tongtienthanhtoan;
    private String phuongThucThanhToan;

    public HoaDon() {
    }

    public HoaDon(Long maHD, String makhachHang, String manhanVien, String ngayTao, String ngayThanhToan, Integer tinhTrang, String ghichu, Double tongTien, Double chietkhau, Double tongtienthanhtoan, String phuongThucThanhToan) {
        this.maHD = maHD;
        this.makhachHang = makhachHang;
        this.manhanVien = manhanVien;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tinhTrang = tinhTrang;
        this.ghichu = ghichu;
        this.tongTien = tongTien;
        this.chietkhau = chietkhau;
        this.tongtienthanhtoan = tongtienthanhtoan;
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public Long getMaHD() {
        return maHD;
    }

    public void setMaHD(Long maHD) {
        this.maHD = maHD;
    }

    public String getMakhachHang() {
        return makhachHang;
    }

    public void setMakhachHang(String makhachHang) {
        this.makhachHang = makhachHang;
    }

    public String getManhanVien() {
        return manhanVien;
    }

    public void setManhanVien(String manhanVien) {
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

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public Double getChietkhau() {
        return chietkhau;
    }

    public void setChietkhau(Double chietkhau) {
        this.chietkhau = chietkhau;
    }

    public Double getTongtienthanhtoan() {
        return tongtienthanhtoan;
    }

    public void setTongtienthanhtoan(Double tongtienthanhtoan) {
        this.tongtienthanhtoan = tongtienthanhtoan;
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
