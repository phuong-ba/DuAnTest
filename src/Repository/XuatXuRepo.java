package Repository;

import Model.XuatXu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class XuatXuRepo {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy tất cả xuất xứ có trạng thái hiển thị (Trang_Thai = 1)
    public ArrayList<XuatXu> getAll() {
        ArrayList<XuatXu> list = new ArrayList<>();
        String query = "SELECT * FROM Xuat_Xu WHERE Trang_Thai = 1";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                XuatXu xx = new XuatXu();
                xx.setId(rs.getInt("ID_Xuat_Xu"));

                xx.setTenXX(rs.getString("Ten_Xuat_Xu"));
                xx.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(xx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    // Thêm mới xuất xứ
    public boolean add(XuatXu xx) {
        String query = "INSERT INTO Xuat_Xu (Ten_Xuat_Xu, Trang_Thai) VALUES (?, 1)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, xx.getTenXX());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Cập nhật xuất xứ
    public boolean update(XuatXu xx) {
        String query = "UPDATE Xuat_Xu SET Ten_Xuat_Xu = ? WHERE ID_Xuat_Xu = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, xx.getTenXX());
            ps.setInt(2, xx.getId());
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
        String query = "UPDATE Xuat_Xu SET Trang_Thai = 0 WHERE ID_Xuat_Xu = ?";
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

    // Khôi phục xuất xứ đã bị ẩn (Trang_Thai = 0 -> 1)
    public boolean restore(int id) {
        String query = "UPDATE Xuat_Xu SET Trang_Thai = 1 WHERE ID_Xuat_Xu = ?";
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

    // Tìm kiếm theo tên xuất xứ (cả dữ liệu ẩn lẫn hiển thị)
    public ArrayList<XuatXu> getTimKT(String keyword) {
        ArrayList<XuatXu> list = new ArrayList<>();
        String query = "SELECT * FROM Xuat_Xu WHERE Trang_Thai = 1 AND LOWER(Ten_Xuat_Xu) LIKE LOWER(?)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                XuatXu th = new XuatXu();
                th.setId(rs.getInt("ID_Xuat_Xu"));
                th.setTenXX(rs.getString("Ten_Xuat_Xu"));
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

    public boolean checkTrung(String tenXX) {
        for (XuatXu cl : getAll()) {
            if (cl.getTenXX().equalsIgnoreCase(tenXX)) {
                return true;
            }
        }
        return false;
    }

    // Đóng kết nối
    public int getIdXuatXu(String tenChatLieu) {
        String sql = "SELECT ID_Xuat_Xu FROM Xuat_Xu WHERE Ten_Xuat_Xu = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenChatLieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_Xuat_Xu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
