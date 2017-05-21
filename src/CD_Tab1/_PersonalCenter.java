package CD_Tab1;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * ������->��������->�ҵĸ������ģ�JTree��
 *
 */
public class _PersonalCenter extends JPanel implements TreeSelectionListener {
	JTable table;
	JScrollPane js;
	DefaultTableModel model;
	DefaultMutableTreeNode root, treenode[];
	JTree tree;	
	public _PersonalCenter() {
		setLayout(new BorderLayout());
		String[] data = {"�ҵ���Ϣ", "�ҵ�����", "ͨѶ¼", "��ʾ����", "�����޸�"};
		root = new DefaultMutableTreeNode("�ҵĸ�������");	//���ڵ�
		treenode = new DefaultMutableTreeNode[5];	//�ӽڵ�����
		for(int i=0; i<=4; i++) {
			treenode[i] = new DefaultMutableTreeNode(data[i]);
			root.add(treenode[i]);	//���ӽڵ������ڵ�
		}
		tree= new JTree(root);	//�����ڵ������
		js = new JScrollPane(tree);	//�������������
		add(js, BorderLayout.WEST);	
		tree.addTreeSelectionListener(this);	//Ϊ��ע�����
	}
	public void valueChanged(TreeSelectionEvent e) {
		if(e.getSource()==tree) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node.isLeaf()) {
				String s = node.toString();	//ѡ������ڵ������
				removeAll();	//Ϊ��ÿ������Ӳ�ͬ����壬���PersonalCenter����ϵ��������
				add(js, BorderLayout.WEST);	//��������������
				if(s.equals("�ҵ���Ϣ")) {
					add(new MyInfo(), BorderLayout.CENTER);
				} else if(s.equals("�ҵ�����")) {
					add(new MyMission(), BorderLayout.CENTER);
				}else if(s.equals("ͨѶ¼")) {
					add(new AddressList(), BorderLayout.CENTER);
				} else if(s.equals("��ʾ����")) {
					add(new ViewCalendar(), BorderLayout.CENTER);
				} else if(s.equals("�����޸�")) {
					add(new AlterPassword(), BorderLayout.CENTER);
				} 
			}
		}
	}
}
