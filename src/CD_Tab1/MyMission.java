package CD_Tab1;

import java.awt.Font;
import javax.swing.JTextArea;
import CD_DataBase.DataManagement;

/**
 * ������->��������->�ҵ��������
 *
 */
public class MyMission extends JTextArea {
	MyMission() {
		setEditable(false);
		setFont(new Font("Dialog", Font.BOLD, 40));
		setText(new DataManagement().getMission());
	}
}

