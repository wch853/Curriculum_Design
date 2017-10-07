package CD_Tab2;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import CD_DataBase.DataManagement;

/**
 * ������->�ͻ���Ϣ���ṩ�������͵Ŀͻ���Ϣ��ѯ���ܣ�
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
		jc.addItem("��ѯ��ʽ");
		jc.addItem("����Ա��Ų�ѯ");
		jc.addItem("���ͻ�������ѯ");
		jc.addItem("���ͻ��Ա��ѯ");
		prompt2 = new JLabel("�������ѯ�ؼ���:");
		input = new JTextField(16);
		fuzzyquery = new JButton("��ѯ��ģ����ѯ��");
		fullquery = new JButton("ȫ����ѯ");
		p = new JPanel();
		p.add(prompt1);
		p.add(jc);
		p.add(prompt2);
		p.add(input);
		p.add(fuzzyquery);
		p.add(fullquery);
		add(p, BorderLayout.NORTH);
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"��Ա���", "����", "��ϵ�绰", "�Ա�", "����"});
		table = new JTable(model);	
		table.setEnabled(false);	//���ñ�񲻿ɱ��༭
		js = new JScrollPane(table);
		jc.addItemListener(this);
		fuzzyquery.addActionListener(this);
		fullquery.addActionListener(this);
		add(js, BorderLayout.CENTER);
	}
	public void itemStateChanged(ItemEvent e) {	//�õ������б�ѡ�е�ֵ�������ַ�������
		jctxt = (String)e.getItem().toString(); 
	}
	public void actionPerformed(ActionEvent e) {
		model.setRowCount(0);	//��������ϴβ�ѯ������
		if(e.getSource()==fuzzyquery) {	//�����ѯ��ģ����ѯ����ť
			intxt = input.getText().trim();	//�õ��ؼ����ı�����ַ���
			if(jctxt.equals("")) {	//���û��ѡ���ѯ����
				JOptionPane.showMessageDialog(this, "��ѡ���ѯ��ʽ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				if(intxt.trim().equals("")) {	//�ж�����Ĺؼ����Ƿ�Ϊ��
					JOptionPane.showMessageDialog(this, "��ѯ�ؼ��ֲ���Ϊ�գ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
				} else{
					new DataManagement().queryClient(jctxt, intxt, model);
				}
			}
		} else if(e.getSource()==fullquery) {	//���ȫ����ѯ��ť
			new DataManagement().queryAllClient(model);
		}
		if(model.getRowCount()==0) {	//���Ϊ��
			JOptionPane.showMessageDialog(null, "û�м�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
		}
	}
}

