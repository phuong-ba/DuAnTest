package Repository;

import Model.NhanVienModel;
import Model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import duantest.DBConnect.Dbconnect;

public class TaiKhoanRepo {

    // Lấy danh sách tài khoản
    public ArrayList<TaiKhoan> getAllTaiKhoan() {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getInt("ID_Tai_Khoan"), rs.getString("User_Name"), rs.getString("Pass_Word"), rs.getString("Role"), rs.getInt("ID_Nhan_Vien"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Thêm tài khoản mới
    public boolean addTaiKhoan(TaiKhoan tk) {
        String sql = "INSERT INTO Tai_Khoan (User_Name, Pass_Word, Role, ID_Nhan_Vien) VALUES (?, ?, ?, ?)";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tk.getUserName());
            ps.setString(2, tk.getPassWord());
            ps.setString(3, tk.getRole());
            ps.setInt(4, tk.getIdNhanVien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sửa tài khoản
    public boolean updateTaiKhoan(TaiKhoan tk) {
        String sql = "UPDATE Tai_Khoan SET Pass_Word = ?, Role = ? WHERE ID_Tai_Khoan = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tk.getPassWord());
            ps.setString(2, tk.getRole());
            ps.setInt(3, tk.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa tài khoản
    public boolean deleteTaiKhoan(int idTaiKhoan) {
        String sql = "DELETE FROM Tai_Khoan WHERE ID_Tai_Khoan = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTaiKhoan);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm tài khoản theo UserName
    public TaiKhoan searchTaiKhoan(String userName) {
        String sql = "SELECT * FROM Tai_Khoan WHERE User_Name = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(rs.getInt("ID_Tai_Khoan"), rs.getString("User_Name"), rs.getString("Pass_Word"), rs.getString("Role"), rs.getInt("ID_Nhan_Vien"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Tìm kiếm tài khoản theo Role
    public ArrayList<TaiKhoan> searchTaiKhoanByRole(String role) {
        ArrayList<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT * FROM Tai_Khoan WHERE Role = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan(rs.getInt("ID_Tai_Khoan"), rs.getString("User_Name"), rs.getString("Pass_Word"), rs.getString("Role"), rs.getInt("ID_Nhan_Vien"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean checkLogin(String username, String password) {
        String sql = "SELECT * FROM Tai_Khoan WHERE User_Name = ? AND Pass_Word = ?";

        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // Nếu có dữ liệu trả về, tài khoản hợp lệ
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(String username) {
        String role = "";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement stmt = conn.prepareStatement("SELECT role FROM Tai_Khoan WHERE User_Name = ?")) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                role = rs.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    public int getTrangThaiNhanVien(String username) {
        try {
            String sql = "SELECT nv.Trang_Thai FROM Tai_Khoan tk JOIN Nhan_Vien nv ON tk.ID_Nhan_Vien = nv.ID_Nhan_Vien WHERE tk.User_Name = ?";
            Connection conn = Dbconnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("Trang_Thai");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0; 
    }

}
