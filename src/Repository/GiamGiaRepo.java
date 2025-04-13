package Repository;

import Model.GiamGiaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;
import java.math.BigDecimal;
import java.util.List;

public class GiamGiaRepo {

    // Lấy danh sách tất cả chương trình giảm giá
    public ArrayList<GiamGiaModel> getAllGiamGia() {
        ArrayList<GiamGiaModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Giam_Gia WHERE Trang_Thai=1";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GiamGiaModel gg = new GiamGiaModel(
                        rs.getInt("ID_Giam_Gia"),
                        rs.getString("Ma_Giam_Gia"),
                        rs.getString("Ten_Chuong_Trinh"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc"),
                        rs.getInt("So_Luong"),
                        rs.getBoolean("Kieu_Giam"),
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

    // Lấy danh sách tất cả chương trình giảm giá
    public ArrayList<GiamGiaModel> getAllGiamGiaNgung() {
        ArrayList<GiamGiaModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Giam_Gia WHERE Trang_Thai=0 ";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GiamGiaModel gg = new GiamGiaModel(
                        rs.getInt("ID_Giam_Gia"),
                        rs.getString("Ma_Giam_Gia"),
                        rs.getString("Ten_Chuong_Trinh"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc"),
                        rs.getInt("So_Luong"),
                        rs.getBoolean("Kieu_Giam"),
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
    public boolean insertGiamGia(GiamGiaModel gg) {
        String sql = "INSERT INTO Giam_Gia (Ten_Chuong_Trinh, Ngay_Tao, Ngay_Bat_Dau, Ngay_Ket_Thuc, So_Luong, Kieu_Giam, Gia_Tri_DH_Toi_Thieu, Muc_Gia_Giam, Muc_Gia_Giam_Toi_Da, Trang_Thai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, gg.getTenChuongTrinh());
            ps.setDate(2, new java.sql.Date(gg.getNgayTao().getTime())); // Đã thêm Ngay_Tao
            ps.setDate(3, new java.sql.Date(gg.getNgayBatDau().getTime()));
            ps.setDate(4, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            ps.setInt(5, gg.getSoLuong());
            ps.setBoolean(6, gg.getKieuGiam());
            ps.setBigDecimal(7, gg.getGiaTriDHToiThieu());
            ps.setBigDecimal(8, gg.getMucGiaGiam());
            ps.setBigDecimal(9, gg.getMucGiaGiamToiDa());
            ps.setBoolean(10, gg.getTrangThai());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật chương trình giảm giá
    public boolean updateGiamGia(GiamGiaModel gg) {
        String sql = "UPDATE Giam_Gia SET Ten_Chuong_Trinh = ?, Ngay_Tao = ?, Ngay_Bat_Dau = ?, Ngay_Ket_Thuc = ?, So_Luong = ?, Kieu_Giam = ?, Gia_Tri_DH_Toi_Thieu = ?, Muc_Gia_Giam = ?, Muc_Gia_Giam_Toi_Da = ?, Trang_Thai = ? WHERE ID_Giam_Gia = ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, gg.getTenChuongTrinh());

            ps.setDate(2, new java.sql.Date(gg.getNgayTao().getTime())); // Thêm set Ngay_Tao
            ps.setDate(3, new java.sql.Date(gg.getNgayBatDau().getTime()));
            ps.setDate(4, new java.sql.Date(gg.getNgayKetThuc().getTime()));
            ps.setInt(5, gg.getSoLuong());
            ps.setBoolean(6, gg.getKieuGiam());
            ps.setBigDecimal(7, gg.getGiaTriDHToiThieu());
            ps.setBigDecimal(8, gg.getMucGiaGiam());
            ps.setBigDecimal(9, gg.getMucGiaGiamToiDa());
            ps.setBoolean(10, gg.getTrangThai());
            ps.setInt(11, gg.getId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa chương trình giảm giá
    public boolean deleteGiamGia(int idGG) {
        String sql = "UPDATE Giam_Gia SET Trang_Thai = 0 WHERE ID_Giam_Gia = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idGG);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean khôiPhucGiamGia(int idGG) {
        String sql = "UPDATE Giam_Gia SET Trang_Thai = 1 WHERE ID_Giam_Gia = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idGG);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm chương trình giảm giá theo all còn
    public ArrayList<GiamGiaModel> searchGiamGiaCon(String keyword) {
        ArrayList<GiamGiaModel> list = new ArrayList<>();

        // Câu lệnh SQL cơ bản
        String sql = "SELECT * FROM Giam_Gia WHERE Trang_Thai = 1 AND ("
                + "Ma_Giam_Gia LIKE ? OR Ten_Chuong_Trinh LIKE ? OR Mo_Ta LIKE ? "
                + "OR FORMAT(Ngay_Tao, 'yyyy-MM-dd') LIKE ? OR FORMAT(Ngay_Bat_Dau, 'yyyy-MM-dd') LIKE ? "
                + "OR FORMAT(Ngay_Ket_Thuc, 'yyyy-MM-dd') LIKE ? OR CAST(So_Luong AS VARCHAR) LIKE ? "
                + ")";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            // Đặt các giá trị LIKE cho các cột tương ứng
            for (int i = 1; i <= 7; i++) {  // 7 tham số LIKE cho các cột
                ps.setString(i, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            boolean hasData = false;

            while (rs.next()) {

                GiamGiaModel gg = new GiamGiaModel(
                        rs.getInt("ID_Giam_Gia"),
                        rs.getString("Ma_Giam_Gia"),
                        rs.getString("Ten_Chuong_Trinh"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc"),
                        rs.getInt("So_Luong"),
                        rs.getBoolean("Kieu_Giam"),
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

    // Tìm kiếm chương trình giảm giá theo all hết
    public ArrayList<GiamGiaModel> searchGiamGiaHet(String keyword) {
        ArrayList<GiamGiaModel> list = new ArrayList<>();

        // Câu lệnh SQL cơ bản
        String sql = "SELECT * FROM Giam_Gia WHERE Trang_Thai = 0 AND ("
                + "Ma_Giam_Gia LIKE ? OR Ten_Chuong_Trinh LIKE ? OR Mo_Ta LIKE ? "
                + "OR FORMAT(Ngay_Tao, 'yyyy-MM-dd') LIKE ? OR FORMAT(Ngay_Bat_Dau, 'yyyy-MM-dd') LIKE ? "
                + "OR FORMAT(Ngay_Ket_Thuc, 'yyyy-MM-dd') LIKE ? OR CAST(So_Luong AS VARCHAR) LIKE ? "
                + ")";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            // Đặt các giá trị LIKE cho các cột tương ứng
            for (int i = 1; i <= 7; i++) {  // 7 tham số LIKE cho các cột
                ps.setString(i, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            boolean hasData = false;

            while (rs.next()) {

                GiamGiaModel gg = new GiamGiaModel(
                        rs.getInt("ID_Giam_Gia"),
                        rs.getString("Ma_Giam_Gia"),
                        rs.getString("Ten_Chuong_Trinh"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getDate("Ngay_Bat_Dau"),
                        rs.getDate("Ngay_Ket_Thuc"),
                        rs.getInt("So_Luong"),
                        rs.getBoolean("Kieu_Giam"),
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

    public GiamGiaModel findMaGG(String maGG) {
        String sql = "select Ngay_Bat_Dau,Ngay_Ket_Thuc,So_Luong,Kieu_Giam,Gia_Tri_DH_Toi_Thieu,Muc_Gia_Giam,Muc_Gia_Giam_Toi_Da from Giam_Gia where Ma_Giam_Gia= ? AND Trang_Thai=1";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maGG);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                GiamGiaModel gg = new GiamGiaModel();
                gg.setMaGiamGia(maGG); 
                gg.setTrangThai(true);
                gg.setNgayBatDau(rs.getDate("Ngay_Bat_Dau"));
                gg.setNgayKetThuc(rs.getDate("Ngay_Ket_Thuc"));
                gg.setSoLuong(rs.getInt("So_Luong"));
                gg.setKieuGiam(rs.getBoolean("Kieu_Giam"));
                gg.setGiaTriDHToiThieu(rs.getBigDecimal("Gia_Tri_DH_Toi_Thieu"));
                gg.setMucGiaGiam(rs.getBigDecimal("Muc_Gia_Giam"));
                gg.setMucGiaGiamToiDa(rs.getBigDecimal("Muc_Gia_Giam_Toi_Da"));
                return gg;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
