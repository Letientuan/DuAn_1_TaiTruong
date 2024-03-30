///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package repository;
//
//import Utilities.DBConnection;
//import entity.DangNhap;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
///**
// *
// * @author Admin
// */
//public class DangNhapRepository {
//
//    public boolean insert(DangNhap dangNhap) {
//        int check = 0;
//        Connection conn = DBConnection.getConnection();
//        String insert
//                = "INSERT INTO [dbo].[Account]"
//                + "           ([UserName] "
//                + "           ,[Pass]) "
//                + "      VALUES"
//                + "           (?,?)";
//        try {
//            PreparedStatement ps = conn.prepareStatement(insert);
//
//            ps.setObject(1, dangNhap.getUserName());
//            ps.setObject(2, dangNhap.getPassWord());
//            check = ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return check > 0;
//    }
//
//    public void update(DangNhap dangNhap, Integer pass) {
//        int check = 0;
//        Connection conn = DBConnection.getConnection();
//        String insert
//                = "UPDATE Account "
//                + "   SET  "
//                + "       UserName =?,    [Pass] = ? "
//                + "  WHERE "
//                + "     [Id] = ?       ";
//        try {
//            PreparedStatement ps = conn.prepareStatement(insert);
//            ps.setObject(1, dangNhap.getUserName());
//            ps.setObject(2, dangNhap.getPassWord());
//            ps.setObject(3, pass);
//            check = ps.executeUpdate();
//
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//    }
//
//    public static ArrayList<DangNhap> getTK() throws SQLException {
//        ArrayList<DangNhap> list = new ArrayList<>();
//        try {
//            Connection conn = DBConnection.getConnection();
//
//            String sql = "SELECT [Id]\n"
//                    + "      ,[UserName]\n"
//                    + "      ,[Pass]\n"
//                    + "  FROM [dbo].[Account]";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//            while (rs.next() == true) {
//
//                Integer id = rs.getInt("id");
//                String userName = rs.getString(2);
//                String pass = rs.getString(3);
//
//                DangNhap taikhoan = new DangNhap(id, userName, pass);
//                list.add(taikhoan);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return list;
//    }
//
//    public DangNhap getOneByMaNhanVien(String maNhanVien) throws SQLException {
//        DangNhap taikhoan = null;
//        try {
//            Connection conn = DBConnection.getConnection();
//
//            String sql = "SELECT [Id]\n"
//                    + "      ,[UserName]\n"
//                    + "      ,[Pass]\n"
//                    + "  FROM [dbo].[Account] b\n"
//                    + "\n"
//                    + "  join NhanVien a on a.MatKhau = b.Id\n"
//                    + "  where a.MaNhanVien = ? ";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setObject(1, maNhanVien);
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//            while (rs.next() == true) {
//                Integer id = rs.getInt("Id");
//                String userName = rs.getString(2);
//                String pass = rs.getString(3);
//
//                taikhoan = new DangNhap(id, userName, pass);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return taikhoan;
//    }
//
//    
//      public DangNhap getOneByUserName(String userName) throws SQLException {
//        DangNhap taikhoan = null;
//        try {
//            Connection conn = DBConnection.getConnection();
//
//            String sql = "SELECT [Id]\n"
//                    + "      ,[UserName]\n"
//                    + "      ,[Pass]\n"
//                    + "  FROM [dbo].[Account] \n"
//                    + "  where UserName = ? ";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setObject(1, userName);
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//            while (rs.next() == true) {
//                Integer id = rs.getInt("Id");
//                String user = rs.getString(2);
//                String pass = rs.getString(3);
//
//                taikhoan = new DangNhap(id, user, pass);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return taikhoan;
//    }
//
//    public DangNhap getOneByMaPass(String passWord) throws SQLException {
//        DangNhap taikhoan = null;
//        try {
//            Connection conn = DBConnection.getConnection();
//
//            String sql = "SELECT [Id]\n"
//                    + "      ,[UserName]\n"
//                    + "      ,[Pass]\n"
//                    + "  FROM [dbo].[Account] b \n"
//                    + "  where b.Pass = ? ";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setObject(1, passWord);
////            ps.setObject(2, username);
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//            while (rs.next() == true) {
//                Integer id = rs.getInt("Id");
//                String userName = rs.getString(2);
//                String pass = rs.getString(3);
//
//                taikhoan = new DangNhap(id, userName, pass);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return taikhoan;
//    }
//    
//     public void deleteByMaNhanVien(String userName) throws SQLException {
//        DangNhap taikhoan = null;
//        try {
//            Connection conn = DBConnection.getConnection();
//
//            String sql = "DELETE "
//                    + "  FROM [dbo].[Account]  "
//                    + "  where UserName = ? ";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setObject(1, userName);
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }
//
//
//    public static void main(String[] args) throws SQLException {
//        System.out.println(new DangNhapRepository().getOneByMaPass("123"));
//
//    }
//}
