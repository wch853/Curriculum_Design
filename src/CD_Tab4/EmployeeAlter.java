package CD_Tab4;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * ������->�޸���Ϣ->Ա���޸�
 *
 */
public class EmployeeAlter extends JPanel implements ActionListener {
	JTextField[] text;
	JButton query, delete, alter, reset;
	public EmployeeAlter() {
		setLayout(null);
		JLabel[] labels = {new JLabel("������Ա������"), new JLabel("�޸ģ�  *����"), 
					new JLabel("       *��ϵ�绰"), 
					new JLabel("                 ����"), new JLabel("                 �Ա�")};
		text = new JTextField[6];
		for(int i=0; i<=4; i++) {
			labels[i].setBounds(50, 10+30*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(150, 10+30*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		query = new JButton("��ѯ");
		delete = new JButton("ɾ��Ա��");
		alter = new JButton("�޸�");
		reset = new JButton("����");
		query.setBounds(320, 10, 60, 25);
		delete.setBounds(390, 10, 90, 25);
		alter.setBounds(155, 170, 60, 25);
		reset.setBounds(240, 170, 60, 25);
		add(query);
		add(delete);
		add(alter);
		add(reset);
		query.addActionListener(this);
		delete.addActionListener(this);
		alter.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String emp_id = text[0].getText().trim();	//�õ�����Ļ�Ա���
		String ename = text[1].getText().trim();
		String etel = text[2].getText().trim();
		String eage = text[3].getText().trim();
		String esex = text[4].getText().trim();
		if(e.getSource()==reset) {	//���������ð�Ŧ
			for(int i=1; i<=4; i++) {
				text[i].setText("");
			}
		} else if(e.getSource()==query) {	//��������ѯ��ť
			if(emp_id.equals("")){	//��Ա����ı�������Ϊ��
				JOptionPane.showMessageDialog(null, "������Ա�����ţ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				String[] data = new DataManagement().alterEmployee_Query(emp_id);
				if(data==null) {	//�����������Ϊ��
					JOptionPane.showMessageDialog(null, "û�м�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				} else {
					for(int i=1; i<=4; i++) {	//����ѯ������Ϣ�����ӡ���ı�����
						text[i].setText(data[i-1]);
					}
				}
			}
		} else if(e.getSource()==delete) {	//������ɾ����ť
			if(emp_id.equals("")){	//���ѯ��ͬ
				JOptionPane.showMessageDialog(null, "�������Ա��ţ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				String[] data = new DataManagement().alterEmployee_Query(emp_id);
				if(data==null) {
					JOptionPane.showMessageDialog(null, "û�м�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				} else { //����м�¼
					int flag = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "��ʾ", JOptionPane.YES_NO_OPTION);
					if(flag==0) {	//����ڵ���ȷ�϶Ի����е㡰�ǡ�
						new DataManagement().alterEmployee_Delete(emp_id);
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		} else if(e.getSource()==alter) {	//�������޸İ�ť
			if(emp_id.equals("") ) {	//����ؼ����ı���Ϊ��
				JOptionPane.showMessageDialog(null, "�������Ա��Ų���ѯ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if(ename.equals("") || etel.equals("")) {	//�����������ϵ�绰Ϊ��
				JOptionPane.showMessageDialog(null, "��������ϵ�绰Ϊ�����", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else if(eage.length()>3 || !esex.equals("��") && !esex.equals("Ů")) {	//�����д��Ϣ�����Ϲ涨
				JOptionPane.showMessageDialog(null, "��������ȷ����Ϣ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				int flag = JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�", "��ʾ", JOptionPane.YES_NO_OPTION);
				if(flag==0) {	//����ڵ���ȷ�϶Ի����е㡰�ǡ�
					String[] data = new DataManagement().alterEmployee_Query(emp_id);
					if(data==null) {
						JOptionPane.showMessageDialog(null, "û�м�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
					} else { //����м�¼
						String[] data2 = {emp_id, ename, etel, eage, esex};
						new DataManagement().alterEmployee_Alter(data2);
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}
