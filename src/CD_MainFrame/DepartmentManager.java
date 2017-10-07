package CD_MainFrame;

import javax.swing.ImageIcon;
import CD_Tab4._AlterInfo;

/**
 * 部门经理（grade2）主界面
 * 
 */
public class DepartmentManager extends Employee {
	public DepartmentManager() {
		super();
		jtp.addTab("修改信息", new ImageIcon("修改信息.png"), new _AlterInfo(), "客户、员工的信息修改");
	}
}

