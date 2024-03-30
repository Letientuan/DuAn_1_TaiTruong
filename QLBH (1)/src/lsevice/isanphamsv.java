/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lsevice;

import entity.KhachHang;
import entity.SanPham;
import java.util.List;
import entity.SanPhammd;

/**
 *
 * @author ADMIN
 */
public interface isanphamsv {
     public List<SanPhammd> getall(); 
     public void add(SanPham sp);
     public boolean Delete(String sp);
     public boolean update(SanPham sp);
     public List<SanPhammd> Timkiem(String mA,int tt);
}
