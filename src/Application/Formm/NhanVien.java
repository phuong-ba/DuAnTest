/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Application.Formm;

import Model.NhanVienModel;
import Repository.NhanVienRepo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class NhanVien extends javax.swing.JPanel {

    private NhanVienRepo repoNV = new NhanVienRepo();
    private int selectedIdNV = -1;

    public NhanVien() {
        initComponents();
        txtIDNV.setEditable(false);
        txtMaNV.setEditable(false);
//      txtIDNhanVienNghi.setEditable(false);
        loadTable();
        loadTableNghiViec();
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblDangLam.getModel();
        model.setRowCount(0);
        // lay danh sach
        List<Model.NhanVienModel> listNhanVien = repoNV.getAllNhanVien();
        for (NhanVienModel nv : listNhanVien) {
            Object[] rowData = {
                nv.getIdNV(),
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getGioiTinhNV() ? "Nam" : "Nữ",
                nv.getNgaySinhNV(),
                nv.getSdtNV(),
                nv.getDiaChiNV(),
                nv.getChucVuNV(),
                nv.getTrangThaiNV() ? "Đang làm" : "Nghỉ việc",
                nv.getTaiKhoanNV(),
                nv.getMatKhauNV(),
                nv.getNgayBatDauNV(),
                nv.getNgayKetThucNV(),};
            model.addRow(rowData);
        }
        rdoLam.setSelected(true);
        rdoNam.setSelected(true);
    }

    public void xoaFrom() {
        txtIDNV.setText("");
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtTaiKhoan.setText("");
        txtMatKhau.setText("");
        rdoNam.setSelected(true);
        rdoNu.setSelected(false);
        rdoLam.setSelected(true);
        rdoNghi.setSelected(false);
        cbbChucVu.setSelectedItem("Nhân viên");
        tblDangLam.clearSelection();
    }

    private void updateTableNhanVien(String keyword, String gioiTinh) {
        DefaultTableModel model = (DefaultTableModel) tblDangLam.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        ArrayList<NhanVienModel> list = repoNV.searchNhanVien(keyword, gioiTinh);
        for (NhanVienModel nv : list) {
            model.addRow(new Object[]{
                nv.getIdNV(),
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getGioiTinhNV() ? "Nam" : "Nữ",
                nv.getNgaySinhNV(),
                nv.getSdtNV(),
                nv.getDiaChiNV(),
                nv.getChucVuNV(),
                nv.getTrangThaiNV() ? "Đang làm" : "Nghỉ việc",
                nv.getTaiKhoanNV(),
                nv.getMatKhauNV(),
                nv.getNgayBatDauNV(),
                nv.getNgayKetThucNV(),});
        }

    }

    private void updateTableNhanVienNghiViec(String keyword, String gioiTinh) {
        DefaultTableModel model = (DefaultTableModel) tblNghiViec.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ

        ArrayList<NhanVienModel> list = repoNV.searchNhanVienNghi(keyword, gioiTinh);
        for (NhanVienModel nv : list) {
            model.addRow(new Object[]{
                nv.getIdNV(),
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getGioiTinhNV() ? "Nam" : "Nữ",
                nv.getNgaySinhNV(),
                nv.getSdtNV(),
                nv.getDiaChiNV(),
                nv.getChucVuNV(),
                nv.getTrangThaiNV() ? "Đang làm" : "Nghỉ việc",
                nv.getTaiKhoanNV(),
                nv.getMatKhauNV(),
                nv.getNgayBatDauNV(),
                nv.getNgayKetThucNV(),});
        }

    }

    public void loadTableNghiViec() {
        DefaultTableModel model = (DefaultTableModel) tblNghiViec.getModel();
        model.setRowCount(0);
        // lay danh sach
        List<Model.NhanVienModel> listNhanVien = repoNV.getAllNhanVienNghiViec();
        for (NhanVienModel nv : listNhanVien) {
            Object[] rowData = {
                nv.getIdNV(),
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getGioiTinhNV() ? "Nam" : "Nữ",
                nv.getNgaySinhNV(),
                nv.getSdtNV(),
                nv.getDiaChiNV(),
                nv.getChucVuNV(),
                nv.getTrangThaiNV() ? "Đang làm" : "Nghỉ việc",
                nv.getTaiKhoanNV(),
                nv.getMatKhauNV(),
                nv.getNgayBatDauNV(),
                nv.getNgayKetThucNV(),};
            model.addRow(rowData);
        }
        rdoLam.setSelected(true);
        rdoNam.setSelected(true);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNVTimDangLam = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cbbGioiTinh = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDangLam = new javax.swing.JTable();
        txtNKT = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNBD = new javax.swing.JTextField();
        btnSua = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTaiKhoan = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        rdoNghi = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        rdoLam = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbChucVu = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        dateNgayBatDau = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtIDNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateNgaySinh = new com.toedter.calendar.JDateChooser();
        txtNgaySinhNV = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNghiViec = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        txtNVNghiLam = new javax.swing.JTextField();
        btnNVKhoiPhuc = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbbNghiViec = new javax.swing.JComboBox<>();

        jLabel1.setText("Tìm Kiếm");

        txtNVTimDangLam.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtNVTimDangLamPropertyChange(evt);
            }
        });
        txtNVTimDangLam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNVTimDangLamKeyReleased(evt);
            }
        });

        jLabel2.setText("Lọc");

        cbbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cbbGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbGioiTinhActionPerformed(evt);
            }
        });

        tblDangLam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MaNV", "Họ Tên", "Giới Tính", "Ngày Sinh", "SDT", "Dịa Chỉ", "Chức Vụ", "Trạng Thái", "Tài Khoản", "Mật Khẩu", "Ngày Bắt Đầu", "Ngày Kết Thúc"
            }
        ));
        tblDangLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDangLamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDangLam);

        jLabel14.setText("Ngày Kết Thúc");

        btnSua.setText("Sửa");
        btnSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaMouseClicked(evt);
            }
        });

        jLabel5.setText("Mã NV");

        jLabel4.setText("Tài Khoản");

        jLabel10.setText("SDT");

        jLabel9.setText("Họ Tên");

        jLabel7.setText("Địa Chỉ");

        jLabel11.setText("Giới Tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        buttonGroup2.add(rdoNghi);
        rdoNghi.setText("Nghỉ việc");

        jLabel12.setText("Trạng Thái");

        buttonGroup2.add(rdoLam);
        rdoLam.setText("Đang làm");

        jLabel6.setText("Mật Khẩu");

        jLabel8.setText("Chức Vụ");

        cbbChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Quản lý" }));
        cbbChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChucVuActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiMouseClicked(evt);
            }
        });
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel15.setText("Ngày Bắt Đầu");

        dateNgayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayKetThucPropertyChange(evt);
            }
        });

        dateNgayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayBatDauPropertyChange(evt);
            }
        });

        jLabel16.setText("ID NV");

        jLabel3.setText("Ngay Sinh");

        dateNgaySinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgaySinhPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel9))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                                .addComponent(txtMaNV)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDNV, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtSDT)
                                    .addComponent(txtTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoNam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdoNu))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel12))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoLam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoNghi, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(btnLamMoi))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtNBD, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtNKT, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNVTimDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 157, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNVTimDangLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel14)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(txtNBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(cbbChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(rdoLam)
                                .addComponent(rdoNghi)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoNu)
                            .addComponent(jLabel11)
                            .addComponent(rdoNam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnThem)
                                    .addComponent(btnSua))
                                .addContainerGap(15, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(txtIDNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnXoa)
                                    .addComponent(btnLamMoi))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jTabbedPane1.addTab("Đang Làm", jPanel2);

        tblNghiViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MaNV", "Họ Tên", "Giới Tính", "Ngày Sinh", "SDT", "Địa Chỉ", "Chức Vụ", "Trạng Thái", "Tài Khoản", "Mật Khẩu", "Ngày Bắt Đầu ", "Ngày Kết Thúc"
            }
        ));
        tblNghiViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNghiViecMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNghiViec);

        jLabel13.setText("Tìm Kiếm");

        txtNVNghiLam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNVNghiLamKeyReleased(evt);
            }
        });

        btnNVKhoiPhuc.setText("Khôi Phục");
        btnNVKhoiPhuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNVKhoiPhucMouseClicked(evt);
            }
        });

        jLabel17.setText("Lọc");

        cbbNghiViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất Cả", "Nam", "Nữ" }));
        cbbNghiViec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbNghiViecActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(txtNVNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNVKhoiPhuc))
                .addContainerGap(386, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtNVNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNVKhoiPhuc))
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbbNghiViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(61, 61, 61)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(217, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nghỉ Việc", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 645, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void cbbChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbChucVuActionPerformed

    private void btnSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaMouseClicked
        try {
            // Lấy chỉ số dòng đã chọn trong bảng
            int chonRow = tblDangLam.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng cần sửa!");
                return;
            }

            // Lấy ID của nhân viên từ bảng
            int idNV = Integer.parseInt(tblDangLam.getValueAt(chonRow, 0).toString());

            // Lấy các giá trị từ form
            String maNV = txtMaNV.getText().trim();
            String tenNV = txtHoTen.getText().trim();
            boolean gioiTinhNV = rdoNam.isSelected();
            String sdtNV = txtSDT.getText().trim();
            String diaChiNV = txtDiaChi.getText().trim();
            String taiKhoanNV = txtTaiKhoan.getText().trim();
            String matKhauNV = txtMatKhau.getText().trim();
            String chucVuNV = cbbChucVu.getSelectedItem() != null ? cbbChucVu.getSelectedItem().toString().trim() : "";
            boolean trangThaiNV = rdoLam.isSelected();

            // ========================= KIỂM TRA DỮ LIỆU NHẬP VÀO ==========================
            if (tenNV.isEmpty() || sdtNV.isEmpty() || diaChiNV.isEmpty() || taiKhoanNV.isEmpty() || matKhauNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Kiểm tra khoảng trắng liên tục (2 dấu cách trở lên giữa các từ)
            if (tenNV.contains("  ")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa số không
            if (tenNV.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chứa số.");
                return;
            }

            // Kiểm tra có chứa ký tự đặc biệt không (trừ dấu cách)
            if (!tenNV.matches("^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]+$")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chứa ký tự đặc biệt.");
                return;
            }

            // Kiểm tra số điện thoại (chỉ chấp nhận số, đúng 10 chữ số)
            if (!sdtNV.matches("^[0-9]{10}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Vui lòng nhập đúng 10 chữ số.");
                return;
            }

            // Kiểm tra độ dài mật khẩu (tối thiểu 6 ký tự)
            if (matKhauNV.length() < 6) {
                JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
                return;
            }
// ========================= CHUYỂN ĐỔI NGÀY THÁNG ==========================
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date ngaySinhNV = null;
            Date ngayBatDauNV = null;
            Date ngayKetThucNV = null;

            try {
                ngaySinhNV = sdf.parse(txtNgaySinhNV.getText().trim());
                ngayBatDauNV = java.sql.Date.valueOf(txtNBD.getText().trim());

                // Nếu người dùng không nhập ngày kết thúc, gán null
                if (txtNKT.getText().trim().isEmpty()) {
                    ngayKetThucNV = null;
                } else {
                    ngayKetThucNV = java.sql.Date.valueOf(txtNKT.getText().trim());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
                return;
            }

            // ========================= TẠO ĐỐI TƯỢNG NHÂN VIÊN ==========================
            NhanVienModel nv = new NhanVienModel(idNV, maNV, tenNV, gioiTinhNV, ngaySinhNV, sdtNV, diaChiNV, chucVuNV, trangThaiNV, taiKhoanNV, matKhauNV, ngayBatDauNV, ngayKetThucNV);

            // ========================= CẬP NHẬT DỮ LIỆU ==========================
            boolean isUpdated = repoNV.updateNhanVien(nv);
            if (isUpdated) {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                loadTable();
                xoaFrom();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật thông tin nhân viên! Vui lòng kiểm tra lại.");
        }
    }//GEN-LAST:event_btnSuaMouseClicked

    private void tblDangLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDangLamMouseClicked
        int chonRow = tblDangLam.getSelectedRow();
        if (chonRow != -1) {
            // Dùng phương thức getCellValue để tránh lỗi NullPointerException
            String idNV = getCellValue(tblDangLam, chonRow, 0);
            String maNV = getCellValue(tblDangLam, chonRow, 1);
            String tenNV = getCellValue(tblDangLam, chonRow, 2);
            String gioiTinhNV = getCellValue(tblDangLam, chonRow, 3);
            String ngaySinhNV = getCellValue(tblDangLam, chonRow, 4);
            String sdt = getCellValue(tblDangLam, chonRow, 5);
            String diaChi = getCellValue(tblDangLam, chonRow, 6);
            String chuVu = getCellValue(tblDangLam, chonRow, 7);
            String trangThai = getCellValue(tblDangLam, chonRow, 8);
            String taiKhoan = getCellValue(tblDangLam, chonRow, 9);
            String matKhau = getCellValue(tblDangLam, chonRow, 10);
            String ngayBatDau = getCellValue(tblDangLam, chonRow, 11);
            String ngayKetThuc = getCellValue(tblDangLam, chonRow, 12);

            txtIDNV.setText(idNV);
            txtMaNV.setText(maNV);
            txtHoTen.setText(tenNV);
            rdoNam.setSelected(gioiTinhNV.equalsIgnoreCase("Nam"));
            rdoNu.setSelected(gioiTinhNV.equalsIgnoreCase("Nữ"));
            txtNgaySinhNV.setText(ngaySinhNV);
            txtSDT.setText(sdt);
            txtDiaChi.setText(diaChi);
            cbbChucVu.setSelectedItem("Nhân viên".equalsIgnoreCase(chuVu) ? "Nhân viên" : "Quản lý");
            rdoLam.setSelected(trangThai.equalsIgnoreCase("Đang làm"));
            rdoNghi.setSelected(trangThai.equalsIgnoreCase("Nghỉ việc"));
            txtTaiKhoan.setText(taiKhoan);
            txtMatKhau.setText(matKhau);
            txtNBD.setText(ngayBatDau);
            txtNKT.setText(ngayKetThuc);

        }
    }//GEN-LAST:event_tblDangLamMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        try {
            // Lấy dữ liệu từ form
            String tenNV = txtHoTen.getText().trim();
            boolean gioiTinhNV = rdoNam.isSelected();
            String sdtNV = txtSDT.getText().trim();
            String diaChiNV = txtDiaChi.getText().trim();
            String taiKhoanNV = txtTaiKhoan.getText().trim();
            String matKhauNV = txtMatKhau.getText().trim();
            String chucVuNV = cbbChucVu.getSelectedItem() != null ? cbbChucVu.getSelectedItem().toString().trim() : "";
            boolean trangThaiNV = rdoLam.isSelected();

            // ========================= KIỂM TRA DỮ LIỆU NHẬP VÀO ==========================
            if (tenNV.isEmpty() || sdtNV.isEmpty() || diaChiNV.isEmpty() || taiKhoanNV.isEmpty() || matKhauNV.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            // Kiểm tra khoảng trắng liên tục (2 dấu cách trở lên giữa các từ)
            if (tenNV.contains("  ")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa số không
            if (tenNV.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chứa số.");
                return;
            }

            // Kiểm tra có chứa ký tự đặc biệt không (trừ dấu cách)
            if (!tenNV.matches("^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]+$")) {
                JOptionPane.showMessageDialog(this, "Họ tên không được chứa ký tự đặc biệt.");
                return;
            }
            //------------------------tenNV----------------------------------------

            // Kiểm tra số điện thoại (chỉ chấp nhận số, đúng 10 chữ số)
            if (!sdtNV.matches("^[0-9]{10}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ! Vui lòng nhập đúng 10 chữ số.");
                return;
            }

            // Kiểm tra số điện thoại đã tồn tại
            if (repoNV.existsPhoneNumber(sdtNV)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại! Vui lòng nhập số khác.");
                return;
            }
            //-----------------------SDT------------------------------------------------

            // Kiểm tra độ dài mật khẩu (tối thiểu 6 ký tự)
            if (matKhauNV.length() < 6) {
                JOptionPane.showMessageDialog(this, "Mật khẩu phải có ít nhất 6 ký tự!");
                return;
            }
            //-----------MK-------------------------------
            // Kiểm tra khoảng trắng liên tục
            if (diaChiNV.contains("  ")) {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa ký tự đặc biệt ngoài (dấu chấm, dấu phẩy, dấu gạch,số)còn ký tự khác không được
            if (!diaChiNV.matches("^[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ0-9 ,.-]+$")) {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa ký tự đặc biệt (ngoài dấu chấm, dấu phẩy và dấu gạch ngang)");
                return;
            }
            //-------------Địa chỉ---------------------------------------
            //---------------------------------------
            // Kiểm tra tài khoản , đã tồn tại
            if (repoNV.existsAccount(taiKhoanNV, matKhauNV)) {
                JOptionPane.showMessageDialog(this, "Tài khoản đã tồn tại! Vui lòng chọn tài khoản khác.");
                return;
            }

            // ========================= CHUYỂN ĐỔI NGÀY THÁNG ==========================
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date ngaySinhNV = null;
            Date ngayBatDauNV = null;
            Date ngayKetThucNV = null;

            try {
                ngaySinhNV = sdf.parse(txtNgaySinhNV.getText().trim());
                ngayBatDauNV = java.sql.Date.valueOf(txtNBD.getText().trim());

                // Nếu người dùng không nhập ngày kết thúc, gán null
                if (txtNKT.getText().trim().isEmpty()) {
                    ngayKetThucNV = null;
                } else {
                    ngayKetThucNV = java.sql.Date.valueOf(txtNKT.getText().trim());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày theo định dạng yyyy-MM-dd!");
                return;
            }

            // ========================= TẠO ĐỐI TƯỢNG NHÂN VIÊN ==========================
            NhanVienModel nvv = new NhanVienModel(tenNV, gioiTinhNV, ngaySinhNV, sdtNV, diaChiNV, chucVuNV,
                    trangThaiNV, taiKhoanNV, matKhauNV, ngayBatDauNV, ngayKetThucNV);

            // ========================= THÊM VÀO DATABASE ==========================
            this.repoNV.insertNhanVien(nvv);
            JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
            loadTable();
            xoaFrom();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm nhân viên! Vui lòng kiểm tra lại.");
        }

    }//GEN-LAST:event_btnThemMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        try {
            int chonRow = tblDangLam.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần xóa!");
                return;
            }

            // Kiểm tra ID hợp lệ
            int idNV;
            try {
                idNV = Integer.parseInt(tblDangLam.getValueAt(chonRow, 0).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: ID nhân viên không hợp lệ!");
                return;
            }

            // Xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa nhân viên này?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Xóa nhân viên
            if (repoNV.deleteNhanVien(idNV)) {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thành công!");
                loadTable(); // Cập nhật lại bảng
                xoaFrom();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa nhân viên thất bại! Vui lòng kiểm tra lại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnLamMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiMouseClicked
        xoaFrom();
    }//GEN-LAST:event_btnLamMoiMouseClicked

    private void txtNVTimDangLamPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtNVTimDangLamPropertyChange


    }//GEN-LAST:event_txtNVTimDangLamPropertyChange

    private void cbbGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbGioiTinhActionPerformed
        String keyword = txtNVTimDangLam.getText().trim();
        String gioiTinh = (String) cbbGioiTinh.getSelectedItem();
        updateTableNhanVien(keyword, gioiTinh);
    }//GEN-LAST:event_cbbGioiTinhActionPerformed

    private void dateNgayBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayBatDauPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgayBatDau.getDate();

            if (selectedDate != null) {
                String ngayBatDau = sdf.format(selectedDate);
                txtNBD.setText(ngayBatDau); // Gán ngày vào JTextField
            } else {
                txtNBD.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgayBatDauPropertyChange

    private void dateNgayKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayKetThucPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgayKetThuc.getDate();

            if (selectedDate != null) {
                String ngayKetThuc = sdf.format(selectedDate);
                txtNKT.setText(ngayKetThuc); // Gán ngày vào JTextField
            } else {
                txtNKT.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgayKetThucPropertyChange

    private void dateNgaySinhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgaySinhPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgaySinh.getDate();

            if (selectedDate != null) {
                String ngaySinh = sdf.format(selectedDate);
                txtNgaySinhNV.setText(ngaySinh); // Gán ngày vào JTextField
            } else {
                txtNgaySinhNV.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgaySinhPropertyChange

    private void txtNVTimDangLamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNVTimDangLamKeyReleased
        String timKiem = txtNVTimDangLam.getText().trim();
        String gioiTinh = (String) cbbChucVu.getSelectedItem();
        updateTableNhanVien(timKiem, gioiTinh);

    }//GEN-LAST:event_txtNVTimDangLamKeyReleased

    private void txtNVNghiLamKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNVNghiLamKeyReleased
        String timKiem = txtNVNghiLam.getText().trim();
        String gioiTinh = (String) cbbNghiViec.getSelectedItem();
        updateTableNhanVienNghiViec(timKiem, gioiTinh);
    }//GEN-LAST:event_txtNVNghiLamKeyReleased

    private void btnNVKhoiPhucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNVKhoiPhucMouseClicked
        if (selectedIdNV == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn nhân viên cần khôi phục!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (repoNV.khoiPhuc(selectedIdNV)) {
                JOptionPane.showMessageDialog(this, "Khôi phục nhân viên thành công!");
                selectedIdNV = -1;
                loadTableNghiViec();
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục nhân viên thất bại!");
            }
        }
    }//GEN-LAST:event_btnNVKhoiPhucMouseClicked

    private void tblNghiViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNghiViecMouseClicked
        int selectedRow = tblNghiViec.getSelectedRow();
        if (selectedRow != -1) {
            selectedIdNV = (int) tblNghiViec.getValueAt(selectedRow, 0); // Lấy ID từ cột đầu tiên
        }
    }//GEN-LAST:event_tblNghiViecMouseClicked

    private void cbbNghiViecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbNghiViecActionPerformed
        String timKiem = txtNVNghiLam.getText().trim();
        String gioiTinh = (String) cbbNghiViec.getSelectedItem();
        updateTableNhanVienNghiViec(timKiem, gioiTinh);
    }//GEN-LAST:event_cbbNghiViecActionPerformed

    private String getCellValue(JTable table, int row, int col) {
        Object value = table.getValueAt(row, col);
        return (value != null) ? value.toString() : "";
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnNVKhoiPhuc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbChucVu;
    private javax.swing.JComboBox<String> cbbGioiTinh;
    private javax.swing.JComboBox<String> cbbNghiViec;
    private com.toedter.calendar.JDateChooser dateNgayBatDau;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private com.toedter.calendar.JDateChooser dateNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghi;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblDangLam;
    private javax.swing.JTable tblNghiViec;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtIDNV;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNBD;
    private javax.swing.JTextField txtNKT;
    private javax.swing.JTextField txtNVNghiLam;
    private javax.swing.JTextField txtNVTimDangLam;
    private javax.swing.JTextField txtNgaySinhNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
