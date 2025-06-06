package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import duantest.DBConnect.Dbconnect;
import Model.SanPhamModel;
import Model.SanPhamModel2;

public class SanPhamRepo {

    // Thêm sản phẩm
    public boolean insertSanPham(SanPhamModel2 sp) {
        String sql = "INSERT INTO San_Pham (Ten_San_Pham, So_Luong_Ton, Mo_Ta, Ngay_Tao, Gia_Nhap, Gia_Ban, ID_Mau_Sac, ID_Kich_Thuoc, ID_Chat_Lieu, ID_Xuat_Xu, ID_Thuong_Hieu) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getSoLuongTon());
            ps.setString(3, sp.getMoTa());
            ps.setDate(4, new java.sql.Date(sp.getNgayTao().getTime()));
            ps.setBigDecimal(5, sp.getGiaNhap());
            ps.setBigDecimal(6, sp.getGiaBan());

            ps.setInt(7, sp.getIdMauSac());
            ps.setInt(8, sp.getIdKichThuoc());
            ps.setInt(9, sp.getIdChatLieu());
            ps.setInt(10, sp.getIdXuatXu());
            ps.setInt(11, sp.getIdThuongHieu());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Cập nhật sản phẩm
    public boolean updateSanPham(SanPhamModel2 sp) {
        String sql = "UPDATE San_Pham SET "
                + "Ten_San_Pham = ?, "
                + "So_Luong_Ton = ?, "
                + "Mo_Ta = ?, "
                + "Gia_Nhap = ?, "
                + "Gia_Ban = ?, "
                + "ID_Mau_Sac = ?, "
                + "ID_Kich_Thuoc = ?, "
                + "ID_Chat_Lieu = ?, "
                + "ID_Xuat_Xu = ?, "
                + "ID_Thuong_Hieu = ? "
                + "WHERE ID_San_Pham = ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            // Gán giá trị vào PreparedStatement theo thứ tự các tham số
            ps.setString(1, sp.getTenSanPham());
            ps.setInt(2, sp.getSoLuongTon());
            ps.setString(3, sp.getMoTa());
            ps.setBigDecimal(4, sp.getGiaNhap());
            ps.setBigDecimal(5, sp.getGiaBan());
            ps.setInt(6, sp.getIdMauSac());
            ps.setInt(7, sp.getIdKichThuoc());
            ps.setInt(8, sp.getIdChatLieu());
            ps.setInt(9, sp.getIdXuatXu());
            ps.setInt(10, sp.getIdThuongHieu());
            ps.setInt(11, sp.getId());

            // Thực thi câu lệnh UPDATE
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa sản phẩm
    public boolean deleteSanPham(int id) {
        String sql = "UPDATE San_Pham SET Trang_Thai = 0 WHERE ID_San_Pham = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm sản phẩm All trừ join bảng ,boolean
   public List<SanPhamModel> searchSanPhamBan(String keyword) {
    List<SanPhamModel> list = new ArrayList<>();
    
    String sql = "SELECT sp.ID_San_Pham, sp.Ma_San_Pham, sp.Ten_San_Pham, sp.So_Luong_Ton, "
                + "sp.Mo_Ta, sp.Ngay_Tao, sp.Gia_Nhap, sp.Gia_Ban, sp.Trang_Thai, "
                + "ms.Ten_Mau_Sac, kt.Ten_Kich_Thuoc, cl.Ten_Chat_Lieu, xx.Ten_Xuat_Xu, th.Ten_Thuong_Hieu "
                + "FROM San_Pham sp "
                + "LEFT JOIN Mau_Sac ms ON sp.ID_Mau_Sac = ms.ID_Mau_Sac "
                + "LEFT JOIN Kich_Thuoc kt ON sp.ID_Kich_Thuoc = kt.ID_Kich_Thuoc "
                + "LEFT JOIN Chat_Lieu cl ON sp.ID_Chat_Lieu = cl.ID_Chat_Lieu "
                + "LEFT JOIN Xuat_Xu xx ON sp.ID_Xuat_Xu = xx.ID_Xuat_Xu "
                + "LEFT JOIN Thuong_Hieu th ON sp.ID_Thuong_Hieu = th.ID_Thuong_Hieu "
                + "WHERE sp.Trang_Thai = 1 AND (LOWER(sp.Ma_San_Pham) LIKE LOWER(?) OR LOWER(sp.Ten_San_Pham) LIKE LOWER(?) "
                + "OR LOWER(sp.Mo_Ta) LIKE LOWER(?) OR sp.So_Luong_Ton LIKE ? "
                + "OR sp.Ngay_Tao LIKE ? OR sp.Gia_Nhap LIKE ? OR sp.Gia_Ban LIKE ?)";  

    try (Connection con = Dbconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
 
        ps.setString(1, "%" + keyword + "%"); 
        ps.setString(2, "%" + keyword + "%");  
        ps.setString(3, "%" + keyword + "%"); 
        ps.setString(4, "%" + keyword + "%");  
        ps.setString(5, "%" + keyword + "%");  
        ps.setString(6, "%" + keyword + "%");  
        ps.setString(7, "%" + keyword + "%");  

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new SanPhamModel(
                        rs.getInt("ID_San_Pham"),
                        rs.getString("Ma_San_Pham"),
                        rs.getString("Ten_San_Pham"),
                        rs.getInt("So_Luong_Ton"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getBigDecimal("Gia_Nhap"),
                        rs.getBigDecimal("Gia_Ban"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Ten_Mau_Sac"),
                        rs.getString("Ten_Kich_Thuoc"),
                        rs.getString("Ten_Chat_Lieu"),
                        rs.getString("Ten_Xuat_Xu"),
                        rs.getString("Ten_Thuong_Hieu")
                ));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}


    // Tìm kiếm sản phẩm
    public List<SanPhamModel> searchSanPhamNgung(String keyword) {
        List<SanPhamModel> list = new ArrayList<>();
    
    String sql = "SELECT sp.ID_San_Pham, sp.Ma_San_Pham, sp.Ten_San_Pham, sp.So_Luong_Ton, "
                + "sp.Mo_Ta, sp.Ngay_Tao, sp.Gia_Nhap, sp.Gia_Ban, sp.Trang_Thai, "
                + "ms.Ten_Mau_Sac, kt.Ten_Kich_Thuoc, cl.Ten_Chat_Lieu, xx.Ten_Xuat_Xu, th.Ten_Thuong_Hieu "
                + "FROM San_Pham sp "
                + "LEFT JOIN Mau_Sac ms ON sp.ID_Mau_Sac = ms.ID_Mau_Sac "
                + "LEFT JOIN Kich_Thuoc kt ON sp.ID_Kich_Thuoc = kt.ID_Kich_Thuoc "
                + "LEFT JOIN Chat_Lieu cl ON sp.ID_Chat_Lieu = cl.ID_Chat_Lieu "
                + "LEFT JOIN Xuat_Xu xx ON sp.ID_Xuat_Xu = xx.ID_Xuat_Xu "
                + "LEFT JOIN Thuong_Hieu th ON sp.ID_Thuong_Hieu = th.ID_Thuong_Hieu "
                + "WHERE sp.Trang_Thai = 0 AND (LOWER(sp.Ma_San_Pham) LIKE LOWER(?) OR LOWER(sp.Ten_San_Pham) LIKE LOWER(?) "
                + "OR LOWER(sp.Mo_Ta) LIKE LOWER(?) OR sp.So_Luong_Ton LIKE ? "
                + "OR sp.Ngay_Tao LIKE ? OR sp.Gia_Nhap LIKE ? OR sp.Gia_Ban LIKE ?)";  

    try (Connection con = Dbconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
 
        ps.setString(1, "%" + keyword + "%"); 
        ps.setString(2, "%" + keyword + "%");  
        ps.setString(3, "%" + keyword + "%"); 
        ps.setString(4, "%" + keyword + "%");  
        ps.setString(5, "%" + keyword + "%");  
        ps.setString(6, "%" + keyword + "%");  
        ps.setString(7, "%" + keyword + "%");  

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new SanPhamModel(
                        rs.getInt("ID_San_Pham"),
                        rs.getString("Ma_San_Pham"),
                        rs.getString("Ten_San_Pham"),
                        rs.getInt("So_Luong_Ton"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getBigDecimal("Gia_Nhap"),
                        rs.getBigDecimal("Gia_Ban"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Ten_Mau_Sac"),
                        rs.getString("Ten_Kich_Thuoc"),
                        rs.getString("Ten_Chat_Lieu"),
                        rs.getString("Ten_Xuat_Xu"),
                        rs.getString("Ten_Thuong_Hieu")
                ));
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

// Lấy danh sách tất cả sản phẩm
    public List<SanPhamModel> getAllSanPham() {
        List<SanPhamModel> list = new ArrayList<>();
        String sql = "SELECT sp.ID_San_Pham, sp.Ma_San_Pham, sp.Ten_San_Pham, sp.So_Luong_Ton, "
                + "sp.Mo_Ta, sp.Ngay_Tao, sp.Gia_Nhap, sp.Gia_Ban, sp.Trang_Thai, "
                + "ms.Ten_Mau_Sac, kt.Ten_Kich_Thuoc, cl.Ten_Chat_Lieu, xx.Ten_Xuat_Xu, th.Ten_Thuong_Hieu "
                + "FROM San_Pham sp "
                + "LEFT JOIN Mau_Sac ms ON sp.ID_Mau_Sac = ms.ID_Mau_Sac "
                + "LEFT JOIN Kich_Thuoc kt ON sp.ID_Kich_Thuoc = kt.ID_Kich_Thuoc "
                + "LEFT JOIN Chat_Lieu cl ON sp.ID_Chat_Lieu = cl.ID_Chat_Lieu "
                + "LEFT JOIN Xuat_Xu xx ON sp.ID_Xuat_Xu = xx.ID_Xuat_Xu "
                + "LEFT JOIN Thuong_Hieu th ON sp.ID_Thuong_Hieu = th.ID_Thuong_Hieu "
                + "WHERE sp.Trang_Thai = 1";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SanPhamModel(
                        rs.getInt("ID_San_Pham"),
                        rs.getString("Ma_San_Pham"),
                        rs.getString("Ten_San_Pham"),
                        rs.getInt("So_Luong_Ton"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getBigDecimal("Gia_Nhap"),
                        rs.getBigDecimal("Gia_Ban"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Ten_Mau_Sac"),
                        rs.getString("Ten_Kich_Thuoc"),
                        rs.getString("Ten_Chat_Lieu"),
                        rs.getString("Ten_Xuat_Xu"),
                        rs.getString("Ten_Thuong_Hieu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh sách tất cả sản phẩm
    public List<SanPhamModel> getAllSanPhamNgung() {
        List<SanPhamModel> list = new ArrayList<>();
        String sql = "SELECT sp.ID_San_Pham, sp.Ma_San_Pham, sp.Ten_San_Pham, sp.So_Luong_Ton, "
                + "sp.Mo_Ta, sp.Ngay_Tao, sp.Gia_Nhap, sp.Gia_Ban, sp.Trang_Thai, "
                + "ms.Ten_Mau_Sac, kt.Ten_Kich_Thuoc, cl.Ten_Chat_Lieu, xx.Ten_Xuat_Xu, th.Ten_Thuong_Hieu "
                + "FROM San_Pham sp "
                + "LEFT JOIN Mau_Sac ms ON sp.ID_Mau_Sac = ms.ID_Mau_Sac "
                + "LEFT JOIN Kich_Thuoc kt ON sp.ID_Kich_Thuoc = kt.ID_Kich_Thuoc "
                + "LEFT JOIN Chat_Lieu cl ON sp.ID_Chat_Lieu = cl.ID_Chat_Lieu "
                + "LEFT JOIN Xuat_Xu xx ON sp.ID_Xuat_Xu = xx.ID_Xuat_Xu "
                + "LEFT JOIN Thuong_Hieu th ON sp.ID_Thuong_Hieu = th.ID_Thuong_Hieu "
                + "WHERE sp.Trang_Thai = 0";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new SanPhamModel(
                        rs.getInt("ID_San_Pham"),
                        rs.getString("Ma_San_Pham"),
                        rs.getString("Ten_San_Pham"),
                        rs.getInt("So_Luong_Ton"),
                        rs.getString("Mo_Ta"),
                        rs.getDate("Ngay_Tao"),
                        rs.getBigDecimal("Gia_Nhap"),
                        rs.getBigDecimal("Gia_Ban"),
                        rs.getBoolean("Trang_Thai"),
                        rs.getString("Ten_Mau_Sac"),
                        rs.getString("Ten_Kich_Thuoc"),
                        rs.getString("Ten_Chat_Lieu"),
                        rs.getString("Ten_Xuat_Xu"),
                        rs.getString("Ten_Thuong_Hieu")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean khoiPhuc(int id) {
        String sql = "UPDATE San_Pham SET Trang_Thai = 1 WHERE ID_San_Pham = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSoLuongGioHang(int id, int soNew) {
        String sql = "UPDATE San_Pham SET So_Luong_Ton = ?,Trang_Thai = 1 WHERE ID_San_Pham = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            // Thiết lập tham số cho số lượng mới
            ps.setInt(1, soNew); // Đây là số lượng mới sau khi trừ số lượng người dùng nhập
            // Thiết lập tham số cho ID sản phẩm
            ps.setInt(2, id); // ID của sản phẩm cần cập nhật

            // Thực thi câu lệnh cập nhật
            return ps.executeUpdate() > 0; // Nếu có dòng nào bị ảnh hưởng, trả về true
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Nếu có lỗi, trả về false
    }

    public int getSoLuongTheoMaSP(String maSP) {
        String sql = "SELECT So_Luong_Ton FROM San_Pham WHERE Ma_San_Pham = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("So_Luong_Ton");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

// Cập nhật số lượng theo mã sản phẩm
    public void updateSoLuongGioHangTheoMa(String maSP, int soLuongMoi) {
        String sql = "UPDATE San_Pham SET So_Luong_Ton = ? WHERE Ma_San_Pham = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongMoi);
            ps.setString(2, maSP);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
