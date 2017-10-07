package CD_DataBase;

import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import CD_Login.LoginWindow;
import CD_Tool.CheckDigit;

/**
 * ���ݹ����࣬���ڵ������ݿ�ĸ������
 * ��������ɾ���顢�����ݿ�ĸ��ַ���
 *
 */
public class DataManagement {
	Connection conn = new DataBase().getConnection();
	/**
	 * ��¼ʱ�����û���������
	 * ���ݵ�¼ʹ�õĹ��ŷ���Ա���ȼ���������ͬ������
	 * @param username �û���
	 * @param password	����
	 * @return	Ա���ȼ�
	 */
	public int checkUser(String username, String password) {	
		try {
			Statement stmt = conn.createStatement();
			String query = "select grade from emp where emp_id = '"+username+"' "
					+ "and password = '"+password+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				return rs.getInt(1);	//��ȡ�ȼ�1��2��3
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * ������->��������->��ѯ������Ϣ��Ϣ
	 * @param model ���ģ��
	 */
	public void queryMyInfo(DefaultTableModel model) {	//��ѯԱ��������Ϣ
		try {
			String query = "select emp_id, ename, eposition, etel, eage, esex, dep_id, income "
					+ "from emp where emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(query);	//Ԥ����
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
				String query2 = "select dname from dep where dep_id = '"+dep_id+"'"; //ͨ�����źŲ�ѯ��������
				ps = conn.prepareStatement(query2);
				ResultSet rs2 = ps.executeQuery();
				String dname = "";
				if(rs2.next()) {
					dname = rs2.getString(1);
				}
				//���������
				model.addRow(new Object[]{emp_id, ename, eposition, etel, eage, esex, dname, income});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *������->��������->��ѯ�����߷���������
	 *����String�����ı�
	 * @return	�����ı�
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
	 * ������->��������->��ѯͨѶ¼
	 * @param model	���ģ��
	 */
	public void addressList(DefaultTableModel model) {	//��ѯ����Ա���Ĳ�����Ϣ��ͨѶ¼��
		try {
			Statement stmt = conn.createStatement();
			String query = "select emp_id, ename, eposition, etel from emp";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String emp_id = rs.getString(1);
				String ename = rs.getString(2);
				String eposition = rs.getString(3);
				String etel = rs.getString(4);
				//���������
				model.addRow(new Object[]{emp_id, ename, eposition, etel});
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ҵĸ�������->�޸�����
	 * @param s1 ԭ����
	 * @param s2 ������
	 * @param s3 �ٴ������������
	 */
	public void alterPassword(String s1, String s2, String s3) { 
		try {
			Statement stmt = conn.createStatement();
			String query = "select password from emp where emp_id = '"+LoginWindow.user_name+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				String password = rs.getString(1);
				if(!password.equals(s1)) {	//���ԭ���벻��ȷ
					JOptionPane.showMessageDialog(null, "ԭ������д����", "��ʾ", JOptionPane.WARNING_MESSAGE);
				} else {	//ԭ������ȷ
					String query2 = "update emp set password = '"+s2+"' where emp_id = '"
							+ LoginWindow.user_name + "'";
					stmt.executeUpdate(query2);
					JOptionPane.showMessageDialog(null, "�޸ĳɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ͻ���Ϣ->��ѯ��ģ����ѯ��
	 * @param jctxt	�����б�ѡ��
	 * @param intxt	�ı�������
	 * @param model ���ģ��
	 */
	public void queryClient(String jctxt, String intxt, DefaultTableModel model) {
		try {
			Statement stmt = conn.createStatement();
			String query = "";
			if(jctxt.equals("����Ա��Ų�ѯ")) {	//�����б�ѡ��Ĳ�ѯ���ͣ�ʹ��ͨ�����%����ʾģ����ѯ
				query = "select * from cli where cli_id Like '%"+intxt+"%'";
			} else if(jctxt.equals("���ͻ�������ѯ")) {
				query = "select * from cli where cname Like '%"+intxt+"%'";
			} else if(jctxt.equals("���ͻ��Ա��ѯ")) {
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
	 * �ͻ���Ϣ->ȫ����ѯ
	 * @param model ���ģ��
	 */
	public void queryAllClient(DefaultTableModel model) {	//��ѯȫ���ͻ���Ϣ
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
	 * �޸���Ϣ->ע���¿ͻ�
	 * @param data ��Ϣ���飨�ͻ���������ϵ�绰�����䣬�Ա�
	 */
	public void registerClient(String[] data) {
		try {
			int cage = Integer.parseInt(data[2]);
			String update = "insert into cli values(?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(update);
			ps.setString(1, "0"+data[1]);	//���ݿͻ���ϵ�绰����ǰ��0�����ɻ�Ա���
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
	 * �޸���Ϣ->��ѯ�ͻ���Ϣ
	 * ���ؿͻ���Ϣ��ɵ�String����
	 * @param cli_id �ͻ���Ա���
	 * @return �ͻ���Ϣ���ͻ���������ϵ�绰�����䣬�Ա�
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
	 * �޸���Ϣ->ɾ���ͻ���Ϣ
	 * @param cli_id �ͻ���Ա���
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
	 * �޸���Ϣ->�޸Ŀͻ���Ϣ
	 * @param data ��Ϣ���飨�ͻ���Ա��ţ���������ϵ��ʽ�����䣬�Ա�
	 */
	public void alterClient_Alter(String[] data) {
		try {
			int age = Integer.parseInt(data[3]);	//��String����תΪint����
			String update = "update cli set cname = ?, ctel = ?, cage = ?, csex = ? where cli_id = ?";
			PreparedStatement ps = conn.prepareStatement(update);	//Ԥ����
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
	 * �޸���Ϣ->ע����Ա��
	 * @param data ��Ϣ���飨Ա���������ƣ�ְλ���Ա���������ϵ�绰����ְ���ڣ����䣬�ȼ���
	 */
	public void registerEmployee(String[] data) {
		try {
			int grade = Integer.parseInt(data[7]);	//��String����תΪint����
			String query = "select dep_id from dep where dname = '"+data[0]+"'";	//���ݲ������Ʋ�ѯ���ź�
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				String dep_id = rs.getString(1);
				String update = "insert into emp values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(update);	//Ԥ����
				//���ù�����ʹ�á��ȼ�+���ź�+��ְ���ڣ�0000-00-00��+��Уλ������ʽ����Ա������
				ps.setString(1, new CheckDigit().getCheckDigit(grade + dep_id + data[5].replaceAll("-", "")));
				ps.setString(2, data[3]);
				ps.setString(3, data[1]);
				ps.setString(4, data[4]);
				ps.setInt(5, Integer.parseInt(data[6]));
				ps.setString(6, data[2]);
				ps.setString(7, dep_id);
				ps.setString(8, data[5]);
				ps.setInt(9, grade);
				ps.setFloat(10, (float) 0.00);	//ע����Ա��ʱ��Ĭ��������нΪ��0.00��
				ps.setString(11, "123456");	//ע����Ա��ʱ��Ĭ����������Ϊ��123456��
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * �޸���Ϣ->��ѯԱ����Ϣ
	 * ����Ա��������Ϣ��ɵ�String���飨Ա����������ϵ�绰�����䡢�Ա�
	 * @param emp_id Ա������
	 * @return	��Ϣ����
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
	 * �޸���Ϣ->ɾ��Ա����Ϣ
	 * @param emp_id Ա������
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
	 * �޸���Ϣ->�޸�Ա����Ϣ
	 * @param data ��Ϣ���飨Ա�����ţ���������ϵ�绰�����䣬�Ա�
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
	 * �߼�����->н�ʹ���->��ѯԱ����н
	 * ����Ա����н
	 * @param emp_id Ա������
	 * @return н����Ϣ
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
	 * �߼�����->н�ʹ���->�޸�Ա����н
	 * @param emp_id Ա������
	 * @param income �޸ĺ����н
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
	 * �߼�����->��������
	 * @param text �����ı�
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
	 * ��ȡ��һ��������
	 * @return sales[]
	 */
	public int[] getValues_1() {
		try {
			String query = "select * from quarter1 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"��Ʒ1","��Ʒ2","��Ʒ3","��Ʒ4","��Ʒ5","��Ʒ6"};
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
	 * ��ȡ�ڶ���������
	 * @return sales[]
	 */
	public int[] getValues_2() {
		try {
			String query = "select * from quarter2 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"��Ʒ1","��Ʒ2","��Ʒ3","��Ʒ4","��Ʒ5","��Ʒ6"};
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
	 * ��ȡ������������
	 * @return sales[]
	 */
	public int[] getValues_3() {
		try {
			String query = "select * from quarter3 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"��Ʒ1","��Ʒ2","��Ʒ3","��Ʒ4","��Ʒ5","��Ʒ6"};
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
	 * ��ȡ���ļ�������
	 * @return sales[]
	 */
	public int[] getValues_4() {
		try {
			String query = "select * from quarter4 where goods = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			int[] sales = new int[6];
			String[] a = {"��Ʒ1","��Ʒ2","��Ʒ3","��Ʒ4","��Ʒ5","��Ʒ6"};
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
