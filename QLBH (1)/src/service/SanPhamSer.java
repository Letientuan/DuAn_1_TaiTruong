/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.SanPham;
import java.util.List;

/**
 *
 * @author admin
 */
public interface SanPhamSer {
    List<SanPham> getAll();

    SanPham getOne(String ma);

    String add(SanPham sp);

    String update(SanPham sp, String ma);

    String Delete(String ma);
    public boolean update(SanPham spp);
}
