/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import entity.SanPham;
import java.util.ArrayList;
import java.util.List;
import lsevice.isanphamsv;
import entity.SanPhammd;
import repository.sanphamRp;
import service.SanPhamSer;

/**
 *
 * @author admin
 */

public class SanPhamSerImpl implements isanphamsv{
    private sanphamRp sp = new sanphamRp();
    

    @Override
    public List<SanPhammd> getall() {
        return sp.findAll();
    }

    @Override
    public void add(SanPham sph) {
         sp.add(sph);
    }

    @Override
    public boolean Delete(String spp) {
         sp.delete(spp);
         return true;
    }
    @Override
    public boolean update(SanPham spp){
        sp.update(spp);
        return true;
    }
     @Override
       public List<SanPhammd> Timkiem(String mA,int tt){
         return  sp.Timkiem(mA,tt);
       }
  

    

   
    
   
}
