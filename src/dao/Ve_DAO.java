//1.	Nguyễn Quý Khả - 19530291 (Leader)
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Ve;

public class Ve_DAO {
	public ArrayList<Ve> getAllHoaDon() {
		ArrayList<Ve> dsHD = new ArrayList<Ve>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from HoaDon";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				Date ngayban = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				double tongTien = rs.getDouble(5);
				Ve hd = new Ve(maHD, maNV, maKH, ngayban, tongTien);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsHD;
	}
	public ArrayList<Ve> getAllHoaDonTop40(String ngay) {
		ArrayList<Ve> dsHD = new ArrayList<Ve>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT TOP 40 HoaDon.maHD, HoaDon.maKH,Tổng=sum( HoaDon.tongTien)\r\n"
					+ "FROM     \r\n"
					+ "                  HoaDon \r\n"
					+ "				  where [ngayTao] <'"+ngay+"'\r\n"
					+ "GROUP BY HoaDon.maHD, HoaDon.maKH";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				String maKH = rs.getString(2);
				double tongTien = rs.getDouble(3);
				Ve hd = new Ve(maHD, maKH, tongTien);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsHD;
	}

	public boolean create(Ve hd) {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("insert into" + " HoaDon values(?, ?, ?, ?, ?)");
			stmt.setString(1, hd.getMaHD());
			stmt.setDate(2, hd.getNgayBan());
			stmt.setString(3, hd.getMaKH());
			stmt.setString(4, hd.getMaNV());
			stmt.setDouble(5, hd.getTongTien());
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

	public boolean delete(String maHD) {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String str = "delete from HoaDon where maHD = ?";
			stmt = con.prepareStatement(str);
			stmt.setString(1, maHD);
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

	public boolean update(Ve hd) {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con
					.prepareStatement("update HoaDon set maNhanVien=?, maKhach=?, ngayBan=?, tongTien=? where maHD= ?");
			stmt.setString(1, hd.getMaNV());
			stmt.setString(2, hd.getMaKH());
			stmt.setDate(3, hd.getNgayBan());
			stmt.setDouble(4, hd.getTongTien());
			stmt.setString(5, hd.getMaHD());
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

	public Ve getHoaDonTheoMaHD(String maHD) {
		Ve hd = null;
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from HoaDon where maHD = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maHD);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return hd;
			} else {
				String mahd = rs.getString(1);
				Date ngayban = rs.getDate(2);
				String maKH = rs.getString(3);
				String maNV = rs.getString(4);
				double tongTien = rs.getDouble(5);
				hd = new Ve(mahd, maNV, maKH, ngayban, tongTien);
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
		return hd;
	}

	public ArrayList<Ve> getHoaDonTheoDK(String dk) {
		ArrayList<Ve> dsHD = new ArrayList<Ve>();
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from HoaDon where " + dk;
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maHD = rs.getString(1);
				String manv = rs.getString(2);
				String makh = rs.getString(3);
				Date ngayban = rs.getDate(4);
				double tongTien = rs.getDouble(5);
				Ve hd = new Ve(maHD, makh, manv, ngayban, tongTien);
				dsHD.add(hd);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsHD;
	}

	public int getMaHd() {
		int ma = 1;
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "select max( CONVERT(int , SUBSTRING(maHD,3 , 5))) from [dbo].[HoaDon]";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return ma;
			} else {
				ma = rs.getInt(1);
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
		return ma + 1;
	}
	public ArrayList<Ve> getHDTheoMaNV(String maNV) {
		ArrayList<Ve> dsHD = new ArrayList<Ve>();
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "Select * from HoaDon where maNV = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maNV);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maHD = rs.getString(1);
				String manv = rs.getString(4);
				String makh = rs.getString(3);
				Date ngayban = rs.getDate(2);
				double tongTien = rs.getDouble(5);
				Ve hd = new Ve(maHD, makh, manv, ngayban, tongTien);
				dsHD.add(hd);
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
		return dsHD;
	}
}
