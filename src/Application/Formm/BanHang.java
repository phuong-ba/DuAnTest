package Application.Formm;

import Model.GiamGiaModel;
import Model.GioHang;
import Model.HoaDonChiTiet;
import Model.HoaDonModel;
import Model.KhachHangModel;
import Model.NhanVienModel;
import Model.PhuongThucThanhToan;
import Model.SanPhamModel;
import Repository.GiamGiaRepo;
import Repository.GioHangRepo;
import Repository.HoaDonRepo;
import Repository.KhachHangRepo;
import Repository.NhanVienRepo;
import Repository.PhuongThucThanhToanRepo;
import Repository.SanPhamRepo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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
    private KhachHangRepo repoKH;
    private DefaultTableModel modelGioHang;
    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelHoaDon;
    private GioHangRepo repoGio;
    private GiamGiaRepo repoGG;
    private PhuongThucThanhToanRepo repoPTTT;
    private HoaDon currentHoaDon;
    private String maNV;
    private String maKH;
    private String pttt;
    private String maGG;

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
        repoPTTT = new PhuongThucThanhToanRepo();
        repoGG = new GiamGiaRepo();
        repoKH = new KhachHangRepo();
        txttongtien.setEditable(false);
        txtTienGiam.setEditable(false);
        txtThanhTien.setEditable(false);
        txtTienThua.setEditable(false);
        txtMaHoaDon.setEditable(false);

        loadSanPham();
        loadGioHang();
        loadHoaDon();
        loadComboBoxNhanVien(maNV);
        loadComboBoxKhachHang(maKH);
        loadComboBoxPhuongTTT(pttt);
        loadComboBoxGG(maGG);
        cbbGG.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    capNhatTienGiamVaThanhTien();
                }
            }
        });// xử lý thành tiền 
        txtTienKhachTra.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                tinhTienThuaTuDong();
            }

            public void removeUpdate(DocumentEvent e) {
                tinhTienThuaTuDong();
            }

            public void insertUpdate(DocumentEvent e) {
                tinhTienThuaTuDong();
            }
        });// xử lý tiền thừa và sự thay đổi khi nhập tiền 

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
                gh.getDonGia(),
                gh.getTongTienSP()

            };
            modelGioHang.addRow(rowData);
        }
    }

    private void loadHoaDon() {
        modelHoaDon = (DefaultTableModel) tbhd.getModel();
        modelHoaDon.setRowCount(0);
        List<Model.HoaDonModel> hoadon = hoaDonRepo.getAllHoaDon0();
        for (HoaDonModel hd : hoadon) {
            Object[] rowData = {
                hd.getMaHoaDon(),
                hd.getMaNhanVien(),
                hd.getMaKhachHang(),
                hd.getNgayThanhToan(),
                hd.getThanhTien(),
                hd.getTrangThai() ? "Đã Thanh Toán" : "Chưa Thanh Toán",
                hd.getDiaChi(),
                hd.getSdt(),
                hd.getTongGia(),};

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
        List<KhachHangModel> listKH = khachHangRepo.getAllKhachHang();

        // Thêm đối tượng KhachHangModel vào ComboBox
        for (KhachHangModel kh : listKH) {
            cbbKhachHang.addItem(kh.getMaKH());
        }

        // Đặt item được chọn mặc định
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

    private void loadComboBoxGG(String maGG) {
        cbbGG.removeAllItems(); // Xóa hết dữ liệu cũ
        List<GiamGiaModel> listGG = repoGG.getAllGiamGia();

        for (GiamGiaModel gg : listGG) {
            cbbGG.addItem(gg.getMaGiamGia());
        }

        if (maGG == null && !listGG.isEmpty()) {
            cbbGG.setSelectedIndex(0);
        } else {
            cbbGG.setSelectedItem(maGG);
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

    private void xoaFrom() {
        txtDiaChi.setText("");
        txtNgayThanhToan.setText("");
        txtSDT.setText("");
        tblGioHoaDon.clearSelection();
        txtThanhTien.setText("");
        txtTienGiam.setText("");
        txttongtien.setText("");
        txtTienKhachTra.setText("");
        txtTienThua.setText("");
        txtSoLuong.setText("");

    }

    public String kiemTraThongBaoGiamGia(GiamGiaModel gg, BigDecimal tongTienSP) {
        LocalDate today = LocalDate.now();

        if (gg == null) {
            return "Không tìm thấy mã giảm giá.";
        }

        if (gg.getNgayBatDau().toLocalDate().isAfter(today)) {
            return "Mã giảm giá này chưa được áp dụng.";
        }

        if (gg.getNgayKetThuc().toLocalDate().isBefore(today)) {
            return "Mã giảm giá này đã hết hạn.";
        }

        if (!gg.getTrangThai()) {
            return "Mã giảm giá hiện đang ngừng áp dụng.";
        }

        if (gg.getSoLuong() <= 0) {
            return "Mã giảm giá đã được sử dụng hết.";
        }

        if (tongTienSP.compareTo(gg.getGiaTriDHToiThieu()) < 0) {
            return "Đơn hàng chưa đủ điều kiện để áp dụng mã giảm giá.";
        }

        return "Hợp lệ";
    }

    public BigDecimal tinhTienGiam(BigDecimal tongTien, GiamGiaModel gg) {
        BigDecimal tienGiam;

        if (gg.getKieuGiam()) {
            BigDecimal phanTram = gg.getMucGiaGiam().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

            tienGiam = tongTien.multiply(phanTram);

            if (tienGiam.compareTo(gg.getMucGiaGiamToiDa()) >= 0) {
                tienGiam = gg.getMucGiaGiamToiDa();
            }
        } else {
            tienGiam = gg.getMucGiaGiam();
        }

        return tienGiam;
    }

    public void capNhatTienGiamVaThanhTien() {
        try {
            String tongTienText = txttongtien.getText().trim();
            if (tongTienText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng để thanh toán");
                return;
            }

            BigDecimal tongTienSP = new BigDecimal(tongTienText);
            BigDecimal tienGiam = BigDecimal.ZERO;

            String maGG = cbbGG.getSelectedItem() != null ? cbbGG.getSelectedItem().toString() : "";
            GiamGiaModel giamGia = repoGG.findMaGG(maGG);

            if (giamGia == null || maGG.isBlank()) {
                // k có mã giảm giá được chọn trả về 0
                tienGiam = BigDecimal.ZERO;
            } else {
                String ketQua = kiemTraThongBaoGiamGia(giamGia, tongTienSP);
                if (!ketQua.equals("Hợp lệ")) {
                    // trả về 0 nếu k có mã nào dc app
                    tienGiam = BigDecimal.ZERO;
                    JOptionPane.showMessageDialog(this, ketQua);
                } else {
                    tienGiam = tinhTienGiam(tongTienSP, giamGia);
                }
            }

            BigDecimal tongTienSauGiam = tongTienSP.subtract(tienGiam);

            txtTienGiam.setText(tienGiam.toString());
            txtThanhTien.setText(tongTienSauGiam.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tính tiền: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void xuLyThanhToan() {
        try {
            BigDecimal thanhTien = new BigDecimal(txtThanhTien.getText().trim());
            BigDecimal tienKhachTra = new BigDecimal(txtTienKhachTra.getText().trim());

            if (tienKhachTra.compareTo(thanhTien) < 0) {
                JOptionPane.showMessageDialog(this, "Tiền khách trả chưa đủ!");
                txtTienThua.setText("0");
                return;
            }

            BigDecimal tienThua = tienKhachTra.subtract(thanhTien).setScale(0, RoundingMode.HALF_UP);
            txtTienThua.setText(tienThua.toString());

            JOptionPane.showMessageDialog(this, "Thanh toán thành công!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thanh toán: " + e.getMessage());
        }
    }

    private void tinhTienThuaTuDong() {
        try {
            BigDecimal thanhTien = new BigDecimal(txtThanhTien.getText().trim());
            BigDecimal tienKhachTra = new BigDecimal(txtTienKhachTra.getText().trim());

            if (tienKhachTra.compareTo(thanhTien) >= 0) {
                BigDecimal tienThua = tienKhachTra.subtract(thanhTien).setScale(2, RoundingMode.HALF_UP);
                txtTienThua.setText(tienThua.toString());
            } else {
                txtTienThua.setText("0");
            }
        } catch (Exception e) {
            txtTienThua.setText("0");
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
        jLabel14 = new javax.swing.JLabel();
        txttongtien = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbhd = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtThanhTien = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JTextField();
        cbbGG = new javax.swing.JComboBox<>();
        txtTienGiam = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();

        jLabel1.setText("Giỏ hàng");

        tblGioHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên SP", "Số Lượng", "Đơn Giá", "Tổng Tiền SP"
            }
        ));
        tblGioHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHoaDonMouseClicked(evt);
            }
        });
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
        btnTaoHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTaoHoaDonMouseClicked(evt);
            }
        });
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

        txtTienKhachTra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachTraActionPerformed(evt);
            }
        });

        jLabel12.setText("Số Lượng");

        jLabel13.setText("Ngày Tạo");

        btnThanhToan.setText("Thanh Toán");
        btnThanhToan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThanhToanMouseClicked(evt);
            }
        });
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
        Btlammoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtlammoiMouseClicked(evt);
            }
        });
        Btlammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtlammoiActionPerformed(evt);
            }
        });

        bthuy.setText("Hủy HD");
        bthuy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthuyMouseClicked(evt);
            }
        });
        bthuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bthuyActionPerformed(evt);
            }
        });

        jLabel14.setText("Tổng tiền SP");

        txttongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtienActionPerformed(evt);
            }
        });

        tbhd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã HD", "Nhân viên ", "Khách hàng", "Ngày tạo", "Thành Tiền", "Trạng Thái", "Địa chỉ", "SDT", "Tổng giá SP"
            }
        ));
        tbhd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbhdMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbhd);

        jLabel16.setText("Hoá đơn");

        jLabel15.setText("Thành tiền");

        txtThanhTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtThanhTienActionPerformed(evt);
            }
        });

        jLabel17.setText("Tiền Thừa");

        txtTienThua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienThuaActionPerformed(evt);
            }
        });

        jLabel18.setText("Tiền Giảm");

        txtMaHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHoaDonActionPerformed(evt);
            }
        });

        jLabel19.setText("Mã Hóa Đơn");

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
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTimSP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(btnXoaSPGH)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnThemSP)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(Btlammoi)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThanhToan))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
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
                                                    .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(6, 6, 6)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                                                    .addComponent(txtNgayThanhToan)
                                                                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addComponent(cbbGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                    .addComponent(cbbNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel9)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel10)
                                                .addGap(18, 18, 18)
                                                .addComponent(cbbChonHinhThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addComponent(jLabel15)
                                                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING))
                                                        .addGap(44, 44, 44))
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(txtTienKhachTra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                                                    .addComponent(txtThanhTien)
                                                    .addComponent(txtTienThua)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel18)
                                                    .addGap(48, 48, 48)
                                                    .addComponent(txtTienGiam))
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel19))))
                                .addGap(2, 2, 2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel16)
                        .addGap(198, 198, 198)
                        .addComponent(bthuy)
                        .addGap(64, 64, 64)
                        .addComponent(btnTaoHoaDon)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTaoHoaDon)
                        .addComponent(bthuy))
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbbKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(cbbGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(6, 6, 6)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel18)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienKhachTra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Btlammoi)
                            .addComponent(btnThanhToan)))
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
                txtNgayThanhToan.setText(ngayBan);
            } else {
                txtNgayThanhToan.setText("");
            }
        }
    }//GEN-LAST:event_dateChooserPropertyChange

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed

//        if (currentHoaDon == null) {
//            JOptionPane.showMessageDialog(this, "Vui lòng tạo hóa đơn trước khi thanh toán!");
//            return;
//        }
//        currentHoaDon.setTrangThai("Đã thanh toán");
//        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
//        loadGioHang();

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSPActionPerformed


    }//GEN-LAST:event_btnThemSPActionPerformed

    private void BtlammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtlammoiActionPerformed

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
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thêm!");
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
            ArrayList<GioHang> danhSachTrongGio = repoGio.getAll();
            boolean daTonTai = false;

            for (GioHang item : danhSachTrongGio) {
                if (item.getMaSP().equalsIgnoreCase(maSP)) {
                    // Sản phẩm đã tồn tại trong giỏ hàng → cập nhật số lượng
                    int soLuongMoi = item.getSoLuong() + soLuongNhap;
                    boolean updateResult = repoGio.updateSoLuong(item.getIdGH(), soLuongMoi); // Cập nhật theo ID giỏ hàng
                    daTonTai = true;

                    if (updateResult) {
                        JOptionPane.showMessageDialog(this, "Đã cập nhật số lượng sản phẩm trong giỏ hàng!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không thể cập nhật sản phẩm trong giỏ hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

                    break;
                }
            }

            // Nếu sản phẩm chưa có trong giỏ → thêm mới
            if (!daTonTai) {
                GioHang gh = new GioHang(idSP, maSP, tenSP, soLuongNhap, donGia);
                boolean result = repoGio.addGio(gh);

                if (result) {
                    JOptionPane.showMessageDialog(this, "Sản phẩm đã được thêm vào giỏ hàng!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm sản phẩm vào giỏ hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
            int soMoiTonKho = soLuongTonKho - soLuongNhap;
            sanPhamRepo.updateSoLuongGioHang(idSP, soMoiTonKho);

            loadGioHang();
            loadSanPham();

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
            int idGH;
            try {
                idGH = Integer.parseInt(tblGioHoaDon.getValueAt(chonRow, 0).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: ID sản phẩm trong giỏ hàng không hợp lệ!");
                return;
            }

            // Lấy mã sản phẩm và số lượng đã mua trong giỏ hàng
            String maSP = tblGioHoaDon.getValueAt(chonRow, 1).toString();
            int soLuongMua = Integer.parseInt(tblGioHoaDon.getValueAt(chonRow, 3).toString());

            // Lấy số lượng hiện tại trong bảng Sản phẩm
            int soLuongHienTai = sanPhamRepo.getSoLuongTheoMaSP(maSP);
            int soLuongMoi = soLuongHienTai + soLuongMua;

            // Cập nhật lại số lượng trong bảng Sản phẩm
            sanPhamRepo.updateSoLuongGioHangTheoMa(maSP, soLuongMoi);

            // Xác nhận xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa sản phẩm này khỏi giỏ hàng?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Xóa sản phẩm trong giỏ hàng
            if (repoGio.deleteGioHang(idGH)) {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm trong giỏ hàng thành công!");
                loadGioHang(); // Cập nhật bảng giỏ
                loadSanPham(); // Cập nhật lại bảng sản phẩm
            } else {
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thất bại!");
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

    private void BtlammoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtlammoiMouseClicked
        tbhd.clearSelection();
        tblDSSanPham.clearSelection();
        tblGioHoaDon.clearSelection();
        xoaFrom();
    }//GEN-LAST:event_BtlammoiMouseClicked

    private void btnTaoHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTaoHoaDonMouseClicked
        try {
            String ngayStr = txtNgayThanhToan.getText().trim();
            if (ngayStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày !");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayTao = sdf.parse(ngayStr);
            String maNV = cbbNhanVien.getSelectedItem().toString();
            String maKH = cbbKhachHang.getSelectedItem().toString();
            String diaChi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            BigDecimal thanhTien = new BigDecimal(txtThanhTien.getText().trim());
            BigDecimal tongTienSP = new BigDecimal(txttongtien.getText().trim());
            boolean trangThai = false;
            String hinhThucThanhToan = cbbChonHinhThuc.getSelectedItem().toString();

            //---------------------
            int idKH = repoKH.getIDKhachHangByMaKH(maKH);

            int idNV = repoNhanVien.getIdNhanVien(maNV);

            if (idKH == -1) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã: " + maKH);
                return;
            }

            int idTT = repoPTTT.getIdTT(hinhThucThanhToan);

            if (idNV == -1 || idKH == -1 || idTT == -1) {
                JOptionPane.showMessageDialog(this, " IDNV, IDKH, IDPTTT không tồn tại trong hệ thống!");
                return;
            }

            // Kiểm tra xem các trường thông tin có trống hay không
            if (sdt.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập SDT!");
                return;
            }
            if (diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Dịa Chỉ!");
            }

            // Kiểm tra số điện thoại: phải là số, có 10 ký tự, bắt đầu là số 0
            if (!sdt.matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là số, có 10 ký tự và bắt đầu bằng số 0.");
                return;
            }
            // Kiểm tra khoảng trắng liên tục
            if (diaChi.contains("  ")) {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa ký tự đặc biệt ngoài (dấu chấm, dấu phẩy, dấu gạch,số)còn ký tự khác không được
            if (!diaChi.matches("^[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ0-9 ,.-]+$")) {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa ký tự đặc biệt (ngoài dấu chấm, dấu phẩy và dấu gạch ngang)");
                return;
            }

            // Kiểm tra số điện thoại đã tồn tại trong cơ sở dữ liệu
            if (hoaDonRepo.existsPhoneNumber(sdt)) {
                JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại! Vui lòng nhập số khác.");
                return;
            }

            HoaDonModel hd = new HoaDonModel(ngayTao, tongTienSP, thanhTien, sdt, diaChi, trangThai, idKH, idNV, idTT);
            hoaDonRepo.insertHoaDon(hd);
            this.loadHoaDon();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại!");
        }

    }//GEN-LAST:event_btnTaoHoaDonMouseClicked

    private void tblGioHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHoaDonMouseClicked
        int chonRow = tblGioHoaDon.getSelectedRow();
        if (chonRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm trong giỏ hàng");
            return;
        }
        String soLuong = tblGioHoaDon.getValueAt(chonRow, 3).toString();
        String tongTienSP = tblGioHoaDon.getValueAt(chonRow, 5).toString();
        txtSoLuong.setText(soLuong);
        txttongtien.setText(tongTienSP);
        
    }//GEN-LAST:event_tblGioHoaDonMouseClicked

    private void txtThanhTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtThanhTienActionPerformed
//        cbbGG.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    capNhatTienGiamVaThanhTien();
//                }
//            }
//        });

    }//GEN-LAST:event_txtThanhTienActionPerformed

    private void txtTienThuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienThuaActionPerformed

    }//GEN-LAST:event_txtTienThuaActionPerformed

    private void txtTienKhachTraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachTraActionPerformed


    }//GEN-LAST:event_txtTienKhachTraActionPerformed

    private void tbhdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbhdMouseClicked
        int chonRow = tbhd.getSelectedRow();
        if (chonRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm cần thanh toán!");
            return;
        }
        String maHoaDon = tbhd.getValueAt(chonRow, 0).toString();
        String maNhanVien = tbhd.getValueAt(chonRow, 1).toString();
        String maKhachHang = tbhd.getValueAt(chonRow, 2).toString();
        String ngayTao = tbhd.getValueAt(chonRow, 3).toString();
        String thanhTien = tbhd.getValueAt(chonRow, 4).toString();
        String trangThai = tbhd.getValueAt(chonRow, 5).toString();
        String diaChi = tbhd.getValueAt(chonRow, 6).toString();
        String sdt = tbhd.getValueAt(chonRow, 7).toString();
        String tongTienSP = tbhd.getValueAt(chonRow, 8).toString();

        txtNgayThanhToan.setText(ngayTao);
        txtThanhTien.setText(thanhTien);
        txtDiaChi.setText(diaChi);
        txtSDT.setText(sdt);
        txtMaHoaDon.setText(maHoaDon);
        txttongtien.setText(tongTienSP);
        loadComboBoxKhachHang(maKhachHang);
        loadComboBoxNhanVien(maNhanVien);


    }//GEN-LAST:event_tbhdMouseClicked

    private void btnThanhToanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThanhToanMouseClicked
        try {

            int chonRow = tbhd.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn tạo hóa đơn rồi thanh toán!");
                return;
            }
            int idGH = Integer.parseInt(tblGioHoaDon.getValueAt(chonRow, 0).toString());

            String maHoaDon = tbhd.getValueAt(chonRow, 0).toString();
            //------------
            String ngayStr = txtNgayThanhToan.getText().trim();
            if (ngayStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập ngày !");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date ngayTao = sdf.parse(ngayStr);
            //-------------
            String maNV = cbbNhanVien.getSelectedItem().toString();
            String maKH = cbbKhachHang.getSelectedItem().toString();
            String diaChi = txtDiaChi.getText().trim();
            String sdt = txtSDT.getText().trim();
            //-------------
            String tongTienStr = txttongtien.getText().trim();
            if (tongTienStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập Tổng tiền sản phẩm!");
                return;
            }
            BigDecimal tongTienSP = new BigDecimal(txttongtien.getText().trim());
            //-------------
            BigDecimal thanhTien = new BigDecimal(txtThanhTien.getText().trim());
            boolean trangThai = false;
            String hinhThucThanhToan = cbbChonHinhThuc.getSelectedItem().toString();

            //---------------------
            int idKH = repoKH.getIDKhachHangByMaKH(maKH);

            int idNV = repoNhanVien.getIdNhanVien(maNV);

            if (idKH == -1) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với mã: " + maKH);
                return;
            }

            int idTT = repoPTTT.getIdTT(hinhThucThanhToan);

            if (idNV == -1 || idKH == -1 || idTT == -1) {
                JOptionPane.showMessageDialog(this, " IDNV, IDKH, IDPTTT không tồn tại trong hệ thống!");
                return;
            }

            // Kiểm tra xem các trường thông tin có trống hay không
            if (sdt.isEmpty() || diaChi.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Kiểm tra số điện thoại: phải là số, có 10 ký tự, bắt đầu là số 0
            if (!sdt.matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại phải là số, có 10 ký tự và bắt đầu bằng số 0.");
                return;
            }
            // Kiểm tra khoảng trắng liên tục
            if (diaChi.contains("  ")) {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa ký tự đặc biệt ngoài (dấu chấm, dấu phẩy, dấu gạch,số)còn ký tự khác không được
            if (!diaChi.matches("^[a-zA-Z0-9àáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ0-9 ,.-]+$")) {
                JOptionPane.showMessageDialog(this, "Địa chỉ không được chứa ký tự đặc biệt (ngoài dấu chấm, dấu phẩy và dấu gạch ngang)");
                return;
            }
            if (txtTienKhachTra.getText() == null || txtTienKhachTra.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tiền khách trả không được để trống.");
                return;
            }

            BigDecimal tienKhachTra = new BigDecimal(txtTienKhachTra.getText().trim());
            if (tienKhachTra.compareTo(thanhTien) < 0) {
                JOptionPane.showMessageDialog(this, "Tiền khách trả phải lớn hơn hoặc bằng thành tiền!");
                return;
            }

            HoaDonModel hd = new HoaDonModel(maHoaDon, ngayTao, tongTienSP, thanhTien, sdt, diaChi, trangThai, idKH, idNV, idTT);
            hoaDonRepo.updateHoaDon(hd);
            this.loadHoaDon();
            repoGio.deleteGioHang(idGH);
            this.loadGioHang();
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
            xoaFrom();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThanhToanMouseClicked

    private void txtMaHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHoaDonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHoaDonActionPerformed

    private void txttongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtienActionPerformed

    private void bthuyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthuyMouseClicked
        try {
            int chonRow1 = tbhd.getSelectedRow();
            if (chonRow1 == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Hoa Don cần xóa!");
                return;
            }

            // Kiểm tra mã hợp lệ
            String maHD;
            try {
                maHD = tbhd.getValueAt(chonRow1, 0).toString();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: Mã Hóa Đơn không hợp lệ!");
                return;
            }

            // Xác nhận xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa hóa đơn này chứ?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            // xóa hóa đơn
            if (hoaDonRepo.deleteHD(maHD)) {
                JOptionPane.showMessageDialog(this, "Xóa Hóa Đơn thành công!");
                loadHoaDon();
                xoaFrom();
                txtMaHoaDon.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Xóa Hóa Đơn thất bại!");

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_bthuyMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btlammoi;
    private javax.swing.JButton bthuy;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSP;
    private javax.swing.JButton btnXoaSPGH;
    private javax.swing.JComboBox<String> cbbChonHinhThuc;
    private javax.swing.JComboBox<String> cbbGG;
    private javax.swing.JComboBox<String> cbbKhachHang;
    private javax.swing.JComboBox<String> cbbNhanVien;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtNgayThanhToan;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtThanhTien;
    private javax.swing.JTextField txtTienGiam;
    private javax.swing.JTextField txtTienKhachTra;
    private javax.swing.JTextField txtTienThua;
    private javax.swing.JTextField txtTimSP;
    private javax.swing.JTextField txttongtien;
    // End of variables declaration//GEN-END:variables

}
