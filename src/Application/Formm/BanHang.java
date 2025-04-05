package Application.Formm;

import Model.GioHang;
import Model.HoaDonChiTiet;
import Model.HoaDonModel;
import Model.KhachHangModel;
import Model.NhanVienModel;
import Model.PhuongThucThanhToan;
import Model.SanPhamModel;
import Repository.GioHangRepo;
import Repository.HoaDonRepo;
import Repository.KhachHangRepo;
import Repository.NhanVienRepo;
import Repository.PhuongThucThanhToanRepo;
import Repository.SanPhamRepo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class BanHang extends javax.swing.JPanel {

    private final HoaDonRepo hoaDonRepo;
    private final KhachHangRepo khachHangRepo;
    private final SanPhamRepo sanPhamRepo;
    private NhanVienRepo repoNhanVien;
    private DefaultTableModel modelGioHang;
    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelHoaDon;
    private GioHangRepo repoGio;
    private PhuongThucThanhToanRepo repoPTTT;
    private HoaDon currentHoaDon;
    private String maNV;
    private String maKH;
    private String pttt;

    /**
     * Creates new form BanHang
     */
    public BanHang() {
        initComponents();
        hoaDonRepo = new HoaDonRepo();
        khachHangRepo = new KhachHangRepo();
        sanPhamRepo = new SanPhamRepo();
        repoGio = new GioHangRepo();
        repoNhanVien = new NhanVienRepo();
        repoPTTT=new PhuongThucThanhToanRepo();
        txttongtien.setEditable(false);
        loadSanPham();
        loadGioHang();
        loadHoaDon();
        loadComboBoxNhanVien(maNV);
        loadComboBoxKhachHang(maKH);
        loadComboBoxPhuongTTT(pttt);
    }

    private void loadSanPham() {
        modelSanPham = (DefaultTableModel) tblDSSanPham.getModel();
        modelSanPham.setRowCount(0);
        List<Model.SanPhamModel> danhSachSanPham = sanPhamRepo.getAllSanPham();
        for (SanPhamModel sp : danhSachSanPham) {
            Object[] rowData = {
                sp.getId(),
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getSoLuongTon(),
                sp.getGiaBan(),
                sp.getMauSac(),
                sp.getThuongHieu(),
                sp.getXuatXu(),
                sp.getChatLieu(),
                sp.getKichThuoc()
            };
            modelSanPham.addRow(rowData);
        }
    }

    private void loadGioHang() {
        modelGioHang = (DefaultTableModel) tblGioHoaDon.getModel();
        modelGioHang.setRowCount(0);
        List<Model.GioHang> giohang = repoGio.getAll();
        for (Model.GioHang gh : giohang) {
            Object[] rowData = {
                gh.getIdGH(),
                gh.getMaSP(),
                gh.getTenSP(),
                gh.getSoLuong(),
                gh.getDonGia()

            };
            modelGioHang.addRow(rowData);
        }
    }

    private void loadHoaDon() {
        modelHoaDon = (DefaultTableModel) tbhd.getModel();
        modelHoaDon.setRowCount(0);
        List<Model.HoaDonModel> hoadon = hoaDonRepo.getAllHoaDon();
        for (HoaDonModel hd : hoadon) {
            Object[] rowData = {
                hd.getMaHoaDon(),
                hd.getMaNhanVien(),
                hd.getMaKhachHang(),
                hd.getNgayTao(),
                hd.getTongGia()

            };
            modelHoaDon.addRow(rowData);
        }

    }

    private void loadComboBoxNhanVien(String maNhanVien) {
        cbbNhanVien.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.NhanVienModel> listNV = repoNhanVien.getAllNhanVien();

        for (NhanVienModel cl : listNV) {
            cbbNhanVien.addItem(cl.getMaNV()); // Thêm từng chất liệu vào ComboBox
        }
        if (maNhanVien == null && !listNV.isEmpty()) {
            cbbNhanVien.setSelectedIndex(0);
        } else {
            cbbNhanVien.setSelectedItem(maNhanVien);
        }
    }

    private void loadComboBoxKhachHang(String maKhachHang) {
        cbbKhachHang.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.KhachHangModel> listKH = khachHangRepo.getAllKhachHang();

        for (KhachHangModel cl : listKH) {
            cbbKhachHang.addItem(cl.getMaKH()); // Thêm từng chất liệu vào ComboBox
        }
        if (maKhachHang == null && !listKH.isEmpty()) {
            cbbKhachHang.setSelectedIndex(0);
        } else {
            cbbKhachHang.setSelectedItem(maKhachHang);
        }
    }
     private void loadComboBoxPhuongTTT(String loaiThanhToan) {
        cbbChonHinhThuc.removeAllItems(); // Xóa hết dữ liệu cũ
        List<Model.PhuongThucThanhToan> listKH = repoPTTT.getAllPhuongTTT();

        for (PhuongThucThanhToan cl : listKH) {
            cbbChonHinhThuc.addItem(cl.getLoaiPhuongThuc()); // Thêm từng chất liệu vào ComboBox
        }
        if (loaiThanhToan == null && !listKH.isEmpty()) {
            cbbChonHinhThuc.setSelectedIndex(0);
        } else {
            cbbChonHinhThuc.setSelectedItem(loaiThanhToan);
        }
    }

    private void timKiemSanPham(String tenSP) {
        modelSanPham = (DefaultTableModel) tblDSSanPham.getModel();
        modelSanPham.setRowCount(0);
        List<Model.SanPhamModel> danhSachSanPham = sanPhamRepo.searchSanPhamBan(tenSP);
        for (SanPhamModel sp : danhSachSanPham) {
            Object[] rowData = {
                sp.getId(),
                sp.getMaSanPham(),
                sp.getTenSanPham(),
                sp.getSoLuongTon(),
                sp.getGiaBan(),
                sp.getMauSac(),
                sp.getThuongHieu(),
                sp.getXuatXu(),
                sp.getChatLieu(),
                sp.getKichThuoc()
            };
            modelSanPham.addRow(rowData);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGioHoaDon = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDSSanPham = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTimSP = new javax.swing.JTextField();
        btnXoaSPGH = new javax.swing.JButton();
        btnThemSP = new javax.swing.JButton();
        btnTaoHoaDon = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbbKhachHang = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbbNhanVien = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbbChonHinhThuc = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtTienKhachTra = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNgayThanhToan = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        Btlammoi = new javax.swing.JButton();
        bthuy = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btxoa = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbhd = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtMaGG = new javax.swing.JTextField();

        jLabel1.setText("Giỏ hàng");

        tblGioHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số Lượng", "Giá Bán"
            }
        ));
        jScrollPane1.setViewportView(tblGioHoaDon);

        jLabel2.setText("Danh Sách Sản Phẩm");

        tblDSSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Ma SP", "Ten SP", "So Luong", "Gia Ban", "Mau Sac", "Thuong Hieu", "Xuat Xu", "Chat Lieu", "Kich Thuoc"
            }
        ));
        tblDSSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDSSanPhamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDSSanPham);

        jLabel3.setText("Tìm Kiếm");

        txtTimSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimSPKeyReleased(evt);
            }
        });

        btnXoaSPGH.setText("Xoa SPGH");
        btnXoaSPGH.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaSPGHMouseClicked(evt);
            }
        });
        btnXoaSPGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaSPGHActionPerformed(evt);
            }
        });

        btnThemSP.setText("Thêm SP");
        btnThemSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemSPMouseClicked(evt);
            }
        });
        btnThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSPActionPerformed(evt);
            }
        });

        btnTaoHoaDon.setText("Tạo Hóa Đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        jLabel4.setText("Khách Hàng");

        jLabel5.setText("Nhân Viên");

        jLabel6.setText("Địa Chỉ");

        jLabel7.setText("SDT");

        jLabel9.setText("Mã GG");

        jLabel10.setText("Phương thức thanh toán");

        jLabel11.setText("Tiền Khách Trả");

        jLabel12.setText("Số Lượng");

        jLabel13.setText("Ngày Bán");

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        dateChooser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateChooserMouseClicked(evt);
            }
        });
        dateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooserPropertyChange(evt);
            }
        });

        Btlammoi.setText("Làm mới");
        Btlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtlammoiActionPerformed(evt);
            }
        });

        bthuy.setText("Huỷ");
        bthuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthuyActionPerformed(evt);
            }
        });

        jButton1.setText("Thêm Khách hàng");

        btxoa.setText("Xoá");

        jLabel14.setText("Tổng tiền SP");

        tbhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Nhân viên ", "Khách hàng", "Ngày tạo", "Tổng tiền"
            }
        ));
        jScrollPane3.setViewportView(tbhd);

        jLabel16.setText("Hoá đơn");

        txtMaGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaGGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(btnXoaSPGH)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnThemSP))
                                    .addComponent(jLabel1)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbbChonHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Btlammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btxoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnThanhToan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bthuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButton1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                            .addComponent(txtMaGG, javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                            .addComponent(txtNgayThanhToan)
                                                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel9)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel16)
                        .addGap(334, 334, 334)
                        .addComponent(btnTaoHoaDon)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel6))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLabel8))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel13)
                                        .addComponent(txtNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaSPGH)
                    .addComponent(btnThemSP)
                    .addComponent(jLabel10)
                    .addComponent(cbbChonHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btxoa)
                            .addComponent(bthuy))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btlammoi)
                            .addComponent(btnThanhToan))
                        .addContainerGap())
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateChooserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooserMouseClicked

    }//GEN-LAST:event_dateChooserMouseClicked

    private void dateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateChooser.getDate();

            if (selectedDate != null) {
                String ngayBan = sdf.format(selectedDate);
                txtNgayThanhToan.setText(ngayBan); // Gán ngày vào JTextField
            } else {
                txtNgayThanhToan.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateChooserPropertyChange

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

        if (currentHoaDon == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn trước khi thanh toán!");
            return;
        }
        currentHoaDon.setTrangThai("Đã thanh toán");
        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
        loadGioHang();

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed
//       

    }//GEN-LAST:event_btnThemSPActionPerformed

    private void BtlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtlammoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtlammoiActionPerformed

    private void bthuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bthuyActionPerformed

    }//GEN-LAST:event_bthuyActionPerformed

    private void btnXoaSPGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaSPGHActionPerformed

    }//GEN-LAST:event_btnXoaSPGHActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed

    }//GEN-LAST:event_btnTaoHoaDonActionPerformed

    private void tblDSSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDSSanPhamMouseClicked

    }//GEN-LAST:event_tblDSSanPhamMouseClicked

    private void btnThemSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemSPMouseClicked
        try {
            int selectedRow = tblDSSanPham.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần sửa!");
                return;
            }

            int idSP = Integer.parseInt(tblDSSanPham.getValueAt(selectedRow, 0).toString());

            if (idSP == 0) {
                JOptionPane.showMessageDialog(this, "ID sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String maSP = tblDSSanPham.getValueAt(selectedRow, 1).toString();
            String tenSP = tblDSSanPham.getValueAt(selectedRow, 2).toString();

            if (maSP == null || maSP.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra sự tồn tại của sản phẩm trong cơ sở dữ liệu
            Object soLuongObj = tblDSSanPham.getValueAt(selectedRow, 3);
            Object donGiaObj = tblDSSanPham.getValueAt(selectedRow, 4);

            int soLuongTonKho;
            try {
                soLuongTonKho = Integer.parseInt(soLuongObj.toString().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Số lượng tồn kho không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BigDecimal donGia;
            try {
                donGia = new BigDecimal(donGiaObj.toString().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Đơn giá sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String soLuongNhapStr = JOptionPane.showInputDialog(this, "Nhập số lượng cần mua:", "Nhập số lượng", JOptionPane.QUESTION_MESSAGE);

            if (soLuongNhapStr == null || soLuongNhapStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Số lượng không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int soLuongNhap;
            try {
                soLuongNhap = Integer.parseInt(soLuongNhapStr.trim());
                if (soLuongNhap <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (soLuongNhap > soLuongTonKho) {
                JOptionPane.showMessageDialog(this, "Số lượng nhập vượt quá số lượng tồn kho!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int soNew = soLuongTonKho - soLuongNhap;
            sanPhamRepo.updateSoLuongGioHang(idSP, soNew);
            loadSanPham();

            GioHang gh = new GioHang(idSP, maSP, tenSP, soLuongNhap, donGia);
            boolean result = repoGio.addGio(gh);
            loadGioHang();

            if (result) {
                JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm vào giỏ hàng!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi trong quá trình xử lý!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_btnThemSPMouseClicked

    private void btnXoaSPGHMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaSPGHMouseClicked
        try {
            int chonRow = tblGioHoaDon.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng cần xóa!");
                return;
            }

            // Kiểm tra ID hợp lệ
            int idNV;
            try {
                idNV = Integer.parseInt(tblGioHoaDon.getValueAt(chonRow, 0).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: ID sản phẩm trong giỏ hàng không hợp lệ!");
                return;
            }

            // Xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa sản phẩm trong hàng này?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Xóa nhân viên
            if (repoGio.deleteGioHang(idNV)) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm trong giỏ hàng thành công!");
                loadGioHang(); // Cập nhật lại bảng
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm trong giỏ hàng thất bại! Vui lòng kiểm tra lại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaSPGHMouseClicked

    private void txtTimSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimSPKeyReleased
        String timKiem = txtTimSP.getText().trim();
        timKiemSanPham(timKiem);
    }//GEN-LAST:event_txtTimSPKeyReleased

    private void txtMaGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaGGActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btlammoi;
    private javax.swing.JButton bthuy;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaSPGH;
    private javax.swing.JButton btxoa;
    private javax.swing.JComboBox<String> cbbChonHinhThuc;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tbhd;
    private javax.swing.JTable tblDSSanPham;
    private javax.swing.JTable tblGioHoaDon;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaGG;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables

}
