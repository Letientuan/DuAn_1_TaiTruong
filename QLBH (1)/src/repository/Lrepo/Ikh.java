/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.Lrepo;

import entity.KhachHang;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface Ikh {
     public List<KhachHang> findAll();
     public boolean addDL(KhachHang kh);
     public boolean update(String ma, KhachHang kh);
     public int deleteById(String Ma);
     public List<KhachHang> phantrang(Integer phantu);
     public List<KhachHang> timkiemphantrang(String mA, int phantu, int tt) ;
}
