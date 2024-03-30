/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author thong
 */
public class NhaSanXuat {

    private Integer manhaSanXuat;
    private String tenNhaSanXuat;

    public NhaSanXuat() {
    }

    public NhaSanXuat(Integer manhaSanXuat, String tenNhaSanXuat) {
        this.manhaSanXuat = manhaSanXuat;
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public Integer getManhaSanXuat() {
        return manhaSanXuat;
    }

    public void setManhaSanXuat(Integer manhaSanXuat) {
        this.manhaSanXuat = manhaSanXuat;
    }

    public String getTenNhaSanXuat() {
        return tenNhaSanXuat;
    }

    public void setTenNhaSanXuat(String tenNhaSanXuat) {
        this.tenNhaSanXuat = tenNhaSanXuat;
    }

    public Object[] todataRow() {
        return new Object[]{manhaSanXuat, tenNhaSanXuat};
    }

    @Override
    public String toString() {
        return tenNhaSanXuat;
    }

}
