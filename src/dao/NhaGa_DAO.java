//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.NhaGa;

public class NhaGa_DAO {
	public ArrayList<NhaGa> getAllNhaCC() {
		ArrayList<NhaGa> dsNhacCC = new ArrayList<NhaGa>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from NhaCC";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNhaCC = rs.getString("maNhaCC");
				String tenNhaCC = rs.getString("tenNhaCC");
				NhaGa nhaCC = new NhaGa(maNhaCC, tenNhaCC);
				dsNhacCC.add(nhaCC);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dsNhacCC;
	}

	public NhaGa getNhaCCTheoMa(String ma) {
		NhaGa nhaCC = null;
		
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from NhaCC where maNhaCC = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return nhaCC;
			} else {
				String maNhaCC = rs.getString("maNhaCC");
				String tenNhaCC = rs.getString("tenNhaCC");
				nhaCC = new NhaGa(maNhaCC, tenNhaCC);
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
		return nhaCC;
	}
	//
	
	
	public NhaGa getNhaCCTheoTenFirst(String ten) {
		NhaGa nhaCC = null;
		
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from NhaCC where tenNhaCC = ? order by maNhaCC";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return nhaCC;
			} else {
				String maNhaCC = rs.getString("maNhaCC");
				String tenNhaCC = rs.getString("tenNhaCC");
				nhaCC = new NhaGa(maNhaCC, tenNhaCC);
				return nhaCC;
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
		return nhaCC;
	}

	public boolean create(NhaGa nhaCC) {
		ConnectDB.getInstance(); 
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" NhaCC values(?, ?)");
			stmt.setString(1, nhaCC.getMaNhaCC());
			stmt.setString(2, nhaCC.getTenNhaCC());
			
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

	public boolean delete(String maNhaCC) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			String str = "delete from NhaCC where maNhaCC = ?";
			stmt=con.prepareStatement(str);
			stmt.setString(1, maNhaCC);
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
	
	public boolean update(NhaGa nhaCC) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update NhaCC set tenNhaCC=? where maNhaCC= ?");
			
			stmt.setString(1, nhaCC.getTenNhaCC());
			stmt.setString(2, nhaCC.getMaNhaCC());
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

	public List<NhaGa> getNhaCCTheoTen(String ten) {
		ArrayList<NhaGa> ds = new ArrayList<NhaGa>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from NhaCC where tenNhaCC = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maNhaCC = rs.getString("maNhaCC");
				String tenNhaCC = rs.getString("tenNhaCC");
				NhaGa nhaCC = new NhaGa(maNhaCC, tenNhaCC);
				ds.add(nhaCC);
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
