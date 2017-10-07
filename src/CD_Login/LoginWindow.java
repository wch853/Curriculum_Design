package CD_Login;

import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import CD_DataBase.DataManagement;
import CD_MainFrame.*;

public class LoginWindow extends JFrame implements ActionListener {
	JTextField untxt;
	JPasswordField pwtxt;
	JButton login, cancel;
	BackgroundPanel background;
	public static String user_name;
	public LoginWindow() {
		setTitle("欢迎使用鹏程管理信息系统    " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		setSize(720, 430);
		setLocationRelativeTo(null);	//���ô���ˮƽ����
		setResizable(false);	//���ò������Ŵ���
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		untxt = new JTextField(16);
		pwtxt = new JPasswordField(16);
		login = new JButton("��¼");
		cancel = new JButton("ȡ��");
		untxt.setBounds(300, 180, 150, 25);
		pwtxt.setBounds(300, 215, 150, 25);
		login.setBounds(305, 250, 60, 25);
		cancel.setBounds(380, 250, 60, 25);
		background = new BackgroundPanel();
		background.setLayout(null);
		background.add(untxt);
		background.add(pwtxt);
		background.add(login);
		background.add(cancel);	//�ڱ���ͼƬ�����������
		add(background);
		pwtxt.addActionListener(this);	//�������밴�س�����ʾ��¼
		login.addActionListener(this);
		cancel.addActionListener(this);	//Ϊ��¼��ȡ����ťע�����
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		String username = untxt.getText();
		String password = new String(pwtxt.getPassword());	//ȡ��������û���������
		int grade = 0;	
		if(e.getSource()==login || e.getSource()==pwtxt) {	//ѡ���¼
			if(username.trim().equals("") || password.trim().equals("")) {	//����Ϊ�գ�������ʾ��
				JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ�", "��¼����", JOptionPane.WARNING_MESSAGE);
			} else {
				grade = new DataManagement().checkUser(username, password);
				if(grade==0) {	//�����û���1��2��3����grade���ȼ�������grade=0���û�������
					JOptionPane.showMessageDialog(null, "�û������������", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
				} else {
					setVisible(false);
					LoginWindow.user_name = username;	//���ݵ�¼�û��������Ϣ
					switch(grade) {	//����Ա���ȼ��Ĳ�ͬ��������ͬ���û�����
					case 3: new Employee();	//��ͨԱ��������
					break;
					case 2: new DepartmentManager();	//���ž���������
					break;
					case 1: new GeneralManager();	//�ܾ���������
					break;
					}
				}
			}
		} else {	//���ȡ����ť
			System.exit(0);	//�˳�����
		}
	}
	public static void main(String[] args) {
		new LoginWindow();
	}
}


