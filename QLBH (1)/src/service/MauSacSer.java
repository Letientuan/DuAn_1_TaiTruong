/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.MauSac;
import java.util.List;

/**
 *
 * @author admin
 */
public interface MauSacSer {
    List<MauSac> getAll();

//    MauSac getOneMS(String ten);

    String add(MauSac ms);

    String update(MauSac ms, int ma);

    String delete(int ma);
}
