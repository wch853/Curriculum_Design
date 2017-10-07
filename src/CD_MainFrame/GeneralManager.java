package CD_MainFrame;

import javax.swing.*;
import CD_Tab5._HighGradeManagement;

/**
 * 总经理（grade1）主界面
 *
 */
public class GeneralManager extends DepartmentManager {
	public GeneralManager() {
		super();
		jtp.addTab("高级管理", new ImageIcon("高级管理.png"), new _HighGradeManagement(), "修改员工薪资和发布任务");
	} 
}

