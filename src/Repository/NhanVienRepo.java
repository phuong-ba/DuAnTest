package Repository;

import Model.NhanVienModel;
import duantest.DBConnect.Dbconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class NhanVienRepo {

    // Lấy danh sách nhân viên 
    public ArrayList<NhanVienModel> getAllNhanVien() {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Trang_Thai = 1";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienModel nv = new NhanVienModel(
                        rs.getInt("ID_Nhan_Vien"),
                        rs.getString("Ma_Nhan_Vien"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getDate("Ngay_Sinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi"),
                        rs.getString("Chuc_Vu"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Tai_Khoan"),
                        rs.getString("Mat_Khau"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc")
                );
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // nhan vien nghỉ việc
    public ArrayList<NhanVienModel> getAllNhanVienNghiViec() {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Trang_Thai = 0";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienModel nv = new NhanVienModel(
                        rs.getInt("ID_Nhan_Vien"),
                        rs.getString("Ma_Nhan_Vien"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getDate("Ngay_Sinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi"),
                        rs.getString("Chuc_Vu"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Tai_Khoan"),
                        rs.getString("Mat_Khau"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc")
                );
                list.add(nv);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm nhân viên mới
    public boolean insertNhanVien(NhanVienModel nv) {
        int newID = getNextAvailableID();
        // Nếu ngayKetThucNV là null, thì không truyền vào khi thực hiện insert
        java.sql.Date ngayKetThuc = nv.getNgayKetThucNV() != null ? new java.sql.Date(nv.getNgayKetThucNV().getTime()) : null;

        String sql = "SET IDENTITY_INSERT Nhan_Vien ON; "
                + "INSERT INTO Nhan_Vien (ID_Nhan_Vien, Ho_Ten, Gioi_Tinh, Ngay_Sinh, SDT, Dia_Chi, Chuc_Vu, Trang_Thai, Tai_Khoan, Mat_Khau, Ngay_Bat_Dau, Ngay_Ket_Thuc) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); "
                + "SET IDENTITY_INSERT Nhan_Vien OFF;";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newID);

            ps.setString(2, nv.getTenNV());
            ps.setBoolean(3, nv.getGioiTinhNV());
            ps.setDate(4, new java.sql.Date(nv.getNgaySinhNV().getTime()));

            ps.setString(5, nv.getSdtNV());
            ps.setString(6, nv.getDiaChiNV());
            ps.setString(7, nv.getChucVuNV());
            ps.setBoolean(8, nv.getTrangThaiNV());
            ps.setString(9, nv.getTaiKhoanNV());
            ps.setString(10, nv.getMatKhauNV());
            ps.setDate(11, new java.sql.Date(nv.getNgayBatDauNV().getTime()));
            ps.setDate(12, ngayKetThuc);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getNextAvailableID() {
        String sql = "SELECT MIN(A.ID_Nhan_Vien + 1) AS NextID "
                + "FROM Nhan_Vien A "
                + "LEFT JOIN Nhan_Vien B ON A.ID_Nhan_Vien + 1 = B.ID_Nhan_Vien "
                + "WHERE B.ID_Nhan_Vien IS NULL";

        String sqlMaxID = "SELECT MAX(ID_Nhan_Vien) AS MaxID FROM Nhan_Vien"; // Lấy ID lớn nhất hiện có

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

    public boolean updateNhanVien(NhanVienModel nv) {
        String sql = "UPDATE Nhan_Vien SET  Ho_Ten = ?, Gioi_Tinh = ?, Ngay_Sinh = ?, SDT = ?, Dia_Chi = ?, Chuc_Vu = ?, Trang_Thai = ?, Tai_Khoan = ?, Mat_Khau = ?, Ngay_Bat_Dau = ?, Ngay_Ket_Thuc = ? WHERE ID_Nhan_Vien = ?";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nv.getTenNV());
            ps.setBoolean(2, nv.getGioiTinhNV());
            ps.setDate(3, new java.sql.Date(nv.getNgaySinhNV().getTime()));
            ps.setString(4, nv.getSdtNV());
            ps.setString(5, nv.getDiaChiNV());
            ps.setString(6, nv.getChucVuNV());
            ps.setBoolean(7, nv.getTrangThaiNV());
            ps.setString(8, nv.getTaiKhoanNV());
            ps.setString(9, nv.getMatKhauNV());
            ps.setDate(10, new java.sql.Date(nv.getNgayBatDauNV().getTime()));

            // Kiểm tra nếu ngày kết thúc là null, thì truyền vào null
            if (nv.getNgayKetThucNV() != null) {
                ps.setDate(11, new java.sql.Date(nv.getNgayKetThucNV().getTime()));
            } else {
                ps.setNull(11, java.sql.Types.DATE); // Truyền null nếu Ngay_Ket_Thuc là null
            }

            ps.setInt(12, nv.getIdNV()); // ID của nhân viên cần cập nhật

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteNhanVien(int idNV,java.sql.Date ngayKetThuc) {
        // Cập nhật trạng thái nhân viên thành 'ẩn' (giả sử 'Trang_Thai' = false để ẩn)
        String sql = "UPDATE Nhan_Vien SET Trang_Thai = 0,Ngay_Ket_Thuc = ? WHERE ID_Nhan_Vien = ?";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, ngayKetThuc);
            ps.setInt(2, idNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm nhân viên theo theo ALL trừ boolean
    public ArrayList<NhanVienModel> searchNhanVien(String keyword, String gioiTinh) {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Trang_Thai=1 AND ("
                + "LOWER(Ma_Nhan_Vien) LIKE LOWER(?) OR "
                + "LOWER(Ho_Ten) LIKE LOWER(?) OR "
                + "LOWER(SDT) LIKE LOWER(?) OR "
                + "LOWER(Dia_Chi) LIKE LOWER(?) OR "
                + "LOWER(Tai_Khoan) LIKE LOWER(?) OR "
                + "LOWER(Mat_Khau) LIKE LOWER(?) OR "
                + "FORMAT(Ngay_Sinh, 'yyyy-MM-dd') LIKE ? OR "
                + "FORMAT(Ngay_Bat_Dau, 'yyyy-MM-dd') LIKE ? OR "
                + "FORMAT(Ngay_Ket_Thuc, 'yyyy-MM-dd') LIKE ?"
                + ")";

        // Kiểm tra điều kiện "Tất Cả" đúng
        boolean locGioiTinh = !gioiTinh.equalsIgnoreCase("Tất Cả");
        if (locGioiTinh) {
            sql += " AND Gioi_Tinh = ?";
        }

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // Thêm từ khóa vào các tham số của câu truy vấn
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ps.setString(2, "%" + keyword.toLowerCase() + "%");
            ps.setString(3, "%" + keyword.toLowerCase() + "%");
            ps.setString(4, "%" + keyword.toLowerCase() + "%");
            ps.setString(5, "%" + keyword.toLowerCase() + "%");
            ps.setString(6, "%" + keyword.toLowerCase() + "%");
            ps.setString(7, "%" + keyword.toLowerCase() + "%");
            ps.setString(8, "%" + keyword.toLowerCase() + "%");
            ps.setString(9, "%" + keyword.toLowerCase() + "%");

            // Nếu có điều kiện lọc theo giới tính
            if (locGioiTinh) {
                ps.setBoolean(10, gioiTinh.equalsIgnoreCase("Nam"));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienModel nv = new NhanVienModel(
                        rs.getInt("ID_Nhan_Vien"),
                        rs.getString("Ma_Nhan_Vien"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getDate("Ngay_Sinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi"),
                        rs.getString("Chuc_Vu"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Tai_Khoan"),
                        rs.getString("Mat_Khau"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tìm kiếm khách hàng theo trừ boolean
    public ArrayList<NhanVienModel> searchNhanVienNghi(String keyword, String gioiTinh) {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Nhan_Vien WHERE Trang_Thai=0 AND ("
                + "LOWER(Ma_Nhan_Vien) LIKE LOWER(?) OR "
                + "LOWER(Ho_Ten) LIKE LOWER(?) OR "
                + "LOWER(SDT) LIKE LOWER(?) OR "
                + "LOWER(Dia_Chi) LIKE LOWER(?) OR "
                + "LOWER(Tai_Khoan) LIKE LOWER(?) OR "
                + "LOWER(Mat_Khau) LIKE LOWER(?) OR "
                + "FORMAT(Ngay_Sinh, 'yyyy-MM-dd') LIKE ? OR "
                + "FORMAT(Ngay_Bat_Dau, 'yyyy-MM-dd') LIKE ? OR "
                + "FORMAT(Ngay_Ket_Thuc, 'yyyy-MM-dd') LIKE ?"
                + ")";

        // Kiểm tra điều kiện "Tất Cả" đúng
        boolean locGioiTinh = !gioiTinh.equalsIgnoreCase("Tất Cả");
        if (locGioiTinh) {
            sql += " AND Gioi_Tinh = ?";
        }

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // Thêm từ khóa vào các tham số của câu truy vấn
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            ps.setString(2, "%" + keyword.toLowerCase() + "%");
            ps.setString(3, "%" + keyword.toLowerCase() + "%");
            ps.setString(4, "%" + keyword.toLowerCase() + "%");
            ps.setString(5, "%" + keyword.toLowerCase() + "%");
            ps.setString(6, "%" + keyword.toLowerCase() + "%");
            ps.setString(7, "%" + keyword.toLowerCase() + "%");
            ps.setString(8, "%" + keyword.toLowerCase() + "%");
            ps.setString(9, "%" + keyword.toLowerCase() + "%");

            // Nếu có điều kiện lọc theo giới tính
            if (locGioiTinh) {
                ps.setBoolean(10, gioiTinh.equalsIgnoreCase("Nam"));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVienModel nv = new NhanVienModel(
                        rs.getInt("ID_Nhan_Vien"),
                        rs.getString("Ma_Nhan_Vien"),
                        rs.getString("Ho_Ten"),
                        rs.getBoolean("Gioi_Tinh"),
                        rs.getDate("Ngay_Sinh"),
                        rs.getString("SDT"),
                        rs.getString("Dia_Chi"),
                        rs.getString("Chuc_Vu"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Tai_Khoan"),
                        rs.getString("Mat_Khau"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc")
                );
                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức kiểm tra số điện thoại đã tồn tại trong cơ sở dữ liệu
    public boolean existsPhoneNumber(String phoneNumber) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM Nhan_Vien WHERE SDT = ?"; // Đã sửa thành Khach_Hang

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0; // Nếu có kết quả trả về > 0 nghĩa là số điện thoại đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    public boolean existsAccount(String taiKhoanNV, String matKhauNV) {
        String sql = "SELECT COUNT(*) FROM Nhan_Vien WHERE Tai_Khoan = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, taiKhoanNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu có ít nhất một kết quả trả về thì tài khoản và mật khẩu đã tồn tại
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean khoiPhuc(int idNV) {
        String sql = "UPDATE Nhan_Vien SET Trang_Thai = 1,Ngay_Ket_Thuc = NULL WHERE ID_Nhan_Vien = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getIdNhanVien(String maNV) {
        String sql = "SELECT ID_Nhan_Vien FROM Nhan_Vien WHERE Ma_Nhan_Vien = ?";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNV);  // Gán tên màu vào tham số

            // Thực hiện truy vấn
            ResultSet rs = stmt.executeQuery();

            // Kiểm tra kết quả và trả về ID_Mau_Sac
            if (rs.next()) {
                return rs.getInt("ID_Nhan_Vien"); // Trả về ID_Mau_Sac nếu tìm thấy
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

}
