package Repository;

import Model.ChatLieu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class ChatLieuRepo {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // Lấy tất cả chất liệu có trạng thái hiển thị (Trang_Thai = 1)
    public ArrayList<ChatLieu> getAll() {
        ArrayList<ChatLieu> list = new ArrayList<>();
        String query = "SELECT * FROM Chat_Lieu WHERE Trang_Thai = 1";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("ID_Chat_Lieu"));
                cl.setTenCL(rs.getString("Ten_Chat_Lieu"));
                cl.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }
    // tim theo ten
    public ArrayList<ChatLieu> getTimCl(String keyword) {
        ArrayList<ChatLieu> list = new ArrayList<>();
      String query = "SELECT * FROM Chat_Lieu WHERE Trang_Thai = 1 AND LOWER(Ten_Chat_Lieu) LIKE LOWER(?)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + keyword.toLowerCase() + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("ID_Chat_Lieu"));

                cl.setTenCL(rs.getString("Ten_Chat_Lieu"));
                cl.setTrangThai(rs.getBoolean("Trang_Thai"));
                list.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    // Thêm mới chất liệu
    public boolean add(ChatLieu cl) {
        String query = "INSERT INTO Chat_Lieu ( Ten_Chat_Lieu, Trang_Thai) VALUES (?, 1)";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, cl.getTenCL());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    // Cập nhật chất liệu
    public boolean update(ChatLieu cl) {
        String query = "UPDATE Chat_Lieu SET  Ten_Chat_Lieu = ? WHERE ID_Chat_Lieu = ?";
        try {
            conn = Dbconnect.getConnection();
            ps = conn.prepareStatement(query);
            
            ps.setString(1, cl.getTenCL());
            ps.setInt(2, cl.getId());
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
        String query = "UPDATE Chat_Lieu SET Trang_Thai = 0 WHERE ID_Chat_Lieu = ?";
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

    // Khôi phục chất liệu đã bị ẩn (Trang_Thai = 0 -> 1)
    public boolean restore(int id) {
        String query = "UPDATE Chat_Lieu SET Trang_Thai = 1 WHERE ID_Chat_Lieu = ?";
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
    public boolean checkTrung(String tenCL){
        for (ChatLieu cl : getAll()) {
            if(cl.getTenCL().equalsIgnoreCase(tenCL)){
                return true;
            }
        }
        return false;
    }

  
     public int getIdChatLieu(String tenChatLieu) {
        String sql = "SELECT ID_Chat_Lieu FROM Chat_Lieu WHERE Ten_Chat_Lieu = ?";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenChatLieu);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("ID_Chat_Lieu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    // Đóng kết nối
    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
