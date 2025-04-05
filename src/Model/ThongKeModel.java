/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ThongKeModel {
    private int IdHoaDon;
    private String maHoaDon;
    private Date ngayThanhToan;
    private BigDecimal tongGia;
    private int trangThai;

    public ThongKeModel() {
    }

    public ThongKeModel(int IdHoaDon, String maHoaDon, Date ngayThanhToan, BigDecimal tongGia, int trangThai) {
        this.IdHoaDon = IdHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.tongGia = tongGia;
        this.trangThai = trangThai;
    }

    public int getIdHoaDon() {
        return IdHoaDon;
    }

    public void setIdHoaDon(int IdHoaDon) {
        this.IdHoaDon = IdHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public BigDecimal getTongGia() {
        return tongGia;
    }

    public void setTongGia(BigDecimal tongGia) {
        this.tongGia = tongGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

   
}
