/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

//import entity.ChucVu;
import entity.NhanVien;
import java.util.List;


/**
 *
 * @author Admin
 */
public interface NhanVienService {
    List<NhanVien> getAllNhanVien();
//    List<ChucVu> getALLCV();
   
    String them(NhanVien nv);
    
    void sua(NhanVien nv, String ma);
    
    void xoa(NhanVien nv, String ma);

    
}
