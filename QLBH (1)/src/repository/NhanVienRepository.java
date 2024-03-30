/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.NhanVien;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lsevice.Invsv;
import repository.Lrepo.lNv;

/**
 *
 * @author Admin
 */
public class NhanVienRepository implements lNv {

    @Override
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

//    public List<ChucVu> getCV() {
//
//        listCV = new ArrayList<>();
//        Connection conn = DBConnection.getConnection();
//        String select = "SELECT * FROM ChucVu";
//        try {
//            PreparedStatement ps = conn.prepareStatement(select);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                listCV.add(new ChucVu(rs.getInt(1), rs.getString(2)));
//            }
//            rs.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(NhanVienRepository.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listCV;
//    }
    @Override
    public boolean insert(NhanVien nv) {
        int check = 0;
        try (Connection conn = DBConnection.getConnection()) {
            String insertQuery = "INSERT INTO [dbo].[NhanVien] ([TenNhanVien], [GioiTinh], [NgaySinh], [DiaChi], [Sdt], [Email], [MatKhau], [ChucVu], [TaiKhoan], [TrangThai],[MaNhanvien])\n"
                    + "VALUES (?,?,? , ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(insertQuery)) {
                // Set parameters
                ps.setString(1, nv.getTenNV());
                ps.setString(2, nv.getGioiTinh());
                ps.setString(3, nv.getNgaySinh());
                ps.setString(4, nv.getDiaChi());
                ps.setString(5, nv.getSdt());
                ps.setString(6, nv.getEmail());
                ps.setString(7, nv.getMatKhau());
                ps.setString(8, nv.getChucVu());
                ps.setString(9, nv.getTaiKhoan());
                ps.setInt(10, nv.getTrangThai());
                ps.setString(11, "");
                // Execute update
                check = ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL Exception during statement execution: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception during connection creation: " + e.getMessage());
        }

        return check > 0;
    }

    @Override
    public boolean update(String ma, NhanVien nv) {
        String updateQuery = "UPDATE [dbo].[NhanVien]\n"
                + "SET [TenNhanVien] = ?\n"
                + ",[GioiTinh]= ?\n"
                + ",[NgaySinh]= ?\n"
                + ",[DiaChi]= ?\n"
                + ",[Sdt]= ?\n"
                + ",[Email] = ?\n"
                + ",[MatKhau]= ?\n"
                + ",[ChucVu]= ?\n"
                + ",[TaiKhoan]= ?\n"
                + ",[TrangThai]= ?\n"
                + "WHERE MaNhanVien = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(updateQuery)) {

            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getGioiTinh());
            ps.setString(3, nv.getNgaySinh());
            ps.setString(4, nv.getDiaChi());
            ps.setString(5, nv.getSdt());
            ps.setString(6, nv.getEmail());
            ps.setString(7, nv.getMatKhau());
            ps.setString(8, nv.getChucVu());
            ps.setString(9, nv.getTaiKhoan());
            ps.setInt(10, nv.getTrangThai());
            ps.setString(11, ma);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException ex) {
            // Log the exception or handle it according to your application's needs
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int delete(String Ma) {
        int result = -1;
        try {
            Connection connection = DBConnection.getConnection();
            String query = """
                           DELETE FROM NhanVien  WHERE MaNhanVien =  
                           """ + "'" + Ma + "'";
            System.out.println(query);
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(query);

        } catch (Exception ex) {
            System.out.println("Lỗi");
        }

        return result;
    }

    @Override
    public List<NhanVien> phantrang(Integer phantu) {
        List<NhanVien> listNV = new ArrayList<>();
        NhanVien nv = new NhanVien();
        try {
            Connection con = DBConnection.getConnection();
            String query = "SELECT MaNhanVien, TenNhanVien, GioiTinh, NgaySinh, DiaChi,Sdt, Email,"
                    + " TaiKhoan, MatKhau, ChucVu, TrangThai FROM NhanVien ORDER BY MaNhanVien OFFSET ? "
                    + " ROWS FETCH NEXT 5 ROWS ONLY";

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, phantu);

                ResultSet rs = ps.executeQuery(query);

                while (rs.next()) {

                    // Set properties for each record
                    nv.setMaNV(rs.getString("MaNhanVien"));
                    nv.setTenNV(rs.getString("TenNhanVien"));
                    nv.setGioiTinh(rs.getString("GioiTinh"));
                    nv.setNgaySinh(rs.getString("NgaySinh"));
                    nv.setDiaChi(rs.getString("DiaChi"));
                    nv.setSdt(rs.getString("Sdt"));
                    nv.setEmail(rs.getString("Email"));
                    nv.setTaiKhoan(rs.getString("TaiKhoan"));
                    nv.setMatKhau(rs.getString("MatKhau"));
                    nv.setChucVu(rs.getString("ChucVu"));
                    nv.setTrangThai(rs.getInt("TrangThai"));

                    listNV.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());
        }

        return listNV;
    }

    @Override
    public List<NhanVien> timkiemphantrang(String mA, int phantu, int tt) {
        List<NhanVien> listNV = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();
            String tthai = (tt == -1) ? "trangthai" : String.valueOf(tt);
            String where_condition = "where (MaNhanVien like '%" + mA + "%' or TenNhanVien like '%" + mA + "%') and trangthai like " + tthai + " ";
            String phantrang = " order by MaNhanVien\n" + "offset " + phantu + " rows fetch next 5 rows only";
            String query = "SELECT MaNhanVien, TenNhanVien, GioiTinh, NgaySinh, DiaChi,"
                    + "Sdt, Email, Taikhoan, MatKhau, chucvu, TrangThai from NhanVien "
                    + where_condition + phantrang;

            System.out.println(query);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                NhanVien nv = new NhanVien(); // Tạo một đối tượng mới trong mỗi lần lặp

                nv.setMaNV(rs.getString("MaNhanVien"));
                nv.setTenNV(rs.getString("TenNhanVien"));
                nv.setGioiTinh(rs.getString("GioiTinh"));
                nv.setNgaySinh(rs.getString("NgaySinh"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setSdt(rs.getString("Sdt"));
                nv.setEmail(rs.getString("Email"));
                nv.setTaiKhoan(rs.getString("Taikhoan"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setChucVu(rs.getString("chucvu"));
                nv.setTrangThai(rs.getInt("TrangThai"));

                listNV.add(nv);
            }

        } catch (Exception ex) {
            System.out.println("Lỗi" + ex.toString());
        }

        return listNV;
    }

    @Override
    public ArrayList<NhanVien> getTK() throws Exception {
        ArrayList<NhanVien> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement("SELECT MaNhanVien, TaiKhoan, MatKhau, chucvu FROM NhanVien")) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String ma = rs.getString("MaNhanVien");
                    String taiKhoan = rs.getString("TaiKhoan");
                    String matKhau = rs.getString("MatKhau");
                    String chucVu = rs.getString("chucvu");
                    NhanVien nv = new NhanVien(ma, taiKhoan, matKhau, chucVu);
                    list.add(nv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
