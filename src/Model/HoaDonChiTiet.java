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

  
}
