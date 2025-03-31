/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class XuatXu {
    private int id;
    private String tenXX;
    private Boolean trangThai;

    public XuatXu() {
    }

    public XuatXu(int id, String tenXX, Boolean trangThai) {
        this.id = id;
        this.tenXX = tenXX;
        this.trangThai = trangThai;
    }

    public XuatXu(int id, String tenXX) {
        this.id = id;
        this.tenXX = tenXX;
    }

    public XuatXu(String tenXX) {
        this.tenXX = tenXX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenXX() {
        return tenXX;
    }

    public void setTenXX(String tenXX) {
        this.tenXX = tenXX;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}
