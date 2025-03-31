package Repository;

import Model.GiamGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class GiamGiaRepo {

    // Lấy danh sách tất cả chương trình giảm giá
    public ArrayList<GiamGia> getAllGiamGia() {
        ArrayList<GiamGia> list = new ArrayList<>();
        String sql = "SELECT * FROM Giam_Gia";
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GiamGia gg = new GiamGia(
                        rs.getInt("ID_Giam_Gia"),
                        rs.getString("Ma_Giam_Gia"),
                        rs.getString("Ten_Chuong_Trinh"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc"),
                        rs.getInt("So_Luong"),
                        rs.getString("Kieu_Giam"),
                        rs.getBigDecimal("Gia_Tri_DH_Toi_Thieu"),
                        rs.getBigDecimal("Muc_Gia_Giam"),
                        rs.getBigDecimal("Muc_Gia_Giam_Toi_Da"),
                        rs.getBoolean("Trang_Thai")
                );
                list.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm chương trình giảm giá
    public boolean insertGiamGia(GiamGia gg) {
        String sql = "INSERT INTO Giam_Gia (Ma_Giam_Gia, Ten_Chuong_Trinh, Mo_Ta, Ngay_Bat_Dau, Ngay_Ket_Thuc, So_Luong, Kieu_Giam, Gia_Tri_DH_Toi_Thieu, Muc_Gia_Giam, Muc_Gia_Giam_Toi_Da, Trang_Thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gg.getMaGiamGia());
            ps.setString(2, gg.getTenChuongTrinh());
            ps.setString(3, gg.getMoTa());
            ps.setDate(4, new java.sql.Date(gg.getNgayBatDau().getTime()));
            ps.setDate(5, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            ps.setInt(6, gg.getSoLuong());
            ps.setString(7, gg.getKieuGiam());
            ps.setBigDecimal(8, gg.getGiaTriDHToiThieu());
            ps.setBigDecimal(9, gg.getMucGiaGiam());
            ps.setBigDecimal(10, gg.getMucGiaGiamToiDa());
            ps.setBoolean(11, gg.getTrangThai());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật chương trình giảm giá
    public boolean updateGiamGia(GiamGia gg) {
        String sql = "UPDATE Giam_Gia SET Ten_Chuong_Trinh = ?, Mo_Ta = ?, Ngay_Bat_Dau = ?, Ngay_Ket_Thuc = ?, So_Luong = ?, Kieu_Giam = ?, Gia_Tri_DH_Toi_Thieu = ?, Muc_Gia_Giam = ?, Muc_Gia_Giam_Toi_Da = ?, Trang_Thai = ? WHERE Ma_Giam_Gia = ?";
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gg.getTenChuongTrinh());
            ps.setString(2, gg.getMoTa());
            ps.setDate(3, new java.sql.Date(gg.getNgayBatDau().getTime()));
            ps.setDate(4, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            ps.setInt(5, gg.getSoLuong());
            ps.setString(6, gg.getKieuGiam());
            ps.setBigDecimal(7, gg.getGiaTriDHToiThieu());
            ps.setBigDecimal(8, gg.getMucGiaGiam());
            ps.setBigDecimal(9, gg.getMucGiaGiamToiDa());
            ps.setBoolean(10, gg.getTrangThai());
            ps.setString(11, gg.getMaGiamGia());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa chương trình giảm giá
    public boolean deleteGiamGia(String maGiamGia) {
        String sql = "DELETE FROM Giam_Gia WHERE Ma_Giam_Gia = ?";
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maGiamGia);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm chương trình giảm giá theo mã hoặc tên
    public ArrayList<GiamGia> searchGiamGia(String keyword) {
        ArrayList<GiamGia> list = new ArrayList<>();
        String sql = "SELECT * FROM Giam_Gia WHERE Ma_Giam_Gia LIKE ? OR Ten_Chuong_Trinh LIKE ?";
        try (Connection con = Dbconnect.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                GiamGia gg = new GiamGia(
                        rs.getInt("ID_Giam_Gia"),
                        rs.getString("Ma_Giam_Gia"),
                        rs.getString("Ten_Chuong_Trinh"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc"),
                        rs.getInt("So_Luong"),
                        rs.getString("Kieu_Giam"),
                        rs.getBigDecimal("Gia_Tri_DH_Toi_Thieu"),
                        rs.getBigDecimal("Muc_Gia_Giam"),
                        rs.getBigDecimal("Muc_Gia_Giam_Toi_Da"),
                        rs.getBoolean("Trang_Thai")
                );
                list.add(gg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
