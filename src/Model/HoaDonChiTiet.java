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
public class HoaDonChiTiet {

    private int idHDCT;
    private int soLuong;
    private BigDecimal giaSauGiam;
    private BigDecimal soTienSauGiam;
    private int idHoaDon;
    private int idSanPham;
    private String maSanPham;
    private String tenSanPham;
    private String maHoaDon;
    private BigDecimal thanhTien;
    private BigDecimal giaBan;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int idHDCT, int soLuong, BigDecimal giaSauGiam, BigDecimal soTienSauGiam, int idHoaDon, int idSanPham) {
        this.idHDCT = idHDCT;
        this.soLuong = soLuong;
        this.giaSauGiam = giaSauGiam;
        this.soTienSauGiam = soTienSauGiam;
        this.idHoaDon = idHoaDon;
        this.idSanPham = idSanPham;
    }

    public HoaDonChiTiet(int idHDCT, int soLuong, BigDecimal giaSauGiam, BigDecimal soTienSauGiam, String maSanPham, String tenSanPham, String maHoaDon, BigDecimal thanhTien, BigDecimal giaBan) {
        this.idHDCT = idHDCT;
        this.soLuong = soLuong;
        this.giaSauGiam = giaSauGiam;
        this.soTienSauGiam = soTienSauGiam;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maHoaDon = maHoaDon;
        this.thanhTien = thanhTien;
        this.giaBan = giaBan;
    }

    public int getIdHDCT() {
        return idHDCT;
    }

    public void setIdHDCT(int idHDCT) {
        this.idHDCT = idHDCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGiaSauGiam() {
        return giaSauGiam;
    }

    public void setGiaSauGiam(BigDecimal giaSauGiam) {
        this.giaSauGiam = giaSauGiam;
    }

    public BigDecimal getSoTienSauGiam() {
        return soTienSauGiam;
    }

    public void setSoTienSauGiam(BigDecimal soTienSauGiam) {
        this.soTienSauGiam = soTienSauGiam;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

   

  
}
