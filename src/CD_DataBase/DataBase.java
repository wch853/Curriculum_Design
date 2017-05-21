package CD_DataBase;

import java.sql.*;

/**
 * ��װ���ݿ�
 *
 */
public class DataBase {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/management";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "admin";	//���ݿ��ַ���û���������
	private Connection conn;
	public DataBase() {
		try {	//�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		try {	//�������ݿ�����
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}

