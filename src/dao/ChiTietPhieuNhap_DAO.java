// 2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.ChiTietPhieuNhap;
import entity.ChuyenTau;

public class ChiTietPhieuNhap_DAO {
	
	public ArrayList<ChiTietPhieuNhap> getAllChiTietPhieuNhaps() {
		ArrayList<ChiTietPhieuNhap> dsCTPN = new ArrayList<ChiTietPhieuNhap>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietPhieuNhap order by maPhieuNhap";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				double thanhTien = rs.getDouble("thanhTien");
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");
				ChuyenTau sanPham = new ChuyenTau_DAO().getSanPhamTheoMa(rs.getString("maSP"));
				String maPhieuNhap = rs.getString("maPhieuNhap");
				ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(donGia, soLuong, sanPham, maPhieuNhap);
				dsCTPN.add(chiTietPhieuNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTPN;
	}

	public ArrayList<ChiTietPhieuNhap> getChiTietPhieuNhapTheoMaPhieuNhap(String ma) {
		ArrayList<ChiTietPhieuNhap> dsCTPN = new ArrayList<ChiTietPhieuNhap>();
		PreparedStatement statement=null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietPhieuNhap where maPhieuNhap =?";
			statement=con.prepareStatement(sql);
			statement.setString(1,ma);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				double thanhTien = rs.getDouble("thanhTien");
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");
				ChuyenTau sanPham = new ChuyenTau_DAO().getSanPhamTheoMa(rs.getString("maSP"));
				String maPhieuNhap = rs.getString("maPhieuNhap");
				ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(donGia, soLuong, sanPham, maPhieuNhap);
				dsCTPN.add(chiTietPhieuNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTPN;
	}
	
	public ChiTietPhieuNhap getChiTietPhieuNhapTheoMaPhieuNhapVaMaSP(String maPN, String maSP) {
		ChiTietPhieuNhap CTPN = null;
		PreparedStatement statement=null;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietPhieuNhap where maPhieuNhap =? and maSP = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1,maPN);
			statement.setString(2, maSP);
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				double thanhTien = rs.getDouble("thanhTien");
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");
				ChuyenTau sanPham = new ChuyenTau_DAO().getSanPhamTheoMa(rs.getString("maSP"));
				String maPhieuNhap = rs.getString("maPhieuNhap");
				CTPN = new ChiTietPhieuNhap(donGia, soLuong, sanPham, maPhieuNhap);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CTPN;
	}
	
	public boolean update(ChiTietPhieuNhap sp) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update ChiTietPhieuNhap set thanhTien=?, soLuong= ?, donGia= ? where maSP= ? and maPhieuNhap=?");
			
			stmt.setDouble(1, sp.getThanhTien());
			stmt.setInt(2, sp.getSoLuong());
			stmt.setDouble(3, sp.getDonGia());
			stmt.setString(4, sp.getSanPham().getMaSP());
			stmt.setString(5, sp.getMaPhieuNhap());
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}

	public boolean create(ChiTietPhieuNhap sp) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" ChiTietPhieuNhap values(?, ?, ?, ?, ?)");
			stmt.setDouble(1, sp.getThanhTien());
			stmt.setInt(2, sp.getSoLuong());
			stmt.setDouble(3, sp.getDonGia());
			stmt.setString(4, sp.getMaPhieuNhap());
			stmt.setString(5, sp.getSanPham().getMaSP());
			
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}return n>0;
	}

	public boolean delete(String maSP, String maPN) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			String str = "delete from ChiTietPhieuNhap where maSP= ? and maPhieuNhap=?";
			stmt=con.prepareStatement(str);
			stmt.setString(1, maSP);
			stmt.setString(2, maPN);
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n>0;
	}
}
