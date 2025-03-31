package Repository;

import Model.LichSuHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.math.BigDecimal;
import duantest.DBConnect.Dbconnect;

public class LichSuHDRepo {
    
    public boolean luuLichSuHoaDon(LichSuHD lichSu) {
        if (lichSu == null) {
            throw new IllegalArgumentException("LichSuHD không được null");
        }
        
        String sql = "INSERT INTO Lich_Su_Hoa_Don (ID_Hoa_Don, Hanh_Dong, Ngay_Thay_Doi, Tong_Tien_Cu, Tong_Tien_Moi, Ghi_Chu, ID_Nhan_Vien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Dbconnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            if (conn == null) {
                throw new SQLException("Không thể kết nối đến cơ sở dữ liệu");
            }
            
            stmt.setInt(1, lichSu.getIdHoaDon());
            stmt.setString(2, lichSu.getHanhDong());
            stmt.setTimestamp(3, new java.sql.Timestamp(lichSu.getNgayThayDoi().getTime()));

            // Chuyển đổi từ double sang BigDecimal
            stmt.setBigDecimal(4, BigDecimal.valueOf(lichSu.getTongTienCu()));
            stmt.setBigDecimal(5, BigDecimal.valueOf(lichSu.getTongTienMoi()));
            
            stmt.setString(6, lichSu.getGhiChu() != null ? lichSu.getGhiChu() : "");
            
            if (lichSu.getIdNhanVien() != null) {
                stmt.setInt(7, lichSu.getIdNhanVien());
            } else {
                stmt.setNull(7, java.sql.Types.INTEGER);
            }
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
