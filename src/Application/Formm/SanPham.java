/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Application.Formm;

import Model.ChatLieu;
import Model.KichThuoc;
import Model.MauSac;
import Model.SanPhamModel;
import Model.SanPhamModel2;
import Model.ThuongHieu;
import Model.XuatXu;
import Repository.ChatLieuRepo;
import Repository.KichThuocRepo;
import Repository.MauSacRepo;
import Repository.SanPhamRepo;
import Repository.ThuongHieuRepo;
import Repository.XuatXuRepo;
import View_Thuoc_Tinh.ViewChatLieu;
import View_Thuoc_Tinh.ViewKichThuoc;
import View_Thuoc_Tinh.ViewMauSac;
import View_Thuoc_Tinh.ViewThuongHieu;
import View_Thuoc_Tinh.ViewXuatXu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class SanPham extends javax.swing.JPanel {

    private SanPhamRepo repoSP;
    private int selectedIdSP = -1;
    private KichThuocRepo repoKT = new KichThuocRepo();
    private ChatLieuRepo repoCL = new ChatLieuRepo();
    private MauSacRepo repoMS = new MauSacRepo();
    private ThuongHieuRepo repoTH = new ThuongHieuRepo();
    private XuatXuRepo repoXX = new XuatXuRepo();

    public SanPham() {
        this.repoSP = new SanPhamRepo();
        initComponents();
        txtID.setEditable(false);
        txtMaSP.setEditable(false);
        loadComboBoxChatLieu(null);
        loadComboBoxKichThuoc(null);
        loadComboBoxMauXac(null);
        loadComboBoxThuongHieu(null);
        loadComboBoxXuatXu(null);
        loadTable();
        loadTableNghi();
    }

    public void xoaForm() {
        txtTenSP.setText("");
        txtSoLuong.setText("");
        txtMoTa.setText("");
        txtNgayTao.setText("");
        txtGiaNhap.setText("");
        txtGiaBan.setText("");

        // Đặt lại trạng thái radio button
        rdoConSP.setSelected(true); // Giả sử mặc định là "Còn hàng"

        // Đặt lại giá trị mặc định cho ComboBox
        cbbMauSac.setSelectedIndex(0);
        cbbKichThuoc.setSelectedIndex(0);
        cbbChatLieu.setSelectedIndex(0);
        cbbXuatXu.setSelectedIndex(0);
        cbbThuongHieu.setSelectedIndex(0);

        // Bỏ chọn dòng trong bảng nếu có
        tblSP.clearSelection();
    }

    private void loadComboBoxChatLieu(String chatLieu) {
        cbbChatLieu.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.ChatLieu> listCL = repoCL.getAll(); // Lấy danh sách chất liệu từ database

        for (ChatLieu cl : listCL) {
            cbbChatLieu.addItem(cl.getTenCL()); // Thêm từng chất liệu vào ComboBox
        }
        if (chatLieu == null && !listCL.isEmpty()) {
            cbbChatLieu.setSelectedIndex(0);
        } else {
            cbbChatLieu.setSelectedItem(chatLieu);
        }
    }

    private void loadComboBoxKichThuoc(String kichThuoc) {
        cbbKichThuoc.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.KichThuoc> listKT = repoKT.getAll(); // Lấy danh sách chất liệu từ database

        for (KichThuoc cl : listKT) {
            cbbKichThuoc.addItem(cl.getTenKT()); // Thêm từng chất liệu vào ComboBox
        }
        if (kichThuoc == null && !listKT.isEmpty()) {
            cbbKichThuoc.setSelectedIndex(0);
        } else {
            cbbKichThuoc.setSelectedItem(kichThuoc);
        }
    }

    private void loadComboBoxMauXac(String mauXac) {
        cbbMauSac.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.MauSac> listKT = repoMS.getAll(); // Lấy danh sách chất liệu từ database

        for (MauSac cl : listKT) {
            cbbMauSac.addItem(cl.getTenMau()); // Thêm từng chất liệu vào ComboBox
        }
        if (mauXac == null && !listKT.isEmpty()) {
            cbbMauSac.setSelectedIndex(0);
        } else {
            cbbMauSac.setSelectedItem(mauXac);
        }
    }

    private void loadComboBoxThuongHieu(String thuongHieu) {
        cbbThuongHieu.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.ThuongHieu> listKT = repoTH.getAll(); // Lấy danh sách chất liệu từ database

        for (ThuongHieu cl : listKT) {
            cbbThuongHieu.addItem(cl.getTenTH()); // Thêm từng chất liệu vào ComboBox
        }
        if (thuongHieu == null && !listKT.isEmpty()) {
            cbbThuongHieu.setSelectedIndex(0);
        } else {
            cbbThuongHieu.setSelectedItem(thuongHieu);
        }
    }

    private void loadComboBoxXuatXu(String xuatXu) {
        cbbXuatXu.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.XuatXu> listKT = repoXX.getAll(); // Lấy danh sách chất liệu từ database

        for (XuatXu cl : listKT) {
            cbbXuatXu.addItem(cl.getTenXX()); // Thêm từng chất liệu vào ComboBox
        }
        if (xuatXu == null && !listKT.isEmpty()) {
            cbbXuatXu.setSelectedIndex(0);
        } else {
            cbbXuatXu.setSelectedItem(xuatXu);
        }
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) this.tblSP.getModel();
        model.setRowCount(0);
        List<Model.SanPhamModel> listSP = repoSP.getAllSanPham();
        for (Model.SanPhamModel sanPham : listSP) {
            Object[] rowData = {
                sanPham.getId(),
                sanPham.getMaSanPham(),
                sanPham.getTenSanPham(),
                sanPham.getSoLuongTon(),
                sanPham.getMauSac(),
                sanPham.getKichThuoc(),
                sanPham.getThuongHieu(),
                sanPham.getXuatXu(),
                sanPham.getChatLieu(),
                sanPham.getMoTa(),
                sanPham.getNgayTao(),
                sanPham.getGiaNhap(),
                sanPham.getGiaBan(),
                sanPham.getTrangThai() ? "Còn SP" : "Hết SP",};
            model.addRow(rowData);
        }
    }

    public void updateloadTable(String keyword) {
        DefaultTableModel model = (DefaultTableModel) this.tblSP.getModel();
        model.setRowCount(0);
        List<Model.SanPhamModel> listSP = repoSP.searchSanPhamBan(keyword);
        for (Model.SanPhamModel sanPham : listSP) {
            Object[] rowData = {
                sanPham.getId(),
                sanPham.getMaSanPham(),
                sanPham.getTenSanPham(),
                sanPham.getSoLuongTon(),
                sanPham.getMauSac(),
                sanPham.getKichThuoc(),
                sanPham.getThuongHieu(),
                sanPham.getXuatXu(),
                sanPham.getChatLieu(),
                sanPham.getMoTa(),
                sanPham.getNgayTao(),
                sanPham.getGiaNhap(),
                sanPham.getGiaBan(),
                sanPham.getTrangThai() ? "Còn SP" : "Hết SP",};
            model.addRow(rowData);
        }
    }

    public void loadTableNghi() {
        DefaultTableModel model = (DefaultTableModel) this.tblSPNgungBan.getModel();
        model.setRowCount(0);
        List<Model.SanPhamModel> listSP = repoSP.getAllSanPhamNgung();
        for (Model.SanPhamModel sanPham : listSP) {
            Object[] rowData = {
                sanPham.getId(),
                sanPham.getMaSanPham(),
                sanPham.getTenSanPham(),
                sanPham.getSoLuongTon(),
                sanPham.getMauSac(),
                sanPham.getKichThuoc(),
                sanPham.getThuongHieu(),
                sanPham.getXuatXu(),
                sanPham.getChatLieu(),
                sanPham.getMoTa(),
                sanPham.getNgayTao(),
                sanPham.getGiaNhap(),
                sanPham.getGiaBan(),
                sanPham.getTrangThai() ? "Còn SP" : "Hết SP",};
            model.addRow(rowData);
        }
    }

    public void updateloadTableNghi(String keyword) {
        DefaultTableModel model = (DefaultTableModel) this.tblSPNgungBan.getModel();
        model.setRowCount(0);
        List<Model.SanPhamModel> listSP = repoSP.searchSanPhamNgung(keyword);
        for (Model.SanPhamModel sanPham : listSP) {
            Object[] rowData = {
                sanPham.getId(),
                sanPham.getMaSanPham(),
                sanPham.getTenSanPham(),
                sanPham.getSoLuongTon(),
                sanPham.getMauSac(),
                sanPham.getKichThuoc(),
                sanPham.getThuongHieu(),
                sanPham.getXuatXu(),
                sanPham.getChatLieu(),
                sanPham.getMoTa(),
                sanPham.getNgayTao(),
                sanPham.getGiaNhap(),
                sanPham.getGiaBan(),
                sanPham.getTrangThai() ? "Còn SP" : "Hết SP",};
            model.addRow(rowData);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemSp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbKichThuoc = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbbThuongHieu = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbXuatXu = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbbChatLieu = new javax.swing.JComboBox<>();
        btnThemMauSac = new javax.swing.JButton();
        btnThemKichThuoc = new javax.swing.JButton();
        btnThemThuongHieu = new javax.swing.JButton();
        btnThemXuatXu = new javax.swing.JButton();
        btnThemChatLieu = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        btnSuaSp = new javax.swing.JButton();
        btnXoaSP = new javax.swing.JButton();
        btnLamMoiSP = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        rdoConSP = new javax.swing.JRadioButton();
        rdoHetSP = new javax.swing.JRadioButton();
        jLabel15 = new javax.swing.JLabel();
        txtMoTa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dateNgayTaoSP = new com.toedter.calendar.JDateChooser();
        txtNgayTao = new javax.swing.JTextField();
        tbnLoad = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSPNgungBan = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtTimSPKetThuc = new javax.swing.JTextField();
        btnSPKhoiPhuc = new javax.swing.JButton();
        btnLoadNghi = new javax.swing.JButton();

        jLabel1.setText("Tìm kiếm");

        txtTimKiemSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemSpKeyReleased(evt);
            }
        });

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Số Lượng", "Màu Sắc", "Kích Thước", "Thương Hiệu", "Xuất Xứ ", "Chất Liệu", "Mô Tả", "Ngày Tạo", "Giá Nhập", "Giá Bán", "Trang Thai"
            }
        ));
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        jLabel2.setText("Ma SP");

        jLabel3.setText("Ten SP");

        jLabel4.setText("So Luong");

        jLabel5.setText("Giá Bán");

        jLabel6.setText("Màu Sắc");

        cbbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel8.setText("Thương Hiệu");

        jLabel9.setText("Xuất Xứ");

        jLabel10.setText("Chất Liệu");

        btnThemMauSac.setText("+");
        btnThemMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemMauSacActionPerformed(evt);
            }
        });

        btnThemKichThuoc.setText("+");
        btnThemKichThuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKichThuocActionPerformed(evt);
            }
        });

        btnThemThuongHieu.setText("+");
        btnThemThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemThuongHieuActionPerformed(evt);
            }
        });

        btnThemXuatXu.setText("+");
        btnThemXuatXu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemXuatXuActionPerformed(evt);
            }
        });

        btnThemChatLieu.setText("+");
        btnThemChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemChatLieuActionPerformed(evt);
            }
        });

        btnThemSP.setText("Thêm");
        btnThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSPMouseClicked(evt);
            }
        });

        btnSuaSp.setText("Sửa");
        btnSuaSp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaSpMouseClicked(evt);
            }
        });
        btnSuaSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSpActionPerformed(evt);
            }
        });

        btnXoaSP.setText("Xóa");
        btnXoaSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSPMouseClicked(evt);
            }
        });

        btnLamMoiSP.setText("Làm Mới");
        btnLamMoiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiSPMouseClicked(evt);
            }
        });

        jLabel12.setText("ID");

        jLabel13.setText("Kích Thước");

        jLabel7.setText("Giá Nhập");

        jLabel14.setText("Trạng Thái");

        buttonGroup1.add(rdoConSP);
        rdoConSP.setText("Còn SP");

        buttonGroup1.add(rdoHetSP);
        rdoHetSP.setText("Hết SP");

        jLabel15.setText("Mô tả");

        jLabel16.setText("Ngày Tạo");

        dateNgayTaoSP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayTaoSPPropertyChange(evt);
            }
        });

        txtNgayTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayTaoActionPerformed(evt);
            }
        });

        tbnLoad.setText("reset");
        tbnLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbnLoadMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(3, 3, 3)
                                        .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(45, 45, 45)
                                                .addComponent(jLabel4)
                                                .addGap(6, 6, 6))
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(btnThemMauSac)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel13)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnThemKichThuoc)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnThemThuongHieu)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                                .addComponent(jLabel9))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(btnThemSP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(77, 77, 77)
                                                        .addComponent(btnSuaSp, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(101, 101, 101)
                                                        .addComponent(btnXoaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(btnThemChatLieu))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rdoConSP)
                                        .addGap(121, 121, 121))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                    .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(btnThemXuatXu))
                                                .addComponent(btnLamMoiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rdoHetSP))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtID)
                                .addGap(54, 54, 54)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(423, 423, 423)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateNgayTaoSP, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(txtGiaNhap))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tbnLoad)
                        .addGap(314, 314, 314))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimKiemSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tbnLoad))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtID)
                    .addComponent(jLabel12))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(rdoConSP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rdoHetSP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbKichThuoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(cbbThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(cbbXuatXu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemMauSac)
                    .addComponent(btnThemKichThuoc)
                    .addComponent(btnThemThuongHieu)
                    .addComponent(btnThemXuatXu)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(btnThemChatLieu)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMoTa, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(dateNgayTaoSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnSuaSp)
                                .addComponent(btnThemSP)
                                .addComponent(btnLamMoiSP))
                            .addComponent(btnXoaSP))))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        tblSPNgungBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã SP", "Tên SP", "Số Lượng", "Màu Sắc", "Kích Thước", "Thương Hiệu", "Xuất Xứ ", "Chất Liệu", "Giá"
            }
        ));
        tblSPNgungBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPNgungBanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblSPNgungBan);

        jLabel11.setText("Tìm Kiếm");

        txtTimSPKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimSPKetThucActionPerformed(evt);
            }
        });
        txtTimSPKetThuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSPKetThucKeyReleased(evt);
            }
        });

        btnSPKhoiPhuc.setText("Khôi Phục");
        btnSPKhoiPhuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSPKhoiPhucMouseClicked(evt);
            }
        });

        btnLoadNghi.setText("reset");
        btnLoadNghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLoadNghiMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTimSPKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118)
                        .addComponent(btnLoadNghi)
                        .addGap(36, 36, 36)
                        .addComponent(btnSPKhoiPhuc))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(217, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtTimSPKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSPKhoiPhuc)
                    .addComponent(btnLoadNghi))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ngừng Bán", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimSPKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimSPKetThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimSPKetThucActionPerformed

    private void btnThemMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemMauSacActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Màu Sắc", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        ViewMauSac ms = new ViewMauSac(cbbMauSac);
        dialog.setContentPane(ms);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnThemMauSacActionPerformed

    private void btnThemKichThuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKichThuocActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Kích Thước", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        ViewKichThuoc kt = new ViewKichThuoc(cbbKichThuoc);
        dialog.setContentPane(kt);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnThemKichThuocActionPerformed

    private void btnThemThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemThuongHieuActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Thương Hiệu", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        ViewThuongHieu th = new ViewThuongHieu(cbbThuongHieu);
        dialog.setContentPane(th);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnThemThuongHieuActionPerformed

    private void btnThemXuatXuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemXuatXuActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Xuất Xứ", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        ViewXuatXu xx = new ViewXuatXu(cbbXuatXu);
        dialog.setContentPane(xx);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnThemXuatXuActionPerformed

    private void btnThemChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemChatLieuActionPerformed
        JDialog dialog = new JDialog((Frame) null, "Chất Liệu", true);
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        ViewChatLieu cl = new ViewChatLieu(cbbChatLieu);
        dialog.setContentPane(cl);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnThemChatLieuActionPerformed

    private void dateNgayTaoSPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayTaoSPPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgayTaoSP.getDate();

            if (selectedDate != null) {
                String ngayBatDau = sdf.format(selectedDate);
                txtNgayTao.setText(ngayBatDau); // Gán ngày vào JTextField
            } else {
                txtNgayTao.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgayTaoSPPropertyChange

    private void txtNgayTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayTaoActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked
        int selectedRow = tblSP.getSelectedRow();
        if (selectedRow != -1) {
            String idSP = tblSP.getValueAt(selectedRow, 0).toString();
            String maSP = tblSP.getValueAt(selectedRow, 1).toString();
            String tenSP = tblSP.getValueAt(selectedRow, 2).toString();
            String soLuong = tblSP.getValueAt(selectedRow, 3).toString();
            String mauSac = tblSP.getValueAt(selectedRow, 4).toString();
            String kichThuoc = tblSP.getValueAt(selectedRow, 5).toString();
            String thuongHieu = tblSP.getValueAt(selectedRow, 6).toString();
            String xuatXu = tblSP.getValueAt(selectedRow, 7).toString();
            String chatLieu = tblSP.getValueAt(selectedRow, 8).toString();
            String moTa = tblSP.getValueAt(selectedRow, 9).toString();
            String ngayTao = tblSP.getValueAt(selectedRow, 10).toString();
            String giaNhap = tblSP.getValueAt(selectedRow, 11).toString();
            String giaBan = tblSP.getValueAt(selectedRow, 12).toString();
            String trangThai = tblSP.getValueAt(selectedRow, 13).toString();
            //
            txtID.setText(idSP);
            txtMaSP.setText(maSP);
            txtTenSP.setText(tenSP);
            txtSoLuong.setText(soLuong);
            txtMoTa.setText(moTa);
            txtNgayTao.setText(ngayTao);
            txtGiaNhap.setText(giaNhap);
            txtGiaBan.setText(giaBan);
            rdoConSP.setSelected(trangThai.equalsIgnoreCase("Còn SP"));
            rdoHetSP.setSelected(trangThai.equalsIgnoreCase("Hết SP"));
            //
            loadComboBoxXuatXu(xuatXu);
            loadComboBoxThuongHieu(thuongHieu);
            loadComboBoxMauXac(mauSac);
            loadComboBoxKichThuoc(kichThuoc);
            loadComboBoxChatLieu(chatLieu);

        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnSuaSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSpActionPerformed

    }//GEN-LAST:event_btnSuaSpActionPerformed

    private void btnSuaSpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaSpMouseClicked
        try {
            int chonRow = tblSP.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
                return;
            }

            int id = Integer.parseInt(tblSP.getValueAt(chonRow, 0).toString());

            // ================= LẤY DỮ LIỆU TỪ FORM =================
            String tenSanPham = txtTenSP.getText().trim();
            String moTa = txtMoTa.getText().trim();
            String ngayTaoDate = txtNgayTao.getText().trim();
            Boolean trangThai = rdoConSP.isSelected();

            // Lấy dữ liệu từ ComboBox
            String tenMau = cbbMauSac.getSelectedItem().toString().trim();
            String tenKichThuoc = cbbKichThuoc.getSelectedItem().toString().trim();
            String tenChatLieu = cbbChatLieu.getSelectedItem().toString().trim();
            String tenXuatXu = cbbXuatXu.getSelectedItem().toString().trim();
            String tenThuongHieu = cbbThuongHieu.getSelectedItem().toString().trim();

            // Lấy ID từ DB
            int idMauSac = repoMS.getIdMauSac(tenMau);
            int idKichThuoc = repoKT.getIdKichThuoc(tenKichThuoc);
            int idChatLieu = repoCL.getIdChatLieu(tenChatLieu);
            int idXuatXu = repoXX.getIdXuatXu(tenXuatXu);
            int idThuongHieu = repoTH.getIdThuongHieu(tenThuongHieu);

            // Kiểm tra xem có ID nào bị lỗi không
            if (idMauSac == -1 || idKichThuoc == -1 || idChatLieu == -1 || idXuatXu == -1 || idThuongHieu == -1) {
                JOptionPane.showMessageDialog(this, "Một số thông tin danh mục không tồn tại trong hệ thống!");
                return;
            }

            // ================= KIỂM TRA DỮ LIỆU HỢP LỆ =================
            if (tenSanPham.isEmpty() || moTa.isEmpty() || ngayTaoDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm!");
                return;
            }

            int soLuongTon;
            try {
                soLuongTon = Integer.parseInt(txtSoLuong.getText().trim());
                if (soLuongTon < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng  không thể âm!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng  phải là số nguyên!");
                return;
            }

            // Chuyển đổi ngày tạo từ String thành Date
            java.sql.Date ngayTao;
            try {
                ngayTao = java.sql.Date.valueOf(ngayTaoDate);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Ngày tạo không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd.");
                return;
            }

            // Chuyển đổi giá nhập và giá bán sang BigDecimal
            BigDecimal giaNhap, giaBan;
            try {
                giaNhap = new BigDecimal(txtGiaNhap.getText().trim());
                giaBan = new BigDecimal(txtGiaBan.getText().trim());
                if (giaNhap.compareTo(BigDecimal.ZERO) < 0 || giaBan.compareTo(BigDecimal.ZERO) < 0) {
                    JOptionPane.showMessageDialog(this, "Giá nhập và giá bán không được âm!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá nhập và giá bán phải là số!");
                return;
            }

            // ================= TẠO ĐỐI TƯỢNG SẢN PHẨM =================
            SanPhamModel2 sp = new SanPhamModel2(id, tenSanPham, soLuongTon, moTa, ngayTao,
                    giaNhap, giaBan, trangThai, idMauSac, idKichThuoc,
                    idChatLieu, idXuatXu, idThuongHieu);

            // ================= CẬP NHẬT VÀO DATABASE =================
            repoSP.updateSanPham(sp);
            JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");

            // Load lại danh sách sản phẩm
            loadTable();
            xoaForm();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật sản phẩm! Vui lòng kiểm tra lại.");
        }

    }//GEN-LAST:event_btnSuaSpMouseClicked

    private void btnThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseClicked
        try {

            String tenSanPham = txtTenSP.getText().trim();
            String moTa = txtMoTa.getText().trim();
            String ngayTaoDate = txtNgayTao.getText().trim();
            Boolean trangThai = rdoConSP.isSelected();

            // Lấy dữ liệu từ ComboBox
            String tenMau = cbbMauSac.getSelectedItem().toString().trim();
            String tenKichThuoc = cbbKichThuoc.getSelectedItem().toString().trim();
            String tenChatLieu = cbbChatLieu.getSelectedItem().toString().trim();
            String tenXuatXu = cbbXuatXu.getSelectedItem().toString().trim();
            String tenThuongHieu = cbbThuongHieu.getSelectedItem().toString().trim();

            // Lấy ID từ DB
            int idMauSac = repoMS.getIdMauSac(tenMau);
            int idKichThuoc = repoKT.getIdKichThuoc(tenKichThuoc);
            int idChatLieu = repoCL.getIdChatLieu(tenChatLieu);
            int idXuatXu = repoXX.getIdXuatXu(tenXuatXu);
            int idThuongHieu = repoTH.getIdThuongHieu(tenThuongHieu);

            // Kiểm tra xem có ID nào bị lỗi không
            if (idMauSac == -1 || idKichThuoc == -1 || idChatLieu == -1 || idXuatXu == -1 || idThuongHieu == -1) {
                JOptionPane.showMessageDialog(this, "Một số thông tin danh mục không tồn tại trong hệ thống!");
                return;
            }

            // ================= KIỂM TRA DỮ LIỆU HỢP LỆ =================
            if (tenSanPham.isEmpty() || moTa.isEmpty() || ngayTaoDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin sản phẩm!");
                return;
            }

            int soLuongTon;
            try {
                soLuongTon = Integer.parseInt(txtSoLuong.getText().trim());
                if (soLuongTon < 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng  không thể âm!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng  phải là số nguyên!");
                return;
            }

            // Chuyển đổi ngày tạo từ String thành Date
            java.sql.Date ngayTao;
            try {
                ngayTao = java.sql.Date.valueOf(ngayTaoDate);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, "Ngày tạo không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd.");
                return;
            }

            // Chuyển đổi giá nhập và giá bán sang BigDecimal
            BigDecimal giaNhap, giaBan;
            try {
                giaNhap = new BigDecimal(txtGiaNhap.getText().trim());
                giaBan = new BigDecimal(txtGiaBan.getText().trim());
                if (giaNhap.compareTo(BigDecimal.ZERO) < 0 || giaBan.compareTo(BigDecimal.ZERO) < 0) {
                    JOptionPane.showMessageDialog(this, "Giá nhập và giá bán không được âm!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá nhập và giá bán phải là số!");
                return;
            }

            // ================= TẠO ĐỐI TƯỢNG SẢN PHẨM =================
            SanPhamModel2 sp = new SanPhamModel2(tenSanPham, soLuongTon, moTa, ngayTao,
                    giaNhap, giaBan, trangThai, idMauSac, idKichThuoc,
                    idChatLieu, idXuatXu, idThuongHieu);

            // ================= CẬP NHẬT VÀO DATABASE =================
            repoSP.insertSanPham(sp);
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");

            // Load lại danh sách sản phẩm
            loadTable();
            xoaForm();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật sản phẩm! Vui lòng kiểm tra lại.");
        }

    }//GEN-LAST:event_btnThemSPMouseClicked

    private void btnXoaSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPMouseClicked
        try {
            int chonRow = tblSP.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần xóa!");
                return;
            }

            // Kiểm tra ID hợp lệ
            int idNV;
            try {
                idNV = Integer.parseInt(tblSP.getValueAt(chonRow, 0).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: ID sản phẩm không hợp lệ!");
                return;
            }

            // Xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa sản phẩm này?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Xóa nhân viên
            if (repoSP.deleteSanPham(idNV)) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công!");
                loadTable(); // Cập nhật lại bảng
                xoaForm();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại! Vui lòng kiểm tra lại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaSPMouseClicked

    private void btnLamMoiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiSPMouseClicked
        xoaForm();
    }//GEN-LAST:event_btnLamMoiSPMouseClicked

    private void txtTimKiemSpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemSpKeyReleased
        String timKiem = txtTimKiemSp.getText().trim();
        updateloadTable(timKiem);
    }//GEN-LAST:event_txtTimKiemSpKeyReleased

    private void btnSPKhoiPhucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSPKhoiPhucMouseClicked
        if (selectedIdSP == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần khôi phục!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục sản phẩm này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (repoSP.khoiPhuc(selectedIdSP)) {
                JOptionPane.showMessageDialog(this, "Khôi phục sản phẩm thành công!");
                selectedIdSP = -1;
                loadTableNghi();
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục sản phẩm thất bại!");
            }
        }
    }//GEN-LAST:event_btnSPKhoiPhucMouseClicked

    private void txtTimSPKetThucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSPKetThucKeyReleased
        String timKiem = txtTimSPKetThuc.getText().trim();
        updateloadTableNghi(timKiem);
    }//GEN-LAST:event_txtTimSPKetThucKeyReleased

    private void tblSPNgungBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPNgungBanMouseClicked
        int selectedRow = tblSPNgungBan.getSelectedRow();
        if (selectedRow != -1) {
            selectedIdSP = (int) tblSPNgungBan.getValueAt(selectedRow, 0);
        }
    }//GEN-LAST:event_tblSPNgungBanMouseClicked

    private void tbnLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbnLoadMouseClicked
        loadTable();
    }//GEN-LAST:event_tbnLoadMouseClicked

    private void btnLoadNghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLoadNghiMouseClicked
        loadTableNghi();
    }//GEN-LAST:event_btnLoadNghiMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiSP;
    private javax.swing.JButton btnLoadNghi;
    private javax.swing.JButton btnSPKhoiPhuc;
    private javax.swing.JButton btnSuaSp;
    private javax.swing.JButton btnThemChatLieu;
    private javax.swing.JButton btnThemKichThuoc;
    private javax.swing.JButton btnThemMauSac;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnThemThuongHieu;
    private javax.swing.JButton btnThemXuatXu;
    private javax.swing.JButton btnXoaSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbKichThuoc;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbThuongHieu;
    private javax.swing.JComboBox<String> cbbXuatXu;
    private com.toedter.calendar.JDateChooser dateNgayTaoSP;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JRadioButton rdoConSP;
    private javax.swing.JRadioButton rdoHetSP;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblSPNgungBan;
    private javax.swing.JButton tbnLoad;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMoTa;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTimKiemSp;
    private javax.swing.JTextField txtTimSPKetThuc;
    // End of variables declaration//GEN-END:variables
}
