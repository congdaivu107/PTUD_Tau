//	Nguyễn Nhật Quang - 19437361 (Reporter)
package entity;

import java.sql.Date;
import java.util.List;

import dao.ChiTietPhieuNhap_DAO;

public class PhieuNhap {
	private String maPhieuNhap;
	private String nhaCC;
	private Date ngayNhap;
	private double tongTien;
	private double thue;
	private NhanVien nhanVien;

	private List<ChiTietPhieuNhap> chiTietPhieuNhaps;
	
	public List<ChiTietPhieuNhap> getChiTietPhieuNhaps() {
		return chiTietPhieuNhaps;
	}
	public void setChiTietPhieuNhaps(List<ChiTietPhieuNhap> chiTietPhieuNhaps) {
		this.chiTietPhieuNhaps = chiTietPhieuNhaps;
	}
	public String getMaPhieuNhap() {
		return maPhieuNhap;
	}
	public void setMaPhieuNhap(String maPhieuNhap) {
		this.maPhieuNhap = maPhieuNhap;
	}
	public String getNhaCC() {
		return nhaCC;
	}
	public void setNhaCC(String nhaCC) {
		this.nhaCC = nhaCC;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public double getTongTien() {
		return tongTien;
	}
	
	public double getThue() {
		return thue;
	}
	public void setThue(double thue) {
		this.thue = thue;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public PhieuNhap(String maPhieuNhap, String nhaCC, Date ngayNhap, double thue, NhanVien nhanVien) {
		super();
		this.maPhieuNhap = maPhieuNhap;
		this.nhaCC = nhaCC;
		this.ngayNhap = ngayNhap;
		this.thue = thue;
		this.nhanVien = nhanVien;
		int sumThanhTien = 0;
		this.chiTietPhieuNhaps = new ChiTietPhieuNhap_DAO().getChiTietPhieuNhapTheoMaPhieuNhap(maPhieuNhap);
		for (ChiTietPhieuNhap chiTietPhieuNhap : chiTietPhieuNhaps) {
			sumThanhTien+= chiTietPhieuNhap.getThanhTien();
		}
		this.tongTien = (double) Math.ceil((sumThanhTien + (this.thue/100)*sumThanhTien) * 100) / 100;
	}
	
	@Override
	public String toString() {
		return "PhieuNhap [maPhieuNhap=" + maPhieuNhap + ", nhaCC=" + nhaCC + ", ngayNhap=" + ngayNhap + ", tongTien="
				+ tongTien + ", thue=" + thue + ", nhanVien=" + nhanVien + "]";
	}
	
	public boolean isContainsCTPN(ChiTietPhieuNhap chiTietPhieuNhap) {
		return chiTietPhieuNhaps.contains(chiTietPhieuNhap);
	}
	
}
