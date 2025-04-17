/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Application.Formm;

import Model.HoaDonModel;
import Model.ThongKeModel;
import Repository.HoaDonRepo;
import Repository.ThongKeRepo;
import com.sun.jdi.connect.spi.Connection;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Admin
 */
public class ThongKe extends javax.swing.JPanel {

    private int year;
    private BigDecimal tongTien;
    private ThongKeRepo repoTK;

    public ThongKe() {

        this.repoTK = new ThongKeRepo();
        initComponents();
        txtTongTien.setEditable(false);
        loadTable();
        loadComboBoxChatLieu(year);

    }

    private void hienThiTongTien(BigDecimal tongTien) {
        txtTongTien.setText("Tổng tiền: " + (tongTien != null ? tongTien : BigDecimal.ZERO) + " VND");
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) this.tblThongKe.getModel();
        model.setRowCount(0);
        List<Model.ThongKeModel> listTK = repoTK.getAllTK();
        for (ThongKeModel tk : listTK) {
            Object[] rowData = {
                tk.getIdHoaDon(),
                tk.getMaHoaDon(),
                tk.getNgayThanhToan(),
                tk.getTongGia(),
                tk.getTrangThai(),};
            model.addRow(rowData);
        }
    }

    private void loadComboBoxChatLieu(int year) {
        cboNam.removeAllItems();
        cboNam.addItem("Tất cả");
        List<Model.ThongKeModel> listCL = repoTK.getAllTK();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Set<String> yearsSet = new HashSet<>();
        for (ThongKeModel cl : listCL) {
            String yearStr = sdf.format(cl.getNgayThanhToan());
            yearsSet.add(yearStr);
        }
        for (String yearStr : yearsSet) {
            cboNam.addItem(yearStr);
        }
        if (yearsSet.contains(String.valueOf(year))) {
            cboNam.setSelectedItem(String.valueOf(year));
        } else if (!yearsSet.isEmpty()) {
            cboNam.setSelectedIndex(0);
        }
    }

   private void UpdateloadTableNam(int year) {
    DefaultTableModel model = (DefaultTableModel) this.tblThongKe.getModel();
    model.setRowCount(0);

    // Lấy tổng tiền theo năm
    BigDecimal tongTien = repoTK.TimYear(year);

    // Cập nhật bảng với danh sách hóa đơn
    List<ThongKeModel> listTK = repoTK.getHoaDonByYear(year);
    for (ThongKeModel tk : listTK) {
        Object[] rowData = {
            tk.getIdHoaDon(),
            tk.getMaHoaDon(),
            tk.getNgayThanhToan(),
            tk.getTongGia(),
            tk.getTrangThai(),
        };
        model.addRow(rowData);
    }

   
    hienThiTongTien(tongTien); 
}

    private void UpdateloadTableKhoang(List<ThongKeModel> list) {
        DefaultTableModel model = (DefaultTableModel) tblThongKe.getModel();
        model.setRowCount(0);

        for (ThongKeModel tk : list) {
            Object[] rowData = {
                tk.getIdHoaDon(),
                tk.getMaHoaDon(),
                tk.getNgayThanhToan(),
                tk.getTongGia(),
                tk.getTrangThai()
            };
            model.addRow(rowData);
        }
    }

    public void xoaForm() {
        txtTimDenNgay.setText("");
        txtTimTuNgay.setText("");
        txtTongTien.setText("");
        cboNam.setSelectedItem(0);
        tblThongKe.clearSelection();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTimTuNgay = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTimDenNgay = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnTimTheoKhoang = new javax.swing.JButton();
        dateTuNgay = new com.toedter.calendar.JDateChooser();
        dateDenNgay = new com.toedter.calendar.JDateChooser();
        cboNam = new javax.swing.JComboBox<>();
        txtTongTien = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jLabel3.setText("Tìm Theo Năm");

        jLabel1.setText("Từ Ngày");

        jLabel2.setText("Đến Ngày");

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã Hóa Đơn", "Ngày Thanh Toán", "Tổng Giá", "Trạng Thái"
            }
        ));
        jScrollPane2.setViewportView(tblThongKe);

        jLabel4.setText("Tìm Theo Thời Gian");

        btnTimTheoKhoang.setText("Tìm Khoảng");
        btnTimTheoKhoang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimTheoKhoangMouseClicked(evt);
            }
        });
        btnTimTheoKhoang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimTheoKhoangActionPerformed(evt);
            }
        });

        dateTuNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateTuNgayPropertyChange(evt);
            }
        });

        dateDenNgay.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateDenNgayPropertyChange(evt);
            }
        });

        cboNam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboNamMouseClicked(evt);
            }
        });
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });

        jButton2.setText("Làm mới");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(132, 132, 132)
                                        .addComponent(jLabel4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(189, 189, 189)
                                                .addComponent(jButton2))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(70, 70, 70)
                                                .addComponent(jLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtTimTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(dateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel2)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnTimTheoKhoang)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(txtTimDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txtTimTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dateTuNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtTimDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dateDenNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnTimTheoKhoang))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dateTuNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateTuNgayPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateTuNgay.getDate();

            if (selectedDate != null) {
                String ngayBan = sdf.format(selectedDate);
                txtTimTuNgay.setText(ngayBan); // Gán ngày vào JTextField
            } else {
                txtTimTuNgay.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateTuNgayPropertyChange

    private void dateDenNgayPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateDenNgayPropertyChange
        if ("date".equals(evt.getPropertyName())) { // Kiểm tra nếu giá trị ngày thay đổi
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date selectedDate = dateDenNgay.getDate();

            if (selectedDate != null) {
                String ngayBan = sdf.format(selectedDate);
                txtTimDenNgay.setText(ngayBan); // Gán ngày vào JTextField
            } else {
                txtTimDenNgay.setText(""); // Nếu không có ngày, làm rỗng ô text
            }
        }
    }//GEN-LAST:event_dateDenNgayPropertyChange

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed

    }//GEN-LAST:event_txtTongTienActionPerformed

    private void cboNamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboNamMouseClicked

    }//GEN-LAST:event_cboNamMouseClicked

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        String selectedYear = cboNam.getSelectedItem().toString();

        if ("Tất cả".equalsIgnoreCase(selectedYear)) {
            loadTable();
        } else {
            try {
                int timKiem = Integer.parseInt(selectedYear);
                UpdateloadTableNam(timKiem);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi: Dữ liệu không hợp lệ!");
            }
        }

    }//GEN-LAST:event_cboNamActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        xoaForm();
        loadTable();
    }//GEN-LAST:event_jButton2MouseClicked

    private void btnTimTheoKhoangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimTheoKhoangActionPerformed
//        String tu = txtTimTuNgay.getText().trim();
//        String den = txtTimDenNgay.getText().trim();
//
//        // Kiểm tra nếu chưa nhập hoặc nhập thiếu ngày
//        if (tu.isEmpty() || den.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc!");
//            return;
//        }
//
//        try {
//            // Chuyển đổi String sang Date
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            Date ngayBatDau = sdf.parse(tu);
//            Date ngayKetThuc = sdf.parse(den);
//
//            // Kiểm tra ngày hợp lệ
//            if (ngayBatDau.after(ngayKetThuc)) {
//                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
//                return;
//            }
//
//            // Gọi phương thức cập nhật bảng theo khoảng ngày
//            UpdateloadTableKhoang(ngayBatDau, ngayKetThuc);
//
//        } catch (ParseException e) {
//            JOptionPane.showMessageDialog(this, "Lỗi: Định dạng ngày không hợp lệ! Vui lòng nhập theo yyyy-MM-dd.");
//        }
    }//GEN-LAST:event_btnTimTheoKhoangActionPerformed

    private void btnTimTheoKhoangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimTheoKhoangMouseClicked
        // Giả sử ngày bạn lấy từ TextField (như txtTimTuNgay và txtTimDenNgay) là String.
        String tu = txtTimTuNgay.getText();
        String den = txtTimDenNgay.getText();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (tu.isEmpty() || den.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ ngày bắt đầu và ngày kết thúc!");
            return;
        }

        try {
            Date ngayBD = sdf.parse(tu);
            Date ngayKT = sdf.parse(den);

            if (ngayBD.after(ngayKT)) {
                JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày kết thúc!");
                return;
            }

            // Chuyển đổi thành java.sql.Date
            java.sql.Date sqlNgayBD = new java.sql.Date(ngayBD.getTime());
            java.sql.Date sqlNgayKT = new java.sql.Date(ngayKT.getTime());

            // Gọi phương thức của repository
            List<ThongKeModel> result = repoTK.TimTheoKhoangNgay(sqlNgayBD, sqlNgayKT);
            // Cập nhật bảng với dữ liệu trả về
            UpdateloadTableKhoang(result);
            BigDecimal tongTien = BigDecimal.ZERO;
            for (ThongKeModel item : result) {
                tongTien = tongTien.add(item.getTongGia());
            }

            // Hiển thị tổng tiền lên giao diện
            hienThiTongTien(tongTien);

        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi chuyển đổi ngày!");
        }


    }//GEN-LAST:event_btnTimTheoKhoangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimTheoKhoang;
    private javax.swing.JComboBox<String> cboNam;
    private com.toedter.calendar.JDateChooser dateDenNgay;
    private com.toedter.calendar.JDateChooser dateTuNgay;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTextField txtTimDenNgay;
    private javax.swing.JTextField txtTimTuNgay;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
