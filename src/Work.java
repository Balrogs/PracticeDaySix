import java.util.Scanner;
import java.util.*;

/**
 * ����� ���������� ���������� �����. ����� ��� ������: setup - �������� ������
 * ���� � ������������ ������, run - ������ ����� � ������ �������, qui - ������
 * ����� � ������ ����������� ����. �������� ������ �� ���� ��������, ��������
 * �������� 5 �� ��� � �������� ���� � �������� ������. ���������� ������������
 * � ���� � ������������
 * 
 * @author ����� ���������� � ������� �������
 *
 */
public class Work {

	/**
	 * ��������� ������ �������� � ����������� ������ �� ������. ���������� ����
	 * "+", ���� ������ ���������, ���� "-" � ��������� ������
	 * 
	 * @param s1
	 *            - ����� ��������
	 * @param s2
	 *            - ���������� �����
	 * @return string - ��������� ���������
	 */
	public String CompareString(String s1, String s2) {
		if (s1.equals(s2)) {
			return "���������";
		}
		return "�����������";
	}

	/**
	 * ����������� ������ �� 5 ��������� ����� �� 0 �� 9
	 * 
	 * @param list
	 */
	public void Random(ArrayList<Integer> list) {
		Random r = new Random();
		while (list.size() != 5) {
			int n = r.nextInt(9);
			if (list.indexOf(n) == -1) {
				list.add(n);
			}
		}
	}

	/**
	 * ����� ����� � ���������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SQLite sq = new SQLite();
		Work work = new Work();
		String DBname = "FirstTest";
		if (args[0].equals("setup")) {
			sq.createTestBase(DBname);
		}
		if (args[0].equals("run")) {

			ArrayList<String> listQuestions = new ArrayList<String>();
			ArrayList<String> listAnswers = new ArrayList<String>();
			ArrayList<String> listTrueAnswers = new ArrayList<String>();
			ArrayList<String> listTrueAnswersStudet = new ArrayList<String>();
			ArrayList<String> resultList = new ArrayList<String>();
			ArrayList<Integer> random = new ArrayList<Integer>();
			sq.read(listQuestions, listAnswers, listTrueAnswers);
			Scanner sc = new Scanner(System.in);
			int mark = 0;
			StringBuilder sb = new StringBuilder();
			System.out.println("���: ");
			if (sc.hasNext()) {
				sb.append(sc.next() + " ");
			}
			System.out.println("�������: ");
			if (sc.hasNext()) {
				sb.append(sc.next() + " ");
			}
			System.out.println("������� ������(����� ������): ");
			if (sc.hasNext()) {
				sb.append("������: " + sc.next());
			}
			work.Random(random);
			for (int i = 0; i < 5; i++) {
				int r = random.get(i);
				long before = System.currentTimeMillis();
				System.out.println(listQuestions.get(r));
				resultList.add(listQuestions.get(r) + "\n");
				System.out.println(listAnswers.get(r));
				if (sc.hasNext()) {
					listTrueAnswersStudet.add(sc.next());
				}
				long after = System.currentTimeMillis();
				long diff = after - before;
				if (work.CompareString(listTrueAnswersStudet.get(i),
						listTrueAnswers.get(r)) == "+") {
					mark++;
				}
				resultList.add("��� �����: "
						+ listTrueAnswersStudet.get(i)
						+ "\t"
						+ work.CompareString(listTrueAnswersStudet.get(i),
								listTrueAnswers.get(r))
						+ " ����������� �����: " + (double) diff / 1000
						+ " ��� \n");
			}
			sq.write(DBname, sb.toString(), resultList, mark);
			System.out.println(sq.ReadResult(DBname, sb.toString()));
			sc.close();
		}
		if (args[0].equals("gui")) {
			Window l = new Window();
			int i=0;
			i=1;
			
		}
	}
}