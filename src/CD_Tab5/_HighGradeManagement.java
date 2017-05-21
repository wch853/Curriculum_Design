package CD_Tab5;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *�߼�������壬�����޸�����Ա����н�ʺͷ�������
 *
 */
public class _HighGradeManagement extends JPanel implements TreeSelectionListener {
	DefaultMutableTreeNode root, treenode1, treenode2;
	JTree tree;	
	JScrollPane js;
	public _HighGradeManagement() {
		setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode("н�ʹ���������");	//���ڵ�
		treenode1 = new DefaultMutableTreeNode("н�ʹ���");
		treenode2 = new DefaultMutableTreeNode("��������");	//�ӽڵ�
		tree= new JTree(root);	//�����ڵ������
		js = new JScrollPane(tree);	//�������������
		root.add(treenode1);
		root.add(treenode2);	//���ӽڵ������ڵ�
		add(js, BorderLayout.WEST);	
		tree.addTreeSelectionListener(this);	//Ϊ��ע�����
	}
	public void valueChanged(TreeSelectionEvent e) {
		if(e.getSource()==tree) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node.isLeaf()) {
				String s = node.toString();
				removeAll();	//Ϊ��ÿ������Ӳ�ͬ����壬���PersonalCenter����ϵ��������
				add(js, BorderLayout.WEST);	//��������������
				if(s.equals("н�ʹ���")) {
					add(new Payment(), BorderLayout.CENTER);
				} else if(s.equals("��������")) {
					add(new SetMission(), BorderLayout.CENTER);
				}
			}
		}
	}
}
	