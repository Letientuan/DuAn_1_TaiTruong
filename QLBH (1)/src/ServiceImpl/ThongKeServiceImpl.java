/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ServiceImpl;

import entity.entities.HoaDonChiTietAn;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author thong
 */
public interface ThongKeServiceImpl {

    List<HoaDonChiTietAn> getListTK();

    BigDecimal getListDoangThu();

    int getLisKhachHang();

    int getListhoaDon();

    List<HoaDonChiTietAn> searchDoanhThu();

    List<HoaDonChiTietAn> searchHoaDon();

    List<HoaDonChiTietAn> searchSoLuong();
    
    List<HoaDonChiTietAn> searchTenSP(String tenSP);
    
}
