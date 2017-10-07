package CD_Tab4;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * 主界面->修改信息->员工注册
 *
 */
public class EmployeeRegister extends JPanel implements ItemListener, ActionListener {
	JLabel dname, eposition, esex;
	JTextField[] text;
	JComboBox jc;
	JRadioButton employee, dmanager, male, female;
	ButtonGroup bg1, bg2;
	JButton confirm, reset;
	String jctxt = "", bg1txt = "", bg2txt = "", grade = "";
	public EmployeeRegister() {
		setLayout(null);
		dname = new JLabel("        *部门");
		dname.setBounds(50, 10, 70, 25);
		eposition = new JLabel("        *职位");
		eposition.setBounds(50, 45, 70, 25);
		esex = new JLabel("        *性别");
		esex.setBounds(50, 80, 70, 25);
		jc = new JComboBox();
		jc.setBounds(125, 10, 120, 25);
		jc.addItem("请选择部门");
		jc.addItem("总经理办公室");
		jc.addItem("研发部");
		jc.addItem("生产部");
		jc.addItem("销售部");
		jc.addItem("财务部");
		employee = new JRadioButton("职员");
		dmanager = new JRadioButton("经理");
		employee.setBounds(125, 37, 60, 40);
		dmanager.setBounds(185, 37, 60, 40);
		bg1 = new ButtonGroup();
		bg1.add(employee);
		bg1.add(dmanager);
		male = new JRadioButton("男");
		female = new JRadioButton("女");
		male.setBounds(125, 72, 60, 40);
		female.setBounds(185, 72, 60, 40);
		bg2 = new ButtonGroup();
		bg2.add(male);
		bg2.add(female);
		JLabel[] labels = {new JLabel("         *姓名"), new JLabel("*联系电话"), 
				new JLabel("*入职日期"), new JLabel("         *年龄")};
		text = new JTextField[4];
		for(int i=0; i<=3; i++) {
			labels[i].setBounds(50, 115+35*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(120, 115+35*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		text[2].setText("0000-00-00");
		confirm = new JButton("确定");
		reset = new JButton("重置");
		confirm.setBounds(115, 255, 60, 25);
		reset.setBounds(205, 255, 60, 25);
		add(dname);
		add(jc);
		add(eposition);
		add(employee);
		add(dmanager);
		add(esex);
		add(male);
		add(female);
		add(confirm);
		add(reset);
		jc.addItemListener(this);
		employee.addActionListener(this);
		dmanager.addActionListener(this);
		male.addActionListener(this);
		female.addActionListener(this);
		confirm.addActionListener(this);
		reset.addActionListener(this);
	}
	public void itemStateChanged(ItemEvent e) {	
		jctxt = (String)e.getItem().toString();	//得到下拉列表选中的值并返回字符串类型
	}
	public void actionPerformed(ActionEvent e) {
		String ename = text[0].getText().trim();
		String etel = text[1].getText().trim();
		String hiredate = text[2].getText().trim();
		String eage = text[3].getText().trim();
		if(e.getSource()==employee) {	//点击职员单选按钮
			bg1txt = "职员";
			grade = "3";
		} else if(e.getSource()==dmanager) {	//点击经理单选按钮
			bg1txt = "经理";
			grade = "2";
		}
		if(e.getSource()==male) {	//点击男单选按钮
			bg2txt = "男";
		} else if(e.getSource()==female) {	//点击女单选按钮
			bg2txt = "女";
		}
		if(e.getSource()==confirm) {	//点击确定按钮
			String[] data = {jctxt, bg1txt, bg2txt, ename, etel, hiredate, eage, grade};	//组成员工信息数组
			for(String s : data) {
				if(s.equals("")) {	//如果任一项有空值
					JOptionPane.showMessageDialog(null, "所有项为必填项！", "提示 ", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			int flag = JOptionPane.showConfirmDialog(null, "确定注册？", "提示", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION) {	//点击确定
				new DataManagement().registerEmployee(data);
				JOptionPane.showMessageDialog(null, "注册新员工成功！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
				for(int i=0; i<=3; i++) {
					text[i].setText("");	//设置文本框为空
				}
				text[2].setText("0000-00-00");
			}
		} else if(e.getSource()==reset) {
			for(int i=0; i<=3; i++) {
				text[i].setText("");	//设置文本框为空
			}
			text[2].setText("0000-00-00");
		}
	}
}
