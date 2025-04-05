/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class GioHang {

    int idGH;
    int idSanPham;
    String maSP;
    String tenSP;
    int SoLuong;
    BigDecimal donGia;

    public GioHang() {
    }

    public GioHang(int idSanPham, String maSP, String tenSP, int SoLuong, BigDecimal donGia) {
        this.idSanPham = idSanPham;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.SoLuong = SoLuong;
        this.donGia = donGia;
    }

    public GioHang(int idGH, int idSanPham, String maSP, String tenSP, int SoLuong, BigDecimal donGia) {
        this.idGH = idGH;
        this.idSanPham = idSanPham;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.SoLuong = SoLuong;
        this.donGia = donGia;
    }

    public GioHang(int idGH, int idSanPham, int SoLuong) {
        this.idGH = idGH;
        this.idSanPham = idSanPham;
        this.SoLuong = SoLuong;
    }

    public int getIdGH() {
        return idGH;
    }

    public void setIdGH(int idGH) {
        this.idGH = idGH;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

   

}
