package CD_Tab1;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import CD_DataBase.DataManagement;

/**
 * ������->��������->�ҵ���Ϣ���
 *
 */
public class MyInfo extends JPanel {
	DefaultTableModel model;
	JTable table;
	JScrollPane js;
	MyInfo() {
		setLayout(new BorderLayout());
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"����", "����", "ְλ", "��ϵ�绰", "����", "�Ա�", "����", "��н"});
		new DataManagement().queryMyInfo(model);	//���ò�ѯ���ҵ���Ϣ������
		table = new JTable(model);	
		table.setEnabled(false);	//���ñ�񲻿ɱ��༭
		js = new JScrollPane(table);
		add(js, BorderLayout.CENTER);
	}
}

