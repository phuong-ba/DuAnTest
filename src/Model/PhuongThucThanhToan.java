/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class PhuongThucThanhToan {
    private int id;
    private String loaiPhuongThuc;

    public PhuongThucThanhToan() {
    }

    public PhuongThucThanhToan(int id, String loaiPhuongThuc) {
        this.id = id;
        this.loaiPhuongThuc = loaiPhuongThuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoaiPhuongThuc() {
        return loaiPhuongThuc;
    }

    public void setLoaiPhuongThuc(String loaiPhuongThuc) {
        this.loaiPhuongThuc = loaiPhuongThuc;
    }
    
}
