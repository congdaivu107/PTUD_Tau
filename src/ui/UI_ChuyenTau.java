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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.loading.PrivateClassLoader;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.FocusManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.renderer.LabelProvider;

import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietPhieuNhap_DAO;
import dao.Loai_DAO;
import dao.NhaGa_DAO;
import dao.NhanVien_DAO;
import dao.PhieuNhap_DAO;
import dao.ChuyenTau_DAO;
import dao.User_Dao;
import entity.ChiTietPhieuNhap;
import entity.Loai;
import entity.NhaGa;
import entity.NhanVien;
import entity.PhieuNhap;
import entity.ChuyenTau;
import entity.User;

public class UI_ChuyenTau extends JFrame implements ActionListener {

	JButton btnHoaDon, btnSanPham, btnKhachHang, btnNhanVien, btnBaoCao, btnHoTro, btnDangXuat;

	UI_ChuyenTau frmBanHang;
	UI_Ve frmHoaDon;
	UI_ChuyenTau frmSanPham;
	UI_KhachHang frmKhachHang;
	UI_NhanVien frmNhanVien;
	UI_ThongKe frmThongKe;
	UI_DangNhap frmDangXuat;

	private DefaultTableModel modelSanPham, modelLoai,modelSanPhamNV;

	private JTable tblSanPham, tblLoai,tblSanPhamNV;

	private JLabel lblMaSanPham, lblTenSanPham, lblSoLuong, lblLoai, lblGiaNhap, lblGiaBan, lblMaLoai, lblThanhTien,
			lblTongTien, lblTinhTongTien, lblDonVi, lblTenLoai, lblMaPhieuNhap, lblNhaCC, lblThue, lblDonGia,
			lblSoLuongPhieu, lblNgayNhap;

	private JTextField txtMaSanPham, txtTenSanPham, txtSoLuong, txtGiaNhap, txtGiaBan, txtMaLoai, txtThanhTien,
			txtDonVi, txtTenLoai, txtMaPhieuNhap, txtNhaCC, txtThue, txtSoLuongPhieu, txtDonGia, txtNgayNhap;

	private JComboBox<String> cmbLoai;
 private JButton btnSuaSanPham;
	private JLabel lblNhaCCPhieuNhap;

	private JTextField txtNhaCCPhieuNhap;

	private JComboBox<String> cmbNhaCC;

	private ArrayList<ChuyenTau> dsSanPham;

	private JComboBox<String> cmbNhaCCPhieuNhap;

	private JLabel lblMaSanPhamPhieuNhap;

	private JTextField txtMaSanPhamPhieuNhap;

	private JLabel lblTenSanPhamPhieuNhap;

	private JLabel lblSoLuongPhieuNhap;

	private JTextField txtSoLuongPhieuNhap;

	private JLabel lblDonViPhieuNhap;

	private JTextField txtDonViPhieuNhap;

	private JLabel lblGiaNhapPhieuNhap;

	private JTextField txtGiaNhapPhieuNhap;

	private JLabel lblGiaBanPhieuNhap;

	private JTextField txtGiaBanPhieuNhap;

	private JLabel lblLoaiPhieuNhap;

	private JComboBox<String> cmbLoaiPhieuNhap;

	private JComponent pnlTblSanPham;

	private DefaultTableModel modelSanPhamPhieuNhap;

	private JTable tblSanPhamPhieuNhap;

	private DefaultTableModel modelPhieuNhap;

	private JTable tblPhieuNhap;

	private JLabel lblNhanVien;

	private JTextField txtNhanVien;

	private JComboBox<String> cmbTenSanPhamPhieuNhap;

	private JComboBox<String> cmbTimMaSanPham;

	private JComboBox<String> cmbTimTenSanPham;

	private JComboBox<String> cmbTimMaLoaiSanPham;

	private JComboBox<String> cmbTimTenLoaiSanPham;

	private JComboBox<String> cmbTimMaSanPhamPhieuNhap;

	private JComboBox<String> cmbTimTenSanPhamPhieuNhap;

	private JDateChooser ngayNhapChooser;
	DecimalFormat formatter = new DecimalFormat("###,### VND");

	private DefaultTableModel modelNhaCC;

	private JTable tblNhaCC;

	private JLabel lblMaNhaCC;

	private JTextField txtMaNhaCC;

	private JLabel lblTenNhaCC;

	private JTextField txtTenNhaCC;

	private JComboBox<String> cmbTimMaNhaCCSanPham;

	private JComboBox<String> cmbTimTenNhaCCSanPham;
	private static User userCurr;

	public UI_ChuyenTau(User userCurr) throws IOException, SQLException {
		// TODO Auto-generated constructor stub
		setMinimumSize(new Dimension(1350, 700));
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Trang chính");
		this.userCurr = userCurr;		
		giaoDienChinh1();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private String formaterMoney(double money) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		return formatter.format(money).toString() + " VND";
	}

	private String getMaTiepTheo(DefaultTableModel model, String kieuMa) {
		System.out.println(kieuMa);
		String number0 = "";
		int lenghtKieuMa = kieuMa.length();
		String maCurr = model.getValueAt(model.getRowCount() - 1, 0).toString();
		System.out.println(maCurr);
		int maSPIntCurr = Integer.parseInt(maCurr.substring(maCurr.length() - 5, maCurr.length()));
		int maSPIntCurrLenght = String.valueOf(maSPIntCurr).length();
		if(maSPIntCurrLenght < String.valueOf(maSPIntCurr+1).length()) {
			maSPIntCurrLenght++;
		}
		for (int i = 0; i < 5 - (maSPIntCurrLenght); i++) {
			number0 += "0";
		}

		return kieuMa + number0 + (maSPIntCurr + 1);
	}

	private void giaoDienChinh1() throws IOException, SQLException {

		// Menu Tác Vụ Bên Trái
		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Quản Lý Chuyến Tàu"));

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
		pWest.add(Box.createVerticalStrut(65));

		// Nút Đăng Xuất
		pWest.add(btnDangXuat = new JButton("Đăng Xuất", iconOut));
		btnDangXuat.setBorder(BorderFactory.createRaisedBevelBorder());
		btnDangXuat.setForeground(Color.black);
		btnDangXuat.setPreferredSize(new Dimension(200, 50));
		pWest.add(Box.createVerticalStrut(65));

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

//		Center
		JTabbedPane tabbedPane = new JTabbedPane();
		BufferedImage imagetag = ImageIO.read(new File("image/bagtab.png"));
		ImageIcon icontag = new ImageIcon(imagetag.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		BufferedImage imagetag1 = ImageIO.read(new File("image/bagtab.png"));
		ImageIcon icontag1 = new ImageIcon(imagetag1.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		BufferedImage imagetag2 = ImageIO.read(new File("image/bagtab.png"));
		ImageIcon icontag2 = new ImageIcon(imagetag2.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		
		BufferedImage imagetag3 = ImageIO.read(new File("image/bagtab.png"));
		ImageIcon icontag3 = new ImageIcon(imagetag2.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		tabbedPane.addTab("Chuyến Tàu", icontag, sanPham(), "Chuyến Tàu");
		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
		tabbedPane.addTab("Loại Vé", icontag1, loaiSanPham(), "Loại Vé ");
		tabbedPane.addTab("Nhà Ga", icontag2, nhaCC(), "Nhà Ga");
		tabbedPane.addTab("Phiếu thêm tàu", icontag3, phieuNhapHang(), "Thêm tàu");
		}

		add(tabbedPane, BorderLayout.CENTER);

		btnBaoCao.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnHoTro.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnDangXuat.addActionListener(this);
	}

	private Component sanPham() {

		int indentLeftLbl = 20;
		int indentLeftTxt = 130;
		int heightComponent = 28;
		int widthLbl = 100;
		int widthTxt = 400;
		int topSpacing = 30;
		int lineSpacingIncreasing = 50;

		int indentRightLbl = 680;
		int indentRightTxt = 810;

		JPanel pnlSanPham = new JPanel(null);

		// table
		
		String[] columnNamesNV = { "Mã Chuyến Tàu ", "Tên", "Khởi hành - Đến", "Vé còn", "Loại", "Nhà Ga", "Giá Bán" };
		JPanel pnlTblSanPhamNV = new JPanel(null);
		pnlTblSanPhamNV.setBorder(BorderFactory.createTitledBorder("Bảng Chuyến Tàu"));
		pnlTblSanPhamNV.setBounds(10, 20, 1230, 390);
		modelSanPhamNV = new DefaultTableModel(columnNamesNV, 0);
		tblSanPhamNV = new JTable(modelSanPhamNV);
		tblSanPhamNV.setBounds(20, 20, 1210, 360);
		JScrollPane srcSanPhamNV = new JScrollPane(tblSanPhamNV);
		srcSanPhamNV.setBounds(10, 20, 1210, 360);
//		pnlTblSanPhamNV.add(srcSanPhamNV);
//		pnlSanPham.add(pnlTblSanPhamNV);
		
		String[] columnNames = { "Mã Chuyến Tàu ", "Tên", "Khởi hành - Đến", "Vé còn", "Loại", "Nhà Ga", "Giá Bán" };
		JPanel pnlTblSanPham = new JPanel(null);
		pnlTblSanPham.setBorder(BorderFactory.createTitledBorder("Bảng Chuyến Tàu"));
		pnlTblSanPham.setBounds(10, 20, 1230, 390);
		modelSanPham = new DefaultTableModel(columnNames, 0);
		tblSanPham = new JTable(modelSanPham);
		tblSanPham.setBounds(20, 20, 1210, 360);
		JScrollPane srcSanPham = new JScrollPane(tblSanPham);
		srcSanPham.setBounds(10, 20, 1210, 360);
		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
		pnlTblSanPham.add(srcSanPham);
		
		pnlSanPham.add(pnlTblSanPham);
		} else {
			pnlTblSanPhamNV.add(srcSanPhamNV);
			pnlSanPham.add(pnlTblSanPhamNV);
		}

		// nhập liệu trái

		JPanel pnlTacVu = new JPanel(null);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVu.setBounds(10, 420, 1230, 250);
		pnlSanPham.add(pnlTacVu);

		lblMaSanPham = new JLabel("Mã Chuyến Tàu:");
		lblMaSanPham.setBounds(indentLeftLbl, topSpacing, widthLbl, heightComponent);
		txtMaSanPham = new JTextField();
		txtMaSanPham.setEditable(false);
		txtMaSanPham.setBounds(indentLeftTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVu.add(lblMaSanPham);
		pnlTacVu.add(txtMaSanPham);

		lblTenSanPham = new JLabel("Tên Chuyến Tàu:");
		lblTenSanPham.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 1, widthLbl, heightComponent);
		txtTenSanPham = new JTextField();

		txtTenSanPham.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 1, widthTxt, heightComponent);
		pnlTacVu.add(lblTenSanPham);
		pnlTacVu.add(txtTenSanPham);

		lblDonVi = new JLabel("Khởi hành - Đến:");
		lblDonVi.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 2, widthLbl, heightComponent);
		txtDonVi = new JTextField();
		txtDonVi.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 2, widthTxt, heightComponent);
		pnlTacVu.add(lblDonVi);
		pnlTacVu.add(txtDonVi);

		lblSoLuong = new JLabel("Vé còn:");
		lblSoLuong.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 3, widthLbl, heightComponent);
		txtSoLuong = new JTextField();
		txtSoLuong.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 3, widthTxt, heightComponent);
		pnlTacVu.add(lblSoLuong);
		pnlTacVu.add(txtSoLuong);

		JLabel lblException = new JLabel("*");
		lblException.setForeground(Color.red);
		lblException.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 3 + 30, widthLbl + 900,
				heightComponent);
		// nhập liệu phải
		pnlTacVu.add(lblException);
		lblGiaNhap = new JLabel("Giá Nhập:");
		lblGiaNhap.setBounds(indentRightLbl, topSpacing, widthLbl, heightComponent);
		txtGiaNhap = new JTextField();
		txtGiaNhap.setBounds(indentRightTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVu.add(lblGiaNhap);
		pnlTacVu.add(txtGiaNhap);

		lblGiaBan = new JLabel("Giá Bán:");
		lblGiaBan.setBounds(indentRightLbl, topSpacing + lineSpacingIncreasing * 1, widthLbl, heightComponent);
		txtGiaBan = new JTextField();
		txtGiaBan.setBounds(indentRightTxt, topSpacing + lineSpacingIncreasing * 1, widthTxt, heightComponent);
		pnlTacVu.add(lblGiaBan);
		pnlTacVu.add(txtGiaBan);

		lblLoai = new JLabel("Loại Vé:");
		lblLoai.setBounds(indentRightLbl, topSpacing + lineSpacingIncreasing * 2, widthLbl, heightComponent);
		cmbLoai = new JComboBox<String>();
		cmbLoai.setBounds(indentRightTxt, topSpacing + lineSpacingIncreasing * 2, widthTxt, heightComponent);
		pnlTacVu.add(lblLoai);
		pnlTacVu.add(cmbLoai);

		lblNhaCC = new JLabel("Nhà Ga:");
		lblNhaCC.setBounds(indentRightLbl, topSpacing + lineSpacingIncreasing * 3, widthLbl, heightComponent);
		cmbNhaCC = new JComboBox<String>();
		cmbNhaCC.setBounds(indentRightTxt, topSpacing + lineSpacingIncreasing * 3, widthTxt, heightComponent);
		pnlTacVu.add(lblNhaCC);
		pnlTacVu.add(cmbNhaCC);

		int widthBtn = 120;
		int heightBtn = 28;
		int spacingBetweenBtn = 140;

		JPanel pnlControl = new JPanel(null);
		pnlControl.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
		pnlControl.setBounds(10, 680, 1230, 80);
		pnlSanPham.add(pnlControl);

		JButton btnThemSanPham = new JButton("Thêm");
		btnThemSanPham.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
		btnThemSanPham.setEnabled(false);
		pnlControl.add(btnThemSanPham);

		JButton btnXoaSanPham = new JButton("Xóa");
		btnXoaSanPham.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
		btnXoaSanPham.setEnabled(false);
		pnlControl.add(btnXoaSanPham);
		btnSuaSanPham = new JButton("Sửa");
		btnSuaSanPham.setBounds(indentLeftLbl + spacingBetweenBtn * 2, topSpacing, widthBtn, heightBtn);
		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
		
//		btnSuaSanPham.setEnabled(false);
		pnlControl.add(btnSuaSanPham);
		}
		JButton btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setBounds(indentLeftLbl + spacingBetweenBtn * 3, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnXoaRong);

		int indentTimMa = indentLeftLbl + spacingBetweenBtn * 4;
		int widthCmbTim = 80;
		int widthbtnTim = 60;
		JLabel lblTimMa = new JLabel("Tìm Theo Mã:");
		lblTimMa.setBounds(indentTimMa, topSpacing, widthLbl, heightComponent);
		cmbTimMaSanPham = new JComboBox<String>();
		cmbTimMaSanPham.setEditable(true);

		cmbTimMaSanPham.setBounds(indentTimMa + widthLbl, topSpacing, widthCmbTim + 70, heightComponent);
//		JButton btnTimMa = new JButton("Tìm");
//		btnTimMa.setBounds(indentTimMa + widthLbl + widthCmbTim + 10, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimMa);
		pnlControl.add(cmbTimMaSanPham);
//		pnlControl.add(btnTimMa);

		int indentTimTen = indentTimMa + widthLbl + widthCmbTim + 80;
		JLabel lblTimTen = new JLabel("Tìm Theo Tên:");
		lblTimTen.setBounds(indentTimTen + 15, topSpacing, widthLbl, heightComponent);
		cmbTimTenSanPham = new JComboBox<String>();
		cmbTimTenSanPham.setBounds(indentTimTen + widthLbl +10, topSpacing, widthCmbTim + 180, heightComponent);
//		JButton btnTimTen = new JButton("Tìm");
//		btnTimTen.setBounds(indentTimTen + widthLbl + widthCmbTim + 90, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimTen);
		pnlControl.add(cmbTimTenSanPham);
//		pnlControl.add(btnTimTen);
		if (userCurr != null && userCurr.getChucNang() != null && "Quan Ly".equals(userCurr.getChucNang().getTenChucVu())) {
		    addDataTableSanPham();
		}
else {
			addDataTableSanPhamNV();
		}
		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
		txtMaSanPham.setText(getMaTiepTheo(modelSanPham, "CT"));
		}else {
			txtMaSanPham.setText(getMaTiepTheo(modelSanPhamNV, "CT"));
		}
		cmbTimMaSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblSanPham.getSelectionModel().clearSelection();
				modelSanPham.getDataVector().removeAllElements();

				Object item = cmbTimMaSanPham.getSelectedItem();
				if (item != null && item != "") {
					ChuyenTau sanPham = new ChuyenTau_DAO().getSanPhamTheoMa(cmbTimMaSanPham.getSelectedItem().toString());

					if (sanPham != null) {
						modelSanPham.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
								sanPham.getTonKho(), sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(),
								formatter.format(sanPham.getGiaNhap()), formatter.format(sanPham.getGiaBan()) });
					} else {
						
						List<ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPham();
						for (ChuyenTau sp : sanPhams) {
							modelSanPham.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getDonViSP(),
									sp.getTonKho(), sp.getLoai().getTenLoai(), sp.getNhaCC().getTenNhaCC(),
									formatter.format(sp.getGiaNhap()), formatter.format(sp.getGiaBan()) });
						}
					}
				}

			}

		});
		cmbTimMaSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblSanPhamNV.getSelectionModel().clearSelection();
				modelSanPhamNV.getDataVector().removeAllElements();

				Object item = cmbTimMaSanPham.getSelectedItem();
				if (item != null && item != "") {
					ChuyenTau sanPham = new ChuyenTau_DAO().getSanPhamTheoMa(cmbTimMaSanPham.getSelectedItem().toString());

					if (sanPham != null) {
						modelSanPhamNV.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
								sanPham.getTonKho(), sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC()
							, formatter.format(sanPham.getGiaBan()) });
					} else {
						
						List<ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPhamNV();
						for (ChuyenTau sp : sanPhams) {
							modelSanPhamNV.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getDonViSP(),
									sp.getTonKho(), sp.getLoai().getTenLoai(), sp.getNhaCC().getTenNhaCC(),
									 formatter.format(sp.getGiaBan()) });
						}
					}
				}

			}

		});
		cmbTimTenSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				tblSanPham.getSelectionModel().clearSelection();
				modelSanPham.getDataVector().removeAllElements();

				Object item = cmbTimTenSanPham.getSelectedItem();
				if (item != null && item.toString() != "") {
					List<ChuyenTau> sanPhams = new ChuyenTau_DAO()
							.getSanPhamTheoTen(cmbTimTenSanPham.getSelectedItem().toString());
					if (sanPhams != null) {
						for (ChuyenTau sp : sanPhams) {
							modelSanPham.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getDonViSP(),
									sp.getTonKho(), sp.getLoai().getTenLoai(), sp.getNhaCC().getTenNhaCC(),
									sp.getGiaNhap(), sp.getGiaBan() });
						}
					} 
				} else if(modelSanPham.getRowCount() != new ChuyenTau_DAO().getAllSanPham().size()) {
					addDataTableSanPhamNV();
				}
				
			}
		});
		cmbTimTenSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				tblSanPhamNV.getSelectionModel().clearSelection();
				modelSanPhamNV.getDataVector().removeAllElements();

				Object item = cmbTimTenSanPham.getSelectedItem();
				if (item != null && item.toString() != "") {
					List<ChuyenTau> sanPhams = new ChuyenTau_DAO()
							.getSanPhamTheoTen(cmbTimTenSanPham.getSelectedItem().toString());
					if (sanPhams != null) {
						for (ChuyenTau sp : sanPhams) {
							modelSanPhamNV.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getDonViSP(),
									sp.getTonKho(), sp.getLoai().getTenLoai(), sp.getNhaCC().getTenNhaCC(),
									 sp.getGiaBan() });
						}
					} 
				} else if(modelSanPhamNV.getRowCount() != new ChuyenTau_DAO().getAllSanPhamNV().size()) {
					addDataTableSanPhamNV();
				}
				
			}
		});
		txtTenSanPham.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtTenSanPham.getText().length() > 0)) {
					lblException.setText("Error: Tên chuyến tàu không dược để trống");

				} else {
					lblException.setText("*");
				}

			}

		});

		txtDonVi.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtDonVi.getText().length() > 0)) {
					lblException.setText("Error: Điểm khởi hành và đến không dược để trống");

				} else {
					lblException.setText("*");
				}

			}

		});

		txtSoLuong.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtSoLuong.getText().length() > 0 && txtSoLuong.getText().matches("[0-9]+"))) {
					lblException.setText("Error: Số lượng vé không dược để trống và chỉ dược là số");

				} else {
					lblException.setText("*");
				}

			}

		});

		txtGiaBan.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtGiaBan.getText().length() > 0 && txtGiaBan.getText().matches("[0-9.]+"))) {
					lblException.setText("Error: Giá Bán không dược để trống và chỉ dược là số");

				} else {
					lblException.setText("*");
				}

			}

		});

		txtGiaNhap.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtGiaNhap.getText().length() > 0 && txtGiaNhap.getText().matches("[0-9.]+"))) {
					lblException.setText("Error: Giá Nhập không dược để trống và chỉ dược là số");

				} else {
					lblException.setText("*");
				}

			}

		});

		tblSanPham.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			private ChuyenTau sanPhamSelected;

			@Override
			public void valueChanged(ListSelectionEvent e) {
			    if (tblSanPham.getSelectedRowCount() == 1) {
			        int row = tblSanPham.getSelectedRow();
			        sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoMa((String) tblSanPham.getValueAt(row, 0));

			        txtMaSanPham.setText(sanPhamSelected.getMaSP());
			        txtTenSanPham.setText(sanPhamSelected.getTenSP());
			        txtDonVi.setText(sanPhamSelected.getDonViSP());
			        txtSoLuong.setText(String.valueOf(sanPhamSelected.getTonKho()));

			        if (sanPhamSelected.getLoai() != null) {
			            cmbLoai.setSelectedItem(sanPhamSelected.getLoai().getTenLoai());
			        } else {
			            cmbLoai.setSelectedItem("Unknown"); // Hoặc giá trị mặc định nào đó
			        }

			        if (sanPhamSelected.getNhaCC() != null) {
			            cmbNhaCC.setSelectedItem(sanPhamSelected.getNhaCC().getTenNhaCC());
			        } else {
			            cmbNhaCC.setSelectedItem("Unknown"); // Hoặc giá trị mặc định nào đó
			        }

			        txtGiaNhap.setText(String.valueOf(sanPhamSelected.getGiaNhap()));
			        txtGiaBan.setText(String.valueOf(sanPhamSelected.getGiaBan()));
			    }
			}


		});
		tblSanPhamNV.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			private ChuyenTau sanPhamSelected;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tblSanPhamNV.getSelectedRowCount() == 1) {
					int row = tblSanPhamNV.getSelectedRow();
					sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoMa((String) tblSanPhamNV.getValueAt(row, 0));

					txtMaSanPham.setText(sanPhamSelected.getMaSP());
					txtTenSanPham.setText(sanPhamSelected.getTenSP());
					txtDonVi.setText(sanPhamSelected.getDonViSP());
					txtSoLuong.setText(sanPhamSelected.getTonKho() + "");
					cmbLoai.setSelectedItem(sanPhamSelected.getLoai().getTenLoai());
					cmbNhaCC.setSelectedItem(sanPhamSelected.getNhaCC().getTenNhaCC());
//					txtGiaNhap.setText(sanPhamSelected.getGiaNhap() + "");
					txtGiaBan.setText(sanPhamSelected.getGiaBan() + "");
				}
			}

		});

//		btnThemSanPham.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (txtGiaBan.getText().length() > 0 || 
//					txtGiaNhap.getText().length() > 0 ||
//					txtSoLuong.getText().length() > 0 ||
//					txtDonVi.getText().length() > 0 ||
//					txtTenSanPham.getText().length() > 0) {
//					
//					String maSP = txtMaSanPham.getText();
//					String tenSP = txtTenSanPham.getText();
//					String donViSP = txtDonVi.getText();
//					double giaNhap = Double.parseDouble(txtGiaNhap.getText());
//					double giaBan = Double.parseDouble(txtGiaBan.getText());
//					int tonKho = Integer.parseInt(txtSoLuong.getText());
//					String tinhTrang = tonKho > 0 ? "con hang" : "het hang";
//					String image = "tinh sao";
//					NhaCC nhaCC = new NhaCC_DAO().getNhaCCTheoTenFirst(String.valueOf(cmbNhaCC.getSelectedItem()));
//					Loai loai = new Loai_DAO().getLoaiTheoTenFirst(String.valueOf(cmbLoai.getSelectedItem()));
//
//					SanPham sanPham = new SanPham(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC,
//							loai);
//
//					System.out.println(new SanPham_DAO().create(sanPham));
//					modelSanPham.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
//							sanPham.getTonKho(), sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(),
//							sanPham.getGiaNhap(), sanPham.getGiaBan() });
//					txtMaSanPham.setText(getMaTiepTheo(modelSanPham, "SP"));
//				}else {
//					lblException.setText("Error: Các ô nhập liêu không được để trống");
//				}
//				
//			}
//
//		});

		btnXoaRong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tblSanPham.getSelectionModel().clearSelection();
				txtMaSanPham.setText(getMaTiepTheo(modelSanPham, "CT"));
				txtTenSanPham.setText("");
				txtDonVi.setText("");
				txtSoLuong.setText("");
				txtGiaNhap.setText("");
				txtGiaBan.setText("");
				cmbLoai.setSelectedIndex(0);
				cmbNhaCC.setSelectedIndex(0);
			}
		});

		btnXoaSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblSanPham.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(sanPham(), "Bạn phải chòn dòng cần xóa !!!");
				else {
					String maSP = txtMaSanPham.getText();
					System.out.println(new ChuyenTau_DAO().delete(maSP));
					modelSanPham.removeRow(row);
					tblSanPham.getSelectionModel().clearSelection();
				}
			}
		});

		btnSuaSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			    if (txtGiaBan.getText().length() > 0 && txtGiaNhap.getText().length() > 0
			            && txtSoLuong.getText().length() > 0 && txtDonVi.getText().length() > 0
			            && txtTenSanPham.getText().length() > 0) {
			        int row = tblSanPham.getSelectedRow();

			        // Kiểm tra nếu chỉ số row là hợp lệ
			        if (row >= 0 && row < modelSanPham.getRowCount()) {
			            String maSP = txtMaSanPham.getText();
			            String tenSP = txtTenSanPham.getText();
			            String donViSP = txtDonVi.getText();
			            double giaNhap = Double.parseDouble(txtGiaNhap.getText());
			            double giaBan = Double.parseDouble(txtGiaBan.getText());
			            int tonKho = Integer.parseInt(txtSoLuong.getText());
			            String tinhTrang = tonKho > 0 ? "con ve" : "het ve";
			            String image = "tinh sao";
			            NhaGa nhaCC = new NhaGa_DAO().getNhaCCTheoTenFirst(String.valueOf(cmbNhaCC.getSelectedItem()));
			            Loai loai = new Loai_DAO().getLoaiTheoTenFirst(String.valueOf(cmbLoai.getSelectedItem()));

			            ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image, nhaCC, loai);

			            System.out.println(new ChuyenTau_DAO().update(sanPham));

			            modelSanPham.setValueAt(txtTenSanPham.getText(), row, 1);
			            modelSanPham.setValueAt(txtDonVi.getText(), row, 2);
			            modelSanPham.setValueAt(txtSoLuong.getText(), row, 3);
			            modelSanPham.setValueAt(String.valueOf(cmbLoai.getSelectedItem()), row, 4);
			            modelSanPham.setValueAt(String.valueOf(cmbNhaCC.getSelectedItem()), row, 5);
			            modelSanPham.setValueAt(txtGiaNhap.getText(), row, 6);
			            modelSanPham.setValueAt(txtGiaBan.getText(), row, 7);
			        } else {
			            lblException.setText("Error: Không có hàng nào được chọn hoặc chỉ số hàng không hợp lệ");
			        }
			    } else {
			        lblException.setText("Error: Các ô nhập liệu không được để trống");
			    }
			}


		});

		

		return pnlSanPham;
	}

	private void addDataTableSanPham() {
		
		
		cmbTimMaSanPham.removeAllItems();
		cmbTimTenSanPham.removeAllItems();
		cmbLoai.removeAllItems();
		cmbNhaCC.removeAllItems();
		tblSanPham.getSelectionModel().clearSelection();
		modelSanPham.getDataVector().removeAllElements();
		
//		modelSanPham.addRow(new Object[] {""});
//		modelSanPham.removeRow(0);
			dsSanPham = new ChuyenTau_DAO().getAllSanPham();
			if (cmbTimMaSanPham != null && cmbTimTenSanPham != null) {
				cmbTimMaSanPham.addItem("");
				cmbTimTenSanPham.addItem("");
				
				
				for (ChuyenTau sanPham : dsSanPham) {
					
					cmbTimMaSanPham.addItem(sanPham.getMaSP());
					cmbTimTenSanPham.addItem(sanPham.getTenSP());
				}
			}
			
			for (ChuyenTau sanPham : dsSanPham) {
			    String tenLoai = (sanPham.getLoai() != null) ? sanPham.getLoai().getTenLoai() : "Unknown";
			    String tenNhaCC = (sanPham.getNhaCC() != null) ? sanPham.getNhaCC().getTenNhaCC() : "Unknown";
			    
			    modelSanPham.addRow(new Object[] {
			        sanPham.getMaSP(),
			        sanPham.getTenSP(),
			        sanPham.getDonViSP(),
			        sanPham.getTonKho(),
			        tenLoai,
			        tenNhaCC,
			        formatter.format(sanPham.getGiaNhap()),
			        formatter.format(sanPham.getGiaBan())
			    });
			}

			List<Loai> loais = new Loai_DAO().getAllLoai();
			for (Loai loai : loais) {
				cmbLoai.addItem(loai.getTenLoai());
			}
			List<NhaGa> nhaCCs = new NhaGa_DAO().getAllNhaCC();
			for (NhaGa nhaCC : nhaCCs) {
				cmbNhaCC.addItem(nhaCC.getTenNhaCC());
			}
			txtMaSanPham.setText(getMaTiepTheo(modelSanPham, "CT"));
			AutoCompleteDecorator.decorate(cmbTimMaSanPham);
			AutoCompleteDecorator.decorate(cmbTimTenSanPham);
		
		
	}
private void addDataTableSanPhamNV() {
		
		
		cmbTimMaSanPham.removeAllItems();
		cmbTimTenSanPham.removeAllItems();
		cmbLoai.removeAllItems();
		cmbNhaCC.removeAllItems();
		tblSanPhamNV.getSelectionModel().clearSelection();
		modelSanPhamNV.getDataVector().removeAllElements();
		
//		modelSanPham.addRow(new Object[] {""});
//		modelSanPham.removeRow(0);
			dsSanPham = new ChuyenTau_DAO().getAllSanPhamNV();
			if (cmbTimMaSanPham != null && cmbTimTenSanPham != null) {
				cmbTimMaSanPham.addItem("");
				cmbTimTenSanPham.addItem("");
				
				
				for (ChuyenTau sanPham : dsSanPham) {
					
					cmbTimMaSanPham.addItem(sanPham.getMaSP());
					cmbTimTenSanPham.addItem(sanPham.getTenSP());
				}
			}
			
			for (ChuyenTau sanPham : dsSanPham) {
				modelSanPhamNV.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
						sanPham.getTonKho(), sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(),
						formatter.format( sanPham.getGiaBan()) });
			}
			List<Loai> loais = new Loai_DAO().getAllLoai();
			for (Loai loai : loais) {
				cmbLoai.addItem(loai.getTenLoai());
			}
			List<NhaGa> nhaCCs = new NhaGa_DAO().getAllNhaCC();
			for (NhaGa nhaCC : nhaCCs) {
				cmbNhaCC.addItem(nhaCC.getTenNhaCC());
			}
			txtMaSanPham.setText(getMaTiepTheo(modelSanPhamNV, "CT"));
			AutoCompleteDecorator.decorate(cmbTimMaSanPham);
			AutoCompleteDecorator.decorate(cmbTimTenSanPham);
		
		
	}

	private Component loaiSanPham() {

		int indentLeftLbl = 20;
		int indentLeftTxt = 130;
		int heightComponent = 28;
		int widthLbl = 100;
		int widthTxt = 400;
		int topSpacing = 30;
		int lineSpacingIncreasing = 32;

		int indentRightLbl = 680;
		int indentRightTxt = 810;

		JPanel pnlLoaiSP = new JPanel(null);

		// table
		
		String[] columnNames = { "Mã Loại", "Tên Loại" };
		JPanel pnlTblLoai = new JPanel(null);
		pnlTblLoai.setBorder(BorderFactory.createTitledBorder("Bảng Loai"));
		pnlTblLoai.setBounds(10, 20, 1230, 530);
		modelLoai = new DefaultTableModel(columnNames, 0);
		tblLoai = new JTable(modelLoai);
		tblLoai.setBounds(20, 20, 1210, 500);
		JScrollPane srcLoai= new JScrollPane(tblLoai);
		srcLoai.setBounds(10, 20, 1210, 500);
		pnlTblLoai.add(srcLoai);
		pnlLoaiSP.add(pnlTblLoai);

		JPanel pnlTacVu = new JPanel(null);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVu.setBounds(10, 570, 1230, 95);
		pnlLoaiSP.add(pnlTacVu);

		lblMaLoai = new JLabel("Mã Loại:");
		lblMaLoai.setBounds(indentLeftLbl, topSpacing, widthLbl, heightComponent);
		txtMaLoai = new JTextField();
		txtMaLoai.setBounds(indentLeftTxt, topSpacing, widthTxt, heightComponent);
		txtMaLoai.setEditable(false);
		pnlTacVu.add(lblMaLoai);
		pnlTacVu.add(txtMaLoai);

		lblTenLoai = new JLabel("Tên Loại:");
		lblTenLoai.setBounds(indentRightLbl, topSpacing, widthLbl, heightComponent);
		txtTenLoai = new JTextField();
		txtTenLoai.setBounds(indentRightTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVu.add(lblTenLoai);
		pnlTacVu.add(txtTenLoai);
		JLabel lblException = new JLabel("*");
		lblException.setForeground(Color.red);
		lblException.setBounds(indentLeftLbl, topSpacing + 30, widthLbl + 900, heightComponent);
		pnlTacVu.add(lblException);
		
		int widthBtn = 120;
		int heightBtn = 28;
		int spacingBetweenBtn = 140;

		JPanel pnlControl = new JPanel(null);
		pnlControl.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
		pnlControl.setBounds(10, 680, 1230, 80);
		pnlLoaiSP.add(pnlControl);

		JButton btnThemLoaiSanPham = new JButton("Thêm");
		btnThemLoaiSanPham.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
//		btnThemLoaiSanPham.setEnabled(false);
		pnlControl.add(btnThemLoaiSanPham);

		JButton btnXoaLoaiSanPham = new JButton("Xóa");
		btnXoaLoaiSanPham.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
		btnXoaLoaiSanPham.setEnabled(false);
		pnlControl.add(btnXoaLoaiSanPham);

		JButton btnSuaLoaiSanPham = new JButton("Sửa");
		btnSuaLoaiSanPham.setBounds(indentLeftLbl + spacingBetweenBtn * 2, topSpacing, widthBtn, heightBtn);
//		btnSuaLoaiSanPham.setEnabled(false);
		pnlControl.add(btnSuaLoaiSanPham);

		JButton btnLoaiXoaRong = new JButton("Làm mới");
		btnLoaiXoaRong.setBounds(indentLeftLbl + spacingBetweenBtn * 3, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnLoaiXoaRong);

		int indentTimMa = indentLeftLbl + spacingBetweenBtn * 4;
		int widthCmbTim = 80;
		int widthbtnTim = 60;
		JLabel lblTimMa = new JLabel("Tìm Theo Mã:");
		lblTimMa.setBounds(indentTimMa, topSpacing, widthLbl, heightComponent);
		cmbTimMaLoaiSanPham = new JComboBox<String>();
		cmbTimMaLoaiSanPham.setEditable(true);

		cmbTimMaLoaiSanPham.setBounds(indentTimMa + widthLbl, topSpacing, widthCmbTim + 70, heightComponent);
//		JButton btnTimMa = new JButton("Tìm");
//		btnTimMa.setBounds(indentTimMa + widthLbl + widthCmbTim + 10, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimMa);
		pnlControl.add(cmbTimMaLoaiSanPham);
//		pnlControl.add(btnTimMa);

		int indentTimTen = indentTimMa + widthLbl + widthCmbTim + 80;
		JLabel lblTimTen = new JLabel("Tìm Theo Tên:");
		lblTimTen.setBounds(indentTimTen + 15, topSpacing, widthLbl, heightComponent);
		cmbTimTenLoaiSanPham = new JComboBox<String>();
		cmbTimTenLoaiSanPham.setBounds(indentTimTen + widthLbl +10, topSpacing, widthCmbTim + 180, heightComponent);
//		JButton btnTimTen = new JButton("Tìm");
//		btnTimTen.setBounds(indentTimTen + widthLbl + widthCmbTim + 90, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimTen);
		pnlControl.add(cmbTimTenLoaiSanPham);
//		pnlControl.add(btnTimTen);

		
		
		//		pnlControl.add(btnTimTen);

//		int indentTimTen = indentTimMa + widthLbl + widthTxtTim +80;
//		JLabel lblTimTen = new JLabel("Tìm Theo Tên:");
//		lblTimTen.setBounds(indentTimTen, topSpacing, widthLbl, heightComponent);
//		JTextField txtTimTen= new JTextField();
//		txtTimTen.setBounds(indentTimTen + widthLbl, topSpacing, widthTxtTim, heightComponent);
//		JButton btnTimTen = new JButton("Tìm");
//		btnTimTen.setBounds(indentTimTen + widthLbl + widthTxtTim +10, topSpacing, widthbtnTim, heightBtn);
//		
//		pnlControl.add(lblTimTen);
//		pnlControl.add(txtTimTen);
//		pnlControl.add(btnTimTen);

		addDataTableLoai();

		txtTenLoai.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtTenLoai.getText().length() > 0)) {
					lblException.setText("Error: Tên Loai không dược để trống");

				} else {
					lblException.setText("*");
				}

			}

		});

		txtMaLoai.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtMaLoai.getText().length() > 0)) {
					lblException.setText("Error: Mã Loại không dược để trống");

				} else {
					lblException.setText("*");
				}

			}

		});

		tblLoai.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			private Loai loaiSelected;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tblLoai.getSelectedRowCount() == 1) {

					int row = tblLoai.getSelectedRow();

					loaiSelected = new Loai_DAO().getLoaiTheoMa((String) tblLoai.getValueAt(row, 0));
					txtMaLoai.setText(loaiSelected.getMaLoai());
					txtTenLoai.setText(loaiSelected.getTenLoai());
				}
			}

		});

		btnThemLoaiSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtMaLoai.getText().length() > 0 && txtTenLoai.getText().length() > 0) {
					String maLoai = txtMaLoai.getText().toUpperCase();
					String tenLoai = txtTenLoai.getText();

					Loai loai = new Loai(maLoai, tenLoai);
					
					if (!isTrungMaLoai(loai)) {
						new Loai_DAO().create(loai);

						modelLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });

						cmbLoai.addItem(loai.getTenLoai());
						cmbLoaiPhieuNhap.addItem(loai.getTenLoai());
						addDataTableLoai();
					}
					else {
						lblException.setText("Error: Các ô nhập liêu không được để trống");
						
					}
					
				} else {
					lblException.setText("Error: Các ô nhập liêu không được để trống");
				}

			}

			private boolean isTrungMaLoai(Loai loai) {
				
				return new Loai_DAO().getAllLoai().contains(loai);
			}
		});

		btnLoaiXoaRong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblLoai.getSelectionModel().clearSelection();
				txtMaLoai.setText("");
				txtTenLoai.setText("");

				addDataTableLoai();
			}
		});

		btnXoaLoaiSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = tblLoai.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(sanPham(), "Bạn phải chòn dòng cần xóa !!!");
				else {
					
					String maLoai = txtMaLoai.getText();
					if(isDsSanPhamCoMaLoai(maLoai)) {
						JOptionPane.showMessageDialog(sanPham(), "Bạn không được xóa loại vé đã có chuyến tàu tương thích");
					} else {
						System.out.println(new Loai_DAO().delete(maLoai));
						modelLoai.removeRow(row);
						tblLoai.getSelectionModel().clearSelection();
					}
					
				}
			}

		});

		btnSuaLoaiSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtMaLoai.getText().length() > 0 && txtTenLoai.getText().length() > 0) {
					int row = tblLoai.getSelectedRow();
					String maLoai = txtMaLoai.getText();
					String tenLoai = txtTenLoai.getText();

					Loai loai = new Loai(maLoai, tenLoai);

					System.out.println(new Loai_DAO().update(loai));
					System.out.println(txtMaLoai.getText());
					modelLoai.setValueAt(txtMaLoai.getText(), row, 0);
					modelLoai.setValueAt(txtTenLoai.getText(), row, 1);
				} else {
					lblException.setText("Error: Các ô nhập liêu không được để trống");
				}

			}

		});

		cmbTimMaLoaiSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				modelLoai.getDataVector().removeAllElements();
				tblLoai.getSelectionModel().clearSelection();
				Object item = cmbTimMaLoaiSanPham.getSelectedItem();
				if (item != null && item != "") {
					Loai l = new Loai_DAO().getLoaiTheoMa(cmbTimMaLoaiSanPham.getSelectedItem().toString());

					if (l != null) {
						
						modelLoai.addRow(new Object[] { l.getMaLoai(), l.getTenLoai() });
					} else {
						
						List<Loai> loais = new Loai_DAO().getAllLoai();
						for (Loai loai : loais) {
							modelLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
						}
					}
				}
			}

		});

		cmbTimTenLoaiSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblLoai.getSelectionModel().clearSelection();
				modelLoai.getDataVector().removeAllElements();
				Object item = cmbTimTenLoaiSanPham.getSelectedItem();
				if (item != null && item != "") {
					List<Loai> loais = new Loai_DAO().getLoaiTheoTen(cmbTimTenLoaiSanPham.getSelectedItem().toString());

					if (loais != null) {
						for (Loai loai : loais) {
							modelLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
						}
					}
					if (loais.size() == 0) {
						loais = new Loai_DAO().getAllLoai();
						for (Loai loai : loais) {
							modelLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
						}
					}
				}
			}

		});

		return pnlLoaiSP;
	}

	protected boolean isDsSanPhamCoMaLoai(String maLoai) {
		List<ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPham();
		for (ChuyenTau sanPham : sanPhams) {
			if (sanPham.getLoai().getMaLoai().equals(maLoai)) {
				return true;
			} 
		}
		return false;
	}

	private void addDataTableLoai() {
		tblLoai.getSelectionModel().clearSelection();
		modelLoai.getDataVector().removeAllElements();
		List<Loai> loais = new Loai_DAO().getAllLoai();
		cmbTimMaLoaiSanPham.removeAllItems();
		cmbTimTenLoaiSanPham.removeAllItems();
		cmbTimMaLoaiSanPham.addItem("");
		cmbTimTenLoaiSanPham.addItem("");
		for (Loai loai : loais) {
			modelLoai.addRow(new Object[] { loai.getMaLoai(), loai.getTenLoai() });
			cmbTimMaLoaiSanPham.addItem(loai.getMaLoai());
			cmbTimTenLoaiSanPham.addItem(loai.getTenLoai());
		}
		AutoCompleteDecorator.decorate(cmbTimMaLoaiSanPham);
		AutoCompleteDecorator.decorate(cmbTimTenLoaiSanPham);
		txtMaLoai.setText(getMaTiepTheo(modelLoai, "LO"));
	}
	
	private Component nhaCC() {

		int indentLeftLbl = 20;
		int indentLeftTxt = 130;
		int heightComponent = 28;
		int widthLbl = 100;
		int widthTxt = 400;
		int topSpacing = 30;
		int lineSpacingIncreasing = 32;

		int indentRightLbl = 680;
		int indentRightTxt = 810;

		JPanel pnlNhaCC = new JPanel(null);

		// table
		
		String[] columnNames = { "Mã Nhà Ga", "Tên Nhà Ga" };
		JPanel pnlTblNhaCC = new JPanel(null);
		pnlTblNhaCC.setBorder(BorderFactory.createTitledBorder("Bảng Nhà Ga"));
		pnlTblNhaCC.setBounds(10, 20, 1230, 530);
		modelNhaCC = new DefaultTableModel(columnNames, 0);
		tblNhaCC = new JTable(modelNhaCC);
		tblLoai.setBounds(20, 20, 1210, 500);
		JScrollPane srcNhaCC= new JScrollPane(tblNhaCC);
		srcNhaCC.setBounds(10, 20, 1210, 500);
		pnlTblNhaCC.add(srcNhaCC);
		pnlNhaCC.add(pnlTblNhaCC);

		JPanel pnlTacVu = new JPanel(null);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVu.setBounds(10, 570, 1230, 95);
		pnlNhaCC.add(pnlTacVu);

		lblMaNhaCC = new JLabel("Mã Nhà Ga:");
		lblMaNhaCC.setBounds(indentLeftLbl, topSpacing, widthLbl, heightComponent);
		txtMaNhaCC = new JTextField();
		txtMaNhaCC.setBounds(indentLeftTxt, topSpacing, widthTxt, heightComponent);
		txtMaNhaCC.setEditable(false);
		pnlTacVu.add(lblMaNhaCC);
		pnlTacVu.add(txtMaNhaCC);

		lblTenNhaCC = new JLabel("Tên Nhà Ga:");
		lblTenNhaCC.setBounds(indentRightLbl, topSpacing, widthLbl, heightComponent);
		txtTenNhaCC = new JTextField();
		txtTenNhaCC.setBounds(indentRightTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVu.add(lblTenNhaCC);
		pnlTacVu.add(txtTenNhaCC);
		JLabel lblException = new JLabel("*");
		lblException.setForeground(Color.red);
		lblException.setBounds(indentLeftLbl, topSpacing + 30, widthLbl + 900, heightComponent);
		pnlTacVu.add(lblException);
		
		int widthBtn = 120;
		int heightBtn = 28;
		int spacingBetweenBtn = 140;

		JPanel pnlControl = new JPanel(null);
		pnlControl.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
		pnlControl.setBounds(10, 680, 1230, 80);
		pnlNhaCC.add(pnlControl);

		JButton btnThemNhaCCSanPham = new JButton("Thêm");
		btnThemNhaCCSanPham.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
//		btnThemNhaCCSanPham.setEnabled(false);
		pnlControl.add(btnThemNhaCCSanPham);

		JButton btnXoaNhaCCSanPham = new JButton("Xóa");
		btnXoaNhaCCSanPham.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
		btnXoaNhaCCSanPham.setEnabled(false);
		pnlControl.add(btnXoaNhaCCSanPham);

		JButton btnSuaNhaCCSanPham = new JButton("Sửa");
		btnSuaNhaCCSanPham.setBounds(indentLeftLbl + spacingBetweenBtn * 2, topSpacing, widthBtn, heightBtn);
//		btnSuaNhaCCSanPham.setEnabled(false);
		pnlControl.add(btnSuaNhaCCSanPham);

		JButton btnNhaCCXoaRong = new JButton("Làm mới");
		btnNhaCCXoaRong.setBounds(indentLeftLbl + spacingBetweenBtn * 3, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnNhaCCXoaRong);

		int indentTimMa = indentLeftLbl + spacingBetweenBtn * 4;
		int widthCmbTim = 80;
		int widthbtnTim = 60;
		JLabel lblTimMa = new JLabel("Tìm Theo Mã:");
		lblTimMa.setBounds(indentTimMa, topSpacing, widthLbl, heightComponent);
		cmbTimMaNhaCCSanPham = new JComboBox<String>();
		cmbTimMaNhaCCSanPham.setEditable(true);

		cmbTimMaNhaCCSanPham.setBounds(indentTimMa + widthLbl, topSpacing, widthCmbTim + 70, heightComponent);
//		JButton btnTimMa = new JButton("Tìm");
//		btnTimMa.setBounds(indentTimMa + widthLbl + widthCmbTim + 10, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimMa);
		pnlControl.add(cmbTimMaNhaCCSanPham);
//		pnlControl.add(btnTimMa);

		int indentTimTen = indentTimMa + widthLbl + widthCmbTim + 80;
		JLabel lblTimTen = new JLabel("Tìm Theo Tên:");
		lblTimTen.setBounds(indentTimTen + 15, topSpacing, widthLbl, heightComponent);
		cmbTimTenNhaCCSanPham = new JComboBox<String>();
		cmbTimTenNhaCCSanPham.setBounds(indentTimTen + widthLbl +10, topSpacing, widthCmbTim + 180, heightComponent);
//		JButton btnTimTen = new JButton("Tìm");
//		btnTimTen.setBounds(indentTimTen + widthLbl + widthCmbTim + 90, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimTen);
		pnlControl.add(cmbTimTenNhaCCSanPham);
//		pnlControl.add(btnTimTen);

		
		
		//		pnlControl.add(btnTimTen);

//		int indentTimTen = indentTimMa + widthLbl + widthTxtTim +80;
//		JLabel lblTimTen = new JLabel("Tìm Theo Tên:");
//		lblTimTen.setBounds(indentTimTen, topSpacing, widthLbl, heightComponent);
//		JTextField txtTimTen= new JTextField();
//		txtTimTen.setBounds(indentTimTen + widthLbl, topSpacing, widthTxtTim, heightComponent);
//		JButton btnTimTen = new JButton("Tìm");
//		btnTimTen.setBounds(indentTimTen + widthLbl + widthTxtTim +10, topSpacing, widthbtnTim, heightBtn);
//		
//		pnlControl.add(lblTimTen);
//		pnlControl.add(txtTimTen);
//		pnlControl.add(btnTimTen);

		addDataTableNhaCC();

		txtTenNhaCC.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtTenNhaCC.getText().length() > 0)) {
					lblException.setText("Error: Tên nhà ga không dược để trống");

				} else {
					lblException.setText("*");
				}

			}

		});

		txtMaNhaCC.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtMaNhaCC.getText().length() > 0)) {
					lblException.setText("Error: Mã Loại không dược để trống");

				} else {
					lblException.setText("*");
				}

			}

		});

		tblNhaCC.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			private NhaGa nhaCCSelected;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tblNhaCC.getSelectedRowCount() == 1) {

					int row = tblNhaCC.getSelectedRow();

					nhaCCSelected = new NhaGa_DAO().getNhaCCTheoMa((String) tblNhaCC.getValueAt(row, 0));
					txtMaNhaCC.setText(nhaCCSelected.getMaNhaCC());
					txtTenNhaCC.setText(nhaCCSelected.getTenNhaCC());
				}
			}

		});

		btnThemNhaCCSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (txtMaNhaCC.getText().length() > 0 && txtTenNhaCC.getText().length() > 0) {
					String maNhaCC = txtMaNhaCC.getText();
					String tenNhaCC = txtTenNhaCC.getText();

					NhaGa NhaCC = new NhaGa(maNhaCC, tenNhaCC);

					new NhaGa_DAO().create(NhaCC);

					modelNhaCC.addRow(new Object[] { NhaCC.getMaNhaCC(), NhaCC.getTenNhaCC() });

					cmbNhaCC.addItem(NhaCC.getTenNhaCC());
					cmbNhaCCPhieuNhap.addItem(NhaCC.getTenNhaCC());
					addDataTableNhaCC();
				} else {
					lblException.setText("Error: Các ô nhập liêu không được để trống");
				}

			}
		});

		btnNhaCCXoaRong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblNhaCC.getSelectionModel().clearSelection();
				txtMaNhaCC.setText("");
				txtTenNhaCC.setText("");

				addDataTableNhaCC();
			}
		});

		btnXoaNhaCCSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = tblNhaCC.getSelectedRow();
				if (row == -1)
					JOptionPane.showMessageDialog(sanPham(), "Bạn phải chòn dòng cần xóa !!!");
				else {
					
					String maNhaCC = txtMaNhaCC.getText();
					if(isDsSanPhamCoMaNhaCC(maNhaCC)) {
						JOptionPane.showMessageDialog(sanPham(), "Bạn không được xóa loại vé đã có chuyến tàu tương thích");
					} else {
						System.out.println(new NhaGa_DAO().delete(maNhaCC));
						modelNhaCC.removeRow(row);
						tblNhaCC.getSelectionModel().clearSelection();
					}
					
				}
			}

		});

		btnSuaNhaCCSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtMaNhaCC.getText().length() > 0 && txtTenNhaCC.getText().length() > 0) {
					int row = tblNhaCC.getSelectedRow();
					String maNhaCC = txtMaNhaCC.getText();
					String tenNhaCC = txtTenNhaCC.getText();

					NhaGa NhaCC = new NhaGa(maNhaCC, tenNhaCC);

					System.out.println(new NhaGa_DAO().update(NhaCC));
					System.out.println(txtMaNhaCC.getText());
					modelNhaCC.setValueAt(txtMaNhaCC.getText(), row, 0);
					modelNhaCC.setValueAt(txtTenNhaCC.getText(), row, 1);
				} else {
					lblException.setText("Error: Các ô nhập liêu không được để trống");
				}

			}

		});

		cmbTimMaNhaCCSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				modelNhaCC.getDataVector().removeAllElements();
				tblNhaCC.getSelectionModel().clearSelection();
				Object item = cmbTimMaNhaCCSanPham.getSelectedItem();
				if (item != null && item != "") {
					NhaGa l = new NhaGa_DAO().getNhaCCTheoMa(cmbTimMaNhaCCSanPham.getSelectedItem().toString());

					if (l != null) {
						
						modelNhaCC.addRow(new Object[] { l.getMaNhaCC(), l.getTenNhaCC() });
					} else {
						
						List<NhaGa> NhaCCs = new NhaGa_DAO().getAllNhaCC();
						for (NhaGa NhaCC : NhaCCs) {
							modelNhaCC.addRow(new Object[] { NhaCC.getMaNhaCC(), NhaCC.getTenNhaCC() });
						}
					}
				}
			}

		});

		cmbTimTenNhaCCSanPham.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblNhaCC.getSelectionModel().clearSelection();
				modelNhaCC.getDataVector().removeAllElements();
				Object item = cmbTimTenNhaCCSanPham.getSelectedItem();
				if (item != null && item != "") {
					List<NhaGa> NhaCCs = new NhaGa_DAO().getNhaCCTheoTen(cmbTimTenNhaCCSanPham.getSelectedItem().toString());

					if (NhaCCs != null) {
						for (NhaGa NhaCC : NhaCCs) {
							modelNhaCC.addRow(new Object[] { NhaCC.getMaNhaCC(), NhaCC.getTenNhaCC() });
						}
					}
					if (NhaCCs.size() == 0) {
						NhaCCs = new NhaGa_DAO().getAllNhaCC();
						for (NhaGa NhaCC : NhaCCs) {
							modelNhaCC.addRow(new Object[] { NhaCC.getMaNhaCC(), NhaCC.getTenNhaCC() });
						}
					}
				}
			}

		});

		return pnlNhaCC;
	}

	protected boolean isDsSanPhamCoMaNhaCC(String maNhaCC) {
		List<ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPham();
		for (ChuyenTau sanPham : sanPhams) {
			if (sanPham.getLoai().getMaLoai().equals(maNhaCC)) {
				return true;
			} 
		}
		return false;
	}

	private void addDataTableNhaCC() {
		
		
		tblNhaCC.getSelectionModel().clearSelection();
		modelNhaCC.getDataVector().removeAllElements();
		List<NhaGa> nhaCCs = new NhaGa_DAO().getAllNhaCC();
		cmbTimMaNhaCCSanPham.removeAllItems();
		cmbTimTenNhaCCSanPham.removeAllItems();
		cmbTimMaNhaCCSanPham.addItem("");
		cmbTimTenNhaCCSanPham.addItem("");
		for (NhaGa nhaCC : nhaCCs) {
			modelNhaCC.addRow(new Object[] { nhaCC.getMaNhaCC(), nhaCC.getTenNhaCC() });
			cmbTimMaNhaCCSanPham.addItem(nhaCC.getMaNhaCC());
			cmbTimTenNhaCCSanPham.addItem(nhaCC.getTenNhaCC());
		}
		AutoCompleteDecorator.decorate(cmbTimMaNhaCCSanPham);
		AutoCompleteDecorator.decorate(cmbTimTenNhaCCSanPham);
		txtMaNhaCC.setText(getMaTiepTheo(modelNhaCC, "NG"));
	}

	private Component phieuNhapHang() {
		
		int indentLeftLbl = 20;
		int indentLeftTxt = 130;
		int heightComponent = 28;
		int widthLbl = 100;
		int widthTxt = 410;
		int topSpacing = 30;
		int lineSpacingIncreasing = 40;

		int indentRightLbl = 680;
		int indentRightTxt = 810;

		JPanel pnlPhieuNhap = new JPanel(null);

		JPanel pnlTacVuPhieuNhap = new JPanel(null);

		String[] columnNamesPN = { "Mã Chuyến Tàu", "Ngày Nhập", "Thuế(%)", "Tổng Tiền", "Nhân Viên" };

		JPanel pnlTblPhieuNhap = new JPanel(null);
		pnlTblPhieuNhap.setBorder(BorderFactory.createTitledBorder("Bảng Phiếu Nhập"));
		pnlTblPhieuNhap.setBounds(10, 10, 650, 260);

		modelPhieuNhap = new DefaultTableModel(columnNamesPN, 0);
		tblPhieuNhap = new JTable(modelPhieuNhap);
		tblPhieuNhap.setBounds(10, 20, 630, 230);
		JScrollPane srcPhieuNhap = new JScrollPane(tblPhieuNhap);
		srcPhieuNhap.setBounds(10, 20, 630, 230);
		pnlTblPhieuNhap.add(srcPhieuNhap);
		pnlPhieuNhap.add(pnlTblPhieuNhap);

		pnlTacVuPhieuNhap.setBorder(BorderFactory.createTitledBorder("Tác Vụ Phiếu Nhập"));
		pnlTacVuPhieuNhap.setBounds(indentRightLbl - 10, 10, 570, 260);
		pnlPhieuNhap.add(pnlTacVuPhieuNhap);

		lblMaPhieuNhap = new JLabel("Mã Phiếu Nhập:");
		lblMaPhieuNhap.setBounds(indentLeftLbl, topSpacing, widthLbl, heightComponent);
		txtMaPhieuNhap = new JTextField();
		txtMaPhieuNhap.setEditable(false);
		txtMaPhieuNhap.setBounds(indentLeftTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVuPhieuNhap.add(lblMaPhieuNhap);
		pnlTacVuPhieuNhap.add(txtMaPhieuNhap);

		lblNhanVien = new JLabel("Nhân Viên:");
		lblNhanVien.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 1, widthLbl, heightComponent);
		txtNhanVien = new JTextField();
		txtNhanVien.setText(userCurr.getNhanVien().getTenNV());
		txtNhanVien.setEditable(false);
		txtNhanVien.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 1, widthTxt, heightComponent);
		pnlTacVuPhieuNhap.add(lblNhanVien);
		pnlTacVuPhieuNhap.add(txtNhanVien);

		lblThue = new JLabel("Thuế(%):");
		lblThue.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 2, widthLbl, heightComponent);
		txtThue = new JTextField();
		txtThue.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 2, widthTxt, heightComponent);
		pnlTacVuPhieuNhap.add(lblThue);
		pnlTacVuPhieuNhap.add(txtThue);

		lblNgayNhap = new JLabel("Ngày Nhập:");
		lblNgayNhap.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 3, widthLbl, heightComponent);
//		txtNgayNhap= new JTextField();
		ngayNhapChooser = new JDateChooser();
		ngayNhapChooser.setDateFormatString("yyyy-MM-dd");
		ngayNhapChooser.getDateEditor().getUiComponent().setFocusable(false);
		ngayNhapChooser.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 3, widthTxt, heightComponent);
		pnlTacVuPhieuNhap.add(lblNgayNhap);
		pnlTacVuPhieuNhap.add(ngayNhapChooser);

		lblTongTien = new JLabel("Tổng tiền:");
		lblTongTien.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 4, widthLbl, heightComponent);
		lblTinhTongTien = new JLabel("0.0");
		lblTinhTongTien.setForeground(Color.red);
		lblTinhTongTien.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 4, widthTxt, heightComponent);
		pnlTacVuPhieuNhap.add(lblTongTien);
		pnlTacVuPhieuNhap.add(lblTinhTongTien);

		JLabel lblExceptionPhieuNhap = new JLabel("*");
		lblExceptionPhieuNhap.setForeground(Color.red);
		lblExceptionPhieuNhap.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 4 + 30, widthLbl + 900,
				heightComponent);
		pnlTacVuPhieuNhap.add(lblExceptionPhieuNhap);

		// table

		String[] columnNames = { "Mã Chuyến Tàu", "Tên Chuyến Tàu", "Khởi hành - Đến", "Số Lượng", "Loai", "Nhà Ga", "Giá Nhập", "Giá Bán",
				"Đơn Giá Nhập Vào" };
		JPanel pnlTblSanPhamPhieuNhap = new JPanel(null);
		pnlTblSanPhamPhieuNhap.setBorder(BorderFactory.createTitledBorder("Bảng Sản Phẩm"));
		pnlTblSanPhamPhieuNhap.setBounds(10, 280, 1230, 160);
		modelSanPhamPhieuNhap = new DefaultTableModel(columnNames, 0);
		tblSanPhamPhieuNhap = new JTable(modelSanPhamPhieuNhap);
		tblSanPhamPhieuNhap.setBounds(20, 20, 1210, 130);
		JScrollPane srcSanPhamPhieuNhap = new JScrollPane(tblSanPhamPhieuNhap);
		srcSanPhamPhieuNhap.setBounds(10, 20, 1210, 130);
		pnlTblSanPhamPhieuNhap.add(srcSanPhamPhieuNhap);
		pnlPhieuNhap.add(pnlTblSanPhamPhieuNhap);

//		String[] columnNamesCTPN = {"Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"};
//		DefaultTableModel modelCTPhieuNhap = new DefaultTableModel(columnNamesCTPN, 0);
//		JTable tblCTPhieuNhap = new JTable(modelCTPhieuNhap);
//		tblCTPhieuNhap.setBounds(620, 20, 420, 380);
//		JScrollPane srcCTPhieuNhap = new JScrollPane(tblCTPhieuNhap);
//		srcCTPhieuNhap.setBounds(620, 20, 420, 380);
//		pnlPhieuNhap.add(srcCTPhieuNhap);

		// nhập liệu trái

		JPanel pnlTacVuSanPham = new JPanel(null);
		pnlTacVuSanPham.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVuSanPham.setBounds(10, 450, 1230, 220);
		pnlPhieuNhap.add(pnlTacVuSanPham);

		lblMaSanPhamPhieuNhap = new JLabel("Mã Chuyến Tàu:");
		lblMaSanPhamPhieuNhap.setBounds(indentLeftLbl, topSpacing, widthLbl, heightComponent);
		txtMaSanPhamPhieuNhap = new JTextField();
		txtMaSanPhamPhieuNhap.setEditable(false);
		txtMaSanPhamPhieuNhap.setBounds(indentLeftTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblMaSanPhamPhieuNhap);
		pnlTacVuSanPham.add(txtMaSanPhamPhieuNhap);

		lblTenSanPhamPhieuNhap = new JLabel("Tên Chuyến Tàu:");
		lblTenSanPhamPhieuNhap.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 1, widthLbl,
				heightComponent);
		cmbTenSanPhamPhieuNhap = new JComboBox<String>();
		cmbTenSanPhamPhieuNhap.setEditable(true);
		cmbTenSanPhamPhieuNhap.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 1, widthTxt,
				heightComponent);
		pnlTacVuSanPham.add(lblTenSanPhamPhieuNhap);
		pnlTacVuSanPham.add(cmbTenSanPhamPhieuNhap);

		lblDonViPhieuNhap = new JLabel("Khởi hành - Đến:");
		lblDonViPhieuNhap.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 2, widthLbl, heightComponent);
		txtDonViPhieuNhap = new JTextField();
		txtDonViPhieuNhap.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 2, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblDonViPhieuNhap);
		pnlTacVuSanPham.add(txtDonViPhieuNhap);

		lblSoLuongPhieuNhap = new JLabel("Số Lượng:");
		lblSoLuongPhieuNhap.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 3, widthLbl, heightComponent);
		txtSoLuongPhieuNhap = new JTextField();
		txtSoLuongPhieuNhap.setBounds(indentLeftTxt, topSpacing + lineSpacingIncreasing * 3, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblSoLuongPhieuNhap);
		pnlTacVuSanPham.add(txtSoLuongPhieuNhap);

		JLabel lblExceptionSanPhamPhieuNhap = new JLabel("*");
		lblExceptionSanPhamPhieuNhap.setForeground(Color.red);
		lblExceptionSanPhamPhieuNhap.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 3 + 30,
				widthLbl + 900, heightComponent);
		pnlTacVuSanPham.add(lblExceptionSanPhamPhieuNhap);

		// nhập liệu phải

		lblGiaNhapPhieuNhap = new JLabel("Giá Nhập:");
		lblGiaNhapPhieuNhap.setBounds(indentRightLbl, topSpacing, widthLbl, heightComponent);
		txtGiaNhapPhieuNhap = new JTextField();
		txtGiaNhapPhieuNhap.setBounds(indentRightTxt, topSpacing, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblGiaNhapPhieuNhap);
		pnlTacVuSanPham.add(txtGiaNhapPhieuNhap);

		lblGiaBanPhieuNhap = new JLabel("Giá Bán:");
		lblGiaBanPhieuNhap.setBounds(indentRightLbl, topSpacing + lineSpacingIncreasing * 1, widthLbl, heightComponent);
		txtGiaBanPhieuNhap = new JTextField();
		txtGiaBanPhieuNhap.setBounds(indentRightTxt, topSpacing + lineSpacingIncreasing * 1, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblGiaBanPhieuNhap);
		pnlTacVuSanPham.add(txtGiaBanPhieuNhap);

		lblLoaiPhieuNhap = new JLabel("Loại Chuyến Tàu:");
		lblLoaiPhieuNhap.setBounds(indentRightLbl, topSpacing + lineSpacingIncreasing * 2, widthLbl, heightComponent);
		cmbLoaiPhieuNhap = new JComboBox<String>();

		cmbLoaiPhieuNhap.setBounds(indentRightTxt, topSpacing + lineSpacingIncreasing * 2, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblLoaiPhieuNhap);
		pnlTacVuSanPham.add(cmbLoaiPhieuNhap);

		lblNhaCCPhieuNhap = new JLabel("Nhà Ga:");
		lblNhaCCPhieuNhap.setBounds(indentRightLbl, topSpacing + lineSpacingIncreasing * 3, widthLbl, heightComponent);
		cmbNhaCCPhieuNhap = new JComboBox<String>();

		cmbNhaCCPhieuNhap.setBounds(indentRightTxt, topSpacing + lineSpacingIncreasing * 3, widthTxt, heightComponent);
		pnlTacVuSanPham.add(lblNhaCCPhieuNhap);
		pnlTacVuSanPham.add(cmbNhaCCPhieuNhap);

		int widthBtn = 120;
		int heightBtn = 28;
		int spacingBetweenBtn = 140;

		JPanel pnlControl = new JPanel(null);
		pnlControl.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
		pnlControl.setBounds(10, 680, 1230, 80);
		pnlPhieuNhap.add(pnlControl);

		ngayNhapChooser.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

		JButton btnThemSanPhamPhieuNhap = new JButton("Thêm");
		btnThemSanPhamPhieuNhap.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
//		btnThemSanPhamPhieuNhap.setEnabled(false);
		pnlControl.add(btnThemSanPhamPhieuNhap);

		JButton btnXoaSanPhamPhieuNhap = new JButton("Xóa");
		btnXoaSanPhamPhieuNhap.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnXoaSanPhamPhieuNhap);

		JButton btnSuaSanPhamPhieuNhap = new JButton("Sửa");
		btnSuaSanPhamPhieuNhap.setBounds(indentLeftLbl + spacingBetweenBtn * 2, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnSuaSanPhamPhieuNhap);

		JButton btnXoaRongPhieuNhap = new JButton("Làm mới");
		btnXoaRongPhieuNhap.setBounds(indentLeftLbl + spacingBetweenBtn * 3, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnXoaRongPhieuNhap);

		int indentTimMa = indentLeftLbl + spacingBetweenBtn * 4;
		int widthCmbTim = 100;
		int widthbtnTim = 60;
		JLabel lblTimMa = new JLabel("Tìm Theo Mã:");
		lblTimMa.setBounds(indentTimMa, topSpacing, widthLbl, heightComponent);
		cmbTimMaSanPhamPhieuNhap = new JComboBox<String>();
		cmbTimMaSanPhamPhieuNhap.setEditable(true);

		cmbTimMaSanPhamPhieuNhap.setBounds(indentTimMa + widthLbl, topSpacing, widthCmbTim + 50, heightComponent);
//		JButton btnTimMa = new JButton("Tìm");
//		btnTimMa.setBounds(indentTimMa + widthLbl + widthCmbTim + 10, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimMa);
		pnlControl.add(cmbTimMaSanPhamPhieuNhap);
//		pnlControl.add(btnTimMa);

		int indentTimTen = indentTimMa + widthLbl + widthCmbTim + 80;
		JLabel lblTimTen = new JLabel("Tìm Theo Tên NV:");
		lblTimTen.setBounds(indentTimTen-10, topSpacing, widthLbl + 10, heightComponent);
		cmbTimTenSanPhamPhieuNhap = new JComboBox<String>();
		cmbTimTenSanPhamPhieuNhap.setBounds(indentTimTen + widthLbl + 10, topSpacing, widthCmbTim + 140, heightComponent);
//		JButton btnTimTen = new JButton("Tìm");
//		btnTimTen.setBounds(indentTimTen + widthLbl + widthCmbTim + 90, topSpacing, widthbtnTim, heightBtn);

		pnlControl.add(lblTimTen);
		pnlControl.add(cmbTimTenSanPhamPhieuNhap);
//		pnlControl.add(btnTimTen);

		updateAllDataPhieuNhap();
		addDataTablePhieuNhap();
		
		txtMaPhieuNhap.setText(getMaTiepTheo(modelPhieuNhap, "PN"));
		txtMaSanPhamPhieuNhap.setText(getMaTiepTheo(modelSanPham, "CT"));
		txtThue.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtThue.getText().length() > 0 && txtThue.getText().matches("[0-9.]+"))) {
					lblExceptionPhieuNhap.setText("Error: Thuế không dược để trống và chỉ dược là số");
					
				} else {
					
					lblExceptionPhieuNhap.setText("*");
				}

			}

		});

		cmbTenSanPhamPhieuNhap.getEditor().getEditorComponent().addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {

			}

			@Override
			public void focusLost(FocusEvent e) {
				if ((cmbTenSanPhamPhieuNhap.getSelectedItem().toString().length() == 0)) {
					lblExceptionSanPhamPhieuNhap.setText("Error: Tên chuyến tàu không dược để trống");

				} else {
					lblExceptionSanPhamPhieuNhap.setText("*");
				}

			}

		});

		txtDonViPhieuNhap.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtDonViPhieuNhap.getText().length() > 0)) {
					lblExceptionSanPhamPhieuNhap.setText("Error: Khởi hành - đến không dược để trống");

				} else {
					lblExceptionSanPhamPhieuNhap.setText("*");
				}

			}

		});

		txtSoLuongPhieuNhap.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtSoLuongPhieuNhap.getText().length() > 0 && txtSoLuongPhieuNhap.getText().matches("[0-9]+"))) {
					lblExceptionSanPhamPhieuNhap.setText("Error: Số lượng không dược để trống và chỉ dược là số");

				} else {
					lblExceptionSanPhamPhieuNhap.setText("*");
				}

			}

		});

		txtGiaBanPhieuNhap.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtGiaBanPhieuNhap.getText().length() > 0 && txtGiaBanPhieuNhap.getText().matches("[0-9.]+"))) {
					lblExceptionSanPhamPhieuNhap.setText("Error: Giá Bán không dược để trống và chỉ dược là số");

				} else {
					lblExceptionSanPhamPhieuNhap.setText("*");
				}

			}

		});

		txtGiaNhapPhieuNhap.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void focusLost(FocusEvent e) {
				if (!(txtGiaNhapPhieuNhap.getText().length() > 0 && txtGiaNhapPhieuNhap.getText().matches("[0-9.]+"))) {
					lblExceptionSanPhamPhieuNhap.setText("Error: Giá Nhập không dược để trống và chỉ dược là số");

				} else {
					lblExceptionSanPhamPhieuNhap.setText("*");
				}

			}

		});

		tblPhieuNhap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			private PhieuNhap phieuNhapSelected;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tblPhieuNhap.getSelectedRowCount() == 1) {
					int row = tblPhieuNhap.getSelectedRow();
					phieuNhapSelected = new PhieuNhap_DAO()
							.getPhieuNhapTheoMa((String) tblPhieuNhap.getValueAt(row, 0));

					txtMaPhieuNhap.setText(phieuNhapSelected.getMaPhieuNhap());
					txtNhanVien.setText(phieuNhapSelected.getNhanVien().getTenNV());
					txtThue.setText((phieuNhapSelected.getThue()) + "");
					ngayNhapChooser.setDate(phieuNhapSelected.getNgayNhap());
					lblTinhTongTien.setText(formaterMoney(phieuNhapSelected.getTongTien()));

					List<ChiTietPhieuNhap> chiTietPhieuNhaps = phieuNhapSelected.getChiTietPhieuNhaps();

					tblSanPhamPhieuNhap.getSelectionModel().clearSelection();
					modelSanPhamPhieuNhap.getDataVector().removeAllElements();
					modelSanPhamPhieuNhap.addRow(new Object[] {});
					modelSanPhamPhieuNhap.removeRow(0);
					dsSanPham = new ChuyenTau_DAO().getAllSanPham();

					for (ChiTietPhieuNhap chiTietPhieuNhap : chiTietPhieuNhaps) {
						ChuyenTau sanPham = chiTietPhieuNhap.getSanPham();
						modelSanPhamPhieuNhap.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(),
								sanPham.getDonViSP(), chiTietPhieuNhap.getSoLuong() + "",
								sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(), sanPham.getGiaNhap(),
								sanPham.getGiaBan(), chiTietPhieuNhap.getThanhTien() });
					}
				}
			}
		});

		cmbTenSanPhamPhieuNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String ten = (String) cmbTenSanPhamPhieuNhap.getSelectedItem();
					ChuyenTau sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoTenFirst(ten);

					txtMaSanPhamPhieuNhap.setText(sanPhamSelected.getMaSP());
//					txtTenSanPhamPhieuNhap.setText(sanPhamSelected.getTenSP());
					txtDonViPhieuNhap.setText(sanPhamSelected.getDonViSP());
					txtSoLuongPhieuNhap.setText(sanPhamSelected.getTonKho() + "");
					cmbLoaiPhieuNhap.setSelectedItem(sanPhamSelected.getLoai().getTenLoai());
					cmbNhaCCPhieuNhap.setSelectedItem(sanPhamSelected.getNhaCC().getTenNhaCC());
					txtGiaNhapPhieuNhap.setText(sanPhamSelected.getGiaNhap() + "");
					txtGiaBanPhieuNhap.setText(sanPhamSelected.getGiaBan() + "");
					
					txtDonViPhieuNhap.setEditable(false);
					txtGiaNhapPhieuNhap.setEditable(false);
					txtGiaBanPhieuNhap.setEditable(false);
				} catch (Exception e2) {
					txtMaSanPhamPhieuNhap.setText(getMaTiepTheo(modelSanPham, "CT"));
//					cmbTenSanPhamPhieuNhap.setSelectedItem("");
					txtDonViPhieuNhap.setText("");
					txtSoLuongPhieuNhap.setText("");
					cmbLoaiPhieuNhap.setSelectedItem("");
					cmbNhaCCPhieuNhap.setSelectedItem("");
					txtGiaNhapPhieuNhap.setText("");
					txtGiaBanPhieuNhap.setText("");
					
					txtDonViPhieuNhap.setEditable(true);
					txtGiaNhapPhieuNhap.setEditable(true);
					txtGiaBanPhieuNhap.setEditable(true);

				}
			}
		});

		tblSanPhamPhieuNhap.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			private ChuyenTau sanPhamSelected;

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tblSanPhamPhieuNhap.getSelectedRowCount() == 1) {
					int row = tblSanPhamPhieuNhap.getSelectedRow();
					sanPhamSelected = new ChuyenTau_DAO()
							.getSanPhamTheoMa((String) tblSanPhamPhieuNhap.getValueAt(row, 0));

					txtMaSanPhamPhieuNhap.setText(sanPhamSelected.getMaSP());
					cmbTenSanPhamPhieuNhap.setSelectedItem(sanPhamSelected.getTenSP());
					txtDonViPhieuNhap.setText(sanPhamSelected.getDonViSP());
					txtSoLuongPhieuNhap.setText(tblSanPhamPhieuNhap.getValueAt(row, 3).toString());
					cmbLoaiPhieuNhap.setSelectedItem(sanPhamSelected.getLoai().getTenLoai());
					cmbNhaCCPhieuNhap.setSelectedItem(sanPhamSelected.getNhaCC().getTenNhaCC());
					txtGiaNhapPhieuNhap.setText(sanPhamSelected.getGiaNhap() + "");
					txtGiaBanPhieuNhap.setText(sanPhamSelected.getGiaBan() + "");
				}
			}
		});

		btnXoaRongPhieuNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cmbTenSanPhamPhieuNhap.setSelectedIndex(0);

				txtMaSanPhamPhieuNhap.setText(getMaTiepTheo(modelSanPham, "CT"));
				cmbTenSanPhamPhieuNhap.setSelectedItem("");
				txtDonViPhieuNhap.setText("");
				txtSoLuongPhieuNhap.setText("");
				cmbLoaiPhieuNhap.setSelectedItem("");
				cmbNhaCCPhieuNhap.setSelectedItem("");
				txtGiaNhapPhieuNhap.setText("");
				txtGiaBanPhieuNhap.setText("");

				txtMaPhieuNhap.setText(getMaTiepTheo(modelPhieuNhap, "PN"));
				txtNhanVien.setText(userCurr.getNhanVien().getTenNV());
				txtThue.setText("");
				ngayNhapChooser.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
				lblTinhTongTien.setText("0.0");
				tblSanPhamPhieuNhap.getSelectionModel().clearSelection();
				modelSanPhamPhieuNhap.getDataVector().removeAllElements();
				modelSanPhamPhieuNhap.addRow(new Object[] {});
				modelSanPhamPhieuNhap.removeRow(0);
				addDataTablePhieuNhap();
			}
		});

		btnThemSanPhamPhieuNhap.addActionListener(new ActionListener() {

			private PhieuNhap phieuNhap;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtGiaBanPhieuNhap.getText().length() > 0 && txtGiaNhapPhieuNhap.getText().length() > 0
						&& txtSoLuongPhieuNhap.getText().length() > 0 && txtDonViPhieuNhap.getText().length() > 0
						&& cmbTenSanPhamPhieuNhap.getSelectedItem().toString().length() > 0) {

					String maPhieuNhap = txtMaPhieuNhap.getText();
					String maSanPham = txtMaSanPhamPhieuNhap.getText();
					ChuyenTau sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoMa(maSanPham);
					PhieuNhap phieuNhapSelected = new PhieuNhap_DAO().getPhieuNhapTheoMa(maPhieuNhap);

					String maSP = txtMaSanPhamPhieuNhap.getText();
					String tenSP = cmbTenSanPhamPhieuNhap.getSelectedItem().toString();
					String donViSP = txtDonViPhieuNhap.getText();
					double giaNhap = Double.parseDouble(txtGiaNhapPhieuNhap.getText());
					double giaBan = Double.parseDouble(txtGiaBanPhieuNhap.getText());
					int tonKhoHienTai = sanPhamSelected != null ? sanPhamSelected.getTonKho() : 0;
					int tonKho = Integer.parseInt(txtSoLuongPhieuNhap.getText()) + tonKhoHienTai;
					String tinhTrang = tonKho > 0 ? "con ve" : "het ve";
					String image = "cap nhat sau";
					NhaGa nhaCC = new NhaGa_DAO()
							.getNhaCCTheoTenFirst(String.valueOf(cmbNhaCCPhieuNhap.getSelectedItem()));
					Loai loai = new Loai_DAO().getLoaiTheoTenFirst(String.valueOf(cmbLoaiPhieuNhap.getSelectedItem()));

					ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image,
							nhaCC, loai);
					ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(giaNhap,
							Integer.parseInt(txtSoLuongPhieuNhap.getText()), sanPham, maPhieuNhap);
					
					if (!maPhieuNhap.equals(getMaTiepTheo(modelPhieuNhap, "PN"))) { // phiếu nhập đã có
						if (!phieuNhapSelected.isContainsCTPN(chiTietPhieuNhap)) {
							workByMaSanPham(sanPham, txtSoLuongPhieuNhap.getText(), maSanPham);
							
						} else {
							lblExceptionSanPhamPhieuNhap.setText("Bạn không thể thêm trùng mã chuyến tàu và mã phiếu nhập");
						}
							

					} else {
						String maPN = txtMaPhieuNhap.getText();
						double thue = Double.parseDouble(txtThue.getText());
						Date ngayNhap = new Date(ngayNhapChooser.getDate().getTime());
						NhanVien nhanVien = new NhanVien_DAO().getNhanVienTheoTenNV(txtNhanVien.getText());
						PhieuNhap phieuNhap = new PhieuNhap(maPN, nhaCC.getTenNhaCC(), ngayNhap, thue, nhanVien);
						
						
						System.out.println(new PhieuNhap_DAO().create(phieuNhap));
						workByMaSanPham(sanPham, txtSoLuongPhieuNhap.getText(), maSanPham);
						System.out.println(new ChiTietPhieuNhap_DAO().create(chiTietPhieuNhap));
						addDataTablePhieuNhap();
						
						
					}
					addDataTablePhieuNhap();
					if (phieuNhapSelected !=null && !phieuNhapSelected.isContainsCTPN(chiTietPhieuNhap)) {
						System.out.println(new ChiTietPhieuNhap_DAO().create(chiTietPhieuNhap));
						updateAllDataPhieuNhap();
					}
					
					
				} else {
					lblExceptionSanPhamPhieuNhap.setText("Error: Các ô nhập liêu không được để trống");
				}
			}

		});

		btnSuaSanPhamPhieuNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtGiaBanPhieuNhap.getText().length() > 0 && txtGiaNhapPhieuNhap.getText().length() > 0
						&& txtSoLuongPhieuNhap.getText().length() > 0 && txtDonViPhieuNhap.getText().length() > 0
						&& cmbTenSanPhamPhieuNhap.getSelectedItem().toString().length() > 0) {
					int rowSanPham = tblSanPhamPhieuNhap.getSelectedRow();
					String maPhieuNhap = txtMaPhieuNhap.getText();
					String maSanPham = txtMaSanPhamPhieuNhap.getText();
					ChuyenTau sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoMa(maSanPham);
					PhieuNhap phieuNhapSelected = new PhieuNhap_DAO().getPhieuNhapTheoMa(maPhieuNhap);
					List<ChiTietPhieuNhap> chiTietPhieuNhaps = phieuNhapSelected.getChiTietPhieuNhaps();

					PhieuNhap phieuNhap = new PhieuNhap(maPhieuNhap, phieuNhapSelected.getNhaCC(),
							new Date(ngayNhapChooser.getDate().getTime()), Double.parseDouble(txtThue.getText()),
							new NhanVien_DAO().getNhanVienTheoTenNV(txtNhanVien.getText()));
					System.out.println(new PhieuNhap_DAO().update(phieuNhap));
					addDataTablePhieuNhap();
					if (rowSanPham >= 0) {
						String maSP = txtMaSanPhamPhieuNhap.getText();
						String tenSP = cmbTenSanPhamPhieuNhap.getSelectedItem().toString();
						String donViSP = txtDonViPhieuNhap.getText();
						double giaNhap = Double.parseDouble(txtGiaNhapPhieuNhap.getText());
						double giaBan = Double.parseDouble(txtGiaBanPhieuNhap.getText());
						int soLuongChanged = Integer.parseInt(txtSoLuongPhieuNhap.getText());
						int tonKhoHienTai = sanPhamSelected != null ? soLuongChanged - new ChiTietPhieuNhap_DAO()
								.getChiTietPhieuNhapTheoMaPhieuNhapVaMaSP(maPhieuNhap, maSP).getSoLuong() : 0;
						int tonKho = sanPhamSelected.getTonKho() + tonKhoHienTai;
						String tinhTrang = tonKho > 0 ? "con ve" : "het ve";
						String image = "tinh sao";
						NhaGa nhaCC = new NhaGa_DAO()
								.getNhaCCTheoTenFirst(String.valueOf(cmbNhaCCPhieuNhap.getSelectedItem()));
						Loai loai = new Loai_DAO()
								.getLoaiTheoTenFirst(String.valueOf(cmbLoaiPhieuNhap.getSelectedItem()));

						ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image,
								nhaCC, loai);
						System.out.println(new ChuyenTau_DAO().update(sanPham));
						ChiTietPhieuNhap chiTietPhieuNhap = new ChiTietPhieuNhap(giaNhap, soLuongChanged, sanPham,
								maPhieuNhap);
//						modelSanPhamPhieuNhap.setValueAt(cmbTenSanPhamPhieuNhap.getSelectedItem().toString(),
//								rowSanPham, 1); // update san pham phieu nhap
//						modelSanPhamPhieuNhap.setValueAt(txtDonViPhieuNhap.getText(), rowSanPham, 2);
//						modelSanPhamPhieuNhap.setValueAt(txtSoLuongPhieuNhap.getText(), rowSanPham, 3);
//						modelSanPhamPhieuNhap.setValueAt(String.valueOf(cmbLoaiPhieuNhap.getSelectedItem()), rowSanPham,
//								4);
//						modelSanPhamPhieuNhap.setValueAt(String.valueOf(cmbNhaCCPhieuNhap.getSelectedItem()),
//								rowSanPham, 5);
//						modelSanPhamPhieuNhap.setValueAt(txtGiaNhapPhieuNhap.getText(), rowSanPham, 6);
//						modelSanPhamPhieuNhap.setValueAt(txtGiaBanPhieuNhap.getText(), rowSanPham, 7);
//						modelSanPhamPhieuNhap.setValueAt(soLuongChanged * giaNhap, rowSanPham, 8);
						System.out.println(new ChiTietPhieuNhap_DAO().update(chiTietPhieuNhap));
					} 
				}else {
					lblExceptionSanPhamPhieuNhap.setText("Error: Các ô nhập liêu không được để trống");
				}
			}
		});

		btnXoaSanPhamPhieuNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowSanPham = tblSanPhamPhieuNhap.getSelectedRow();
				int rowMaPhieuNhap = tblPhieuNhap.getSelectedRow();
				if (rowMaPhieuNhap == -1)
					JOptionPane.showMessageDialog(sanPham(), "Bạn phải chòn dòng cần xóa !!!");
				else {
					String maPhieuNhap = txtMaPhieuNhap.getText();
					String maSanPham = txtMaSanPhamPhieuNhap.getText();
					ChuyenTau sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoMa(maSanPham);
					PhieuNhap phieuNhapSelected = new PhieuNhap_DAO().getPhieuNhapTheoMa(maPhieuNhap);
					List<ChiTietPhieuNhap> chiTietPhieuNhaps = phieuNhapSelected.getChiTietPhieuNhaps();

					PhieuNhap phieuNhap = new PhieuNhap(maPhieuNhap, phieuNhapSelected.getNhaCC(),
							new Date(ngayNhapChooser.getDate().getTime()), Double.parseDouble(txtThue.getText()),
							new NhanVien_DAO().getNhanVienTheoTenNV(txtNhanVien.getText()));
					System.out.println(new PhieuNhap_DAO().update(phieuNhap));
					addDataTablePhieuNhap();
					if (rowSanPham >= 0) {
						String maSP = txtMaSanPhamPhieuNhap.getText();
						String tenSP = cmbTenSanPhamPhieuNhap.getSelectedItem().toString();
						String donViSP = txtDonViPhieuNhap.getText();
						double giaNhap = Double.parseDouble(txtGiaNhapPhieuNhap.getText());
						double giaBan = Double.parseDouble(txtGiaBanPhieuNhap.getText());
						int tonKho = sanPhamSelected.getTonKho() - new ChiTietPhieuNhap_DAO()
								.getChiTietPhieuNhapTheoMaPhieuNhapVaMaSP(maPhieuNhap, maSP).getSoLuong();
						
						String tinhTrang = tonKho > 0 ? "con ve" : "het ve";
						String image = "tinh sao";
						NhaGa nhaCC = new NhaGa_DAO()
								.getNhaCCTheoTenFirst(String.valueOf(cmbNhaCCPhieuNhap.getSelectedItem()));
						Loai loai = new Loai_DAO()
								.getLoaiTheoTenFirst(String.valueOf(cmbLoaiPhieuNhap.getSelectedItem()));

						ChuyenTau sanPham = new ChuyenTau(maSP, tenSP, donViSP, giaNhap, giaBan, tinhTrang, tonKho, image,
								nhaCC, loai);
						System.out.println(new ChuyenTau_DAO().update(sanPham));
						System.out.println(new ChiTietPhieuNhap_DAO().delete(maSanPham, maPhieuNhap));

						System.out.println(new ChuyenTau_DAO().delete(maSP));
						tblSanPhamPhieuNhap.getSelectionModel().clearSelection();
						addDataTableSanPham();
					} else {
						for (ChiTietPhieuNhap chiTietPhieuNhap : chiTietPhieuNhaps) {
							ChuyenTau sanPham = chiTietPhieuNhap.getSanPham();
							sanPham.setTonKho(sanPham.getTonKho() - chiTietPhieuNhap.getSoLuong());
							System.out.println(new ChuyenTau_DAO().update(sanPham));
							System.out.println(new ChiTietPhieuNhap_DAO().delete(
									chiTietPhieuNhap.getSanPham().getMaSP(), chiTietPhieuNhap.getMaPhieuNhap()));
						}
						System.out.println("del PN " + new PhieuNhap_DAO().delete(maPhieuNhap));
						modelSanPhamPhieuNhap.getDataVector().removeAllElements();
						modelSanPhamPhieuNhap.addRow(new Object[] {});
						modelSanPhamPhieuNhap.removeRow(0);

						modelPhieuNhap.removeRow(rowMaPhieuNhap);
						tblPhieuNhap.getSelectionModel().clearSelection();
						addDataTableSanPham();
					}
				}
			}
		});

		cmbTimMaSanPhamPhieuNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblPhieuNhap.getSelectionModel().clearSelection();
				modelPhieuNhap.getDataVector().removeAllElements();
				tblSanPhamPhieuNhap.getSelectionModel().clearSelection();
				modelSanPhamPhieuNhap.getDataVector().removeAllElements();
				modelSanPhamPhieuNhap.addRow(new Object[] {});
				modelSanPhamPhieuNhap.removeRow(0);

				Object item = cmbTimMaSanPhamPhieuNhap.getSelectedItem();
				if (item != null && item.toString() != "") {
					PhieuNhap pn = new PhieuNhap_DAO()
							.getPhieuNhapTheoMa(cmbTimMaSanPhamPhieuNhap.getSelectedItem().toString());
					if (pn != null) {
						modelPhieuNhap.addRow(new Object[] { pn.getMaPhieuNhap(), pn.getNgayNhap().toString(),
								pn.getThue() + "", pn.getTongTien() + "", pn.getNhanVien().getTenNV() });
					} else {

						List<PhieuNhap> phieuNhaps = new PhieuNhap_DAO().getAllPhieuNhaps();
						for (PhieuNhap phieuNhap : phieuNhaps) {
							modelPhieuNhap.addRow(new Object[] { phieuNhap.getMaPhieuNhap(),
									phieuNhap.getNgayNhap().toString(), phieuNhap.getThue() + "",
									phieuNhap.getTongTien() + "", phieuNhap.getNhanVien().getTenNV() });

						}

					}

				}

			}

		});

		cmbTimTenSanPhamPhieuNhap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblPhieuNhap.getSelectionModel().clearSelection();
				modelPhieuNhap.getDataVector().removeAllElements();
				modelPhieuNhap.addRow(new Object[] {});
				modelPhieuNhap.removeRow(0);
				tblSanPhamPhieuNhap.getSelectionModel().clearSelection();
				modelSanPhamPhieuNhap.getDataVector().removeAllElements();
				modelSanPhamPhieuNhap.addRow(new Object[] {});
				modelSanPhamPhieuNhap.removeRow(0);
				List<PhieuNhap> phieuNhaps = null;
				Object item = cmbTimTenSanPhamPhieuNhap.getSelectedItem();
				if (item != null && item != "") {
					phieuNhaps = new PhieuNhap_DAO().getPhieuNhapTheoMaNV(new NhanVien_DAO()
							.getNhanVienTheoTenNV(cmbTimTenSanPhamPhieuNhap.getSelectedItem().toString()).getMaNV());
				} else {
					phieuNhaps = new PhieuNhap_DAO().getAllPhieuNhaps();
				}

				if (phieuNhaps.size() != 0) {
					for (PhieuNhap phieuNhap : phieuNhaps) {
						modelPhieuNhap.addRow(new Object[] { phieuNhap.getMaPhieuNhap(),
								phieuNhap.getNgayNhap().toString(), phieuNhap.getThue() + "",
								phieuNhap.getTongTien() + "", phieuNhap.getNhanVien().getTenNV() });

					}
				}
			}
		});

		return pnlPhieuNhap;
	}

	private void updateAllDataPhieuNhap() {
		cmbTenSanPhamPhieuNhap.removeAllItems();
		cmbLoaiPhieuNhap.removeAllItems();
		cmbNhaCCPhieuNhap.removeAllItems();
		
		cmbTenSanPhamPhieuNhap.addItem("");
		List<ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPham();
		for (ChuyenTau sanPham : sanPhams) {
			cmbTenSanPhamPhieuNhap.addItem(sanPham.getTenSP());
		}
		AutoCompleteDecorator.decorate(cmbTenSanPhamPhieuNhap);
		List<Loai> loais = new Loai_DAO().getAllLoai();
		for (Loai loai : loais) {
			cmbLoaiPhieuNhap.addItem(loai.getTenLoai());
		}
		List<NhaGa> nhaCCs = new NhaGa_DAO().getAllNhaCC();
		for (NhaGa nhaCC : nhaCCs) {
			cmbNhaCCPhieuNhap.addItem(nhaCC.getTenNhaCC());
		}

	}

	protected void workByMaSanPham(ChuyenTau sanPham, String soLuong, String maSanPham) {
		if (!maSanPham.equals(getMaTiepTheo(modelSanPham, "CT"))) { // maSanPham da co
			System.out.println(new ChuyenTau_DAO().update(sanPham)); // update SP
			modelSanPhamPhieuNhap.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
					soLuong, sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(),formatter.format( sanPham.getGiaNhap()),
					formatter.format(sanPham.getGiaBan()),formatter.format((sanPham.getGiaBan() * Integer.parseInt(soLuong))) });
			addDataTableSanPham();
		} else {
			
			System.out.println(new ChuyenTau_DAO().create(sanPham)); // them SP
			modelSanPhamPhieuNhap.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
					soLuong, sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(),formatter.format( sanPham.getGiaNhap()),
					formatter.format(sanPham.getGiaBan()), (sanPham.getGiaBan() * Integer.parseInt(soLuong)) });
			txtMaSanPham.setText(getMaTiepTheo(modelSanPham, "SP"));
			txtMaSanPhamPhieuNhap.setText(getMaTiepTheo(modelSanPham, "CT"));
			addDataTableSanPham();
		}
	}

	private void addDataTablePhieuNhap() {

		tblPhieuNhap.getSelectionModel().clearSelection();
		modelPhieuNhap.getDataVector().removeAllElements();
		List<PhieuNhap> phieuNhaps = new PhieuNhap_DAO().getAllPhieuNhaps();
		cmbTimTenSanPhamPhieuNhap.removeAllItems();
		cmbTimMaSanPhamPhieuNhap.removeAllItems();

		cmbTimTenSanPhamPhieuNhap.addItem("");
		cmbTimMaSanPhamPhieuNhap.addItem("");
		for (PhieuNhap phieuNhap : phieuNhaps) {
			modelPhieuNhap.addRow(new Object[] { phieuNhap.getMaPhieuNhap(), phieuNhap.getNgayNhap().toString(),
					phieuNhap.getThue() + "",formatter.format( phieuNhap.getTongTien()) + "", phieuNhap.getNhanVien().getTenNV() });
			cmbTimMaSanPhamPhieuNhap.addItem(phieuNhap.getMaPhieuNhap());

		}

		List<NhanVien> nhanViens = new NhanVien_DAO().gettalltbNhanVien();
		for (NhanVien nhanVien : nhanViens) {
			cmbTimTenSanPhamPhieuNhap.addItem(nhanVien.getTenNV());
		}
		AutoCompleteDecorator.decorate(cmbTimTenSanPhamPhieuNhap);
		AutoCompleteDecorator.decorate(cmbTimMaSanPhamPhieuNhap);
	}

	public static void main(String[] args) throws IOException, SQLException {
		new UI_ChuyenTau(userCurr);
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
			/*
			 * try { frmHoTro= new UI_HoTro(); this.dispose(); } catch (IOException e1) { //
			 * TODO Auto-generated catch block e1.printStackTrace(); }
			 */
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
			frmDangXuat = new UI_DangNhap();
			this.dispose();

		}
	}

}
