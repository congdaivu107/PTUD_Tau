//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.Loai;

public class Loai_DAO {
	public ArrayList<Loai> getAllLoai() {
		ArrayList<Loai> dsLoai = new ArrayList<Loai>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from Loai order by maLoai";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maLoai = rs.getString("maLoai");
				String tenLoai = rs.getString("tenLoai");
				Loai loai = new Loai(maLoai, tenLoai);
				dsLoai.add(loai);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsLoai;
	}

	public Loai getLoaiTheoMa(String ma) {
		Loai loai = null;
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from Loai where maLoai = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return loai;
			} else {
				String maLoai = rs.getString("maLoai");
				String tenLoai = rs.getString("tenLoai");
				loai = new Loai(maLoai, tenLoai);
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
		return loai;
	}
	
	public Loai getLoaiTheoTenFirst(String ten) {
		Loai loai = null;
		
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from Loai where tenLoai = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return loai;
			} else {
				String maLoai = rs.getString("maLoai");
				String tenLoai = rs.getString("tenLoai");
				loai = new Loai(maLoai, tenLoai);
				return loai;
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
		return loai;
	}
	
	public ArrayList<Loai> getLoaiTheoTen(String ten) {
		ArrayList<Loai> ds = new ArrayList<Loai>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from Loai where tenLoai = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maLoai = rs.getString("maLoai");
				String tenLoai = rs.getString("tenLoai");
				Loai loai = new Loai(maLoai, tenLoai);
				ds.add(loai);
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
	
	public boolean update(Loai loai) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update Loai set tenLoai=? where maLoai= ?");
			
			stmt.setString(1, loai.getTenLoai());
			stmt.setString(2, loai.getMaLoai());
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
	
	public boolean create(Loai loai) {
		ConnectDB.getInstance(); 
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" Loai values(?, ?)");
			stmt.setString(1, loai.getMaLoai());
			stmt.setString(2, loai.getTenLoai());
			
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
	
	public boolean delete(String maLoai) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			String str = "delete from Loai where maLoai = ?";
			stmt=con.prepareStatement(str);
			stmt.setString(1, maLoai);
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
