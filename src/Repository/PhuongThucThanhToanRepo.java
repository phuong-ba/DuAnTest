/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.PhuongThucThanhToan;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import duantest.DBConnect.Dbconnect;

/**
 *
 * @author Admin
 */
public class PhuongThucThanhToanRepo {

    public ArrayList<PhuongThucThanhToan> getAllPhuongTTT() {
        ArrayList<PhuongThucThanhToan> list = new ArrayList<>();
        String sql = "SELECT * FROM Phuong_Thuc_Thanh_Toan";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                PhuongThucThanhToan pttt = new PhuongThucThanhToan(
                        rs.getInt("ID_Thanh_Toan"),
                        rs.getString("Loai_Thanh_Toan")
                );
                list.add(pttt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
     public int getIdTT(String loaiThanhToan) {
        String sql = "SELECT ID_Thanh_Toan FROM Phuong_Thuc_Thanh_Toan WHERE Loai_Thanh_Toan = ?";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, loaiThanhToan);  // Gán tên màu vào tham số

            // Thực hiện truy vấn
            ResultSet rs = stmt.executeQuery();

            // Kiểm tra kết quả và trả về ID_Mau_Sac
            if (rs.next()) {
                return rs.getInt("ID_Thanh_Toan"); // Trả về ID_Mau_Sac nếu tìm thấy
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }
}
