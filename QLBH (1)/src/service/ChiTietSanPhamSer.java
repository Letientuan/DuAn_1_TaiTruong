/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.ChatLieu;
import entity.SanPham;
import entity.DongSanPham;
//import entity.KichThuoc;
import entity.MauSac;
import entity.NhaSanXuat;
import entity.SanPham;
import java.util.List;

/**
 *
 * @author admin
 */
public interface ChiTietSanPhamSer {

    List<SanPham> getAll();

    List<MauSac> getAllMs();
    
    List<SanPham> getSP();

    List<ChatLieu> getAllCL();

    List<NhaSanXuat> getAllNSX();

    List<DongSanPham> getAllDSP();

//    List<KichThuoc> getAllKT();

    SanPham getOne(String ma);

    void add(SanPham ctsp);

    String update(SanPham ctsp, String ma);

    String delete(String ma);

    SanPham getOneTen(String ten);

    void updateSoLuong(SanPham ctsp, String ma);
}
