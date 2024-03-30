///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package ServiceImpl;
//
//import entity.ChatLieu;
//import entity.SanPham;
//import entity.DongSanPham;
//import entity.KichThuoc;
//import entity.MauSac;
//import entity.NhaSanXuat;
//import entity.SanPham;
//import java.util.ArrayList;
//import java.util.List;
//import repository.sanphamRp;
//import service.ChiTietSanPhamSer;
//
///**
// *
// * @author admin
// */
//public class ChiTietSanPhamSerImpl implements ChiTietSanPhamSer {
//
//    private sanphamRp ChiTietSPRes = new sanphamRp();
//
//    @Override
//    public List<SanPham> getAll() {
//        
//        return ChiTietSPRes.getAll();
//    }
//
//    @Override
//    public SanPham getOne(String ma) {
//        return ChiTietSPRes.getOne(ma);
//    }
//
//    @Override
//    public void add(SanPham ctsp) {
//        ChiTietSPRes.add(ctsp);
//    }
//
//    @Override
//    public String update(SanPham ctsp, String ma) {
//        if (ChiTietSPRes.update(ctsp, ma)) {
//            return "Update thành công";
//        }
//        return "Update thất bại";
//    }
//
//    @Override
//    public String delete(String ma) {
//        if (ChiTietSPRes.delete(ma)) {
//            return "Delete thành công";
//        }
//        return "Delete thất bại";
//    }
//
//    @Override
//    public SanPham getOneTen(String ten) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public void updateSoLuong(SanPham ctsp, String ma) {
//        ChiTietSPRes.updateSoLuong(ctsp, ma);
//    }
//
//    @Override
//    public List<MauSac> getAllMs() {
//        return ChiTietSPRes.getMS();
//    }
//
//    @Override
//    public List<ChatLieu> getAllCL() {
//        return ChiTietSPRes.getCL();
//    }
//
//    @Override
//    public List<NhaSanXuat> getAllNSX() {
//        return ChiTietSPRes.getNSX();
//    }
//
//    @Override
//    public List<DongSanPham> getAllDSP() {
//        return ChiTietSPRes.getDSP();
//    }
//
//    @Override
//    public List<KichThuoc> getAllKT() {
//        return ChiTietSPRes.getSize();
//    }
//
//    @Override
//    public List<SanPham> getSP() {
//        return ChiTietSPRes.getSP();
//    }
//}
