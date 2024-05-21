// 2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.NhanVien;
import entity.PhieuNhap;

public class PhieuNhap_DAO {
	public ArrayList<PhieuNhap> getAllPhieuNhaps() {
		ArrayList<PhieuNhap> dsPN = new ArrayList<PhieuNhap>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from PhieuNhap order by maPhieuNhap";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maPhieuNhap = rs.getString("maPhieuNhap");
				String nhaCC = rs.getString("nhaCC");
				Date ngayNhap = rs.getDate("ngayNhap");
				double tongTien = rs.getDouble("tongTien");
				double thue = rs.getDouble("thue");
				NhanVien nhanVien = new NhanVien_DAO().getNhanVienTheoMaNV(rs.getString("maNV"));
				PhieuNhap phieuNhap = new PhieuNhap(maPhieuNhap, nhaCC, ngayNhap, thue, nhanVien);
				dsPN.add(phieuNhap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsPN;
	}
	
	public PhieuNhap getPhieuNhapTheoMa(String ma) {
		PhieuNhap phieuNhap = null;
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from PhieuNhap where maPhieuNhap = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return phieuNhap;
			} else {
				String maPhieuNhap = rs.getString("maPhieuNhap");
				String nhaCC = rs.getString("nhaCC");
				Date ngayNhap = rs.getDate("ngayNhap");
				double tongTien = rs.getDouble("tongTien");
				double thue = rs.getDouble("thue");
				NhanVien nhanVien = new NhanVien_DAO().getNhanVienTheoMaNV(rs.getString("maNV"));
				phieuNhap = new PhieuNhap(maPhieuNhap, nhaCC, ngayNhap, thue, nhanVien);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return phieuNhap;
	}
	
	public boolean create(PhieuNhap sp) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" PhieuNhap values(?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sp.getMaPhieuNhap());
			stmt.setString(2, sp.getNhaCC());
			stmt.setDate(3, sp.getNgayNhap());
			stmt.setDouble(4, sp.getTongTien());
			stmt.setDouble(5, sp.getThue());
			stmt.setString(6, sp.getNhanVien().getMaNV());
			
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
	
	public boolean update(PhieuNhap pn) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update PhieuNhap set nhaCC=?, ngayNhap= ?, tongTien= ?, thue=?, maNV= ? where maPhieuNhap= ?");
			
			stmt.setString(1, pn.getNhaCC());
			stmt.setDate(2, pn.getNgayNhap());
			stmt.setDouble(3, pn.getTongTien());
			stmt.setDouble(4, pn.getThue());
			stmt.setString(5, pn.getNhanVien().getMaNV());
			stmt.setString(6, pn.getMaPhieuNhap());
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
	
	public boolean delete(String maPN) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			String str = "delete from PhieuNhap where maPhieuNhap=?";
			stmt=con.prepareStatement(str);
			stmt.setString(1, maPN);
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
	
	public ArrayList<PhieuNhap> getPhieuNhapTheoMaNV(String ma) {
		ArrayList<PhieuNhap> ds = new ArrayList<PhieuNhap>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from PhieuNhap where maNV = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maPhieuNhap = rs.getString("maPhieuNhap");
				String nhaCC = rs.getString("nhaCC");
				Date ngayNhap = rs.getDate("ngayNhap");
				double tongTien = rs.getDouble("tongTien");
				double thue = rs.getDouble("thue");
				NhanVien nhanVien = new NhanVien_DAO().getNhanVienTheoMaNV(rs.getString("maNV"));
				PhieuNhap phieuNhap = new PhieuNhap(maPhieuNhap, nhaCC, ngayNhap, thue, nhanVien);
				System.out.println(phieuNhap);
				ds.add(phieuNhap);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ds;
	}
}
