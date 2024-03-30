/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.khachhangSevice;
import lsevice.Ikhsv;

/**
 *
 * @author thong
 */
public class KhachHang extends javax.swing.JPanel {

    int vitri;
    /**
     * Creates new form KhachHang
     */

    private DefaultTableModel dtm = new DefaultTableModel();
    private khachhangSevice khsv = new khachhangSevice();
    private List<entity.KhachHang> listKhachHangs = khsv.getAll();

    public KhachHang() {
        initComponents();
        String ma = txt_timkiem.getText();
        List<entity.KhachHang> kh = khsv.timkiemphantrang(ma, TienLui, tt);
        loadTable(kh);
    }

    private void loadTable(List<entity.KhachHang> khachHang) {
        DefaultTableModel model = (DefaultTableModel) tbl_khachhang.getModel();
        model.setColumnCount(0);

        model.addColumn("Makh");
        model.addColumn("Tênkh");
        model.addColumn("diachi");
        model.addColumn("Sdt ");
        model.addColumn("email");
        model.addColumn("gioitinh");
        model.addColumn("trangthai");
        model.setRowCount(0);
        for (entity.KhachHang kh : khachHang) {
            Object[] row = new Object[]{
                kh.getMaKH(),
                kh.getTenKH(),
                kh.getDiaChi(),
                kh.getSdt(),
                kh.getEmail(),
                kh.getgioitinh(),
                kh.getTrangThai(1),};
            model.addRow(row);
        }

        listKhachHangs = khachHang;
    }

//    private void getAll() {
//        List<entity.KhachHang> vexemphim = khsv.getAll();
//        loadTable(vexemphim);
//    }
    public void tk(int status) {
        String ma = txt_timkiem.getText();
        List<entity.KhachHang> output = khsv.timkiemphantrang(ma, TienLui, status);
        loadTable(output);
    }

    public void timkiem() {
        String ma = txt_timkiem.getText();
        List<entity.KhachHang> kh = khsv.timkiemphantrang(ma, TienLui, tt);
        loadTable(kh);
    }

    public void them() {

        entity.KhachHang kh = new entity.KhachHang();
        kh.setMaKH(txt_ma.getText());
        kh.setTenKH(txt_ten.getText());
        kh.setDiaChi(txt_diachi.getText());
        kh.setSdt(txt_sdt.getText());
        kh.setEmail(txt_mail.getText());
        Boolean gt = true;

        if (nu.isSelected()) {
            gt = false;
        }

        if (gt == false) {

        }
        kh.setGioiTinh(gt);
        Integer tt = 0;
        if (hoatdong.isSelected()) {
            tt = 1;
        }
        kh.setTrangThai(tt);
        khsv.add(kh);

    }

    public boolean checkmail() {
        if (!isValidEmail(txt_mail.getText())) {
            JOptionPane.showMessageDialog(this, "mail không đúng định dạng");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        // Kiểm tra email không được viết hoa
        if (email.matches(".*[A-Z].*")) {
            return false;
        }

        String lowercaseEmail = email.toLowerCase();
        String regex = "^[a-z0-9._%+-]+(\\.[a-z0-9._%+-]+)*@[a-z0-9.-]+\\.[a-z]{2,}$";
        boolean hasConsecutiveDots = lowercaseEmail.contains("..");
        return lowercaseEmail.matches(regex) && !hasConsecutiveDots;
    }

    public void sua() {
        entity.KhachHang kh = new entity.KhachHang();
        String ma = txt_ma.getText();
        kh.setTenKH(txt_ten.getText());
        kh.setDiaChi(txt_diachi.getText());
        kh.setSdt(txt_sdt.getText());
        kh.setEmail(txt_mail.getText());
        Boolean gt = true;
        if (nu.isSelected()) {
            gt = false;
        }

        if (gt == false) {

        }
        kh.setGioiTinh(gt);
        Integer tt = 0;
        if (hoatdong.isSelected()) {
            tt = 1;
        }
        kh.setTrangThai(tt);
        khsv.update(ma, kh);
    }

    public boolean check() {

        if (txt_ten.getText().isEmpty() || txt_diachi.getText().isEmpty() || txt_sdt.getText().isEmpty() || txt_mail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui lòng điền đầy đủ thông tin");

            txt_mail.requestFocus();
            txt_sdt.requestFocus();
            txt_diachi.requestFocus();
            txt_ten.requestFocus();
            return false;
        }

        return true;
    }

    public boolean checkten() {
        String paramater = "^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*";

        if (txt_ten.getText().matches(paramater)) {
            return true;
        }
        if (txt_ten.getText().length() > 2) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Tên Sai Định Dạng ( Phải Là chữ và lớn hơn 2 kí Tự)");

        return false;
    }

    public boolean checksdt() {

        if (txt_sdt.getText().matches("[0,+84][\\d]{8,11}")) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "vui lòng nhập đúng số điện thoại");

        return false;
    }

    public boolean checkTenl() {
        if (txt_ten.getText().matches("[^!,_,-,.,?,|,@,#,$,%,&,*,(,),~, ]+[^0-9]{2,50}")) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "vui lòng nhập đúng Tên (Tên Phải Là chữ)");
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_khachhang = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        hoatdong = new javax.swing.JRadioButton();
        khoatdong = new javax.swing.JRadioButton();
        nam = new javax.swing.JRadioButton();
        nu = new javax.swing.JRadioButton();
        txt_ma = new javax.swing.JTextField();
        txt_ten = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        txt_mail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btn_lammoi = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_them = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_diachi = new javax.swing.JTextArea();
        cbo_trangthai = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_timkiem = new javax.swing.JTextField();
        btn_tiemkiem = new javax.swing.JButton();
        lui = new javax.swing.JButton();
        tien = new javax.swing.JButton();
        phantrang = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jLabel1.setText("Tìm kiếm:");

        tbl_khachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "SĐT", "Email", "Giới tính", "Trạng thái"
            }
        ));
        tbl_khachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khachhangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_khachhang);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã khách hàng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên khách hàng:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Số điện thoại:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Trạng thái:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Giới tính:");

        buttonGroup1.add(hoatdong);
        hoatdong.setText("Hoạt động");
        hoatdong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoatdongActionPerformed(evt);
            }
        });

        buttonGroup1.add(khoatdong);
        khoatdong.setSelected(true);
        khoatdong.setText("Không hoạt động");
        khoatdong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khoatdongActionPerformed(evt);
            }
        });

        buttonGroup2.add(nam);
        nam.setSelected(true);
        nam.setText("Nam");

        buttonGroup2.add(nu);
        nu.setText("Nữ");

        txt_ma.setEnabled(false);
        txt_ma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_maActionPerformed(evt);
            }
        });

        txt_sdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_sdtActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_xoa.setText("Xóa");
        btn_xoa.setEnabled(false);
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_lammoi))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_them, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btn_lammoi)
                .addGap(28, 28, 28)
                .addComponent(btn_them)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btn_sua)
                .addGap(27, 27, 27)
                .addComponent(btn_xoa)
                .addGap(19, 19, 19))
        );

        txt_diachi.setColumns(20);
        txt_diachi.setRows(5);
        jScrollPane2.setViewportView(txt_diachi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(139, 139, 139)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(nam)
                        .addGap(73, 73, 73)
                        .addComponent(nu)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(62, 62, 62)
                                .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(hoatdong)
                                .addGap(41, 41, 41)
                                .addComponent(khoatdong)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(nam)
                            .addComponent(nu))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(khoatdong)
                            .addComponent(hoatdong)
                            .addComponent(jLabel10))))
                .addGap(31, 31, 31))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hoạt Động", "Không Hoạt Động" }));
        cbo_trangthai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_trangthaiItemStateChanged(evt);
            }
        });
        cbo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangthaiActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin khách hàng");

        jLabel5.setText("Trạng thái:");

        btn_tiemkiem.setText("Tim Kiếm");
        btn_tiemkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tiemkiemActionPerformed(evt);
            }
        });

        lui.setText("<");
        lui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                luiMouseClicked(evt);
            }
        });
        lui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                luiActionPerformed(evt);
            }
        });

        tien.setText(">");
        tien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tienActionPerformed(evt);
            }
        });

        phantrang.setText("1");

        jLabel9.setText("Trang :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_tiemkiem)
                                .addGap(53, 53, 53)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(476, 476, 476)))
                        .addGap(28, 28, 28))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lui)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(phantrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tien)
                .addGap(538, 538, 538))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_tiemkiem))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lui)
                    .addComponent(tien)
                    .addComponent(phantrang)
                    .addComponent(jLabel9))
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hoatdongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoatdongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hoatdongActionPerformed

    private void khoatdongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khoatdongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_khoatdongActionPerformed

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        entity.KhachHang kh = new entity.KhachHang();
        txt_ma.setText("");
        txt_ten.setText("");
        txt_diachi.setText("");
        txt_sdt.setText("");
        txt_mail.setText("");
        tk(-1);

    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        Integer row = tbl_khachhang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chon dong can sửa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn Sửa thông tin khách hàng") == JOptionPane.NO_OPTION) {
            return;
        }
        try {

            if (check() && checkTenl() == true && checksdt() == true && checkmail() == true) {
                sua();
                tk(-1);
                JOptionPane.showMessageDialog(this, "sửa thành công");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ôi bạn ơi thất bại rồi");
        }
    }//GEN-LAST:event_btn_suaActionPerformed

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked

    }//GEN-LAST:event_jPanel1MouseClicked

    private void tbl_khachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khachhangMouseClicked
        // TODO add your handling code here:

        vitri = tbl_khachhang.getSelectedRow();
        entity.KhachHang kh = listKhachHangs.get(vitri);
        txt_ma.setText(kh.getMaKH());
        txt_ten.setText(kh.getTenKH());
        txt_diachi.setText(kh.getDiaChi());
        txt_sdt.setText(kh.getSdt());
        txt_mail.setText(kh.getEmail());
        if (listKhachHangs.get(vitri).getGioiTinh() == true) {
            nam.setSelected(true);
        } else {
            nu.setSelected(true);
        }
        if (listKhachHangs.get(vitri).getTrangThai() == 1) {
            hoatdong.setSelected(true);
        } else {
            khoatdong.setSelected(true);
        }
        cbo_trangthai.setSelectedIndex(0);
    }//GEN-LAST:event_tbl_khachhangMouseClicked

    private void btn_tiemkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tiemkiemActionPerformed
        timkiem();

    }//GEN-LAST:event_btn_tiemkiemActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm khách hàng không?") == JOptionPane.NO_OPTION) {
                return;
            } 
        try {
            if (check() && checkTenl() == true && checksdt() == true && checkmail() == true) {
                them();
                tk(-1);
                JOptionPane.showMessageDialog(this, "Thêm thành công");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi rồi bạn ơi");
        }
    }//GEN-LAST:event_btn_themActionPerformed
    private int tt = -1;
    private void cbo_trangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_trangthaiItemStateChanged
        // TODO add your handling code here:
        String a = cbo_trangthai.getSelectedItem().toString();

        switch (a) {
            case "Hoạt Động":
                tt = 1;
                break;

            case "Không Hoạt Động":
                tt = 0;
                break;

            default:
                tt = -1;
                break;
        }
        String ma = txt_timkiem.getText();
        List<entity.KhachHang> output = khsv.timkiemphantrang(ma, TienLui, tt);
        loadTable(output);
    }//GEN-LAST:event_cbo_trangthaiItemStateChanged

    private void cbo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_trangthaiActionPerformed

    private void txt_maActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_maActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_maActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
    Integer row = tbl_khachhang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "chọn dòng cầ xóa");
            return;
        }
             
        try {

            if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá  khỏi CSDL?") == JOptionPane.NO_OPTION) {
                return;
            } else {
                String Ma = txt_ma.getText();
                khsv.deleteById(Ma);
                tk(-1);
                JOptionPane.showMessageDialog(this, "xóa Thành Công khách hàng có mã là : " + Ma);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void luiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luiActionPerformed

        System.out.println(so);
        String ma = txt_timkiem.getText();
        if (TienLui > 0) {
            so -= 1;
            phantrang.setText(String.valueOf(so));
            TienLui -= 10;
            int b = TienLui;
            List<entity.KhachHang> kh = khsv.timkiemphantrang(ma, b, tt);
            loadTable(kh);
        } else {
            int b = 0;
            List<entity.KhachHang> kh = khsv.timkiemphantrang(ma, b, tt);
            loadTable(kh);
        }
    }//GEN-LAST:event_luiActionPerformed
    private int TienLui = 0;
    private int so = 1;
    private void luiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_luiMouseClicked


    }//GEN-LAST:event_luiMouseClicked


    private void tienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tienActionPerformed
        so += 1;
        TienLui += 10;
        System.out.println(so);
        String ma = txt_timkiem.getText();
        if (TienLui > 0) {
            phantrang.setText(String.valueOf(so));
            int b = TienLui;
            List<entity.KhachHang> kh = khsv.timkiemphantrang(ma, b, tt);
            loadTable(kh);
        }
    }//GEN-LAST:event_tienActionPerformed

    private void txt_sdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_sdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_sdtActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_tiemkiem;
    private javax.swing.JButton btn_xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_trangthai;
    private javax.swing.JRadioButton hoatdong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton khoatdong;
    private javax.swing.JButton lui;
    private javax.swing.JRadioButton nam;
    private javax.swing.JRadioButton nu;
    private javax.swing.JLabel phantrang;
    private javax.swing.JTable tbl_khachhang;
    private javax.swing.JButton tien;
    private javax.swing.JTextArea txt_diachi;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JTextField txt_mail;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
