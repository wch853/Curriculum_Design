package CD_Tab5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * �߼�����->��������
 *
 */
public class SetMission extends JPanel {
	JTextArea area;
	JPanel p;
	JButton alter, reset;
	SetMission() {
		setLayout(new BorderLayout());
		area = new JTextArea();
		area.setFont(new Font("Dialog", Font.BOLD, 40));
		area.setText(new DataManagement().getMission());
		p = new JPanel();
		alter = new JButton("�޸�");
		reset = new JButton("����");
		p.add(alter);
		p.add(reset);
		add(area, BorderLayout.CENTER);
		add(p, BorderLayout.SOUTH);
		alter.addActionListener(new InnerListen());
		reset.addActionListener(new InnerListen());
	}
	class InnerListen implements ActionListener {	//ʹ���ڲ�������¼���Ӧ
		public void actionPerformed(ActionEvent e) {
			String text = area.getText();
			if(e.getSource()==alter) {	//����޸İ�ť
				new DataManagement().setAreaText(text);
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			} else if(e.getSource()==reset) {	//������ð�ť
				area.setText(null);
			}
		}
	}
}
