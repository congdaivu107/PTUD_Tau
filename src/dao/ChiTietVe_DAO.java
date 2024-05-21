// 1.	Nguyễn Quý Khả - 19530291 (Leader)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChiTietVe;

public class ChiTietVe_DAO {
	public ArrayList<ChiTietVe> getAllChiTietHD() {
		ArrayList<ChiTietVe> dsCTHD = new ArrayList<ChiTietVe>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from ChiTietH";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maSP = rs.getString(2);
				int soLuong = rs.getInt(3);
				double donGia = rs.getDouble(4);
				double giamGia = rs.getDouble(5);
				double thanhTien = rs.getDouble(6);
				ChiTietVe cthd = new ChiTietVe(maHD, maSP, soLuong, donGia, giamGia, thanhTien);
				dsCTHD.add(cthd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsCTHD;
	}

	public boolean create(ChiTietVe cthd) {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " ChiTietHoaDon values(?, ?, ?, ?, ?, ?)");

			stmt.setDouble(1, cthd.getGiamGia());
			stmt.setInt(2, cthd.getSoLuong());
			stmt.setDouble(3, cthd.getDonGia());
			stmt.setString(4, cthd.getMaHD());
			stmt.setString(5, cthd.getMaSP());
			stmt.setDouble(6, cthd.getThanhTien());
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean delete(String maHD, String maSP) {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String str = "delete from ChiTietHoaDon where maHD = ? and maSP = ?";
			stmt = con.prepareStatement(str);
			stmt.setString(1, maHD);
			stmt.setString(2, maSP);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public boolean update(ChiTietVe cthd) {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement(
					"update ChiTietHoaDon set soLuong=?, donGia=?, giamGia=?, thanhTien=? where maHD = ? and maSP = ?");
			stmt.setInt(1, cthd.getSoLuong());
			stmt.setDouble(2, cthd.getDonGia());
			stmt.setDouble(3, cthd.getGiamGia());
			stmt.setDouble(4, cthd.getThanhTien());
			stmt.setString(5, cthd.getMaHD());
			stmt.setString(6, cthd.getMaSP());

			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return n > 0;
	}

	public ArrayList<ChiTietVe> getChiTietHDTheoMaHD(String maHD) {
		ArrayList<ChiTietVe> dsCTHD = new ArrayList<ChiTietVe>();
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from ChiTietHoaDon where maHD = ? ";
			statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString("maHD");
				String maSP = rs.getString("maSP");
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");
				double giamGia = rs.getDouble("giamGia");
				double thanhTien = rs.getDouble("thanhTien");
				ChiTietVe cthd = new ChiTietVe(mahd, maSP, soLuong, donGia, giamGia, thanhTien);
				dsCTHD.add(cthd);
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
		return dsCTHD;
	}
	public ArrayList<ChiTietVe> getChiTietHDTheoMaSP(String maSPnew) {
		ArrayList<ChiTietVe> dsCTHD = new ArrayList<ChiTietVe>();
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from ChiTietHoaDon where maSP = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maSPnew);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String mahd = rs.getString("maHD");
				String maSP = rs.getString("maSP");
				int soLuong = rs.getInt("soLuong");
				double donGia = rs.getDouble("donGia");
				double giamGia = rs.getDouble("giamGia");
				double thanhTien = rs.getDouble("thanhTien");
				ChiTietVe cthd = new ChiTietVe(mahd, maSP, soLuong, donGia, giamGia, thanhTien);
				dsCTHD.add(cthd);
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
		return dsCTHD;
	}

}
