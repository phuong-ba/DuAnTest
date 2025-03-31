package Repository;

import Model.KichThuoc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class KichThuocRepo {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy tất cả kích thước có trạng thái hiển thị (Trang_Thai = 1)
    public ArrayList<KichThuoc> getAll() {
        ArrayList<KichThuoc> list = new ArrayList<>();
        String query = "SELECT * FROM Kich_Thuoc WHERE Trang_Thai = 1";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc kt = new KichThuoc();
                kt.setId(rs.getInt("ID_Kich_Thuoc"));
               
                kt.setTenKT(rs.getString("Ten_Kich_Thuoc"));
                kt.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    // Thêm mới kích thước
    public boolean add(KichThuoc kt) {
        String query = "INSERT INTO Kich_Thuoc ( Ten_Kich_Thuoc, Trang_Thai) VALUES (?, 1)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
           
            ps.setString(1, kt.getTenKT());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Cập nhật kích thước
    public boolean update(KichThuoc kt) {
        String query = "UPDATE Kich_Thuoc SET  Ten_Kich_Thuoc = ? WHERE ID_Kich_Thuoc = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, kt.getTenKT());
            ps.setInt(2, kt.getId());
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
        String query = "UPDATE Kich_Thuoc SET Trang_Thai = 0 WHERE ID_Kich_Thuoc = ?";
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

    // Khôi phục kích thước đã bị ẩn (Trang_Thai = 0 -> 1)
    public boolean restore(int id) {
        String query = "UPDATE Kich_Thuoc SET Trang_Thai = 1 WHERE ID_Kich_Thuoc = ?";
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
    public int getIdKichThuoc(String tenKichThuoc) {
    String sql = "SELECT ID_Kich_Thuoc FROM Kich_Thuoc WHERE Ten_Kich_Thuoc = ?";
    try (Connection conn = Dbconnect.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, tenKichThuoc);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("ID_Kich_Thuoc"); // Sửa lại đúng tên cột
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return -1; // Trả về -1 nếu không tìm thấy
}

    // Tìm kiếm theo tên kích thước (cả dữ liệu ẩn lẫn hiển thị)
    // tim theo ten
    public ArrayList<KichThuoc> getTimKT(String keyword) {
        ArrayList<KichThuoc> list = new ArrayList<>();
        String query = "SELECT * FROM Kich_Thuoc WHERE Trang_Thai = 1 AND LOWER(Ten_Kich_Thuoc) LIKE LOWER(?)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                KichThuoc kt = new KichThuoc();
                kt.setId(rs.getInt("ID_Kich_Thuoc"));

                kt.setTenKT(rs.getString("Ten_Kich_Thuoc"));
                kt.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(kt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    public boolean checkTrung(String tenKT){
        for (KichThuoc kt : getAll()) {
            if(kt.getTenKT().equalsIgnoreCase(tenKT)){
                return true;
            }
        }
        return false;
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
