package CD_Tab2;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import CD_DataBase.DataManagement;

/**
 * 主界面->客户信息（提供多种类型的客户信息查询功能）
 *
 */
public class ClientInfo extends JPanel implements ItemListener, ActionListener {
	JPanel p;
	JLabel prompt1, prompt2;
	JComboBox jc;
	JTextField input;
	JButton fuzzyquery, fullquery;
	JTable table;
	DefaultTableModel model;
	JScrollPane js;
	String jctxt = ""; 
	String intxt;
	public ClientInfo() {
		setLayout(new BorderLayout());
		prompt1 = new JLabel();
		jc = new JComboBox();
		jc.addItem("查询方式");
		jc.addItem("按会员编号查询");
		jc.addItem("按客户姓名查询");
		jc.addItem("按客户性别查询");
		prompt2 = new JLabel("请输入查询关键字:");
		input = new JTextField(16);
		fuzzyquery = new JButton("查询（模糊查询）");
		fullquery = new JButton("全部查询");
		p = new JPanel();
		p.add(prompt1);
		p.add(jc);
		p.add(prompt2);
		p.add(input);
		p.add(fuzzyquery);
		p.add(fullquery);
		add(p, BorderLayout.NORTH);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"会员编号", "姓名", "联系电话", "性别", "年龄"});
		table = new JTable(model);	
		table.setEnabled(false);	//设置表格不可被编辑
		js = new JScrollPane(table);
		jc.addItemListener(this);
		fuzzyquery.addActionListener(this);
		fullquery.addActionListener(this);
		add(js, BorderLayout.CENTER);
	}
	public void itemStateChanged(ItemEvent e) {	//得到下拉列表选中的值并返回字符串类型
		jctxt = (String)e.getItem().toString(); 
	}
	public void actionPerformed(ActionEvent e) {
		model.setRowCount(0);	//用于清空上次查询的内容
		if(e.getSource()==fuzzyquery) {	//点击查询（模糊查询）按钮
			intxt = input.getText().trim();	//得到关键字文本框的字符串
			if(jctxt.equals("")) {	//如果没有选择查询类型
				JOptionPane.showMessageDialog(this, "请选择查询方式！", "提示", JOptionPane.WARNING_MESSAGE);
			} else {
				if(intxt.trim().equals("")) {	//判断输入的关键字是否为空
					JOptionPane.showMessageDialog(this, "查询关键字不可为空！", "提示", JOptionPane.WARNING_MESSAGE);
				} else{
					new DataManagement().queryClient(jctxt, intxt, model);
				}
			}
		} else if(e.getSource()==fullquery) {	//点击全部查询按钮
			new DataManagement().queryAllClient(model);
		}
		if(model.getRowCount()==0) {	//表格为空
			JOptionPane.showMessageDialog(null, "没有记录！", "提示", JOptionPane.WARNING_MESSAGE);
		}
	}
}

