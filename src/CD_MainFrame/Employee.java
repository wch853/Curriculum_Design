package CD_MainFrame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import CD_Login.LoginWindow;
import CD_Tab1.*;
import CD_Tab2.*;
import CD_Tab3.GoodsChart;

/**
 * ��ͨԱ����grade3�� ������
 * ������ļ���help.txt����CD��Ŀ¼��
 */
public class Employee extends JFrame implements ActionListener {
	JMenuBar bar;
	JMenu user, help;
	JMenuItem cancel, exit, helptxt;
	JTabbedPane jtp; //ѡ����
	TrayIcon trayIcon; 
	SystemTray tray; // ϵͳ����
	PopupMenu pop;	//ϵͳ�����Ҽ������˵���SystemTrayֻ����awt�����
	MenuItem show, trayexit;	//�Ҽ������˵��е�ѡ��
	JDialog helpdialog;	//ϵͳ�����Ի���
	JTextArea area;
	JScrollPane js;
	public Employee() {
		setTitle("��ӭʹ�����̹�����Ϣϵͳ�����ã��û�" + LoginWindow.user_name 
				+ "  " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		setSize(860, 580);
		setLocationRelativeTo(null);	//���ô���ˮƽ����
		setResizable(false);	//���ô��ڲ��ɸı��С
		addWindowListener(new WindowAdapter() {	//���ô������رհ�ť��С����ϵͳ����  
		    public void windowClosed(WindowEvent e) {	 
		    	setVisible(false);
		    	dispose();	
		    }   
		});
		bar = new JMenuBar();
		user = new JMenu("�˺�");
		help = new JMenu("����");
		cancel = new JMenuItem("ע��");
		exit = new JMenuItem("�˳�");
		helptxt = new JMenuItem("�����ĵ�");
		jtp = new JTabbedPane();
		jtp.addTab("��������", new ImageIcon("��������.png"), new _PersonalCenter(), "�ҵĸ�������");
		jtp.addTab("�ͻ���Ϣ", new ImageIcon("�ͻ���Ϣ.png"), new ClientInfo(), "��ѯ�ͻ���Ϣ");
		jtp.addTab("��Ʒͼ��", new ImageIcon("��Ʒͼ��.png"), new GoodsChart(), "��Ʒͼ��");
		setJMenuBar(bar);
		user.add(cancel);
		user.add(exit);
		help.add(helptxt);
		bar.add(user);
		bar.add(help);
		add(jtp);
		tray = SystemTray.getSystemTray();	// ��ñ�����ϵͳ���̵�ʵ��
		ImageIcon icon = new ImageIcon("ϵͳ����.png");	// ��Ҫ��ʾ�������е�ͼ��
		pop = new PopupMenu();	// ����һ���Ҽ�����ʽ�˵�
		show = new MenuItem("��ʾ");
		trayexit = new MenuItem("�˳�");
		pop.add(show);
		pop.add(trayexit);
		trayIcon = new TrayIcon(icon.getImage(), "���̹�����Ϣϵͳ", pop);	//ʵ��������ͼ��
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()== 2) {  
					setExtendedState(JFrame.NORMAL);	//����Ϊ����״̬
					setVisible(true);	
				}
			}
		});
		try {
			tray.add(trayIcon);	
	    } catch (AWTException ex) {
	    	ex.printStackTrace();
	    }
		helpdialog = new JDialog(this, "�����ĵ�");	//�����ĵ��Ի���
		helpdialog.setSize(485, 270);
		helpdialog.setLocationRelativeTo(null);
		area = new JTextArea(20, 1);
		area.setEditable(false);	//���ð����ĵ��е��ı��򲻿ɱ��༭
		js = new JScrollPane(area);
		helpdialog.add(js);	//�Ի���Ľ�����
		cancel.addActionListener(this);
		exit.addActionListener(this);
		helptxt.addActionListener(this);
		show.addActionListener(this);
		trayexit.addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cancel) {	//�˵��е�ע��
			new LoginWindow();	//������¼����
			dispose();
		} else if(e.getSource()==exit){	//�˵��е��˳�
			int flag = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION) {
				System.exit(0);	//�˳�����
			}
		} else if(e.getSource()==helptxt) {	//��help.txt����Ի����е��ı���
			try {
				FileReader fr = new FileReader("help.txt");	//��CD��Ŀ¼��
				BufferedReader br = new BufferedReader(fr);
				String s;
				while((s=br.readLine())!=null) {
					area.append(s + "\n");
				}
				br.close();
				fr.close();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			helpdialog.setVisible(true);
		}
		if(e.getSource()==show) {	//�����ʾ
			setExtendedState(JFrame.NORMAL);	//����Ϊ����״̬
			setVisible(true);
		} else if(e.getSource()==trayexit) {	//����˳�
			int flag = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "��ʾ", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}

