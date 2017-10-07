package CD_Tab4;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * ������->�޸���Ϣ
 *
 */
public class _AlterInfo extends JPanel implements TreeSelectionListener {
	JScrollPane js;
	DefaultMutableTreeNode root, treenode1, treenode2, treenode[];
	JTree tree;	
	public _AlterInfo() {
		setLayout(new BorderLayout());
		String[] data1 = {"�ͻ�ע��", "�ͻ��޸�"};
		String[] data2 = {"Ա��ע��", "Ա���޸�"};
		root = new DefaultMutableTreeNode("��Ϣ�޸�");	//���ڵ�
		treenode1 = new DefaultMutableTreeNode("�ͻ���Ϣ�޸�");
		treenode2 = new DefaultMutableTreeNode("Ա����Ϣ�޸�");
		root.add(treenode1);
		root.add(treenode2);
		treenode = new DefaultMutableTreeNode[5];	//�ӽڵ�����
		for(int i=0; i<=1; i++) {
			treenode[i] = new DefaultMutableTreeNode(data1[i]);
			treenode1.add(treenode[i]);	
		}
		for(int i=0; i<=1; i++) {
			treenode[i] = new DefaultMutableTreeNode(data2[i]);
			treenode2.add(treenode[i]);	
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
				if(s.equals("�ͻ�ע��")) {
					add(new ClientRegister(), BorderLayout.CENTER);
				} else if(s.equals("�ͻ��޸�")) {
					add(new ClientAlter(), BorderLayout.CENTER);
				}else if(s.equals("Ա��ע��")) {
					add(new EmployeeRegister(), BorderLayout.CENTER);
				} else if(s.equals("Ա���޸�")) {
					add(new EmployeeAlter(), BorderLayout.CENTER);
				} 
			}
		}
	}
}

