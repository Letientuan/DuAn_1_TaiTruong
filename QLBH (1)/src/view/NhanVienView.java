/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.util.ArrayList;
import java.util.List;
import entity.NhanVien;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.NhanVienRepository;
import service.NhanVienService;
import service.NhanVienSV;
//import ServiceImpl.NhanVienImpl;

/**
 *
 * @author thong
 */
public class NhanVienView extends javax.swing.JPanel {

    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form NhanVien
     */
    DefaultTableModel dtm = new DefaultTableModel();
    private NhanVienSV nvsv = new NhanVienSV();
    List<NhanVien> listNV = nvsv.getAllNhanVien();

    public NhanVienView() {
        initComponents();
        String ma = txtTimKiem.getText();
        List<NhanVien> nv = nvsv.timkiemphantrang(ma, TienLui, tt);
        loadTB(listNV);

    }

    public void loadTB(List<NhanVien> nhanVien) {
        DefaultTableModel model = (DefaultTableModel) tbnNhanVien.getModel();
        model.setColumnCount(0);
        model.addColumn("Mã Nhân Viên");
        model.addColumn("Tên Nhân Viên");
        model.addColumn("Ngày Sinh");
        model.addColumn("Giới tính");
        model.addColumn("Địa Chỉ");
        model.addColumn("Số Điện thoại");
        model.addColumn("Email");
        model.addColumn("Chức vụ");
        model.addColumn("Tài Khoản");
        model.addColumn("Mật Khẩu");
        model.addColumn("Trạng thái");
        model.setRowCount(0);
        for (NhanVien nv : nhanVien) {
            Object[] row = new Object[]{
                nv.getMaNV(), nv.getTenNV(), nv.getNgaySinh(), nv.getGioiTinh(),
                nv.getDiaChi(), nv.getSdt(), nv.getEmail(), nv.getChucVu(),
                nv.getTaiKhoan(), nv.getMatKhau(), nv.gettrangthai(nv.getTrangThai()),};
            model.addRow(row);

        }
        listNV = nhanVien;
    }

    public void clearfrom() {
        this.txtDiaChi.setText("");
        this.txtMaNV.setText("");
        this.txtTen.setText("");
        this.txtEmail.setText("");
        this.txtMK.setText("");
        this.txtNgaySinh.setText("");
        this.txtSDT.setText("");
        this.r_Nam.setSelected(true);
        this.txtTenDN.setText("");
        this.cbbChucVu.setSelectedIndex(0);
        this.r_DangLam.setSelected(true);

    }

    public boolean checksdt() {

        if (txtSDT.getText().matches("[0,+84][\\d]{9}")) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "vui lòng nhập đúng số điện thoại");

        return false;
    }

    public boolean checkNgay() {
        SimpleDateFormat s = new SimpleDateFormat();
        s.applyPattern("dd-MM-yyyy");
        try {
            Date ngay = s.parse(txtNgaySinh.getText().toString());
            return true;
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Sai đinh dang ngay sinh");
            return false;
        }
    }

    public boolean checkten() {
        String paramater = "^[AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+ [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]+(?: [AÀẢÃÁẠĂẰẲẴẮẶÂẦẨẪẤẬBCDĐEÈẺẼÉẸÊỀỂỄẾỆFGHIÌỈĨÍỊJKLMNOÒỎÕÓỌÔỒỔỖỐỘƠỜỞỠỚỢPQRSTUÙỦŨÚỤƯỪỬỮỨỰVWXYỲỶỸÝỴZ][aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz]*)*";

        if (txtTen.getText().matches(paramater)) {
            return true;
        }
        if (txtTen.getText().length() > 2) {
            return true;
        }
        JOptionPane.showMessageDialog(this, "Tên Sai Định Dạng ( Phải Là chữ và lớn hơn 2 kí Tự)");

        return false;
    }

    public boolean checkmail() {
        if (!isValidEmail(txtEmail.getText())) {
            JOptionPane.showMessageDialog(this, "mail không đúng định dạng");
            return false;
        }

        return true;
    }

    public void sua() throws Exception {
        try {
            NhanVien nv = new NhanVien();
            String ma = txtMaNV.getText();
            nv.setTenNV(txtTen.getText());
            nv.setTaiKhoan(txtTenDN.getText());
            nv.setMatKhau(txtMK.getText());
            nv.setDiaChi(txtDiaChi.getText());
            nv.setNgaySinh(txtNgaySinh.getText());
            nv.setSdt(txtSDT.getText());
            nv.setEmail(txtEmail.getText());
            Boolean gt = r_Nam.isSelected(); // Assuming r_Nam corresponds to Male
            nv.setGioiTinh(gt.toString());
            nv.setChucVu(cbbChucVu.getSelectedItem().toString());
            int tt = r_nghiViec.isSelected() ? 0 : 1;
            nv.setTrangThai(tt);
            nvsv.update(ma, nv);
            tk(-1);
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception stack trace for debugging
            throw e; // Re-throw the exception after printing the stack trace
        }
    }

    public void tk(int status) {
        List<NhanVien> nhanViens = nvsv.getAllNhanVien();
        loadTB(nhanViens);
    }

    public void add() {
        try {
            if (check() && checkten() && checkNgay() && checkmail() && checksdt()) {
                NhanVien nv = new NhanVien();
                nv.setTenNV(txtTen.getText());
                nv.setTaiKhoan(txtTenDN.getText());
                nv.setMatKhau(txtMK.getText());
                nv.setDiaChi(txtDiaChi.getText());
                nv.setNgaySinh(txtNgaySinh.getText());
                nv.setSdt(txtSDT.getText());
                nv.setEmail(txtEmail.getText());
                Boolean gt = r_Nam.isSelected(); // Assuming r_Nam corresponds to Male
                nv.setGioiTinh(gt.toString());
                nv.setChucVu(cbbChucVu.getSelectedItem().toString());
                int tt = r_nghiViec.isSelected() ? 0 : 1;
                nv.setTrangThai(tt);

                nvsv.insert(nv);
                tk(-1);
                JOptionPane.showMessageDialog(this, "Thêm mới thành công");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
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

    public boolean check() {

        if (txtTen.getText().isEmpty() || txtTenDN.getText().isEmpty() || txtSDT.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "vui lòng điền đầy đủ thông tin");

            txtEmail.requestFocus();
            txtSDT.requestFocus();
            txtDiaChi.requestFocus();
            txtTen.requestFocus();
            return false;
        }

        return true;
    }

    /**
     * x. This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        txtTimKiem = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        r_DangLam = new javax.swing.JRadioButton();
        r_nghiViec = new javax.swing.JRadioButton();
        r_Nam = new javax.swing.JRadioButton();
        r_Nu = new javax.swing.JRadioButton();
        cbbChucVu = new javax.swing.JComboBox();
        txtMaNV = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtMK = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        bttUpdate = new javax.swing.JButton();
        bttAdd = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtTenDN = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbnNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        lui = new javax.swing.JButton();
        tien = new javax.swing.JButton();
        soTrang = new javax.swing.JLabel();
        btn_tiemkiem = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cbo_trangthai = new javax.swing.JComboBox<>();

        setName("jpnNhanVien"); // NOI18N

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Thông tin nhân viên");

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Mã nhân viên:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Tên nhân viên:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Ngày sinh:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Số điện thoại:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Email:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Chức vụ:");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Trạng thái:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Mật khẩu:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Giới tính:");

        buttonGroup1.add(r_DangLam);
        r_DangLam.setText("Đang làm việc");
        r_DangLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_DangLamActionPerformed(evt);
            }
        });

        buttonGroup1.add(r_nghiViec);
        r_nghiViec.setSelected(true);
        r_nghiViec.setText("Nghỉ việc");
        r_nghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r_nghiViecActionPerformed(evt);
            }
        });

        buttonGroup2.add(r_Nam);
        r_Nam.setSelected(true);
        r_Nam.setText("Nam");

        buttonGroup2.add(r_Nu);
        r_Nu.setText("Nữ");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Admin", "User" }));

        txtMaNV.setEnabled(false);

        txtMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMKActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton4.setBackground(new java.awt.Color(153, 0, 153));
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Làm mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        bttUpdate.setBackground(new java.awt.Color(153, 0, 153));
        bttUpdate.setForeground(new java.awt.Color(255, 255, 255));
        bttUpdate.setText("Sửa");
        bttUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttUpdateActionPerformed(evt);
            }
        });

        bttAdd.setBackground(new java.awt.Color(153, 0, 153));
        bttAdd.setForeground(new java.awt.Color(255, 255, 255));
        bttAdd.setText("Thêm");
        bttAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bttAddActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(153, 0, 153));
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xoa");
        btnXoa.setEnabled(false);
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bttAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(bttAdd)
                .addGap(18, 18, 18)
                .addComponent(bttUpdate)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Tên đăng nhập :");

        txtTenDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenDNActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 204, 51));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(136, 136, 136)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addGap(43, 43, 43)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEmail)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(r_Nam)
                                            .addComponent(r_DangLam))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(r_nghiViec)
                                            .addComponent(r_Nu)))
                                    .addComponent(cbbChucVu, 0, 219, Short.MAX_VALUE)))
                            .addComponent(jLabel10)))
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(r_DangLam)
                            .addComponent(r_nghiViec))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(r_Nam)
                            .addComponent(r_Nu))
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtTenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbnNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", "SĐT", "Email", "Chức vụ", "Giới tính", "Tên đăng nhập", "Mật khẩu", "Trạng thái"
            }
        ));
        tbnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbnNhanVien);

        jLabel1.setText("Tìm kiếm:");

        lui.setText("<");
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

        soTrang.setText("1");

        btn_tiemkiem.setText("Tim Kiếm");
        btn_tiemkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tiemkiemActionPerformed(evt);
            }
        });

        jLabel14.setText("Trạng thái:");

        cbo_trangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đang làm việc", "Nghỉ việc" }));
        cbo_trangthai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbo_trangthaiItemStateChanged(evt);
            }
        });
        cbo_trangthai.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cbo_trangthaiAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cbo_trangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangthaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel1)
                            .addGap(18, 18, 18)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btn_tiemkiem)
                            .addGap(109, 109, 109)
                            .addComponent(jLabel14)
                            .addGap(29, 29, 29)
                            .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(558, 558, 558)
                        .addComponent(lui)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(soTrang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tien)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btn_tiemkiem)
                    .addComponent(jLabel14)
                    .addComponent(cbo_trangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soTrang)
                    .addComponent(tien)
                    .addComponent(lui))
                .addGap(13, 13, 13))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void r_DangLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_DangLamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_DangLamActionPerformed

    private void r_nghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r_nghiViecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_r_nghiViecActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        this.clearfrom();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void bttUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttUpdateActionPerformed

        int row = tbnNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng cần sửa!");
            return;
        }

        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn cập nhật thông tin ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            if (check() && checkten() == true && checkNgay() == true && checkmail() == true
                    && checksdt() == true) {
                sua();
                loadTB(listNV);
                JOptionPane.showMessageDialog(this, "Thành Công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
        loadTB(listNV);
    }//GEN-LAST:event_bttUpdateActionPerformed

    private void bttAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bttAddActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thêm ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            if (check() && checkten() == true && checkNgay() == true && checkmail() == true
                    && checksdt() == true) {
                add();
                JOptionPane.showMessageDialog(this, "Thành Công");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }//GEN-LAST:event_bttAddActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        Integer row = tbnNhanVien.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chon dong can xoa");
            return;
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá  khỏi CSDL?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            String ma = (String) tbnNhanVien.getValueAt(row, 0);
            nvsv.delete(ma);
            tk(-1);
            JOptionPane.showMessageDialog(jButton1, "Xóa thành công");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(jButton1, "Lỗi");
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMKActionPerformed

    private void tbnNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnNhanVienMouseClicked
        Integer row = tbnNhanVien.getSelectedRow();
        NhanVien nv = listNV.get(row);
        txtMaNV.setText(nv.getMaNV());
        txtTen.setText(nv.getTenNV());
        txtDiaChi.setText(nv.getDiaChi());
        txtEmail.setText(nv.getEmail());
        txtMK.setText(nv.getMatKhau());
        txtNgaySinh.setText(nv.getNgaySinh());
        txtSDT.setText(nv.getSdt());
        txtTenDN.setText(nv.getTaiKhoan());
        if (listNV.get(row).getGioiTinh().equalsIgnoreCase("Nam")) {
            r_Nam.setSelected(true);
        } else {
            r_Nu.setSelected(true);
        }
        if (listNV.get(row).getTrangThai() == 1) {
            r_DangLam.setSelected(true);
        } else {
            r_nghiViec.setSelected(true);
        }
        if (listNV.get(row).getChucVu().equalsIgnoreCase("admin")) {
            cbbChucVu.setSelectedItem("Admin");
        } else {
            cbbChucVu.setSelectedItem("User");
        }

    }//GEN-LAST:event_tbnNhanVienMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
//        //tim kiem theo ma va ten 
//        String txtSearch = this.txtTimKiem.getText().toString();
//        List<NhanVien> lstNV = new NhanVienRepository().timKiemTheoTenHoacMa(txtSearch);
//        model = (DefaultTableModel) tbnNhanVien.getModel();
//        model.setRowCount(0);
//        for (NhanVien x : lstNV) {
//            Object roww[] = new Object[]{
//                x.getMaNV(), x.getTenNV(), x.getNgaySinh(), x.getDiaChi(), x.getSdt(), x.getEmail(), x.getChucVu().getTenCV(), x.getGioiTinh(), x.getDangNhap().getPassWord(), x.getTrangThai() == 0 ? "Đi làm" : "Không đi làm"
//            };
//            model.addRow(roww);
//        }


    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void txtTenDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenDNActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenDNActionPerformed
    private int TienLui = 0;
    private int so = 1;

    private void tienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tienActionPerformed
//        // TODO add your handling code here:
//         TienLui += 5;
//         
//        System.out.println(TienLui);
//        if (TienLui > 0) {
//             so += 1;
//            int b = TienLui;
//           
//         soTrang.setText(String.valueOf(so));
//            List<NhanVien> kh = repoNV.phantrang(b);
//            loadTB();
//        }
    }//GEN-LAST:event_tienActionPerformed

    private void luiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_luiActionPerformed
//        // TODO add your handling code here:
//        System.out.println(TienLui);
//     
//       
//        if (TienLui > 0) {
//               so -= 1;
//            TienLui -= 5;
//            int b = TienLui;
//              soTrang.setText(String.valueOf(so));
//            List<NhanVien> kh = repoNV.phantrang(b);
//            loadTB();
//           
//        } else {
//            int b = 0;
//            List<entity.NhanVien> kh = repoNV.phantrang(b);
//            
//        }

    }//GEN-LAST:event_luiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
//        DoiMatKhau dmk = new DoiMatKhau();
//        dmk.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btn_tiemkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tiemkiemActionPerformed

        String ma = txtTimKiem.getText();
        List<NhanVien> nv = nvsv.timkiemphantrang(ma, TienLui, tt);
        loadTB(nv);
    }//GEN-LAST:event_btn_tiemkiemActionPerformed

    private int tt = -1;
    private void cbo_trangthaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbo_trangthaiItemStateChanged
//         TODO add your handling code here:
        String a = cbo_trangthai.getSelectedItem().toString();

        switch (a) {
            case "Đang làm việc":
                tt = 1;
                break;

            case "Nghỉ việc":
                tt = 0;
                break;

            default:
                tt = -1;
                break;
        }
        String ma = txtTimKiem.getText();
        List<NhanVien> output = nvsv.timkiemphantrang(ma, TienLui, tt);
        loadTB(output);
    }//GEN-LAST:event_cbo_trangthaiItemStateChanged

    private void cbo_trangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangthaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_trangthaiActionPerformed

    private void cbo_trangthaiAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cbo_trangthaiAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cbo_trangthaiAncestorAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btn_tiemkiem;
    private javax.swing.JButton bttAdd;
    private javax.swing.JButton bttUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cbbChucVu;
    private javax.swing.JComboBox<String> cbo_trangthai;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lui;
    private javax.swing.JRadioButton r_DangLam;
    private javax.swing.JRadioButton r_Nam;
    private javax.swing.JRadioButton r_Nu;
    private javax.swing.JRadioButton r_nghiViec;
    private javax.swing.JLabel soTrang;
    private javax.swing.JTable tbnNhanVien;
    private javax.swing.JButton tien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenDN;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

//    private void loadDataOnTable(NhanVien nv) {
//        txtTen.setText(nv.getTenNV());
//        txtDiaChi.setText(nv.getDiaChi());
//        txtSDT.setText(nv.getSdt());
//        txtEmail.setText(nv.getEmail());
//        txtMK.setText(nv.getDangNhap().getPassWord());
//        txtTenDN.setText(nv.getDangNhap().getUserName());
//        txtNgaySinh.setText(nv.getNgaySinh());
//        txtMaNV.setText(nv.getMaNV());
//
//        cbbChucVu.getModel().setSelectedItem(nv.getChucVu().getTenCV().toString());
//
//        if (nv.getGioiTinh().equalsIgnoreCase("Nam")) {
//            r_Nam.setSelected(true);
//        } else {
//            r_Nu.setSelected(true);
//        }
//
//        if (nv.getTrangThai() == 1) {
//            r_DangLam.setSelected(true);
//        } else {
//            r_nghiViec.setSelected(true);
//        }
//    }
}
