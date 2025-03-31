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
public class LichSuHD {
    private int id;
    private int idHoaDon;
    private String hanhDong;
    private Date ngayThayDoi;
    private double tongTienCu;
    private double tongTienMoi;
    private String ghiChu;
    private Integer idNhanVien;

    public LichSuHD() {
    }

    public LichSuHD(int id, int idHoaDon, String hanhDong, Date ngayThayDoi, double tongTienCu, double tongTienMoi, String ghiChu, Integer idNhanVien) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.hanhDong = hanhDong;
        this.ngayThayDoi = ngayThayDoi;
        this.tongTienCu = tongTienCu;
        this.tongTienMoi = tongTienMoi;
        this.ghiChu = ghiChu;
        this.idNhanVien = idNhanVien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getHanhDong() {
        return hanhDong;
    }

    public void setHanhDong(String hanhDong) {
        this.hanhDong = hanhDong;
    }

    public Date getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(Date ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public double getTongTienCu() {
        return tongTienCu;
    }

    public void setTongTienCu(double tongTienCu) {
        this.tongTienCu = tongTienCu;
    }

    public double getTongTienMoi() {
        return tongTienMoi;
    }

    public void setTongTienMoi(double tongTienMoi) {
        this.tongTienMoi = tongTienMoi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Integer idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

}
