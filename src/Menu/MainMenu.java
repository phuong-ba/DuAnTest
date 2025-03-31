/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Menu;

import Application.Formm.BanHang;
import Application.formLogin.Login;
import Model.GiamGia;
import Model.HoaDon;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import Menu.MenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class MainMenu extends javax.swing.JFrame {

    private JPanel contentPanel;
    private String userRole; // Thêm biến lưu vai trò

    public MainMenu(String role) {
        this.userRole = role; // Lưu quyền
        setTitle("Trang chủ");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Tạo panel bên trái (Menu)
        // Tạo panel chứa menu (giữ vị trí cố định)
        JPanel menuContainer = new JPanel(new GridBagLayout());
        menuContainer.setPreferredSize(new Dimension(220, 500));
        menuContainer.setBackground(new Color(45, 45, 45)); // Màu nền tối

// Tạo menuPanel với BoxLayout để không bị kéo giãn
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(new Color(45, 45, 45));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Thêm padding
//        JPanel menuPanel = new JPanel();
//        menuPanel.setLayout(new GridLayout(8, 1, 10, 10));
//        menuPanel.setPreferredSize(new Dimension(220, 500));
//        menuPanel.setBackground(new Color(50, 50, 50)); // Màu nền của menu
//        menuPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Thêm padding

        // Tạo nút menu với màu sắc
        JButton btnBanHang = createMenuButton(" Bán hàng", new Color(68, 68, 68));
        JButton btnGiamGia = createMenuButton(" Giảm giá", new Color(68, 68, 68));
        JButton btnHoaDon = createMenuButton(" Hóa đơn", new Color(68, 68, 68));
        JButton btnKhachHang = createMenuButton(" Khách hàng", new Color(68, 68, 68));
        JButton btnNhanVien = createMenuButton(" Nhân viên", new Color(68, 68, 68));
        JButton btnSanPham = createMenuButton(" Sản phẩm", new Color(68, 68, 68));
        JButton btnThongKe = createMenuButton(" Thống kê", new Color(68, 68, 68));
        JButton btnDangXuat = createMenuButton(" Đăng xuất", new Color(255, 50, 50)); // Màu đỏ
        // Kiểm tra quyền truy cập và ẩn các nút nếu là STAFF
        if ("ROLE_STAFF".equals(userRole)) {
            btnGiamGia.setVisible(false);
            btnNhanVien.setVisible(false);
            btnSanPham.setVisible(false);
            btnThongKe.setVisible(false);
        }

        // Thêm hiệu ứng hover cho các nút menu
        addHoverEffect(btnBanHang);
        addHoverEffect(btnGiamGia);
        addHoverEffect(btnHoaDon);
        addHoverEffect(btnKhachHang);
        addHoverEffect(btnNhanVien);
        addHoverEffect(btnSanPham);
        addHoverEffect(btnThongKe);
        addHoverEffect(btnDangXuat);

        // Thêm nút vào menuPanel (có khoảng cách đều)
        menuPanel.add(btnBanHang);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Thêm khoảng cách
        menuPanel.add(btnGiamGia);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(btnHoaDon);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(btnKhachHang);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(btnNhanVien);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(btnSanPham);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(btnThongKe);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Khoảng cách lớn hơn trước Đăng xuất
        menuPanel.add(btnDangXuat);
        menuPanel.add(Box.createVerticalGlue()); // Giúp menu luôn cân đối

// Thêm menuPanel vào menuContainer
        menuContainer.add(menuPanel, new GridBagConstraints());

        // Tạo panel bên phải (Hiển thị nội dung)
        contentPanel = new JPanel();  // Khởi tạo contentPanel
        contentPanel.setLayout(new BorderLayout());

        // Tạo SplitPane để chia giao diện
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, contentPanel);
        splitPane.setDividerLocation(200); // Đặt vị trí thanh chia
        splitPane.setOneTouchExpandable(true);

        add(splitPane, BorderLayout.CENTER);

        btnBanHang.addActionListener(e -> showContent(new Application.Formm.BanHang(), "Bán hàng"));
        btnGiamGia.addActionListener(e -> showContent(new Application.Formm.GiamGia(), "Giảm giá"));
        btnHoaDon.addActionListener(e -> showContent(new Application.Formm.HoaDon(), "Hóa đơn"));
        btnKhachHang.addActionListener(e -> showContent(new Application.Formm.KhachHang(), "Khách hàng"));
        btnNhanVien.addActionListener(e -> showContent(new Application.Formm.NhanVien(), "Nhân viên"));
        btnSanPham.addActionListener(e -> showContent(new Application.Formm.SanPham(), "Sản phẩm"));
        btnThongKe.addActionListener(e -> showContent(new Application.Formm.ThongKe(), "Thống kê"));
        btnDangXuat.addActionListener(e -> {
            dispose();
            Login login = new Login();
            login.setVisible(true);
        });

        setVisible(true);
    }

    // Hàm cập nhật nội dung bên phải khi chọn menuItem và đổi tiêu đề
    private void showContent(JComponent component, String title) {
        if (contentPanel.getComponentCount() > 0 && contentPanel.getComponent(0) == component) {
            return; // Nếu nội dung hiện tại giống với nội dung mới, không làm gì cả
        }
        contentPanel.removeAll();
        contentPanel.add(component, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        // Cập nhật tiêu đề cửa sổ
        setTitle(title);
    }
// Hàm tạo nút menu với màu nền

    private JButton createMenuButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE); // Màu chữ đen để dễ đọc
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor); // Đặt màu nền theo tham số
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(180, 40)); // Kích thước cố định
        button.setMaximumSize(new Dimension(180, 40)); // Giữ kích thước khi mở rộng màn hình
        return button;
    }

    private void addHoverEffect(JButton button) {
        Color defaultColor = button.getBackground();
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor.brighter()); // Khi di chuột vào
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(defaultColor); // Khi rời chuột khỏi nút
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String userRole = "ROLE_STAFF"; // Lấy từ database sau khi đăng nhập
        SwingUtilities.invokeLater(() -> new MainMenu(userRole));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
