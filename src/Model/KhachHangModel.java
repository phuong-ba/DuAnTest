/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class KhachHangModel {
    private int id;
    private String maKH;
    private String tenKH;
    private Boolean gioiTinhKH;
    private String sdtKH;
    private String diaChiKH;

    public KhachHangModel() {
    }

    public KhachHangModel(int id, String maKH, String tenKH, Boolean gioiTinhKH, String sdtKH, String diaChiKH) {
        this.id = id;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.gioiTinhKH = gioiTinhKH;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
    }

    public KhachHangModel(String tenKH, Boolean gioiTinhKH, String sdtKH, String diaChiKH) {
        this.tenKH = tenKH;
        this.gioiTinhKH = gioiTinhKH;
        this.sdtKH = sdtKH;
        this.diaChiKH = diaChiKH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public Boolean getGioiTinhKH() {
        return gioiTinhKH;
    }

    public void setGioiTinhKH(Boolean gioiTinhKH) {
        this.gioiTinhKH = gioiTinhKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getDiaChiKH() {
        return diaChiKH;
    }

    public void setDiaChiKH(String diaChiKH) {
        this.diaChiKH = diaChiKH;
    }

    
}
