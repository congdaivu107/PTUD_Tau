//	Nguyễn Nhật Quang - 19437361 (Reporter)
package entity;

import java.util.Objects;

public class ChuyenTau {
	private String maSP;
	private String tenSP;
	private String donViSP;
	private double giaNhap;
	private double giaBan;
	private String tinhTrang;
	private int tonKho;
	private String image;
	private NhaGa nhaCC;
	private Loai loai;
	public ChuyenTau(String maSP, String tenSP, String donViSP, double giaNhap, double giaBan, String tinhTrang,
			int tonKho, String image, NhaGa nhaCC, Loai loai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donViSP = donViSP;
		this.giaNhap = giaNhap;
		this.giaBan = giaBan;
		this.tinhTrang = tinhTrang;
		this.tonKho = tonKho;
		this.image = image;
		this.nhaCC = nhaCC;
		this.loai = loai;
	}
	
	public ChuyenTau(String maSP, String tenSP, String donViSP, double giaBan, String tinhTrang, int tonKho, String image,
			NhaGa nhaCC, Loai loai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.donViSP = donViSP;
		this.giaBan = giaBan;
		this.tinhTrang = tinhTrang;
		this.tonKho = tonKho;
		this.image = image;
		this.nhaCC = nhaCC;
		this.loai = loai;
	}

	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public String getDonViSP() {
		return donViSP;
	}
	public void setDonViSP(String donViSP) {
		this.donViSP = donViSP;
	}
	public double getGiaNhap() {
		return giaNhap;
	}
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	public double getGiaBan() {
		return giaBan;
	}
	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}
	public String getTinhTrang() {
		return tinhTrang;
	}
	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}
	public int getTonKho() {
		return tonKho;
	}
	public void setTonKho(int tonKho) {
		this.tonKho = tonKho;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public NhaGa getNhaCC() {
		return nhaCC;
	}
	public void setNhaCC(NhaGa nhaCC) {
		this.nhaCC = nhaCC;
	}
	public Loai getLoai() {
		return loai;
	}
	public void setLoai(Loai loai) {
		this.loai = loai;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", donViSP=" + donViSP + ", giaNhap=" + giaNhap
				+ ", giaBan=" + giaBan + ", tinhTrang=" + tinhTrang + ", tonKho=" + tonKho + ", image=" + image
				+ ", nhaCC=" + nhaCC + ", loai=" + loai + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maSP);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuyenTau other = (ChuyenTau) obj;
		return Objects.equals(maSP, other.maSP);
	}
	
	
	
	
	
}
