//	Nguyễn Nhật Quang - 19437361 (Reporter)
package entity;

public class User {
private String id, password;
private ChucVu chucNang;
private NhanVien nhanVien;


public User(String id, String password, ChucVu chucNang, NhanVien nhanVien) {
	super();
	this.id = id;
	this.password = password;
	this.chucNang = chucNang;
	this.nhanVien = nhanVien;
}

public User(String id) {
	super();
	this.id = id;
}

public User(String id, String password) {
	super();
	this.id = id;
	this.password = password;
}

public User() {
	super();
}

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public ChucVu getChucNang() {
	return chucNang;
}

public void setChucNang(ChucVu chucNang) {
	this.chucNang = chucNang;
}

public NhanVien getNhanVien() {
	return nhanVien;
}

public void setNhanVien(NhanVien nhanVien) {
	this.nhanVien = nhanVien;
}

@Override
public String toString() {
	return "User [id=" + id + ", password=" + password + ", chucNang=" + chucNang + ", nhanVien=" + nhanVien + "]";
}



}
