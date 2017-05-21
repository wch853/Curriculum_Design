package CD_Tab5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * 高级管理->发布任务
 *
 */
public class SetMission extends JPanel {
	JTextArea area;
	JPanel p;
	JButton alter, reset;
	SetMission() {
		setLayout(new BorderLayout());
		area = new JTextArea();
		area.setFont(new Font("Dialog", Font.BOLD, 40));
		area.setText(new DataManagement().getMission());
		p = new JPanel();
		alter = new JButton("修改");
		reset = new JButton("重置");
		p.add(alter);
		p.add(reset);
		add(area, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		alter.addActionListener(new InnerListen());
		reset.addActionListener(new InnerListen());
	}
	class InnerListen implements ActionListener {	//使用内部类进行事件响应
		public void actionPerformed(ActionEvent e) {
			String text = area.getText();
			if(e.getSource()==alter) {	//点击修改按钮
				new DataManagement().setAreaText(text);
				JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			} else if(e.getSource()==reset) {	//点击重置按钮
				area.setText(null);
			}
		}
	}
}
