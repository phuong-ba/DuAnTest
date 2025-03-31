package Repository;

import Model.MauSac;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class MauSacRepo {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy tất cả màu sắc có trạng thái hiển thị
    public ArrayList<MauSac> getAll() {
        ArrayList<MauSac> list = new ArrayList<>();
        String query = "SELECT * FROM Mau_Sac WHERE Trang_Thai = 1";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("ID_Mau_Sac"));

                ms.setTenMau(rs.getString("Ten_Mau_Sac"));
                ms.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    // Thêm mới màu sắc
    public boolean add(MauSac ms) {
        String query = "INSERT INTO Mau_Sac ( Ten_Mau_Sac, Trang_Thai) VALUES (?, 1)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, ms.getTenMau());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Cập nhật màu sắc
    public boolean update(MauSac ms) {
        String query = "UPDATE Mau_Sac SET  Ten_Mau_Sac = ? WHERE ID_Mau_Sac = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, ms.getTenMau());
            ps.setInt(2, ms.getId());
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
        String query = "UPDATE Mau_Sac SET Trang_Thai = 0 WHERE ID_Mau_Sac = ?";
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

   public int getIdMauSac(String tenMau) {
    String sql = "SELECT ID_Mau_Sac FROM Mau_Sac WHERE Ten_Mau_Sac = ?";

    try (Connection conn = Dbconnect.getConnection(); 
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, tenMau);  // Gán tên màu vào tham số
        
        // Thực hiện truy vấn
        ResultSet rs = stmt.executeQuery();  
        
        // Kiểm tra kết quả và trả về ID_Mau_Sac
        if (rs.next()) {
            return rs.getInt("ID_Mau_Sac"); // Trả về ID_Mau_Sac nếu tìm thấy
        } else {
            System.out.println("Không tìm thấy màu sắc với tên: " + tenMau);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy
}



    // Khôi phục màu sắc đã bị ẩn (Trang_Thai = 0 -> 1)
    public boolean restore(int id) {
        String query = "UPDATE Mau_Sac SET Trang_Thai = 1 WHERE ID_Mau_Sac = ?";
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

    // Tìm kiếm theo tên màu (cả dữ liệu ẩn lẫn dữ liệu hiển thị)
    public ArrayList<MauSac> getTimKT(String keyword) {
        ArrayList<MauSac> list = new ArrayList<>();
        String query = "SELECT * FROM Mau_Sac WHERE Trang_Thai = 1 AND LOWER(Ten_Mau_Sac) LIKE LOWER(?)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("ID_Mau_Sac"));

                ms.setTenMau(rs.getString("Ten_Mau_Sac"));
                ms.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean checkTrung(String tenMS) {
        for (MauSac cl : getAll()) {
            if (cl.getTenMau().equalsIgnoreCase(tenMS)) {
                return true;
            }
        }
        return false;
    }

    // Đóng kết nối
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
