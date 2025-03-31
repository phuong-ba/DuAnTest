/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class NhanVienModel {
    private int idNV;
    private String maNV;
    private String tenNV;
    private Boolean gioiTinhNV;
    private Date ngaySinhNV;
    private String sdtNV;
    private String diaChiNV;
    private String chucVuNV;
    private Boolean trangThaiNV;
    private String taiKhoanNV;
    private String matKhauNV;
    private Date ngayBatDauNV;
    private Date ngayKetThucNV;

    public NhanVienModel() {
    }

    public NhanVienModel(int idNV, String maNV, String tenNV, Boolean gioiTinhNV, Date ngaySinhNV, String sdtNV, String diaChiNV, String chucVuNV, Boolean trangThaiNV, String taiKhoanNV, String matKhauNV, Date ngayBatDauNV, Date ngayKetThucNV) {
        this.idNV = idNV;
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinhNV = gioiTinhNV;
        this.ngaySinhNV = ngaySinhNV;
        this.sdtNV = sdtNV;
        this.diaChiNV = diaChiNV;
        this.chucVuNV = chucVuNV;
        this.trangThaiNV = trangThaiNV;
        this.taiKhoanNV = taiKhoanNV;
        this.matKhauNV = matKhauNV;
        this.ngayBatDauNV = ngayBatDauNV;
        this.ngayKetThucNV = ngayKetThucNV;
    }

    public NhanVienModel(String tenNV, Boolean gioiTinhNV, Date ngaySinhNV, String sdtNV, String diaChiNV, String chucVuNV, Boolean trangThaiNV, String taiKhoanNV, String matKhauNV, Date ngayBatDauNV, Date ngayKetThucNV) {
        this.tenNV = tenNV;
        this.gioiTinhNV = gioiTinhNV;
        this.ngaySinhNV = ngaySinhNV;
        this.sdtNV = sdtNV;
        this.diaChiNV = diaChiNV;
        this.chucVuNV = chucVuNV;
        this.trangThaiNV = trangThaiNV;
        this.taiKhoanNV = taiKhoanNV;
        this.matKhauNV = matKhauNV;
        this.ngayBatDauNV = ngayBatDauNV;
        this.ngayKetThucNV = ngayKetThucNV;
    }

    public int getIdNV() {
        return idNV;
    }

    public void setIdNV(int idNV) {
        this.idNV = idNV;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public Boolean getGioiTinhNV() {
        return gioiTinhNV;
    }

    public void setGioiTinhNV(Boolean gioiTinhNV) {
        this.gioiTinhNV = gioiTinhNV;
    }

    public Date getNgaySinhNV() {
        return ngaySinhNV;
    }

    public void setNgaySinhNV(Date ngaySinhNV) {
        this.ngaySinhNV = ngaySinhNV;
    }

    public String getSdtNV() {
        return sdtNV;
    }

    public void setSdtNV(String sdtNV) {
        this.sdtNV = sdtNV;
    }

    public String getDiaChiNV() {
        return diaChiNV;
    }

    public void setDiaChiNV(String diaChiNV) {
        this.diaChiNV = diaChiNV;
    }

    public String getChucVuNV() {
        return chucVuNV;
    }

    public void setChucVuNV(String chucVuNV) {
        this.chucVuNV = chucVuNV;
    }

    public Boolean getTrangThaiNV() {
        return trangThaiNV;
    }

    public void setTrangThaiNV(Boolean trangThaiNV) {
        this.trangThaiNV = trangThaiNV;
    }

    public String getTaiKhoanNV() {
        return taiKhoanNV;
    }

    public void setTaiKhoanNV(String taiKhoanNV) {
        this.taiKhoanNV = taiKhoanNV;
    }

    public String getMatKhauNV() {
        return matKhauNV;
    }

    public void setMatKhauNV(String matKhauNV) {
        this.matKhauNV = matKhauNV;
    }

    public Date getNgayBatDauNV() {
        return ngayBatDauNV;
    }

    public void setNgayBatDauNV(Date ngayBatDauNV) {
        this.ngayBatDauNV = ngayBatDauNV;
    }

    public Date getNgayKetThucNV() {
        return ngayKetThucNV;
    }

    public void setNgayKetThucNV(Date ngayKetThucNV) {
        this.ngayKetThucNV = ngayKetThucNV;
    }

    

  

   

}
