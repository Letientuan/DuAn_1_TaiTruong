/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
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
public class NSXRepository {

    public List<NhaSanXuat> getAll() {
        String query = """
                       select MaNsx,ten from NSX
                       """;
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<NhaSanXuat> listSp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSp.add(new NhaSanXuat(rs.getInt(1), rs.getString(2)));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

      
        
        public String getOnensx(String ten) {
        
           String query = """
                      SELECT [MaNsx] 
                        FROM [dbo].[NSX]
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
      
    

    public boolean addSanPham(NhaSanXuat kt) {
        int check = 0;
        String query = "INSERT INTO [dbo].[NSX]"
                + "           ([Ten])"
                + "     VALUES(?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, kt.getTenNhaSanXuat());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
//
//    public boolean updateSanPham(NhaSanXuat kt, int ma) {
//        int check = 0;
//        String query = "UPDATE [dbo].[NSX]"
//                + "   SET [Ten] = ?"
//                + " WHERE Ma =?";
//        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
//            ps.setObject(1, kt.getNhaSanXuat());
//            ps.setObject(2, kt.getTenNhaSanXuat());
//            ps.setObject(3, ma);
//            check = ps.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace(System.out);
//        }
//        return check > 0;
//    }
//
    public boolean deleteSanPham(String ten) {
        int check = 0;
        String query = "DELETE FROM [dbo].[NSX]"
                + "      WHERE MaNsx = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ten);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }
//
}
    
