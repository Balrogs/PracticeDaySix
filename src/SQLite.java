import java.sql.*;
import java.util.ArrayList;

/**
 * ����� SQLite ������������ ��� ������ � ������ ������ JDBC. � ������� ����
 * ����� ���������, ��������� � ��������� ������ �� ���.
 * 
 * @author ����� ���������� � ������� �������
 *
 */
public class SQLite {
	/**
	 * ������� ����������� � ����� �� ������ DBname.
	 * 
	 * @param DBname
	 *            - ��� ���� ������
	 * @return connection
	 */
	private Connection GetConnection(String DBname) {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DBname + ".db");
			c.setAutoCommit(false);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return c;
	}

	/**
	 * ����� �������� � ���������� ���� � ���������. ������� ���� � ������� �
	 * ��� � ������: id - ����� �������, Question - ����� �������, Options -
	 * ��������� �������� ������, Correct - ���������� ����� �� ������.
	 */
	public void FillBase() {
		Connection c = GetConnection("test1");
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String sql = "CREATE TABLE TEST "
					+ "(Id INT PRIMARY KEY     NOT NULL,"
					+ " Question        TEXT    NOT NULL, "
					+ " Options        	TEXT 	NOT NULL,"
					+ " Correct         TEXT	NOT NULL)";
			stmt.executeUpdate(sql);
			String insertText = "INSERT INTO TEST (Id,Question,Options,Correct)";
			sql = insertText
					+ "VALUES (0, '� ����� ���� �������� ������ ������� �����?','a) 1905\n b) 1914\n c) 1918\n d) 1922', '1914' );";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (1, '� ����� ���� ������� ���������� ������� �� ����� ������ ������� �����?','a) ������������\nb) �������\nc) ��������\nd) ������', '�������' );";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (2, '� ����� ���� ���� ��� ����������� � ��������� ����?','a) 1945\nb) 1956\nc) 1954\nd) 1955','1954');";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (3, '������� ��� ���� ��� ������ �������?','a) 120\nb) 60\nc) 54\nd) 23','60');";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (4, '��� ��� ����������� ��� �� ����� ������ ������� �����?','a) �������\nb) ����� ���\nc) ��������\nd) ��������', '��������' );";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (5, '� ����� ���� ��� ������ ������ ����� �� ����� ������ ������� �����?','a) 1942\nb) 1941\nc) 1946\nd) 1944','1944');";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (6, '��� ���� ���������� �����?','a) �������\nb) �������\nc) ���������\nd) �������', '�������');";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (7, '� ����� ���� ����������� ������ ������� �����?','a) 1944\nb) 1945\nc) 1946\nd) 1943', '1945');";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (8, '��� ��� ������ � ��������� ����������� ����?','a) ������\nb) ��������\nc) ������\nd) ��������', '��������' );";
			stmt.executeUpdate(sql);
			sql = insertText
					+ "VALUES (9, '��� ��� ������� ���(�)?','a) ���������\nb) �������\nc) �������\nd) ����������', '�������');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * ����� ������ �� ���� ������ � ���������. ������ ������ �� ������� �
	 * ��������� ������ � ���������, ���������� ������ � ����������� ��������
	 * 
	 * @param listQuestions
	 *            - ������ ��������
	 * @param listAnswers
	 *            - ������ ��������� ������
	 * @param listTrueAnswers
	 *            - ������ ���������� �������
	 */
	public void read(ArrayList<String> listQuestions,
			ArrayList<String> listAnswers, ArrayList<String> listTrueAnswers) {

		Connection c = GetConnection("test1");
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TEST;");
			while (rs.next()) {
				String question = rs.getString("Question");
				String options = rs.getString("Options");
				String correctOpt = rs.getString("Correct");
				listQuestions.add(question);
				listAnswers.add(options);
				listTrueAnswers.add(correctOpt);
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}

	/**
	 * ������� ������ ���� ������ � ���������� � ����������� ������ �������
	 * ������� TESTS � ������: Name - ��� ��������, Answers - ���������� ��
	 * ������� �� �������(������������ �������, ����� ����������), Mark - ������
	 * �� ����
	 * 
	 * @param name
	 *            - ��� ��
	 */
	public void createTestBase(String name) {
		Connection c = GetConnection(name);
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			System.out.println("Opened database " + name + " successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE TESTS " + "(Name TEXT NOT NULL,"
					+ " Answers TEXT NOT NULL," + " Mark INT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	/**
	 * ���������� ������ � ��������� ����� � ���� � ������������
	 * 
	 * @param DBname
	 *            - ��� ���� �����������
	 * @param StudentName
	 *            - ��� ��������
	 * @param result
	 *            - ��������� ����������� �����
	 * @param mark
	 *            - ������
	 */
	public void write(String DBname, String StudentName,
			ArrayList<String> result, int mark) {
		Connection c = GetConnection(DBname);
		Statement stmt = null;
		try {
			c.setAutoCommit(false);
			System.out.println("Opened for write database " + DBname
					+ " successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS TESTS "
					+ "(Name TEXT NOT NULL," + " Answers TEXT NOT NULL,"
					+ " Mark INT NOT NULL)";
			stmt.executeUpdate(sql);
			String answers = "";
			for (String str : result) {
				answers += str;
			}
			sql = "INSERT INTO TESTS (Name, Answers, Mark)" + "VALUES ('"
					+ StudentName + "', '" + answers + "', " + mark + ");";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	/**
	 * ��������� ���������� ������ �� ���� ������
	 * 
	 * @param DBname
	 *            - ��� ���� ������
	 * @param StudentName
	 *            - ��� ��������
	 * @return string - ���������� � ����������� �����
	 */
	public String ReadResult(String DBname, String StudentName) {
		Connection c = GetConnection(DBname);
		Statement stmt = null;
		String result = "";
		try {
			c.setAutoCommit(false);
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM TESTS;");
			while (rs.next()) {
				String name = rs.getString("Name");
				if (name.equals(StudentName)) {
					String answers = rs.getString("Answers");
					int mark = rs.getInt("Mark");
					result = (StudentName + "\n" + answers + "\n" + "������: " + mark);
				}

			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return result;
	}
}
