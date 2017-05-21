package CD_Tab1;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import CD_DataBase.DataManagement;

/**
 * 主界面->个人中心->通讯录面板
 *
 */
public class AddressList extends JPanel {
	DefaultTableModel model;
	JTable table;
	JScrollPane js;
	AddressList() {
		setLayout(new BorderLayout());
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new Object[]{"工号", "姓名", "职位", "联系电话"});
		new DataManagement().addressList(model);
		table = new JTable(model);	
		table.setEnabled(false);	//设置表格不可被编辑
		js = new JScrollPane(table);
		add(js, BorderLayout.CENTER);
	}
}
