/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class MauSac {
    private int id;
    private String tenMau;
    private Boolean trangThai;//true con false la het
    
     public MauSac() {
    }

    public MauSac(int id, String tenMau, Boolean trangThai) {
        this.id = id;
        this.tenMau = tenMau;
        this.trangThai = trangThai;
    }

    public MauSac(int id, String tenMau) {
        this.id = id;
        this.tenMau = tenMau;
    }

    public MauSac(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    
}
