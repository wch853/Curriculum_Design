package CD_DataBase;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import CD_Login.LoginWindow;
import CD_Tool.CheckDigit;

/**
 * 数据管理类，用于调用数据库的各种情况
 * 包含增、删、查、改数据库的各种方法
 *
 */
public class DataManagement {
	Connection conn = new DataBase().getConnection();
	/**
	 * 登录时检验用户名和密码
	 * 根据登录使用的工号返回员工等级，弹出不同主界面
	 * @param username 用户名
	 * @param password	密码
	 * @return	员工等级
	 */
	public int checkUser(String username, String password) {	
		try {
			Statement stmt = conn.createStatement();
			String query = "select grade from emp where emp_id = '"+username+"' "
					+ "and password = '"+password+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				return rs.getInt(1);	//获取等级1、2或3
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 主界面->个人中心->查询个人信息信息
	 * @param model 表格模板
	 */
	public void queryMyInfo(DefaultTableModel model) {	//查询员工个人信息
		try {
			String query = "select emp_id, ename, eposition, etel, eage, esex, dep_id, income "
					+ "from emp where emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);	//预编译
			ps.setString(1, LoginWindow.user_name);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String emp_id = rs.getString(1);
				String ename = rs.getString(2);
				String eposition = rs.getString(3);
				String etel = rs.getString(4);
				String eage = rs.getString(5);
				String esex = rs.getString(6);
				String dep_id = rs.getString(7);
				String income = rs.getString(8);
				String query2 = "select dname from dep where dep_id = '"+dep_id+"'"; //通过部门号查询部门名称
				ps = conn.prepareStatement(query2);
				ResultSet rs2 = ps.executeQuery();
				String dname = "";
				if(rs2.next()) {
					dname = rs2.getString(1);
				}
				//向表格中添加
				model.addRow(new Object[]{emp_id, ename, eposition, etel, eage, esex, dname, income});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *主界面->个人中心->查询管理者发布的任务
	 *返回String设置文本
	 * @return	任务文本
	 */
	public String getMission() {
		try {
			Statement stmt = conn.createStatement();
			String query = "select mission from miss where Id = 1";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String s = rs.getString(1);
				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	/**
	 * 主界面->个人中心->查询通讯录
	 * @param model	表格模板
	 */
	public void addressList(DefaultTableModel model) {	//查询所有员工的部分信息（通讯录）
		try {
			Statement stmt = conn.createStatement();
			String query = "select emp_id, ename, eposition, etel from emp";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String emp_id = rs.getString(1);
				String ename = rs.getString(2);
				String eposition = rs.getString(3);
				String etel = rs.getString(4);
				//向表格中添加
				model.addRow(new Object[]{emp_id, ename, eposition, etel});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 我的个人中心->修改密码
	 * @param s1 原密码
	 * @param s2 新密码
	 * @param s3 再次输入的新密码
	 */
	public void alterPassword(String s1, String s2, String s3) { 
		try {
			Statement stmt = conn.createStatement();
			String query = "select password from emp where emp_id = '"+LoginWindow.user_name+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				String password = rs.getString(1);
				if(!password.equals(s1)) {	//如果原密码不正确
					JOptionPane.showMessageDialog(null, "原密码填写错误！", "提示", JOptionPane.WARNING_MESSAGE);
				} else {	//原密码正确
					String query2 = "update emp set password = '"+s2+"' where emp_id = '"
							+ LoginWindow.user_name + "'";
					stmt.executeUpdate(query2);
					JOptionPane.showMessageDialog(null, "修改成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户信息->查询（模糊查询）
	 * @param jctxt	下拉列表选项
	 * @param intxt	文本框输入
	 * @param model 表格模板
	 */
	public void queryClient(String jctxt, String intxt, DefaultTableModel model) {
		try {
			Statement stmt = conn.createStatement();
			String query = "";
			if(jctxt.equals("按会员编号查询")) {	//下拉列表选择的查询类型，使用通配符“%”表示模糊查询
				query = "select * from cli where cli_id Like '%"+intxt+"%'";
			} else if(jctxt.equals("按客户姓名查询")) {
				query = "select * from cli where cname Like '%"+intxt+"%'";
			} else if(jctxt.equals("按客户性别查询")) {
				query = "select * from cli where csex = '"+intxt+"'";
			} 
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String cli_id = rs.getString(1);
				String cname = rs.getString(2);
				String ctel = rs.getString(3);
				String csex = rs.getString(4);
				String cage = rs.getString(5);
				model.addRow(new Object[]{cli_id, cname, ctel, csex, cage});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户信息->全部查询
	 * @param model 表格模板
	 */
	public void queryAllClient(DefaultTableModel model) {	//查询全部客户信息
		try {
			Statement stmt = conn.createStatement();
			String query = "select * from cli";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String cli_id = rs.getString(1);
				String cname = rs.getString(2);
				String ctel = rs.getString(3);
				String csex = rs.getString(4);
				String cage = rs.getString(5);
				model.addRow(new Object[]{cli_id, cname, ctel, csex, cage});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改信息->注册新客户
	 * @param data 信息数组（客户姓名，联系电话，年龄，性别）
	 */
	public void registerClient(String[] data) {
		try {
			int cage = Integer.parseInt(data[2]);
			String update = "insert into cli values(?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setString(1, "0"+data[1]);	//根据客户联系电话，在前添“0”生成会员编号
			ps.setString(2, data[0]);
			ps.setString(3, data[1]);
			ps.setInt(4, cage);
			ps.setString(5, data[3]);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改信息->查询客户信息
	 * 返回客户信息组成的String数组
	 * @param cli_id 客户会员编号
	 * @return 客户信息（客户姓名，联系电话，年龄，性别）
	 */
	public String[] alterClient_Query(String cli_id) {
		try {
			String query = "select * from cli where cli_id = '"+cli_id+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String cname = rs.getString("cname");
				String ctel = rs.getString("ctel");
				String  cage = rs.getInt("cage") + "";
				String csex = rs.getString("csex");
				String[] data = {cname, ctel, cage, csex};
				return data;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改信息->删除客户信息
	 * @param cli_id 客户会员编号
	 */
	public void alterClient_Delete(String cli_id) {
		try {
			String delete = "delete from cli where cli_id = '"+cli_id+"'";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改信息->修改客户信息
	 * @param data 信息数组（客户会员编号，姓名，联系方式，年龄，性别）
	 */
	public void alterClient_Alter(String[] data) {
		try {
			int age = Integer.parseInt(data[3]);	//将String类型转为int类型
			String update = "update cli set cname = ?, ctel = ?, cage = ?, csex = ? where cli_id = ?";
			PreparedStatement ps = conn.prepareStatement(update);	//预编译
			ps.setString(1, data[1]);
			ps.setString(2, data[2]);
			ps.setInt(3, age);
			ps.setString(4, data[4]);
			ps.setString(5, data[0]);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改信息->注册新员工
	 * @param data 信息数组（员工部门名称，职位，性别，姓名，联系电话，入职日期，年龄，等级）
	 */
	public void registerEmployee(String[] data) {
		try {
			int grade = Integer.parseInt(data[7]);	//将String类型转为int类型
			String query = "select dep_id from dep where dname = '"+data[0]+"'";	//根据部门名称查询部门号
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String dep_id = rs.getString(1);
				String update = "insert into emp values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(update);	//预编译
				//调用工具类使用“等级+部门号+入职日期（0000-00-00）+检校位”的形式生成员工工号
				ps.setString(1, new CheckDigit().getCheckDigit(grade + dep_id + data[5].replaceAll("-", "")));
				ps.setString(2, data[3]);
				ps.setString(3, data[1]);
				ps.setString(4, data[4]);
				ps.setInt(5, Integer.parseInt(data[6]));
				ps.setString(6, data[2]);
				ps.setString(7, dep_id);
				ps.setString(8, data[5]);
				ps.setInt(9, grade);
				ps.setFloat(10, (float) 0.00);	//注册新员工时，默认设置月薪为“0.00”
				ps.setString(11, "123456");	//注册新员工时，默认设置密码为“123456”
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改信息->查询员工信息
	 * 返回员工部分信息组成的String数组（员工姓名、联系电话、年龄、性别）
	 * @param emp_id 员工工号
	 * @return	信息数组
	 */
	public String[] alterEmployee_Query(String emp_id) {
		try {
			String query = "select * from emp where emp_id = '"+emp_id+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String ename = rs.getString("ename");
				String etel = rs.getString("etel");
				String eage = rs.getInt("eage") + "";
				String esex = rs.getString("esex");
				String[] data = {ename, etel, eage, esex};
				return data;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 修改信息->删除员工信息
	 * @param emp_id 员工工号
	 */
	public void alterEmployee_Delete(String emp_id) {
		try {
			String delete = "delete from emp where emp_id = '"+emp_id+"'";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(delete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改信息->修改员工信息
	 * @param data 信息数组（员工工号，姓名，联系电话，年龄，性别）
	 */
	public void alterEmployee_Alter(String[] data) {
		try {
			int age = Integer.parseInt(data[3]);
			String update = "update emp set ename = ?, etel = ?, eage = ?, esex = ? where emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setString(1, data[1]);
			ps.setString(2, data[2]);
			ps.setInt(3, age);
			ps.setString(4, data[4]);
			ps.setString(5, data[0]);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 高级管理->薪资管理->查询员工月薪
	 * 返回员工月薪
	 * @param emp_id 员工工号
	 * @return 薪资信息
	 */
	public String queryIncome(String emp_id) {
		try {
			String query = "select income from emp where emp_id = '"+emp_id+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String income = rs.getString(1);
				return income;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 高级管理->薪资管理->修改员工月薪
	 * @param emp_id 员工工号
	 * @param income 修改后的月薪
	 */
	public void alterEmployee_Alter(String emp_id, String income) {
		try {
			String update = "update emp set income = '"+income+"' where emp_id = '"+emp_id+"'";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 高级管理->发布任务
	 * @param text 任务文本
	 */
	public void setAreaText(String text) {
		try {
			String update = "update miss set mission = '"+text+"' where Id =1";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(update);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * 获取第一季度数据
	 * @return sales[]
	 */
	public int[] getValues_1() {
		try {
			String query = "select * from quarter1 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"商品1","商品2","商品3","商品4","商品5","商品6"};
			for(int k = 0; k <= 5; ++k){
				ps.setString(1, a[k]);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					sales[k] = rs.getInt(2);
				}
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取第二季度数据
	 * @return sales[]
	 */
	public int[] getValues_2() {
		try {
			String query = "select * from quarter2 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"商品1","商品2","商品3","商品4","商品5","商品6"};
			for(int k = 0; k <= 5; ++k){
				ps.setString(1, a[k]);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					sales[k] = rs.getInt(2);
				}
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 获取第三季度数据
	 * @return sales[]
	 */
	public int[] getValues_3() {
		try {
			String query = "select * from quarter3 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"商品1","商品2","商品3","商品4","商品5","商品6"};
			for(int k = 0; k <= 5; ++k){
				ps.setString(1, a[k]);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					sales[k] = rs.getInt(2);
				}
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * 获取第四季度数据
	 * @return sales[]
	 */
	public int[] getValues_4() {
		try {
			String query = "select * from quarter4 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"商品1","商品2","商品3","商品4","商品5","商品6"};
			for(int k = 0; k <= 5; ++k){
				ps.setString(1, a[k]);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					sales[k] = rs.getInt(2);
				}
			}
			return sales;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
