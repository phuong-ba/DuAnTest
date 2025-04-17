package Repository;

import Model.HoaDonModel;
import Model.HoaDonChiTiet;
import duantest.DBConnect.Dbconnect;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonRepo {

    // Lấy tất cả hóa đơn
    public List<HoaDonModel> getAllHoaDon() {
        List<HoaDonModel> hoaDonList = new ArrayList<>();
        String sql = """
    SELECT hd.ID_Hoa_Don, hd.Ma_Hoa_Don, nv.Ma_Nhan_Vien, kh.Ma_Khach_Hang, 
           hd.Ngay_Thanh_Toan, hd.Thanh_Tien, hd.Trang_Thai,
           hd.SDT, hd.Dia_Chi, pt.ID_Thanh_Toan, pt.Loai_Thanh_Toan
    FROM Hoa_Don hd
    LEFT JOIN Nhan_Vien nv ON hd.ID_Nhan_Vien = nv.ID_Nhan_Vien
    LEFT JOIN Khach_Hang kh ON hd.ID_Khach_Hang = kh.ID_Khach_Hang
    LEFT JOIN Phuong_Thuc_Thanh_Toan pt ON hd.ID_Thanh_Toan = pt.ID_Thanh_Toan;
""";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonModel hoaDon = new HoaDonModel();
                hoaDon.setIdHoaDon(rs.getInt("ID_Hoa_Don"));
                hoaDon.setMaHoaDon(rs.getString("Ma_Hoa_Don"));
                hoaDon.setNgayThanhToan(rs.getDate("Ngay_Thanh_Toan"));
                hoaDon.setThanhTien(rs.getBigDecimal("Thanh_Tien"));
                hoaDon.setTrangThai(rs.getBoolean("Trang_Thai"));
                hoaDon.setSdt(rs.getString("SDT"));
                hoaDon.setDiaChi(rs.getString("Dia_Chi"));
                hoaDon.setMaKhachHang(rs.getString("Ma_Khach_Hang"));
                hoaDon.setMaNhanVien(rs.getString("Ma_Nhan_Vien"));
                hoaDon.setLoaiThanhToan(rs.getString("Loai_Thanh_Toan"));

                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

    public List<HoaDonModel> getAllHoaDon0() {
        List<HoaDonModel> hoaDonList = new ArrayList<>();
        String sql = """
    SELECT hd.ID_Hoa_Don, hd.Ma_Hoa_Don, nv.Ma_Nhan_Vien, kh.Ma_Khach_Hang, 
           hd.Ngay_Thanh_Toan, hd.Thanh_Tien, hd.Trang_Thai,
           hd.SDT, hd.Dia_Chi,hd.Tong_Gia_SP, pt.ID_Thanh_Toan, pt.Loai_Thanh_Toan
    FROM Hoa_Don hd
    LEFT JOIN Nhan_Vien nv ON hd.ID_Nhan_Vien = nv.ID_Nhan_Vien
    LEFT JOIN Khach_Hang kh ON hd.ID_Khach_Hang = kh.ID_Khach_Hang
    LEFT JOIN Phuong_Thuc_Thanh_Toan pt ON hd.ID_Thanh_Toan = pt.ID_Thanh_Toan
    WHERE hd.Trang_Thai = 0;
""";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonModel hoaDon = new HoaDonModel();
                hoaDon.setIdHoaDon(rs.getInt("ID_Hoa_Don"));
                hoaDon.setMaHoaDon(rs.getString("Ma_Hoa_Don"));
                hoaDon.setNgayThanhToan(rs.getDate("Ngay_Thanh_Toan"));
                hoaDon.setThanhTien(rs.getBigDecimal("Thanh_Tien"));
                hoaDon.setTrangThai(rs.getBoolean("Trang_Thai"));
                hoaDon.setSdt(rs.getString("SDT"));
                hoaDon.setDiaChi(rs.getString("Dia_Chi"));
                hoaDon.setTongGia(rs.getBigDecimal("Tong_Gia_SP"));
                hoaDon.setMaKhachHang(rs.getString("Ma_Khach_Hang"));
                hoaDon.setMaNhanVien(rs.getString("Ma_Nhan_Vien"));
                hoaDon.setLoaiThanhToan(rs.getString("Loai_Thanh_Toan"));

                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

    // Tìm kiếm hóa đơn
    public List<HoaDonModel> timKiem(String keyword) {
        List<HoaDonModel> list = new ArrayList<>();
        String sql = """
            SELECT * FROM HoaDon WHERE
                LOWER(Ma_Hoa_Don) LIKE LOWER(?) OR
                LOWER(SDT) LIKE LOWER(?) OR
                LOWER(Dia_Chi) LIKE LOWER(?) OR
                FORMAT(Ngay_Tao, 'yyyy-MM-dd') LIKE ? OR
                FORMAT(Ngay_Thanh_Toan, 'yyyy-MM-dd') LIKE ? OR
                CAST(Thanh_Tien AS VARCHAR) LIKE ? OR
                LOWER(Hinh_Thuc_Thanh_Toan) LIKE LOWER(?) OR
                LOWER(Trang_Thai) LIKE LOWER(?) OR
                LOWER(ID_Khach_Hang) LIKE LOWER(?) OR
                LOWER(ID_Nhan_Vien) LIKE LOWER(?) OR
                LOWER(ID_Thanh_Toan) LIKE LOWER(?);
        """;

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 8; i++) {
                ps.setString(i, searchKeyword);
            }

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonModel hd = new HoaDonModel();
                    hd.setIdHoaDon(rs.getInt("ID_Hoa_Don"));
                    hd.setMaHoaDon(rs.getString("Ma_Hoa_Don"));
                    hd.setNgayTao(rs.getDate("NgayTao"));
                    hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                    hd.setTongGia(rs.getBigDecimal("TongGia"));
                    hd.setSdt(rs.getString("SDT"));
                    hd.setDiaChi(rs.getString("DiaChi"));
                    hd.setTrangThai(rs.getBoolean("TrangThai"));
                    hd.setIdKhachHang(rs.getInt("ID_Khach_Hang"));
                    hd.setIdNhanVien(rs.getInt("ID_Nhan_Vien"));
                    hd.setIdPhuongThucThanhToan(rs.getInt("ID_Phuong_Thuc_Thanh_Toan"));
                    list.add(hd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thống kê doanh thu theo năm
    public List<HoaDonModel> getHoaDonByYear(String year) {
        List<HoaDonModel> hoaDons = new ArrayList<>();
        // Viết câu truy vấn lọc hóa đơn theo năm, ví dụ:
        String query = "SELECT * FROM Hoa_Don WHERE YEAR(Ngay_Thanh_Toan) = ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, year);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HoaDonModel hoaDon = new HoaDonModel();
                hoaDon.setIdHoaDon(rs.getInt("ID_Hoa_Don"));
                hoaDon.setMaHoaDon(rs.getString("Ma_Hoa_Don"));
                hoaDon.setNgayThanhToan(rs.getDate("Ngay_Thanh_Toan"));
                hoaDon.setTongGia(rs.getBigDecimal("Tong_Gia"));
                hoaDon.setTrangThai(rs.getBoolean("Trang_Thai"));
                hoaDons.add(hoaDon);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return hoaDons;
    }

    // Thống kê theo khoảng thời gian
    public List<HoaDonModel> thongKeTheoKhoangThoiGian(Date tu, Date den) {
        List<HoaDonModel> list = new ArrayList<>();
        String sql = """
            SELECT Ngay_Tao, SUM(Tong_Gia) AS Doanh_Thu
            FROM Hoa_Don
            WHERE Ngay_Tao BETWEEN ? AND ? AND Trang_Thai = 1
            GROUP BY Ngay_Tao
            ORDER BY Ngay_Tao
        """;

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(tu.getTime()));
            ps.setDate(2, new java.sql.Date(den.getTime()));

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDonModel hd = new HoaDonModel();
                    hd.setNgayTao(rs.getDate("Ngay_Tao"));
                    hd.setTongGia(rs.getBigDecimal("Tong_Gia"));
                    list.add(hd);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // hoa don chi tiết
    public List<HoaDonChiTiet> getAllHoaDonChiTiet() {
        List<HoaDonChiTiet> hoaDonList = new ArrayList<>();
        String sql = """
    SELECT 
            hdc.ID_HDCT, 
            hdc.So_Luong, 
            hdc.Gia_Sau_Giam, 
            hdc.So_Tien_Sau_Giam,
            hd.Ma_Hoa_Don, 
        	hd.Thanh_Tien,
            sp.Ma_San_Pham, 
            sp.Ten_San_Pham,
        	sp.Gia_Ban                     
        FROM Hoa_Don_Chi_Tiet hdc
        LEFT JOIN Hoa_Don hd ON hdc.ID_Hoa_Don = hd.ID_Hoa_Don
        LEFT JOIN San_Pham sp ON hdc.ID_San_Pham = sp.ID_San_Pham;        
""";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDonChiTiet hoaDonCT = new HoaDonChiTiet();
                hoaDonCT.setIdHoaDon(rs.getInt("ID_HDCT"));
                hoaDonCT.setMaHoaDon(rs.getString("Ma_Hoa_Don"));
                hoaDonCT.setMaSanPham(rs.getString("Ma_San_Pham"));
                hoaDonCT.setTenSanPham(rs.getString("Ten_San_Pham"));
                hoaDonCT.setGiaBan(rs.getBigDecimal("Gia_Ban"));
                hoaDonCT.setSoLuong(rs.getInt("So_Luong"));
                hoaDonCT.setThanhTien(rs.getBigDecimal("Thanh_Tien"));
                hoaDonCT.setGiaSauGiam(rs.getBigDecimal("Gia_Sau_Giam"));
                hoaDonCT.setSoTienSauGiam(rs.getBigDecimal("So_Tien_Sau_Giam"));
                hoaDonList.add(hoaDonCT);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

    public boolean insertHoaDon(HoaDonModel hd) {
        String sql = """
INSERT INTO Hoa_Don (
    Ngay_Thanh_Toan,Tong_Gia_SP,SDT,Dia_Chi,Trang_Thai,Thanh_Tien,
    ID_Khach_Hang,ID_Nhan_Vien, ID_Thanh_Toan
)
VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)
""";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setBigDecimal(2, hd.getTongGia());
            ps.setString(3, hd.getSdt());
            ps.setString(4, hd.getDiaChi());
            ps.setBoolean(5, hd.getTrangThai());
            if (hd.getThanhTien() != null) {
                ps.setBigDecimal(6, hd.getThanhTien());
            } else {
                ps.setNull(6, java.sql.Types.DECIMAL);
            }

            ps.setInt(7, hd.getIdKhachHang());
            ps.setInt(8, hd.getIdNhanVien());
            ps.setInt(9, hd.getIdThanhToan());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean existsPhoneNumber(String phoneNumber) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM Hoa_Don WHERE SDT = ?";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exists;
    }

    public boolean updateHoaDon(HoaDonModel hd) {
        String sql = "UPDATE Hoa_Don SET "
                + "Ngay_Thanh_Toan = ?, "
                + "Tong_Gia_SP = ?, "
                + "SDT = ?, "
                + "Dia_Chi = ?, "
                + "Trang_Thai = 1, "
                + "Thanh_Tien = ?, "
                + "ID_Khach_Hang = ?, "
                + "ID_Nhan_Vien = ?, "
                + "ID_Thanh_Toan = ? "
                + "WHERE Ma_Hoa_Don = ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(hd.getNgayThanhToan().getTime()));
            ps.setBigDecimal(2, hd.getTongGia());
            ps.setString(3, hd.getSdt());
            ps.setString(4, hd.getDiaChi());

            if (hd.getThanhTien() != null) {
                ps.setBigDecimal(5, hd.getThanhTien());
            } else {
                ps.setNull(5, java.sql.Types.DECIMAL);
            }

            ps.setInt(6, hd.getIdKhachHang());
            ps.setInt(7, hd.getIdNhanVien());
            ps.setInt(8, hd.getIdThanhToan());
            ps.setString(9, hd.getMaHoaDon());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
