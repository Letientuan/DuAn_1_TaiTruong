/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String diaChi;
    private String sdt;
    private String email;
    public Boolean gioiTinh;
    private Integer trangThai;

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, String diaChi, String sdt, String email, Boolean gioiTinh, Integer trangThai) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.trangThai = trangThai;
    }

    public KhachHang(String maKH, String tenKH) {
        this.maKH = maKH;
        this.tenKH = tenKH;
    }

    public KhachHang(String maKH) {
        this.maKH = maKH;
    }
    
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
  public Object[] todataRow() {
        return new Object[]{maKH, tenKH, diaChi, sdt, email, gioiTinh,trangThai};
      }
  public String getTrangThai(Integer trangthai){
      if(trangThai == 1 ){
          return "hoạt động";
      }
      return "không hoạt động";
  }
  public String getgioitinh(){
      if(gioiTinh ==true){
          return "nam";
          
      }
      return "nữ";
  }
      public Integer isgioitinh(){
      if(gioiTinh ==true){
          return 1;
          
      }
      return 0;
     
}
     
}
