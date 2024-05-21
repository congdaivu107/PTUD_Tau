//4.	Trần Thị Anh Thư - 19516531 (Note taker)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;

public class ChucVu_Dao {
	ArrayList<ChucVu> dsChucVu;
	public ArrayList<ChucVu> gettalltbChucVu() {
		dsChucVu= new ArrayList<ChucVu>();
		try {
			ConnectDB.getInstance();
			Connection con= ConnectDB.getConnection();
			
			String sql="Select * from ChucVu";
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
				String maCV=rs.getString(1);
				String tenCV=rs.getString(2);
				ChucVu p=new ChucVu(maCV, tenCV);
				dsChucVu.add(p);
				
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsChucVu;
	}
	public ChucVu getChucVuTheoMa(String ma) {
		ChucVu cc = null;
		
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from ChucVu where maChucVu = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return cc;
			} else {
				String maChucVu = rs.getString("maChucVu");
				String tenChucVu = rs.getString("tenChucVu");
				cc = new ChucVu(maChucVu, tenChucVu);
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
		return cc;
	}
	public boolean create(ChucVu cv) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" ChucVu values(?, ?)");
			stmt.setString(1, cv.getMaChucVu());
			stmt.setString(2,cv.getTenChucVu());
			
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
		
	public boolean update(ChucVu nv) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update ChucVu set tenChucVu=?"+" where maChucVu=?");
			stmt.setString(1,nv.getTenChucVu());
			stmt.setString(2,nv.getMaChucVu());
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
	public int count() {
		// TODO Auto-generated method stub
		return dsChucVu.size();
	}
	public ArrayList<ChucVu> getDsSach() {
		return dsChucVu;
	}
	public ChucVu getChucVuTheoTen(String ten) {
		ChucVu cv = null;
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from ChucVu where tenChucVu = ? order by maChucVu";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return cv;
			} else {
				String maCV = rs.getString("maChucVu");
				String tenCV = rs.getString("tenChucVu");
				cv = new ChucVu(maCV, tenCV);
				return cv;
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
		return cv;
	}
}
