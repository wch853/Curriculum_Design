package CD_MainFrame;

import javax.swing.ImageIcon;
import CD_Tab4._AlterInfo;

/**
 * ���ž���grade2��������
 * 
 */
public class DepartmentManager extends Employee {
	public DepartmentManager() {
		super();
		jtp.addTab("�޸���Ϣ", new ImageIcon("�޸���Ϣ.png"), new _AlterInfo(), "�ͻ���Ա������Ϣ�޸�");
	}
}

