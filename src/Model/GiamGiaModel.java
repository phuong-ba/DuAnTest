/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Admin
 */
public class GiamGiaModel {
    private int id;
    private String maGiamGia;
    private String tenChuongTrinh;
    private String moTa;
    private Date ngayTao;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Integer soLuong;
    private Boolean kieuGiam;
    private BigDecimal giaTriDHToiThieu;
    private BigDecimal mucGiaGiam;
    private BigDecimal mucGiaGiamToiDa;
    private Boolean trangThai;

    public GiamGiaModel() {
    }
    
    
    public GiamGiaModel(int id, String maGiamGia, String tenChuongTrinh, String moTa, Date ngayTao, Date ngayBatDau, Date ngayKetThuc, Integer soLuong, Boolean kieuGiam, BigDecimal giaTriDHToiThieu, BigDecimal mucGiaGiam, BigDecimal mucGiaGiamToiDa, Boolean trangThai) {
        this.id = id;
        this.maGiamGia = maGiamGia;
        this.tenChuongTrinh = tenChuongTrinh;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuong = soLuong;
        this.kieuGiam = kieuGiam;
        this.giaTriDHToiThieu = giaTriDHToiThieu;
        this.mucGiaGiam = mucGiaGiam;
        this.mucGiaGiamToiDa = mucGiaGiamToiDa;
        this.trangThai = trangThai;
    }



    public GiamGiaModel(String tenChuongTrinh, Date ngayTao, Date ngayBatDau, Date ngayKetThuc, Integer soLuong, Boolean kieuGiam, BigDecimal giaTriDHToiThieu, BigDecimal mucGiaGiam, BigDecimal mucGiaGiamToiDa, Boolean trangThai) {
        this.tenChuongTrinh = tenChuongTrinh;
        this.ngayTao = ngayTao;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soLuong = soLuong;
        this.kieuGiam = kieuGiam;
        this.giaTriDHToiThieu = giaTriDHToiThieu;
        this.mucGiaGiam = mucGiaGiam;
        this.mucGiaGiamToiDa = mucGiaGiamToiDa;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaGiamGia() {
        return maGiamGia;
    }

    public void setMaGiamGia(String maGiamGia) {
        this.maGiamGia = maGiamGia;
    }

    public String getTenChuongTrinh() {
        return tenChuongTrinh;
    }

    public void setTenChuongTrinh(String tenChuongTrinh) {
        this.tenChuongTrinh = tenChuongTrinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Boolean getKieuGiam() {
        return kieuGiam;
    }

    public void setKieuGiam(Boolean kieuGiam) {
        this.kieuGiam = kieuGiam;
    }

    public BigDecimal getGiaTriDHToiThieu() {
        return giaTriDHToiThieu;
    }

    public void setGiaTriDHToiThieu(BigDecimal giaTriDHToiThieu) {
        this.giaTriDHToiThieu = giaTriDHToiThieu;
    }

    public BigDecimal getMucGiaGiam() {
        return mucGiaGiam;
    }

    public void setMucGiaGiam(BigDecimal mucGiaGiam) {
        this.mucGiaGiam = mucGiaGiam;
    }

    public BigDecimal getMucGiaGiamToiDa() {
        return mucGiaGiamToiDa;
    }

    public void setMucGiaGiamToiDa(BigDecimal mucGiaGiamToiDa) {
        this.mucGiaGiamToiDa = mucGiaGiamToiDa;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }


   
    
   
}
