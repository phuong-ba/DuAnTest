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
public class SanPhamModel2 {

    private int id;
    private String maSanPham;
    private String tenSanPham;
    private Integer soLuongTon;
    private String moTa;
    private Date ngayTao;
    private BigDecimal giaNhap;
    private BigDecimal giaBan;
    private Boolean trangThai;
    private int idMauSac;
    private int idKichThuoc;
    private int idChatLieu;
    private int idXuatXu;
    private int idThuongHieu;

    public SanPhamModel2() {
    }

    public SanPhamModel2(int id, String tenSanPham, Integer soLuongTon, String moTa, Date ngayTao, BigDecimal giaNhap, BigDecimal giaBan, Boolean trangThai, int idMauSac, int idKichThuoc, int idChatLieu, int idXuatXu, int idThuongHieu) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.idMauSac = idMauSac;
        this.idKichThuoc = idKichThuoc;
        this.idChatLieu = idChatLieu;
        this.idXuatXu = idXuatXu;
        this.idThuongHieu = idThuongHieu;
    }

    public SanPhamModel2(String tenSanPham, Integer soLuongTon, String moTa, Date ngayTao, BigDecimal giaNhap, BigDecimal giaBan, Boolean trangThai, int idMauSac, int idKichThuoc, int idChatLieu, int idXuatXu, int idThuongHieu) {
        this.tenSanPham = tenSanPham;
        this.soLuongTon = soLuongTon;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.idMauSac = idMauSac;
        this.idKichThuoc = idKichThuoc;
        this.idChatLieu = idChatLieu;
        this.idXuatXu = idXuatXu;
        this.idThuongHieu = idThuongHieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
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

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(int idMauSac) {
        this.idMauSac = idMauSac;
    }

    public int getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(int idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public int getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(int idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public int getIdXuatXu() {
        return idXuatXu;
    }

    public void setIdXuatXu(int idXuatXu) {
        this.idXuatXu = idXuatXu;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

   
   
   
}
