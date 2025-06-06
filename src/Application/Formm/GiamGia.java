/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Application.Formm;

import Model.GiamGiaModel;
import Repository.GiamGiaRepo;
import static com.microsoft.sqlserver.jdbc.StringUtils.isInteger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class GiamGia extends javax.swing.JPanel {

    private GiamGiaRepo repoGG;
    private int selectIDGG = -1;

    public GiamGia() {
        this.repoGG = new Repository.GiamGiaRepo();
        initComponents();
        txtIDGG.setEditable(false);
        txtMaGG.setEditable(false);
        loadTbale();
        loadTbaleNgung();

    }

    public void loadTbale() {
        DefaultTableModel model = (DefaultTableModel) this.tblGG.getModel();
        model.setRowCount(0);
        List<Model.GiamGiaModel> listGG = repoGG.getAllGiamGia();
        for (GiamGiaModel gg : listGG) {
            Object[] rowData = {
                gg.getId(),
                gg.getMaGiamGia(),
                gg.getTenChuongTrinh(),
                gg.getNgayBatDau(),
                gg.getNgayKetThuc(),
                gg.getSoLuong(),
                gg.getKieuGiam() ? "Phần Trăm" : "VND",
                gg.getGiaTriDHToiThieu(),
                gg.getMucGiaGiam(),
                gg.getMucGiaGiamToiDa(),
                gg.getTrangThai() ? "Đang diễn ra" : "Kết Thúc",
                gg.getNgayTao(),};
            model.addRow(rowData);

        }
        rdoPhanTramGG.setSelected(true);
        rdoDangDienRa.setSelected(true);
    }

    public void updateConloadTbale(String keyword) {
        DefaultTableModel model = (DefaultTableModel) this.tblGG.getModel();
        model.setRowCount(0);
        List<Model.GiamGiaModel> listGG = repoGG.searchGiamGiaCon(keyword);
        for (GiamGiaModel gg : listGG) {
            Object[] rowData = {
                gg.getId(),
                gg.getMaGiamGia(),
                gg.getTenChuongTrinh(),
                gg.getNgayBatDau(),
                gg.getNgayKetThuc(),
                gg.getSoLuong(),
                gg.getKieuGiam() ? "Phần Trăm" : "VND",
                gg.getGiaTriDHToiThieu(),
                gg.getMucGiaGiam(),
                gg.getMucGiaGiamToiDa(),
                gg.getTrangThai() ? "Đang diễn ra" : "Kết Thúc",
                gg.getNgayTao(),};
            model.addRow(rowData);

        }
    }

    public void loadTbaleNgung() {
        DefaultTableModel model = (DefaultTableModel) this.tblGGNgungBan.getModel();
        model.setRowCount(0);
        List<Model.GiamGiaModel> listGG = repoGG.getAllGiamGiaNgung();
        for (GiamGiaModel gg : listGG) {
            Object[] rowData = {
                gg.getId(),
                gg.getMaGiamGia(),
                gg.getTenChuongTrinh(),
                gg.getNgayBatDau(),
                gg.getNgayKetThuc(),
                gg.getSoLuong(),
                gg.getKieuGiam() ? "Phần Trăm" : "VND",
                gg.getGiaTriDHToiThieu(),
                gg.getMaGiamGia(),
                gg.getMucGiaGiamToiDa(),
                gg.getTrangThai() ? "Đang diễn ra" : "Kết Thúc",
                gg.getNgayTao(),};
            model.addRow(rowData);

        }
    }

    public void updateHetloadTbale(String keyword) {
        DefaultTableModel model = (DefaultTableModel) this.tblGGNgungBan.getModel();
        model.setRowCount(0);
        List<Model.GiamGiaModel> listGG = repoGG.searchGiamGiaHet(keyword);
        for (GiamGiaModel gg : listGG) {
            Object[] rowData = {
                gg.getId(),
                gg.getMaGiamGia(),
                gg.getTenChuongTrinh(),
                gg.getNgayBatDau(),
                gg.getNgayKetThuc(),
                gg.getSoLuong(),
                gg.getKieuGiam() ? "Phần Trăm" : "VND",
                gg.getGiaTriDHToiThieu(),
                gg.getMucGiaGiam(),
                gg.getMucGiaGiamToiDa(),
                gg.getTrangThai() ? "Đang diễn ra" : "Kết Thúc",
                gg.getNgayTao(),};
            model.addRow(rowData);

        }
    }

    public void xoaFrom() {
        txtIDGG.setText("");
        txtMaGG.setText("");
        txtTenChuongTrinh.setText("");
        txtNgayBatDau.setText("");
        txtNgayKetThuc.setText("");
        txtSL.setText("");
        txtGiaTriDH.setText("");
        rdoPhanTramGG.setSelected(true);
        rdoVNDGG.setSelected(false);
        rdoDangDienRa.setSelected(true);
        rdoNgayKetThuc.setSelected(false);
        txtMucGG.setText("");
        txtMucGGToiDa.setText("");
        txtNgayTaoGG.setText("");
        tblGG.clearSelection();
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTimGG = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMaGG = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTenChuongTrinh = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNgayBatDau = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayKetThuc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdoPhanTramGG = new javax.swing.JRadioButton();
        rdoVNDGG = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        txtMucGG = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMucGGToiDa = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtGiaTriDH = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        rdoNgayKetThuc = new javax.swing.JRadioButton();
        rdoDangDienRa = new javax.swing.JRadioButton();
        btnThemGG = new javax.swing.JButton();
        btnSuaGG = new javax.swing.JButton();
        btnXoaGG = new javax.swing.JButton();
        btnLamMoiGG = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGG = new javax.swing.JTable();
        dateNgayBatDau = new com.toedter.calendar.JDateChooser();
        dateNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnReset = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtNgayTaoGG = new javax.swing.JTextField();
        dateNgayTao = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        txtIDGG = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGGNgungBan = new javax.swing.JTable();
        btnKhoiPhuc = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtGGNgungBan = new javax.swing.JTextField();
        btnResetNgung = new javax.swing.JButton();

        jLabel1.setText("Tìm Kiếm");

        txtTimGG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimGGKeyReleased(evt);
            }
        });

        jLabel2.setText("Mã GG");

        jLabel3.setText("Số Lượng");

        jLabel4.setText("Tên Chương Trình ");

        jLabel5.setText("Ngày Bắt Đầu");

        jLabel6.setText("Ngày Kết Thúc");

        jLabel7.setText("Kiểu GG");

        buttonGroup1.add(rdoPhanTramGG);
        rdoPhanTramGG.setText("Phần Trăm");

        buttonGroup1.add(rdoVNDGG);
        rdoVNDGG.setText("VND");

        jLabel8.setText("Mức GG");

        jLabel9.setText("Mức GG Tối Đa");

        jLabel10.setText("Giá Trị Đơn Hàng");

        jLabel11.setText("Trạng Thái");

        buttonGroup2.add(rdoNgayKetThuc);
        rdoNgayKetThuc.setText("Kết Thúc");
        rdoNgayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNgayKetThucActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoDangDienRa);
        rdoDangDienRa.setText("Đang diễn ra");

        btnThemGG.setText("Thêm");
        btnThemGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemGGMouseClicked(evt);
            }
        });
        btnThemGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemGGActionPerformed(evt);
            }
        });

        btnSuaGG.setText("Sửa");
        btnSuaGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSuaGGMouseClicked(evt);
            }
        });

        btnXoaGG.setText("Xóa");
        btnXoaGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaGGMouseClicked(evt);
            }
        });

        btnLamMoiGG.setText("Làm Mới");
        btnLamMoiGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLamMoiGGMouseClicked(evt);
            }
        });

        tblGG.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã GG", "Tên CTrình", "Ngày BĐầu", "Ngày KThúc", "Số Lượng", "KIểu Giảm", "Giá Trị ĐHàng", "Mức GG", "Mức GG TốiĐa", "Trang Thái", "Ngày Tạo"
            }
        ));
        tblGG.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGGMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGG);

        dateNgayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayBatDauPropertyChange(evt);
            }
        });

        dateNgayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayKetThucPropertyChange(evt);
            }
        });

        btnReset.setText("reset");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        jLabel13.setText("Ngày Tạo");

        dateNgayTao.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateNgayTaoPropertyChange(evt);
            }
        });

        jLabel14.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14)
                                            .addComponent(jLabel8))
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMucGG)
                                    .addComponent(txtSL, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                                    .addComponent(txtMaGG, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtIDGG))
                                .addGap(94, 94, 94)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(337, 337, 337)
                                        .addComponent(txtNgayTaoGG)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dateNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(133, 133, 133))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel10)
                                                            .addComponent(jLabel9))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(txtMucGGToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(16, 16, 16)
                                                        .addComponent(jLabel5)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel13))))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(33, 33, 33)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rdoPhanTramGG)
                                                    .addComponent(txtGiaTriDH, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(rdoVNDGG))
                                                .addGap(6, 6, 6)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jLabel11)
                                                        .addGap(15, 15, 15)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(rdoDangDienRa)
                                                            .addComponent(rdoNgayKetThuc))))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnSuaGG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnLamMoiGG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnXoaGG, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTimGG, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnThemGG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(88, 88, 88))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTimGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(dateNgayTao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnThemGG))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtNgayTaoGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13)
                                .addComponent(txtTenChuongTrinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSuaGG, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMucGGToiDa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(dateNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaGG)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(txtMucGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(txtGiaTriDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLamMoiGG)
                            .addComponent(jLabel11)
                            .addComponent(rdoNgayKetThuc)
                            .addComponent(jLabel7)
                            .addComponent(rdoPhanTramGG))
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoDangDienRa)
                            .addComponent(rdoVNDGG)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIDGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Tất cả", jPanel1);

        tblGGNgungBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Mã GG", "Tên CTrình", "Ngày BĐầu", "Ngày KThúc", "Số Lượng", "KIểu Giảm", "Giá Trị ĐHàng", "Mức GG", "Mức GG TĐa", "Trang Thái"
            }
        ));
        tblGGNgungBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGGNgungBanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGGNgungBan);

        btnKhoiPhuc.setText("Khôi Phục");
        btnKhoiPhuc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnKhoiPhucMouseClicked(evt);
            }
        });

        jLabel12.setText("Tìm Kiếm");

        txtGGNgungBan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtGGNgungBanKeyReleased(evt);
            }
        });

        btnResetNgung.setText("reset");
        btnResetNgung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetNgungMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1013, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGGNgungBan, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnResetNgung)
                        .addGap(46, 46, 46)
                        .addComponent(btnKhoiPhuc)
                        .addGap(189, 189, 189))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKhoiPhuc)
                    .addComponent(jLabel12)
                    .addComponent(txtGGNgungBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetNgung))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(317, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ngừng Bán", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1025, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateNgayBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayBatDauPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgayBatDau.getDate();

            if (selectedDate != null) {
                String ngayBan = sdf.format(selectedDate);
                txtNgayBatDau.setText(ngayBan); // Gán ngày vào JTextField
            } else {
                txtNgayBatDau.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgayBatDauPropertyChange

    private void dateNgayKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayKetThucPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgayKetThuc.getDate();

            if (selectedDate != null) {
                String ngayBan = sdf.format(selectedDate);
                txtNgayKetThuc.setText(ngayBan); // Gán ngày vào JTextField
            } else {
                txtNgayKetThuc.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgayKetThucPropertyChange

    private void rdoNgayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNgayKetThucActionPerformed

    }//GEN-LAST:event_rdoNgayKetThucActionPerformed

    private void dateNgayTaoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateNgayTaoPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateNgayTao.getDate();

            if (selectedDate != null) {
                String ngayBan = sdf.format(selectedDate);
                txtNgayTaoGG.setText(ngayBan); // Gán ngày vào JTextField
            } else {
                txtNgayTaoGG.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateNgayTaoPropertyChange

    private void tblGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGGMouseClicked
        int chonRow = tblGG.getSelectedRow();
        if (chonRow != -1) {
            String idSP = getCellValue(tblGG, chonRow, 0).toString();
            String maSP = getCellValue(tblGG, chonRow, 1).toString();
            String tenSP = getCellValue(tblGG, chonRow, 2).toString();
            String ngayBau = getCellValue(tblGG, chonRow, 3).toString();
            String ngayKetThuc = getCellValue(tblGG, chonRow, 4).toString();
            String soLuong = getCellValue(tblGG, chonRow, 5).toString();
            String kieuGiam = getCellValue(tblGG, chonRow, 6).toString();
            String giaTriDH = getCellValue(tblGG, chonRow, 7).toString();
            String mucGG = getCellValue(tblGG, chonRow, 8).toString();
            String mucGGToiDa = getCellValue(tblGG, chonRow, 9).toString();
            String trangThai = getCellValue(tblGG, chonRow, 10).toString();
            String ngayTao = getCellValue(tblGG, chonRow, 11).toString();

            txtIDGG.setText(idSP);
            txtMaGG.setText(maSP);
            txtTenChuongTrinh.setText(tenSP);
            txtNgayBatDau.setText(ngayBau);
            txtNgayKetThuc.setText(ngayKetThuc);
            txtSL.setText(soLuong);
            txtGiaTriDH.setText(giaTriDH);
            txtMucGG.setText(mucGG);
            txtMucGGToiDa.setText(mucGGToiDa);
            txtNgayTaoGG.setText(ngayTao);

            rdoPhanTramGG.setSelected(kieuGiam.equalsIgnoreCase("Phần trăm"));
            rdoVNDGG.setSelected(kieuGiam.equalsIgnoreCase("VND"));
            rdoDangDienRa.setSelected(trangThai.equalsIgnoreCase("Đang diễn ra"));
            rdoNgayKetThuc.setSelected(trangThai.equalsIgnoreCase("Kết Thúc"));

        }
    }//GEN-LAST:event_tblGGMouseClicked

    private void btnLamMoiGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLamMoiGGMouseClicked
        xoaFrom();
    }//GEN-LAST:event_btnLamMoiGGMouseClicked

    private void btnThemGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemGGActionPerformed
    private boolean validateInput() {
        StringBuilder errorMsg = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        String tenChuongTrinh = txtTenChuongTrinh.getText().trim();
        if (tenChuongTrinh.isEmpty()) {
            errorMsg.append("Tên chương trình không được để trống.\n");
        } else if (!isValidText(tenChuongTrinh)) {
            errorMsg.append("Tên chương trình không được chứa ký tự đặc biệt.\n");
        }

        String soLuongStr = txtSL.getText().trim();
        if (soLuongStr.isEmpty() || !soLuongStr.matches("\\d+")) {
            errorMsg.append("Số lượng phải là số nguyên hợp lệ.\n");
        } else {
            int soLuong = Integer.parseInt(soLuongStr);
            if (soLuong <= 0) {
                errorMsg.append("Số lượng phải lớn hơn 0.\n");
            }
        }

        String ngayBatDau = txtNgayBatDau.getText().trim();
        String ngayKetThuc = txtNgayKetThuc.getText().trim();
        String ngayTao = txtNgayTaoGG.getText().trim();

        try {
            Date dateBatDau = sdf.parse(ngayBatDau);
            Date dateKetThuc = sdf.parse(ngayKetThuc);
            Date dateTao = sdf.parse(ngayTao);

            if (dateKetThuc.before(dateBatDau)) {
                errorMsg.append("Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu.\n");
            }
            if (dateTao.after(dateBatDau)) {
                errorMsg.append("Ngày tạo không thể sau ngày bắt đầu.\n");
            }
        } catch (ParseException e) {
            errorMsg.append("Định dạng ngày không hợp lệ (yyyy-MM-dd).\n");
        }

        try {
            BigDecimal giaTriDHToiThieu = new BigDecimal(txtGiaTriDH.getText().trim());
            BigDecimal mucGiaGiam = new BigDecimal(txtMucGG.getText().trim());
            BigDecimal mucGiaGiamToiDa = new BigDecimal(txtMucGGToiDa.getText().trim());

            if (giaTriDHToiThieu.compareTo(BigDecimal.ZERO) < 0) {
                errorMsg.append("Giá trị đơn hàng tối thiểu không được âm.\n");
            }
            if (mucGiaGiam.compareTo(BigDecimal.ZERO) < 0) {
                errorMsg.append("Mức giá giảm không được âm.\n");
            }
            if (mucGiaGiamToiDa.compareTo(BigDecimal.ZERO) < 0) {
                errorMsg.append("Mức giá giảm tối đa không được âm.\n");
            }
            if (mucGiaGiam.compareTo(giaTriDHToiThieu) > 0) {
                errorMsg.append("Mức giá giảm không thể lớn hơn giá trị đơn hàng tối thiểu.\n");
            }
            if (mucGiaGiamToiDa.compareTo(giaTriDHToiThieu) > 0) {
                errorMsg.append("Mức giá giảm tối đa không thể lớn hơn giá trị đơn hàng tối thiểu.\n");
            }
            if (mucGiaGiam.compareTo(mucGiaGiamToiDa) > 0) {
                errorMsg.append("Mức giá giảm không thể lớn hơn Mức giảm giá tôi đa.\n");
            }
        } catch (NumberFormatException e) {
            errorMsg.append("Các giá trị tiền phải là số hợp lệ.\n");
        }

        if (errorMsg.length() > 0) {
            JOptionPane.showMessageDialog(this, errorMsg.toString(), "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private boolean isValidText(String text) {
        return text.matches("^[a-zA-Z0-9À-ỹ]+( [a-zA-Z0-9À-ỹ]+)*$");
    }

    private void btnThemGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemGGMouseClicked
        if (!validateInput()) {
            return;
        }
        try {
            // Lấy dữ liệu từ JTextField và chuyển đổi sang kiểu phù hợp
            String tenChuongTrinh = txtTenChuongTrinh.getText().trim();

            Integer soLuong = Integer.parseInt(txtSL.getText().trim());
            Boolean kieuGiam = rdoPhanTramGG.isSelected();
            BigDecimal giaTriDHToiThieu = new BigDecimal(txtGiaTriDH.getText().trim());
            BigDecimal mucGiaGiam = new BigDecimal(txtMucGG.getText().trim());
            BigDecimal mucGiaGiamToiDa = new BigDecimal(txtMucGGToiDa.getText().trim());
            Boolean trangThai = rdoDangDienRa.isSelected(); // Sửa lỗi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date ngayBatDau = new java.sql.Date(sdf.parse(txtNgayBatDau.getText().trim()).getTime());
            java.sql.Date ngayKetThuc = new java.sql.Date(sdf.parse(txtNgayKetThuc.getText().trim()).getTime());
            java.sql.Date ngayTao = new java.sql.Date(sdf.parse(txtNgayTaoGG.getText().trim()).getTime());

            GiamGiaModel gg = new GiamGiaModel(tenChuongTrinh, ngayTao, ngayBatDau, ngayKetThuc, soLuong, kieuGiam, giaTriDHToiThieu, mucGiaGiam, mucGiaGiamToiDa, trangThai);
            repoGG.insertGiamGia(gg);
            loadTbale();
            xoaFrom();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThemGGMouseClicked

    private void btnSuaGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSuaGGMouseClicked
        if (!validateInput()) {
            return;
        }
        try {
            int chonRow = tblGG.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giảm giá cần sửa!");
                return;
            }
            int id = Integer.parseInt(tblGG.getValueAt(chonRow, 0).toString());
            String maGiamGia = txtMaGG.getText().trim();

            String tenChuongTrinh = txtTenChuongTrinh.getText().trim();
            Integer soLuong = Integer.parseInt(txtSL.getText().trim());
            Boolean kieuGiam = rdoPhanTramGG.isSelected();
            BigDecimal giaTriDHToiThieu = new BigDecimal(txtGiaTriDH.getText().trim());
            BigDecimal mucGiaGiam = new BigDecimal(txtMucGG.getText().trim());
            BigDecimal mucGiaGiamToiDa = new BigDecimal(txtMucGGToiDa.getText().trim());
            Boolean trangThai = rdoDangDienRa.isSelected(); // Sửa lỗi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date ngayBatDau = new java.sql.Date(sdf.parse(txtNgayBatDau.getText().trim()).getTime());
            java.sql.Date ngayKetThuc = new java.sql.Date(sdf.parse(txtNgayKetThuc.getText().trim()).getTime());
            java.sql.Date ngayTao = new java.sql.Date(sdf.parse(txtNgayTaoGG.getText().trim()).getTime());
            GiamGiaModel gg = new GiamGiaModel(id, maGiamGia, tenChuongTrinh, maGiamGia, ngayTao, ngayBatDau, ngayKetThuc, soLuong, kieuGiam, giaTriDHToiThieu, mucGiaGiam, mucGiaGiamToiDa, trangThai);
            repoGG.updateGiamGia(gg);
            loadTbale();
            xoaFrom();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSuaGGMouseClicked

    private void btnXoaGGMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaGGMouseClicked
        try {
            int chonRow = tblGG.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn giảm giá cần xóa!");
                return;
            }

            // Kiểm tra ID hợp lệ
            int idGG;
            try {
                idGG = Integer.parseInt(tblGG.getValueAt(chonRow, 0).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: ID giảm giá không hợp lệ!");
                return;
            }

            // Xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa giảm giá này?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Xóa nhân viên
            if (repoGG.deleteGiamGia(idGG)) {
                JOptionPane.showMessageDialog(this, "Xóa giảm giá thành công!");
                loadTbale(); // Cập nhật lại bảng
                xoaFrom();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa giảm giá thất bại! Vui lòng kiểm tra lại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnXoaGGMouseClicked

    private void txtTimGGKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimGGKeyReleased
        String timKiem = txtTimGG.getText().trim();
        updateConloadTbale(timKiem);
    }//GEN-LAST:event_txtTimGGKeyReleased

    private void txtGGNgungBanKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGGNgungBanKeyReleased
        String timKiem = txtGGNgungBan.getText().trim();
        updateHetloadTbale(timKiem);
    }//GEN-LAST:event_txtGGNgungBanKeyReleased

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        loadTbale();
    }//GEN-LAST:event_btnResetMouseClicked

    private void btnResetNgungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetNgungMouseClicked
        loadTbaleNgung();
    }//GEN-LAST:event_btnResetNgungMouseClicked

    private void btnKhoiPhucMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhoiPhucMouseClicked
        if (selectIDGG == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn giảm giá cần khôi phục!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn khôi phục giảm giá này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (repoGG.khôiPhucGiamGia(selectIDGG)) {
                JOptionPane.showMessageDialog(this, "Khôi phục giảm giá thành công!");
                selectIDGG = -1;
                loadTbaleNgung();
            } else {
                JOptionPane.showMessageDialog(this, "Khôi phục giảm giá thất bại!");
            }
        }
    }//GEN-LAST:event_btnKhoiPhucMouseClicked

    private void tblGGNgungBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGGNgungBanMouseClicked
        int selectedRow = tblGGNgungBan.getSelectedRow();
        if (selectedRow != -1) {
            selectIDGG = (int) tblGGNgungBan.getValueAt(selectedRow, 0); // Lấy ID từ cột đầu tiên
        }
    }//GEN-LAST:event_tblGGNgungBanMouseClicked
    private String getCellValue(JTable table, int row, int col) {
        Object value = table.getValueAt(row, col);
        return (value != null) ? value.toString() : "";
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKhoiPhuc;
    private javax.swing.JButton btnLamMoiGG;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnResetNgung;
    private javax.swing.JButton btnSuaGG;
    private javax.swing.JButton btnThemGG;
    private javax.swing.JButton btnXoaGG;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.toedter.calendar.JDateChooser dateNgayBatDau;
    private com.toedter.calendar.JDateChooser dateNgayKetThuc;
    private com.toedter.calendar.JDateChooser dateNgayTao;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdoDangDienRa;
    private javax.swing.JRadioButton rdoNgayKetThuc;
    private javax.swing.JRadioButton rdoPhanTramGG;
    private javax.swing.JRadioButton rdoVNDGG;
    private javax.swing.JTable tblGG;
    private javax.swing.JTable tblGGNgungBan;
    private javax.swing.JTextField txtGGNgungBan;
    private javax.swing.JTextField txtGiaTriDH;
    private javax.swing.JTextField txtIDGG;
    private javax.swing.JTextField txtMaGG;
    private javax.swing.JTextField txtMucGG;
    private javax.swing.JTextField txtMucGGToiDa;
    private javax.swing.JTextField txtNgayBatDau;
    private javax.swing.JTextField txtNgayKetThuc;
    private javax.swing.JTextField txtNgayTaoGG;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenChuongTrinh;
    private javax.swing.JTextField txtTimGG;
    // End of variables declaration//GEN-END:variables
}
