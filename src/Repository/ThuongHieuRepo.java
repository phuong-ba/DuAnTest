package Repository;

import Model.ThuongHieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class ThuongHieuRepo {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy tất cả thương hiệu có trạng thái hiển thị (Trang_Thai = 1)
    public ArrayList<ThuongHieu> getAll() {
        ArrayList<ThuongHieu> list = new ArrayList<>();
        String query = "SELECT * FROM Thuong_Hieu WHERE Trang_Thai = 1";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt("ID_Thuong_Hieu"));
                
                th.setTenTH(rs.getString("Ten_Thuong_Hieu"));
                th.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    // Thêm mới thương hiệu
    public boolean add(ThuongHieu th) {
        String query = "INSERT INTO Thuong_Hieu ( Ten_Thuong_Hieu, Trang_Thai) VALUES (?, 1)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, th.getTenTH());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Cập nhật thương hiệu
    public boolean update(ThuongHieu th) {
        String query = "UPDATE Thuong_Hieu SET   Ten_Thuong_Hieu = ? WHERE ID_Thuong_Hieu = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, th.getTenTH());
            ps.setInt(2, th.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Xóa mềm: Cập nhật trạng thái thành 0 thay vì xóa vĩnh viễn
    public boolean delete(int id) {
        String query = "UPDATE Thuong_Hieu SET Trang_Thai = 0 WHERE ID_Thuong_Hieu = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Khôi phục thương hiệu đã bị ẩn (Trang_Thai = 0 -> 1)
    public boolean restore(int id) {
        String query = "UPDATE Thuong_Hieu SET Trang_Thai = 1 WHERE ID_Thuong_Hieu = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Tìm kiếm theo tên thương hiệu (cả dữ liệu ẩn lẫn hiển thị)
   public ArrayList<ThuongHieu> getTimKT(String keyword) {
        ArrayList<ThuongHieu> list = new ArrayList<>();
        String query = "SELECT * FROM Thuong_Hieu WHERE Trang_Thai = 1 AND LOWER(Ten_Thuong_Hieu) LIKE LOWER(?)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt("ID_Thuong_Hieu"));

                th.setTenTH(rs.getString("Ten_Thuong_Hieu"));
                th.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(th);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
 public boolean checkTrung(String tenTH){
        for (ThuongHieu cl : getAll()) {
            if(cl.getTenTH().equalsIgnoreCase(tenTH)){
                return true;
            }
        }
        return false;
    }
 public int getIdThuongHieu(String tenChatLieu) {
        String sql = "SELECT ID_Thuong_Hieu FROM Thuong_Hieu WHERE Ten_Thuong_Hieu = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenChatLieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_Thuong_Hieu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }
    // Đóng kết nối
    private void closeConnection() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
