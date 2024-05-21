//2.	Nguyễn Nhật Quang - 19437361 (Reporter)
package ui;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;


import javax.swing.Box;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import javax.swing.JTextField;



import dao.User_Dao;
import entity.User;

public class UI_DangNhap extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String chucVu;
	JLabel lblTaiKhoan, lblMatKhau, lblTieuDe, lblnote;
	JTextField txtTaiKhoan;
	JPasswordField txtMatKhau;
	JButton btnDangNhap, btnQuenMK;
	Color gbMain = new Color(214, 214, 214);

	public UI_DangNhap() {
		// TODO Auto-generated constructor stub
		setTitle("ĐĂNG NHẬP");

		JPanel pNorth;
		add(pNorth = new JPanel(), BorderLayout.NORTH);

//		 BufferedImage image = ImageIO.read(new File("img/img_DangNhap.jpg"));
//		ImageIcon img = new ImageIcon(image.getScaledInstance(470, 350, image.SCALE_SMOOTH));
//		JLabel showImage = new JLabel();
//		showImage.setIcon(img);
//		pNorth.add(showImage);		

		JPanel pCenter;
		add(pCenter = new JPanel(), BorderLayout.CENTER);
		pCenter.setBackground(gbMain);
		Box x;
		pCenter.add(x = Box.createVerticalBox());
		x.add(Box.createVerticalStrut(30));

		Box x0;
		x.add(x0 = Box.createHorizontalBox());
		x0.add(lblTieuDe = new JLabel("CHƯƠNG TRÌNH QUẢN LÝ BÁN VÉ TÀU TẠI GA"));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 15));
		lblTieuDe.setForeground(Color.RED);
		x.add(Box.createVerticalStrut(30));

		Box x1;
		x.add(x1 = Box.createHorizontalBox());
		x1.add(Box.createHorizontalStrut(50));
		x1.add(lblTaiKhoan = new JLabel("Tài khoản: "));
		x1.add(Box.createHorizontalStrut(20));
		x1.add(txtTaiKhoan = new JTextField());
		x1.add(Box.createHorizontalStrut(50));
		x.add(Box.createVerticalStrut(15));

		Box x2;
		x.add(x2 = Box.createHorizontalBox());
		x2.add(Box.createHorizontalStrut(50));
		x2.add(lblMatKhau = new JLabel("Mật khẩu:  "));
		x2.add(Box.createHorizontalStrut(20));
		x2.add(txtMatKhau = new JPasswordField());
		x2.add(Box.createHorizontalStrut(50));
		x.add(Box.createVerticalStrut(20));

		Box x3;
		x.add(x3 = Box.createHorizontalBox());
		x3.add(btnDangNhap = new JButton("    Đăng nhập    "));
		btnDangNhap.setForeground(Color.CYAN);
		btnDangNhap.setBackground(Color.GRAY);
		x3.add(Box.createHorizontalStrut(20));
		x3.add(btnQuenMK = new JButton("Quên Mật Khẩu"));
		btnQuenMK.setForeground(Color.CYAN);
		btnQuenMK.setBackground(Color.GRAY);
		x.add(Box.createVerticalStrut(30));
		
//		Box x4;
//		x.add(x4 = Box.createHorizontalBox());
//		x4.add(lblnote= new JLabel("Tài Khoản: NV00001    |    Mật khẩu: 123"));

		btnDangNhap.addActionListener(this);
		btnQuenMK.addActionListener(this);
		txtTaiKhoan.addActionListener(this);
		txtMatKhau.addActionListener(this);
		setSize(500, 300);
		// setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
		// (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new UI_DangNhap();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object sou = e.getSource();
		if (sou.equals(btnDangNhap) || sou.equals(txtMatKhau) || sou.equals(txtTaiKhoan)) {
			String taiKhoan = txtTaiKhoan.getText();
			char[] char_matKhau =txtMatKhau.getPassword();
			String matKhau = new String(char_matKhau);
			User user = new User_Dao().getUserTheomaNVFirst(taiKhoan);
//			System.out.println(user);

			if (user != null && taiKhoan.equalsIgnoreCase(user.getNhanVien().getMaNV())
					&&matKhau.equalsIgnoreCase(user.getPassword() )) {
				try {
					UI_Ve frm1 = new UI_Ve(user);
				} catch (SQLException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.dispose();
			} else
				JOptionPane.showMessageDialog(this, "Tên đăng nhập hoặc mật khẩu không đúng");
		} else if (sou.equals(btnQuenMK)) {
			JTextField txtMaNV = new JTextField(10);
			int actionMaNV = JOptionPane.showConfirmDialog(null, txtMaNV, "Nhập mã nhân viên",
					JOptionPane.OK_CANCEL_OPTION);

			JPasswordField txtMatKhau = new JPasswordField(10);

			if (actionMaNV == JOptionPane.OK_OPTION) {
				User user = new User_Dao().getUserTheomaNVFirst(txtMaNV.getText());
				if (user == null) {
					JOptionPane.showInternalMessageDialog(this.getContentPane(),
							"Không có mã nhân viên này trong hệ thống");
				} else {
					int actionPwd = JOptionPane.showConfirmDialog(null, txtMatKhau, "Nhập mật khẩu mới",
							JOptionPane.OK_CANCEL_OPTION);
					if (actionPwd == JOptionPane.OK_OPTION) {
						char[] char_matKhau = txtMatKhau.getPassword();
						String matKhau = new String(char_matKhau);

						new User_Dao().update(new User(user.getId(), matKhau, user.getChucNang(), user.getNhanVien()));
						JOptionPane.showInternalMessageDialog(this.getContentPane(), "Thay đổi mật khẩu thành công");
					}
				}

			}

		}
	}

}
