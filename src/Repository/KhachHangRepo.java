package Repository;

import Model.KhachHangModel;
import duantest.DBConnect.Dbconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhachHangRepo {

    // Lấy danh sách tất cả khách hàng
    public ArrayList<KhachHangModel> getAllKhachHang() {
        ArrayList<KhachHangModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Trang_Thai=1";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel(
                        rs.getInt("ID_Khach_Hang"), // Lấy ID tự sinh
                        rs.getString("Ma_Khach_Hang"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi")
                );
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
      public ArrayList<KhachHangModel> getAllKhachHangXoa() {
        ArrayList<KhachHangModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Trang_Thai=0";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel(
                        rs.getInt("ID_Khach_Hang"), // Lấy ID tự sinh
                        rs.getString("Ma_Khach_Hang"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi")
                );
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm khách hàng mới
    public boolean insertKhachHang(KhachHangModel kh) {
        int newID = getNextAvailableID();  // Tìm ID bị xóa để tái sử dụng
        String sql = "SET IDENTITY_INSERT Khach_Hang ON; "
                + "INSERT INTO Khach_Hang (ID_Khach_Hang, Ho_Ten, Gioi_Tinh, SDT, Dia_Chi) VALUES (?, ?, ?, ?, ?);"
                + "SET IDENTITY_INSERT Khach_Hang OFF;";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, newID);
            ps.setString(2, kh.getTenKH());
            ps.setBoolean(3, kh.getGioiTinhKH());
            ps.setString(4, kh.getSdtKH());
            ps.setString(5, kh.getDiaChiKH());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNextAvailableID() {
        String sql = "SELECT MIN(A.ID_Khach_Hang + 1) AS NextID "
                + "FROM Khach_Hang A "
                + "LEFT JOIN Khach_Hang B ON A.ID_Khach_Hang + 1 = B.ID_Khach_Hang "
                + "WHERE B.ID_Khach_Hang IS NULL";

        String sqlMaxID = "SELECT MAX(ID_Khach_Hang) AS MaxID FROM Khach_Hang"; // Lấy ID lớn nhất hiện có

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            if (rs.next() && rs.getInt("NextID") > 0) {
                return rs.getInt("NextID");  // Trả về ID nhỏ nhất bị xóa
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Nếu không có ID nào bị xóa, lấy MAX(ID) + 1
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement psMax = con.prepareStatement(sqlMaxID);  ResultSet rsMax = psMax.executeQuery()) {

            if (rsMax.next()) {
                return rsMax.getInt("MaxID") + 1;  // Lấy ID cao nhất +1
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1; // Nếu bảng rỗng, bắt đầu từ 1
    }

    // Cập nhật thông tin khách hàng
    public boolean updateKhachHang(KhachHangModel kh) {
        String sql = "UPDATE Khach_Hang SET Ho_Ten = ?,Gioi_Tinh = ?, Dia_Chi = ?,SDT = ? WHERE ID_Khach_Hang=?  ";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getTenKH());
            ps.setBoolean(2, kh.getGioiTinhKH());
            ps.setString(3, kh.getDiaChiKH());
            ps.setString(4, kh.getSdtKH());
            ps.setInt(5, kh.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa khách hàng
    public boolean deleteKhachHang(int idKh) {
        String sql = "UPDATE Khach_Hang SET Trang_Thai = 0 WHERE ID_Khach_Hang = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKh);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean KhoiPhucKhachHang(int idKh) {
        String sql = "UPDATE Khach_Hang SET Trang_Thai = 1 WHERE ID_Khach_Hang = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idKh);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm khách hàng theo tên hoặc số điện thoại
    public ArrayList<KhachHangModel> searchKhachHang(String keyword, String gioiTinh) {
        ArrayList<KhachHangModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Khach_Hang WHERE Trang_Thai=1 AND (LOWER(Ho_Ten) LIKE LOWER(?) OR LOWER(SDT) LIKE LOWER(?))";

        // Kiểm tra điều kiện "Tất Cả" đúng
        boolean locGioiTinh = !gioiTinh.equalsIgnoreCase("Tất Cả");
        if (locGioiTinh) {
            sql += " AND Gioi_Tinh = ?";
        }

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ps.setString(2, "%" + keyword.toLowerCase() + "%");

            if (locGioiTinh) {
                ps.setBoolean(3, gioiTinh.equalsIgnoreCase("Nam"));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KhachHangModel kh = new KhachHangModel(
                        rs.getInt("ID_Khach_Hang"),
                        rs.getString("Ma_Khach_Hang"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi")
                );
                list.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức kiểm tra số điện thoại đã tồn tại trong cơ sở dữ liệu
    public boolean existsPhoneNumber(String phoneNumber) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM Khach_Hang WHERE SDT = ?"; // Đã sửa thành Khach_Hang

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0; // Nếu có kết quả trả về > 0 nghĩa là số điện thoại đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

}
