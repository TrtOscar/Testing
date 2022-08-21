package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.DrugInfoDao;
import model.DrugInfo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class DrugInfoUpdateFrame extends JFrame {

	private JPanel contentPane;
	private JTextField idTxt;
	private JTextField drugnameTxt;
	private JTextField priceTxt;
	private JTextField dateTxt;
	private JTextField useTxt;


	/**
	 * Create the frame.
	 */
	public DrugInfoUpdateFrame(DrugInfoFrame frame, String id) {
		setTitle("药品信息添加");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 493, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("药品编号：");
		label.setBounds(97, 33, 82, 15);
		contentPane.add(label);
		
		idTxt = new JTextField();
		idTxt.setBounds(189, 30, 149, 21);
		contentPane.add(idTxt);
		idTxt.setColumns(10);
		
		JLabel label_1 = new JLabel("药品名称：");
		label_1.setBounds(97, 95, 82, 15);
		contentPane.add(label_1);
		
		drugnameTxt = new JTextField();
		drugnameTxt.setColumns(10);
		drugnameTxt.setBounds(189, 92, 149, 21);
		contentPane.add(drugnameTxt);
		
		JLabel label_2 = new JLabel("价格：");
		label_2.setBounds(97, 156, 82, 15);
		contentPane.add(label_2);
		
		priceTxt = new JTextField();
		priceTxt.setColumns(10);
		priceTxt.setBounds(189, 153, 149, 21);
		contentPane.add(priceTxt);
		
		JLabel label_3 = new JLabel("生产日期：");
		label_3.setBounds(97, 215, 82, 15);
		contentPane.add(label_3);
		
		dateTxt = new JTextField();
		dateTxt.setColumns(10);
		dateTxt.setBounds(189, 212, 149, 21);
		contentPane.add(dateTxt);
		
		JLabel label_4 = new JLabel("使用期限：");
		label_4.setBounds(97, 272, 82, 15);
		contentPane.add(label_4);
		
		useTxt = new JTextField();
		useTxt.setColumns(10);
		useTxt.setBounds(189, 269, 149, 21);
		contentPane.add(useTxt);
		
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idTxt.getText();
				String drugname = drugnameTxt.getText();
				String productdate = dateTxt.getText();
				String drugprice = priceTxt.getText();
				String uselimit = useTxt.getText();
				
				if(id.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入药品编号");
					return;
				}
				
				DrugInfo drugInfo = new DrugInfo(id, drugname, productdate, drugprice, uselimit);
				
				DrugInfoDao.updateDrugInfo(drugInfo);
				
				JOptionPane.showMessageDialog(null, "药品信息修改成功");
				frame.refreshList("");
			}
		});
		button.setBounds(97, 346, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("关闭");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(245, 346, 93, 23);
		contentPane.add(button_1);
		
		List<DrugInfo> drugInfos = DrugInfoDao.getDrugInfos(" and id='"+id+"'");
		
		idTxt.setText(drugInfos.get(0).getId());
		idTxt.setEditable(false);
		drugnameTxt.setText(drugInfos.get(0).getDrugname());
		dateTxt.setText(drugInfos.get(0).getProductdate());
		priceTxt.setText(drugInfos.get(0).getDrugprice());
		useTxt.setText(drugInfos.get(0).getUselimit());
	}
}
