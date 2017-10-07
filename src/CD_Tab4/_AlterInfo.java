package CD_Tab4;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 主界面->修改信息
 *
 */
public class _AlterInfo extends JPanel implements TreeSelectionListener {
	JScrollPane js;
	DefaultMutableTreeNode root, treenode1, treenode2, treenode[];
	JTree tree;	
	public _AlterInfo() {
		setLayout(new BorderLayout());
		String[] data1 = {"客户注册", "客户修改"};
		String[] data2 = {"员工注册", "员工修改"};
		root = new DefaultMutableTreeNode("信息修改");	//根节点
		treenode1 = new DefaultMutableTreeNode("客户信息修改");
		treenode2 = new DefaultMutableTreeNode("员工信息修改");
		root.add(treenode1);
		root.add(treenode2);
		treenode = new DefaultMutableTreeNode[5];	//子节点数组
		for(int i=0; i<=1; i++) {
			treenode[i] = new DefaultMutableTreeNode(data1[i]);
			treenode1.add(treenode[i]);	
		}
		for(int i=0; i<=1; i++) {
			treenode[i] = new DefaultMutableTreeNode(data2[i]);
			treenode2.add(treenode[i]);	
		}
		tree= new JTree(root);	//将根节点加入树
		js = new JScrollPane(tree);	//将树加入滚动条
		add(js, BorderLayout.WEST);	
		tree.addTreeSelectionListener(this);	//为树注册监听
	}
	public void valueChanged(TreeSelectionEvent e) {
		if(e.getSource()==tree) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if(node.isLeaf()) {
				String s = node.toString();	//选择的树节点的名称
				removeAll();	//为了每次能添加不同的面板，清除PersonalCenter面板上的所有组件
				add(js, BorderLayout.WEST);	//将树加入面板左侧
				if(s.equals("客户注册")) {
					add(new ClientRegister(), BorderLayout.CENTER);
				} else if(s.equals("客户修改")) {
					add(new ClientAlter(), BorderLayout.CENTER);
				}else if(s.equals("员工注册")) {
					add(new EmployeeRegister(), BorderLayout.CENTER);
				} else if(s.equals("员工修改")) {
					add(new EmployeeAlter(), BorderLayout.CENTER);
				} 
			}
		}
	}
}

