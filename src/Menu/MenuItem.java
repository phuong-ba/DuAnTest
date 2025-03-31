package Menu;

import javax.swing.*;
import java.awt.*;

public class MenuItem extends JPanel {
    public MenuItem() {
        setLayout(new GridLayout(10, 1, 5, 5)); // 10 hàng, 1 cột, khoảng cách 5px
        setBackground(new Color(30, 30, 40)); // Màu nền tối
        
        // Tạo các nút menu
        String[] menuItems = {"Bán Hàng", "Sản phẩm", "Hóa Đơn", "Giảm Giá", "Nhân Viên", "Khách Hàng", "Thống Kê", "Đăng Xuất"};
        for (String item : menuItems) {
            JButton btn = new JButton(item);
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(50, 50, 70));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            add(btn);
        }
    }
}
