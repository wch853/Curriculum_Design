package CD_Tab1;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * 主界面->个人中心->修改密码面板
 *
 */
public class AlterPassword extends JPanel implements ActionListener {
	JButton confirm, reset;
	JPasswordField[] password;
	AlterPassword() {
		setLayout(null);
		JLabel[] prompt = {new JLabel("    *原密码："), new JLabel("    *新密码："), new JLabel("*确认密码：")};
		password = new JPasswordField[3];
		confirm = new JButton("确定");
		reset = new JButton("重置");
		for(int i=0; i<=2; i++) {
			prompt[i].setBounds(50, 10+30*i, 100, 25);
			password[i] = new JPasswordField("", 16);
			password[i].setBounds(120, 10+30*i, 160, 25);
			add(prompt[i]);
			add(password[i]);
		}
		confirm.setBounds(120, 110, 60, 25);
		reset.setBounds(200, 110, 60, 25);
		add(confirm);
		add(reset);
		setVisible(true);
		confirm.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==confirm) {
			String s1 = new String(password[0].getPassword());
			String s2 = new String(password[1].getPassword());
			String s3 = new String(password[2].getPassword());	//得到原密码、新密码、再次输入的新密码
			if(s1.equals("") || s2.equals("") || s3.equals("")) {	//如果有一项为空
				JOptionPane.showMessageDialog(null, "每项均为必填！", "提示", JOptionPane.WARNING_MESSAGE);
			} else if(!s2.equals(s3)) {	//如果两次输入的新密码不同
				JOptionPane.showMessageDialog(null, "两次输入的新密码不同！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {	//调用数据库
				int flag = JOptionPane.showConfirmDialog(null, "确定修改？", "提示", JOptionPane.YES_NO_OPTION);
				if(flag==0) {
					new DataManagement().alterPassword(s1, s2, s3);
					for(int i=0; i<=2; i++) {	//设置三个密码框内容为空
						password[i].setText("");
					}
				}		
			}
		} else if(e.getSource()==reset) {
			for(int i=0; i<=2; i++) {	//设置三个密码框内容为空
				password[i].setText("");
			}	
		}
	}
}

