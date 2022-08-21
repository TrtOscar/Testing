package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.DrugInfoDao;
import dao.DrugInventoryDao;
import model.DrugInfo;
import model.DrugInventory;

/**
 *  药品信息管理
 *
 */
public class DrugInventoryFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JButton but_select;
	private JPanel panel;
	private JTextField idTxt;
	private JButton button_3;
	private JLabel lblNewLabel;
	private JTextField minTxt;
	private JButton button_4;

	public static void main(String[] args) {
		new DrugInventoryFrame().setVisible(true);
	}


	/**
	 * Create the frame.
	 */
	public DrugInventoryFrame() {
		setTitle("药品库存管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 905, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DrugInventoryFrame mainFrame = this;
		model = new DefaultTableModel(new Object[][] {},
				new String[] {"编号", "药品编号", "药品名称", "价格", "剩余库存","是否预警"});

		but_select = new JButton("查询");
		but_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				String id = idTxt.getText();	//获取药品编号
				String drugname = minTxt.getText();	// 获取药品名称

				//拼接查询条件
				StringBuilder builder = new StringBuilder();

				if (id.length() > 0) {
					builder.append(" and drugid like '%"+id+"%'");
				}

				if(drugname.length() > 0) {
					builder.append(" and drugname like '%"+drugname+"%'");
				}

				//根据条件查询并刷新表格
				refreshList(builder.toString());

			}
		});
		but_select.setBounds(527, 25, 91, 27);
		contentPane.add(but_select);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "药品库存列表", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(14, 82, 861, 488);
		contentPane.add(panel);
		panel.setLayout(null);

		table = new JTable();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(15, 28, 832, 447);
		panel.add(scrollPane);

		JLabel lblNewLabel_1 = new JLabel("药品编号:");
		lblNewLabel_1.setBounds(14, 29, 74, 18);
		contentPane.add(lblNewLabel_1);

		idTxt = new JTextField();
		idTxt.setBounds(80, 27, 98, 24);
		contentPane.add(idTxt);
		idTxt.setColumns(10);

		button_3 = new JButton("退出");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

				new LoginFrame().setVisible(true);
			}
		});
		button_3.setBounds(301, 592, 113, 27);
		contentPane.add(button_3);

		lblNewLabel = new JLabel("药品名称:");
		lblNewLabel.setBounds(204, 29, 68, 18);
		contentPane.add(lblNewLabel);

		minTxt = new JTextField();
		minTxt.setColumns(10);
		minTxt.setBounds(266, 27, 98, 24);
		contentPane.add(minTxt);

		button_4 = new JButton("药品入库");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DrugInventoryAddFrame(mainFrame).setVisible(true);
			}
		});
		button_4.setBounds(14, 592, 113, 27);
		contentPane.add(button_4);

		JButton button_3_1 = new JButton("返回");
		button_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new DrugInfoFrame().setVisible(true);
			}
		});
		button_3_1.setBounds(160, 592, 113, 27);
		contentPane.add(button_3_1);

		// 刷新表格
		refreshList("");
	}


	public void refreshList(String condition) {
		// 清空表格内容
		model.setRowCount(0);

		List<DrugInventory> drugInventories = DrugInventoryDao.getDrugInventorys(condition);

		// 将查询出来的结果集填充到表格中
		for(DrugInventory item : drugInventories) {
			model.insertRow(table.getRowCount(),
					new Object[] {item.getId(),item.getDrugid(), item.getDrugname(),item.getDrugprice(),item.getInventory(),item.getInventory()<20?"库存不足":"正常"});
		}
	}
}
