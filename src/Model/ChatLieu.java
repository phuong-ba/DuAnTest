/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author Admin
 */
public class ChatLieu {

    private int id; 
    private String tenCL;
    private Boolean trangThai;

    public ChatLieu() {
    }

    public ChatLieu(int id, String tenCL, Boolean trangThai) {
        this.id = id;
        this.tenCL = tenCL;
        this.trangThai = trangThai;
    }

    public ChatLieu(int id, String tenCL) {
        this.id = id;
        this.tenCL = tenCL;
    }

    public ChatLieu(String tenCL) {
        this.tenCL = tenCL;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }       
   
}
