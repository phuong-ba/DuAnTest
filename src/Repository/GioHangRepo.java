/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.GioHang;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import duantest.DBConnect.Dbconnect;

/**
 *
 * @author Admin
 */
public class GioHangRepo {

    public ArrayList<GioHang> getAll() {
        ArrayList<GioHang> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    gh.ID_Gio_Hang,\n"
                + "    sp.Ma_San_Pham,\n"
                + "    sp.Ten_San_Pham,\n"
                + "    sp.Gia_Ban,\n"
                + "    gh.So_Luong\n"
                + "FROM Gio_Hang gh\n"
                + "JOIN San_Pham sp ON gh.ID_San_Pham = sp.ID_San_Pham;";
        try ( Connection conn = Dbconnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GioHang gh = new GioHang();
                gh.setIdGH(rs.getInt("ID_Gio_Hang"));               
                gh.setMaSP(rs.getString("Ma_San_Pham"));
                gh.setTenSP(rs.getString("Ten_San_Pham"));
                gh.setSoLuong(rs.getInt("So_Luong"));
                gh.setDonGia(rs.getBigDecimal("Gia_Ban"));

                list.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   public boolean addGio(GioHang gh) {
    String checkSql = "SELECT COUNT(*) FROM San_Pham WHERE ID_San_Pham = ?";
    try (Connection conn = Dbconnect.getConnection(); 
         PreparedStatement checkPs = conn.prepareStatement(checkSql)) {
        
        checkPs.setInt(1, gh.getIdSanPham()); // Kiểm tra ID_San_Pham
        
        ResultSet rs = checkPs.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            // Nếu ID_San_Pham tồn tại, thêm vào bảng Gio_Hang
            String sql = "INSERT INTO Gio_Hang (So_Luong, ID_San_Pham) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, gh.getSoLuong());
                ps.setInt(2, gh.getIdSanPham());
                return ps.executeUpdate() > 0;
            }
        } else {
            System.out.println("ID_San_Pham không tồn tại trong bảng San_Pham.");
            return false;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

  public boolean deleteGioHang(int idGh) {
        String sql = "DELETE FROM Gio_Hang WHERE ID_Gio_Hang = ?";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idGh);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
