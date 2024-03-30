/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import ServiceImpl.SanPhamSerImpl;
import entity.HoaDon;
import entity.HoaDonChiTiet;
import entity.NhanVien;

import entity.SanPhammd;
import java.awt.Component;
import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import repository.BanHangrp;
import java.awt.Image;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
/**
 *
 * @author thong
 */
public class BanHang extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    BanHangrp list = new BanHangrp();
 
    private SanPhamSerImpl spsv = new SanPhamSerImpl();
    private List<SanPhammd> listsp = spsv.getall();
    private List<HoaDonChiTiet> listhdct = list.getListHDCTTT();
    private List<NhanVien> listnv = list.getAllNhanVien();
    private List<HoaDon> listhd = list.getListHD();
    private List<entity.KhachHang> listkh = list.findAll();
    private view.Login login;
    NhanVien nv = new NhanVien();
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    DefaultTableModel model = new DefaultTableModel();
    private DefaultComboBoxModel boxModel = new DefaultComboBoxModel();
    long mahd;
    toancuc tc = new toancuc();
    String tennv;
   
   
    public BanHang() {

        initComponents();
        tk();

        trangthaihd();
        loadComboboxkh();
        String nhanvien = toancuc.getTen();
        for (NhanVien nv : listnv) {
            if (nv.getTaiKhoan().equals(nhanvien)) {

                tennv = nv.getTenNV();
                jpn_manv.setText(tennv);
            }
        }
    }

   private void loadTable(List<SanPhammd> list) {
         int rowHeight = 50; // Chiều cao mong muốn
            tbl_sp.setRowHeight(rowHeight);
        DefaultTableModel model = (DefaultTableModel) tbl_sp.getModel();
        model.setColumnCount(0);

        model.addColumn("Masp");
        model.addColumn("tên sp");
        model.addColumn("màu sắc");
        model.addColumn("chất liệu");
        model.addColumn("size");
        model.addColumn("giá bán");
        model.addColumn("số lượng");
        model.addColumn("trạng thái");
        model.addColumn("Hình Ảnh");

        model.setRowCount(0);

        for (SanPhammd sanPhammd : list) {
            Object[] row = new Object[]{
                    sanPhammd.getMaSP(),
                    sanPhammd.getTenSP(),
                    sanPhammd.getTenmauSac(),
                    sanPhammd.getTenchatLieu(),
                    sanPhammd.getSize(),
                    sanPhammd.getGiaBan(),
                    sanPhammd.getSoLuongTon(),
                    sanPhammd.gettrangthaisp(0),
                    createImageIcon(sanPhammd.getAnhSp()) // Tạo ImageIcon từ đường dẫn file ảnh
            };

            model.addRow(row);
        }

        tbl_sp.getColumnModel().getColumn(8).setCellRenderer(new ImageRenderer());

        listsp = list;
    }

    private class ImageRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setIcon((ImageIcon) value);
            return this;
        }
    }

    private ImageIcon createImageIcon(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            // Tạo ImageIcon từ đường dẫn file ảnh và thay đổi kích thước nếu cần
            ImageIcon icon = new ImageIcon(imagePath);
            Image image = icon.getImage();
            Image newImage = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
            return new ImageIcon(newImage);
        } else {
            // Trả về null nếu đường dẫn ảnh là rỗng
            return null;
        }
    }

   
    private ImageIcon createImageIcon(byte[] imageData) {
        if (imageData != null && imageData.length > 0) {
            return new ImageIcon(imageData);
        } else {
            return null;
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "số lượng phải là Số nguyên");
            return false;
        }
    }
//  private double convertedToNumbers(String s) {
//        String number = "";
//        String[] array = s.replace(".", " ").split("\\s");
//        for (String i : array) {
//            number = number.concat(i);
//        }
//        return Double.parseDouble(number);
//    }

    private void add() {
        try {

        } catch (Exception e) {
            // Step 6: Handle exceptions
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi cập nhật chi tiết sản phẩm!");
        }
    }

    private void loadComboboxkh() {
        boxModel = (DefaultComboBoxModel) cbo_kh.getModel();
        listkh = list.findAll();
        cbo_kh.removeAllItems();
        listkh.forEach(s -> boxModel.addElement(s.getTenKH()));
    }

    private void loadHDCTTT(List<HoaDonChiTiet> listhd) {
        // Step 1: Retrieve a list of HoaDonChiTiet based on the provided invoice ID
        DefaultTableModel model = (DefaultTableModel) tbl_giohang.getModel();
        model.setColumnCount(0);
        model.addColumn("Masp");
        model.addColumn("tên sp");
        model.addColumn("đơn giá");
        model.addColumn("số lượng");
        model.setRowCount(0);
        for (HoaDonChiTiet hd : listhd) {
            Object[] row = new Object[]{
                hd.getMasanPham(),
                hd.getTenSP(),
                hd.getDonGia(),
                hd.getSoLuong(),};

            model.addRow(row);

        }
        listhdct = listhd;

    }

    public void trangthaihd() {
        List<HoaDon> listhdd = list.getListHDByTinhTrang(0);

        if (listhdd != null) {
            loadHD(listhdd);

        } else {
            System.out.println("No matching records found."); // Adjust this message as needed
            // You might want to display a message to the user or handle the situation accordingly
        }
    }

    public void timkiem() {
        try {
            int maHoaDon = Integer.parseInt(jpn_mahoadon.getText());

            // Assuming that list is an instance of some class that contains the method getListHDCt
            List<HoaDonChiTiet> listhdd = list.getListHDCt(maHoaDon);

            // Assuming that loadHDCTTT is a method to load/display the details of the listhdd
            loadHDCTTT(listhdd);
            double gia = 0;
            for (HoaDonChiTiet a : listhdd) {
                gia += a.getDonGia();
            }

//            double tongtien1 = list.getTongTien(Long.valueOf(jpn_mahoadon.getText()));
//            DecimalFormat decimalFormat = new DecimalFormat("#,##0.000000");
//            String numberAsString = decimalFormat.format(tongtien1);          
            double total_amount = list.getTongTien(Long.valueOf(jpn_mahoadon.getText()));

            jtongtien.setText(new BigDecimal(total_amount).toPlainString());

//            gia = list.getTongTien(Long.valueOf(maHoaDon));
//            jtongtien.setText(gia + "");
            jtienthanhtoan.setText(new BigDecimal(total_amount).toPlainString());

        } catch (NumberFormatException e) {
            // Handle the case where the input in jpn_mahoadon is not a valid integer
            System.err.println("Invalid input for maHoaDon. Please enter a valid integer.");
        } catch (Exception e) {
            // Handle any other exceptions that might occur during the process
            e.printStackTrace(); // print the exception stack trace (you might want to handle this more gracefully)
        }
    }

public void xuatex(){
    
}
    
    private void loadHD(List<HoaDon> listhoadon) {
        // Step 1: Retrieve a list of HoaDonChiTiet based on the provided invoice ID
        DefaultTableModel model = (DefaultTableModel) tbl_hoadon.getModel();
        model.setColumnCount(0);
        model.addColumn("Mã hóa Đơn");
        model.addColumn("Ngày Tạo");
        model.addColumn("Nhân Viên");
        model.addColumn("Khách Hàng");
        model.addColumn("Tình Trạng");
        model.setRowCount(0);
        for (HoaDon hd : listhoadon) {
            Object[] row = new Object[]{
                hd.getMaHD(),
                hd.getNgayTao(),
                hd.getManhanVien(),
                hd.getMakhachHang(),
                hd.gettrangthaiHD(hd.getTinhTrang())
            };
            model.addRow(row);
        }
        listhd = listhoadon;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_giohang = new javax.swing.JTable();
        xoa_ALl = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_tiemkiem = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_sp = new javax.swing.JTable();
        cbo_size = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jpn_mahoadon = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbo_phuongthuc = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_tienkhacdua = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txt_ghichu = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        btn_huyhoadon = new javax.swing.JButton();
        btn_xuathoadon = new javax.swing.JButton();
        jtienthanhtoan = new javax.swing.JLabel();
        jtongtien = new javax.swing.JLabel();
        cbo_kh = new javax.swing.JComboBox<>();
        jpn_manv = new javax.swing.JLabel();
        jtienthua = new javax.swing.JLabel();
        btn_themKH = new javax.swing.JButton();
        btn_Taohoadon = new javax.swing.JButton();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Ngày tạo", "Nhân viên", "Khách hàng"
            }
        ));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoadon);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_giohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã sản phầm", "Tên sản phẩm", "Đơn giá", "Số lượng"
            }
        ));
        tbl_giohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_giohangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_giohang);

        xoa_ALl.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        xoa_ALl.setText("Xóa Tất Cả");
        xoa_ALl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoa_ALlActionPerformed(evt);
            }
        });

        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(xoa_ALl)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_xoa)
                        .addGap(32, 32, 32))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(xoa_ALl, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Tìm kiếm:");

        txt_tiemkiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tiemkiemCaretUpdate(evt);
            }
        });
        txt_tiemkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tiemkiemActionPerformed(evt);
            }
        });

        tbl_sp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Màu sắc", "Chất liệu", "Size", "Đơn giá", "Số lượng tồn"
            }
        ));
        tbl_sp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_spMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_sp);

        cbo_size.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "S", "M", "L", "XL", "XXL", "XXXL" }));
        cbo_size.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_sizeItemStateChanged(evt);
            }
        });
        cbo_size.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_sizeActionPerformed(evt);
            }
        });

        jLabel5.setText("Size :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_tiemkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(cbo_size, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_tiemkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_size, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)), "Thanh Toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setText("Tên Nhân Viên:");

        jLabel3.setText("Tên khách hàng:");

        jLabel4.setText("Mã hóa đơn:");

        jpn_mahoadon.setText("...");

        jLabel6.setText("Tổng tiền:");

        jLabel7.setText("Thanh toán:");

        jLabel8.setText("Tiền khách đưa:");

        jLabel9.setText("Tiền thừa:");

        jLabel10.setText("Hình thức thanh toán:");

        cbo_phuongthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền Mặt", "Chuyển Khoản" }));

        jLabel11.setText("VND");

        jLabel12.setText("VND");

        jLabel13.setText("VND");

        txt_tienkhacdua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tienkhacduaCaretUpdate(evt);
            }
        });

        jLabel14.setText("Ghi chú:");

        txt_ghichu.setColumns(20);
        txt_ghichu.setRows(5);
        jScrollPane4.setViewportView(txt_ghichu);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("Làm mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_huyhoadon.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_huyhoadon.setText("Hủy hóa đơn");
        btn_huyhoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyhoadonActionPerformed(evt);
            }
        });

        btn_xuathoadon.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_xuathoadon.setText("Xuất Hóa Đơn");
        btn_xuathoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xuathoadonActionPerformed(evt);
            }
        });

        jtienthanhtoan.setForeground(new java.awt.Color(255, 0, 51));
        jtienthanhtoan.setText("0");

        jtongtien.setForeground(new java.awt.Color(255, 0, 51));
        jtongtien.setText("0");

        cbo_kh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_kh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_khActionPerformed(evt);
            }
        });

        jpn_manv.setText("...");

        jtienthua.setForeground(new java.awt.Color(255, 0, 51));
        jtienthua.setText("0");

        btn_themKH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_themKH.setText("+");
        btn_themKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbo_phuongthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButton1)
                                .addGap(76, 76, 76)
                                .addComponent(btn_huyhoadon))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel2))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(17, 17, 17)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jtongtien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                                    .addGap(11, 11, 11)
                                                                    .addComponent(txt_tienkhacdua, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(jpn_mahoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jtienthanhtoan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                            .addGap(0, 0, Short.MAX_VALUE)))
                                                    .addGap(18, 18, 18))
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(jtienthua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addGap(5, 5, 5)))
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jpn_manv, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel5Layout.createSequentialGroup()
                                                    .addComponent(cbo_kh, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(btn_themKH))))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(56, 56, 56)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(59, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(btn_xuathoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jpn_manv))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbo_kh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_themKH))
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jpn_mahoadon))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jtongtien))
                .addGap(46, 46, 46)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel12)
                    .addComponent(jtienthanhtoan))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_tienkhacdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)
                    .addComponent(jtienthua))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbo_phuongthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btn_huyhoadon))
                .addGap(18, 18, 18)
                .addComponent(btn_xuathoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        btn_Taohoadon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_Taohoadon.setText("Tạo Hóa Đơn");
        btn_Taohoadon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TaohoadonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_Taohoadon)
                        .addGap(11, 11, 11))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(btn_Taohoadon, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );

        jPanel2.getAccessibleContext().setAccessibleName("Hóa Đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
int vitri;
    int vitri1;

    public void tk() {
        List<SanPhammd> output = spsv.getall();
        loadTable(output);
    }

    public void update(SanPhammd hd) {

        list.updateSoLuong(hd);
        tk();
    }
    private void tbl_spMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_spMouseClicked
        try {

            int selectedRow = tbl_sp.getSelectedRow();
            if (selectedRow == -1) {
                // No row selected, handle accordingly
                return;
            }

            SanPhammd sp = listsp.get(selectedRow);
            String masp = sp.getMaSP();
            String tesp = sp.getTenSP();
            Double dongia = sp.getGiaBan();
            int sl = sp.getSoLuongTon();
            int trant = sp.getTrangthai();

            String soLuongInput = JOptionPane.showInputDialog("Nhập số lượng sản phẩm:");
            if (soLuongInput == null) {
                return;
            } else {
                if (isNumeric(soLuongInput)) {
                    int soLuong1 = Integer.parseInt(soLuongInput);

                    if (soLuong1 > 0 && soLuong1 <= 500) {
                        HoaDonChiTiet hd = new HoaDonChiTiet();
                        hd.setMasanPham(masp);
                        hd.setTenSP(tesp);
                        hd.setDonGia(dongia);
                        hd.setSoLuong(soLuong1);
                        hd.setMahd((int) mahd);

                        int slconlai = sl - soLuong1;

                        try {
                            if (trant == 2) {
                                JOptionPane.showMessageDialog(this, "sản Phẩm đã ngừng kinh doanh");
                            } else {

                                Long check = Long.parseLong(jpn_mahoadon.getText());
                                if (slconlai >= 0) {
                                    boolean found = false;

                                    for (HoaDonChiTiet a : listhdct) {
                                        if (a.getMasanPham().equals(masp)) {
                                            long b = a.getMaHDCT();
                                            a.setMaHDCT(b);
                                            a.setSoLuong(soLuong1);

                                            list.updateHoaDonChiTietma(a);
                                            found = true;
                                            break;
                                        }
                                    }
                                    timkiem();

                                    if (!found) {
                                        list.addHDCTTT(hd);
                                        timkiem();
                                    }
                                    SanPhammd spup = new SanPhammd();
                                    spup.setMaSP(masp);
                                    spup.setSoLuongTon(slconlai);
                                    spup.setTrangthai(slconlai == 0 ? 0 : 1);

                                    update(spup);
                                    tk();
                                } else {
                                    JOptionPane.showMessageDialog(this, "sản phẩm đã hết hàng(hoặc quá số lượng sp)");
                                    return;
                                }
                            }

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Vui lòng Tạo Hóa Đơn Hoặc Chọn hóa Đơn");
                        }
                    } else if (soLuong1 < 0) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng lớn hơn 0 ");
                        return;
                    } else if (soLuong1 > 500) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số lượng Không quá  500");
                        return;

                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi xử lý sự kiện chuột!");
        }
    }//GEN-LAST:event_tbl_spMouseClicked

    private void btn_xuathoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xuathoadonActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xuất hóa đơn không ?") == JOptionPane.YES_OPTION) {
            try {
                HoaDon hd = new HoaDon();
                hd.setTongTien(Double.parseDouble(jtongtien.getText()));
                hd.setTongtienthanhtoan(Double.parseDouble(jtienthanhtoan.getText()));
                hd.setNgayThanhToan(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
                hd.setGhichu(txt_ghichu.getText());
                hd.setPhuongThucThanhToan(cbo_phuongthuc.getSelectedItem().toString());
                hd.setTinhTrang(1);
                hd.setMaHD(Long.valueOf(jpn_mahoadon.getText()));
                list.editHDTT(hd);
                JOptionPane.showMessageDialog(this, "xuất hóa đơn Thành công");
                trangthaihd();
                jpn_mahoadon.setText(0 + "");
                timkiem();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return;
        }


    }//GEN-LAST:event_btn_xuathoadonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        txt_tienkhacdua.setText("");
        txt_ghichu.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_TaohoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TaohoadonActionPerformed

        HoaDon hd = new HoaDon();
        hd.setNgayTao(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())));
        String khach = cbo_kh.getSelectedItem().toString();
        for (entity.KhachHang d : listkh) {
            if (d.getTenKH().equalsIgnoreCase(khach)) {
                String dong1 = d.getMaKH();
                hd.setMakhachHang(dong1);
            }
        }
        hd.setTinhTrang(0);
        String nhanvien = tc.getTen();

        for (NhanVien nv : listnv) {
            if (nv.getTaiKhoan().equals(nhanvien)) {
                String manv = nv.getMaNV();
                tennv = nv.getTenNV();
                hd.setManhanVien(manv);
            }
        }
        try {
            list.addHDTT(hd);
        } catch (SQLException ex) {
            Logger.getLogger(BanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
        trangthaihd();
    }//GEN-LAST:event_btn_TaohoadonActionPerformed
    double tongtien;
    int mahdct;
    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        vitri = tbl_hoadon.getSelectedRow();
        HoaDon hd = listhd.get(vitri);
        mahd = hd.getMaHD();
        jpn_mahoadon.setText(String.valueOf(mahd));
        tongtien = list.getTongTien(mahd);
        jtongtien.setText(String.valueOf(tongtien));

        List<String> liststrdong = new ArrayList<>();
        listkh.forEach(s -> liststrdong.add(s.getTenKH()));
        int indext2 = liststrdong.indexOf(hd.getMakhachHang());
        cbo_kh.setSelectedIndex(indext2);
        timkiem();
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void cbo_khActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_khActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_khActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed

        try {
            String soLuongInput = JOptionPane.showInputDialog("Nhập số lượng sản phẩm muốn xóa" + " ");
            if (soLuongInput == null) {
                return;
            } else {
                if (isNumeric(soLuongInput)) {
//           
                    Integer slcn = Integer.parseInt(soLuongInput);
//            spup.setMaSP(maspkhixoa);

                    int sltong = slcapnhap - slcn;
                    if (sltong > 0) {

                        HoaDonChiTiet hdct = new HoaDonChiTiet();
                        hdct.setSoLuong(sltong);
                        hdct.setMaHDCT(ma);
                        list.updateHoaDonChiTiet(hdct);
                        timkiem();
                    } else if (sltong == 0) {
                        deletemahdct((int) ma);
                    } else {
                        JOptionPane.showMessageDialog(this, "bạn nhập quá số lượng trong giỏ hàng");
                    }
                    SanPhammd spup = new SanPhammd();
                    int tt = slcapnhap;
                    int trangthai;
                    if (tt == 0) {
                        trangthai = 1;
                        spup.setTrangthai(trangthai);
                    } else {
                        trangthai = 1;
                        spup.setTrangthai(trangthai);
                    }
                    spup.setSoLuongTon(slcn);
                    spup.setMaSP(maspkhixoa);
                    list.updateSoLuongkhixoa(spup);
                    tk();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn sản Phẩm trên giỏ hàng");
        }
    }//GEN-LAST:event_btn_xoaActionPerformed
    int indext111;
    long ma;
    String maspkhixoa;
    int slcapnhap;
    private void tbl_giohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_giohangMouseClicked
        indext111 = tbl_giohang.getSelectedRow();
        HoaDonChiTiet hdct = listhdct.get(indext111);
        ma = hdct.getMaHDCT();
        maspkhixoa = hdct.getMasanPham();
        slcapnhap = hdct.getSoLuong();


    }//GEN-LAST:event_tbl_giohangMouseClicked

    private void xoa_ALlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoa_ALlActionPerformed
        try {
            for (HoaDonChiTiet lss : listhdct) {
                for (SanPhammd sanPhammd : listsp) {
                    if (lss.getMasanPham().equals(sanPhammd.getMaSP())) {
                        deletemahd(Integer.parseInt(jpn_mahoadon.getText()));
                        int sl = lss.getSoLuong();
                        sanPhammd.setSoLuongTon(sl);
                        sanPhammd.setMaSP(lss.getMasanPham());
                        sanPhammd.setTrangthai(1);
                        list.updateSoLuongkhixoa(sanPhammd);
                        timkiem();
                        tk();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "vui lòng trọn hóa đơn");
        }
    }//GEN-LAST:event_xoa_ALlActionPerformed

    private void txt_tienkhacduaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tienkhacduaCaretUpdate
        double tienthua = Double.parseDouble(txt_tienkhacdua.getText()) - Double.parseDouble(jtongtien.getText());

        jtienthua.setText(new BigDecimal(tienthua).toPlainString());
    }//GEN-LAST:event_txt_tienkhacduaCaretUpdate

    private void btn_huyhoadonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyhoadonActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn hủy hóa đơn không ?") == JOptionPane.YES_OPTION) {
            try {
                HoaDon hd = new HoaDon();
                hd.setMaHD(Long.valueOf(jpn_mahoadon.getText()));
                hd.setTinhTrang(2);
                list.editHDTTTrangThai(hd);
                trangthaihd();
                for (HoaDonChiTiet lss : listhdct) {
                    for (SanPhammd sanPhammd : listsp) {
                        if (lss.getMasanPham().equals(sanPhammd.getMaSP())) {

                            int sl = lss.getSoLuong();
                            sanPhammd.setSoLuongTon(sl);
                            sanPhammd.setMaSP(lss.getMasanPham());

                            list.updateSoLuongkhixoa(sanPhammd);
                            jpn_mahoadon.setText(0 + "");
                            timkiem();
                            tk();
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "hóa Đơn đã Hủy");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "lỗi hủy");
            }
        }
    }//GEN-LAST:event_btn_huyhoadonActionPerformed
    private int tt = -1;

    public void timkiemsp() {
        String ma = txt_tiemkiem.getText();
        List<SanPhammd> list1 = spsv.Timkiem(ma, tt);
        loadTable(list1);
    }
    private void txt_tiemkiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tiemkiemCaretUpdate
        List<SanPhammd> list1 = list.Timkiemsp(txt_tiemkiem.getText(), size);
        loadTable(list1);
    }//GEN-LAST:event_txt_tiemkiemCaretUpdate
    private boolean isKH = false;
    private static formKH form = null;
    private void btn_themKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themKHActionPerformed
        if (!isKH) {
            form = new formKH();
            form.setVisible(true);
            form.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                    // Gọi phương thứloadCombobox();
                    loadComboboxkh();
                    isKH = false;
                }
            });
            isKH = true;
        } else {
            form.toFront();
        }

    }//GEN-LAST:event_btn_themKHActionPerformed

    private void txt_tiemkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tiemkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tiemkiemActionPerformed
    String size = "Size";
    private void cbo_sizeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_sizeItemStateChanged
        String a = cbo_size.getSelectedItem().toString();
        switch (a) {
            case "S":
                size = "S";
                break;
            case "M":
                size = "M";
                break;

            case "L":
                size = "L";
                break;
            case "XL":
                size = "XL";
                break;
            case "XXL":
                size = "XXL";
                break;
            case "XXXL":
                size = "XXXL";
                break;
            default:
                size = "Size";
                break;
        }

        List<SanPhammd> list1 = list.Timkiemsp(txt_tiemkiem.getText(), size);
        loadTable(list1);

    }//GEN-LAST:event_cbo_sizeItemStateChanged

    private void cbo_sizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_sizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_sizeActionPerformed
    public void deletemahdct(int ma) {
        list.deleteHoaDonChiTietByMaHoaDonct(ma);
        timkiem();
    }

    public void deletemahd(int mahd) {
        list.deleteHoaDonChiTietByMaHoaDon(mahd);
        timkiem();
    }
    int vitri2;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Taohoadon;
    private javax.swing.JButton btn_huyhoadon;
    private javax.swing.JButton btn_themKH;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JButton btn_xuathoadon;
    private javax.swing.JComboBox<String> cbo_kh;
    private javax.swing.JComboBox<String> cbo_phuongthuc;
    private javax.swing.JComboBox<String> cbo_size;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel jpn_mahoadon;
    private javax.swing.JLabel jpn_manv;
    private javax.swing.JLabel jtienthanhtoan;
    private javax.swing.JLabel jtienthua;
    private javax.swing.JLabel jtongtien;
    private javax.swing.JTable tbl_giohang;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTable tbl_sp;
    private javax.swing.JTextArea txt_ghichu;
    private javax.swing.JTextField txt_tiemkiem;
    private javax.swing.JTextField txt_tienkhacdua;
    private javax.swing.JButton xoa_ALl;
    // End of variables declaration//GEN-END:variables
}
