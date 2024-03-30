/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.SanPhammd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.KhuyenMai;

/**
 *
 * @author ADMIN
 */
public class BanHangrp {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void updateSp(String masp, int soluongHDC) throws SQLException {
        String sql = "update sanpham set SoLuongTon = (SoLuongTon - ?) where masp = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, soluongHDC);
        ps.setString(2, masp);
        ps.executeUpdate();
    }

    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> listNV = new ArrayList<>();
        try {
            Connection conn = DBConnection.getConnection();
            String query = "SELECT MaNhanVien, TenNhanVien,GioiTinh,NgaySinh,DiaChi,"
                    + "Sdt,Email,Taikhoan,MatKhau,chucvu,TrangThai from NhanVien";

            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MaNhanVien");
                String ten = rs.getString("TenNhanVien");
                String gt = rs.getString("GioiTinh");
                String date = rs.getString("NgaySinh");
                String diaChi = rs.getString("DiaChi");
                String sdt = rs.getString("Sdt");
                String email = rs.getString("Email");
                String taiKhoan = rs.getString("Taikhoan");
                String matKhau = rs.getString("MatKhau");
                String chucVu = rs.getString("chucvu");
                Integer trangThai = rs.getInt("TrangThai");
                NhanVien nv = new NhanVien();
                nv.setMaNV(ma);
                nv.setTenNV(ten);
                nv.setGioiTinh(gt);
                nv.setNgaySinh(date);
                nv.setDiaChi(diaChi);
                nv.setSdt(sdt);
                nv.setTaiKhoan(taiKhoan);
                nv.setMatKhau(matKhau);
                nv.setChucVu(chucVu);
                nv.setEmail(email);
                nv.setTrangThai(trangThai);

                listNV.add(nv);
            }
            System.out.println(query);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listNV;
    }

    public void addHDCTTT(HoaDonChiTiet hdct) throws SQLException {
        String sql = "INSERT INTO [dbo].[HoaDonChiTiet] ([MaSP], [tenSp], [SoLuong], [DonGia], [MaHoaDon], [chietkhau])"
                + "VALUES (?, ?, ?, ?, ?, ?);";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, hdct.getMasanPham());
            ps.setString(2, hdct.getTenSP());
            ps.setInt(3, hdct.getSoLuong());
            ps.setDouble(4, hdct.getDonGia());
            ps.setInt(5, hdct.getMahd());
            ps.setDouble(6, hdct.getChietkhau());

            ps.executeUpdate();
        } catch (SQLException ex) {
            // Handle the exception as needed (throw a custom exception, log, or notify)
            ex.printStackTrace(); // Log or handle the exception appropriately
            throw ex; // Re-throw the exception to indicate that the operation failed
        }
    }

    public double getTongTien(Long maHD) {
        double tongTien = 0;
        String sql = "SELECT SUM(DonGia * SoLuong) AS TongTien "
                + "FROM HoaDonChiTiet "
                + "JOIN HoaDon ON HoaDonChiTiet.MaHoaDon = HoaDon.MaHoaDon "
                + "WHERE HoaDonChiTiet.MaHoaDon = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, maHD);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongTien = rs.getDouble("TongTien");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or handle the exception appropriately
        }

        return tongTien;
    }
    
    public String getStrTongTien(Long maHD) {
        String tongTien = "";
        String sql = "SELECT SUM(DonGia * SoLuong) AS TongTien "
                + "FROM HoaDonChiTiet "
                + "JOIN HoaDon ON HoaDonChiTiet.MaHoaDon = HoaDon.MaHoaDon "
                + "WHERE HoaDonChiTiet.MaHoaDon = ?";

        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, maHD);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongTien = rs.getString("TongTien");
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace(); // Log or handle the exception appropriately
        }
        return tongTien;
    }

    public List<HoaDonChiTiet> getListHDCTTT() {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = "SELECT H.[MaHDCT], H.[MaSP], H.[tenSp], H.[SoLuong], H.[DonGia], H.[chietkhau] "
                + "FROM [dbo].[HoaDonChiTiet] H "
                + "JOIN [SanPham] S ON H.[MaSP] = S.[MaSP] "
                + "JOIN HoaDon D ON H.[MaHoaDon] = D.[MaHoaDon];";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long MaHDCT = rs.getLong("MaHDCT");
                String MaSP = rs.getString("MaSP");
                String tenSp = rs.getString("tenSp");
                int SoLuong = rs.getInt("SoLuong");
                double DonGia = rs.getDouble("DonGia");
                double chietkhau = rs.getDouble("chietkhau");

                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setMaHDCT(MaHDCT);
                hd.setMasanPham(MaSP);
                hd.setTenSP(tenSp);
                hd.setSoLuong(SoLuong);
                hd.setDonGia(DonGia);
                hd.setChietkhau(chietkhau);

                listHDCT.add(hd);
            }
        } catch (SQLException ex) {
            // Handle the exception as needed (throw a custom exception, log, or notify)
            ex.printStackTrace(); // Log or handle the exception appropriately
        }
        return listHDCT;
    }

    public boolean updateSoLuong(SanPhammd sp) {
        int check = 0;
        String query = "UPDATE [dbo].[Sanpham]\n"
                + "SET SoLuongTon = ?, TrangThai = ?\n"
                + "WHERE MaSP = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {

            ps.setObject(1, sp.getSoLuongTon());
            ps.setObject(2, sp.getTrangthai());
            ps.setObject(3, sp.getMaSP());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            // Log or throw a custom exception based on your application's needs
        }
        return check > 0;
    }

    public boolean updateSoLuongkhixoa(SanPhammd sp) {
        int check = 0;
        String query = "UPDATE [dbo].[Sanpham]\n"
                + "SET SoLuongTon = ? + SoLuongTon, TrangThai = ?\n"
                + "WHERE MaSP = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {

            ps.setObject(1, sp.getSoLuongTon());
            ps.setObject(2, sp.getTrangthai());
            ps.setObject(3, sp.getMaSP());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            // Log or throw a custom exception based on your application's needs
        }
        return check > 0;
    }

    public boolean updateHoaDonChiTiet(HoaDonChiTiet hdct) {
        int check = 0;
        String sql = "UPDATE [dbo].[HoaDonChiTiet] "
                + "SET  [SoLuong] = ?"
                + " WHERE [MaHDCT] = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, hdct.getSoLuong());

            ps.setLong(2, hdct.getMaHDCT());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            // Handle the exception as needed (throw a custom exception, log, or notify)
            ex.printStackTrace(); // Log or handle the exception appropriately

        }
        return check > 0;
    }

    public boolean updateHoaDonChiTietma(HoaDonChiTiet hdct) {
        int check = 0;
        String sql = "UPDATE [dbo].[HoaDonChiTiet] "
                + "SET  [SoLuong] = ? + SoLuong"
                + " WHERE [MaHDCT] = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {

            ps.setInt(1, hdct.getSoLuong());

            ps.setLong(2, hdct.getMaHDCT());

            check = ps.executeUpdate();

        } catch (SQLException ex) {
            // Handle the exception as needed (throw a custom exception, log, or notify)
            ex.printStackTrace(); // Log or handle the exception appropriately

        }
        return check > 0;
    }

    public List<HoaDonChiTiet> getListHDCt(int mahd) {
        List<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String sql = "SELECT H.[MaHDCT], H.[MaHoaDon], H.[MaSP], H.[tenSp], H.[SoLuong], H.[DonGia], H.[chietkhau] "
                + "FROM [dbo].[HoaDonChiTiet] H "
                + "JOIN [SanPham] S ON H.[MaSP] = S.[MaSP] "
                + "JOIN HoaDon D ON H.[MaHoaDon] = D.[MaHoaDon] "
                + "WHERE D.[MaHoaDon] = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {

            // Set parameter for the prepared statement
            ps.setInt(1, mahd);

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long maHDCT = rs.getLong(1);
                    String maSP = rs.getString(3);
                    String tenSP = rs.getString(4);
                    int soLuong = rs.getInt(5);
                    double donGia = rs.getDouble(6);
                    double chietKhau = rs.getDouble(7);

                    // Create HoaDonChiTiet object and set its properties
                    HoaDonChiTiet hd = new HoaDonChiTiet();
                    hd.setMaHDCT(maHDCT);
                    hd.setMasanPham(maSP);
                    hd.setTenSP(tenSP);
                    hd.setSoLuong(soLuong);
                    hd.setDonGia(donGia);
                    hd.setChietkhau(chietKhau);

                    // Add the HoaDonChiTiet object to the list
                    listHDCT.add(hd);
                }
            }
        } catch (SQLException ex) {
            // Handle the exception as needed (log or throw a custom exception)
            ex.printStackTrace();
        }
        return listHDCT;
    }

    public List<HoaDon> getListHD() {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT\n"
                + "    H.MaHoaDon, \n"
                + "    K.TenKhachHang, \n"
                + "    N.TenNhanVien,  \n"
                + "    H.NgayTao,\n"
                + "    H.NgayThanhToan,\n"
                + "    H.TinhTrang,\n"
                + "    H.GhiChu,\n"
                + "    H.Tongtien,\n"
                + "    H.Chietkhau,\n"
                + "    H.ThanhToan,\n"
                + "    H.PhuongThucThanhToan\n"
                + "FROM\n"
                + "    HoaDon H\n"
                + "JOIN\n"
                + "    KhachHang K ON H.IdKH = K.MaKhachHang\n"
                + "JOIN\n"
                + "    NhanVien N ON H.IdNV = N.MaNhanVien;";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                HoaDon hd = new HoaDon(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getString(11));
                listHD.add(hd);
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return listHD;
    }

    public List<HoaDon> getListHDByTinhTrang(int tinhTrang) {
        List<HoaDon> listHD = new ArrayList<>();
        String sql = "SELECT\n"
                + "    H.MaHoaDon, \n"
                + "    K.TenKhachHang, \n"
                + "    N.TenNhanVien,  \n"
                + "    H.NgayTao,\n"
                + "    H.NgayThanhToan,\n"
                + "    H.TinhTrang,\n"
                + "    H.GhiChu,\n"
                + "    H.Tongtien,\n"
                + "    H.Chietkhau,\n"
                + "    H.ThanhToan,\n"
                + "    H.PhuongThucThanhToan\n"
                + "FROM\n"
                + "    HoaDon H\n"
                + "JOIN\n"
                + "    KhachHang K ON H.IdKH = K.MaKhachHang\n"
                + "JOIN\n"
                + "    NhanVien N ON H.IdNV = N.MaNhanVien\n"
                + "WHERE\n"
                + "    H.TinhTrang = ?;"; // Using parameterized query to prevent SQL injection

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {
            ps.setInt(1, tinhTrang); // Set the value for the parameter

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getString(11));
                listHD.add(hd);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listHD;
    }

    public void addHDTT(HoaDon don) throws SQLException {
        String sql = "Insert into HoaDon(NgayTao,IdNV,IdKH,TinhTrang) values(?,?,?,?)";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, don.getNgayTao());
        ps.setString(2, don.getManhanVien());
        ps.setString(3, don.getMakhachHang());
        ps.setInt(4, don.getTinhTrang());
        ps.executeUpdate();

    }

    public List<KhachHang> findAll() {
        List<KhachHang> customers = new ArrayList();

        try {
            Connection connection = DBConnection.getConnection();
            String query = """
                           select MaKhachHang,TenKhachHang,DiaChi,SoDienThoai,Email,GioiTinh,TrangThai  from KhachHang
                           """;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                String ma = rs.getString("MaKhachHang");
                String ten = rs.getString("TenKhachHang");
                String diachi = rs.getString("DiaChi");
                String sdt = rs.getString("SoDienThoai");
                String email = rs.getString("Email");

                boolean gioitinh = rs.getBoolean("GioiTinh");
                Integer trangthai = rs.getInt("TrangThai");

                KhachHang customer = new KhachHang();
                customer.setMaKH(ma);
                customer.setTenKH(ten);
                customer.setDiaChi(diachi);
                customer.setSdt(sdt);
                customer.setEmail(email);

                customer.setGioiTinh(gioitinh);
                customer.setTrangThai(trangthai);

                customers.add(customer);
            }

        } catch (Exception ex) {
            System.out.println("Lỗi" + ex.toString());
        }

        return customers;
    }

    public boolean deleteHoaDonChiTietByMaHoaDon(int maHoaDon) {
        int affectedRows = 0;
        String sql = "DELETE FROM [dbo].[HoaDonChiTiet] WHERE [MaHoaDon] = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {

            // Set parameter for the prepared statement
            ps.setInt(1, maHoaDon);

            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            // Handle the exception as needed (log or throw a custom exception)
            ex.printStackTrace();
        }

        return affectedRows > 0;
    }

    public boolean deleteHoaDonChiTietByMaHoaDonct(int maHoaDon) {
        int affectedRows = 0;
        String sql = "DELETE FROM [dbo].[HoaDonChiTiet] WHERE [MaHDCT] = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(sql)) {

            // Set parameter for the prepared statement
            ps.setInt(1, maHoaDon);

            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            // Handle the exception as needed (log or throw a custom exception)
            ex.printStackTrace();
        }

        return affectedRows > 0;
    }

    public void editHDTT(HoaDon don) throws SQLException {
        String sql = "Update HoaDon set TinhTrang = ?  , TongTien = ? , PhuongThucThanhToan = ? ,ThanhToan=?,GhiChu = ?,ngaythanhtoan = ?  where MaHoaDon = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, don.getTinhTrang());
        ps.setDouble(2, don.getTongTien());
        ps.setString(3, don.getPhuongThucThanhToan());
        ps.setDouble(4, don.getTongtienthanhtoan());
        ps.setString(5, don.getGhichu());
        ps.setString(6, don.getNgayThanhToan());

        ps.setLong(7, don.getMaHD());
        ps.executeUpdate();
    }

    public void editHDTTTrangThai(HoaDon don) throws SQLException {
        String sql = "Update HoaDon set TinhTrang = ?    where MaHoaDon = ?";
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, don.getTinhTrang());
        ps.setLong(2, don.getMaHD());
        ps.executeUpdate();
    }

    public List<SanPhammd> chietkhau() {
        List<SanPhammd> sp = new ArrayList<>();
        String query = """
SELECT 
                           SanPham.MaSP,
                           NSX.Ten AS TenNSX,
                           MauSac.Ten AS TenMauSac,
                           DongSP.Ten AS TenDongSP,
                           ChatLieu.Ten AS TenChatLieu,
                           SanPham.Size,
                           SanPham.MoTa,
                           SanPham.SoLuongTon,
                           SanPham.GiaNhap,
                           SanPham.GiaBan,
                           SanPham.Anh,
                           SanPham.tensp,
                           SanPham.trangthai,
                           KhuyenMai.GiaTriGiam
                       FROM 
                           SanPham
                       JOIN 
                           NSX ON NSX.MANSX = SanPham.MANSX
                       JOIN 
                           mausac ON mausac.MaMau = SanPham.MAMausac
                       JOIN 
                           dongsp ON dongsp.MaDongSP = SanPham.MaDongSP
                       JOIN 
                           ChatLieu ON ChatLieu.MaChatLieu = SanPham.MaChatLieu
                       LEFT JOIN
                           KhuyenMai ON KhuyenMai.MaSanPham = SanPham.MaSP;  """;
        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String masp = rs.getString(1);
                String tensp = rs.getString(12);
                String NSX = rs.getString(2);
                String MauSac = rs.getString(3);
                String DongSP = rs.getString(4);
                String ChatLieu = rs.getString(5);
                String Size = rs.getString(6);
                String MoTa = rs.getString(7);
                int SoLuongTon = Integer.parseInt(rs.getString(8));
                double GiaNhap = Double.parseDouble(rs.getString(9));
                double GiaBan = Double.parseDouble(rs.getString(10));
                String anhsp = rs.getString(11);
                int trangthai = rs.getInt(13);
                double chieukhau = Double.valueOf(rs.getString(14));

                SanPhammd sanPhammd = new SanPhammd();
                sanPhammd.setMaSP(masp);
                sanPhammd.setTenSP(tensp);
                sanPhammd.setTennsx(NSX);
                sanPhammd.setTenmauSac(MauSac);
                sanPhammd.setTendongSP(DongSP);
                sanPhammd.setTenchatLieu(ChatLieu);
                sanPhammd.setSize(Size);
                sanPhammd.setMoTa(MoTa);
                sanPhammd.setSoLuongTon(SoLuongTon);
                sanPhammd.setGiaNhap(GiaNhap);
                sanPhammd.setGiaBan(GiaBan);
                sanPhammd.setAnhSp(anhsp);
                sanPhammd.setTrangthai(trangthai);
                sanPhammd.setChietkhau(chieukhau);
                sp.add(sanPhammd);
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sp;
    }

    public List<entity.KhuyenMai> findKhuyenMai() {
        List<entity.KhuyenMai> khuyenMaiList = new ArrayList<>();

        try ( Connection connection = DBConnection.getConnection();  Statement statement = connection.createStatement();  ResultSet rs = statement.executeQuery("SELECT MaKM, TenKhuyenMai,hinhthucgiam, GiaTriGiam, MoTa, MaSanPham FROM KhuyenMai")) {

            while (rs.next()) {
                int maKM = rs.getInt("MaKM");
                String tenKhuyenMai = rs.getString("TenKhuyenMai");
                double giaTriGiam = rs.getDouble("GiaTriGiam");
                String moTa = rs.getString("MoTa");
                String maSanPham = rs.getString("MaSanPham");
                boolean hinhthuc = rs.getBoolean("hinhthucgiam");

                entity.KhuyenMai khuyenMai = new entity.KhuyenMai();
                khuyenMai.setMakm(maKM);
                khuyenMai.setTenkm(tenKhuyenMai);
                khuyenMai.setGiatrigiam(giaTriGiam);
                khuyenMai.setMota(moTa);
                khuyenMai.setMasp(maSanPham);
                khuyenMai.setHinhthucgiam(hinhthuc);
                khuyenMaiList.add(khuyenMai);
            }

        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.toString());
        }

        return khuyenMaiList;
    }

    public boolean insertKhuyenMai(entity.KhuyenMai khuyenMai) {
        try ( Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO KhuyenMai ( TenKhuyenMai, hinhthucgiam, GiaTriGiam, MoTa, MaSanPham) VALUES ( ?, ?, ?, ?, ?)";

            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setString(1, khuyenMai.getTenkm());
                preparedStatement.setBoolean(2, khuyenMai.isHinhthucgiam());
                preparedStatement.setDouble(3, khuyenMai.getGiatrigiam());
                preparedStatement.setString(4, khuyenMai.getMota());
                preparedStatement.setString(5, khuyenMai.getMasp());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.toString());
            return false;
        }
    }

    public boolean insertKhuyenMai1(entity.KhuyenMai khuyenMai) {
        try ( Connection connection = DBConnection.getConnection()) {
            String query = "INSERT INTO KhuyenMai ( hinhthucgiam, GiaTriGiam, MaSanPham) VALUES ( ?, ?, ?)";

            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setBoolean(1, khuyenMai.isHinhthucgiam());
                preparedStatement.setDouble(2, khuyenMai.getGiatrigiam());
                preparedStatement.setString(3, khuyenMai.getMasp());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.toString());
            return false;
        }
    }

    public boolean updateKhuyenMai(entity.KhuyenMai khuyenMai) {
        try ( Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE KhuyenMai SET TenKhuyenMai = ?, hinhthucgiam = ?, GiaTriGiam = ?, MoTa = ?, MaSanPham = ? WHERE MaKM = ?";

            try ( PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, khuyenMai.getTenkm());
                preparedStatement.setBoolean(2, khuyenMai.isHinhthucgiam());
                preparedStatement.setDouble(3, khuyenMai.getGiatrigiam());
                preparedStatement.setString(4, khuyenMai.getMota());
                preparedStatement.setString(5, khuyenMai.getMasp());
                preparedStatement.setInt(6, khuyenMai.getMakm());

                int rowsAffected = preparedStatement.executeUpdate();

                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            System.out.println("Lỗi: " + ex.toString());
            return false;
        }
    }

  public List<SanPhammd> Timkiemsp(String mA, String tt) {
    List<SanPhammd> sp = new ArrayList<>();
    String Trangthai="Size";
    if(tt == "Size"){
        Trangthai = Trangthai;
    }
    else{
        Trangthai = "'"+tt+"'";
    }

    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("SELECT MaSP, NSX.Ten, MauSac.Ten, DongSP.Ten, ChatLieu.Ten, Size, MoTa, SoLuongTon, GiaNhap, GiaBan, Anh, tensp, trangthai ")
                .append("FROM sanpham ")
                .append("JOIN NSX ON nsx.mansx = sanpham.mansx ")
                .append("JOIN mausac ON mausac.MaMau = sanpham.mamausac ")
                .append("JOIN dongsp ON dongsp.madongsp = sanpham.madongsp ")
                .append("JOIN ChatLieu ON ChatLieu.machatlieu = SanPham.MaChatLieu ")
                .append("WHERE (MaSP LIKE ? OR tensp LIKE ?) AND Size LIKE " + Trangthai +"");

    try (Connection cnn = DBConnection.getConnection();
         PreparedStatement ps = cnn.prepareStatement(queryBuilder.toString())) {

        ps.setString(1, "%" + mA + "%");
        ps.setString(2, "%" + mA + "%");
       

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
              String masp = rs.getString(1);
                String tensp = rs.getString(12);
                String NSX = rs.getString(2);
                String MauSac = rs.getString(3);
                String DongSP = rs.getString(4);
                String ChatLieu = rs.getString(5);
                String Size = rs.getString(6);
                String MoTa = rs.getString(7);
                int SoLuongTon = rs.getInt(8);
                double GiaNhap = rs.getDouble(9);
                double GiaBan = rs.getDouble(10);
                String anhsp = rs.getString(11);
                int trangthai = rs.getInt(13);

                SanPhammd sanPhammd = new SanPhammd();
                sanPhammd.setMaSP(masp);
                sanPhammd.setTenSP(tensp);
                sanPhammd.setTennsx(NSX);
                sanPhammd.setTenmauSac(MauSac);
                sanPhammd.setTendongSP(DongSP);
                sanPhammd.setTenchatLieu(ChatLieu);
                sanPhammd.setSize(Size);
                sanPhammd.setMoTa(MoTa);
                sanPhammd.setSoLuongTon(SoLuongTon);
                sanPhammd.setGiaNhap(GiaNhap);
                sanPhammd.setGiaBan(GiaBan);
                sanPhammd.setAnhSp(anhsp);
                sanPhammd.setTrangthai(trangthai);
                sp.add(sanPhammd);
            }
        }
    } catch (SQLException e) {
        // Handle or log the exception appropriately
        e.printStackTrace(System.out);
    } catch (Exception e) {
        // Handle or log the exception appropriately
        e.printStackTrace(System.out);
    }
    return sp;
}



}
