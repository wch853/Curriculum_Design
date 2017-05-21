package CD_Tab3;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

/**
 * ������->��Ʒͼ��
 *
 */
public class GoodsChart extends JPanel implements ItemListener, ActionListener{
	private JPanel p;
	private JComboBox quarter;  //���������
	private JLabel label;     	//��ǩ
	private JButton bt;
	String s = "";
	public GoodsChart() {
		label = new JLabel();
		label.setText("����");
		quarter = new JComboBox();
		//���������ѡ��
		quarter.addItem("ѡ�񼾶�");	
		quarter.addItem("��һ����");
		quarter.addItem("�ڶ�����");
		quarter.addItem("��������");
		quarter.addItem("���ļ���");
		bt = new JButton("ȷ��");
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
				JOptionPane.showMessageDialog(null, "��ѡ�񼾶ȣ�");
			}else if(s.equals("��һ����")) {
				new GoodsQuarter1();
			}else if(s.equals("�ڶ�����")) {
				new GoodsQuarter2();
			}else if(s.equals("��������")) {
				new GoodsQuarter3();
			}else if(s.equals("���ļ���")) {
				new GoodsQuarter4();
			}
		}
	}
}
