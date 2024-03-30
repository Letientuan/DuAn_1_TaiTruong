/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.Lrepo;


import entity.SanPham;
import java.util.List;
import entity.SanPhammd;

/**
 *
 * @author ADMIN
 */
public interface isanphamrp {
     public List<SanPhammd> findAll();
     public void add(SanPham sp);
    
      public boolean delete(String sp);
     public boolean update(SanPham sp);
     public List<SanPhammd> Timkiem(String mA,int tt);
}
