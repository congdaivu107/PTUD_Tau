//1.	Nguyễn Quý Khả - 19530291 (Leader)
package entity;

import java.sql.Date;

public class Ve {
	private String maHD;
	private String maNV;
	private String maKH;
	private Date ngayBan;
	private double tongTien;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public Date getNgayBan() {
		return ngayBan;
	}
	public void setNgayBan(Date ngayBan) {
		this.ngayBan = ngayBan;
	}
	public double getTongTien() {
		return tongTien;
	}
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maHD == null) ? 0 : maHD.hashCode());
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
		Ve other = (Ve) obj;
		if (maHD == null) {
			if (other.maHD != null)
				return false;
		} else if (!maHD.equals(other.maHD))
			return false;
		return true;
	}
	public Ve(String maHD, String maNV, String maKH, Date ngayBan, double tongTien) {
		super();
		setMaHD(maHD);
		setMaKH(maKH);
		setMaNV(maNV);
		setNgayBan(ngayBan);
		setTongTien(tongTien);
	}

	public Ve(String maHD) {
		super();
		setMaHD(maHD);
	}


	
	public Ve(String maHD, String maKH, double tongTien) {
		super();
		this.maHD = maHD;
		this.maKH = maKH;
		this.tongTien = tongTien;
	}
	@Override
	public String toString() {
		return "HoaDon [maHD=" + maHD + ", maNV=" + maNV + ", maKH=" + maKH + ", ngayBan=" + ngayBan
				+ ", tongTien=" + tongTien + "]";
	}
	
	
}
