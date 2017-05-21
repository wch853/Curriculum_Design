package CD_Tab4;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * 主界面->修改信息->客户修改
 *
 */
public class ClientAlter extends JPanel implements ActionListener {
	JTextField[] text;
	JButton query, delete, alter, reset;
	public ClientAlter() {
		setLayout(null);
		JLabel[] labels = {new JLabel("请输入会员编号"), new JLabel("修改：  *姓名"), 
					new JLabel("       *联系电话"), 
					new JLabel("                 年龄"), new JLabel("                 性别")};
		text = new JTextField[5];
		for(int i=0; i<=4; i++) {
			labels[i].setBounds(50, 10+30*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(150, 10+30*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		query = new JButton("查询");
		delete = new JButton("删除会员");
		alter = new JButton("修改");
		reset = new JButton("重置");
		query.setBounds(320, 10, 60, 25);
		delete.setBounds(390, 10, 90, 25);
		alter.setBounds(155, 170, 60, 25);
		reset.setBounds(240, 170, 60, 25);
		add(query);
		add(delete);
		add(alter);
		add(reset);
		query.addActionListener(this);
		delete.addActionListener(this);
		alter.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String cli_id = text[0].getText().trim();	//得到输入的会员编号
		String cname = text[1].getText().trim();
		String ctel = text[2].getText().trim();
		String cage = text[3].getText().trim();
		String csex = text[4].getText().trim();
		if(e.getSource()==reset) {	//如果点击重置按纽
			for(int i=1; i<=4; i++) {
				text[i].setText("");
			}
		} else if(e.getSource()==query) {	//如果点击查询按钮
			if(cli_id.equals("")){	//会员编号文本框输入为空
				JOptionPane.showMessageDialog(null, "请输入会员编号！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				String[] data = new DataManagement().alterClient_Query(cli_id);
				if(data==null) {	//如果返回数组为空
					JOptionPane.showMessageDialog(null, "没有记录！", "提示", JOptionPane.WARNING_MESSAGE);
				} else {
					for(int i=1; i<=4; i++) {	//将查询到的信息数组打印在文本框中
						text[i].setText(data[i-1]);
					}
				}
			}
		} else if(e.getSource()==delete) {	//如果点击删除按钮
			if(cli_id.equals("")){	//与查询相同
				JOptionPane.showMessageDialog(null, "请输入会员编号！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				String[] data = new DataManagement().alterClient_Query(cli_id);
				if(data==null) {
					JOptionPane.showMessageDialog(null, "没有记录！", "提示", JOptionPane.WARNING_MESSAGE);
				} else { //如果有记录
					int flag = JOptionPane.showConfirmDialog(null, "确认删除？", "提示", JOptionPane.YES_NO_OPTION);
					if(flag==0) {	//如果在弹出确认对话框中点“是”
						new DataManagement().alterClient_Delete(cli_id);
						JOptionPane.showMessageDialog(null, "删除成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} else if(e.getSource()==alter) {	//如果点击修改按钮
			if(cli_id.equals("") ) {	//如果关键字文本框为空
				JOptionPane.showMessageDialog(null, "请输入会员编号并查询！", "提示", JOptionPane.WARNING_MESSAGE);
			} else if(cname.equals("") || ctel.equals("")) {	//如果姓名或联系电话为空
				JOptionPane.showMessageDialog(null, "姓名和联系电话为必填项！", "提示", JOptionPane.WARNING_MESSAGE);
			} else if(cage.length()>3 || !csex.equals("男") && !csex.equals("女")) {	//如果填写信息不符合规定
				JOptionPane.showMessageDialog(null, "请输入正确的信息！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				int flag = JOptionPane.showConfirmDialog(null, "确认修改？", "提示", JOptionPane.YES_NO_OPTION);
				if(flag==0) {	//如果在弹出确认对话框中点“是”
					String[] data = new DataManagement().alterClient_Query(cli_id);
					if(data==null) {	//如果返回数组为空
						JOptionPane.showMessageDialog(null, "没有记录！", "提示", JOptionPane.WARNING_MESSAGE);
					} else {
						String[] data2 = {cli_id, cname, ctel, cage, csex};
						new DataManagement().alterClient_Alter(data2);
						JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}
