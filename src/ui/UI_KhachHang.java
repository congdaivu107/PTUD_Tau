//3.	Nguyễn Nhật Linh - 19530601 (Time keeper)
package ui;

import dao.KhachHang_DAO;
import dao.Loai_DAO;
import dao.ChuyenTau_DAO;
import dao.User_Dao;
import entity.KhachHang;
import entity.Loai;
import entity.ChuyenTau;
import entity.User;

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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
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


public class UI_KhachHang extends JFrame implements ActionListener {

	JButton btnHoaDon,btnSanPham,btnKhachHang,btnNhanVien,btnBaoCao,btnHoTro,btnDangXuat;
	
	JLabel lblmaKH, lblTenKH,lblDiaChi,lblSDT,lblTen,lblTimKiem;
    JTextField txtmaKH, txtTenKH, txtDiaChi, txtSDT, txtTen;
    JButton btnThem, btnXoa, btnSua, btnReset, btnTim;
    
	UI_KhachHang frmBanHang;
	UI_Ve frmHoaDon;
	UI_ChuyenTau frmSanPham;
	UI_KhachHang frmKhachHang;
	UI_NhanVien frmNhanVien;
	UI_ThongKe frmThongKe;
	UI_DangNhap frmDangXuat;
        private JTable tblKhachHang;
    public DefaultTableModel model;
    KhachHang_DAO khachHang_DAO = new KhachHang_DAO();
    private ArrayList<KhachHang> dsKhachHang;
    
	private JComboBox<String> cmbTimSDTKhachHang;

	private JComboBox<String> cmbTimTenKhachHang;

	private static User userCurr;

	public UI_KhachHang(User userCurr) throws IOException, SQLException {
		// TODO Auto-generated constructor stub
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

	private void giaoDienChinh1() throws IOException, SQLException {

		// Menu Tác Vụ Bên Trái
		JPanel pWest = new JPanel();
		pWest.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),"Quản Lý Khách Hàng"));

		//Image
		
		
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
		pWest.add(btnHoaDon = new JButton("Vé",iconbill));
		btnHoaDon.setPreferredSize(new Dimension(200, 50));
		btnHoaDon.setForeground(Color.BLACK);
		btnHoaDon.setBorder(BorderFactory.createRaisedBevelBorder());
		pWest.add(Box.createVerticalStrut(65));

		
		// Nút Sản Phầm
		pWest.add(btnSanPham = new JButton("Chuyến Tàu",iconbag));
		btnSanPham.setPreferredSize(new Dimension(200, 50));
		btnSanPham.setForeground(Color.black);
		btnSanPham.setBorder(BorderFactory.createRaisedBevelBorder());
		pWest.add(Box.createVerticalStrut(65));

		// Nút Khách Hàng
		pWest.add(btnKhachHang = new JButton("Khách Hàng",iconcus));
		btnKhachHang.setPreferredSize(new Dimension(200, 50));
		btnKhachHang.setForeground(Color.black);
		btnKhachHang.setBorder(BorderFactory.createRaisedBevelBorder());
		pWest.add(Box.createVerticalStrut(65));

		
		// Nút Nhân Viên
		btnNhanVien = new JButton("Nhân Viên",iconstaff);
		btnNhanVien.setPreferredSize(new Dimension(200, 50));
		btnNhanVien.setForeground(Color.black);
		btnNhanVien.setBorder(BorderFactory.createRaisedBevelBorder());

		// Nút Thống Kê
		btnBaoCao = new JButton("Thống Kê",iconAna);
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
		pWest.add(btnHoTro = new JButton("Hỗ Trợ",iconSer));
		btnHoTro.setBorder(BorderFactory.createRaisedBevelBorder());
		btnHoTro.setForeground(Color.black);
		btnHoTro.setPreferredSize(new Dimension(200, 50));
		pWest.add(Box.createVerticalStrut(65));


		// Nút Đăng Xuất
		pWest.add(btnDangXuat = new JButton("Đăng Xuất",iconOut));
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
		BufferedImage imagetag = ImageIO.read(new File("image/customer.png"));
		ImageIcon icontag = new ImageIcon(imagetag.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		
		BufferedImage imagetag1 = ImageIO.read(new File("image/customer.png"));
		ImageIcon icontag1 = new ImageIcon(imagetag1.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		
		BufferedImage imagetag2 = ImageIO.read(new File("image/customer.png"));
		ImageIcon icontag2 = new ImageIcon(imagetag2.getScaledInstance(15, 15, Image.SCALE_SMOOTH));
		
		tabbedPane.addTab("Khách Hàng", icontag, KhachHang(), "Danh Sách Nhân Viên");
//                tabbedPane.addTab("Đăng Nhập", icontag, KhachHang(), "Danh Sách Nhân Viên");
                
		add(tabbedPane, BorderLayout.CENTER);

		btnBaoCao.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnHoTro.addActionListener(this);
		btnKhachHang.addActionListener(this);
		btnNhanVien.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnDangXuat.addActionListener(this);
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
	

	

	private Component KhachHang() {
		
		int indentLeftLbl = 20;
		int indentLeftTxt = 130;
		int heightComponent = 28;
		int widthLbl = 100;
		int widthTxt = 400;
		int topSpacing = 30;
		int lineSpacingIncreasing = 50;

		int indentRightLbl = 680;
		int indentRightTxt = 810;
		
		JPanel pnlKhachHang = new JPanel(null);
                //khachHang
        String[] colname = {"Mã Khách Hàng", "Tên Khách Hàng", "Địa Chỉ", "Số Điện Thoại"};
        JPanel pnlTblKhachHang = new JPanel(null);
        
		pnlTblKhachHang.setBorder(BorderFactory.createTitledBorder("Bảng Khách Hàng"));
        pnlTblKhachHang.setBounds(10, 20, 1230, 490);
        model = new DefaultTableModel(colname, 0);
        tblKhachHang = new JTable(model);
        tblKhachHang.setBounds(20, 20, 1210, 460);
        JScrollPane srcSanPham = new JScrollPane(tblKhachHang);
        srcSanPham.setBounds(10, 20, 1210, 460);
        pnlTblKhachHang.add(srcSanPham);
        pnlKhachHang.add(pnlTblKhachHang);
        
      //nhập liệu trái
		
      		JPanel pnlTacVu = new JPanel(null);
      		pnlTacVu.setBorder(BorderFactory.createTitledBorder("Tác Vụ"));
      		pnlTacVu.setBounds(10,520, 1230, 145);
      		pnlKhachHang.add(pnlTacVu);
      		
      		lblmaKH = new JLabel("Mã Khách Hàng:");
      		lblmaKH.setBounds(indentLeftLbl, topSpacing, widthLbl, heightComponent);
      		txtmaKH = new JTextField();
      		txtmaKH.setEditable(false);
      		txtmaKH.setBounds(indentLeftTxt, topSpacing, widthTxt, heightComponent);
      		pnlTacVu.add(lblmaKH);
      		pnlTacVu.add(txtmaKH);
      		
      		lblTenKH = new JLabel("Tên Khách Hàng:");
      		lblTenKH.setBounds(indentLeftLbl, topSpacing+lineSpacingIncreasing*1, widthLbl, heightComponent);
      		txtTenKH = new JTextField();
      		txtTenKH.setBounds(indentLeftTxt, topSpacing+lineSpacingIncreasing*1, widthTxt, heightComponent);
      		pnlTacVu.add(lblTenKH);
      		pnlTacVu.add(txtTenKH);
      		
      		JLabel lblException = new JLabel("*");
    		lblException.setForeground(Color.red);
    		lblException.setBounds(indentLeftLbl, topSpacing + lineSpacingIncreasing * 1 + 25, widthLbl + 900,
    				heightComponent);
    		pnlTacVu.add(lblException);
      		
      		//nhập liệu phải
      		
      		lblDiaChi = new JLabel("Địa Chỉ:");
      		lblDiaChi.setBounds(indentRightLbl, topSpacing, widthLbl, heightComponent);
      		txtDiaChi= new JTextField();
      		txtDiaChi.setBounds(indentRightTxt, topSpacing, widthTxt, heightComponent);
      		pnlTacVu.add(lblDiaChi);
      		pnlTacVu.add(txtDiaChi);
      		
      		lblSDT = new JLabel("Số Điện Thoại:");
      		lblSDT.setBounds(indentRightLbl, topSpacing+lineSpacingIncreasing*1, widthLbl, heightComponent);
      		txtSDT= new JTextField();
      		txtSDT.setBounds(indentRightTxt, topSpacing+lineSpacingIncreasing*1, widthTxt, heightComponent);
      		pnlTacVu.add(lblSDT);
      		pnlTacVu.add(txtSDT);
                
      		int widthBtn = 120;
    		int heightBtn = 28;
    		int spacingBetweenBtn = 140;
    		
    		JPanel pnlControl = new JPanel(null);
    		pnlControl.setBorder(BorderFactory.createTitledBorder("Bảng Điều Khiển"));
    		pnlControl.setBounds(10, 680, 1230, 80);
    		pnlKhachHang.add(pnlControl);
    		
    		
    		btnThem = new JButton("Thêm");
    		btnThem.setBounds(indentLeftLbl, topSpacing, widthBtn, heightBtn);
    		pnlControl.add(btnThem);
    		
    		btnXoa = new JButton("Xóa");
    		btnXoa.setBounds(indentLeftLbl + spacingBetweenBtn, topSpacing, widthBtn, heightBtn);
    		btnXoa.setEnabled(false);
    		pnlControl.add(btnXoa);
    		
    		btnSua = new JButton("Sửa");
    		btnSua.setBounds(indentLeftLbl + spacingBetweenBtn*2, topSpacing, widthBtn, heightBtn);
    		pnlControl.add(btnSua);
    		
    		btnReset = new JButton("Làm mới");
    		btnReset.setBounds(indentLeftLbl + spacingBetweenBtn*3, topSpacing, widthBtn, heightBtn);
    		pnlControl.add(btnReset);
    		
    		int indentTimMa = indentLeftLbl + spacingBetweenBtn * 4;
    		int widthCmbTim = 80;
    		int widthbtnTim = 60;
    		JLabel lblTimMa = new JLabel("Tìm Theo SDT:");
    		lblTimMa.setBounds(indentTimMa, topSpacing, widthLbl, heightComponent);
    		cmbTimSDTKhachHang = new JComboBox<String>();
    		cmbTimSDTKhachHang.setEditable(true);
    		
    		cmbTimSDTKhachHang.setBounds(indentTimMa + widthLbl, topSpacing, widthCmbTim+70, heightComponent);
//    		JButton btnTimMa = new JButton("Tìm");
//    		btnTimMa.setBounds(indentTimMa + widthLbl + widthCmbTim +10, topSpacing, widthbtnTim, heightBtn);
    		
    		pnlControl.add(lblTimMa);
    		pnlControl.add(cmbTimSDTKhachHang);
//    		pnlControl.add(btnTimMa);
    		
    		int indentTimTen = indentTimMa + widthLbl + widthCmbTim +80;
    		JLabel lblTimTen = new JLabel("Tìm Theo Tên:");
    		lblTimTen.setBounds(indentTimTen, topSpacing, widthLbl, heightComponent);
    		cmbTimTenKhachHang = new JComboBox<String>();
    		cmbTimTenKhachHang.setEditable(true);
    		cmbTimTenKhachHang.setBounds(indentTimTen + widthLbl, topSpacing, widthCmbTim+170, heightComponent);
//    		JButton btnTimTen = new JButton("Tìm");
//    		btnTimTen.setBounds(indentTimTen + widthLbl + widthCmbTim +90, topSpacing, widthbtnTim, heightBtn);
    		
    		pnlControl.add(lblTimTen);
    		pnlControl.add(cmbTimTenKhachHang);
//    		pnlControl.add(btnTimTen);
    		
    		addDataTableKhachHang();
    		
    		txtTenKH.addFocusListener(new FocusListener() {

    			@Override
    			public void focusGained(FocusEvent e) {

    			}

    			@Override
    			public void focusLost(FocusEvent e) {
    				if (!(txtTenKH.getText().length() > 0)) {
    					lblException.setText("Error: Tên khách hàng không dược để trống");

    				} else {
    					lblException.setText("*");
    				}

    			}

    		});
    		
    		txtDiaChi.addFocusListener(new FocusListener() {

    			@Override
    			public void focusGained(FocusEvent e) {

    			}

    			@Override
    			public void focusLost(FocusEvent e) {
    				if (!(txtDiaChi.getText().length() > 0)) {
    					lblException.setText("Error: Địa chỉ không dược để trống");

    				} else {
    					lblException.setText("*");
    				}

    			}
    		});
    		
    		txtSDT.addFocusListener(new FocusListener() {

    			@Override
    			public void focusGained(FocusEvent e) {

    			}

    			@Override
    			public void focusLost(FocusEvent e) {
    				if (!(txtSDT.getText().length() > 0 && txtSDT.getText().matches("[0][0-9]{9}+"))) {
    					lblException.setText("Error: Số diện thoại không dược để trống và phải theo dịnh dạng 0346829111" );

    				} else {
    					lblException.setText("*");
    				}

    			}
    		});
    		
    		
    		tblKhachHang.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
   		     
    			private KhachHang khachHangSelected;

    			@Override
    			public void valueChanged(ListSelectionEvent e) {
    				if (tblKhachHang.getSelectedRowCount() == 1) {
    					int row = tblKhachHang.getSelectedRow();
    					khachHangSelected = new KhachHang_DAO().getKhachHangTheoMa((String) tblKhachHang.getValueAt(row, 0));
    					System.out.println(khachHangSelected);
    					txtmaKH.setText(khachHangSelected.getMaKH());
    					txtTenKH.setText(khachHangSelected.getTenKH());
    					txtDiaChi.setText(khachHangSelected.getDiaChi());
    					txtSDT.setText(khachHangSelected.getSdt());
    				}	
    			}
    		});
                
    		btnThem.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				String maKH = txtmaKH.getText();
    				String tenKH = txtTenKH.getText();
    				String diaChi = txtDiaChi.getText();
    				String sdt = txtSDT.getText();
    				KhachHang khachHang = new KhachHang(tenKH,maKH,sdt,diaChi);
    				System.out.println(new KhachHang_DAO().create(khachHang));
    				if (txtmaKH.getText().equals("") || txtTenKH.getText().equals("") || txtDiaChi.getText().equals("")
    						|| txtSDT.getText().equals("") )
    					lblException.setText("Error: Các ô nhập liêu không được để trống");
    				else {
    				khachHang_DAO.create(khachHang);
    				model.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(),khachHang.getDiaChi(),khachHang.getSdt() });
    				txtmaKH.setText(getMaTiepTheo(model, "KH"));
    			}}
    		});
                
    		btnReset.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {

    				tblKhachHang.getSelectionModel().clearSelection();
    				txtmaKH.setText(getMaTiepTheo(model, "KH"));
    				txtTenKH.setText("");
    				txtDiaChi.setText("");
    				txtSDT.setText("");
    				
    				addDataTableKhachHang();
    			}
    		});
    		btnSua.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				int row = tblKhachHang.getSelectedRow();
    				if (row >= 0) {
    					if (txtmaKH.getText().equals("") || txtTenKH.getText().equals("") || txtDiaChi.getText().equals("")
    							|| txtSDT.getText().equals("") )
    						lblException.setText("Phải nhập dữ liệu trước.");
    					else {
    				String maKH = txtmaKH.getText();
    				String TenKH = txtTenKH.getText();
    				String DiaChi = txtDiaChi.getText();
    				String SDT = txtSDT.getText();
    				KhachHang khachHang = new KhachHang(TenKH, maKH, SDT, DiaChi);
    				System.out.println(new KhachHang_DAO().update(khachHang));
    				if (khachHang_DAO.update(khachHang)) {
    				model.setValueAt(txtTenKH.getText(), row, 1);
    				model.setValueAt(txtDiaChi.getText(), row, 2);
    				model.setValueAt(txtSDT.getText(), row, 3);
    			}}}
    				else {
    					lblException.setText("Phải chọn dòng cần cập nhật.");
    				}
    				}
    		});
    		
    		btnXoa.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int row = tblKhachHang.getSelectedRow();
					if (row == -1)
						JOptionPane.showMessageDialog(KhachHang(), "Bạn phải chòn dòng cần xóa !!!");
					else {
					String maKH = txtmaKH.getText();
					System.out.println(new KhachHang_DAO().delete(maKH));
					model.removeRow(row);
					tblKhachHang.getSelectionModel().clearSelection();
					}
				}
    		});
    		
    		cmbTimSDTKhachHang.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				tblKhachHang.getSelectionModel().clearSelection();
    				model.getDataVector().removeAllElements();
    				Object item = cmbTimSDTKhachHang.getSelectedItem();
    				if (item != null && item != "") {
    					KhachHang l = new KhachHang_DAO().getKhachHangTheoSDT(cmbTimSDTKhachHang.getSelectedItem().toString());

    					if (l != null) {
    						System.out.println("1");
    						KhachHang khachHang = new KhachHang_DAO().getKhachHangTheoSDT(cmbTimSDTKhachHang.getSelectedItem().toString());
    						model.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(), 
    	    						khachHang.getDiaChi(), khachHang.getSdt()});
    					} else {
    						System.out.println("2");
    						List<KhachHang> khachHangs = new KhachHang_DAO().getAllKhachHang();
    						for (KhachHang khachHang : khachHangs) {
    							model.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(), 
    		    						khachHang.getDiaChi(), khachHang.getSdt()});
    						}
    					}
    				}
    			}
    			
    		});

    		cmbTimTenKhachHang.addActionListener(new ActionListener() {

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				tblKhachHang.getSelectionModel().clearSelection();
    				model.getDataVector().removeAllElements();
    				
    				Object item = cmbTimTenKhachHang.getSelectedItem();
    				if (item != null && item != "") {
    					List<KhachHang> khachHangs = new KhachHang_DAO().getKhachHangTheoTen(cmbTimTenKhachHang.getSelectedItem().toString());

    					if (khachHangs != null) {
    						for (KhachHang khachHang : khachHangs) {
    	    					model.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(), 
    	        						khachHang.getDiaChi(), khachHang.getSdt()});
    	    				}
    					}
    					if (khachHangs.size() == 0) {
    						khachHangs = new KhachHang_DAO().getAllKhachHang();
    						for (KhachHang khachHang : khachHangs) {
    	    					model.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(), 
    	        						khachHang.getDiaChi(), khachHang.getSdt()});
    	    				}
    					}
    				}
    				
    			}
    			
    		});
		return pnlKhachHang;
	}
	private void addDataTableKhachHang() {
		tblKhachHang.getSelectionModel().clearSelection();
		model.getDataVector().removeAllElements();
		dsKhachHang = new KhachHang_DAO().getAllKhachHang();
		cmbTimSDTKhachHang.removeAllItems();
		cmbTimTenKhachHang.removeAllItems();
		cmbTimSDTKhachHang.addItem("");
		cmbTimTenKhachHang.addItem("");
		for (KhachHang khachHang : dsKhachHang) {
			model.addRow(new Object[] {khachHang.getMaKH(), khachHang.getTenKH(), 
					khachHang.getDiaChi(), khachHang.getSdt()});
			cmbTimSDTKhachHang.addItem(khachHang.getSdt());
			cmbTimTenKhachHang.addItem(khachHang.getTenKH());
		}
		AutoCompleteDecorator.decorate(cmbTimSDTKhachHang);
		AutoCompleteDecorator.decorate(cmbTimTenKhachHang);
		txtmaKH.setText(getMaTiepTheo(model, "KH"));
		
	}

	public static void main(String[] args) throws IOException, SQLException {
		new UI_KhachHang(userCurr);
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

