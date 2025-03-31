/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ThuongHieu {
    private int id;
    private String tenTH;
    private Boolean trangThai;

    public ThuongHieu() {
    }

    public ThuongHieu(int id, String tenTH, Boolean trangThai) {
        this.id = id;
        this.tenTH = tenTH;
        this.trangThai = trangThai;
    }

    public ThuongHieu(int id, String tenTH) {
        this.id = id;
        this.tenTH = tenTH;
    }

    public ThuongHieu(String tenTH) {
        this.tenTH = tenTH;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    
   
    
}
