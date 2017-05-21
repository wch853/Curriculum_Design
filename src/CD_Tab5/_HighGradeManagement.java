package CD_Tab5;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *高级管理面板，用于修改所有员工的薪资和发布任务
 *
 */
public class _HighGradeManagement extends JPanel implements TreeSelectionListener {
	DefaultMutableTreeNode root, treenode1, treenode2;
	JTree tree;	
	JScrollPane js;
	public _HighGradeManagement() {
		setLayout(new BorderLayout());
		root = new DefaultMutableTreeNode("薪资管理与任务");	//根节点
		treenode1 = new DefaultMutableTreeNode("薪资管理");
		treenode2 = new DefaultMutableTreeNode("发布任务");	//子节点
		tree= new JTree(root);	//将根节点加入树
		js = new JScrollPane(tree);	//将树加入滚动条
		root.add(treenode1);
		root.add(treenode2);	//将子节点加入根节点
		add(js, BorderLayout.WEST);	
		tree.addTreeSelectionListener(this);	//为树注册监听
	}
	public void valueChanged(TreeSelectionEvent e) {
		if(e.getSource()==tree) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node.isLeaf()) {
				String s = node.toString();
				removeAll();	//为了每次能添加不同的面板，清除PersonalCenter面板上的所有组件
				add(js, BorderLayout.WEST);	//将树加入面板左侧
				if(s.equals("薪资管理")) {
					add(new Payment(), BorderLayout.CENTER);
				} else if(s.equals("发布任务")) {
					add(new SetMission(), BorderLayout.CENTER);
				}
			}
		}
	}
}
	