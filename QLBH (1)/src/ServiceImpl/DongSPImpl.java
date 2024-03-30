/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import entity.DongSanPham;
import java.util.ArrayList;
import java.util.List;
import repository.LoaiRepo;
import service.DongSPSer;

/**
 *
 * @author admin
 */
public class DongSPImpl implements DongSPSer {

    private LoaiRepo spRep = new LoaiRepo();

    @Override
    public List<DongSanPham> getAll() {
        List<DongSanPham> listAll = new ArrayList<>();
        return listAll = spRep.getAll();
    }

//    @Override
//    public DongSanPham getOneDSP(String ten) {
//        return spRep.getOneDSP(ten);
//    }

    @Override
    public String add(DongSanPham dsp) {
        if (spRep.addSanPham(dsp)) {
            return "Add thành công";
        }
        return "Add thất bại";
    }

    @Override
    public String update(DongSanPham dsp, int ma) {
        if (spRep.updateSanPham(dsp, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String delete(String ma) {
        if (spRep.deleteSanPham(ma)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }
}

