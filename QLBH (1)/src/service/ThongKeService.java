/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import ServiceImpl.ThongKeServiceImpl;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.entities.HoaDonChiTietAn;
import java.math.BigDecimal;
import java.util.List;
import repository.ThongKeRpository;

/**
 *
 * @author thong
 */
public class ThongKeService implements ThongKeServiceImpl {

    private ThongKeRpository tkRpo = new ThongKeRpository();

    @Override
    public List<HoaDonChiTietAn> getListTK() {
        return tkRpo.getListTK();
    }

    @Override
    public BigDecimal getListDoangThu() {
        return tkRpo.getListDoangThu();
    }

    @Override
    public int getLisKhachHang() {
        return tkRpo.getLisKhachHang();
    }

    @Override
    public int getListhoaDon() {
        return tkRpo.getListhoaDon();
    }

    @Override
    public List<HoaDonChiTietAn> searchDoanhThu() {
        return tkRpo.searchDoanhThu();
    }

    @Override
    public List<HoaDonChiTietAn> searchHoaDon() {
        return tkRpo.searchHoaDon();
    }

    @Override
    public List<HoaDonChiTietAn> searchSoLuong() {
        return tkRpo.searchSoLuong();
    }

    @Override
    public List<HoaDonChiTietAn> searchTenSP(String tenSP) {
        return tkRpo.searchTenSP(tenSP);
    }

}
