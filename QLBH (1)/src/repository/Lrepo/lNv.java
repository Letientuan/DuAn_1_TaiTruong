/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.Lrepo;

import entity.NhanVien;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author AN
 */
public interface lNv {
     public List<NhanVien> getAllNhanVien();
    public boolean insert(NhanVien nv);
    public boolean update(String ma ,NhanVien nv)throws Exception;
    public int delete(String ma);
    public List<NhanVien> phantrang(Integer phantu);
    public List<NhanVien> timkiemphantrang(String mA,int phantu , int tt);
    public ArrayList<NhanVien> getTK() throws Exception;
}
