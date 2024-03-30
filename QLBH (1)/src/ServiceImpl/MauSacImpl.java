/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceImpl;

import entity.MauSac;
import java.util.ArrayList;
import java.util.List;
import repository.MauRepository;
import service.MauSacSer;

/**
 *
 * @author admin
 */
public class MauSacImpl implements MauSacSer {

    private MauRepository spRep = new MauRepository();

    @Override
    public List<MauSac> getAll() {
        List<MauSac> listAll = new ArrayList<>();
        return listAll = spRep.getAll();
    }

//    @Override
//    public MauSac getOneMS(String ten) {
//        return spRep.getOneMau(ten);
//    }

    @Override
    public String add(MauSac ms) {
        if (spRep.addSanPham(ms)) {
            return "Add thành công";
        }
        return "Add thất bại";
    }

    @Override
    public String update(MauSac ms, int ma) {
        if (spRep.updateSanPham(ms, ma)) {
            return "Update thành công";
        }
        return "Update thất bại";
    }

    @Override
    public String delete(int ma) {
        if (spRep.deleteSanPham(ma)) {
            return "Delete thành công";
        }
        return "Delete thất bại";
    }
}
