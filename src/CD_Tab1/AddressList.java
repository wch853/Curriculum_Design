package CD_Tab1;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import CD_DataBase.DataManagement;

/**
 * ������->��������->ͨѶ¼���
 *
 */
public class AddressList extends JPanel {
	DefaultTableModel model;
	JTable table;
	JScrollPane js;
	AddressList() {
		setLayout(new BorderLayout());
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"����", "����", "ְλ", "��ϵ�绰"});
		new DataManagement().addressList(model);
		table = new JTable(model);	
		table.setEnabled(false);	//���ñ�񲻿ɱ��༭
		js = new JScrollPane(table);
		add(js, BorderLayout.CENTER);
	}
}
