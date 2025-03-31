package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import duantest.DBConnect.Dbconnect;
import Model.GiamGiaKhachHang;
import Model.GiamGia;

public class GiamGiaKhachHangRepo {

    // Kiểm tra xem khách hàng có mã giảm giá hay không
    public boolean hasDiscount(String maKhachHang) {
        String sql = "SELECT 1 FROM Giam_Gia_Khach_Hang ggkh "
                   + "JOIN Khach_Hang kh ON ggkh.ID_Khach_Hang = kh.ID_Khach_Hang "
                   + "WHERE kh.Ma_Khach_Hang = ? LIMIT 1"; // Tối ưu bằng LIMIT 1
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKhachHang);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Chỉ cần có 1 kết quả là đủ
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Lấy danh sách tất cả mã giảm giá của khách hàng
    public double getTongGiaTriGiamGia(String maKhachHang) {
        String sql = "SELECT SUM(gg.Muc_Gia_Giam) FROM Giam_Gia gg "
                   + "JOIN Giam_Gia_Khach_Hang ggkh ON gg.ID_Giam_Gia = ggkh.ID_Giam_Gia "
                   + "JOIN Khach_Hang kh ON ggkh.ID_Khach_Hang = kh.ID_Khach_Hang "
                   + "WHERE kh.Ma_Khach_Hang = ?";
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maKhachHang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1); // Lấy tổng mức giảm giá
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tính tổng tiền sau khi áp dụng giảm giá
    public double tinhTienSauGiamGia(String maKhachHang, double tongTien) {
        if (hasDiscount(maKhachHang)) {
            double tongGiamGia = getTongGiaTriGiamGia(maKhachHang);
            tongTien -= tongGiamGia;
        }
        return Math.max(tongTien, 0); // Đảm bảo tổng tiền không âm
    }
}
