/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.DongSanPham;
import entity.NhaSanXuat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class LoaiRepo {

    public List<DongSanPham> getAll() {
        String query = """
                       select MaDongSp,Ten from DongSP
                       """;
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<DongSanPham> listSp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSp.add(new DongSanPham(rs.getInt(1), rs.getString(2)));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

   
        
       public String getOnedsp(String ten) {
        
           String query = """
                      SELECT MaDongSp FROM DongSP where Ten=?
                      """;
        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                return id;
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
        
    
    public boolean addSanPham(DongSanPham kt) {
        int check = 0;
        String query = "INSERT INTO [dbo].[DongSP]"
                + "           ([Ten])"
                + "     VALUES(?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenDongSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateSanPham(DongSanPham kt, int ma) {
        int check = 0;
        String query = "UPDATE [dbo].[DongSP]"
                + "   SET [Ten] = ?"
                + " WHERE =?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenDongSP());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteSanPham(String ma) {
        int check = 0;
        String query = "DELETE FROM [dbo].[DongSP]"
                + "      WHERE MaDongSp = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
public static void main(String[] args) {
        List<DongSanPham> list = new LoaiRepo().getAll();
        for (DongSanPham kh : list) {
            System.out.println(kh.toString());
       }
   }
// public static void main(String[] args) {
//        DongSanPham gv = new DongSanPham(1,"Bennel");
//        boolean testdelete = new LoaiRepo().addSanPham(gv);
//        System.out.println(testdelete);
//    }
}
