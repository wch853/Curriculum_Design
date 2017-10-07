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
 * 普通员工（grade3） 主界面
 * 读入的文件“help.txt”在CD根目录下
 */
public class Employee extends JFrame implements ActionListener {
	JMenuBar bar;
	JMenu user, help;
	JMenuItem cancel, exit, helptxt;
	JTabbedPane jtp; //选项卡面板
	TrayIcon trayIcon; 
	SystemTray tray; // 系统托盘
	PopupMenu pop;	//系统托盘右键弹出菜单（SystemTray只接受awt组件）
	MenuItem show, trayexit;	//右键弹出菜单中的选项
	JDialog helpdialog;	//系统帮助对话框
	JTextArea area;
	JScrollPane js;
	public Employee() {
		setTitle("欢迎使用鹏程管理信息系统！您好，用户" + LoginWindow.user_name 
				+ "  " + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		setSize(860, 580);
		setLocationRelativeTo(null);	//设置窗体水平居中
		setResizable(false);	//设置窗口不可改变大小
		addWindowListener(new WindowAdapter() {	//设置窗体点击关闭按钮最小化到系统托盘  
		    public void windowClosed(WindowEvent e) {	 
		    	setVisible(false);
		    	dispose();	
		    }   
		});
		bar = new JMenuBar();
		user = new JMenu("账号");
		help = new JMenu("帮助");
		cancel = new JMenuItem("注销");
		exit = new JMenuItem("退出");
		helptxt = new JMenuItem("帮助文档");
		jtp = new JTabbedPane();
		jtp.addTab("个人中心", new ImageIcon("个人中心.png"), new _PersonalCenter(), "我的个人中心");
		jtp.addTab("客户信息", new ImageIcon("客户信息.png"), new ClientInfo(), "查询客户信息");
		jtp.addTab("商品图表", new ImageIcon("商品图表.png"), new GoodsChart(), "商品图表");
		setJMenuBar(bar);
		user.add(cancel);
		user.add(exit);
		help.add(helptxt);
		bar.add(user);
		bar.add(help);
		add(jtp);
		tray = SystemTray.getSystemTray();	// 获得本操作系统托盘的实例
		ImageIcon icon = new ImageIcon("系统托盘.png");	// 将要显示到托盘中的图标
		pop = new PopupMenu();	// 构造一个右键弹出式菜单
		show = new MenuItem("显示");
		trayexit = new MenuItem("退出");
		pop.add(show);
		pop.add(trayexit);
		trayIcon = new TrayIcon(icon.getImage(), "鹏程管理信息系统", pop);	//实例化托盘图标
		trayIcon.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount()== 2) {  
					setExtendedState(JFrame.NORMAL);	//设置为正常状态
					setVisible(true);	
				}
			}
		});
		try {
			tray.add(trayIcon);	
	    } catch (AWTException ex) {
	    	ex.printStackTrace();
	    }
		helpdialog = new JDialog(this, "帮助文档");	//帮助文档对话框
		helpdialog.setSize(485, 270);
		helpdialog.setLocationRelativeTo(null);
		area = new JTextArea(20, 1);
		area.setEditable(false);	//设置帮助文档中的文本域不可被编辑
		js = new JScrollPane(area);
		helpdialog.add(js);	//对话框的进度条
		cancel.addActionListener(this);
		exit.addActionListener(this);
		helptxt.addActionListener(this);
		show.addActionListener(this);
		trayexit.addActionListener(this);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==cancel) {	//菜单中的注销
			new LoginWindow();	//弹出登录窗口
			dispose();
		} else if(e.getSource()==exit){	//菜单中的退出
			int flag = JOptionPane.showConfirmDialog(null, "确认退出？", "提示", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION) {
				System.exit(0);	//退出程序
			}
		} else if(e.getSource()==helptxt) {	//将help.txt读入对话框中的文本域
			try {
				FileReader fr = new FileReader("help.txt");	//在CD根目录下
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
		if(e.getSource()==show) {	//点击显示
			setExtendedState(JFrame.NORMAL);	//设置为正常状态
			setVisible(true);
		} else if(e.getSource()==trayexit) {	//点击退出
			int flag = JOptionPane.showConfirmDialog(null, "确认退出？", "提示", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}
	}
}

