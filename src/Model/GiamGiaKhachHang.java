/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class GiamGiaKhachHang {
    private int id;
    private int idKhachHang;
    private int  idGiamGia;

    public GiamGiaKhachHang() {
    }

    public GiamGiaKhachHang(int id, int idKhachHang, int idGiamGia) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.idGiamGia = idGiamGia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdGiamGia() {
        return idGiamGia;
    }

    public void setIdGiamGia(int idGiamGia) {
        this.idGiamGia = idGiamGia;
    }

   
   
}
