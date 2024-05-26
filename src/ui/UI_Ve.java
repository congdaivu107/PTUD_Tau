//1.	Nguyễn Quý Khả - 19530291 (Leader)
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import connectDB.ConnectDB;
import dao.ChiTietVe_DAO;
import dao.Ve_DAO;
import dao.KhachHang_DAO;

import dao.NhanVien_DAO;

import dao.ChuyenTau_DAO;
import entity.ChiTietVe;

import entity.Ve;
import entity.KhachHang;
import entity.NhanVien;
import entity.ChuyenTau;
import entity.User;

public class UI_Ve extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NO_SUCH_PAGE = 0;
	private static final int PAGE_EXISTS = 0;
	JButton btnHoaDon, btnSanPham, btnKhachHang, btnNhanVien, btnBaoCao, btnHoTro, btnDangXuat;
	UI_Ve frmHoaDon;
	UI_ChuyenTau frmSanPham;
	UI_KhachHang frmKhachHang;
	UI_NhanVien frmNhanVien;
	UI_ThongKe frmThongKe;
	UI_DangNhap frmDangXuat;
	private JPanel pnlBanHangMain;
	private JPanel pnlBanHangMainTop;
	private JTextField txtTaiKoan, txtCV;
	private JLabel lblMaHDBan;
	private JTextField txtMaHDBan;
	private JLabel lblNgayBan;
	private JTextField txtNgayBan;
	private JLabel lblMaNV;
	private JTextField txtMaNV;
	private JLabel lblTenNV;
	private JTextField txtTenNV;
	private JLabel lblMaKH;
	private JTextField txtMaKH;
	private JLabel lblTenKH;
	private JTextField txtTenKH;
	private JLabel lblDiaChi;
	private JTextField txtDiaChi;
	private JLabel lblDienThoai;
	private JTextField txtDienThoai;
	private JButton btnTimKH;
	private JPanel pnlBanHangMainCenter;
	private JLabel lblMaSanPham;
	private JTextField txtMaSanPham;
	private JButton btnTimKiemMaSP;
	private JLabel lblTenSP;
	private JComboBox<String> cmbTenSP;
	private JLabel lblSoLuong;
	private JTextField txtSoLuong;
	private JLabel lblGiamGia;
	private JTextField txtGiamGia;
	private JLabel lblDonGia;
	private JTextField txtDonGia;
	private JPanel pnlBanHangMainTable;
	String header[] = { "Mã Chuyến Tàu", "Tên Chuyến Tàu", "Số Lượng", "Đơn Giá", "Giảm giá", "Thành Tiền" };
	private JScrollPane scr;
	private JPanel pnlThanhTien;
	private JLabel lblThanhTien;
	private JLabel lblTongThanhTien;

	private JPanel pnlBanHangMainConTrol;
	private JButton btnLuuTT;
	private JButton btnXoaSP;
	private JButton btnSuaSP;
	private JButton btnXoaRong;
	private JButton btnInHD;
	private JButton btnLuuHD;
	private JButton btnSuaHD = new JButton("Sửa");
	private JButton btnXoaHD = new JButton("Xóa");

	DecimalFormat formatter = new DecimalFormat("###,### VND");

	private ChuyenTau_DAO sp_dao = new ChuyenTau_DAO();
	private KhachHang_DAO kh_dao = new KhachHang_DAO();
	private NhanVien_DAO nv_dao = new NhanVien_DAO();
	private Ve_DAO hd_dao = new Ve_DAO();
	private ChiTietVe_DAO cthd_dao = new ChiTietVe_DAO();
//	private  UI_KhachHang kh_iu= new UI_KhachHang(null);
	ArrayList<ChiTietVe> dsSP = new ArrayList<ChiTietVe>();
	ChuyenTau spN;
	JTable tb;
	DefaultTableModel model;
	private double TongTien = 0.0;
	private double TienThoi = 0.0;
	private double TienKhachDua = 0.0;

	private DefaultTableModel modelHoaDon;
	private JTable tblHoaDon;
	private ArrayList<entity.Ve> dsHD;
	private DefaultTableModel modedCTHoaDon;
	private JTable tblCTHoaDon;

	private JComboBox<String> cmbTimHoaDon;
	private JButton btnXoaRongHD;
	private JLabel lblTienKhachDua;
	private JLabel lblTienThoi;
	private JLabel lblTongTienThoi;
	private JTextField txtTienKhachDua;

	private static User userCurr;

	public UI_Ve(User user) throws IOException, SQLException {
		// TODO Auto-generated constructor stub
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMinimumSize(new Dimension(1350, 700));
		setExtendedState(MAXIMIZED_BOTH);
		setTitle("Trang chính");
		UI_Ve.userCurr = user;
		giaoDienChinh1();
//		setMinimumSize(new Dimension(1350, 700));
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	@SuppressWarnings("deprecation")
	private void giaoDienChinh1() throws IOException, SQLException {

		// Menu Tác Vụ Bên Trái
		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Quản Lý Vé"));

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
		// Tài Khoản
		pWest.add(btnImUser = new JButton("", iconUS));
		pWest.add(btnTaiKhoan = new JButton());
		btnTaiKhoan.add(txtTaiKoan = new JTextField());
		btnImUser.setPreferredSize(new Dimension(200, 50));
		btnTaiKhoan.setPreferredSize(new Dimension(200, 50));
		txtTaiKoan.setEditable(false);
		txtTaiKoan.setText(userCurr.getNhanVien().getTenNV());
		btnImUser.setBorderPainted(false);
		btnImUser.setContentAreaFilled(false);
		btnImUser.setFocusPainted(false);
		btnTaiKhoan.setBorderPainted(false);
		btnTaiKhoan.setContentAreaFilled(false);
		btnTaiKhoan.setFocusPainted(false);
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
		// Nút Báo Cáo
		btnBaoCao = new JButton("Thống Kê", iconAna);
		btnBaoCao.setBorder(BorderFactory.createRaisedBevelBorder());
		btnBaoCao.setForeground(Color.black);
		btnBaoCao.setPreferredSize(new Dimension(200, 50));

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

		add(pWest, BorderLayout.WEST);
		int heightCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 5 / 11;

		int widthCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 11;

		pWest.setPreferredSize(new Dimension(widthCenter, heightCenter));

//		Center

		// Image tab
		JTabbedPane tabbedPane = new JTabbedPane();
		BufferedImage imagetag = ImageIO.read(new File("image/invoice.png"));
		ImageIcon icontag = new ImageIcon(imagetag.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		BufferedImage imagetag1 = ImageIO.read(new File("image/invoice.png"));
		ImageIcon icontag1 = new ImageIcon(imagetag1.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		tabbedPane.addTab("Phiếu mua vé", icontag, BanHang(), "Vé");
		if (userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
			tabbedPane.addTab("Vé", icontag1, HoaDon(), "Chi tiết vé ");
		}
//		tabbedPane.addTab("Hoá Đơn", icontag1, HoaDon(), "Chi tiết hóa đơn ");

		add(tabbedPane, BorderLayout.CENTER);

		btnBaoCao.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnHoTro.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnDangXuat.addActionListener(this);

//	
	}

	private Component HoaDon() {

		int indentLeftLbl = 20;
		int heightComponent = 28;
		int widthLbl = 100;
		int topSpacing = 30;

		JPanel pnlHoaDon = new JPanel(null);

		// table

		String[] columnNames = { "Mã Vé", "Tên Khách Hàng", "Tên Nhân Viên", "Ngày Bán", "Tổng Tiền" };
		JPanel pnlTblHoaDon = new JPanel(null);
		pnlTblHoaDon.setBorder(BorderFactory.createTitledBorder("Danh sách vé "));
		pnlTblHoaDon.setBounds(10, 20, 1230, 357);
		modelHoaDon = new DefaultTableModel(columnNames, 0);
		tblHoaDon = new JTable(modelHoaDon);
		tblHoaDon.setBounds(20, 20, 1210, 330);
		JScrollPane srcHoaDon = new JScrollPane(tblHoaDon);
		srcHoaDon.setBounds(10, 20, 1210, 330);
		pnlTblHoaDon.add(srcHoaDon);
		pnlHoaDon.add(pnlTblHoaDon);

		///
		String[] Header1 = { "Mã Vé", "Tên chuyến tàu", " Đơn giá", "Số lượng", "Thành tiền" };
		JPanel pnlChiTietHoaDon = new JPanel(null);
		pnlChiTietHoaDon.setBorder(BorderFactory.createTitledBorder("Bảng chi tiết vé"));
		pnlChiTietHoaDon.setBounds(10, 400, 1230, 260);
		modedCTHoaDon = new DefaultTableModel(Header1, 0);
		tblCTHoaDon = new JTable(modedCTHoaDon);
		tblCTHoaDon.setBounds(20, 20, 1210, 230);
		JScrollPane srcSanPhamPhieuNhap = new JScrollPane(tblCTHoaDon);
		srcSanPhamPhieuNhap.setBounds(10, 20, 1210, 230);
		pnlChiTietHoaDon.add(srcSanPhamPhieuNhap);
		pnlHoaDon.add(pnlChiTietHoaDon);

		///
		int widthBtn = 120;
		int heightBtn = 28;
		int spacingBetweenBtn = 140;

		JPanel pnlControl = new JPanel(null);
		pnlControl.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
		pnlControl.setBounds(10, 680, 1230, 80);
		pnlHoaDon.add(pnlControl);

		JButton btnThemHD = new JButton("Thêm");
		btnThemHD.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
		btnThemHD.setEnabled(false);
		pnlControl.add(btnThemHD);

		btnXoaHD.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnXoaHD);
		btnXoaHD.setEnabled(false);

		btnSuaHD.setBounds(indentLeftLbl + spacingBetweenBtn * 2, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnSuaHD);
		btnSuaHD.setEnabled(false);

		btnXoaRongHD = new JButton("Làm mới");
		btnXoaRongHD.setBounds(indentLeftLbl + spacingBetweenBtn * 3, topSpacing, widthBtn, heightBtn);
		pnlControl.add(btnXoaRongHD);

		int indentTimMa = indentLeftLbl + spacingBetweenBtn * 4;
		int widthCmbTim = 80;
		JLabel lblTimMa = new JLabel("Tìm Theo Mã:");
		lblTimMa.setBounds(indentTimMa, topSpacing, widthLbl, heightComponent);
		cmbTimHoaDon = new JComboBox<String>();
		cmbTimHoaDon.setEditable(true);
		cmbTimHoaDon.setBounds(indentTimMa + widthLbl, topSpacing, widthCmbTim + 70, heightComponent);

		pnlControl.add(lblTimMa);
		pnlControl.add(cmbTimHoaDon);

		btnXoaRongHD.addActionListener(this);
		addDataTableHoaDon();
		cmbTimHoaDon.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblHoaDon.getSelectionModel().clearSelection();
				modelHoaDon.getDataVector().removeAllElements();
				Object item = cmbTimHoaDon.getSelectedItem();
				if (item != null && item != "") {
					Ve h = new Ve_DAO().getHoaDonTheoMaHD(cmbTimHoaDon.getSelectedItem().toString());
					if (h != null) {
						Ve hd = new Ve_DAO().getHoaDonTheoMaHD(cmbTimHoaDon.getSelectedItem().toString());
						modelHoaDon
								.addRow(new Object[] { hd.getMaHD(), kh_dao.getKhachHangTheoMa(hd.getMaKH()).getTenKH(),
										nv_dao.getNhanVienTheoMaNV(hd.getMaNV()).getTenNV(), hd.getNgayBan(),
										formatter.format(hd.getTongTien()) });
					} else {
						tblHoaDon.getSelectionModel().clearSelection();
						modelHoaDon.getDataVector().removeAllElements();
						dsHD = new Ve_DAO().getAllHoaDon();
						for (Ve hd : dsHD) {
							modelHoaDon.addRow(
									new Object[] { hd.getMaHD(), kh_dao.getKhachHangTheoMa(hd.getMaKH()).getTenKH(),
											nv_dao.getNhanVienTheoMaNV(hd.getMaNV()).getTenNV(), hd.getNgayBan(),
											formatter.format(hd.getTongTien()) });
						}
					}
				}
			}

		});

		tblHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {

				if (tblHoaDon.getSelectedRowCount() == 1) {
					int row = tblHoaDon.getSelectedRow();
					String maHD = (String) tblHoaDon.getValueAt(row, 0);
					List<ChiTietVe> chiTietHDs = new ChiTietVe_DAO().getChiTietHDTheoMaHD(maHD);

					tblCTHoaDon.getSelectionModel().clearSelection();
					modedCTHoaDon.getDataVector().removeAllElements();
					modedCTHoaDon.addRow(new Object[] {});
					modedCTHoaDon.removeRow(0);

					for (ChiTietVe chiTietHD : chiTietHDs) {
						ChuyenTau sanPham = new ChuyenTau_DAO().getSanPhamTheoMa(chiTietHD.getMaSP());
						modedCTHoaDon.addRow(new Object[] { chiTietHD.getMaHD(), sanPham.getTenSP(),
								formatter.format(chiTietHD.getDonGia()), chiTietHD.getSoLuong(),
								formatter.format(chiTietHD.getThanhTien()) });
					}
				}
			}
		});

		return pnlHoaDon;

	}

	private void addDataTableHoaDon() {
	    tblHoaDon.getSelectionModel().clearSelection();
	    modelHoaDon.getDataVector().removeAllElements();
	    dsHD = new Ve_DAO().getAllHoaDon();
	    cmbTimHoaDon.removeAllItems();

	    cmbTimHoaDon.addItem("");
	    for (Ve hd : dsHD) {
	        String tenKH = kh_dao.getKhachHangTheoMa(hd.getMaKH()).getTenKH();
	        NhanVien nv = nv_dao.getNhanVienTheoMaNV(hd.getMaNV());
	        String tenNV = (nv != null) ? nv.getTenNV() : "Unknown"; 

	        modelHoaDon.addRow(new Object[] { hd.getMaHD(), tenKH, tenNV, hd.getNgayBan(), formatter.format(hd.getTongTien()) });
	        cmbTimHoaDon.addItem(hd.getMaHD());
	    }
	    AutoCompleteDecorator.decorate(cmbTimHoaDon);
	}


	private Component BanHang() {
		pnlBanHangMain = new JPanel(null);
		
		pnlBanHangMainTop = new JPanel();
		pnlBanHangMainTop.setBounds(0, 5, 1230, 220);
		pnlBanHangMainTop.setBorder(BorderFactory.createTitledBorder("Thông tin chung"));
		pnlBanHangMainTop.setLayout(null);

		// TOP_LEFT
		pnlBanHangMainTop.add(lblMaHDBan = new JLabel("Mã Vé: "));
		lblMaHDBan.setBounds(750, 20, 120, 30);
		pnlBanHangMainTop.add(txtMaHDBan = new JTextField());
		txtMaHDBan.setBounds(850, 20, 350, 30);
		txtMaHDBan.setText(autoMaHD());
		txtMaHDBan.setEditable(false);

		pnlBanHangMainTop.add(lblNgayBan = new JLabel("Ngày Bán: "));
		lblNgayBan.setBounds(750, 70, 120, 30);
		pnlBanHangMainTop.add(txtNgayBan = new JTextField());
		txtNgayBan.setBounds(850, 70, 350, 30);
		txtNgayBan.setText(String.valueOf(LocalDate.now()));
		txtNgayBan.setEditable(false);

		pnlBanHangMainTop.add(lblMaNV = new JLabel("Mã Nhân Viên: "));
		lblMaNV.setBounds(750, 120, 120, 30);
		pnlBanHangMainTop.add(txtMaNV = new JTextField());
		txtMaNV.setBounds(850, 120, 350, 30);
		txtMaNV.setEditable(false);

		pnlBanHangMainTop.add(lblTenNV = new JLabel("Tên Nhân Viên: "));
		lblTenNV.setBounds(750, 170, 120, 30);
		pnlBanHangMainTop.add(txtTenNV = new JTextField());
		txtTenNV.setBounds(850, 170, 350, 30);
		txtTenNV.setEditable(false);
		// TOP_RIGHT
		pnlBanHangMainTop.add(lblMaKH = new JLabel("Mã Khách Hàng: "));
		lblMaKH.setBounds(60, 20, 120, 30);
		pnlBanHangMainTop.add(txtMaKH = new JTextField());
		txtMaKH.setBounds(180, 20, 350, 30);
		txtMaKH.setText(autoMaKH());
		txtMaKH.setEditable(false);

		pnlBanHangMainTop.add(lblTenKH = new JLabel("Tên Khách Hàng: "));
		lblTenKH.setBounds(60, 70, 120, 30);
		pnlBanHangMainTop.add(txtTenKH = new JTextField());
		txtTenKH.setBounds(180, 70, 350, 30);

		pnlBanHangMainTop.add(lblDiaChi = new JLabel("Địa chỉ: "));
		lblDiaChi.setBounds(60, 120, 120, 30);
		pnlBanHangMainTop.add(txtDiaChi = new JTextField());
		txtDiaChi.setBounds(180, 120, 350, 30);

		pnlBanHangMainTop.add(lblDienThoai = new JLabel("Điện Thoại: "));
		lblDienThoai.setBounds(60, 170, 120, 30);
		pnlBanHangMainTop.add(txtDienThoai = new JTextField());
		txtDienThoai.setBounds(180, 170, 350, 30);
		pnlBanHangMainTop.add(btnTimKH = new JButton("Tìm"));
		btnTimKH.setBounds(535, 170, 55, 30);
//												PANEL_CENTER
		pnlBanHangMainCenter = new JPanel(null);
		pnlBanHangMainCenter.setBounds(0, 230, 1230, 130);
		pnlBanHangMainCenter.setBorder(BorderFactory.createTitledBorder("Thông tin chuyến tàu: "));

//		
		pnlBanHangMainCenter.add(lblMaSanPham = new JLabel("Mã chuyến tàu: "));
		lblMaSanPham.setBounds(60, 20, 150, 30);
		pnlBanHangMainCenter.add(txtMaSanPham = new JTextField());
		txtMaSanPham.setBounds(180, 20, 250, 30);
		pnlBanHangMainCenter.add(btnTimKiemMaSP = new JButton("Tìm"));
		btnTimKiemMaSP.setBounds(435, 20, 55, 30);

		pnlBanHangMainCenter.add(lblTenSP = new JLabel("Tên chuyến tàu: "));
		lblTenSP.setBounds(60, 70, 250, 30);
		pnlBanHangMainCenter.add(cmbTenSP = new JComboBox<String>());
		List<ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPham(); // add item vao combobox
		cmbTenSP.addItem(""); // khởi đầu là ""
		for (ChuyenTau sanPham : sanPhams) {
			cmbTenSP.addItem(sanPham.getTenSP());
		}
		AutoCompleteDecorator.decorate(cmbTenSP);
		cmbTenSP.setEditable(true);
		cmbTenSP.setBounds(180, 70, 250, 30);

		pnlBanHangMainCenter.add(lblSoLuong = new JLabel("Số Lượng: "));
		lblSoLuong.setBounds(520, 20, 250, 30);
		pnlBanHangMainCenter.add(txtSoLuong = new JTextField());
		txtSoLuong.setBounds(600, 20, 250, 30);

		pnlBanHangMainCenter.add(lblGiamGia = new JLabel("Giảm giá (%): "));
		lblGiamGia.setBounds(520, 70, 250, 30);
		pnlBanHangMainCenter.add(txtGiamGia = new JTextField());
		txtGiamGia.setText("0.0");
		txtGiamGia.setBounds(600, 70, 250, 30);

		pnlBanHangMainCenter.add(lblDonGia = new JLabel("Đơn giá: "));
		lblDonGia.setBounds(880, 20, 245, 30);
		pnlBanHangMainCenter.add(txtDonGia = new JTextField());
		txtDonGia.setBounds(950, 20, 250, 30);
		txtDonGia.setEditable(false);

//													PANEL_TABLE
		pnlBanHangMainTable = new JPanel(null);
		pnlBanHangMainTable.setBounds(0, 360, 1230, 220);

		model = new DefaultTableModel(header, 0);
		tb = new JTable(model);
		tb.setBounds(5, 10, 1225, 200);
		pnlBanHangMainTable.add(scr = new JScrollPane(tb));
		scr.setBounds(5, 10, 1225, 200);

		// THÀNH TIỀN
		pnlThanhTien = new JPanel(null);
		pnlThanhTien.setBounds(0, 600, 1230, 50);

		JLabel lblTienKhachDua = new JLabel(" Tiền khách đưa");
		lblTienKhachDua.setBounds(10, 10, 200, 30);
		lblTienKhachDua.setFont(new Font("Serif", Font.PLAIN, 20));
		pnlThanhTien.add(lblTienKhachDua);
		txtTienKhachDua = new JTextField();
		txtTienKhachDua.setBounds(170, 10, 250, 30);
		pnlThanhTien.add(txtTienKhachDua);

		pnlThanhTien.add(lblTienThoi = new JLabel("Trả khách:"));
		lblTienThoi.setBounds(460, 10, 300, 25);
		lblTienThoi.setFont(new Font("Serif", Font.PLAIN, 20));
		pnlThanhTien.add(lblTongTienThoi = new JLabel(""));
		lblTongTienThoi.setBounds(550, 9, 350, 25);
		lblTongTienThoi.setText(String.valueOf(formatter.format(0.0)));
		lblTongTienThoi.setForeground(Color.red);
		lblTongTienThoi.setFont(new Font("Serif", Font.PLAIN, 22));

		pnlThanhTien.add(lblThanhTien = new JLabel("Tổng tiền thanh toán:"));
		lblThanhTien.setBounds(800, 10, 300, 25);
		lblThanhTien.setFont(new Font("Serif", Font.PLAIN, 20));
		pnlThanhTien.add(lblTongThanhTien = new JLabel(""));
		lblTongThanhTien.setBounds(990, 9, 350, 25);
		lblTongThanhTien.setText(String.valueOf(formatter.format(0.0)));
		lblTongThanhTien.setForeground(Color.red);
		lblTongThanhTien.setFont(new Font("Serif", Font.PLAIN, 22));

//													PANEL_CTRL\

		int widthBtn = 120;
		int heightBtn = 28;
		int spacingBetweenBtn = 140;
		int indentLeftLbl = 20;
		int topSpacing = 30;
		pnlBanHangMainConTrol = new JPanel();
		pnlBanHangMainConTrol.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
		pnlBanHangMainConTrol.setBounds(10, 680, 1230, 80);
		pnlBanHangMain.add(pnlBanHangMainConTrol);

		btnLuuTT = new JButton("Thêm");
		btnLuuTT.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
		pnlBanHangMainConTrol.add(btnLuuTT);

		btnXoaSP = new JButton("Xóa chuyến tàu");
		btnXoaSP.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
		pnlBanHangMainConTrol.add(btnXoaSP);

		btnSuaSP = new JButton("Sửa chuyến tàu");
		btnSuaSP.setBounds(indentLeftLbl + spacingBetweenBtn * 2, topSpacing, widthBtn, heightBtn);
		pnlBanHangMainConTrol.add(btnSuaSP);

		btnXoaRong = new JButton("Làm mới");
		btnXoaRong.setBounds(indentLeftLbl + spacingBetweenBtn * 3, topSpacing, widthBtn, heightBtn);
		pnlBanHangMainConTrol.add(btnXoaRong);

		btnInHD = new JButton("In vé");
		btnInHD.setBounds(indentLeftLbl + spacingBetweenBtn * 5, topSpacing, widthBtn, heightBtn);
		pnlBanHangMainConTrol.add(btnInHD);

		btnLuuHD = new JButton("Thanh toán");
		btnInHD.setBounds(indentLeftLbl + spacingBetweenBtn * 6, topSpacing, widthBtn, heightBtn);
		pnlBanHangMainConTrol.add(btnLuuHD);

		pnlBanHangMain.add(pnlBanHangMainTop);
		pnlBanHangMain.add(pnlBanHangMainCenter);
		pnlBanHangMain.add(pnlBanHangMainTable);
		pnlBanHangMain.add(pnlThanhTien);
		pnlBanHangMain.add(pnlBanHangMainConTrol);

		btnLuuHD.addActionListener(this);
		btnLuuTT.addActionListener(this);
		btnTimKiemMaSP.addActionListener(this);
		btnXoaSP.addActionListener(this);
		btnSuaSP.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnTimKH.addActionListener(this);
		btnInHD.addActionListener(this);
		tb.addMouseListener(this);

		txtMaNV.setText(userCurr.getNhanVien().getMaNV()); // set ma nv cua user
		txtTenNV.setText(userCurr.getNhanVien().getTenNV()); // set ma nv cua user

		cmbTenSP.addActionListener(new ActionListener() { // event diền thông tin vào ô nhập liệu bằng tên

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String ten = (String) cmbTenSP.getSelectedItem(); // khi có tên trong cmbTen
					ChuyenTau sanPhamSelected = new ChuyenTau_DAO().getSanPhamTheoTenFirst(ten);

					txtMaSanPham.setText(sanPhamSelected.getMaSP());
					txtDonGia.setText(String.valueOf(formatter.format(sanPhamSelected.getGiaBan())));

					txtGiamGia.setText("0.0");
					txtSoLuong.setText("1");
				} catch (Exception e2) {
					txtMaSanPham.setText(""); // "" trong cmbTen

				}

			}

		});

		return pnlBanHangMain;
	}

	public void XoaRong() {
		int temp = JOptionPane.showConfirmDialog(this, "Xóa tất cả thông tin về vé ?", "Cảnh báo",
				JOptionPane.YES_NO_CANCEL_OPTION);
		if (temp == JOptionPane.YES_OPTION) {
			txtMaSanPham.setText("");
			txtSoLuong.setText("");
			txtGiamGia.setText("");
			cmbTenSP.setSelectedItem("");
			txtDonGia.setText("");
			txtMaKH.setText(autoMaKH());
			txtMaHDBan.setText(autoMaHD());
			txtDiaChi.setText("");
			txtDienThoai.setText("");
			txtTenKH.setText("");
			txtTenKH.setEditable(true);
			txtDiaChi.setEditable(true);
			lblTongThanhTien.setText(String.valueOf(formatter.format(0.0)));

			model.setRowCount(0);
			dsSP.removeAll(dsSP);

		}
	}

	public String autoMaHD() {
		String str;
		int ma = hd_dao.getMaHd();
		if (ma < 10)
			str = "HD0000" + ma;
		else if (ma < 100)
			str = "HD000" + ma;
		else if (ma < 1000)
			str = "HD00" + ma;
		else if (ma < 10000)
			str = "HD0" + ma;
		else
			str = "HD" + ma;
		return str;

	}

	public String autoMaKH() {
		String str;
		int ma = kh_dao.getMaKH();
		if (ma < 10)
			str = "KH0000" + ma;
		else if (ma < 100)
			str = "KH000" + ma;
		else if (ma < 1000)
			str = "KH00" + ma;
		else if (ma < 10000)
			str = "KH0" + ma;
		else
			str = "KH" + ma;
		return str;
	}

	public boolean KiemTraTTC() {

		if (txtTenKH.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tên khách hàng không được bỏ trống");
			txtTenKH.requestFocus();
			return false;
		} else if (!txtTenKH.getText().trim().matches("([A-Z]{1}[A-Za-z']{1,}[ ]{0,1}){1,}")) {
			JOptionPane.showMessageDialog(this,
					"Tên khách hàng chữ cái đầu viết in hoa, các chữ cái sau viết thường và không được chứa số");
			txtTenKH.requestFocus();
			txtTenKH.selectAll();
			return false;
		} else if (txtDiaChi.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được bỏ trống");
			txtDiaChi.requestFocus();
			return false;
		} else if (txtTienKhachDua.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Tiền khách đưa không được bỏ trống");
			txtTienKhachDua.requestFocus();
			return false;
		} else if (Double.valueOf(txtTienKhachDua.getText())<TongTien) {
			JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ thanh toán");
			txtTienKhachDua.requestFocus();
			return false;
		}

		else if (txtDienThoai.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không được bỏ trống");
			txtDienThoai.requestFocus();
			return false;
		} else if (!txtDienThoai.getText().trim().matches("[0][1-9][0-9]{8}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại gồm 10 số và đầu số là 0 ");
			txtDienThoai.requestFocus();
			txtDienThoai.selectAll();
			return false;
		} else if (dsSP.size() <= 0) {
			JOptionPane.showMessageDialog(this, "Thêm ít nhất 1 chuyến tàu vào vé");
			return false;
		}
		return true;
	}

	public boolean KiemTraTTSP() {
		if (txtMaSanPham.equals("")) {
			JOptionPane.showMessageDialog(this, "Mã chuyến tàu không được để trống");
			txtMaSanPham.requestFocus();
			return false;
		} else if (txtSoLuong.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Số lượng không được để trống");
			txtSoLuong.requestFocus();
			return false;
		} else if (!txtSoLuong.getText().trim().matches("[0-9]{1,}")) {
			JOptionPane.showMessageDialog(this, "Số lượng chỉ được nhập số nguyên");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		} else if (Integer.parseInt(txtSoLuong.getText().trim()) <= 0) {
			JOptionPane.showMessageDialog(this, "Số Lượng phải lớn hơn 0");
			txtSoLuong.requestFocus();
			txtSoLuong.selectAll();
			return false;
		} else if (txtGiamGia.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Giảm giá không được để trống");
			txtGiamGia.requestFocus();
			return false;
		} else if (Double.parseDouble(txtGiamGia.getText().trim()) < 0
				|| Double.parseDouble(txtGiamGia.getText().trim()) > 100) {
			JOptionPane.showMessageDialog(this, "Không giảm giá thấp hơn 0 và lớn hơn 100");
			txtGiamGia.requestFocus();
			txtGiamGia.selectAll();
			return false;
		}
		return true;
	}
	// *************************************************************************************************************************************
//		Phần In Vé
	//	
// *************************************************************************************************************************************

	public PageFormat getPageFormat(PrinterJob pj) {

		PageFormat pf = pj.defaultPage();
		Paper paper = pf.getPaper();

		double bodyHeight = bHeight;
		double headerHeight = 5.0;
		double footerHeight = 5.0;
		double width = cm_to_pp(50);
		double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
		paper.setSize(width, height);
		paper.setImageableArea(0, 10, width, height - cm_to_pp(1));

		pf.setOrientation(PageFormat.PORTRAIT);
		pf.setPaper(paper);

		return pf;
	}

	protected static double cm_to_pp(double cm) {
		return toPPI(cm * 0.393600787);
	}

	protected static double toPPI(double inch) {
		return inch * 722d;
	}


	Double totalAmount = 0.0;
	Double cash = 0.0;
	Double balance = 0.0;
	Double bHeight = 0.0;

	ArrayList<String> itemName = new ArrayList<>();
	ArrayList<String> quantity = new ArrayList<>();
	ArrayList<String> itemPrice = new ArrayList<>();
	ArrayList<String> subtotal = new ArrayList<>();

	public class BillPrintable implements Printable {

		public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

			int r = itemName.size();
			ImageIcon icon = new ImageIcon("image/logo.jpg");
			int result = NO_SUCH_PAGE;
			if (pageIndex == 0) {

				Graphics2D g2d = (Graphics2D) graphics;
				double width = pageFormat.getImageableWidth();
				g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());

				FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));

				try {
					int y = 20;
					int yShift = 10;
					int headerRectHeight = 15;
					g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
					g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);
					y += yShift + 30;
					g2d.drawString("-------------------------------------", 12, y);
					y += yShift;
					g2d.drawString("     Nhà Ga Puppi              ", 12, y);
					y += yShift;
					g2d.drawString("Đồng hành cùng bạn trên mọi chuyến đi  ", 12, y);
					y += yShift;
					g2d.drawString("Địa chỉ: 12 Phạm Văn Chiêu,P.12, Gò Vấp,TP.HCM ", 12, y);
					y += yShift;
					g2d.drawString("Fanpage: www.facebook.com/Puppi ", 12, y);
					y += yShift;
					g2d.drawString("Liên hệ: +8446829111      ", 12, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 12, y);
					y += headerRectHeight;
					g2d.drawString("Mã vé:     "+txtMaHDBan.getText(), 12, y);
					y += yShift;
					g2d.drawString("Ngày bán:       "+txtNgayBan.getText(), 12, y);
					y += yShift;
					g2d.drawString("Nhân viên bán hàng:   "+txtTenNV.getText(), 12, y);
					y += headerRectHeight;
					g2d.drawString("Họ tên khách hàng:   "+txtTenKH.getText(), 12, y);
					y += headerRectHeight;
					g2d.drawString("Mã CT / Tên CT / SL / Giá                     ", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += headerRectHeight;
					for (int j = 0; j < tb.getRowCount(); j++) {
						for (int k = 0; k < tb.getColumnCount(); k++) {
							g2d.drawString(" " + model.getValueAt(j, k) + "\t" + "                            ", 10, y);
							y += yShift;
						}
						g2d.drawString("\n", 0, y);
						y += yShift;
						y += headerRectHeight;
					}
					g2d.drawString("", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Tổng tiền:               " + lblTongThanhTien.getText() + "   ", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Khách đưa:               " + txtTienKhachDua.getText() + "   ", 10, y);
					y += yShift;
					g2d.drawString("-------------------------------------", 10, y);
					y += yShift;
					g2d.drawString(" Tiền thừa:               " + lblTongTienThoi.getText() + "   ", 10, y);
					y += yShift;
					g2d.drawString("*************************************", 10, y);
					y += yShift;
					g2d.drawString("       THANK YOU & COME AGAIN            ", 10, y);
					y += yShift;
					g2d.drawString("*************************************", 10, y);
					y += yShift;
					g2d.drawString("       SOFTWARE BY : NHOM 12         ", 10, y);
					y += yShift;
					g2d.drawString("   CONTACT: 0346829111       ", 10, y);
					y += yShift;

				} catch (Exception e) {
					e.printStackTrace();
				}

				result = PAGE_EXISTS;
			}
			return result;
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		new UI_Ve(userCurr);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sou = e.getSource();
		if (sou.equals(btnTimKiemMaSP)) {
			spN = sp_dao.getSanPhamTheoMa(txtMaSanPham.getText().trim().toUpperCase());
			cmbTenSP.setSelectedItem(spN.getTenSP());
			txtDonGia.setText(String.valueOf(formatter.format(spN.getGiaBan())));
			txtSoLuong.setText("1");
			txtGiamGia.setText("0.0");
		} else if (sou.equals(btnLuuTT)) {
			if (KiemTraTTSP()) {
				spN = sp_dao.getSanPhamTheoMa(txtMaSanPham.getText().trim().toUpperCase());

				if (spN.getTonKho() <= 0) {
					JOptionPane.showMessageDialog(this, "Chuyến tàu đã hết vé!!");
					return;
				}
				for (ChiTietVe hd2 : dsSP) {
					if (hd2.getMaSP().equalsIgnoreCase(spN.getMaSP())) {
						JOptionPane.showMessageDialog(this, "Chuyến tàu đã tồn tại");
						return;
					}
				}
				int sl = Integer.parseInt(txtSoLuong.getText());
				double dongia = spN.getGiaBan();
				double giamgia = sl * dongia * Double.parseDouble(txtGiamGia.getText()) * 1.0 / 100;
				ChiTietVe hd = new ChiTietVe(txtMaHDBan.getText().toUpperCase(),
						txtMaSanPham.getText().trim().toUpperCase(), sl, dongia, giamgia, sl * dongia - giamgia);
				dsSP.add(hd);
				model.addRow(new Object[] { spN.getMaSP(), spN.getTenSP(), sl, formatter.format(dongia),
						formatter.format(giamgia), formatter.format((sl * dongia - giamgia)) });
				TongTien += sl * dongia - giamgia;
				lblTongThanhTien.setText(String.valueOf(formatter.format(TongTien)));
				txtMaSanPham.setText("");
				txtSoLuong.setText("");
				txtGiamGia.setText("");
				cmbTenSP.setSelectedItem("");
				txtDonGia.setText("");
			}
		} else if (sou.equals(btnXoaSP)) {
			int row = tb.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần Xóa");
			} else {
				spN = sp_dao.getSanPhamTheoMa(txtMaSanPham.getText().trim().toUpperCase());
				for (ChiTietVe hd3 : dsSP) {
					if (hd3.getMaSP().equalsIgnoreCase(spN.getMaSP()))
						if (dsSP.remove(hd3)) {
							model.removeRow(tb.getSelectedRow());
							txtMaSanPham.setText("");
							txtSoLuong.setText("");
							txtGiamGia.setText("");
							cmbTenSP.setSelectedItem("");
							txtDonGia.setText("");
							TongTien = TongTien - hd3.getThanhTien();
							lblTongThanhTien.setText(String.valueOf(formatter.format(TongTien)));
							JOptionPane.showMessageDialog(this, "Xóa chuyến tàu thành công");
							return;
						}
				}
			}
		} else if (sou.equals(btnSuaSP)) {
			int row = tb.getSelectedRow();
			if (row < 0) {
				JOptionPane.showMessageDialog(this, "Chọn dòng cần sửa");
			} else {
				if (KiemTraTTSP()) {
					spN = sp_dao.getSanPhamTheoMa(String.valueOf(model.getValueAt(row, 0)));
					if (String.valueOf(model.getValueAt(row, 0)).equalsIgnoreCase(txtMaSanPham.getText())) {
						for (ChiTietVe hd2 : dsSP) {
							if (hd2.getMaSP().equalsIgnoreCase(spN.getMaSP())) {
								int sl = Integer.parseInt(txtSoLuong.getText());
								double dongia = spN.getGiaBan();
								double giamgia = sl * dongia * Double.parseDouble(txtGiamGia.getText()) * 1.0 / 100;
								TongTien += sl * dongia - giamgia - hd2.getSoLuong() * dongia;
								lblTongThanhTien.setText(String.valueOf(formatter.format(TongTien)));
								hd2.setGiamGia(giamgia);
								hd2.setSoLuong(sl);
								model.setValueAt(sl, row, 2);
								model.setValueAt(String.valueOf(formatter.format(giamgia)), row, 4);
								model.setValueAt(String.valueOf(formatter.format((sl * dongia - giamgia))), row, 5);
								txtMaSanPham.setText("");
								txtSoLuong.setText("");
								txtGiamGia.setText("");
								cmbTenSP.setSelectedItem("");
								txtDonGia.setText("");
							}
						}
					} else {
						JOptionPane.showMessageDialog(this, "Không Được thay đổi mã chuyến tàu");
						txtMaSanPham.selectAll();
						txtMaSanPham.requestFocus();
					}
				}
			}
		} else if (sou.equals(btnXoaRong)) {
			XoaRong();
		} else if (sou.equals(btnInHD)) {
			bHeight = Double.valueOf(itemName.size());
// Inbill
			PrinterJob pj = PrinterJob.getPrinterJob();
			pj.setPrintable(new BillPrintable(), getPageFormat(pj));
			try {
				pj.print();
				KhachHang kh;
				kh = new KhachHang(txtTenKH.getText(), txtMaKH.getText(), txtDienThoai.getText(), txtDiaChi.getText());
				 kh_dao.create(kh);
			} catch (PrinterException ex) {
				ex.printStackTrace();
			}

		} else if (sou.equals(btnTimKH)) {
			KhachHang kh = null;
			kh = kh_dao.getKhachHangTheoSDT(txtDienThoai.getText());
			if (kh == null) {
				txtTenKH.setText("");
				txtTenKH.setEditable(true);
				txtDiaChi.setText("");
				txtDiaChi.setEditable(true);
				JOptionPane.showMessageDialog(this, "Số Điện thoại chưa tồn tại trong danh sách");
			} else {
				txtMaKH.setText(kh.getMaKH());
				txtTenKH.setText(kh.getTenKH());
				txtTenKH.setEditable(false);
				txtDiaChi.setText(kh.getDiaChi());
				txtDiaChi.setEditable(false);

			}
		} else if (sou.equals(btnLuuHD)) {
		    if (KiemTraTTC()) {
		        KhachHang kh;
		        Ve hd;
		        kh = new KhachHang(txtTenKH.getText(), txtMaKH.getText(), txtDienThoai.getText(), txtDiaChi.getText());
		        hd = new Ve(txtMaHDBan.getText(), txtMaNV.getText(), txtMaKH.getText(), Date.valueOf(txtNgayBan.getText()), TongTien);
		        
		        if (kh_dao.getKhachHangTheoMa(txtMaKH.getText()) == null) {
		            kh_dao.create(kh);
		        }
		        
		        hd_dao.create(hd);
		        
		        for (ChiTietVe cthd : dsSP) {
		            cthd_dao.create(cthd);
		            sp_dao.updateSL(sp_dao.getSanPhamTheoMa(cthd.getMaSP()), cthd);
		        }
		        
		        JOptionPane.showMessageDialog(this, "Lưu vé thành công");
		        
		        TienThoi = Double.valueOf(txtTienKhachDua.getText()) - TongTien;
		        lblTongTienThoi.setText(String.valueOf(formatter.format(TienThoi)));
		        TienKhachDua = Double.valueOf(txtTienKhachDua.getText());
		        txtTienKhachDua.setText(String.valueOf(formatter.format(TienKhachDua)));
		        
		        int temp = JOptionPane.showConfirmDialog(this, "Bạn có muốn in vé ?", "Thông Báo", JOptionPane.YES_NO_CANCEL_OPTION);
		        if (temp == JOptionPane.YES_OPTION) {
		            PrinterJob pj = PrinterJob.getPrinterJob();
		            pj.setPrintable(new BillPrintable(), getPageFormat(pj));
		            try {
		                pj.print();
		            } catch (PrinterException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }


		} else if (sou.equals(btnXoaRongHD)) {
		    try {
		        frmHoaDon = new UI_Ve(userCurr);
		    } catch (IOException e1) {
		        e1.printStackTrace();
		    } catch (SQLException e1) {
		        e1.printStackTrace();
		    }
		    this.dispose();
		    tblHoaDon.getSelectionModel().clearSelection();
		    modelHoaDon.getDataVector().removeAllElements();

		    if (modelHoaDon.getRowCount() < 2) {
		        dsHD = new Ve_DAO().getAllHoaDon();
		        for (Ve hd : dsHD) {
		            String tenKH = kh_dao.getKhachHangTheoMa(hd.getMaKH()).getTenKH();
		            NhanVien nv = nv_dao.getNhanVienTheoMaNV(hd.getMaNV());
		            String tenNV = (nv != null) ? nv.getTenNV() : "Unknown"; // Kiểm tra null và gán giá trị phù hợp

		            modelHoaDon.addRow(new Object[] { hd.getMaHD(), tenKH, tenNV, hd.getNgayBan(), formatter.format(hd.getTongTien()) });
		        }
		    }
		} else if (sou.equals(btnHoaDon)) {
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
			String url = "https://www.google.com.vn/";
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = tb.getSelectedRow();
		txtMaSanPham.setText(String.valueOf(model.getValueAt(row, 0)));
		txtSoLuong.setText(String.valueOf(model.getValueAt(row, 2)));
		spN = sp_dao.getSanPhamTheoMa(txtMaSanPham.getText().trim().toUpperCase());
		cmbTenSP.setSelectedItem(spN.getTenSP());
		double giamgia = spN.getGiaBan() * Integer.parseInt(String.valueOf(model.getValueAt(row, 2)));
		for (ChiTietVe hd : dsSP) {
			if (hd.getMaSP().equalsIgnoreCase(spN.getMaSP())) {
				giamgia = (hd.getGiamGia() / giamgia) * 100;
				break;
			}
		}
		txtDonGia.setText(String.valueOf(formatter.format(spN.getGiaBan())));

		txtGiamGia.setText(String.valueOf(giamgia));
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

}
