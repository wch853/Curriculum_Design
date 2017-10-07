package CD_Tab5;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * �߼�����->н�ʹ���
 *
 */
public class Payment extends JPanel implements ActionListener {
	JTextField[] text;
	JButton query, alter, reset;
	public Payment() {
		setLayout(null);
		JLabel[] labels = {new JLabel("������Ա������"), new JLabel("�޸ģ�  *��н")};
		text = new JTextField[2];
		for(int i=0; i<=1; i++) {
			labels[i].setBounds(50, 10+30*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(150, 10+30*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		text[1].setText("0.00");
		query = new JButton("��ѯ");
		alter = new JButton("�޸�");
		reset = new JButton("����");
		query.setBounds(320, 10, 60, 25);
		alter.setBounds(155, 75, 60, 25);
		reset.setBounds(240, 75, 60, 25);
		add(query);
		add(alter);
		add(reset);
		query.addActionListener(this);
		alter.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String emp_id = text[0].getText().trim();	//�õ�����Ļ�Ա���
		String income = text[1].getText().trim();
		if(e.getSource()==reset) {	//������ð�Ŧ
			text[0].setText("");
			text[1].setText("0.00");
		} else if(e.getSource()==query) {	//�����ѯ��ť
			if(emp_id.equals("")){	//��Ա����ı�������Ϊ��
				JOptionPane.showMessageDialog(null, "������Ա�����ţ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				String incomequery = new DataManagement().queryIncome(emp_id);
				if(incomequery==null) {
					JOptionPane.showMessageDialog(null, "û�м�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
				} else {
					text[1].setText(incomequery);
				}
			}
		} else if(e.getSource()==alter) {	//����޸İ�ť
			if(emp_id.equals("") ) {	//�ؼ����ı���Ϊ��
				JOptionPane.showMessageDialog(null, "������Ա�����Ų���ѯ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
			} else {
				int flag = JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�", "��ʾ", JOptionPane.YES_NO_OPTION);
				if(flag==0) {	//���ȷ��
					String incomequery = new DataManagement().queryIncome(emp_id);
					if(incomequery==null) {
						JOptionPane.showMessageDialog(null, "û�м�¼��", "��ʾ", JOptionPane.WARNING_MESSAGE);
					} else {
						new DataManagement().alterEmployee_Alter(emp_id, income);
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}
}
