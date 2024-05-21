//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package entity;

import java.util.Objects;

public class Loai {
	private String maLoai;
	private String tenLoai;
	public Loai(String maLoai, String tenLoai) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
	}
	
	public Loai(String maLoai) {
		super();
		this.maLoai = maLoai;
	}

	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	@Override
	public String toString() {
		return "Loai [maLoai=" + maLoai + ", tenLoai=" + tenLoai + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoai);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loai other = (Loai) obj;
		return Objects.equals(maLoai, other.maLoai);
	}
	
	
	
}
