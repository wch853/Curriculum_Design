package CD_MainFrame;

import javax.swing.*;
import CD_Tab5._HighGradeManagement;

/**
 * �ܾ���grade1��������
 *
 */
public class GeneralManager extends DepartmentManager {
	public GeneralManager() {
		super();
		jtp.addTab("�߼�����", new ImageIcon("�߼�����.png"), new _HighGradeManagement(), "�޸�Ա��н�ʺͷ�������");
	} 
}

