package CD_Tab3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * 主界面->商品图表
 *
 */
public class GoodsChart extends JPanel implements ItemListener, ActionListener{
	private JPanel p;
	private JComboBox quarter;  //下拉框组件
	private JLabel label;     	//标签
	private JButton bt;
	String s = "";
	public GoodsChart() {
		label = new JLabel();
		label.setText("季度");
		quarter = new JComboBox();
		//添加下拉框选项
		quarter.addItem("选择季度");	
		quarter.addItem("第一季度");
		quarter.addItem("第二季度");
		quarter.addItem("第三季度");
		quarter.addItem("第四季度");
		bt = new JButton("确定");
		p = new JPanel();
		p.add(label);
		p.add(quarter);
		p.add(bt);
		add(p,BorderLayout.NORTH);
		quarter.addItemListener(this);
		bt.addActionListener(this);
	}
	public void itemStateChanged(ItemEvent e1) {
		s = (String)e1.getItem().toString();
	}
	public void actionPerformed(ActionEvent e2) {
		if(e2.getSource() == bt) {
			if(s.equals("")) {
				JOptionPane.showMessageDialog(null, "请选择季度！");
			}else if(s.equals("第一季度")) {
				new GoodsQuarter1();
			}else if(s.equals("第二季度")) {
				new GoodsQuarter2();
			}else if(s.equals("第三季度")) {
				new GoodsQuarter3();
			}else if(s.equals("第四季度")) {
				new GoodsQuarter4();
			}
		}
	}
}
