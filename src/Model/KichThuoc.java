/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class KichThuoc {
    private int id;
    private String tenKT;
    private Boolean trangThai;

    public KichThuoc() {
    }

    public KichThuoc(int id, String tenKT, Boolean trangThai) {
        this.id = id;
        this.tenKT = tenKT;
        this.trangThai = trangThai;
    }

    public KichThuoc(int id, String tenKT) {
        this.id = id;
        this.tenKT = tenKT;
    }

    public KichThuoc(String tenKT) {
        this.tenKT = tenKT;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    
}
