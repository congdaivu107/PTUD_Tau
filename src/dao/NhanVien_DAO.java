//4.	Trần Thị Anh Thư - 19516531 (Note taker)
package dao;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import connectDB.ConnectDB;
import entity.ChucVu;
import entity.NhanVien;


public class NhanVien_DAO {
	
public ArrayList<NhanVien> gettalltbNhanVien() {
	ArrayList<NhanVien> dsNV=new ArrayList<NhanVien>();
	try {
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		String sql="select * from NhanVien";
		Statement statement= con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		while (rs.next()) {
			String maNV=rs.getString("maNV");
			String tenNV=rs.getString("tenNV");
			String diaChi=rs.getString("diaChi");
			String gioiTinh=rs.getString("gioiTinh");
			Date ngayVaoLam = rs.getDate("ngayVaoLam");
			ChucVu chucVu=new ChucVu_Dao().getChucVuTheoMa(rs.getString("maChucVu"));
			String email=rs.getString("email");
			String sdt=rs.getString("sdt");
			NhanVien nv=new NhanVien(maNV, tenNV, diaChi, gioiTinh, ngayVaoLam, chucVu, email, sdt);
			dsNV.add(nv);
		}
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return dsNV;
}
//Select * from [dbo].[NhanVien]  where not maChucVu ='CV00001'
public ArrayList<NhanVien> gettalltbNhanVienNotQL() {
	ArrayList<NhanVien> dsNV=new ArrayList<NhanVien>();
	try {
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		String sql="Select * from [dbo].[NhanVien]  where not maChucVu ='CV00001'";
		Statement statement= con.createStatement();
		ResultSet rs=statement.executeQuery(sql);
		while (rs.next()) {
			String maNV=rs.getString("maNV");
			String tenNV=rs.getString("tenNV");
			String diaChi=rs.getString("diaChi");
			String gioiTinh=rs.getString("gioiTinh");
			Date ngayVaoLam = rs.getDate("ngayVaoLam");
			ChucVu chucVu=new ChucVu_Dao().getChucVuTheoMa(rs.getString("maChucVu"));
			String email=rs.getString("email");
			String sdt=rs.getString("sdt");
			NhanVien nv=new NhanVien(maNV, tenNV, diaChi, gioiTinh, ngayVaoLam, chucVu, email, sdt);
			dsNV.add(nv);
		}
	} catch (SQLException e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	return dsNV;
}

//UPDATE
//[dbo].[NhanVien]
//SET
//[dbo].[NhanVien].maChucVu  = ?
//from NhanVien,ChucVu 
//where NhanVien.maChucVu=ChucVu.maChucVu and [dbo].[NhanVien].maNV =?
	public boolean updateMaChucVu(NhanVien nv) {
		
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("UPDATE\r\n"
					+ "   [dbo].[NhanVien]\r\n"
					+ "SET\r\n"
					+ "   [dbo].[NhanVien].maChucVu  = ?\r\n"
					+ "from NhanVien,ChucVu \r\n"
					+ "where NhanVien.maChucVu=ChucVu.maChucVu and [dbo].[NhanVien].maNV =?");
			stmt.setString(1,nv.getChucVu().getMaChucVu());
			stmt.setString(2,nv.getMaNV());
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
	
	public NhanVien getNhanVienTheoTenNV(String ten) {
		NhanVien nhanVien=null;
		ConnectDB.getInstance();
		Connection con= ConnectDB.getConnection();
		PreparedStatement statement=null;
		try {
			String sql="select * from NhanVien where tenNV=? ";
			statement=con.prepareStatement(sql);
			statement.setString(1, ten);
			
			ResultSet rs=statement.executeQuery();
			while (rs.next()) {
				String maNV=rs.getString("maNV");
				String tenNV=rs.getString("tenNV");
				String diaChi=rs.getString("diaChi");
				String gioiTinh=rs.getString("gioiTinh");
				Date ngayVaoLam = rs.getDate("ngayVaoLam");
				ChucVu chucVu=new ChucVu_Dao().getChucVuTheoMa(rs.getString("maChucVu"));
				String email=rs.getString("email");
				String sdt=rs.getString("sdt");
				nhanVien =new NhanVien(maNV, tenNV, diaChi, gioiTinh, ngayVaoLam, chucVu, email, sdt);
				return nhanVien;
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
		}return nhanVien;
	}
	
	public boolean update(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update NhanVien set tenNV = ?, diaChi = ? , gioiTinh = ?, ngayVaoLam = ?, maChucVu = ?, email = ?, sdt = ? where maNV = ?");
			stmt.setString(1,nv.getTenNV());
			stmt.setString(2,nv.getDiaChi());
			stmt.setString(3,nv.getGioiTinh());
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String date= simpleDateFormat.format(nv.getNgayVaoLam());
			stmt.setString(4,date);
			stmt.setString(5,nv.getChucVu().getMaChucVu());
			stmt.setString(6,nv.getEmail());
			stmt.setString(7,nv.getSdt());
			stmt.setString(8,nv.getMaNV());
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

	public boolean create(NhanVien nv) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" NhanVien values(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1,nv.getMaNV());
			stmt.setString(2,nv.getTenNV());
			stmt.setString(3,nv.getDiaChi());
			stmt.setString(4,nv.getGioiTinh());
			stmt.setString(5,nv.getEmail());
			SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String date= simpleDateFormat.format(nv.getNgayVaoLam());
			stmt.setString(6, date);
			stmt.setString(7,nv.getSdt());
			stmt.setString(8,nv.getChucVu().getMaChucVu());
			
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
	public boolean delete(String mNV) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("delete from NhanVien where maNV = ?");
			stmt.setString(1, mNV);
			n=stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return n>0;
	}
	public NhanVien getNhanVienTheoMaNV(String ma) {
		NhanVien nv = null;
		
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from NhanVien where maNV = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return nv;
			} else {
				String maNV = rs.getString("maNV");
				String tenNV = rs.getString("tenNV");
				nv = new NhanVien(maNV, tenNV);
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
		return nv;
	}
	
	
	public NhanVien getNhanVienTheoTen(String ten) {
		NhanVien nv = null;
		
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			String sql = "Select * from NhanVien where tenNV = ? order by maNV";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return nv;
			} else {
				String maNhaCC = rs.getString("maNV");
				String tenNhaCC = rs.getString("tenNV");
				nv = new NhanVien(maNhaCC, tenNhaCC);
				return nv;
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
		return nv;
	}
	
}
