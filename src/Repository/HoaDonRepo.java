package Repository;

import Model.HoaDon;
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
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> hoaDonList = new ArrayList<>();
        String sql = """
            SELECT hd.ID, hd.MaHD, nv.MaNV, kh.MaKH, hd.NgayTT, hd.TongTien, hd.TrangThai,
                   hd.HinhThucThanhToan, hd.SDT, hd.DiaChi, pt.MaPhuongThucThanhToan
            FROM HoaDon hd
            LEFT JOIN NhanVien nv ON hd.MaNV = nv.MaNV
            LEFT JOIN KhachHang kh ON hd.MaKH = kh.MaKH
            LEFT JOIN PhuongThucThanhToan pt ON hd.MaPhuongThucThanhToan = pt.MaPhuongThucThanhToan;
        """;

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
                hoaDon.setIdHoaDon(rs.getInt("ID"));
                hoaDon.setMaHoaDon(rs.getString("MaHD"));
                hoaDon.setNgayThanhToan(rs.getDate("NgayTT"));
                hoaDon.setTongGia(rs.getBigDecimal("TongTien"));
                hoaDon.setTrangThai(rs.getBoolean("TrangThai"));
                hoaDon.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
                hoaDon.setSdt(rs.getString("SDT"));
                hoaDon.setDiaChi(rs.getString("DiaChi"));
                hoaDon.setIdKhachHang(rs.getInt("MaKH"));
                hoaDon.setIdNhanVien(rs.getInt("MaNV"));
                hoaDon.setIdPhuongThucThanhToan(rs.getInt("MaPhuongThucThanhToan"));

                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonList;
    }

    // Tìm kiếm hóa đơn
    public List<HoaDon> timKiem(String keyword) {
        List<HoaDon> list = new ArrayList<>();
        String sql = """
            SELECT * FROM HoaDon WHERE
            Ma_Hoa_Don LIKE ? OR SDT LIKE ? OR DiaChi LIKE ? OR
            NgayTao LIKE ? OR NgayThanhToan LIKE ? OR
            TongGia LIKE ? OR HinhThucThanhToan LIKE ? OR
            TrangThai LIKE ? OR ID_Khach_Hang LIKE ? OR
            ID_Nhan_Vien LIKE ? OR ID_Phuong_Thuc_Thanh_Toan LIKE ?;
        """;

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            String searchKeyword = "%" + keyword + "%";
            for (int i = 1; i <= 11; i++) {
                ps.setString(i, searchKeyword);
            }

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HoaDon hd = new HoaDon();
                    hd.setIdHoaDon(rs.getInt("ID_Hoa_Don"));
                    hd.setMaHoaDon(rs.getString("Ma_Hoa_Don"));
                    hd.setNgayTao(rs.getDate("NgayTao"));
                    hd.setNgayThanhToan(rs.getDate("NgayThanhToan"));
                    hd.setTongGia(rs.getBigDecimal("TongGia"));
                    hd.setHinhThucThanhToan(rs.getString("HinhThucThanhToan"));
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
    public List<HoaDon> getHoaDonByYear(String year) {
        List<HoaDon> hoaDons = new ArrayList<>();
        // Viết câu truy vấn lọc hóa đơn theo năm, ví dụ:
        String query = "SELECT * FROM Hoa_Don WHERE YEAR(Ngay_Thanh_Toan) = ?";

        try ( Connection con = Dbconnect.getConnection();  
            PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, year);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                HoaDon hoaDon = new HoaDon();
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
    public List<HoaDon> thongKeTheoKhoangThoiGian(Date tu, Date den) {
        List<HoaDon> list = new ArrayList<>();
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
                    HoaDon hd = new HoaDon();
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
}
