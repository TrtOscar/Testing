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
import model.DrugInfo;

/**
 *  药品信息管理
 *
 */
public class DrugInfoFrame extends JFrame {

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
		new DrugInfoFrame().setVisible(true);
	}
	

	/**
	 * Create the frame.
	 */
	public DrugInfoFrame() {
		setTitle("药品信息管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 905, 685);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DrugInfoFrame mainFrame = this;
		model = new DefaultTableModel(new Object[][] {},
				new String[] {"药品编号", "药品名称", "价格", "生产日期", "使用年限"});

		but_select = new JButton("查询");
		but_select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				String id = idTxt.getText();	//获取药品编号
				String drugname = minTxt.getText();	// 获取药品名称

				//拼接查询条件
				StringBuilder builder = new StringBuilder();

				if (id.length() > 0) {
					builder.append(" and id like '%"+id+"%'");
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
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "药品信息列表", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		JButton button = new JButton("新增");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DrugInfoAddFrame addFrame = new DrugInfoAddFrame(mainFrame);

				addFrame.setVisible(true);
			}
		});
		button.setBounds(24, 583, 113, 27);
		contentPane.add(button);
		
		JButton button_1 = new JButton("修改");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "请选择药品信息");
					return;
				}

				String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				
				DrugInfoUpdateFrame editframe = new DrugInfoUpdateFrame(mainFrame, id);

				editframe.setVisible(true);
			}
		});
		button_1.setBounds(159, 583, 113, 27);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("删除");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "请选择药品信息");
					return;
				}

				String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));


				// 删除药品信息
				DrugInfoDao.deleteDrugInfo(id);

				// 刷新列表
				refreshList("");
			}
		});
		button_2.setBounds(292, 583, 113, 27);
		contentPane.add(button_2);
		
		button_3 = new JButton("退出");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();
				
				new LoginFrame().setVisible(true);
			}
		});
		button_3.setBounds(569, 583, 113, 27);
		contentPane.add(button_3);
		
		lblNewLabel = new JLabel("药品名称:");
		lblNewLabel.setBounds(204, 29, 68, 18);
		contentPane.add(lblNewLabel);
		
		minTxt = new JTextField();
		minTxt.setColumns(10);
		minTxt.setBounds(266, 27, 98, 24);
		contentPane.add(minTxt);
		
		button_4 = new JButton("药品库存管理");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				
				new DrugInventoryFrame().setVisible(true);
			}
		});
		button_4.setBounds(435, 583, 113, 27);
		contentPane.add(button_4);

		// 刷新表格
		refreshList("");
	}

	
	public void refreshList(String condition) {
		// 清空表格内容
		model.setRowCount(0);
		
		List<DrugInfo> drugInfos = DrugInfoDao.getDrugInfos(condition);

		// 将查询出来的结果集填充到表格中
		for(DrugInfo item : drugInfos) {
			model.insertRow(table.getRowCount(),
					new Object[] {item.getId(), item.getDrugname(),item.getDrugprice(),item.getProductdate(),item.getUselimit()});
		}
	}
}
