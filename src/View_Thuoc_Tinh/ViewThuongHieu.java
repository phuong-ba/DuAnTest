/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View_Thuoc_Tinh;

import Model.ThuongHieu;
import Repository.ThuongHieuRepo;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ViewThuongHieu extends javax.swing.JPanel {

    private JComboBox<String> cbbThuongHieu;
    private ThuongHieuRepo repoTH = new ThuongHieuRepo();

    public ViewThuongHieu(JComboBox<String> cbbThuongHieu) {
        this.cbbThuongHieu = cbbThuongHieu;
        initComponents();
        txtIDTH.setEditable(false);
        loadTable();

    }

    private void loadComboBoxChatLieu() {
        cbbThuongHieu.removeAllItems(); // Xóa hết dữ liệu cũ
        List<ThuongHieu> listCL = repoTH.getAll(); // Lấy danh sách chất liệu từ database
        for (ThuongHieu th : listCL) {
            cbbThuongHieu.addItem(th.getTenTH()); // Thêm từng chất liệu vào ComboBox
        }
    }

    public void loadTable() {
        DefaultTableModel mode = (DefaultTableModel) this.tblThuongHieu.getModel();
        mode.setRowCount(0);
        List<ThuongHieu> list = repoTH.getAll();
        for (ThuongHieu ms : list) {
            Object[] rowData = {
                ms.getId(),
                ms.getTenTH(),};
            mode.addRow(rowData);
        }
    }

    public void loadTableTimCL(String keyword) {
        DefaultTableModel mode = (DefaultTableModel) this.tblThuongHieu.getModel();
        mode.setRowCount(0);
        List<ThuongHieu> list = repoTH.getTimKT(keyword);
        for (ThuongHieu ms : list) {
            Object[] rowData = {
                ms.getId(),
                ms.getTenTH(),};
            mode.addRow(rowData);
        }
    }

    public void xoaFrom() {
        txtViewThuongHieu.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtViewTimThuongHieu = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtViewThuongHieu = new javax.swing.JTextField();
        btnThemTH = new javax.swing.JButton();
        btnSuaTH = new javax.swing.JButton();
        btnXoaTH = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThuongHieu = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtIDTH = new javax.swing.JTextField();

        jLabel1.setText("Tìm Kiếm");

        txtViewTimThuongHieu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtViewTimThuongHieuKeyReleased(evt);
            }
        });

        jLabel2.setText("Thương Hiệu");

        btnThemTH.setText("Thêm");
        btnThemTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTHActionPerformed(evt);
            }
        });

        btnSuaTH.setText("Sửa");
        btnSuaTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTHActionPerformed(evt);
            }
        });

        btnXoaTH.setText("Xóa");
        btnXoaTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTHActionPerformed(evt);
            }
        });

        tblThuongHieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Thương Hiệu", "Thương Hiệu"
            }
        ));
        tblThuongHieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuongHieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblThuongHieu);

        jLabel3.setText("ID Thuong Hieu");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDTH))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtViewTimThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtViewThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnThemTH)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaTH))
                    .addComponent(btnXoaTH))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtViewTimThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemTH)
                    .addComponent(btnSuaTH))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtViewThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaTH))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIDTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTHActionPerformed
        try {
            String tenTH = txtViewThuongHieu.getText().trim();

            // Kiểm tra rỗng
            if (tenTH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            // Kiểm tra khoảng trắng liên tục
            if (tenTH.matches(".*\\s{2,}.*")) { // Kiểm tra nếu có 2 khoảng trắng liên tiếp
                JOptionPane.showMessageDialog(this, "Tên không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa số không
            if (tenTH.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Tên không được chứa số.");
                return;
            }

            // Kiểm tra ký tự đặc biệt (chỉ cho phép chữ cái + dấu cách)
            if (!tenTH.matches("^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]+$")) {
                JOptionPane.showMessageDialog(this, "Tên không được chứa ký tự đặc biệt.");
                return;
            }
            if(repoTH.checkTrung(tenTH)){
                JOptionPane.showMessageDialog(this, "Thương Hiệu đã tồn tại");
                return;
            }

            // Tạo đối tượng ChatLieu và thêm vào DB
            ThuongHieu th = new ThuongHieu(tenTH);
            repoTH.add(th); // Giả sử repoCL là lớp xử lý database           
            loadTable();  // Cập nhật lại bảng
            xoaFrom();  // Xóa dữ liệu nhập
            if (cbbThuongHieu != null) {
                cbbThuongHieu.addItem(tenTH);  // Thêm vào danh sách
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi thêm Thuong Hieu: " + e.getMessage());
            e.printStackTrace(); // Hiển thị lỗi chi tiết trong console
        }
    }//GEN-LAST:event_btnThemTHActionPerformed

    private void btnSuaTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTHActionPerformed
        try {
            int chonRow = tblThuongHieu.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Thuong Hieu cần sửa!");
                return;
            }
            int id = Integer.parseInt(tblThuongHieu.getValueAt(chonRow, 0).toString());
            String tenTH = txtViewThuongHieu.getText();
            if (tenTH.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }
            if (tenTH.contains("  ")) {
                JOptionPane.showMessageDialog(this, "tên không được chứa khoảng trắng liên tục.");
                return;
            }

            // Kiểm tra có chứa số không
            if (tenTH.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Tên không được chứa số.");
                return;
            }

            // Kiểm tra có chứa ký tự đặc biệt không (trừ dấu cách)
            if (!tenTH.matches("^[a-zA-ZàáạảãâầấậẩẫăằắặẳẵèéẹẻẽêềếệểễìíịỉĩòóọỏõôồốộổỗơờớợởỡùúụủũưừứựửữỳýỵỷỹđÀÁẠẢÃÂẦẤẬẨẪĂẰẮẶẲẴÈÉẸẺẼÊỀẾỆỂỄÌÍỊỈĨÒÓỌỎÕÔỒỐỘỔỖƠỜỚỢỞỠÙÚỤỦŨƯỪỨỰỬỮỲÝỴỶỸĐ ]+$")) {
                JOptionPane.showMessageDialog(this, "Tên không được chứa ký tự đặc biệt.");
                return;
            }
            if(repoTH.checkTrung(tenTH)){
                JOptionPane.showMessageDialog(this, "Thương Hiệu đã tồn tại");
                return;
            }
            
            ThuongHieu th = new ThuongHieu(id, tenTH);
            this.repoTH.update(th);
            loadTable();
            xoaFrom();
            if (cbbThuongHieu != null) {
                cbbThuongHieu.addItem(tenTH);  // Thêm vào danh sách
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Có lỗi xảy ra khi cập nhật thông tin Thuong Hieu! Vui lòng kiểm tra lại.");
        }
    }//GEN-LAST:event_btnSuaTHActionPerformed

    private void btnXoaTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTHActionPerformed
        try {
            int chonRow = tblThuongHieu.getSelectedRow();
            if (chonRow == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn Thuong Hieu cần xóa!");
                return;
            }

            int id;
            try {
                id = Integer.parseInt(tblThuongHieu.getValueAt(chonRow, 0).toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: ID Thuong Hieu không hợp lệ!");
                return;
            }

            // Xác nhận trước khi xóa
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Bạn có chắc chắn muốn xóa thuong hieu này?",
                    "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Xóa nhân viên
            if (repoTH.delete(id)) {
                JOptionPane.showMessageDialog(this, "Xóa thuong hieu thành công!");
                loadTable(); // Cập nhật lại bảng
                loadComboBoxChatLieu();
                xoaFrom();

            } else {
                JOptionPane.showMessageDialog(this, "Xóa thuong hieu thất bại! Vui lòng kiểm tra lại.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi hệ thống: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaTHActionPerformed

    private void tblThuongHieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuongHieuMouseClicked
        try {
            int chon = tblThuongHieu.getSelectedRow();
            if (chon != -1) {
                String id = tblThuongHieu.getValueAt(chon, 0).toString();
                String tenTH = tblThuongHieu.getValueAt(chon, 1).toString();
                txtIDTH.setText(id);
                txtViewThuongHieu.setText(tenTH);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_tblThuongHieuMouseClicked

    private void txtViewTimThuongHieuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtViewTimThuongHieuKeyReleased
        String timKiem = txtViewTimThuongHieu.getText().trim();
        loadTableTimCL(timKiem);
    }//GEN-LAST:event_txtViewTimThuongHieuKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSuaTH;
    private javax.swing.JButton btnThemTH;
    private javax.swing.JButton btnXoaTH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblThuongHieu;
    private javax.swing.JTextField txtIDTH;
    private javax.swing.JTextField txtViewThuongHieu;
    private javax.swing.JTextField txtViewTimThuongHieu;
    // End of variables declaration//GEN-END:variables
}
