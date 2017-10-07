package CD_Tab4;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * ������->�޸���Ϣ->Ա��ע��
 *
 */
public class EmployeeRegister extends JPanel implements ItemListener, ActionListener {
	JLabel dname, eposition, esex;
	JTextField[] text;
	JComboBox jc;
	JRadioButton employee, dmanager, male, female;
	ButtonGroup bg1, bg2;
	JButton confirm, reset;
	String jctxt = "", bg1txt = "", bg2txt = "", grade = "";
	public EmployeeRegister() {
		setLayout(null);
		dname = new JLabel("        *����");
		dname.setBounds(50, 10, 70, 25);
		eposition = new JLabel("        *ְλ");
		eposition.setBounds(50, 45, 70, 25);
		esex = new JLabel("        *�Ա�");
		esex.setBounds(50, 80, 70, 25);
		jc = new JComboBox();
		jc.setBounds(125, 10, 120, 25);
		jc.addItem("��ѡ����");
		jc.addItem("�ܾ���칫��");
		jc.addItem("�з���");
		jc.addItem("������");
		jc.addItem("���۲�");
		jc.addItem("����");
		employee = new JRadioButton("ְԱ");
		dmanager = new JRadioButton("����");
		employee.setBounds(125, 37, 60, 40);
		dmanager.setBounds(185, 37, 60, 40);
		bg1 = new ButtonGroup();
		bg1.add(employee);
		bg1.add(dmanager);
		male = new JRadioButton("��");
		female = new JRadioButton("Ů");
		male.setBounds(125, 72, 60, 40);
		female.setBounds(185, 72, 60, 40);
		bg2 = new ButtonGroup();
		bg2.add(male);
		bg2.add(female);
		JLabel[] labels = {new JLabel("         *����"), new JLabel("*��ϵ�绰"), 
				new JLabel("*��ְ����"), new JLabel("         *����")};
		text = new JTextField[4];
		for(int i=0; i<=3; i++) {
			labels[i].setBounds(50, 115+35*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(120, 115+35*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		text[2].setText("0000-00-00");
		confirm = new JButton("ȷ��");
		reset = new JButton("����");
		confirm.setBounds(115, 255, 60, 25);
		reset.setBounds(205, 255, 60, 25);
		add(dname);
		add(jc);
		add(eposition);
		add(employee);
		add(dmanager);
		add(esex);
		add(male);
		add(female);
		add(confirm);
		add(reset);
		jc.addItemListener(this);
		employee.addActionListener(this);
		dmanager.addActionListener(this);
		male.addActionListener(this);
		female.addActionListener(this);
		confirm.addActionListener(this);
		reset.addActionListener(this);
	}
	public void itemStateChanged(ItemEvent e) {	
		jctxt = (String)e.getItem().toString();	//�õ������б�ѡ�е�ֵ�������ַ�������
	}
	public void actionPerformed(ActionEvent e) {
		String ename = text[0].getText().trim();
		String etel = text[1].getText().trim();
		String hiredate = text[2].getText().trim();
		String eage = text[3].getText().trim();
		if(e.getSource()==employee) {	//���ְԱ��ѡ��ť
			bg1txt = "ְԱ";
			grade = "3";
		} else if(e.getSource()==dmanager) {	//�������ѡ��ť
			bg1txt = "����";
			grade = "2";
		}
		if(e.getSource()==male) {	//����е�ѡ��ť
			bg2txt = "��";
		} else if(e.getSource()==female) {	//���Ů��ѡ��ť
			bg2txt = "Ů";
		}
		if(e.getSource()==confirm) {	//���ȷ����ť
			String[] data = {jctxt, bg1txt, bg2txt, ename, etel, hiredate, eage, grade};	//���Ա����Ϣ����
			for(String s : data) {
				if(s.equals("")) {	//�����һ���п�ֵ
					JOptionPane.showMessageDialog(null, "������Ϊ�����", "��ʾ ", JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
			int flag = JOptionPane.showConfirmDialog(null, "ȷ��ע�᣿", "��ʾ", JOptionPane.YES_NO_OPTION);
			if(flag==JOptionPane.YES_OPTION) {	//���ȷ��
				new DataManagement().registerEmployee(data);
				JOptionPane.showMessageDialog(null, "ע����Ա���ɹ���", "ע��ɹ�", JOptionPane.INFORMATION_MESSAGE);
				for(int i=0; i<=3; i++) {
					text[i].setText("");	//�����ı���Ϊ��
				}
				text[2].setText("0000-00-00");
			}
		} else if(e.getSource()==reset) {
			for(int i=0; i<=3; i++) {
				text[i].setText("");	//�����ı���Ϊ��
			}
			text[2].setText("0000-00-00");
		}
	}
}
