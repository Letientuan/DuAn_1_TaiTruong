/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.SanPham;
import entity.entities.HoaDonChiTietAn;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thong
 */
public class ThongKeRpository {

    public List<HoaDonChiTietAn> getListTK() {
        List<HoaDonChiTietAn> listTK = new ArrayList<>();
        String sql = "SELECT sp.Tensp as 'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra', SUM(SoLuong) as 'slbanra', sp.GiaBan, sp.GiaNhap, SUM(SoLuong*GiaBan) as 'thanhtien'\n"
                + "FROM HoaDonChiTiet as hdct JOIN HoaDon as hd ON hdct.MaHoaDon = hd.MaHoaDon \n"
                + "JOIN SanPham as sp ON sp.MaSP = hdct.MaSP\n"
                + "WHERE hd.TinhTrang=1 GROUP BY sp.Tensp, sp.GiaBan, sp.GiaNhap";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham ctsp = new SanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));
                listTK.add(new HoaDonChiTietAn(ctsp, rs.getInt("slbanra"), rs.getString("Ten"),
                        rs.getInt("hoadonbanra"), rs.getDouble("thanhtien")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTK;
    }

    public BigDecimal getListDoangThu() {
        BigDecimal max = null;
        String sql = "select Sum(tongtien) as 'thanhtien' from HoaDon\n"
                + "where tinhTrang = 1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getBigDecimal("thanhtien");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return max;
    }

    public int getListhoaDon() {
        int max = 0;
        String sql = "select COUNT(MaHoaDon)as'hoaDon' from HoaDon\n"
                + "where TinhTrang = 1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getInt("hoaDon");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return max;
    }

    public int getLisKhachHang() {
        int max = 0;
        String sql = "select COUNT(IdKH)as'So' from HoaDon\n"
                + "where TinhTrang = 1";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                max = rs.getInt("So");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return max;
    }

    public List<HoaDonChiTietAn> searchSoLuong() {
        List<HoaDonChiTietAn> listTK = new ArrayList<>();
        String sql = "SELECT ctsp.Tensp as 'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra', SUM(SoLuong) as 'slbanra', ctsp.GiaBan, ctsp.GiaNhap, \n"
                + "SUM(SoLuong*GiaBan) as 'thanhtien'\n"
                + "FROM HoaDonChiTiet as hdct JOIN HoaDon as hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                + "JOIN SanPham as ctsp ON hdct.MaSP = ctsp.MaSP \n"
                + "WHERE hd.TinhTrang = 1 GROUP BY ctsp.Tensp, ctsp.GiaBan, ctsp.GiaNhap ORDER BY slbanra DESC";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                SanPham ctsp = new SanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTietAn(ctsp, rs.getInt("slbanra"), rs.getString("Ten"),
                        rs.getInt("hoadonbanra"), rs.getDouble("thanhtien")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTK;
    }

    public List<HoaDonChiTietAn> searchDoanhThu() {
        List<HoaDonChiTietAn> listTK = new ArrayList<>();
        String sql = "SELECT ctsp.Tensp as 'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra', SUM(SoLuong) as 'slbanra', ctsp.GiaBan, ctsp.GiaNhap, \n"
                + "SUM(SoLuong*GiaBan) as 'thanhtien'\n"
                + "FROM HoaDonChiTiet as hdct JOIN HoaDon as hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                + "JOIN SanPham as ctsp ON hdct.MaSP = ctsp.MaSP \n"
                + "WHERE hd.TinhTrang = 1 GROUP BY ctsp.Tensp, ctsp.GiaBan, ctsp.GiaNhap ORDER BY thanhtien DESC";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham ctsp = new SanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));
                listTK.add(new HoaDonChiTietAn(ctsp, rs.getInt("slbanra"), rs.getString("Ten"),
                        rs.getInt("hoadonbanra"), rs.getDouble("thanhtien")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTK;
    }

    public List<HoaDonChiTietAn> searchHoaDon() {
        List<HoaDonChiTietAn> listTK = new ArrayList<>();
        String sql = "SELECT sp.Tensp as 'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra', SUM(SoLuong) as 'slbanra', sp.GiaBan, sp.GiaNhap, SUM(SoLuong*GiaBan) as 'thanhtien'\n"
                + "FROM HoaDonChiTiet as hdct JOIN HoaDon as hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                + "JOIN SanPham as sp ON hdct.MaSP = sp.MaSP\n"
                + "GROUP BY sp.Tensp, sp.GiaBan, sp.GiaNhap\n"
                + "ORDER BY hoadonbanra DESC";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham ctsp = new SanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));

                listTK.add(new HoaDonChiTietAn(ctsp, rs.getInt("slbanra"), rs.getString("Ten"),
                        rs.getInt("hoadonbanra"), rs.getDouble("thanhtien")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTK;
    }

    public List<HoaDonChiTietAn> searchTenSP(String tenSP) {
        List<HoaDonChiTietAn> listTK = new ArrayList<>();
        String sql = "SELECT sp.Tensp as 'Ten', COUNT(hd.MaHoaDon) as 'hoadonbanra', SUM(SoLuong) as 'slbanra', sp.GiaBan, sp.GiaNhap, \n"
                + "SUM(SoLuong*GiaBan) as 'thanhtien'\n"
                + "FROM HoaDonChiTiet as hdct JOIN HoaDon as hd ON hdct.MaHoaDon = hd.MaHoaDon\n"
                + "JOIN SanPham as sp ON sp.MaSP = hdct.MaSP \n"
                + "WHERE hd.TinhTrang = 1 AND sp.Tensp LIKE ? GROUP BY sp.Tensp, sp.GiaBan, sp.GiaNhap";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps;

        try {
            ps = conn.prepareStatement(sql);
            // Set the parameter value
            ps.setString(1, "%" + tenSP + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPham ctsp = new SanPham(rs.getDouble("GiaNhap"), rs.getDouble("GiaBan"));
                listTK.add(new HoaDonChiTietAn(ctsp, rs.getInt("slbanra"), rs.getString("Ten"),
                        rs.getInt("hoadonbanra"), rs.getDouble("thanhtien")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTK;
    }

}
