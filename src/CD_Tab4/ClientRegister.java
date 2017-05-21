package CD_Tab4;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * 主界面->修改信息->客户注册
 *
 */
public class ClientRegister extends JPanel implements ActionListener { 
	JTextField[] text;
	JRadioButton male, female;
	ButtonGroup bg;
	JButton confirm, reset;
	String csex = "";
	public ClientRegister() {
		setLayout(null);
		JLabel[] labels = {new JLabel("        *姓名"), 
				new JLabel("*联系电话"), new JLabel("	          年龄")};
		text = new JTextField[4];
		for(int i=0; i<=2; i++) {
			labels[i].setBounds(50, 10+35*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(120, 10+35*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		text[2].setText("0");
		male = new JRadioButton("男");
		female = new JRadioButton("女");
		bg = new ButtonGroup();
		male.setBounds(130, 105, 40, 40);
		female.setBounds(190, 105, 40, 40);
		bg.add(male);
		bg.add(female);
		add(male);
		add(female);
		confirm = new JButton("确定");
		reset = new JButton("重置");
		confirm.setBounds(100, 150, 60, 25);
		reset.setBounds(195, 150, 60, 25);
		add(confirm);
		add(reset);
		male.addActionListener(this);
		female.addActionListener(this);
		confirm.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String cname = text[0].getText().trim();
		String ctel = text[1].getText().trim();
		String cage = text[2].getText().trim();
		if(e.getSource()==male) {
			csex = "男";
		} else if(e.getSource()==female) {
			csex = "女";
		}
		if(e.getSource()==confirm) {
			if(cname.equals("") || ctel.equals("")) {	//如果姓名或联系电话项为空
				JOptionPane.showMessageDialog(null, "姓名和联系电话为必填项！", "注册失败", JOptionPane.ERROR_MESSAGE);
			} else if(cage.length()>3) {	//如果填写的年龄大于规定字长
				JOptionPane.showMessageDialog(null, "请输入正确的年龄！", "提示", JOptionPane.WARNING_MESSAGE);
			}else {
				int flag = JOptionPane.showConfirmDialog(null, "确定注册？", "提示", JOptionPane.YES_NO_OPTION);
				if(flag==0) {
					String[] data = {cname, ctel, cage, csex};
					new DataManagement().registerClient(data);
					JOptionPane.showMessageDialog(null, "注册新会员成功！", "注册成功", JOptionPane.INFORMATION_MESSAGE);
					for(int i=0; i<=2; i++) {
						text[i].setText("");
					}
				}
			}
		} else if(e.getSource()==reset) {
			for(int i=0; i<=2; i++) {
				text[i].setText("");
			}
		}
	}
}
