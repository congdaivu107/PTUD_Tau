//3.	Nguyễn Nhật Linh - 19530601 (Time keeper)
package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class KhachHang_DAO {
    public ArrayList<KhachHang> getAllKhachHang() {
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from KhachHang order by maKH";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maKH = rs.getString("maKH");
				String tenKH = rs.getString("tenKH");
				String sdt = rs.getString("sdt");
				String diaChi = rs.getString("diaChi");
                                KhachHang KhachHang = new KhachHang(tenKH, maKH, sdt,diaChi);
				ds.add(KhachHang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
    public KhachHang getKhachHangTheoMa(String ma) {
		KhachHang khachHang = null;
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from KhachHang where maKH = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return khachHang;
			} else {
				String maKH = rs.getString("maKH");
				String tenKH = rs.getString("tenKH");
				String sdt = rs.getString("sdt");
				
				String diaChi = rs.getString("diaChi");
				
				
				khachHang = new KhachHang(tenKH, maKH, sdt,diaChi);
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
		return khachHang;
	}
    public boolean update(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update KhachHang set tenKH= ?, sdt= ?, diaChi= ? Where maKH= ?");
			
			stmt.setString(1, kh.getTenKH());
			stmt.setString(2, kh.getSdt());
			stmt.setString(3, kh.getDiaChi());
                        stmt.setString(4, kh.getMaKH());
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
    
    public boolean create(KhachHang kh) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" KhachHang values(?, ?, ?, ?)");
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getTenKH());
			stmt.setString(3, kh.getDiaChi());
			stmt.setString(4, kh.getSdt());
			
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
    public boolean delete(String maKH) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			String str = "delete from KhachHang where maKH = ?";
			stmt=con.prepareStatement(str);
			stmt.setString(1, maKH);
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
    
	public ArrayList<KhachHang> getKhachHangTheoTen(String ten) {
		ArrayList<KhachHang> ds = new ArrayList<KhachHang>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from KhachHang where tenKH = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maKH = rs.getString("maKH");
				String tenKH = rs.getString("tenKH");
				String sdt = rs.getString("sdt");
				String diaChi = rs.getString("diaChi");
                KhachHang KhachHang = new KhachHang(tenKH, maKH, sdt,diaChi);
				ds.add(KhachHang);
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
	
	
	
	/// Them vao nha quang
	public int getMaKH() {
		int ma = 1;
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="select max( CONVERT(int , SUBSTRING(maKH,3 , 10))) from KhachHang";
			statement=con.prepareStatement(sql);
			ResultSet rs=statement.executeQuery();
			 if (rs.next() == false) {
				  return ma;
			 }
			 else {
				 	ma = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return ma + 1;
	}
	public KhachHang getKhachHangTheoSDT(String SDT) {
		KhachHang kh = null;
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="Select * from KhachHang where sdt = ?";
			statement=con.prepareStatement(sql);
			statement.setString(1, SDT);
			ResultSet rs=statement.executeQuery();
			 if (rs.next() == false) {
				  return kh;
			 }
			 else {
				 String makh=rs.getString("maKH");
					String tenKH=rs.getString("tenKH");	
					String diaChi=rs.getString("diaChi");
					String sdt=rs.getString("sdt");
					kh=new KhachHang(tenKH, makh , sdt, diaChi);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				statement.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}return kh;
	}
}
