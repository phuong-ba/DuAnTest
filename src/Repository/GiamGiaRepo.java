package Repository;

import Model.GiamGiaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

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
    
    try (Connection con = Dbconnect.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        
       
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
    
    try (Connection con = Dbconnect.getConnection();  
         PreparedStatement ps = con.prepareStatement(sql)) {
        
     
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
    public boolean deleteGiamGia(String maGiamGia) {
        String sql = "UPDATE FROM Giam_Gia WHERE ID_Giam_Gia = ? Trang_Thai=0";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maGiamGia);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm chương trình giảm giá theo all còn
    public ArrayList<GiamGiaModel> searchGiamGiaCon(String keyword) {
        ArrayList<GiamGiaModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Giam_Gia WHERE Ma_Giam_Gia COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR Ten_Chuong_Trinh COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR Mo_Ta COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR CONVERT(VARCHAR, Ngay_Tao, 120) LIKE ? "
                + "OR CONVERT(VARCHAR, Ngay_Bat_Dau, 120) LIKE ? "
                + "OR CONVERT(VARCHAR, Ngay_Ket_Thuc, 120) LIKE ? "
                + "OR CAST(So_Luong AS VARCHAR) LIKE ? "
                + "OR Kieu_Giam COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR CAST(Gia_Tri_DH_Toi_Thieu AS VARCHAR) LIKE ? "
                + "OR CAST(Muc_Gia_Giam AS VARCHAR) LIKE ? "
                + "OR CAST(Muc_Gia_Giam_Toi_Da AS VARCHAR) LIKE ? "
                + "OR CAST(Trang_Thai AS VARCHAR) LIKE ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 1; i <= 12; i++) {
                ps.setString(i, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
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
        String sql = "SELECT * FROM Giam_Gia WHERE Ma_Giam_Gia COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR Ten_Chuong_Trinh COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR Mo_Ta COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR CONVERT(VARCHAR, Ngay_Tao, 120) LIKE ? "
                + "OR CONVERT(VARCHAR, Ngay_Bat_Dau, 120) LIKE ? "
                + "OR CONVERT(VARCHAR, Ngay_Ket_Thuc, 120) LIKE ? "
                + "OR CAST(So_Luong AS VARCHAR) LIKE ? "
                + "OR Kieu_Giam COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "OR CAST(Gia_Tri_DH_Toi_Thieu AS VARCHAR) LIKE ? "
                + "OR CAST(Muc_Gia_Giam AS VARCHAR) LIKE ? "
                + "OR CAST(Muc_Gia_Giam_Toi_Da AS VARCHAR) LIKE ? "
                + "OR CAST(Trang_Thai AS VARCHAR) LIKE ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 1; i <= 12; i++) {
                ps.setString(i, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
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

}
