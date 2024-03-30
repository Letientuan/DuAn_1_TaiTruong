/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.NhaSanXuat;
import java.util.List;

/**
 *
 * @author admin
 */
public interface NSXSer {
    List<NhaSanXuat> getAll();

    NhaSanXuat getOneNSX(String ten);

    String add(NhaSanXuat nsx);

    String update(NhaSanXuat nsx, int ma);

    String delete(String ten);
}
