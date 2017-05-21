package CD_Tab1;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * ������->��������->�޸��������
 *
 */
public class AlterPassword extends JPanel implements ActionListener {
	JButton confirm, reset;
	JPasswordField[] password;
	AlterPassword() {
		setLayout(null);
		JLabel[] prompt = {new JLabel("    *ԭ���룺"), new JLabel("    *�����룺"), new JLabel("*ȷ�����룺")};
		password = new JPasswordField[3];
		confirm = new JButton("ȷ��");
		reset = new JButton("����");
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
			String s3 = new String(password[2].getPassword());	//�õ�ԭ���롢�����롢�ٴ������������
			if(s1.equals("") || s2.equals("") || s3.equals("")) {	//�����һ��Ϊ��
				JOptionPane.showMessageDialog(null, "ÿ���Ϊ���", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if(!s2.equals(s3)) {	//�����������������벻ͬ
				JOptionPane.showMessageDialog(null, "��������������벻ͬ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {	//�������ݿ�
				int flag = JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�", "��ʾ", JOptionPane.YES_NO_OPTION);
				if(flag==0) {
					new DataManagement().alterPassword(s1, s2, s3);
					for(int i=0; i<=2; i++) {	//�����������������Ϊ��
						password[i].setText("");
					}
				}		
			}
		} else if(e.getSource()==reset) {
			for(int i=0; i<=2; i++) {	//�����������������Ϊ��
				password[i].setText("");
			}	
		}
	}
}

