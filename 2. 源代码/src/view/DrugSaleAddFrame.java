package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DrugInfoDao;
import dao.DrugInventoryDao;
import dao.DrugSaleDao;
import model.DrugInfo;
import model.DrugInventory;
import model.DrugSale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class DrugSaleAddFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField numTxt;
	private JTextField paymoneyTxt;
	private JTextField customerTxt;
	private JTextField buytimeTxt;

	/**
	 * Create the frame.
	 */
	public DrugSaleAddFrame(DrugSaleFrame frame) {
		setTitle("添加销售记录");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 468);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("编号：");
		label.setBounds(96, 22, 77, 15);
		contentPane.add(label);
		
		idTxt = new JTextField();
		idTxt.setBounds(178, 19, 172, 21);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("药品名称：");
		label_1.setBounds(96, 71, 77, 15);
		contentPane.add(label_1);
		JComboBox drugnameCB = new JComboBox();
		drugnameCB.setBounds(178, 68, 172, 21);
		contentPane.add(drugnameCB);
		
		List<DrugInfo> drugInfos = DrugInfoDao.getDrugInfos("");
		for(DrugInfo item : drugInfos) {
			drugnameCB.addItem(item.getDrugname());
		}
		
		JLabel label_2 = new JLabel("数量：");
		label_2.setBounds(96, 128, 77, 15);
		contentPane.add(label_2);
		
		numTxt = new JTextField();
		numTxt.setColumns(10);
		numTxt.setBounds(178, 125, 172, 21);
		contentPane.add(numTxt);
		
		JLabel label_3 = new JLabel("支付金额：");
		label_3.setBounds(96, 178, 77, 15);
		contentPane.add(label_3);
		
		paymoneyTxt = new JTextField();
		paymoneyTxt.setColumns(10);
		paymoneyTxt.setBounds(178, 175, 172, 21);
		contentPane.add(paymoneyTxt);
		
		JLabel label_4 = new JLabel("客户名：");
		label_4.setBounds(96, 232, 77, 15);
		contentPane.add(label_4);
		
		customerTxt = new JTextField();
		customerTxt.setColumns(10);
		customerTxt.setBounds(178, 229, 172, 21);
		contentPane.add(customerTxt);
		
		JLabel label_5 = new JLabel("购买时间：");
		label_5.setBounds(96, 291, 77, 15);
		contentPane.add(label_5);
		
		buytimeTxt = new JTextField();
		buytimeTxt.setColumns(10);
		buytimeTxt.setBounds(178, 288, 172, 21);
		contentPane.add(buytimeTxt);
		
		JButton button = new JButton("添加");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTxt.getText();
				String paymoney = paymoneyTxt.getText();
				String customer = customerTxt.getText();
				String buytime = buytimeTxt.getText();
				
				if(id.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入药品编号");
					return;
				}
				
				// 检查编号是否存在
				String condition = " and id='"+id+"'";
				List<DrugSale> drugSales = DrugSaleDao.getDrugSales(condition);
				
				if(drugSales.size() > 0) {
					JOptionPane.showMessageDialog(null, "该编号已存在");
					return;
				}
				
				int num = 0;
				try {
					num = Integer.valueOf(numTxt.getText());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "请正确填写数量");
					return;
				}
				
				int index = drugnameCB.getSelectedIndex();
				DrugInfo drugInfo = drugInfos.get(index);
				DrugSale drugSale = new DrugSale(id, drugInfo.getId(), drugInfo.getDrugname(), drugInfo.getDrugprice(), num, paymoney, customer, buytime);
				
				// 更改库存数量
				condition = " and drugid='"+drugInfo.getId()+"'";
				List<DrugInventory> drugInvents = DrugInventoryDao.getDrugInventorys(condition);
				
				if(drugInvents.size() == 0) {
					JOptionPane.showMessageDialog(null, "暂无该药品的库存信息");
					return;
				}else {
					if(drugInvents.get(0).getInventory() < num) {
						JOptionPane.showMessageDialog(null, "库存不足");
						return;
					}else {
						drugInvents.get(0).setInventory(drugInvents.get(0).getInventory() - num);
						
						DrugInventoryDao.updateDrugInventory(drugInvents.get(0));
					}
				}
				
				DrugSaleDao.insertDrugSale(drugSale);
				
				JOptionPane.showMessageDialog(null, "添加成功");
				frame.refreshList("");
			}
		});
		button.setBounds(96, 358, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("返回");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(255, 358, 93, 23);
		contentPane.add(button_1);
	}
}
