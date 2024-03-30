/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.NhanVien;
import java.util.ArrayList;
import java.util.List;
import lsevice.Invsv;
import repository.NhanVienRepository;

/**
 *
 * @author AN
 */
public class NhanVienSV implements Invsv{
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public List<NhanVien> getAllNhanVien() {
        return nhanVienRepository.getAllNhanVien();
    }

    @Override
    public boolean insert(NhanVien nv) {
        return nhanVienRepository.insert(nv);
    }

    @Override
    public boolean update(String ma, NhanVien nv) throws Exception{
        return nhanVienRepository.update(ma, nv);
    }

    @Override
    public int delete(String ma) {
        return nhanVienRepository.delete(ma);
    }

    @Override
    public List<NhanVien> phantrang(Integer phantu) {
        return nhanVienRepository.phantrang(phantu);
    }

    @Override
    public List<NhanVien> timkiemphantrang(String mA, int phantu, int tt) {
        return nhanVienRepository.timkiemphantrang(mA, phantu, tt);
    }

    @Override
    public ArrayList<NhanVien> getTK() throws Exception {
        return nhanVienRepository.getTK();
    }
    
    
}
