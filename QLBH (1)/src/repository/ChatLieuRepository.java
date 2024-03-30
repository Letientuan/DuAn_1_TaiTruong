/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Utilities.DBConnection;
import entity.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ChatLieuRepository {

    public List<ChatLieu> getAll() {
        String query = """
                       select machatlieu,ten from chatlieu
                       """;
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            List<ChatLieu> listSp = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSp.add(new ChatLieu(rs.getInt(1), rs.getString(2)));
            }
            return listSp;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

   public String getOneCL(String ten) {
        
           String query = """
                      SELECT [Id] 
                        FROM [dbo].[Loai_phong]
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
        
    

    public boolean addChatLieu(ChatLieu cl) {
        int check = 0;
        String query = "INSERT INTO [dbo].[ChatLieu]"
                + "           ([Ten])"
                + "     VALUES (?)";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, cl.getTenCL());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean updateChatLieu(ChatLieu cl, int ma) {
        int check = 0;
        String query = "UPDATE [dbo].[ChatLieu]"
                + "   SET [Ten] = ?"
                + " WHERE Ten =?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, cl.getMaCL());
            ps.setObject(2, cl.getTenCL());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public boolean deleteChatLieu(String ma) {
        int check = 0;
        String query = "DELETE FROM [dbo].[ChatLieu]"
                + "      WHERE MaChatLieu = ?";
        try (Connection cnn = DBConnection.getConnection(); PreparedStatement ps = cnn.prepareStatement(query)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

}
