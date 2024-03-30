/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class DongSanPham {
    private Integer maDongSP;
    private String tenDongSP;

    public DongSanPham(Integer maDongSP, String tenDongSP) {
        this.maDongSP = maDongSP;
        this.tenDongSP = tenDongSP;
    }

    public DongSanPham(String tenDongSP) {
        this.tenDongSP = tenDongSP;
    }

    public DongSanPham() {
    }

    public Integer getMaDongSP() {
        return maDongSP;
    }

    public void setMaDongSP(Integer maDongSP) {
        this.maDongSP = maDongSP;
    }

    public String getTenDongSP() {
        return tenDongSP;
    }

    public void setTenDongSP(String tenDongSP) {
        this.tenDongSP = tenDongSP;
    }
    public Object[] todataRow() {
        return new Object[]{maDongSP,tenDongSP};
    }

    @Override
    public String toString() {
        return tenDongSP ;
    }
    
}
