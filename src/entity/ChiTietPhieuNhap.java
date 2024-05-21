//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package entity;

import java.util.Objects;

public class ChiTietPhieuNhap {
	private double donGia;
	private int soLuong;
	private double thanhTien;
	private ChuyenTau sanPham;
	private String maPhieuNhap;
	public ChiTietPhieuNhap(double donGia, int soLuong, ChuyenTau sanPham, String maPhieuNhap) {
		super();
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.thanhTien = soLuong * donGia;
		this.sanPham = sanPham;
		this.maPhieuNhap = maPhieuNhap;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void tinhThanhTien(double thanhTien) {
		this.thanhTien = donGia * soLuong;
	}
	public ChuyenTau getSanPham() {
		return sanPham;
	}
	public void setSanPham(ChuyenTau sanPham) {
		this.sanPham = sanPham;
	}
	public String getMaPhieuNhap() {
		return maPhieuNhap;
	}
	public void setMaPhieuNhap(String maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(maPhieuNhap, sanPham);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietPhieuNhap other = (ChiTietPhieuNhap) obj;
		return Objects.equals(maPhieuNhap, other.maPhieuNhap) && Objects.equals(sanPham, other.sanPham);
	}
	@Override
	public String toString() {
		return "ChiTietPhieuNhap [donGia=" + donGia + ", soLuong=" + soLuong + ", thanhTien=" + thanhTien + ", sanPham="
				+ sanPham + ", maPhieuNhap=" + maPhieuNhap + "]";
	}
	
	
}
