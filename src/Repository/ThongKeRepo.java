/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.ThongKeModel;
import duantest.DBConnect.Dbconnect;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongKeRepo {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<ThongKeModel> getAllTK() {
        ArrayList<ThongKeModel> list = new ArrayList<>();
        String sql = "SELECT * FROM Hoa_Don where Trang_Thai=1 ";
        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ThongKeModel tk = new ThongKeModel(
                        rs.getInt("ID_Hoa_Don"),
                        rs.getString("Ma_Hoa_Don"),
                        rs.getDate("Ngay_Thanh_Toan"),
                        rs.getBigDecimal("Thanh_Tien"),
                        rs.getInt("Trang_Thai")
                );
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ThongKeModel> TimTheoKhoangNgay(java.sql.Date ngayBD, java.sql.Date ngayKT) {
        List<ThongKeModel> list = new ArrayList<>();
      String sql = "SELECT ID_Hoa_Don, Ma_Hoa_Don, Ngay_Thanh_Toan, Thanh_Tien, Trang_Thai, "
           + "SUM(Thanh_Tien) OVER () AS TongTien "
           + "FROM Hoa_Don "
           + "WHERE Trang_Thai = 1 AND Ngay_Thanh_Toan BETWEEN ? AND ?";

        try ( Connection con = Dbconnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, ngayBD);
            ps.setDate(2, ngayKT);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ThongKeModel tk = new ThongKeModel(
                        rs.getInt("ID_Hoa_Don"),
                        rs.getString("Ma_Hoa_Don"),
                        rs.getDate("Ngay_Thanh_Toan"),
                        rs.getBigDecimal("Thanh_Tien"),
                        rs.getInt("Trang_Thai")
                );
                list.add(tk);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

  public BigDecimal TimYear(int year) {
    BigDecimal tongTien = BigDecimal.ZERO;
    String sql = "SELECT SUM(Thanh_Tien) AS TongTien FROM Hoa_Don WHERE YEAR(Ngay_Thanh_Toan) = ? and Trang_Thai=1";

    try (Connection con = Dbconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, year);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            tongTien = rs.getBigDecimal("TongTien");  // Lấy tổng tiền
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return tongTien;  // Trả về tổng tiền
}
public List<ThongKeModel> getHoaDonByYear(int year) {
    List<ThongKeModel> list = new ArrayList<>();
    String sql = "SELECT * FROM Hoa_Don WHERE YEAR(Ngay_Thanh_Toan) = ? and Trang_Thai=1";

    try (Connection con = Dbconnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, year);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            ThongKeModel tk = new ThongKeModel(
                    rs.getInt("ID_Hoa_Don"),
                    rs.getString("Ma_Hoa_Don"),
                    rs.getDate("Ngay_Thanh_Toan"),
                    rs.getBigDecimal("Thanh_Tien"),
                    rs.getInt("Trang_Thai")
            );
            list.add(tk);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;  // Trả về danh sách hóa đơn
}


}
