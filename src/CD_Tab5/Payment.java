package CD_Tab5;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * 高级管理->薪资管理
 *
 */
public class Payment extends JPanel implements ActionListener {
	JTextField[] text;
	JButton query, alter, reset;
	public Payment() {
		setLayout(null);
		JLabel[] labels = {new JLabel("请输入员工工号"), new JLabel("修改：  *月薪")};
		text = new JTextField[2];
		for(int i=0; i<=1; i++) {
			labels[i].setBounds(50, 10+30*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(150, 10+30*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		text[1].setText("0.00");
		query = new JButton("查询");
		alter = new JButton("修改");
		reset = new JButton("重置");
		query.setBounds(320, 10, 60, 25);
		alter.setBounds(155, 75, 60, 25);
		reset.setBounds(240, 75, 60, 25);
		add(query);
		add(alter);
		add(reset);
		query.addActionListener(this);
		alter.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String emp_id = text[0].getText().trim();	//得到输入的会员编号
		String income = text[1].getText().trim();
		if(e.getSource()==reset) {	//点击重置按纽
			text[0].setText("");
			text[1].setText("0.00");
		} else if(e.getSource()==query) {	//点击查询按钮
			if(emp_id.equals("")){	//会员编号文本框输入为空
				JOptionPane.showMessageDialog(null, "请输入员工工号！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				String incomequery = new DataManagement().queryIncome(emp_id);
				if(incomequery==null) {
					JOptionPane.showMessageDialog(null, "没有记录！", "提示", JOptionPane.WARNING_MESSAGE);
				} else {
					text[1].setText(incomequery);
				}
			}
		} else if(e.getSource()==alter) {	//点击修改按钮
			if(emp_id.equals("") ) {	//关键字文本框为空
				JOptionPane.showMessageDialog(null, "请输入员工工号并查询！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				int flag = JOptionPane.showConfirmDialog(null, "确认修改？", "提示", JOptionPane.YES_NO_OPTION);
				if(flag==0) {	//点击确认
					String incomequery = new DataManagement().queryIncome(emp_id);
					if(incomequery==null) {
						JOptionPane.showMessageDialog(null, "没有记录！", "提示", JOptionPane.WARNING_MESSAGE);
					} else {
						new DataManagement().alterEmployee_Alter(emp_id, income);
						JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}
