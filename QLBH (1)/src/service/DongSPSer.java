/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.DongSanPham;
import java.util.List;

/**
 *
 * @author admin
 */
public interface DongSPSer {
    List<DongSanPham> getAll();

//    DongSanPham getOneDSP(String ten);

    String add(DongSanPham dsp);

    String update(DongSanPham dsp, int ma);

    String delete(String ma);
}
