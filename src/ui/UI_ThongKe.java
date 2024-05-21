//1.	Nguyễn Quý Khả - 19530291 (Leader)-
//4.	Trần Thị Anh Thư - 19516531 (Note taker)
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Font;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.calendar.DatePickerFormatter;
import org.jdesktop.swingx.painter.AbstractLayoutPainter.HorizontalAlignment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.general.SeriesException;
import org.jfree.data.jdbc.JDBCCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Hour;
import org.jfree.data.time.Minute;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Section;
import com.itextpdf.text.xml.simpleparser.NewLineHandler;
import com.toedter.calendar.JDateChooser;

import connectDB.ConnectDB;
import dao.ChiTietVe_DAO;
import dao.ChiTietPhieuNhap_DAO;
import dao.ChucVu_Dao;
import dao.Ve_DAO;
import dao.KhachHang_DAO;
import dao.Loai_DAO;
import dao.NhaGa_DAO;
import dao.NhanVien_DAO;
import dao.ChuyenTau_DAO;
import entity.ChiTietVe;
import entity.ChiTietPhieuNhap;
import entity.Ve;
import entity.Loai;
import entity.NhaGa;
import entity.NhanVien;
import entity.ChuyenTau;
import entity.User;

public class UI_ThongKe extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btnHoaDon, btnSanPham, btnKhachHang, btnNhanVien, btnBaoCao, btnHoTro, btnDangXuat;
	JTable tblDT;
	UI_ThongKe frmBanHang;
	UI_Ve frmHoaDon;
	UI_ChuyenTau frmSanPham;
	UI_KhachHang frmKhachHang;
	UI_NhanVien frmNhanVien;
	UI_ThongKe frmThongKe;
	UI_DangNhap frmDangNhap;
	private DefaultTableModel modelDT, modelSP, modelNV, modelTQ;

	private JTable tblSanPham, tblNhanVien, tblTQ;

	private JDateChooser tuNgayChooser, tuNgayChooser1, tuNgayChooser2;

	private JLabel lblTuNgay, lblTuNgay2, lblDTHomNay, lblDTThang;

	private JLabel lblDenNgay, lblDenNgay2;

	private JDateChooser denNgayChooser, denNgayChooser1, denNgayChooser2;

	private JLabel lblTongDoanhThu, lblDTTheoThang;

	private JLabel lblTinhTongDoanhThu, lblTinhTongSoLuongTon, lblNCC, lblTongSoVon, lblTinhTongSoVon, lblTongSPBan;
	private JLabel lblLoai, lblLoaiDT, lblLoaiDoanhThu, lblTongDoanhThu2;

	private JLabel lblTongSoLuongBan, lblTongSoLuongTon, lblTongLoaiDT, lblTinhTongDoanhThu2, lblTinhTongSPBan,
			lblTinhTongDTHN, lblTinhTongDTTheoThang, lblTinhTongDTTB, lblTinhTongDTTBTheoThang;

	private JComboBox<String> cbcLoai, cbcNCC, cbcLoaiDoanhThu;
	private JLabel lblTinhTongSoLuongBan;
	DatePickerFormatter formatter2 = new DatePickerFormatter();
	DefaultTableCellRenderer CenterRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer RightRenderer = new DefaultTableCellRenderer();
	DefaultTableCellRenderer LeftRenderer = new DefaultTableCellRenderer();
	private Loai_DAO l_dao = new Loai_DAO();
	private ChuyenTau_DAO sp_dao = new ChuyenTau_DAO();
	private NhaGa_DAO ncc_dao = new NhaGa_DAO();
	private Ve_DAO hoadon_dao = new Ve_DAO();
	private ChucVu_Dao cv_dao = new ChucVu_Dao();
	DecimalFormat formatter = new DecimalFormat("###,###,### VND");
	private JButton btnHienThiDoanhThu, btnHienThiSP, btnHienThiDoanhThuNV;
	private JButton btnBieudocot,btnBieudoLine,btnBieudoTron;
	MessageFormat header;

//	Date tuNgay= tuNgayChooser.getDate();
//	Date denNgay= denNgayChooser.getDate();
	private static User userCurr;

	public UI_ThongKe(User userCurr) throws IOException, SQLException {
		// TODO Auto-generated constructor stub
		setMinimumSize(new Dimension(1350, 700));
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Thống kê");
		UI_ThongKe.userCurr = userCurr;
		giaoDienChinh1();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void giaoDienChinh1() throws IOException, SQLException {

		// Menu Tác Vụ Bên Trái
		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), "Thống Kê"));

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
		JTextField txtTaiKoan;
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
		pWest.add(btnNhanVien = new JButton("Nhân Viên", iconstaff));
		btnNhanVien.setBorder(BorderFactory.createRaisedBevelBorder());
		btnNhanVien.setForeground(Color.black);
		btnNhanVien.setPreferredSize(new Dimension(200, 50));
		pWest.add(Box.createVerticalStrut(65));

		// Nút Thống Kê
		pWest.add(btnBaoCao = new JButton("Thống Kê", iconAna));
		btnBaoCao.setBorder(BorderFactory.createRaisedBevelBorder());
		btnBaoCao.setForeground(Color.black);
		btnBaoCao.setPreferredSize(new Dimension(200, 50));
		pWest.add(Box.createVerticalStrut(65));

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

//		if (!userCurr.getChucNang().getTenChucVu().equals("Quan Ly")) {
//			btnNhanVien.setEnabled(false);
//			btnBaoCao.setEnabled(false);
//		}

		add(pWest, BorderLayout.WEST);
		int heightCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 5 / 11;

		int widthCenter = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 2 / 11;

		pWest.setPreferredSize(new Dimension(widthCenter, heightCenter));

//		Center

		// Image
		JTabbedPane tabbedPane = new JTabbedPane();
		BufferedImage imagetag = ImageIO.read(new File("image/ana.png"));
		ImageIcon icontag = new ImageIcon(imagetag.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		BufferedImage imagetag1 = ImageIO.read(new File("image/ana.png"));
		ImageIcon icontag1 = new ImageIcon(imagetag1.getScaledInstance(15, 15, Image.SCALE_SMOOTH));

		// Tab
		tabbedPane.addTab("Tổng Hợp", icontag1, TongQuan(), "Tổng Quan");
		tabbedPane.addTab("Thống Kê Chuyến Tàu", icontag, SanPham(), "Danh Sách Chuyến tàu");
		tabbedPane.addTab("Thống Kê Doanh Thu", icontag1, DoanhThu(), "Danh Sách Doanh Thu");
		tabbedPane.addTab("Thống Kê Nhân Viên", icontag1, TabNhanVien(), "Danh Sách Nhân Viên");
		add(tabbedPane, BorderLayout.CENTER);

		// Sự kiện
		btnBaoCao.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnHoTro.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnDangXuat.addActionListener(this);

	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Tình Trạng Chuyến Tàu", dataset, true, true, true);
		return chart;
	}

	private static PieDataset createDataset() {
		double cnt = 100;
		double ban = 0;
		DefaultPieDataset dataset = new DefaultPieDataset();
		ArrayList<entity.ChuyenTau> sanPhams = new ChuyenTau_DAO().getAllSanPham();

		ArrayList<ChuyenTau> dsctpn = new ChuyenTau_DAO().getAllSanPham();
		for (ChuyenTau ct : dsctpn) {
			ban = (ct.getTonKho()) * 0.1;
			dataset.setValue("Vé còn", cnt - ban);
			dataset.setValue("Đã bán", ban);
		}
		return dataset;
	}

	private XYDataset createDataset1() {

		final TimeSeries series = new TimeSeries("Random Data");
		Day current = new Day();
		double value = 0;
		try {
			ArrayList<Ve> dshd = hoadon_dao.getAllHoaDon();
			for (Ve p : dshd) {
				for (int i = 0; i < p.getTongTien(); i++)
					value = p.getTongTien();
				current = (Day) current.next();
				series.add(current, new Double(value));
			}
		} catch (SeriesException e) {
			System.err.println("Error adding to series");

		}
		return new TimeSeriesCollection(series);

	}

	/**
	 * Creates a sample chart.
	 * 
	 * @param dataset the dataset.
	 * 
	 * @return A sample chart.
	 */
	private JFreeChart createChart(final XYDataset dataset) {
		return ChartFactory.createTimeSeriesChart("Doanh thu theo ngày", "Ngày", "Doanh thu", dataset, false, false,
				false);
	}

	private Component TongQuan() {

		JPanel pnlTongQuan = new JPanel(null);
		JPanel pnlDTHomNay = new JPanel(null);

		pnlDTHomNay.setBounds(10, 20, 150, 150);
//		
		Border titleBorder;
		Border blueBorder = BorderFactory.createLineBorder(Color.BLUE);
		titleBorder = BorderFactory.createTitledBorder(blueBorder, "Hôm nay (DT)", TitledBorder.LEFT,
				TitledBorder.BELOW_TOP);

		pnlDTHomNay.setBorder(titleBorder);
		lblDTHomNay = new JLabel();
		try {
			lblDTHomNay.setText(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblDTHomNay.setBounds(100, 20, 100, 20);
		pnlDTHomNay.add(lblDTHomNay);

		lblTinhTongDTHN = new JLabel();
		lblTinhTongDTHN.setText("0");
		lblTinhTongDTHN.setForeground(Color.RED);
		lblTinhTongDTHN.setBounds(30, 50, 600, 50);

		pnlDTHomNay.add(lblTinhTongDTHN);

		//
		JPanel pnlDTThang = new JPanel(null);
		pnlDTThang.setBounds(180, 20, 150, 150);
//			
		Border titleBorder1;
		Border blueBorder1 = BorderFactory.createLineBorder(Color.BLUE);
		titleBorder1 = BorderFactory.createTitledBorder(blueBorder1, "Tháng này (DT)", TitledBorder.LEFT,
				TitledBorder.BELOW_TOP);

		pnlDTThang.setBorder(titleBorder1);
		lblDTThang = new JLabel();
		try {
			lblDTThang.setText(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblDTThang.setBounds(100, 20, 100, 20);
		pnlDTThang.add(lblDTThang);

		lblTinhTongDTTheoThang = new JLabel();
		lblTinhTongDTTheoThang.setText("0");
		lblTinhTongDTTheoThang.setForeground(Color.RED);
		lblTinhTongDTTheoThang.setBounds(30, 50, 600, 50);

		pnlDTThang.add(lblTinhTongDTTheoThang);

		///
		JPanel pnlBieuDoSP = new JPanel();
		pnlBieuDoSP.setBounds(350, 20, 300, 300);
		JFreeChart pieChart = createChart(createDataset());

		ChartPanel chartPanel = new ChartPanel(pieChart);

		pnlBieuDoSP.add(chartPanel);
		pnlBieuDoSP.setLayout((LayoutManager) new java.awt.BorderLayout());

		pnlBieuDoSP.add(chartPanel, BorderLayout.CENTER);
		pnlBieuDoSP.validate();

		////
		JPanel pnlBieuDoDT = new JPanel();
		pnlBieuDoDT.setBounds(10, 350, 640, 300);
		final XYDataset dataset = createDataset1();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel1 = new ChartPanel(chart);
		chartPanel1.setPreferredSize(new java.awt.Dimension(450, 250));
		pnlBieuDoDT.add(chartPanel1);
		pnlBieuDoDT.setLayout((LayoutManager) new java.awt.BorderLayout());

		pnlBieuDoDT.add(chartPanel1, BorderLayout.CENTER);
		pnlBieuDoDT.validate();

		////
		JPanel pnlBQThang = new JPanel(null);
		pnlBQThang.setBounds(10, 170, 150, 150);
//					
		Border titleBorder2;
		Border blueBorder2 = BorderFactory.createLineBorder(Color.BLUE);
		titleBorder2 = BorderFactory.createTitledBorder(blueBorder2, "Bình Quân (DT)", TitledBorder.LEFT,
				TitledBorder.BELOW_TOP);

		pnlBQThang.setBorder(titleBorder2);
		lblDTThang = new JLabel();
		try {
			lblDTThang.setText(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblDTThang.setBounds(100, 20, 100, 20);
		pnlBQThang.add(lblDTThang);

		lblTinhTongDTTB = new JLabel();
		lblTinhTongDTTB.setText("0");
		lblTinhTongDTTB.setForeground(Color.RED);
		lblTinhTongDTTB.setBounds(30, 50, 600, 50);

		pnlBQThang.add(lblTinhTongDTTB);

		//
		JPanel pnlBQTheoThang = new JPanel(null);
		pnlBQTheoThang.setBounds(180, 170, 150, 150);
//					
		Border titleBorder3;
		Border blueBorder3 = BorderFactory.createLineBorder(Color.BLUE);
		titleBorder3 = BorderFactory.createTitledBorder(blueBorder3, "Bình Quân Tháng (DT)", TitledBorder.LEFT,
				TitledBorder.BELOW_TOP);

		pnlBQTheoThang.setBorder(titleBorder3);
		lblDTTheoThang = new JLabel();
		try {
			lblDTTheoThang.setText(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblDTTheoThang.setBounds(100, 20, 100, 20);
		pnlBQTheoThang.add(lblDTTheoThang);

		lblTinhTongDTTBTheoThang = new JLabel();
		lblTinhTongDTTBTheoThang.setText("0");
		lblTinhTongDTTBTheoThang.setForeground(Color.RED);
		lblTinhTongDTTBTheoThang.setBounds(30, 50, 600, 50);

		pnlBQTheoThang.add(lblTinhTongDTTBTheoThang);

//Table
		String[] columnNames = { "Mã Vé", "Khách Hàng", "Chuyến Tàu", "Tổng Thành Tiền" };
		JPanel pnlTblTQ = new JPanel(null);
		pnlTblTQ.setBorder(BorderFactory.createTitledBorder("40 Đơn Hàng Gần Nhất"));
		pnlTblTQ.setBounds(700, 20, 450, 750);
		modelTQ = new DefaultTableModel(columnNames, 0);
		tblTQ = new JTable(modelTQ);
		tblTQ.setBounds(500, 150, 450, 750);
		JScrollPane srcSanPham = new JScrollPane(tblTQ);
		srcSanPham.setBounds(10, 20, 430, 720);
		CenterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		RightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		LeftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		tblTQ.getColumn("Mã Vé").setCellRenderer(CenterRenderer);
		tblTQ.getColumn("Khách Hàng").setCellRenderer(LeftRenderer);
		tblTQ.getColumn("Chuyến Tàu").setCellRenderer(CenterRenderer);
		tblTQ.getColumn("Tổng Thành Tiền").setCellRenderer(CenterRenderer);
		pnlTblTQ.add(srcSanPham);
		pnlTongQuan.add(pnlTblTQ);
		tblTQ.setAutoCreateRowSorter(true);
		try {
			addTblTQuan(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pnlTongQuan.add(pnlDTThang);
		pnlTongQuan.add(pnlDTHomNay);
		pnlTongQuan.add(pnlBieuDoSP);
		pnlTongQuan.add(pnlBieuDoDT);
		pnlTongQuan.add(pnlBQThang);
		pnlTongQuan.add(pnlBQTheoThang);

		try {
			hienThiDTHN(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LocalDate today = LocalDate.now();
		int month = today.getMonthValue();
		hienThiDTTheoThang(month);
		try {
			hienThiDTTB(formatter2.valueToString(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hienThiDTHNTheoThang(month);
		return pnlTongQuan;
	}

	private void hienThiDTHNTheoThang(int month) {
		// TODO Auto-generated method stub
		try {
			double cnt = 0.0;
			int dem = 0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT count(*), Tổng=sum([tongTien])\r\n" + "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD\r\n"
					+ "					  where  MONTH( HoaDon.[ngayTao]) ='" + month + "' ";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dem += rs.getInt(1);
				cnt += rs.getDouble(2);
			}
			lblTinhTongDTTBTheoThang.setText(String.valueOf(formatter.format(cnt / dem)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	SELECT  Tổng=sum([soLuong]* [donGia])
//			FROM     ChiTietHoaDon INNER JOIN
//			                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD
//							  where HoaDon.[ngayTao] ='2020-03-15'	
	public void hienThiDTHN(String dk) {
		try {
			double cnt = 0.0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT  Tổng=sum([tongTien])\r\n" + "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD\r\n"
					+ "					  where HoaDon.[ngayTao] ='" + dk + "'	";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {

				cnt += rs.getDouble(1);
			}
			lblTinhTongDTHN.setText(String.valueOf(formatter.format(cnt)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hienThiDTTheoThang(int dk) {
		try {
			double cnt = 0.0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "\r\n" + "SELECT  Tổng=sum([tongTien])\r\n" + "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD\r\n"
					+ "					  where  MONTH( HoaDon.[ngayTao]) ='" + dk + "' ";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {

				cnt += rs.getDouble(1);
			}
			lblTinhTongDTTheoThang.setText(String.valueOf(formatter.format(cnt)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void hienThiDTTB(String dk) {
		// TODO Auto-generated method stub
		try {
			double cnt = 0.0;
			int dem = 0;
			double doanhthu = 0.0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT count(*), Tổng=sum([tongTien])\r\n" + "	FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "	                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD\r\n"
					+ "					  where HoaDon.[ngayTao] ='" + dk + "' ";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				dem += rs.getInt(1);
				cnt += rs.getDouble(2);
			}
			lblTinhTongDTTB.setText(String.valueOf(formatter.format(cnt / dem)));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Component SanPham() {

		JPanel pnlSanPham = new JPanel(null);

		JPanel pnlTacVu = new JPanel(null);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVu.setBounds(70, 20, 1050, 120);
		pnlSanPham.add(pnlTacVu);

		lblTuNgay = new JLabel("Từ ngày:");
		lblTuNgay.setBounds(50, 20, 100, 20);
		pnlTacVu.add(lblTuNgay);

		tuNgayChooser1 = new JDateChooser();
		tuNgayChooser1.setDateFormatString("yyyy-MM-dd");
		tuNgayChooser1.setBounds(120, 20, 200, 20);
		tuNgayChooser1.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		tuNgayChooser1.getDateEditor().getUiComponent().setFocusable(false);
		pnlTacVu.add(tuNgayChooser1);

		lblTongSoLuongTon = new JLabel("Số Vé Còn: ");
		lblTongSoLuongTon.setBounds(50, 50, 250, 20);
		pnlTacVu.add(lblTongSoLuongTon);

		lblTinhTongSoLuongTon = new JLabel("0");
		lblTinhTongSoLuongTon.setForeground(Color.RED);
		lblTinhTongSoLuongTon.setBounds(180, 50, 200, 20);
		pnlTacVu.add(lblTinhTongSoLuongTon);

		lblTongSoVon = new JLabel("Tổng số vốn: ");
		lblTongSoVon.setBounds(50, 80, 250, 20);
		pnlTacVu.add(lblTongSoVon);

		lblTinhTongSoVon = new JLabel("0");
		lblTinhTongSoVon.setForeground(Color.RED);
		lblTinhTongSoVon.setBounds(180, 80, 200, 20);
		pnlTacVu.add(lblTinhTongSoVon);

		lblDenNgay = new JLabel("Đến ngày:");
		lblDenNgay.setBounds(400, 20, 100, 20);
		pnlTacVu.add(lblDenNgay);

		denNgayChooser1 = new JDateChooser();
		denNgayChooser1.setDateFormatString("yyyy-MM-dd");
		denNgayChooser1.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		denNgayChooser1.getDateEditor().getUiComponent().setFocusable(false);

		denNgayChooser1.setBounds(500, 20, 200, 20);
		pnlTacVu.add(denNgayChooser1);

		lblLoai = new JLabel("Loại CT: ");
		lblLoai.setBounds(400, 50, 250, 20);
		pnlTacVu.add(lblLoai);

		cbcLoai = new JComboBox<String>();
		cbcLoai.setBounds(500, 50, 200, 20);
		pnlTacVu.add(cbcLoai);
		ArrayList<Loai> dssp = l_dao.getAllLoai();
		for (Loai p : dssp) {
			cbcLoai.addItem(p.getTenLoai());
		}
		AutoCompleteDecorator.decorate(cbcLoai);

		lblNCC = new JLabel("Nhà Ga: ");
		lblNCC.setBounds(400, 80, 250, 20);
		pnlTacVu.add(lblNCC);

		cbcNCC = new JComboBox<String>();
		cbcNCC.setBounds(500, 80, 200, 20);
		pnlTacVu.add(cbcNCC);
		ArrayList<NhaGa> dsncc = ncc_dao.getAllNhaCC();
		for (NhaGa p : dsncc) {
			cbcNCC.addItem(p.getTenNhaCC());
		}
		AutoCompleteDecorator.decorate(cbcNCC);

		JButton btnExel = new JButton("Xuất excel");
		btnExel.setBounds(860, 80, 150, 20);
		pnlTacVu.add(btnExel);

		JButton btnThongKe = new JButton("In báo cáo vé còn");
		btnThongKe.setBounds(860, 50, 150, 20);
		pnlTacVu.add(btnThongKe);

		btnHienThiSP = new JButton("Hiển thị chuyến tàu");
		btnHienThiSP.setBounds(860, 20, 150, 20);
		pnlTacVu.add(btnHienThiSP);

		// table

		String[] columnNames = { "Mã", "Tên", "Khởi hành- Đến", "Tồn Kho", "Loại", "Nhà Ga", "Giá Nhập", "Giá Bán" };
		JPanel pnlTblSanPham = new JPanel(null);
		pnlTblSanPham.setBorder(BorderFactory.createTitledBorder("Bảng Chuyến Tàu"));
		pnlTblSanPham.setBounds(70, 150, 1050, 500);
		modelSP = new DefaultTableModel(columnNames, 0);
		tblSanPham = new JTable(modelSP);
		tblSanPham.setBounds(20, 20, 950, 400);
		JScrollPane srcSanPham = new JScrollPane(tblSanPham);
		srcSanPham.setBounds(10, 20, 950, 450);
		CenterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		RightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		LeftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		tblSanPham.getColumn("Mã").setCellRenderer(CenterRenderer);
		tblSanPham.getColumn("Tên").setCellRenderer(LeftRenderer);
		tblSanPham.getColumn("Khởi hành- Đến").setCellRenderer(CenterRenderer);
		tblSanPham.getColumn("Tồn Kho").setCellRenderer(CenterRenderer);
		tblSanPham.getColumn("Loại").setCellRenderer(LeftRenderer);
		tblSanPham.getColumn("Nhà Ga").setCellRenderer(LeftRenderer);
		tblSanPham.getColumn("Giá Nhập").setCellRenderer(CenterRenderer);
		tblSanPham.getColumn("Giá Bán").setCellRenderer(RightRenderer);
		pnlTblSanPham.add(srcSanPham);
		pnlSanPham.add(pnlTblSanPham);
		tblSanPham.setAutoCreateRowSorter(true);
		addTblSanPham();

		//
		JPanel pnlBanHangMainConTrol = new JPanel();
		pnlBanHangMainConTrol.setBounds(70, 700, 1050, 70);
		pnlBanHangMainConTrol.setBorder(BorderFactory.createTitledBorder("Các dạng biểu đồ"));
		pnlBanHangMainConTrol.add(btnBieudocot = new JButton("Biểu đồ cột"));
		pnlBanHangMainConTrol.add(btnBieudoLine = new JButton("Biểu đồ đường"));
		pnlBanHangMainConTrol.add(btnBieudoTron = new JButton("Biểu đồ tròn"));
//		pnlBanHangMainConTrol.add(btnXoaRong = new JButton("Làm mới"));
//		pnlBanHangMainConTrol.add(btnInHD = new JButton("In hóa đơn"));
//		pnlBanHangMainConTrol.add(btnLuuHD = new JButton("Lưu hóa đơn"));

		pnlSanPham.add(pnlBanHangMainConTrol);
//		pnlBanHangMain.add(pnlBanHangMainCenter);
//		pnlBanHangMain.add(pnlBanHangMainTable);
//		pnlBanHangMain.add(pnlThanhTien);
//		pnlBanHangMain.add(pnlBanHangMainConTrol);

		//

		btnBieudocot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			DefaultCategoryDataset dad= new DefaultCategoryDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "SELECT SanPham.tenSP,thanhTienT= sum( ChiTietHoaDon.thanhTien) FROM    SanPham INNER JOIN\r\n"
						+ "						         ChiTietHoaDon ON SanPham.maSP = ChiTietHoaDon.maSP\r\n"
						+ "								 group by SanPham.tenSP";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue(rs.getDouble("thanhTienT"), "Vé Tàu", rs.getString("tenSP"));
					}
					JFreeChart chart= ChartFactory.createBarChart("Thống kê chuyến tàu", "Tên", "Doanh thu", dad, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot= chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartFrame chartFrame= new ChartFrame("Thống kê chuyến tàu", chart,true);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		//
		
		btnBieudoLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			DefaultCategoryDataset dad= new DefaultCategoryDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "SELECT SanPham.tenSP,thanhTienT= sum( ChiTietHoaDon.thanhTien) FROM    SanPham INNER JOIN\r\n"
						+ "						         ChiTietHoaDon ON SanPham.maSP = ChiTietHoaDon.maSP\r\n"
						+ "								 group by SanPham.tenSP";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue(rs.getDouble("thanhTienT"), "Vé tàu", rs.getString("tenSP"));
					}
					JFreeChart chart= ChartFactory.createLineChart("Thống kê chuyến tàu", "Tên", "Doanh thu", dad, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot= chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartFrame chartFrame= new ChartFrame("Thống kê chuyến tàu", chart,true);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		//
		btnBieudoTron.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				DefaultPieDataset dad= new DefaultPieDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "SELECT SanPham.maSP, SanPham.tenSP, SanPham.donViSP, SanPham.giaNhap, SanPham.giaBan,tonKhoT= sum(SanPham.tonKho), SanPham.maLoai, SanPham.maNhaCC\r\n"
						+ "FROM     PhieuNhap INNER JOIN\r\n"
						+ "          ChiTietPhieuNhap ON PhieuNhap.maPhieuNhap = ChiTietPhieuNhap.maPhieuNhap INNER JOIN\r\n"
						+ "              SanPham ON ChiTietPhieuNhap.maSP = SanPham.maSP\r\n"
						+"			  where tonKho>0"
						+ "GROUP BY SanPham.maSP, SanPham.tenSP, SanPham.donViSP, SanPham.giaNhap, SanPham.giaBan, SanPham.tonKho, SanPham.maLoai, SanPham.maNhaCC";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue( rs.getString("tenSP"),rs.getInt("tonKhoT"));
					}
					JFreeChart chart= ChartFactory.createPieChart("Thống kê chuyến tàu", dad, true, true, false);
					PiePlot plot= (PiePlot) chart.getPlot();
					ChartFrame chartFrame= new ChartFrame("Thống kê chuyến tàu", chart);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		//
		btnHienThiSP.addActionListener(this);
		btnThongKe.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				header = new MessageFormat("Báo Cáo Tổng Hợp Vé Còn");
				MessageFormat footer = new MessageFormat("page");

				try {
					tblSanPham.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showConfirmDialog(null, "Print...");
				}
			}
		});
		btnExel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				addTblSanPham();
				JFileChooser chooser = new JFileChooser();
				int i = chooser.showSaveDialog(chooser);
				if (i == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						FileWriter out = new FileWriter(file + ".xls");
						BufferedWriter bwrite = new BufferedWriter(out);
						DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
						// ten Cot
						for (int j = 0; j < tblSanPham.getColumnCount(); j++) {
							bwrite.write(model.getColumnName(j) + "\t");
						}
						bwrite.write("\n");
						// Lay du lieu dong
						for (int j = 0; j < tblSanPham.getRowCount(); j++) {
							for (int k = 0; k < tblSanPham.getColumnCount(); k++) {
								bwrite.write(model.getValueAt(j, k) + "\t");
							}
							bwrite.write("\n");
						}
						bwrite.close();
						JOptionPane.showMessageDialog(null, "Lưu file thành công!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
					}
				}

			}

		});

		cbcLoai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblSanPham.getSelectionModel().clearSelection();
				modelSP.getDataVector().removeAllElements();
				int cnt = 0;
				double tongvon = 0.0;
				Object item = cbcLoai.getSelectedItem();
				if (item != null && item != "") {
					Loai maloai = l_dao.getLoaiTheoTenFirst(String.valueOf(cbcLoai.getSelectedItem()));
					ArrayList<entity.ChuyenTau> sanPhams = new ChuyenTau_DAO().getSanPhamTheoMaLoai(maloai.getMaLoai());

					if (sanPhams != null) {
						Object[] obj = new Object[] { "Mã Chuyến Tàu", "Tên Chuyến Tàu", "Khởi hành - Đến", "Vé Còn", "Loại",
								"Nhà Ga", "Giá Nhập", "Giá Bán" };
						DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
						tblSanPham.setModel(tableModel);
						for (ChuyenTau sp : sanPhams) {
							tableModel.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getDonViSP(),
									sp.getTonKho(), sp.getLoai().getTenLoai(), sp.getNhaCC().getTenNhaCC(),
									formatter.format(sp.getGiaNhap()), formatter.format(sp.getGiaBan())

							});
							lblTinhTongSoLuongTon.setText(String.valueOf(cnt += sp.getTonKho()) + "  vé");
							lblTinhTongSoVon.setText(String.valueOf(formatter.format(tongvon += sp.getGiaBan())));

						}
					} else if (modelSP.getRowCount() != new ChuyenTau_DAO().getAllSanPham().size()) {
//						addDataTableSanPham();
					}
				}

			}

		});
		//
		cbcNCC.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tblSanPham.getSelectionModel().clearSelection();
				modelSP.getDataVector().removeAllElements();
				int cnt = 0;
				double tongvon = 0.0;
				Object item = cbcNCC.getSelectedItem();
				if (item != null && item != "") {
					NhaGa maloai = ncc_dao.getNhaCCTheoTenFirst(String.valueOf(cbcNCC.getSelectedItem()));
					ArrayList<entity.ChuyenTau> sanPhams = new ChuyenTau_DAO().getSanPhamTheoMaNCC(maloai.getMaNhaCC());

					if (sanPhams != null) {
						Object[] obj = new Object[] { "Mã Chuyến Tàu", "Tên Chuyến Tàu", "Khởi Hành - Đến", "Vé Còn", "Loại",
								"Nhà Ga", "Giá Nhập", "Giá Bán" };
						DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
						tblSanPham.setModel(tableModel);
						for (ChuyenTau sp : sanPhams) {
							tableModel.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getDonViSP(),
									sp.getTonKho(), sp.getLoai().getTenLoai(), sp.getNhaCC().getTenNhaCC(),
									formatter.format(sp.getGiaNhap()), formatter.format(sp.getGiaBan())

							});
							lblTinhTongSoLuongTon.setText(String.valueOf(cnt += sp.getTonKho()) + "  vé");
							lblTinhTongSoVon.setText(String.valueOf(formatter.format(tongvon += sp.getGiaBan())));
						}
					} else if (modelSP.getRowCount() != new ChuyenTau_DAO().getAllSanPham().size()) {
//						addDataTableSanPham();
					}
				}
			}
		});

		return pnlSanPham;

	}

	private void addTblSanPham() {
		tblSanPham.getSelectionModel().clearSelection();
		modelSP.getDataVector().removeAllElements();
		List<ChuyenTau> dsSanPham = new ChuyenTau_DAO().getSanPhamTonKho();

		for (ChuyenTau sanPham : dsSanPham) {
			modelSP.addRow(new Object[] { sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getDonViSP(),
					sanPham.getTonKho(), sanPham.getLoai().getTenLoai(), sanPham.getNhaCC().getTenNhaCC(),
					formatter.format(sanPham.getGiaNhap()), formatter.format(sanPham.getGiaBan()) });
		}

	}

	private Component DoanhThu() {
		JPanel pnlDoanThu = new JPanel(null);

		JPanel pnlTacVu = new JPanel(null);
		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVu.setBounds(70, 20, 1050, 120);
		pnlDoanThu.add(pnlTacVu);

		lblTuNgay = new JLabel("Từ ngày");
		lblTuNgay.setBounds(90, 20, 100, 20);
		pnlTacVu.add(lblTuNgay);

		tuNgayChooser = new JDateChooser();
		tuNgayChooser.setDateFormatString("yyyy-MM-dd");
		tuNgayChooser.setBounds(190, 20, 200, 20);
		tuNgayChooser.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		tuNgayChooser.getDateEditor().getUiComponent().setFocusable(false);
		pnlTacVu.add(tuNgayChooser);

		lblDenNgay = new JLabel("Đến ngày");
		lblDenNgay.setBounds(470, 20, 100, 20);
		pnlTacVu.add(lblDenNgay);

		denNgayChooser = new JDateChooser();
		denNgayChooser.setDateFormatString("yyyy-MM-dd");
		denNgayChooser.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		denNgayChooser.getDateEditor().getUiComponent().setFocusable(false);

		denNgayChooser.setBounds(570, 20, 200, 20);
		pnlTacVu.add(denNgayChooser);

		lblTongDoanhThu = new JLabel("Tổng Doanh Thu: ");
		lblTongDoanhThu.setBounds(90, 50, 250, 20);
		pnlTacVu.add(lblTongDoanhThu);

		lblTinhTongDoanhThu = new JLabel("0");
		lblTinhTongDoanhThu.setForeground(Color.RED);
		lblTinhTongDoanhThu.setBounds(230, 50, 250, 20);
		pnlTacVu.add(lblTinhTongDoanhThu);

		lblTongSoLuongBan = new JLabel("Số Vé Tàu Đã Bán: ");
		lblTongSoLuongBan.setBounds(90, 80, 270, 20);
		pnlTacVu.add(lblTongSoLuongBan);

		lblTinhTongSoLuongBan = new JLabel("0");
		lblTinhTongSoLuongBan.setForeground(Color.RED);
		lblTinhTongSoLuongBan.setBounds(230, 80, 250, 20);
		pnlTacVu.add(lblTinhTongSoLuongBan);

		lblLoaiDT = new JLabel("Tổng Tiền Vốn: ");
		lblLoaiDT.setBounds(470, 80, 250, 20);
		pnlTacVu.add(lblLoaiDT);

		lblTongLoaiDT = new JLabel("0");
		lblTongLoaiDT.setForeground(Color.RED);
		lblTongLoaiDT.setBounds(600, 80, 200, 20);
		pnlTacVu.add(lblTongLoaiDT);

		lblLoaiDoanhThu = new JLabel("Loại: ");
		lblLoaiDoanhThu.setBounds(470, 50, 250, 20);
		pnlTacVu.add(lblLoaiDoanhThu);

		cbcLoaiDoanhThu = new JComboBox<String>();
		cbcLoaiDoanhThu.setBounds(570, 50, 200, 20);
		pnlTacVu.add(cbcLoaiDoanhThu);
		ArrayList<Loai> dssp = l_dao.getAllLoai();
		for (Loai p : dssp) {
			cbcLoaiDoanhThu.addItem(p.getTenLoai());
		}
		AutoCompleteDecorator.decorate(cbcLoaiDoanhThu);

		btnHienThiDoanhThu = new JButton("Hiển thị doanh thu");
		btnHienThiDoanhThu.setBounds(830, 20, 170, 20);
		pnlTacVu.add(btnHienThiDoanhThu);

		JButton btnInDoanhThu = new JButton("In báo cao doanh thu");
		btnInDoanhThu.setBounds(830, 50, 170, 20);
		pnlTacVu.add(btnInDoanhThu);

		JButton btnExelDT = new JButton("Xuất excel");
		btnExelDT.setBounds(830, 80, 170, 20);
		pnlTacVu.add(btnExelDT);

		JPanel pnlTblSanPham = new JPanel(null);

		// table

//		String[] columnNames = { "Mã", "Tên", "Số Lượng", "Loại", "Giá Bán", "Doanh thu" };
//		modelDT = new DefaultTableModel(columnNames, 0);
//		JScrollPane scrDT;
//		tblDT = new JTable(modelDT);
//		tblDT.setBounds(20, 20, 1010, 490);
//		scrDT= new JScrollPane(tblDT);
//		pnlTblSanPham.setBorder(BorderFactory.createTitledBorder("Bảng Doanh Thu"));
//		pnlTblSanPham.setBounds(10, 100, 1030, 520);
//		pnlTblSanPham.add(scrDT);
//		pnlDoanThu.add(pnlTblSanPham);
		String[] columnNames = { "Mã", "Tên", "Loại", "Giá Bán", "Số Lượng", "Doanh thu" };
		pnlTblSanPham = new JPanel(null);
		pnlTblSanPham.setBorder(BorderFactory.createTitledBorder("Bảng Doanh Thu"));
		pnlTblSanPham.setBounds(70, 140, 1050, 500);
		modelDT = new DefaultTableModel(columnNames, 0);
		tblDT = new JTable(modelDT);
		tblDT.setBounds(20, 20, 950, 450);
		JScrollPane srcDT = new JScrollPane(tblDT);
		srcDT.setBounds(10, 20, 950, 450);
		pnlTblSanPham.add(srcDT);
		pnlDoanThu.add(pnlTblSanPham);
		HienThiDT("");
		CenterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		RightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		LeftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		tblDT.getColumn("Mã").setCellRenderer(CenterRenderer);
		tblDT.getColumn("Tên").setCellRenderer(LeftRenderer);
		tblDT.getColumn("Số Lượng").setCellRenderer(CenterRenderer);
		tblDT.getColumn("Loại").setCellRenderer(RightRenderer);
		tblDT.getColumn("Giá Bán").setCellRenderer(CenterRenderer);
		tblDT.getColumn("Doanh thu").setCellRenderer(RightRenderer);
		tblDT.setAutoCreateRowSorter(true);
		
		//
		JPanel pnlBanHangMainConTrol = new JPanel();
		pnlBanHangMainConTrol.setBounds(70, 700, 1050, 70);
		pnlBanHangMainConTrol.setBorder(BorderFactory.createTitledBorder("Các dạng biểu đồ"));
		pnlBanHangMainConTrol.add(btnBieudocot = new JButton("Biểu đồ cột"));
		pnlBanHangMainConTrol.add(btnBieudoLine = new JButton("Biểu đồ đường"));
		pnlBanHangMainConTrol.add(btnBieudoTron = new JButton("Biểu đồ tròn"));
//		pnlBanHangMainConTrol.add(btnXoaRong = new JButton("Làm mới"));
//		pnlBanHangMainConTrol.add(btnInHD = new JButton("In hóa đơn"));
//		pnlBanHangMainConTrol.add(btnLuuHD = new JButton("Lưu hóa đơn"));

		pnlDoanThu.add(pnlBanHangMainConTrol);
		///
		btnBieudocot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			DefaultCategoryDataset dad= new DefaultCategoryDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "select cthd.maSP, [tenSP], [maLoai], [GiaBan], soLuong = sum(cthd.soLuong) , doanhThu =  sum(thanhTien), ngayTao\r\n"
						+ "from ChiTietHoaDon cthd join SanPham sp on cthd.maSP = sp.maSP join HoaDon hd on cthd.maHD = hd.maHD\r\n"
						+ "group by cthd.maSP, [tenSP], [maLoai], [GiaBan],ngayTao";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue(rs.getDouble("doanhThu"), "Doanh thu", rs.getString("ngayTao"));
					}
					JFreeChart chart= ChartFactory.createBarChart("Thống kê doanh thu vé", "Tên", "Doanh thu", dad, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot= chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartFrame chartFrame= new ChartFrame("Thống kê doanh thu vé", chart,true);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		//
		
		btnBieudoLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			DefaultCategoryDataset dad= new DefaultCategoryDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "select cthd.maSP, [tenSP], [maLoai], [GiaBan], soLuong = sum(cthd.soLuong) , doanhThu =  sum(thanhTien), ngayTao\r\n"
						+ "from ChiTietHoaDon cthd join SanPham sp on cthd.maSP = sp.maSP join HoaDon hd on cthd.maHD = hd.maHD\r\n"
						+ "group by cthd.maSP, [tenSP], [maLoai], [GiaBan],ngayTao";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue(rs.getDouble("doanhThu"), "Doanh thu", rs.getString("ngayTao"));
					}
					JFreeChart chart= ChartFactory.createLineChart("Thống kê doanh thu vé", "Ngày tạo", "Doanh thu", dad, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot= chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartFrame chartFrame= new ChartFrame("Thống kê doanh thu vé", chart,true);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		//
		btnBieudoTron.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				DefaultPieDataset dad= new DefaultPieDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "select  soLuongT = sum(cthd.soLuong) , doanhThu =  sum(thanhTien), ngayTao\r\n"
						+ "from ChiTietHoaDon cthd join SanPham sp on cthd.maSP = sp.maSP join HoaDon hd on cthd.maHD = hd.maHD\r\n"
						+ "group by  ngayTao";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue( rs.getString("ngayTao"),rs.getInt("soLuongT"));
					}
					JFreeChart chart= ChartFactory.createPieChart("Thống kê chuyến tàu theo vé", dad, true, true, false);
					PiePlot plot= (PiePlot) chart.getPlot();
					ChartFrame chartFrame= new ChartFrame("Thống kê chuyến tàu theo vé", chart);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		
		//
		
		btnHienThiDoanhThu.addActionListener(this);

		btnInDoanhThu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MessageFormat header = new MessageFormat("Doanh Thu");
				MessageFormat footer = new MessageFormat("page");
				try {
					tblDT.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showConfirmDialog(null, "Print...");
				}
			}
		});
		btnExelDT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				addTblSanPham();
				JFileChooser chooser = new JFileChooser();
				int i = chooser.showSaveDialog(chooser);
				if (i == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						FileWriter out = new FileWriter(file + ".xls");
						BufferedWriter bwrite = new BufferedWriter(out);
						DefaultTableModel model = (DefaultTableModel) tblDT.getModel();
						// ten Cot
						for (int j = 0; j < tblDT.getColumnCount(); j++) {
							bwrite.write(model.getColumnName(j) + "\t");
						}
						bwrite.write("\n");
						// Lay du lieu dong
						for (int j = 0; j < tblDT.getRowCount(); j++) {
							for (int k = 0; k < tblDT.getColumnCount(); k++) {
								bwrite.write(model.getValueAt(j, k) + "\t");
							}
							bwrite.write("\n");
						}
						bwrite.close();
						JOptionPane.showMessageDialog(null, "Lưu file thành công!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
					}
				}

			}

		});

		cbcLoaiDoanhThu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				tblDT.getSelectionModel().clearSelection();
				modelDT.getDataVector().removeAllElements();
				int cnt = 0;
				double doanhThu = 0.0;
				double cn = 0.0;
				Object item = cbcLoaiDoanhThu.getSelectedItem();
				if (item != null && item != "") {
					Loai maloai = l_dao.getLoaiTheoTenFirst(String.valueOf(cbcLoaiDoanhThu.getSelectedItem()));
					ArrayList<entity.ChuyenTau> sanPhams = new ChuyenTau_DAO().getSanPhamTheoMaLoai(maloai.getMaLoai());

					if (sanPhams != null) {
						Object[] obj = new Object[] { "Mã Chuyến Tàu", "Tên Chuyến Tàu", "Loại", "Giá Bán", "Số Lượng",
								"Doanh thu" };
						DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
						tblDT.setModel(tableModel);
						tblDT.setAutoCreateRowSorter(true);
//						List<HoaDon> hd= hoadon_dao.getHoaDonTheoMaHD(getName())
						for (ChuyenTau sp : sanPhams) {
							List<ChiTietVe> dsCT = new ChiTietVe_DAO().getChiTietHDTheoMaSP(sp.getMaSP());
							for (ChiTietVe ct : dsCT) {
								doanhThu += ct.getThanhTien();
								cn += ct.getDonGia();
//							hd.setChiTietHoaDons(dsCT);
								tableModel.addRow(new Object[] { sp.getMaSP(), sp.getTenSP(), sp.getLoai().getTenLoai(),
										formatter.format(sp.getGiaBan()), ct.getSoLuong(), formatter.format(doanhThu)

								});
								lblTinhTongSoLuongBan.setText(String.valueOf(cnt += ct.getSoLuong()) + "  vé");
								lblTinhTongDoanhThu.setText(String.valueOf(formatter.format(doanhThu)));
								lblTongLoaiDT.setText(String.valueOf(formatter.format(cn)));
							}
						}
					} else if (modelDT.getRowCount() != new ChuyenTau_DAO().getAllSanPham().size()) {
//						addDataTableSanPham();
					}
				}

			}

		});
		//

		return pnlDoanThu;
	}

//	SELECT [dbo].[NhanVien].maNV, [dbo].[NhanVien].tenNV, [dbo].[NhanVien].[maChucVu], doanhThu= sum(thanhTien)
//	FROM     [dbo].[NhanVien] INNER JOIN
//					[dbo].[HoaDon]	 ON NhanVien.maNV = HoaDon.maNV INNER JOIN
//						[dbo].[ChiTietHoaDon] ON ChiTietHoaDon.maHD = HoaDon.maHD
//						where [dbo].[HoaDon].ngayTao >= '2020-03-15' and [dbo].[HoaDon].ngayTao <='2021-02-03'
//	GROUP BY [dbo].[NhanVien].maNV, [dbo].[NhanVien].tenNV, [dbo].[NhanVien].[maChucVu]
	private Component TabNhanVien() {
		JPanel pnlNhanVien = new JPanel(null);
		JPanel pnlTacVuNV = new JPanel(null);
//
		pnlTacVuNV.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
		pnlTacVuNV.setBounds(70, 20, 1050, 120);
		pnlNhanVien.add(pnlTacVuNV);
//
		lblTuNgay2 = new JLabel("Từ ngày:");
		lblTuNgay2.setBounds(20, 30, 100, 20);
		pnlTacVuNV.add(lblTuNgay2);
////
		tuNgayChooser2 = new JDateChooser();
		tuNgayChooser2.setDateFormatString("yyyy-MM-dd");
		tuNgayChooser2.setBounds(100, 30, 200, 20);
		tuNgayChooser2.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		tuNgayChooser2.getDateEditor().getUiComponent().setFocusable(false);
		pnlTacVuNV.add(tuNgayChooser2);
//
		lblDenNgay2 = new JLabel("Đến ngày:");
		lblDenNgay2.setBounds(450, 30, 100, 20);
		pnlTacVuNV.add(lblDenNgay2);
//
		denNgayChooser2 = new JDateChooser();
		denNgayChooser2.setDateFormatString("yyyy-MM-dd");
		denNgayChooser2.setDate(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		denNgayChooser2.getDateEditor().getUiComponent().setFocusable(false);

		denNgayChooser2.setBounds(550, 30, 200, 20);
		pnlTacVuNV.add(denNgayChooser2);
//
		lblTongDoanhThu2 = new JLabel("Tổng Doanh Thu: ");
		lblTongDoanhThu2.setBounds(20, 60, 250, 20);
		pnlTacVuNV.add(lblTongDoanhThu2);

		lblTinhTongDoanhThu2 = new JLabel("0");
		lblTinhTongDoanhThu2.setForeground(Color.RED);
		lblTinhTongDoanhThu2.setBounds(150, 60, 250, 20);
		pnlTacVuNV.add(lblTinhTongDoanhThu2);

		lblTongSPBan = new JLabel("Tổng Vé Tàu đã bán: ");
		lblTongSPBan.setBounds(450, 60, 250, 20);
		pnlTacVuNV.add(lblTongSPBan);

		lblTinhTongSPBan = new JLabel("0");
		lblTinhTongSPBan.setForeground(Color.RED);
		lblTinhTongSPBan.setBounds(600, 60, 250, 20);
		pnlTacVuNV.add(lblTinhTongSPBan);
//

		btnHienThiDoanhThuNV = new JButton("Hiển thị doanh thu");
		btnHienThiDoanhThuNV.setBounds(830, 20, 170, 20);
		pnlTacVuNV.add(btnHienThiDoanhThuNV);

		JButton btnInDoanhThuNV = new JButton("In báo cao doanh thu");
		btnInDoanhThuNV.setBounds(830, 50, 170, 20);
		pnlTacVuNV.add(btnInDoanhThuNV);

		JButton btnExelNV = new JButton("Xuất excel");
		btnExelNV.setBounds(830, 80, 170, 20);
		pnlTacVuNV.add(btnExelNV);
		JPanel pnlTblSanPham = new JPanel(null);
////Table
//
		String[] columnNames = { "Mã", "Tên Nhân Viên", "Chức Vụ", "Doanh Thu" };
		JPanel pnlTblNhanVien = new JPanel(null);
		pnlTblNhanVien.setBorder(BorderFactory.createTitledBorder("Bảng Nhân Viên"));
		pnlTblNhanVien.setBounds(70, 150, 1050, 500);
		modelNV = new DefaultTableModel(columnNames, 0);
		tblNhanVien = new JTable(modelNV);
		tblNhanVien.setBounds(20, 20, 950, 450);
		JScrollPane srcSanPham = new JScrollPane(tblNhanVien);
		srcSanPham.setBounds(10, 20, 950, 450);
		CenterRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		RightRenderer.setHorizontalAlignment(DefaultTableCellRenderer.RIGHT);
		LeftRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
		tblNhanVien.getColumn("Mã").setCellRenderer(CenterRenderer);
		tblNhanVien.getColumn("Tên Nhân Viên").setCellRenderer(LeftRenderer);
		tblNhanVien.getColumn("Chức Vụ").setCellRenderer(LeftRenderer);
		tblNhanVien.getColumn("Doanh Thu").setCellRenderer(LeftRenderer);

		tblNhanVien.setAutoCreateRowSorter(true);
		pnlTblNhanVien.add(srcSanPham);
		pnlNhanVien.add(pnlTblNhanVien);
		addTableNhanVien();
		//
		//
		JPanel pnlBanHangMainConTrol = new JPanel();
		pnlBanHangMainConTrol.setBounds(70, 700, 1050, 70);
		pnlBanHangMainConTrol.setBorder(BorderFactory.createTitledBorder("Các dạng biểu đồ"));
		pnlBanHangMainConTrol.add(btnBieudocot = new JButton("Biểu đồ cột"));
		pnlBanHangMainConTrol.add(btnBieudoLine = new JButton("Biểu đồ đường"));
		pnlBanHangMainConTrol.add(btnBieudoTron = new JButton("Biểu đồ tròn"));
//		pnlBanHangMainConTrol.add(btnXoaRong = new JButton("Làm mới"));
//		pnlBanHangMainConTrol.add(btnInHD = new JButton("In hóa đơn"));
//		pnlBanHangMainConTrol.add(btnLuuHD = new JButton("Lưu hóa đơn"));

		pnlNhanVien.add(pnlBanHangMainConTrol);
		///
		btnBieudocot.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			DefaultCategoryDataset dad= new DefaultCategoryDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu,doanhThu= sum(HoaDon.tongTien), soLuong=sum([dbo].[ChiTietHoaDon].soLuong)\r\n"
						+ "FROM     ChiTietHoaDon INNER JOIN\r\n"
						+ "           HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD INNER JOIN\r\n"
						+ "  NhanVien ON HoaDon.maNV = NhanVien.maNV\r\n"
						+ "GROUP BY NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu having not maChucVu ='CV00001'";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue(rs.getDouble("doanhThu"), "Doanh thu", rs.getString("tenNV"));
					}
					JFreeChart chart= ChartFactory.createBarChart("Thống kê doanh thu nhân viên", "Tên nhân viên", "Doanh thu", dad, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot= chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartFrame chartFrame= new ChartFrame("Thống kê doanh thu nhân viên", chart,true);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		//
		
		btnBieudoLine.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

			DefaultCategoryDataset dad= new DefaultCategoryDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu,doanhThu= sum(HoaDon.tongTien), soLuong=sum([dbo].[ChiTietHoaDon].soLuong)\r\n"
						+ "FROM     ChiTietHoaDon INNER JOIN\r\n"
						+ "           HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD INNER JOIN\r\n"
						+ "  NhanVien ON HoaDon.maNV = NhanVien.maNV\r\n"
						+ "GROUP BY NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu having not maChucVu ='CV00001'";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue(rs.getDouble("doanhThu"), "Doanh thu", rs.getString("tenNV"));
					}
					JFreeChart chart= ChartFactory.createLineChart("Thống kê doanh thu nhân viên", "Tên nhân viên", "Doanh thu", dad, PlotOrientation.VERTICAL, true, true, false);
					CategoryPlot plot= chart.getCategoryPlot();
					plot.setRangeGridlinePaint(Color.black);
					ChartFrame chartFrame= new ChartFrame("Thống kê doanh thu nhân viên", chart,true);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		//
		btnBieudoTron.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				DefaultPieDataset dad= new DefaultPieDataset();
			try {
				ConnectDB.getInstance();
				Connection con = (Connection) ConnectDB.getConnection();
				Statement statement = con.createStatement();
				String cautruyvan = "";
				cautruyvan = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu,doanhThu= sum(HoaDon.tongTien), soLuongT=sum([dbo].[ChiTietHoaDon].soLuong)\r\n"
						+ "FROM     ChiTietHoaDon INNER JOIN\r\n"
						+ "           HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD INNER JOIN\r\n"
						+ "  NhanVien ON HoaDon.maNV = NhanVien.maNV\r\n"
						+ "GROUP BY NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu having not maChucVu ='CV00001'";

				ResultSet rs = ((java.sql.Statement) statement).executeQuery(cautruyvan);
				try {
					while (rs.next()) {
						dad.setValue( rs.getString("tenNV"),rs.getInt("soLuongT"));
					}
					JFreeChart chart= ChartFactory.createPieChart("Thống kê nhân viên theo chuyến tàu bán được", dad, true, true, false);
					PiePlot plot= (PiePlot) chart.getPlot();
					ChartFrame chartFrame= new ChartFrame("Thống kê nhân viên theo chuyến tàu bán được", chart);
					chartFrame.setVisible(true);
					chartFrame.setSize(500,600);
					}
					catch (SQLException ex) {
						System.out.println(ex.toString());
					}
				} catch (Exception e) {
				}
			
			
			}
		});
		
		
		
		
		btnHienThiDoanhThuNV.addActionListener(this);
		btnInDoanhThuNV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				MessageFormat header = new MessageFormat("Tổng Báo Cáo Doanh Thu Nhân Viên");
				MessageFormat footer = new MessageFormat("page");
				try {
					tblNhanVien.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (Exception e) {
					// TODO: handle exception
					JOptionPane.showConfirmDialog(null, "Print...");
				}
			}
		});
		btnExelNV.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
//				addTblSanPham();
				JFileChooser chooser = new JFileChooser();
				int i = chooser.showSaveDialog(chooser);
				if (i == JFileChooser.APPROVE_OPTION) {
					File file = chooser.getSelectedFile();
					try {
						FileWriter out = new FileWriter(file + ".xls");
						BufferedWriter bwrite = new BufferedWriter(out);
						DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
						// ten Cot
						for (int j = 0; j < tblNhanVien.getColumnCount(); j++) {
							bwrite.write(model.getColumnName(j) + "\t");
						}
						bwrite.write("\n");
						// Lay du lieu dong
						for (int j = 0; j < tblNhanVien.getRowCount(); j++) {
							for (int k = 0; k < tblNhanVien.getColumnCount(); k++) {
								bwrite.write(model.getValueAt(j, k) + "\t");
							}
							bwrite.write("\n");
						}
						bwrite.close();
						JOptionPane.showMessageDialog(null, "Lưu file thành công!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
					}
				}

			}

		});

		return pnlNhanVien;
	}

	private void addTableNhanVien() {
		tblNhanVien.getSelectionModel().clearSelection();
		modelNV.getDataVector().removeAllElements();
		List<NhanVien> dsNhanVien = new NhanVien_DAO().gettalltbNhanVienNotQL();
		double doanhthu = 0.0;
		int ctt = 0;
		for (NhanVien nv : dsNhanVien) {
			List<Ve> dshd = new Ve_DAO().getHDTheoMaNV(nv.getMaNV());
			for (Ve hd : dshd) {
				List<ChiTietVe> dChiTietHDs = new ChiTietVe_DAO().getChiTietHDTheoMaHD((hd.getMaHD()));
				doanhthu += hd.getTongTien();
				for (ChiTietVe cthd : dChiTietHDs) {
					ctt += cthd.getSoLuong();
				}
				modelNV.addRow(new Object[] { nv.getMaNV(), nv.getTenNV(), nv.getChucVu().getTenChucVu(),
						formatter.format(hd.getTongTien()) });
			}
			lblTinhTongDoanhThu2.setText(String.valueOf(formatter.format(doanhthu)));
			lblTinhTongSPBan.setText(String.valueOf(ctt + " vé"));
		}

	}
	// SELECT TOP 10 HoaDon.maHD, HoaDon.maKH, HoaDon.tongTien, ChiTietHoaDon.maSP
	// FROM ChiTietHoaDon INNER JOIN
//	                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD
	// GROUP BY HoaDon.maHD, HoaDon.maKH, HoaDon.tongTien, ChiTietHoaDon.maSP

	public void addTblTQuan(String dk) {
		DefaultTableModel dtm = (DefaultTableModel) tblTQ.getModel();
		dtm.setRowCount(0);
		double cnt = 0.0;
		try {
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT TOP 40 HoaDon.maHD,[dbo].[KhachHang].tenKH ,Tổng=sum( HoaDon.tongTien), TôngSP=SUM(ChiTietHoaDon.soLuong)\r\n"
					+ "FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD INNER JOIN\r\n"
					+ "                  KhachHang ON HoaDon.maKH = KhachHang.maKH\r\n" + "	where HoaDon.[ngayTao] <'"
					+ dk + "'			 \r\n" + "GROUP BY HoaDon.maHD,[KhachHang].tenKH";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			Object[] obj = new Object[] { "Mã Vé", "Tên Khách Hàng", "Tổng Tiền", "Vé" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblTQ.setModel(tableModel);
			tblTQ.setAutoCreateRowSorter(true);
			while (rs.next()) {
				Object[] item = new Object[4];
				item[0] = rs.getString("maHD");
				item[1] = rs.getString("tenKH");
				item[2] = rs.getString("Tổng");
				item[3] = rs.getString("TôngSP");
				tableModel.addRow(item);
				cnt += rs.getDouble(3);

			}
//			lblTinhTongDTHN.setText(String.valueOf(formatter.format(cnt)));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void HienThiSP(String dk) {
		DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
		dtm.setRowCount(0);
		try {
			int cnt = 0;
			double cn = 0.0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT SanPham.maSP, SanPham.tenSP, SanPham.donViSP, SanPham.giaNhap, SanPham.giaBan,tonKho= sum(SanPham.tonKho), SanPham.maLoai, SanPham.maNhaCC\r\n"
					+ "FROM     PhieuNhap INNER JOIN\r\n"
					+ "                  ChiTietPhieuNhap ON PhieuNhap.maPhieuNhap = ChiTietPhieuNhap.maPhieuNhap INNER JOIN\r\n"
					+ "                  SanPham ON ChiTietPhieuNhap.maSP = SanPham.maSP\r\n" + dk
					+ "GROUP BY SanPham.maSP, SanPham.tenSP, SanPham.donViSP, SanPham.giaNhap, SanPham.giaBan, SanPham.tonKho, SanPham.maLoai, SanPham.maNhaCC\r\n"
					+ "";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			Object[] obj = new Object[] { "Mã Chuyến Tàu", "Tên Chuyến Tàu", "Khởi Hành - Đến", "Giá nhập", "Giá bán", "Vé còn",
					"Mã loại", "Nhà Ga" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblSanPham.setModel(tableModel);
			tblSanPham.setAutoCreateRowSorter(true);
			while (rs.next()) {
				Object[] item = new Object[8];
				item[0] = rs.getString("maSP");
				item[1] = rs.getString("tenSP");
				item[2] = rs.getString("donViSP");
				item[3] = rs.getString("giaNhap");
				item[4] = rs.getString("giaBan");
				item[5] = rs.getString("tonKho");
				item[6] = rs.getString("maLoai");

				item[7] = rs.getString("maNhaCC");
				tableModel.addRow(item);
//					TongDT += doanhThu;
				int tonKho = rs.getInt(6);
				double von = rs.getDouble(4);
				cn += von;
				cnt += tonKho;
			}

			lblTinhTongSoLuongTon.setText(String.valueOf(cnt) + "  vé");
			lblTinhTongSoVon.setText(String.valueOf(formatter.format(cn)));
			if (cnt == 0)
				JOptionPane.showMessageDialog(this, "Không có chuyến tàu nào");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void HienThiDT(String dk) {
		DefaultTableModel dtm = (DefaultTableModel) tblDT.getModel();
		dtm.setRowCount(0);
		try {
			Double TongDT = 0.0;
			Double cn = 0.0;
			int cnt = 0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select cthd.maSP, [tenSP], [maLoai], [GiaBan], soLuong = sum(cthd.soLuong) , doanhThu =  sum(thanhTien), Tu = min(ngayTao),Den = Max(ngayTao) \r\n"
					+ "					from ChiTietHoaDon cthd join SanPham sp on cthd.maSP = sp.maSP join HoaDon hd on cthd.maHD = hd.maHD\r\n"
					+ dk + "group by cthd.maSP, [tenSP], [maLoai], [GiaBan]";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				String maL = rs.getString(3);
				double donGia = rs.getDouble(4);
				int soluong = rs.getInt(5);
				double doanhThu = rs.getDouble(6);
				TongDT += doanhThu;
				cnt += soluong;
				cn += donGia;
				dtm.addRow(new Object[] { maSP, tenSP, l_dao.getLoaiTheoMa(maL).getTenLoai(),
						String.valueOf(formatter.format(donGia)), soluong,
						String.valueOf(formatter.format(doanhThu)) });

			}
			lblTinhTongDoanhThu.setText(String.valueOf(formatter.format(TongDT)));
			lblTinhTongSoLuongBan.setText(String.valueOf(cnt) + "  vé");
			lblTongLoaiDT.setText(String.valueOf(formatter.format(cn)));
			if (cnt == 0) {
				JOptionPane.showMessageDialog(this, "Không có chuyến tàu nào");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void HienThiDTTheoMaNCC(String dk) {
		DefaultTableModel dtm = (DefaultTableModel) tblDT.getModel();
		dtm.setRowCount(0);
		try {
			Double TongDT = 0.0;
			int cnt = 0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "select cthd.maSP, [tenSP], [maLoai], [GiaBan], soLuong = sum(cthd.soLuong) , doanhThu =  sum(thanhTien)\r\n"
					+ "from ChiTietHoaDon cthd join SanPham sp on cthd.maSP = sp.maSP join HoaDon hd on cthd.maHD = hd.maHD\r\n"
					+ "group by cthd.maSP, [tenSP], [maLoai], [GiaBan]";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			Object[] obj = new Object[] { "Mã", "Tên", "Loại", "Giá Bán", "Số Lượng", "Doanh Thu" };
			DefaultTableModel tableModel = new DefaultTableModel(obj, 0);
			tblDT.setModel(tableModel);
			while (rs.next()) {
				String maSP = rs.getString(1);
				String tenSP = rs.getString(2);
				String maL = rs.getString(3);
				double donGia = rs.getDouble(4);
				int soluong = rs.getInt(5);
				double doanhThu = rs.getDouble(6);
				TongDT += doanhThu;
				cnt += soluong;
				tableModel.addRow(new Object[] { maSP, tenSP, l_dao.getLoaiTheoMa(maL).getTenLoai(),
						String.valueOf(formatter.format(donGia)), soluong,
						String.valueOf(formatter.format(doanhThu)) });

			}
			lblTinhTongDoanhThu.setText(String.valueOf(formatter.format(TongDT)));
			lblTinhTongSoLuongBan.setText(String.valueOf(cnt) + "  vé");
			if (cnt == 0) {
				JOptionPane.showMessageDialog(this, "Không có vé nào");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu,doanhThu= sum(HoaDon.tongTien)
//FROM     ChiTietHoaDon INNER JOIN
//                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD INNER JOIN
//                  NhanVien ON HoaDon.maNV = NhanVien.maNV
//where  not maChucVu ='CV00001' or HoaDon.[ngayTao]='2021-11-14' and  HoaDon.[ngayTao]='2021-11-14'
//GROUP BY NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu, HoaDon.tongTien
	public void HienThiDTNhanVien(String dk) {
		DefaultTableModel dtm = (DefaultTableModel) tblNhanVien.getModel();
		dtm.setRowCount(0);
		try {
			Double TongDT = 0.0;
			int cnt = 0;
			ConnectDB.getInstance();
			Connection con = ConnectDB.getConnection();
			String sql = "SELECT NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu,doanhThu= sum(HoaDon.tongTien), soLuong=sum([dbo].[ChiTietHoaDon].soLuong)\r\n"
					+ "FROM     ChiTietHoaDon INNER JOIN\r\n"
					+ "                  HoaDon ON ChiTietHoaDon.maHD = HoaDon.maHD INNER JOIN\r\n"
					+ "                  NhanVien ON HoaDon.maNV = NhanVien.maNV\r\n" + dk
					+ "GROUP BY NhanVien.maNV, NhanVien.tenNV, NhanVien.maChucVu having not maChucVu ='CV00001'";
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				String maNV = rs.getString(1);
				String tenNV = rs.getString(2);
				String maCV = rs.getString(3);
				double doanhThu = rs.getDouble(4);
				int soLuong = rs.getInt(5);
				TongDT += doanhThu;
				cnt += soLuong;
				dtm.addRow(new Object[] { maNV, tenNV, cv_dao.getChucVuTheoMa(maCV).getTenChucVu(),
						String.valueOf(formatter.format(doanhThu)), soLuong });

			}
			lblTinhTongDoanhThu2.setText(String.valueOf(formatter.format(TongDT)));
			lblTinhTongSPBan.setText(String.valueOf(cnt) + "  vé");
//			lblTongLoaiDT.setText(String.valueOf(formatter.format(cn)));
			if (cnt == 0) {
				JOptionPane.showMessageDialog(this, "Không có nhân viên nào");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, SQLException {
		new UI_ThongKe(userCurr);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
			frmDangNhap = new UI_DangNhap();
			this.dispose();

		} else if (sou.equals(btnHienThiDoanhThu)) {

			String dk = null;
			try {
				dk = "where hd.ngayTao >= '" + formatter2.valueToString(tuNgayChooser.getDate())
						+ "' and hd.ngayTao <= '" + formatter2.valueToString(denNgayChooser.getDate())
						+ "' and  sp.tonKho > 0";
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HienThiDT(dk);

		}

		else if (sou.equals(btnHienThiSP)) {

			String dk = null;
			try {
				dk = " where PhieuNhap.ngayNhap >= '" + formatter2.valueToString(tuNgayChooser1.getDate())
						+ "' and PhieuNhap.ngayNhap <='" + formatter2.valueToString(denNgayChooser1.getDate())
						+ "' and  SanPham.tonKho > 0";
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HienThiSP(dk);

		} else if (sou.equals(btnHienThiDoanhThuNV)) {

			String dk = null;
			try {
				dk = " where HoaDon.[ngayTao]='" + formatter2.valueToString(tuNgayChooser2.getDate())
						+ "' and  HoaDon.[ngayTao]='" + formatter2.valueToString(denNgayChooser2.getDate()) + "'\r\n"
						+ "";
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HienThiDTNhanVien(dk);

		}
	}
}
