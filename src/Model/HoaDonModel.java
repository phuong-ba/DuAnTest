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
public class HoaDonModel {

    private int idHoaDon;
    private String maHoaDon;
    private Date ngayTao;
    private Date ngayThanhToan;
    private BigDecimal tongGia;
    private BigDecimal thanhTien;
    private String sdt;
    private String diaChi;
    private Boolean trangThai;
    private int idKhachHang;
    private int idNhanVien;
    private int idThanhToan;
    private int idPhuongThucThanhToan;
    private String maNhanVien;
    private String maKhachHang;
    private String loaiThanhToan;

    public HoaDonModel() {
    }

    public HoaDonModel(String maHoaDon, Date ngayThanhToan, BigDecimal tongGia, BigDecimal thanhTien, String sdt, String diaChi, Boolean trangThai, int idKhachHang, int idNhanVien, int idThanhToan) {
        this.maHoaDon = maHoaDon;
        this.ngayThanhToan = ngayThanhToan;
        this.tongGia = tongGia;
        this.thanhTien = thanhTien;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idThanhToan = idThanhToan;
    }

    public HoaDonModel(Date ngayThanhToan, BigDecimal tongGia, BigDecimal thanhTien, String sdt, String diaChi, Boolean trangThai, int idKhachHang, int idNhanVien, int idThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
        this.tongGia = tongGia;
        this.thanhTien = thanhTien;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idThanhToan = idThanhToan;
    }
    

    public HoaDonModel(Date ngayThanhToan, BigDecimal thanhTien, String sdt, String diaChi, Boolean trangThai, int idKhachHang, int idNhanVien, int idThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
        this.thanhTien = thanhTien;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idThanhToan = idThanhToan;
    }

    

    public int getIdThanhToan() {
        return idThanhToan;
    }

    public void setIdThanhToan(int idThanhToan) {
        this.idThanhToan = idThanhToan;
    }

    public HoaDonModel(Date ngayTao, BigDecimal thanhTien, String sdt, String diaChi, Boolean trangThai, String maNhanVien, String maKhachHang, String loaiThanhToan) {
        this.ngayTao = ngayTao;
        this.thanhTien = thanhTien;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.loaiThanhToan = loaiThanhToan;
    }

    public HoaDonModel(int idHoaDon, String maHoaDon, Date ngayTao, Date ngayThanhToan, BigDecimal tongGia, String sdt, String diaChi, Boolean trangThai, int idKhachHang, int idNhanVien, int idPhuongThucThanhToan) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tongGia = tongGia;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idPhuongThucThanhToan = idPhuongThucThanhToan;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public HoaDonModel(int idHoaDon, String maHoaDon, Date ngayTao, Date ngayThanhToan, BigDecimal tongGia, String sdt, String diaChi, Boolean trangThai, String maNhanVien, String maKhachHang, String loaiThanhToan) {
        this.idHoaDon = idHoaDon;
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.tongGia = tongGia;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.maNhanVien = maNhanVien;
        this.maKhachHang = maKhachHang;
        this.loaiThanhToan = loaiThanhToan;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdPhuongThucThanhToan() {
        return idPhuongThucThanhToan;
    }

    public void setIdPhuongThucThanhToan(int idPhuongThucThanhToan) {
        this.idPhuongThucThanhToan = idPhuongThucThanhToan;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
    }

}
