//4.	Trần Thị Anh Thư - 19516531 (Note taker)
package entity;

/**
 *
 * @author DELL
 */
public class KhachHang {
    private String tenKH;
    private String maKH;
    private String sdt;
    private String diaChi;

    public KhachHang(String tenKH, String maKH, String sdt, String diaChi) {
        this.tenKH = tenKH;
        this.maKH = maKH;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public KhachHang() {
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "tenKH=" + tenKH + ", maKH=" + maKH + ", sdt=" + 
                sdt + ", diaChi=" + diaChi + '}';
    }
            
    
}
