package CD_Tab4;

import java.awt.event.*;
import javax.swing.*;
import CD_DataBase.DataManagement;

/**
 * ������->�޸���Ϣ->�ͻ�ע��
 *
 */
public class ClientRegister extends JPanel implements ActionListener { 
	JTextField[] text;
	JRadioButton male, female;
	ButtonGroup bg;
	JButton confirm, reset;
	String csex = "";
	public ClientRegister() {
		setLayout(null);
		JLabel[] labels = {new JLabel("        *����"), 
				new JLabel("*��ϵ�绰"), new JLabel("	          ����")};
		text = new JTextField[4];
		for(int i=0; i<=2; i++) {
			labels[i].setBounds(50, 10+35*i, 100, 25);
			text[i] = new JTextField("",16);
			text[i].setBounds(120, 10+35*i, 160, 25);
			add(labels[i]);
			add(text[i]);
		}
		text[2].setText("0");
		male = new JRadioButton("��");
		female = new JRadioButton("Ů");
		bg = new ButtonGroup();
		male.setBounds(130, 105, 40, 40);
		female.setBounds(190, 105, 40, 40);
		bg.add(male);
		bg.add(female);
		add(male);
		add(female);
		confirm = new JButton("ȷ��");
		reset = new JButton("����");
		confirm.setBounds(100, 150, 60, 25);
		reset.setBounds(195, 150, 60, 25);
		add(confirm);
		add(reset);
		male.addActionListener(this);
		female.addActionListener(this);
		confirm.addActionListener(this);
		reset.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String cname = text[0].getText().trim();
		String ctel = text[1].getText().trim();
		String cage = text[2].getText().trim();
		if(e.getSource()==male) {
			csex = "��";
		} else if(e.getSource()==female) {
			csex = "Ů";
		}
		if(e.getSource()==confirm) {
			if(cname.equals("") || ctel.equals("")) {	//�����������ϵ�绰��Ϊ��
				JOptionPane.showMessageDialog(null, "��������ϵ�绰Ϊ�����", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
			} else if(cage.length()>3) {	//�����д��������ڹ涨�ֳ�
				JOptionPane.showMessageDialog(null, "��������ȷ�����䣡", "��ʾ", JOptionPane.WARNING_MESSAGE);
			}else {
				int flag = JOptionPane.showConfirmDialog(null, "ȷ��ע�᣿", "��ʾ", JOptionPane.YES_NO_OPTION);
				if(flag==0) {
					String[] data = {cname, ctel, cage, csex};
					new DataManagement().registerClient(data);
					JOptionPane.showMessageDialog(null, "ע���»�Ա�ɹ���", "ע��ɹ�", JOptionPane.INFORMATION_MESSAGE);
					for(int i=0; i<=2; i++) {
						text[i].setText("");
					}
				}
			}
		} else if(e.getSource()==reset) {
			for(int i=0; i<=2; i++) {
				text[i].setText("");
			}
		}
	}
}
