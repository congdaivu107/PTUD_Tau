//4.	Trần Thị Anh Thư - 19516531 (Note taker)
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChucVu_Dao;
import dao.NhanVien_DAO;
import dao.ChuyenTau_DAO;
import dao.User_Dao;
import entity.NhanVien;
import entity.ChuyenTau;
import entity.ChucVu;
import entity.User;

public class UI_NhanVien extends JFrame implements ActionListener, MouseListener {

	private static final String OK = null;
	JButton btnHoaDon, btnSanPham, btnKhachHang, btnNhanVien, btnBaoCao, btnHoTro, btnDangXuat, btnTimSanPham,
			btnThemNV, btnXoaNV, btnSuaNV, btnThemTK, btnXoaTK, btnSuaTK, btnXoaRong, btnXoaRong1, btnXoaRong2,
			btnThemCV, btnSuaCV, btnAnTT, btnDoiMa;
	JLabel lblMaNV, lblTenNV, lblDiachi, lblGT, lblCV, lblSDT, lblEmail, lblNgayVaoLam, lblTimKiem, lblTimKiemTheoMa,
			lblTimKiemTheoTen, lblMaChucVu, lblTenChucVu, lblID, lblNhanvien, lblPassword, lblChucnang;
	JRadioButton rdbTimKiemMa, rdbTimKiemTen,rdbNam,rdbNu;
	JTextField txtMaNV, txtTenNV, txtDiachi, txtGT, txtEmail, txtSDT, txtTimKiem, txtID, txtPassword, txtMaCV, txtTenCV,
			txtMess;
	JDateChooser cboNgayVaoLam;
	JComboBox<String> cboThangVaoLam, cboNamVaoLam, cbcChucVu, cbcNhanVien, cboChucNang;
	JTable tblnv, tbltk, tblcv, tblcvnv;
	JScrollPane scroll;
	DefaultTableModel tblModelNV, tblModelTK, tblModelCV, tblModelCVNV;
	JLabel lblLogName;

	UI_NhanVien frmBanHang;
	UI_Ve frmHoaDon;
	UI_ChuyenTau frmSanPham;
	UI_KhachHang frmKhachHang;
	UI_NhanVien frmNhanVien;
	UI_ThongKe frmThongKe;
	UI_DangNhap frmDangNhap;

	NhanVien_DAO dsnv = new NhanVien_DAO();
	ChucVu_Dao dsChucVu_Dao = new ChucVu_Dao();
	User_Dao dstk_Dao = new User_Dao();
	private static User userCurr;

	public UI_NhanVien(User userCurr) throws IOException, SQLException {
		// TODO Auto-generated constructor stub
		ConnectDB.getInstance().connect();
		setMinimumSize(new Dimension(1350, 700));
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Trang chính");
		this.userCurr = userCurr;
		giaoDienChinh1();
//		setMinimumSize(new Dimension(1350, 700));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);

	}

	private String getMaTiepTheo(DefaultTableModel model, String kieuMa) {
		String number0 = "";
		String maSPCurr = model.getValueAt(model.getRowCount() - 1, 0).toString();
		int maSPIntCurr = Integer.parseInt(maSPCurr.substring(maSPCurr.length() - 5, maSPCurr.length()));
		int maSPIntCurrLenght = String.valueOf(maSPIntCurr).length();
		if(maSPIntCurrLenght < String.valueOf(maSPIntCurr+1).length()) {
			maSPIntCurrLenght++;
		}
		for (int i = 0; i < 5 - (maSPIntCurrLenght); i++) {
			number0 += "0";
		}

		return kieuMa + number0 + (maSPIntCurr + 1);
	}

	private void giaoDienChinh1() throws IOException {

		// Menu Tác Vụ Bên Trái
		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Quản Lý Nhân Viên"));

		// Image
		BufferedImage imagebag = ImageIO.read(new File("image/bag.png"));
		ImageIcon iconbag = new ImageIcon(imagebag.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagebill = ImageIO.read(new File("image/bill.png"));
		ImageIcon iconbill = new ImageIcon(imagebill.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagecus = ImageIO.read(new File("image/customer.png"));
		ImageIcon iconcus = new ImageIcon(imagecus.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagestaff = ImageIO.read(new File("image/steward.png"));
		ImageIcon iconstaff = new ImageIcon(imagestaff.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagesAna = ImageIO.read(new File("image/analytics.png"));
		ImageIcon iconAna = new ImageIcon(imagesAna.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagesSer = ImageIO.read(new File("image/service.png"));
		ImageIcon iconSer = new ImageIcon(imagesSer.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagesOut = ImageIO.read(new File("image/logout.png"));
		ImageIcon iconOut = new ImageIcon(imagesOut.getScaledInstance(35, 35, Image.SCALE_SMOOTH));

		BufferedImage imagesUs = ImageIO.read(new File("image/taikhoan.png"));
		ImageIcon iconUS = new ImageIcon(imagesUs.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
		JButton btnTaiKhoan;
		JButton btnImUser;
		//Tài Khoản
		pWest.add(btnImUser = new JButton("",iconUS));
		pWest.add(btnTaiKhoan = new JButton());
		JTextField txtTaiKoan;
		btnTaiKhoan.add(txtTaiKoan= new JTextField());
		btnImUser.setPreferredSize(new Dimension(200, 50));
		btnTaiKhoan.setPreferredSize(new Dimension(200, 50));
		txtTaiKoan.setEditable(false);
		txtTaiKoan.setText(userCurr.getNhanVien().getTenNV());
		btnImUser.setBorderPainted (false);
		btnImUser.setContentAreaFilled (false);
		btnImUser.setFocusPainted (false);
		btnTaiKhoan.setBorderPainted (false);
		btnTaiKhoan.setContentAreaFilled (false);
		btnTaiKhoan.setFocusPainted (false);
		btnTaiKhoan.setBorder(null); 

		txtTaiKoan.setFont(new Font("Arial", Font.BOLD, 20));
		// Nút Hóa Đơn
		pWest.add(btnHoaDon = new JButton("Vé", iconbill));
		btnHoaDon.setPreferredSize(new Dimension(200, 50));
		btnHoaDon.setForeground(Color.BLACK);
		btnHoaDon.setBorder(BorderFactory.createRaisedBevelBorder());
		pWest.add(Box.createVerticalStrut(65));

		// Nút Sản Phầm
		pWest.add(btnSanPham = new JButton("Chuyến Tàu", iconbag));
		btnSanPham.setPreferredSize(new Dimension(200, 50));
		btnSanPham.setForeground(Color.black);
		btnSanPham.setBorder(BorderFactory.createRaisedBevelBorder());
		pWest.add(Box.createVerticalStrut(65));

		// Nút Khách Hàng
		pWest.add(btnKhachHang = new JButton("Khách Hàng", iconcus));
		btnKhachHang.setBorder(BorderFactory.createRaisedBevelBorder());
		btnKhachHang.setForeground(Color.black);
		btnKhachHang.setPreferredSize(new Dimension(200, 50));
		pWest.add(Box.createVerticalStrut(65));

		// Nút Nhân Viên
		btnNhanVien = new JButton("Nhân Viên", iconstaff);
		btnNhanVien.setBorder(BorderFactory.createRaisedBevelBorder());
		btnNhanVien.setForeground(Color.black);
		btnNhanVien.setPreferredSize(new Dimension(200, 50));

		// Nút Thống Kê
		btnBaoCao = new JButton("Thống Kê", iconAna);
		btnBaoCao.setBorder(BorderFactory.createRaisedBevelBorder());
		btnBaoCao.setForeground(Color.black);
		btnBaoCao.setPreferredSize(new Dimension(200, 50));
		//
		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
//			btnNhanVien.setEnabled(false);
//			btnBaoCao.setEnabled(false);
//			btnXoaHD.setEnabled(false);
//			btnSuaHD.setEnabled(false);
			pWest.add(btnNhanVien);
			pWest.add(Box.createVerticalStrut(65));
			// Nút Thống Kê
			pWest.add(btnBaoCao);
			pWest.add(Box.createVerticalStrut(65));
			
		}
		// Nút Hỗ Trợ
		pWest.add(btnHoTro = new JButton("Hỗ Trợ", iconSer));
		btnHoTro.setBorder(BorderFactory.createRaisedBevelBorder());
		btnHoTro.setForeground(Color.black);
		btnHoTro.setPreferredSize(new Dimension(200, 50));
		// Nút Đăng Xuất
		pWest.add(btnDangXuat = new JButton("Đăng Xuất", iconOut));
		btnDangXuat.setBorder(BorderFactory.createRaisedBevelBorder());
		btnDangXuat.setForeground(Color.black);
		btnDangXuat.setPreferredSize(new Dimension(200, 50));
		pWest.add(Box.createVerticalStrut(65));
		
		User listNV1 = dstk_Dao.getUserTheomaNVFirst(userCurr.getNhanVien().getMaNV().toString());
		
//		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
////			btnNhanVien.setEnabled(false);
////			btnBaoCao.setEnabled(false);
////			btnXoaHD.setEnabled(false);
////			btnSuaHD.setEnabled(false);
//			pWest.add(btnNhanVien);
//			// Nút Thống Kê
//			pWest.add(btnBaoCao);
//			
//		}
		

		add(pWest, BorderLayout.WEST);
		int heightCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 5 / 11;

		int widthCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 11;

		pWest.setPreferredSize(new Dimension(widthCenter, heightCenter));

		// Center
		JTabbedPane tabbedPane = new JTabbedPane();
		BufferedImage imagetag = ImageIO.read(new File("image/staff.png"));
		ImageIcon icontag = new ImageIcon(imagetag.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		BufferedImage imagetag1 = ImageIO.read(new File("image/staff.png"));
		ImageIcon icontag1 = new ImageIcon(imagetag1.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		BufferedImage imagetag2 = ImageIO.read(new File("image/staff.png"));
		ImageIcon icontag2 = new ImageIcon(imagetag2.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		tabbedPane.addTab("Nhân Viên", icontag, NhanVien(), "Danh Sách Nhân Viên");
		tabbedPane.addTab("Tài Khoản", icontag1, TaiKhoan(), "Danh Sách Tài Khoản");
		tabbedPane.addTab("Chức Vụ", icontag2, ChucVu(), "Danh Sách Chức Vụ");

		add(tabbedPane, BorderLayout.CENTER);

		btnBaoCao.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnHoTro.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnDangXuat.addActionListener(this);

	}

	private Box NhanVien() throws IOException {

		Box box = Box.createVerticalBox();
		Dimension dim = new Dimension(300, 700);
		// Bảng Nhân Viên
		Box box_table_nv;
		box.add(box_table_nv = Box.createVerticalBox());
		box_table_nv.add(Box.createVerticalStrut(10));
		String[] str = { "Mã nhân viên", "Tên nhân viên", "Địa chỉ", "Giới tính", "Ngày vào làm", "Chức vụ", "Email",
				"Số điện thoại" };
		tblModelNV = new DefaultTableModel(str, 0);
		tblnv = new JTable(tblModelNV);
		JScrollPane jsc = new JScrollPane(tblnv);
		tblnv.setAutoCreateRowSorter(true);
		jsc.getVerticalScrollBar().setUnitIncrement(16);
		box.add(jsc);

		// Xuất Thông tin vô bảng
		ArrayList<NhanVien> listNV1 = dsnv.gettalltbNhanVien();
		for (NhanVien nv1 : listNV1) {
			tblModelNV.addRow(new Object[] { nv1.getMaNV(), nv1.getTenNV(), nv1.getDiaChi(), nv1.getGioiTinh(),
					nv1.getNgayVaoLam(), nv1.getChucVu().getTenChucVu(), nv1.getEmail(), nv1.getSdt() });
		}

		//
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(700, 300));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		pnlNorth.setLayout(null); // Absolute layout

		pnlNorth.add(lblMaNV = new JLabel("Mã nhân viên: "));
		pnlNorth.add(lblTenNV = new JLabel("Tên nhân viên: "));
		pnlNorth.add(lblDiachi = new JLabel("Địa chỉ : "));
		pnlNorth.add(lblGT = new JLabel("Giới tính : "));
		pnlNorth.add(lblCV = new JLabel("Chức vụ : "));
		pnlNorth.add(lblEmail = new JLabel("Email: "));
		pnlNorth.add(lblSDT = new JLabel("Số điện thoại : "));
		pnlNorth.add(lblNgayVaoLam = new JLabel("Ngày vào làm : "));

		pnlNorth.add(txtMaNV = new JTextField());
		txtMaNV.setEditable(false);
		pnlNorth.add(txtTenNV = new JTextField());
		pnlNorth.add(txtDiachi = new JTextField());
//		pnlNorth.add(txtGT = new JTextField());
		pnlNorth.add(rdbNam = new JRadioButton("Nam"));
		rdbNam.setSelected(true);
		pnlNorth.add(rdbNu = new JRadioButton("Nữ"));
		ButtonGroup groupGT = new ButtonGroup();
		groupGT.add(rdbNam);
		groupGT.add(rdbNu);
		pnlNorth.add(cbcChucVu = new JComboBox<String>());
		cbcChucVu.setEditable(true);
		pnlNorth.add(txtEmail = new JTextField());
		pnlNorth.add(txtSDT = new JTextField());
		pnlNorth.add(cboNgayVaoLam = new JDateChooser());
		cboNgayVaoLam.setDateFormatString("yyyy-MM-dd");
		cboNgayVaoLam.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		cboNgayVaoLam.getDateEditor().getUiComponent().setFocusable(false);
		cbcChucVu.setEditable(true);

		pnlNorth.add(btnThemNV = new JButton("Thêm"));
		pnlNorth.add(btnXoaNV = new JButton("Xóa"));
		pnlNorth.add(btnSuaNV = new JButton("Sửa"));
		pnlNorth.add(btnXoaRong = new JButton("Làm mới"));

		pnlNorth.add(rdbTimKiemMa = new JRadioButton("Mã nhân viên"));
		pnlNorth.add(rdbTimKiemTen = new JRadioButton("Tên nhân viên"));
		ButtonGroup group = new ButtonGroup();
		group.add(rdbTimKiemMa);
		group.add(rdbTimKiemTen);

		box.add(pnlNorth);
		pnlNorth.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 300, h = 20;
		lblMaNV.setBounds(20, 20, w1, h);
		txtMaNV.setBounds(120, 20, w2, h);
		lblDiachi.setBounds(450, 20, w1, h);
		txtDiachi.setBounds(570, 20, w2, h);

		lblTenNV.setBounds(20, 60, w1, h);
		txtTenNV.setBounds(120, 60, w2, h);
		lblCV.setBounds(450, 60, w1, h);
		cbcChucVu.setBounds(570, 60, w2, h);

		lblGT.setBounds(20, 100, w1, h);
		rdbNam.setBounds(120, 100, w1, h);
		rdbNu.setBounds(250, 100, w1, h);
		lblSDT.setBounds(450, 100, w1, h);
		txtSDT.setBounds(570, 100, w2, h);

		lblEmail.setBounds(20, 140, w1, h);
		txtMess.setBounds(20, 120, 300, h);
		txtEmail.setBounds(120, 140, w2, h);
		lblNgayVaoLam.setBounds(450, 140, w1, h);
		cboNgayVaoLam.setBounds(570, 140, w2, h);
		
		
		btnThemNV.setBounds(1000, 20, w1, h+20);
		btnXoaNV.setBounds(1000, 75, w1, h+20);
		btnSuaNV.setBounds(1000, 130, w1, h+20);
		btnXoaRong.setBounds(1000, 185, w1, h+20);
		
		rdbTimKiemMa.setBounds(800, 260, w2 / 2, h);
		rdbTimKiemTen.setBounds(950, 260, w2 / 2, h);

		// Combo chức vụ
		ArrayList<entity.ChucVu> listcv = dsChucVu_Dao.gettalltbChucVu();
		for (entity.ChucVu p : listcv) {
			cbcChucVu.addItem(p.getTenChucVu());
		}
		AutoCompleteDecorator.decorate(cbcChucVu);
		//
		Box btn;
		box.add(Box.createVerticalStrut(10));
		box.add(btn = Box.createHorizontalBox());
		btn.add(Box.createHorizontalStrut(800));
		btn.add(lblTimKiem = new JLabel("Tìm Kiếm :    "));
		btn.add(txtTimKiem = new JTextField());
		txtTimKiem.setToolTipText("Search here!");
		txtTimKiem.setFont(new java.awt.Font("Arial", Font.ITALIC | Font.BOLD, 12));
		txtTimKiem.setForeground(Color.BLACK);
		btn.add(Box.createHorizontalStrut(10));
		box.add(Box.createVerticalStrut(10));

		// Sự kiện nút
		btnXoaRong.addActionListener(this);
		tblnv.addMouseListener(this);
		btnThemNV.addActionListener(this);
		btnXoaNV.addActionListener(this);
		btnSuaNV.addActionListener(this);

		txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					cbbTimKiemTenNhanVien(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					xetRangBuocSDT(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

//		txtMaNV.addKeyListener(new java.awt.event.KeyAdapter() {
//			public void keyReleased(java.awt.event.KeyEvent evt) {
//				try {
//					xetRangBuocMa(evt);
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//		
		txtTenNV.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					xetRangBuocTen(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					xetRangBuocEmail(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		txtDiachi.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					xetRangBuocDiaChi(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		// Xuất bảng

		txtMaNV.setText(getMaTiepTheo(tblModelNV, "NV"));
		return box;

	}

	private Box TaiKhoan() {

		Box box = Box.createVerticalBox();
		Dimension dim = new Dimension(150, 20);

		Box box_table_taikhoan;
		box.add(box_table_taikhoan = Box.createVerticalBox());
		box_table_taikhoan.add(Box.createVerticalStrut(10));
		String[] headtblTK = "ID;Password;Chức năng;Nhân viên".split(";");
		tblModelTK = new DefaultTableModel(headtblTK, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		JScrollPane srolltbltk = new JScrollPane(tbltk = new JTable(tblModelTK),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		srolltbltk.getVerticalScrollBar().setUnitIncrement(16);
		tbltk.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		box_table_taikhoan.add(srolltbltk);
		box_table_taikhoan.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Danh sách tài khoản"));
		box.add(Box.createVerticalStrut(10));
		tbltk.getColumnModel().getColumn(0).setPreferredWidth(30);
		tbltk.getColumnModel().getColumn(1).setPreferredWidth(80);
		tbltk.getColumnModel().getColumn(2).setPreferredWidth(30);
		tbltk.getColumnModel().getColumn(3).setPreferredWidth(40);
		tbltk.setAutoCreateRowSorter(true);
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.SOUTH);
		pnlNorth.setPreferredSize(new Dimension(0, 150));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		pnlNorth.setLayout(null);

		pnlNorth.add(lblID = new JLabel("ID: "));
		pnlNorth.add(lblNhanvien = new JLabel("Nhân viên: "));
		pnlNorth.add(lblPassword = new JLabel("Password : "));
		pnlNorth.add(lblChucnang = new JLabel("Chức năng : "));

		pnlNorth.add(txtID = new JTextField());
		txtID.setEditable(false);
		pnlNorth.add(cbcNhanVien = new JComboBox<String>());
		cbcNhanVien.setEnabled(true);
		cbcNhanVien.setEditable(true);
		pnlNorth.add(txtPassword = new JPasswordField(10));
		txtPassword.setActionCommand(OK);

		pnlNorth.add(cboChucNang = new JComboBox<String>());
		cbcChucVu.setEnabled(true);

//		
		pnlNorth.add(btnThemTK = new JButton("Thêm"));
		pnlNorth.add(btnXoaTK = new JButton("Xóa"));
		pnlNorth.add(btnSuaTK = new JButton("Sửa"));
		pnlNorth.add(btnXoaRong1 = new JButton("Làm mới"));

//		Combo chức vụ
		ArrayList<entity.ChucVu> listcv = dsChucVu_Dao.gettalltbChucVu();
		for (entity.ChucVu p : listcv) {
			cboChucNang.addItem(p.getTenChucVu());
		}
		AutoCompleteDecorator.decorate(cboChucNang);

		// Combo nhân viên
		ArrayList<NhanVien> listnv = dsnv.gettalltbNhanVien();
		for (NhanVien p : listnv) {
			cbcNhanVien.addItem(p.getTenNV());
		}
		AutoCompleteDecorator.decorate(cbcNhanVien);
		box.add(pnlNorth);
//		pnlNorth.add(txtMess = new JTextField());
//		txtMess.setEditable(false);
//		txtMess.setBorder(null);
//		txtMess.setForeground(Color.red);
//		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 200, h = 20;
		lblID.setBounds(20, 20, w1, h);
		txtID.setBounds(120, 20, w2, h);
		lblNhanvien.setBounds(350, 20, w1, h);
		cbcNhanVien.setBounds(470, 20, w2, h);

		lblPassword.setBounds(20, 45, w1, h);
		txtPassword.setBounds(120, 45, w2, h);
		lblChucnang.setBounds(350, 45, w1, h);
		cboChucNang.setBounds(470, 45, w2, h);

		btnThemTK.setBounds(790, 20, w1, h);
		btnSuaTK.setBounds(790, 45, w1, h);
		btnXoaTK.setBounds(900, 20, w1, h);
		btnXoaRong1.setBounds(900, 45, w1, h);

		// Xuất thông tin ra bảng Tài khoản
		ArrayList<User> dstk = dstk_Dao.gettalltbTaiKhoan();
		for (User u : dstk) {
			tblModelTK.addRow(new Object[] { u.getId(),md5(u.getPassword()), u.getChucNang().getTenChucVu(),
					u.getNhanVien().getTenNV() });
		}

		// Sự kiện nút
		btnXoaRong1.addActionListener(this);
		btnThemTK.addActionListener(this);
		btnXoaTK.addActionListener(this);
		btnSuaTK.addActionListener(this);

		tbltk.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					tblTaikhoan(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		txtID.setText(getMaTiepTheo(tblModelTK, "ID"));
		return box;
	}

	private Box ChucVu() {
		Box box = Box.createVerticalBox();
		Dimension dim = new Dimension(150, 20);

		// Bảng chức vụ
		Box box_table_chucvu;
		box.add(box_table_chucvu = Box.createVerticalBox());
		box_table_chucvu.add(Box.createVerticalStrut(10));
		String[] headtblChucVu = "Mã chức vụ;Tên chức vụ".split(";");
		tblModelCV = new DefaultTableModel(headtblChucVu, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		JScrollPane srolltblcv = new JScrollPane(tblcv = new JTable(tblModelCV),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		srolltblcv.getVerticalScrollBar().setUnitIncrement(16);
		tblcv.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		box_table_chucvu.add(srolltblcv);
		box_table_chucvu.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Danh sách các chức vụ"));
		box.add(Box.createVerticalStrut(10));
		tblcv.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblcv.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblcv.getTableHeader().setBackground(new ColorUIResource(98, 167, 207));
		tblcv.setAutoCreateRowSorter(true);
		// Tác vụ của chức vụ
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.SOUTH);
		pnlNorth.setPreferredSize(new Dimension(0, 150));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Tác vụ"));
		pnlNorth.setLayout(null);

		pnlNorth.add(lblMaChucVu = new JLabel("Mã chức vụ: "));
		pnlNorth.add(lblTenChucVu = new JLabel("Tên chức vụ: "));

		pnlNorth.add(txtMaCV = new JTextField());
		txtMaCV.setEditable(false);
		pnlNorth.add(txtTenCV = new JTextField());

		pnlNorth.add(btnThemCV = new JButton("Thêm"));
		pnlNorth.add(btnSuaCV = new JButton("Sửa"));
		pnlNorth.add(btnAnTT = new JButton("Xóa"));
		pnlNorth.add(btnXoaRong2 = new JButton("Làm mới"));

		box.add(pnlNorth);

//		pnlNorth.add(txtMess = new JTextField());
//		txtMess.setEditable(false);
//		txtMess.setBorder(null);
//		txtMess.setForeground(Color.red);
//		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 200, h = 20;
		lblMaChucVu.setBounds(20, 20, w1, h);
		txtMaCV.setBounds(120, 20, w2, h);
		lblTenChucVu.setBounds(350, 20, w1, h);
		txtTenCV.setBounds(470, 20, w2, h);

		btnThemCV.setBounds(790, 20, w1, h);
		btnAnTT.setBounds(790, 45, w1, h);
		btnSuaCV.setBounds(900, 20, w1, h);
		btnXoaRong2.setBounds(900, 45, w1, h);

		ArrayList<entity.ChucVu> listLoai = dsChucVu_Dao.gettalltbChucVu();
		for (entity.ChucVu p : listLoai) {
			tblModelCV.addRow(new Object[] { p.getMaChucVu(), p.getTenChucVu() });
		}

		// Bảng nhân viên
		Box box_table_nhanvien;
		box.add(box_table_nhanvien = Box.createVerticalBox());
		box_table_nhanvien.add(Box.createVerticalStrut(10));
		String[] headtblChucVuNV = "Mã nhân viên;Tên nhân viên;Chức vụ;".split(";");
		tblModelCVNV = new DefaultTableModel(headtblChucVuNV, 0) {
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};

		JScrollPane srolltblcvnv = new JScrollPane(tblcvnv = new JTable(tblModelCVNV),
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tblcvnv.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		srolltblcvnv.getVerticalScrollBar().setUnitIncrement(16);
		box_table_nhanvien.add(srolltblcvnv);
		box_table_nhanvien.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), "Danh sách nhân viên"));
		box.add(Box.createVerticalStrut(10));
		tblcvnv.getColumnModel().getColumn(0).setPreferredWidth(30);
		tblcvnv.getColumnModel().getColumn(1).setPreferredWidth(80);
		tblcvnv.getColumnModel().getColumn(2).setPreferredWidth(30);
		tblcvnv.getTableHeader().setEnabled(false);
		tblcvnv.setAutoCreateRowSorter(true);
		//
		Box btn;
		box.add(Box.createVerticalStrut(10));
		box.add(btn = Box.createHorizontalBox());
		btn.add(Box.createHorizontalStrut(800));
//		btn.add(btnDoiMa = new JButton("Đổi mã chức vụ"));
//		btn.add(cbcDoiMa = new JComboBox<String>());
//		cbcDoiMa.setEditable(true);
		btn.add(Box.createHorizontalStrut(10));
		box.add(Box.createVerticalStrut(10));

		// Combo đổi mã
//		ArrayList<ChucVu> listdoima = dsChucVu_Dao.gettalltbChucVu();
//		for (ChucVu p : listdoima) {
//			cbcDoiMa.addItem(p.getMaChucVu());
//		}

		// Sự kiện nút
		btnXoaRong2.addActionListener(this);
		btnThemCV.addActionListener(this);
		btnSuaCV.addActionListener(this);
		btnAnTT.addActionListener(this);
		tblcv.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				try {
					tblHoaDon_HoaDonMouseClicked(evt);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		txtMaCV.setText(getMaTiepTheo(tblModelCV, "CV"));
		txtTenCV.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyReleased(java.awt.event.KeyEvent evt) {
				try {
					xetRangBuocTenCV(evt);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		return box;
	}

	public static void main(String[] args) throws IOException, SQLException {
		new UI_NhanVien(userCurr);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tblnv.getSelectedRow();
		txtMaNV.setText(tblnv.getValueAt(row, 0).toString());
		txtTenNV.setText(tblnv.getValueAt(row, 1).toString());
		txtDiachi.setText(tblnv.getValueAt(row, 2).toString());
		 String gioitinh = tblnv.getValueAt(row, 3).toString();
	        if (gioitinh.equals("Nam")) {
	            rdbNam.setSelected(true);
	            rdbNu.setSelected(false);
	        } else {
	        	rdbNu.setSelected(true);
	        	rdbNam.setSelected(false);
	        }
		try {
			java.util.Date date = new SimpleDateFormat("yyyy-MM-dd")
					.parse((String) tblnv.getValueAt(row, 4).toString());
			cboNgayVaoLam.setDate(date);
		} catch (Exception e1) {
		}

		cbcChucVu.setSelectedItem(tblnv.getValueAt(row, 5).toString());
		txtEmail.setText(tblnv.getValueAt(row, 6).toString());
		txtSDT.setText(tblnv.getValueAt(row, 7).toString());

	}

	// MouseClick Bảng chuc vu
	private void tblHoaDon_HoaDonMouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
		int viTriDongVuaBam = tblcv.getSelectedRow();
		txtMaCV.setText(tblcv.getValueAt(viTriDongVuaBam, 0).toString());
		txtTenCV.setText(tblcv.getValueAt(viTriDongVuaBam, 1).toString());
		LayDuLieuChucVuofNhanVien(txtMaCV.getText());
	}

	private void tblTaikhoan(java.awt.event.MouseEvent evt) throws SQLException {
		int viTriDongVuaBam = tbltk.getSelectedRow();
		txtID.setText(tbltk.getValueAt(viTriDongVuaBam, 0).toString());
		txtPassword.setText(tbltk.getValueAt(viTriDongVuaBam, 1).toString());
		cboChucNang.setSelectedItem(tbltk.getValueAt(viTriDongVuaBam, 2).toString());
		cbcNhanVien.setSelectedItem(tbltk.getValueAt(viTriDongVuaBam, 3).toString());
	}

	// Lấy dữ liệu từ bảng chức vụ xuống nhân viên
	public void LayDuLieuChucVuofNhanVien(String MaChucVu) throws SQLException {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		Statement statement = con.createStatement();
		String cautruyvan = "";
		cautruyvan = "select maNV,tenNV,ChucVu.maChucVu" + " from NhanVien,ChucVu where"
				+ " NhanVien.maChucVu=ChucVu.maChucVu and ChucVu.maChucVu='" + MaChucVu + "'";

		ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
		Object[] obj1 = new Object[] { "Mã Nhân viên", "Tên nhân viên", "Chức vụ" };
		DefaultTableModel tableModel1 = new DefaultTableModel(obj1, 0);
		tblcvnv.setAutoCreateRowSorter(true);
		tblcvnv.setModel(tableModel1);
		try {
			while (rs.next()) {
				Object[] item = new Object[3];
				item[0] = rs.getString("maNV");
				item[1] = rs.getString("tenNV");
				item[2] = rs.getString("maChucVu");
				tableModel1.addRow(item);
			}
		} catch (SQLException ex) {

		}
	}

	private void cbbTimKiemTenNhanVien(java.awt.event.KeyEvent evt) throws SQLException {
		txtTimKiem.getText().trim();
		TimKiemNhanVien();

	}

	private void xetRangBuocSDT(java.awt.event.KeyEvent evt) throws SQLException {
		txtSDT.getText().trim();
		validData();

	}
	
	private void xetRangBuocTenCV(java.awt.event.KeyEvent evt) throws SQLException {
		txtTenCV.getText().trim();
		kiemtraTenCV();

	}

	private void xetRangBuocTen(java.awt.event.KeyEvent evt) throws SQLException {
		txtTenNV.getText().trim();
		kiemtraTenNV();

	}

	private void xetRangBuocEmail(java.awt.event.KeyEvent evt) throws SQLException {
		txtEmail.getText().trim();
		kiemtraEmail();

	}

	private void xetRangBuocDiaChi(java.awt.event.KeyEvent evt) throws SQLException {
		txtDiachi.getText().trim();
		kiemtraDiaChi();

	}


	private void TimKiemNhanVien() throws SQLException {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		Statement statement = con.createStatement();
		if (!(rdbTimKiemMa.isSelected() || rdbTimKiemTen.isSelected())) {
			JOptionPane.showMessageDialog(this, "Phải chọn kiểu tìm kiếm!");
		} else {

			String wheretk = "";
			if (rdbTimKiemMa.isSelected()) {
				wheretk = "maNV like N'%" + txtTimKiem.getText() + "%'";
			} else if (rdbTimKiemTen.isSelected()) {
				wheretk = "tenNV like N'%" + txtTimKiem.getText() + "%'";
			}

			String cautruyvan = "";
			cautruyvan = "select maNV,tenNV,diaChi,gioiTinh,ngayVaoLam,maChucVu,email,sdt " + " from NhanVien where "
					+ wheretk;
			ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
			Object[] obj = new Object[] { "Mã Nhân viên", "Tên nhân viên", "Địa chỉ", "Giới tính", "Ngày vào làm",
					"Mã chất vụ", "Email", "Số điện thoại" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblnv.setModel(tableModel);
			tblnv.setAutoCreateRowSorter(true);
			try {
				while (rs.next()) {
					Object[] item = new Object[8];
					item[0] = rs.getString("maNV");
					item[1] = rs.getString("tenNV");
					item[2] = rs.getString("diaChi");
					item[3] = rs.getString("gioiTinh");
					item[4] = rs.getString("ngayVaoLam");
					item[5] = rs.getString("maChucVu");
					item[6] = rs.getString("email");
					item[7] = rs.getString("sdt");
					tableModel.addRow(item);

				}
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}

		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static String md5(String msg) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msg.getBytes());
			byte byteData[] = md.digest();
			// convert the byte to hex format method 1
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (Exception ex) {
			return "";
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sou = e.getSource();
		if (sou.equals(btnHoaDon)) {
			try {
				frmHoaDon = new UI_Ve(userCurr);
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnSanPham)) {
			try {
				frmSanPham = new UI_ChuyenTau(userCurr);
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnKhachHang)) {
			try {
				frmKhachHang = new UI_KhachHang(userCurr);
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnNhanVien)) {
			try {
				frmNhanVien = new UI_NhanVien(userCurr);
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnBaoCao)) {
			try {
				frmThongKe = new UI_ThongKe(userCurr);
				this.dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnHoTro)) {
//			JOptionPane.showMessageDialog(this, "Mọi thắc mắc xin liên hệ 0932904529. Xin chân thành cảm ơn");
			String url = "https://www.google.com.vn/?hl=vi";

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				Runtime runtime = Runtime.getRuntime();
				try {
					runtime.exec("xdg-open " + url);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (sou.equals(btnDangXuat)) {
			frmDangNhap = new UI_DangNhap();
			this.dispose();
		}

		// Sự kiện nút trong box
		if (sou.equals(btnXoaRong)) {
			txtMaNV.setText(getMaTiepTheo(tblModelNV, "NV"));
			txtTenNV.setText("");
			txtEmail.setText("");
			txtDiachi.setText("");
			rdbNam.setSelected(true);
			cbcChucVu.setSelectedIndex(-1);
			cboNgayVaoLam.setDate(null);
			txtSDT.setText("");
			txtMaNV.requestFocus();
		} else if (sou.equals(btnXoaRong1)) {
			txtID.setText(getMaTiepTheo(tblModelTK, "ID"));
			cbcNhanVien.setSelectedIndex(-1);
			txtPassword.setText("");
			cboChucNang.setSelectedIndex(-1);
			txtID.requestFocus();
		} else if (sou.equals(btnXoaRong2)) {
			txtMaCV.setText(getMaTiepTheo(tblModelCV, "CV"));
			txtTenCV.setText("");
			txtMaCV.requestFocus();
		} else if (sou.equals(btnThemCV)) {
			if (txtMaCV.getText().equals("") || txtTenCV.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
			} else {
				ChucVu cv = new ChucVu(txtMaCV.getText(), txtTenCV.getText());

				try {
					dsChucVu_Dao.create(cv);
					tblModelCV.addRow(new Object[] { cv.getMaChucVu(), cv.getTenChucVu() });
					txtMaCV.setText(getMaTiepTheo(tblModelCV, "CV"));
					txtTenCV.setText("");
					txtTenCV.requestFocus();
//				updateComboboxData();

				} catch (Exception e2) {
					JOptionPane.showConfirmDialog(this, "Trùng");
				}

			}
		} else if (sou.equals(btnSuaCV)) {
			
			int row = tblcv.getSelectedRow();
			if (row >= 0) {
				if (txtMaCV.getText().equals("") || txtTenCV.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
				} else {
				ChucVu cv = new ChucVu(txtMaCV.getText(), txtTenCV.getText());
				if (dsChucVu_Dao.update(cv)) {
					tblcv.setValueAt(txtTenCV.getText(), row, 1);
					JOptionPane.showConfirmDialog(this, "Thông tin đã được cập nhật");
					txtMaCV.setText(getMaTiepTheo(tblModelCV, "CV"));
					txtTenCV.setText("");
					txtTenCV.requestFocus();
				}}
			} else {
				JOptionPane.showConfirmDialog(this, "Phải chọn dòng cần cập nhật");
			}
		} else if (sou.equals(btnThemTK)) {
			if (txtID.getText().equals("") || txtPassword.getText().equals(""))
				JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
			else {
				ChucVu cv = new ChucVu_Dao().getChucVuTheoTen(String.valueOf(cbcChucVu.getSelectedItem()));
				NhanVien nVien = new NhanVien_DAO().getNhanVienTheoTen(String.valueOf(cbcNhanVien.getSelectedItem()));
				User us = new User(txtID.getText(), txtPassword.getText(), cv, nVien);

				try {
					dstk_Dao.create(us);
					tblModelTK.addRow(
							new Object[] { us.getId(), md5(us.getPassword()), cv.getTenChucVu(), nVien.getTenNV() });
					txtID.setText(getMaTiepTheo(tblModelTK, "ID"));
					cbcNhanVien.setSelectedIndex(-1);
					txtPassword.setText("");
					cboChucNang.setSelectedIndex(-1);
					txtPassword.requestFocus();
				} catch (Exception e2) {

					JOptionPane.showConfirmDialog(this, "Trùng");
				}

			}
		} else if (sou.equals(btnXoaTK)) {
			int row = tbltk.getSelectedRow();
			if (row >= 0) {
				String id = (String) tbltk.getValueAt(row, 0);
				if (dstk_Dao.delete(id)) {
					if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này không?", "Cảnh báo",
							JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
						tblModelTK.removeRow(row);
					txtID.setText(getMaTiepTheo(tblModelTK, "ID"));
					cbcNhanVien.setSelectedIndex(-1);
					txtPassword.setText("");
					cboChucNang.setSelectedIndex(-1);
					txtPassword.requestFocus();
				}
			} else
				JOptionPane.showConfirmDialog(this, "Phải chọn dòng cần xóa");
		} else if (sou.equals(btnSuaTK)) {
			int row = tbltk.getSelectedRow();
			if (row >= 0) {
				if (txtID.getText().equals("") || txtPassword.getText().equals(""))
					JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
				else {
				NhanVien nv = new NhanVien_DAO().getNhanVienTheoTen(String.valueOf(cbcNhanVien.getSelectedItem()));
				ChucVu cv = new ChucVu_Dao().getChucVuTheoTen(String.valueOf(cboChucNang.getSelectedItem()));
				User user = new User(txtID.getText(), md5(txtPassword.getText()), cv, nv);
				if (dstk_Dao.update(user)) {
					tbltk.setValueAt(md5(txtPassword.getText()), row, 1);
					tbltk.setValueAt(cboChucNang.getSelectedItem(), row, 2);
					tbltk.setValueAt(cbcNhanVien.getSelectedItem(), row, 3);
					JOptionPane.showConfirmDialog(this, "Thông tin đã được cập nhật");
					txtID.setText(getMaTiepTheo(tblModelTK, "ID"));
					cbcNhanVien.setSelectedIndex(-1);
					txtPassword.setText("");
					cboChucNang.setSelectedIndex(-1);
					txtPassword.requestFocus();
				}}
			} else {
				JOptionPane.showConfirmDialog(this, "Phải chọn dòng cần cập nhật");
			}
		} else if (sou.equals(btnThemNV)) {
			if (txtMaNV.getText().equals("") || txtTenNV.getText().equals("") || txtDiachi.getText().equals("")
					|| txtEmail.getText().equals("") || txtSDT.getText().equals("")  
					|| cboNgayVaoLam.getDateFormatString().equals(""))
				JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
			else {
				String GioiTinh;
				 if (rdbNam.isSelected()) {
			            GioiTinh = "Nam";
			        } else {
			            GioiTinh = "Nữ";
			        }
				ChucVu cv = new ChucVu_Dao().getChucVuTheoTen(String.valueOf(cbcChucVu.getSelectedItem()));
				NhanVien nv = new NhanVien(txtMaNV.getText(), txtTenNV.getText(), txtDiachi.getText(), GioiTinh,
						cboNgayVaoLam.getDate(), cv, txtEmail.getText(), txtSDT.getText());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String date = simpleDateFormat.format(nv.getNgayVaoLam());
				if (validData()) {
					try {
						dsnv.create(nv);
						tblModelNV.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getDiaChi(), GioiTinh,
								date, cv.getTenChucVu(), nv.getEmail(), nv.getSdt() });
						txtMaNV.setText(getMaTiepTheo(tblModelNV, "NV"));
						txtTenNV.setText("");
						txtEmail.setText("");
						txtDiachi.setText("");
						rdbNam.setSelected(true);
						cbcChucVu.setSelectedIndex(-1);
						cboNgayVaoLam.setDate(null);
						txtSDT.setText("");
						txtTenNV.requestFocus();
					} catch (Exception e1) {
						ThongBao("Lỗi rồi", "Mã không trùng", 2);
					}
				}
			}
		} else if (sou.equals(btnXoaNV)) {
			try {
				xoaNVDuaVaoMa();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (sou.equals(btnSuaNV)) {
			int row = tblnv.getSelectedRow();
			if (row >= 0) {
				if (txtMaNV.getText().equals("") || txtTenNV.getText().equals("") || txtDiachi.getText().equals("")
						|| txtEmail.getText().equals("") || txtSDT.getText().equals("")  
						|| cboNgayVaoLam.getDateFormatString().equals(""))
					JOptionPane.showMessageDialog(this, "Phải nhập dữ liệu trước.");
				else {
				String GioiTinh;
				 if (rdbNam.isSelected()) {
			            GioiTinh = "Nam";
			        } else {
			            GioiTinh = "Nữ";
			        }
				ChucVu cv = new ChucVu_Dao().getChucVuTheoTen(String.valueOf(cbcChucVu.getSelectedItem()));
				NhanVien nv = new NhanVien(txtMaNV.getText(), txtTenNV.getText(), txtDiachi.getText(), GioiTinh,
						cboNgayVaoLam.getDate(), cv, txtEmail.getText(), txtSDT.getText());
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				if (dsnv.update(nv)) {
					tblnv.setValueAt(txtTenNV.getText(), row, 1);
					tblnv.setValueAt(txtDiachi.getText(), row, 2);
					tblnv.setValueAt(rdbNam.isSelected() ? "Nam" :"Nữ", row, 3);
					tblnv.setValueAt(simpleDateFormat.format(cboNgayVaoLam.getDate()).toString(), row, 4);
					tblnv.setValueAt(cbcChucVu.getSelectedItem(), row, 5);
					tblnv.setValueAt(txtEmail.getText(), row, 6);
					tblnv.setValueAt(txtSDT.getText(), row, 7);
					JOptionPane.showConfirmDialog(this, "Thông tin đã được cập nhật");
					txtMaNV.setText(getMaTiepTheo(tblModelNV, "NV"));
					txtTenNV.setText("");
					txtEmail.setText("");
					txtDiachi.setText("");
					rdbNam.setSelected(true);
					cbcChucVu.setSelectedIndex(-1);
					cboNgayVaoLam.setDate(null);
					txtSDT.setText("");
					txtTenNV.requestFocus();
				}}
			} else {
				JOptionPane.showConfirmDialog(this, "Phải chọn dòng cần cập nhật");
			}
		} else if (sou.equals(btnAnTT)) {
			ThongBao("Lưu ý khi xóa có thể xóa nhân viên có trong Phiếu nhập!", "Báo Lỗi", 2);
		}

	}

	public void ThongBao(String noiDungThongBao, String tieuDeThongBao, int icon) {
		JOptionPane.showMessageDialog(new JFrame(), noiDungThongBao, tieuDeThongBao, icon);
	}

	private void xoaNVDuaVaoMa() throws SQLException {
		ConnectDB.getInstance();
		Connection con = (Connection) ConnectDB.getConnection();
		Statement statement = con.createStatement();
		String MaNhanVien = txtMaNV.getText();
		if (!MaNhanVien.equals("")) {
			String ctvKiemThu = "select count(maHD) as SoHoaDon"
					+ " from NhanVien,HoaDon where NhanVien.maNV=HoaDon.maNV and NhanVien.maNV='" + MaNhanVien + "'";
			ResultSet rs1 = ((java.sql.Statement) statement).executeQuery(ctvKiemThu);
			String ctvKiemThu2 = "select count(maPhieuNhap) as SoPhieuNhap"
					+ " from NhanVien,PhieuNhap where NhanVien.maNV=PhieuNhap.maNV and NhanVien.maNV='" + MaNhanVien
					+ "'";
			ResultSet rs2 = ((java.sql.Statement) statement).executeQuery(ctvKiemThu2);
			int so1 = 0, so2 = 0;

			try {
				if (rs1.next()) {
					so1 = rs1.getInt("SoHoaDon");
				}
			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}
			try {

				if (rs2.next()) {
					so2 = rs2.getInt("SoPhieuNhap");
					if (rs2.getInt("SoPhieuNhap") == 0 && so1 == 0) {

						int row = tblnv.getSelectedRow();
						if (row >= 0) {
							String id = (String) tblnv.getValueAt(row, 0);
							if (dsnv.delete(id)) {
								if (JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa dòng này không?", "Cảnh báo",
										JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
									tblModelNV.removeRow(row);
								txtMaNV.setText(getMaTiepTheo(tblModelNV, "NV"));
								txtTenNV.setText("");
								txtEmail.setText("");
								txtDiachi.setText("");
								rdbNam.setSelected(true);
								cbcChucVu.setSelectedIndex(0);
								txtSDT.setText("");
								cboNgayVaoLam.setDate(null);
								txtTenNV.requestFocus();
							}
						} else {
							JOptionPane.showConfirmDialog(this, "Phải chọn dòng cần xóa");
						}

					} else {
						ThongBao("Không thể xóa bởi có trong " + so1 + " vé \n và có trong " + so2 + " phiếu Nhập",
								"Báo Lỗi", 2);
					}
				}

			} catch (SQLException ex) {
				System.out.println(ex.toString());
			}
		} else {
			JOptionPane.showConfirmDialog(this, "Bạn chưa chọn mã nhân viên", "Lỗi rồi", 2);
		}

	}

	private void updateComboboxData() {
		int n = dsChucVu_Dao.count();
		String[] items = new String[n];
		int i = 0;
		for (ChucVu s : dsChucVu_Dao.gettalltbChucVu()) {
			items[i] = s.getTenChucVu();
			i++;
		}
		cbcChucVu.setModel(new DefaultComboBoxModel<String>(items));
	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private boolean kiemtraEmail() {
//		String email= "^[a-zA-Z]+[a-z0-9]*@{1}\\w+mail.com$";
		String ten = "[[a-z0-9]+(@gmail.com|@yahoo.com){1}$]+";
		if (!Pattern.matches(ten, txtEmail.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Email theo định dạng !", " Warning !",
					JOptionPane.WARNING_MESSAGE);
			txtEmail.requestFocus();
			txtEmail.setText("");
		}
		return true;
	}

	private boolean kiemtraDiaChi() {
		String dc = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ0-9' ]+";
		if (!Pattern.matches(dc, txtDiachi.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không có ký tự đặc biệt!", " Warning !", JOptionPane.WARNING_MESSAGE);
			txtDiachi.requestFocus();
			txtDiachi.setText("");
		}
		return true;
	}

	private boolean kiemtraTenNV() {
		String ten = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ' ]+";
		if (!Pattern.matches(ten, txtTenNV.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Tên chữ cái đầu!", " Warning !", JOptionPane.WARNING_MESSAGE);
			txtTenNV.requestFocus();
			txtTenNV.setText("");
		}
		return true;
	}
	
	
	private boolean kiemtraTenCV() {
		String ten = "[aAàÀảẢãÃáÁạẠăĂằẰẳẲẵẴắẮặẶâÂầẦẩẨẫẪấẤậẬbBcCdDđĐeEèÈẻẺẽẼéÉẹẸêÊềỀểỂễỄếẾệỆfFgGhHiIìÌỉỈĩĨíÍịỊjJkKlLmMnNoOòÒỏỎõÕóÓọỌôÔồỒổỔỗỖốỐộỘơƠờỜởỞỡỠớỚợỢpPqQrRsStTuUùÙủỦũŨúÚụỤưƯừỪửỬữỮứỨựỰvVwWxXyYỳỲỷỶỹỸýÝỵỴzZ' ]+";
		if (!Pattern.matches(ten, txtTenCV.getText().trim())) {
			JOptionPane.showMessageDialog(this, "Chức vụ dạng chữ cái!", " Warning !", JOptionPane.WARNING_MESSAGE);
			txtTenCV.requestFocus();
			txtTenCV.setText("");
		}
		return true;
	}

	private boolean validData() {

		String sdt = txtSDT.getText().trim();

		String myPhone = "[0-9]+";
		if (!Pattern.matches(myPhone, sdt)) {
			JOptionPane.showMessageDialog(this, "Phải là số!", " Warning !", JOptionPane.WARNING_MESSAGE);
			txtSDT.requestFocus();
			txtSDT.setText("");
		}

		return true;
	}

}
