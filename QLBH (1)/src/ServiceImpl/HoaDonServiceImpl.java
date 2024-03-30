/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ServiceImpl;

import entity.entities.HoaDonAn;
import entity.entities.HoaDonChiTietAn;
import java.util.List;

/**
 *
 * @author thong
 */
public interface HoaDonServiceImpl {

    List<HoaDonAn> getListHD();

    List<HoaDonAn> searchListHD(Long maHD);

    List<HoaDonAn> searchTinhTrang(int tinhTrang);

    List<HoaDonAn> searchNgay(String ngayTao, String ngaythanhToan);
    
    List<HoaDonChiTietAn> listHDCTGetHD(Long maHoaD);
}
