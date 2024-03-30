/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.ChatLieu;
import entity.SanPham;
import entity.DongSanPham;
//import entity.KichThuoc;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.SanPhammd;
import repository.Lrepo.isanphamrp;

/**
 *
 * @author admin
 */
public class sanphamRp implements isanphamrp {

    @Override
    public List<SanPhammd> findAll() {
        List<SanPhammd> sp = new ArrayList<>();
        String query = """
                         select MaSP,NSX.Ten,MauSac.Ten,DongSP.Ten,ChatLieu.Ten,Size,MoTa,SoLuongTon,GiaNhap,GiaBan,Anh,tensp,trangthai from sanpham join NSX on nsx.mansx = sanpham.mansx join mausac on mausac.MaMau=sanpham.mamausac join dongsp on dongsp.madongsp = sanpham.madongsp join ChatLieu on ChatLieu.machatlieu=SanPham.MaChatLieu
                       """;
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

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sp;
    }

    @Override
    public List<SanPhammd> Timkiem(String mA, int tt) {
        List<SanPhammd> sp = new ArrayList<>();
        String tthai = "trangthai";
        if (tt == -1) {
            tthai = tthai;
        } else {
            tthai = String.valueOf(tt);
        }
        String query = """
                     SELECT MaSP, NSX.Ten, MauSac.Ten, DongSP.Ten, ChatLieu.Ten, Size, MoTa, SoLuongTon, GiaNhap, GiaBan, Anh, tensp, trangthai 
                     FROM sanpham 
                     JOIN NSX ON nsx.mansx = sanpham.mansx 
                     JOIN mausac ON mausac.MaMau = sanpham.mamausac 
                     JOIN dongsp ON dongsp.madongsp = sanpham.madongsp 
                     JOIN ChatLieu ON ChatLieu.machatlieu = SanPham.MaChatLieu
                   """;
        String timkiem = query + " where (MaSP like '%" + mA + "%' or tensp like '%" + mA + "%'  )" + "and trangthai like   " + tthai + " ";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(timkiem)) {

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

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return sp;
    }

    @Override
    public void add(SanPham sp) {
        // int check = 0;
        String query = "INSERT INTO SanPham (MaSP,MaNsx,MaMauSac,MaDongSP,MaChatLieu,Size,MoTa,SoLuongTon,GiaNhap,GiaBan,tensp,Anh,trangthai) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        System.out.println(query);
        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, "");
            ps.setObject(2, sp.getNsx());
            ps.setObject(3, sp.getMamauSac());
            ps.setObject(4, sp.getDongSP());
            ps.setObject(5, sp.getChatLieu());
            ps.setObject(6, sp.getSize());
            ps.setObject(7, sp.getMoTa());
            ps.setObject(8, sp.getSoLuongTon());
            ps.setObject(9, sp.getGiaNhap());
            ps.setObject(10, sp.getGiaBan());
            ps.setObject(11, sp.getTenSP());
            ps.setObject(12, sp.getAnhSp());
            ps.setObject(13, sp.getTrangthai());
            ps.executeUpdate();

            //check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

    }
//

    @Override
    public boolean update(SanPham sp) {
        int check = 0;
        String query = "UPDATE [dbo].[Sanpham]\n"
                + "   SET MaSP = ?,\n"
                + "      MaNsx = ?,\n"
                + "      MaMauSac = ?,\n"
                + "      MaDongSP = ?,\n"
                + "      MaChatLieu = ?,\n"
                + "      Size = ?,\n"
                + "      MoTa = ?,\n"
                + "      SoLuongTon = ?,\n"
                + "      GiaNhap = ?,\n"
                + "      GiaBan = ?,\n"
                + "      tensp = ?,\n"
                + "      Anh = ?,\n"
                + "      trangthai = ?\n"
                + " WHERE MaSP = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, sp.getMaSP());
            ps.setObject(2, sp.getNsx());
            ps.setObject(3, sp.getMamauSac());
            ps.setObject(4, sp.getDongSP());
            ps.setObject(5, sp.getChatLieu());
            ps.setObject(6, sp.getSize());
            ps.setObject(7, sp.getMoTa());
            ps.setObject(8, sp.getSoLuongTon());
            ps.setObject(9, sp.getGiaNhap());
            ps.setObject(10, sp.getGiaBan());
            ps.setObject(11, sp.getTenSP());
            ps.setObject(12, sp.getAnhSp());
            ps.setObject(13, sp.getTrangthai());
            ps.setObject(14, sp.getMaSP()); // Set the value for WHERE clause

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
//

    @Override
    public boolean delete(String sp) {
        int check = 0;
        String query = "DELETE FROM [dbo].[sanpham]"
                + "      WHERE MaSP = ?";
        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, sp);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updatexoa(SanPham sp) {
        int check = 0;
        String query = "UPDATE [dbo].[Sanpham]\n"
                + "SET trangthai = ?\n"
                + "WHERE MaSP = ?";

        try ( Connection cnn = DBConnection.getConnection();  PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, sp.getTrangthai());
            ps.setObject(2, sp.getMaSP()); // Set the value for WHERE clause

            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
