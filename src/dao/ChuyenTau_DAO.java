//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import connectDB.ConnectDB;
import entity.ChiTietVe;
import entity.Loai;
import entity.NhaGa;
import entity.ChuyenTau;

public class ChuyenTau_DAO {
	public ArrayList<ChuyenTau> getAllSanPham() {
		ArrayList<ChuyenTau> dssp = new ArrayList<ChuyenTau>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from SanPham order by maSP";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));

				ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				dssp.add(sanPham);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dssp;
	}
	public ArrayList<ChuyenTau> getAllSanPhamNV() {
		ArrayList<ChuyenTau> dssp = new ArrayList<ChuyenTau>();

		try {
			ConnectDB.getInstance().connect();
			Connection con = ConnectDB.getConnection();
			String sql = "select * from SanPham order by maSP";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
//				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));

				ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				dssp.add(sanPham);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dssp;
	}

	public ChuyenTau getSanPhamTheoMa(String ma) {
		ChuyenTau sanPham = null;
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from SanPham where maSP = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return sanPham;
			} else {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));
				sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
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
		return sanPham;
	}
	
	public ChuyenTau getSanPhamTheoTenFirst(String ten) {
		ChuyenTau sanPham = null;
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from SanPham where tenSP = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			if (rs.next() == false) {
				return sanPham;
			} else {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));
				sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				return sanPham;
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
		return sanPham;
	}
	
	public ArrayList<ChuyenTau> getSanPhamTheoTen(String ten) {
		ArrayList<ChuyenTau> ds = new ArrayList<ChuyenTau>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from SanPham where tenSP = ?";
			statement = con.prepareStatement(sql);
			statement.setString(1, ten);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));
				ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				ds.add(sanPham);
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
//	select* from [dbo].[SanPham] where [maLoai]='QJ'
	public ArrayList<ChuyenTau> getSanPhamTheoMaLoai(String ma) {
		ArrayList<ChuyenTau> ds = new ArrayList<ChuyenTau>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			int cnt = 0;
			String sql = "select maSP, tenSP, donViSP, giaNhap, giaBan,tinhTrang,tonKho, image,maLoai, maNhaCC\r\n"
					+ " from [dbo].[SanPham] where [maLoai]=?  and  tonKho > 0";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));
				ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				ds.add(sanPham);
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
	
	public ArrayList<ChuyenTau> getSanPhamTheoMaNCC(String ma) {
		ArrayList<ChuyenTau> ds = new ArrayList<ChuyenTau>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			int cnt = 0;
			String sql = "select maSP, tenSP, donViSP, giaNhap, giaBan,tinhTrang,tonKho, image,maLoai, maNhaCC\r\n"
					+ " from [dbo].[SanPham] where [maNhaCC]=?  and  tonKho > 0";
			statement = con.prepareStatement(sql);
			statement.setString(1, ma);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));
				ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				ds.add(sanPham);
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
	
	
	
	
	public ArrayList<ChuyenTau> getSanPhamTonKho() {
		ArrayList<ChuyenTau> ds = new ArrayList<ChuyenTau>();
		PreparedStatement statement = null;
		
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			
			String sql = "Select * from SanPham where tonKho > 0";
			statement = con.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				String maSP = rs.getString("maSP");
				String tenSP = rs.getString("tenSP");
				String donViSP = rs.getString("donViSP");
				double giaNhap = rs.getDouble("giaNhap");
				double giaBan = rs.getDouble("giaBan");
				String tinhTrang = rs.getString("tinhTrang");
				int tonKho = rs.getInt("tonKho");
				String image = rs.getString("image");
				NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoMa(rs.getString("maNhaCC"));
				Loai loai = new Loai_DAO().getLoaiTheoMa(rs.getString("maLoai"));
				ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);
				ds.add(sanPham);
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
//	select [tonKho] as SoLuong
//	from [dbo].[SanPham] where [maSP]='SP00001'
	public int demSLSanPham(String maSP) {
		int n=0;
		PreparedStatement statement = null;
		try {
			ConnectDB.getInstance().connect();
			Connection con = (Connection) ConnectDB.getConnection();
			int cnt = 0;
			String sql = "select [tonKho] as SoLuong\r\n"
					+ "from [dbo].[SanPham] where [maSP]=?";
			statement = con.prepareStatement(sql);
			statement.setString(1, maSP);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int tonKho = rs.getInt("tonKho");
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
		return n;
	}
	
	public boolean update(ChuyenTau sp) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update SanPham set tenSP=?, donViSP= ?, giaNhap= ?, giaBan=?, tinhTrang= ?,tonKho = ?, image = ?, maNhaCC = ?, maLoai = ?  where maSP= ?");
			
			stmt.setString(1, sp.getTenSP());
			stmt.setString(2, sp.getDonViSP());
			stmt.setDouble(3, sp.getGiaNhap());
			stmt.setDouble(4, sp.getGiaBan());
			stmt.setString(5, sp.getTinhTrang());
			stmt.setInt(6, sp.getTonKho());
			stmt.setString(7, sp.getImage());
			stmt.setString(8, sp.getNhaCC().getMaNhaCC());
			stmt.setString(9, sp.getLoai().getMaLoai());
			stmt.setString(10, sp.getMaSP());
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
	
	public boolean create(ChuyenTau sp) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("insert into"+" SanPham values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setString(1, sp.getMaSP());
			stmt.setString(2, sp.getTenSP());
			stmt.setString(3, sp.getDonViSP());
			stmt.setDouble(4, sp.getGiaNhap());
			stmt.setDouble(5, sp.getGiaBan());
			stmt.setString(6, sp.getTinhTrang());
			stmt.setInt(7, sp.getTonKho());
			stmt.setString(8, sp.getImage());
			stmt.setString(9, sp.getNhaCC().getMaNhaCC());
			stmt.setString(10, sp.getLoai().getMaLoai());
			
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
	
	public boolean delete(String maSP) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			String str = "delete from SanPham where maSP = ?";
			stmt=con.prepareStatement(str);
			stmt.setString(1, maSP);
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
	public boolean updateSL(ChuyenTau sp, ChiTietVe cthd) {
		ConnectDB.getInstance();
		Connection con=(Connection) ConnectDB.getConnection();
		PreparedStatement stmt=null;
		int n=0;
		try {
			stmt=con.prepareStatement("update SanPham set tonKho = tonKho-? where maSP= ?");
			
			stmt.setInt(1, cthd.getSoLuong()  );
			stmt.setString(2, sp.getMaSP());
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
