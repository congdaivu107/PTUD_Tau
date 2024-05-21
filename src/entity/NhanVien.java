// 4.	Trần Thị Anh Thư - 19516531 (Note taker)
package entity;

import java.util.Date;

public class NhanVien {
private String maNV,tenNV,diaChi,gioiTinh,email;
private Date ngayVaoLam;
private String sdt;

private ChucVu chucVu;



public NhanVien(String maNV, String tenNV, String diaChi, String gioiTinh, String email, Date ngayVaoLam, String sdt,
		ChucVu chucVu) {
	super();
	this.maNV = maNV;
	this.tenNV = tenNV;
	this.diaChi = diaChi;
	this.gioiTinh = gioiTinh;
	this.email = email;
	this.ngayVaoLam = ngayVaoLam;
	this.sdt = sdt;
	this.chucVu = chucVu;
}



public NhanVien(String tenNV, String diaChi, String gioiTinh, Date ngayVaoLam, ChucVu chucVu, String email,
		String sdt) {
	super();
	this.tenNV = tenNV;
	this.diaChi = diaChi;
	this.gioiTinh = gioiTinh;
	this.ngayVaoLam = ngayVaoLam;
	this.chucVu = chucVu;
	this.email = email;
	this.sdt = sdt;
}



public NhanVien(String maNV, String tenNV, String diaChi, String gioiTinh, Date ngayVaoLam, ChucVu chucVu, String email,
		String sdt) {
	super();
	this.maNV = maNV;
	this.tenNV = tenNV;
	this.diaChi = diaChi;
	this.gioiTinh = gioiTinh;
	this.ngayVaoLam = ngayVaoLam;
	this.chucVu = chucVu;
	this.email = email;
	this.sdt = sdt;
}
public NhanVien(String maNV) {
	super();
	this.maNV = maNV;
}


public NhanVien(String maNV, ChucVu chucVu) {
	super();
	this.maNV = maNV;
	this.chucVu = chucVu;
}

public NhanVien(String maNV, String tenNV) {
	super();
	this.maNV = maNV;
	this.tenNV = tenNV;
}
public NhanVien() {
	super();
}
public String getMaNV() {
	return maNV;
}
public void setMaNV(String maNV) {
	this.maNV = maNV;
}
public String getTenNV() {
	return tenNV;
}
public void setTenNV(String tenNV) {
	this.tenNV = tenNV;
}
public String getDiaChi() {
	return diaChi;
}
public void setDiaChi(String diaChi) {
	this.diaChi = diaChi;
}
public String getGioiTinh() {
	return gioiTinh;
}
public void setGioiTinh(String gioiTinh) {
	this.gioiTinh = gioiTinh;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Date getNgayVaoLam() {
	return ngayVaoLam;
}
public void setNgayVaoLam(Date ngayVaoLam) {
	this.ngayVaoLam = ngayVaoLam;
}
public String getSdt() {
	return sdt;
}
public void setSdt(String sdt) {
	this.sdt = sdt;
}
public ChucVu getChucVu() {
	return chucVu;
}
public void setChucVu(ChucVu chucVu) {
	this.chucVu = chucVu;
}




@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	NhanVien other = (NhanVien) obj;
	if (maNV == null) {
		if (other.maNV != null)
			return false;
	} else if (!maNV.equals(other.maNV))
		return false;
	return true;
}

}
