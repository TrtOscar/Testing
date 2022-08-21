package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DrugInfoDao;
import dao.DrugInventoryDao;
import model.DrugInfo;
import model.DrugInventory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DrugInventoryAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField numTxt;
	private JTextField idTxt;


	/**
	 * Create the frame.
	 */
	public DrugInventoryAddFrame(DrugInventoryFrame frame) {
		setTitle("药品入库");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("选择入库商品：");
		label.setBounds(71, 91, 124, 15);
		contentPane.add(label);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(178, 88, 184, 21);
		contentPane.add(comboBox);
		
		List<DrugInfo> drugInfos = DrugInfoDao.getDrugInfos("");
		for(DrugInfo item : drugInfos) {
			comboBox.addItem(item.getDrugname());
		}
		
		JLabel label_1 = new JLabel("入库数量：");
		label_1.setBounds(71, 163, 93, 15);
		contentPane.add(label_1);
		
		numTxt = new JTextField();
		numTxt.setBounds(178, 160, 184, 21);
		contentPane.add(numTxt);
		numTxt.setColumns(10);
		
		JButton button = new JButton("入库");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTxt.getText();
				
				if(id.length() == 0) {
					JOptionPane.showMessageDialog(null, "请填写编号");
					return;
				}
				
				// 检查编号是否存在
				String condition = " and id='"+id+"'";
				List<DrugInventory> drugInvents = DrugInventoryDao.getDrugInventorys(condition);
				
				if(drugInvents.size() > 0) {
					JOptionPane.showMessageDialog(null, "该编号已存在");
					return;
				}
				
				int inventory = 0;
				try {
					inventory = Integer.valueOf(numTxt.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "请正确填写数量");
					return;
				}
				
				int index = comboBox.getSelectedIndex();
				DrugInfo drugInfo = drugInfos.get(index);
				
				// 检查该药品库存是否已经存在
				condition = " and drugid='"+drugInfo.getId()+"'";
				DrugInventory drugInventory = null;
				List<DrugInventory> drugInventories = DrugInventoryDao.getDrugInventorys(condition);
				
				if(drugInventories.size() > 0) {
					drugInventory = drugInventories.get(0);
					drugInventory.setInventory(drugInventory.getInventory() + inventory);
					DrugInventoryDao.updateDrugInventory(drugInventory);
				}else {
					drugInventory = new DrugInventory(id, drugInfo.getId(), drugInfo.getDrugname(), inventory, drugInfo.getDrugprice());
					DrugInventoryDao.insertDrugInventory(drugInventory);
				}
				
				
				
				JOptionPane.showMessageDialog(null, "入库成功");
				frame.refreshList("");
			}
		});
		button.setBounds(71, 215, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(269, 215, 93, 23);
		contentPane.add(button_1);
		
		JLabel label_2 = new JLabel("编号：");
		label_2.setBounds(71, 36, 54, 15);
		contentPane.add(label_2);
		
		idTxt = new JTextField();
		idTxt.setColumns(10);
		idTxt.setBounds(178, 33, 184, 21);
		contentPane.add(idTxt);
	}
}
