/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;

import entity.entities.HoaDonAn;
import entity.entities.HoaDonChiTietAn;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thong
 */
public class HoaDonRepositoryAn {

    public List<HoaDonAn> getListHD() {
        List<HoaDonAn> listHD = new ArrayList<>();
        String sql = "select MaHoaDon, KhachHang.MaKhachHang as 'maKH',KhachHang.TenKhachHang as'TenKH',NhanVien.MaNhanVien as'maNV', NhanVien.TenNhanVien as'tenNV',NgayTao,NgayThanhToan,TinhTrang,GhiChu,Tongtien,Chietkhau,ThanhToan,PhuongThucThanhToan\n"
                + "from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien\n"
                + "			join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang hang = new KhachHang(rs.getString("maKH"), rs.getString("TenKH"));
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"));
                listHD.add(new HoaDonAn(rs.getLong("MaHoaDon"), hang, nhanVien,
                        rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"),
                        rs.getInt("TinhTrang"),
                        rs.getString("GhiChu"),
                        rs.getDouble("ChietKhau"),
                        rs.getDouble("TongTien"),
                        rs.getDouble("ThanhToan"),
                        rs.getString("PhuongThucThanhToan")
                ));
            }
            System.out.println(sql);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return listHD;
    }

    public List<HoaDonAn> searchListHD(Long maHD) {
        List<HoaDonAn> listHD = new ArrayList<>();
        String sql = "select MaHoaDon, KhachHang.MaKhachHang as 'maKH',KhachHang.TenKhachHang as'TenKH',NhanVien.MaNhanVien as'maNV', NhanVien.TenNhanVien as'tenNV',NgayTao,NgayThanhToan,TinhTrang,GhiChu,Tongtien,Chietkhau,ThanhToan,PhuongThucThanhToan\n"
                + "from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien\n"
                + "			join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang\n"
                + "where MaHoaDon = ?";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, maHD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang hang = new KhachHang(rs.getString("maKH"), rs.getString("TenKH"));
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"));
                listHD.add(new HoaDonAn(rs.getLong("MaHoaDon"), hang, nhanVien,
                        rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"),
                        rs.getInt("TinhTrang"),
                        rs.getString("GhiChu"),
                        rs.getDouble("ChietKhau"),
                        rs.getDouble("TongTien"),
                        rs.getDouble("ThanhToan"),
                        rs.getString("PhuongThucThanhToan")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRepositoryAn.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listHD;
    }

    public List<HoaDonAn> searchTinhTrang(int tinhTrang) {
        List<HoaDonAn> listHD = new ArrayList<>();
        String sql = "select MaHoaDon, KhachHang.MaKhachHang as 'maKH',KhachHang.TenKhachHang as'TenKH',NhanVien.MaNhanVien as'maNV', NhanVien.TenNhanVien as'tenNV',NgayTao,NgayThanhToan,TinhTrang,GhiChu,Tongtien,Chietkhau,ThanhToan,PhuongThucThanhToan \n"
                + "from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien\n"
                + "			join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang\n"
                + "where TinhTrang = ?";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, tinhTrang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang hang = new KhachHang(rs.getString("maKH"), rs.getString("TenKH"));
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"));
                listHD.add(new HoaDonAn(rs.getLong("MaHoaDon"), hang, nhanVien,
                        rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"),
                        rs.getInt("TinhTrang"),
                        rs.getString("GhiChu"),
                        rs.getDouble("ChietKhau"),
                        rs.getDouble("TongTien"),
                        rs.getDouble("ThanhToan"),
                        rs.getString("PhuongThucThanhToan")
                ));
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            Logger.getLogger(HoaDonRepositoryAn.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listHD;
    }

    public List<HoaDonAn> searchNgay(String ngayTao, String ngaythanhToan) {
        List<HoaDonAn> listHD = new ArrayList<>();
        String sql = "select MaHoaDon, KhachHang.MaKhachHang as 'maKH',KhachHang.TenKhachHang as'TenKH',NhanVien.MaNhanVien as'maNV', NhanVien.TenNhanVien as'tenNV',\n"
                + "NgayTao,NgayThanhToan,TinhTrang,GhiChu,Tongtien,Chietkhau,ThanhToan,PhuongThucThanhToan \n"
                + "from HoaDon join NhanVien on HoaDon.IdNV = NhanVien.MaNhanVien\n"
                + "				join KhachHang on HoaDon.IdKH = KhachHang.MaKhachHang \n"
                + "				where  NgayTao between ? and ?";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ngaythanhToan);
            ps.setString(2, ngayTao);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang hang = new KhachHang(rs.getString("maKH"), rs.getString("TenKH"));
                NhanVien nhanVien = new NhanVien(rs.getString("maNV"), rs.getString("tenNV"));
                listHD.add(new HoaDonAn(rs.getLong("MaHoaDon"), hang, nhanVien,
                        rs.getString("NgayTao"),
                        rs.getString("NgayThanhToan"),
                        rs.getInt("TinhTrang"),
                        rs.getString("GhiChu"),
                        rs.getDouble("ChietKhau"),
                        rs.getDouble("TongTien"),
                        rs.getDouble("ThanhToan"),
                        rs.getString("PhuongThucThanhToan")
                ));
                System.out.println(sql);
            }
        } catch (SQLException ex) {
            Logger.getLogger(HoaDonRepositoryAn.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listHD;
    }

    public List<HoaDonChiTietAn> listHDCTGetHD(Long maHoaD) {
        List<HoaDonChiTietAn> chiTiets = new ArrayList<>();
        String sql = "select MaHDCT, hdct.MaHoaDon, ctsp.MaSP, ctsp.TenSP, SoLuong, DonGia, sum(SoLuong * DonGia) as thanhtien "
                + "from HoaDonChiTiet as hdct join HoaDon as hd on hdct.MaHoaDon = hd.MaHoaDon join SanPham as ctsp on hdct.MaSP = ctsp.MaSP "
                + "where hdct.MaHoaDon = ? "
                + "group by MaHDCT, hdct.MaHoaDon, ctsp.MaSP, SoLuong, DonGia, ctsp.TenSP;";
        Connection conn = DBConnection.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, maHoaD); // Thiết lập giá trị cho tham số ? trong câu truy vấn
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonAn don = new HoaDonAn(rs.getLong("MaHoaDon"));
                SanPham sanPham = new SanPham(rs.getString("MaSP"));
                chiTiets.add(new HoaDonChiTietAn(rs.getLong("MaHDCT"), don,
                        sanPham, rs.getInt("SoLuong"),
                        rs.getDouble("DonGia"), rs.getString("TenSP"), rs.getDouble("thanhtien")));
            }
            System.out.println(sql);
        } catch (SQLException ex) {
            System.out.println();
            Logger.getLogger(HoaDonRepositoryAn.class.getName()).log(Level.SEVERE, null, ex);
        }

        return chiTiets;
    }

}
