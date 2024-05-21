//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;
import entity.User;

public class User_Dao {

	public ArrayList<User> gettalltbTaiKhoan() {
		ArrayList<User> dsTaiKhoan=new ArrayList<User>();
		try {
			ConnectDB.getInstance();
			Connection con= ConnectDB.getConnection();
			
			String sql="Select * from [dbo].[User]";
			Statement statement=con.createStatement();
			ResultSet rs=statement.executeQuery(sql);
			while (rs.next()) {
				String id=rs.getString(1);
				String password=rs.getString(2);
				ChucVu chucNang=new ChucVu_Dao().getChucVuTheoMa(rs.getString(3));
				NhanVien nv=new NhanVien_DAO().getNhanVienTheoMaNV(rs.getString(4));
				User user=new User(id, password, chucNang, nv);
				dsTaiKhoan.add(user);
				
				
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsTaiKhoan;
	}
	
	public boolean create(User us) throws NoSuchAlgorithmException {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" [dbo].[User] values(?, ?, ?, ?)");
			stmt.setString(1, us.getId());
			stmt.setString(2,us.getPassword());
			stmt.setString(3,us.getChucNang().getMaChucVu());
			stmt.setString(4,us.getNhanVien().getMaNV());
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
	public static String md5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            //convert the byte to hex format method 1
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return  sb.toString();
        } catch (Exception ex) {
            return "";
        }
    }
	public boolean delete(String matk) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			String sql = "delete from [dbo].[User] where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, matk);
			n= stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n > 0;

	}
	public boolean update(User us) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update [dbo].[User] set password = ?, chucNang = ? , maNV = ? where id = ?");
			stmt.setString(1,us.getPassword());
			stmt.setString(2,us.getChucNang().getMaChucVu());
			stmt.setString(3,us.getNhanVien().getMaNV());
			stmt.setString(4,us.getId());
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
	
	public User getUserTheomaNVFirst(String ma) {
		User user = null;
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from [dbo].[User] where maNV = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1,ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return user;
			} else {
				String id=rs.getString(1);
				String password=rs.getString(2);
				ChucVu chucNang=new ChucVu_Dao().getChucVuTheoMa(rs.getString(3));
				NhanVien nv=new NhanVien_DAO().getNhanVienTheoMaNV(rs.getString(4));
				user=new User(id,password,chucNang,nv);
				return user;
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
		return user;
	}
	
}
