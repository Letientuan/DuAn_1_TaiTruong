/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.entities.HoaDonAn;
import entity.entities.HoaDonChiTietAn;
import java.util.List;
import repository.HoaDonRepositoryAn;
import ServiceImpl.HoaDonServiceImpl;

/**
 *
 * @author thong
 */
public class HoaDonService implements HoaDonServiceImpl {

    private HoaDonRepositoryAn hdrepo = new HoaDonRepositoryAn();

    @Override
    public List<HoaDonAn> getListHD() {
        return hdrepo.getListHD();
    }

    @Override
    public List<HoaDonAn> searchListHD(Long maHD) {
        return hdrepo.searchListHD(maHD);
    }

    @Override
    public List<HoaDonAn> searchTinhTrang(int tinhTrang) {
        return hdrepo.searchTinhTrang(tinhTrang);
    }

    @Override
    public List<HoaDonAn> searchNgay(String ngayTao, String ngaythanhToan) {
        return hdrepo.searchNgay(ngayTao, ngaythanhToan);
    }

    @Override
    public List<HoaDonChiTietAn> listHDCTGetHD(Long maHoaD) {
        return hdrepo.listHDCTGetHD(maHoaD);
    }
}
