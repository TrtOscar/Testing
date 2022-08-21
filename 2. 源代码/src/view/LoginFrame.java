package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPasswordField;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginFrame frame = new LoginFrame();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("登录界面");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("用户名：");
		label.setBounds(85, 74, 72, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setBounds(167, 71, 180, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("密码：");
		label_1.setBounds(85, 142, 54, 15);
		contentPane.add(label_1);

		JLabel label_1_1 = new JLabel("角色：");
		label_1_1.setBounds(85, 195, 54, 15);
		contentPane.add(label_1_1);

		String[] listData = new String[] { "管理员", "营业员" };
		JComboBox comboBox = new JComboBox(listData);
		comboBox.setBounds(167, 192, 180, 21);
		contentPane.add(comboBox);

		JButton button = new JButton("登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String password = passwordTxt.getText();
				String role = comboBox.getSelectedItem().toString();
				
				User user = UserDao.userLogin(id, password, role);

				if (role.equals("管理员")) {
					if (user == null) {
						JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新登录");
					} else {
						dispose();

						// 跳转到管理员的主界面
						new DrugInfoFrame().setVisible(true);
					}
				} else if (role.equals("营业员")) {
					if (user == null) {
						JOptionPane.showMessageDialog(null, "用户名或密码错误，请重新登录");
					} else {
						dispose();

						// 跳转到营业员的主界面
						new DrugSaleFrame().setVisible(true);
					}
				}
			}
		});
		button.setBounds(167, 258, 93, 23);
		contentPane.add(button);

		JLabel label_2 = new JLabel("药品库存管理系统");
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		label_2.setBounds(112, 20, 235, 29);
		contentPane.add(label_2);

		passwordTxt = new JPasswordField();
		passwordTxt.setBounds(167, 139, 180, 21);
		contentPane.add(passwordTxt);
	}
}
