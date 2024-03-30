/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class MauRepository {

    public List<MauSac> getAll() {
        String query = """
                       select mamau,ten from mausac
                       """;
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<MauSac> listmau = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listmau.add(new MauSac(rs.getInt(1), rs.getString(2)));
            }
            return listmau;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    
        
      public String getOnemau(String ten) {
        
           String query = """
                      SELECT [mamau] 
                        FROM [dbo].[mausac]
                      	where [Ten]  = ?
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
      

    public boolean addSanPham(MauSac kt) {
        int check = 0;
        String query = "INSERT INTO [dbo].[MauSac]"
                + "           ([Ten])"
                + "     VALUES(?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenMS());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateSanPham(MauSac kt, int ma) {
        int check = 0;
        String query = "UPDATE [dbo].[MauSac]"
                + "   SET [Ten] = ?"
                + " WHERE Ma =?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenMS());
            ps.setObject(2, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteSanPham(int ma) {
        int check = 0;
        String query = "DELETE FROM [dbo].[MauSac]"
                + "      WHERE MaMau = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
 public static void main(String[] args) {
        MauSac gv = new MauSac(1,"Bennel");
        boolean testdelete = new MauRepository().addSanPham(gv);
        System.out.println(testdelete);
    }
}
