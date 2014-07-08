import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.util.ArrayList;
import java.awt.Component;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public  void Start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window frame = new Window();
					frame.setVisible(true);
					frame.setSize(327, 227);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JButton answer1;
	JButton answer2;
	JButton answer3;
	JButton answer4;
	JButton start;
	JButton ok;
	JTextArea questionTxt;
	int next = 0;
	long before;
	long after;
	String DBname = "FirstTest";
	StringBuilder sb = new StringBuilder();

	SQLite sq;
	ArrayList<String> listQuestions;
	ArrayList<String> listAnswers;
	ArrayList<String> listTrueAnswers;
	ArrayList<String> listTrueAnswersStudet;
	ArrayList<String> resultList;
	ArrayList<Integer> random;
	Work work = new Work();
	private JTextField fName;
	private JTextField lName;
	private JTextField group;
	private JPanel panelTest;
	private JTextArea questionTxt_2;
	private JLabel lbA;
	private JLabel lblB;
	private JLabel lblC;
	private JLabel lblD;

	
	
	public int Mark() {
		int mark = 0;
		for (int i = 0; i < 5; i++) {
			int r = random.get(i);
			if (listTrueAnswers.get(r).equals(listTrueAnswersStudet.get(i))) {
				mark++;
			}
		}
		return mark;
	}
	
	

	
	/**
	 * Create the frame.
	 */
	public Window() {
		setResizable(false);
		//ok.setEnabled(false);
		sq = new SQLite();
		work = new Work();
		listQuestions = new ArrayList<String>();
		listAnswers = new ArrayList<String>();
		listTrueAnswers = new ArrayList<String>();
		listTrueAnswersStudet = new ArrayList<String>();
		resultList = new ArrayList<String>();
		random = new ArrayList<Integer>();
		sq.read(listQuestions, listAnswers, listTrueAnswers);
		work.Random(random);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 653, 584);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		final JPanel panelRegistration = new JPanel();
		
		panelTest = new JPanel();
		panelTest.setVisible(false);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panelTest, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelRegistration, GroupLayout.PREFERRED_SIZE, 279, GroupLayout.PREFERRED_SIZE)))
					.addGap(61))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panelTest, GroupLayout.PREFERRED_SIZE, 370, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelRegistration, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(171, Short.MAX_VALUE))
		);
		
	    questionTxt_2 = new JTextArea();
	    questionTxt_2.setEditable(false);
	    questionTxt_2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
	    questionTxt_2.setRows(2);
	    questionTxt_2.setSelectionColor(UIManager.getColor("Button.foreground"));
	    questionTxt_2.setDisabledTextColor(UIManager.getColor("Button.focus"));
	    questionTxt_2.setAlignmentY(Component.BOTTOM_ALIGNMENT);
	    questionTxt_2.setBackground(Color.getHSBColor(150, 12, 69));
		
	    start = new JButton("\u0421\u0442\u0430\u0440\u0442");
	    start.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int r = random.get(0);
	    		questionTxt_2.setText(listQuestions.get(r));
	    		Substring(listAnswers.get(r));
	    		resultList.add(listQuestions.get(r) + "\n");
	    		before = System.currentTimeMillis();
	    		 answer1.setEnabled(true);
		    	 answer2.setEnabled(true);
		    	 answer3.setEnabled(true);
		    	 answer4.setEnabled(true);
		    	 start.setEnabled(false);
	    		
	    	}
	    });
		

		
		answer1 = new JButton("");
		answer1.setAlignmentY(0.0f);
		answer1.setBorder(new LineBorder(new Color(171, 173, 179), 3));
		answer1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		answer1.setActionCommand("answer1");
		answer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				after = System.currentTimeMillis();
				NextQuestion(answer1,questionTxt_2, after);
				
			}
	
		});
		
		
		answer2 = new JButton("");
		answer2.setAlignmentY(0.0f);
		answer2.setBorder(new LineBorder(new Color(171, 173, 179), 3));
		answer2.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		answer2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				after = System.currentTimeMillis();
				NextQuestion(answer2, questionTxt_2,after);
			
			}
			
		});
		
	    answer3 = new JButton("");
	    answer3.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		after = System.currentTimeMillis();
	    		NextQuestion(answer3,questionTxt_2,after);
	    	}
	    });
	    answer3.setBorder(new LineBorder(new Color(171, 173, 179), 3));
	    answer3.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
	    answer4 = new JButton("");
	    answer4.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		after = System.currentTimeMillis();
	    		NextQuestion(answer4,questionTxt_2,after);
	    		
	    	}
	    });
	    answer4.setBorder(new LineBorder(new Color(171, 173, 179), 3));
	    answer4.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		lbA = new JLabel("a)");
		lbA.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		lblB = new JLabel("b)");
		lblB.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		lblC = new JLabel("c)");
		lblC.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		lblD = new JLabel("d)");
		lblD.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		GroupLayout gl_panelTest = new GroupLayout(panelTest);
		gl_panelTest.setHorizontalGroup(
			gl_panelTest.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTest.createSequentialGroup()
					.addGroup(gl_panelTest.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panelTest.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
								.addComponent(lbA)
								.addComponent(lblB)
								.addComponent(lblC, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblD))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelTest.createSequentialGroup()
									.addComponent(answer4, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panelTest.createSequentialGroup()
									.addComponent(answer3, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panelTest.createSequentialGroup()
									.addComponent(answer2, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addComponent(answer1, GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)))
						.addComponent(questionTxt_2, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
					.addGap(18)
					.addComponent(start, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		gl_panelTest.setVerticalGroup(
			gl_panelTest.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTest.createSequentialGroup()
					.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(questionTxt_2, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
							.addGap(18))
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(start, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(210)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
						.addComponent(lbA)
						.addComponent(answer1, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(answer2, GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(lblB, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(9)))
					.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(answer3, GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(lblC, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addGap(11)))
					.addGroup(gl_panelTest.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(answer4, GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
							.addGap(14))
						.addGroup(gl_panelTest.createSequentialGroup()
							.addComponent(lblD, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		panelTest.setLayout(gl_panelTest);
		
		fName = new JTextField();
		fName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(fName.getText().length()!=0&&lName.getText().length()!=0&&group.getText().length()!=0){
					   ok.setEnabled(true);	
				}
				else
				{
					ok.setEnabled(false);	
				}
			}
		});
		fName.setColumns(10);
		
		lName = new JTextField();
		lName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(fName.getText().length()!=0&&lName.getText().length()!=0&&group.getText().length()!=0){
					   ok.setEnabled(true);	
				}
				else
				{
					ok.setEnabled(false);	
				}
			}
		});
		lName.setColumns(10);
		
		 ok = new JButton("ok");
		 ok.setEnabled(false);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelTest.setVisible(true);
				panelRegistration.setVisible(false);
				setSize(580, 410);
				resultList.add(fName.getText() + " " + lName.getText() + "\n"
						+ group.getText()+"\n");
		    	   answer1.setEnabled(false);
		    	   answer2.setEnabled(false);
		    	   answer3.setEnabled(false);
		    	   answer4.setEnabled(false);
			}
		});
		
		group = new JTextField();
		group.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(fName.getText().length()!=0&&lName.getText().length()!=0&&group.getText().length()!=0){
					   ok.setEnabled(true);	
				}
				else
				{
					ok.setEnabled(false);	
				}
			}
		});
		group.setColumns(10);
		
		JLabel lbFName = new JLabel("\u0418\u043C\u044F");
		lbFName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel lbLNmae = new JLabel("\u0424\u0430\u043C\u0438\u043B\u0438\u044F");
		lbLNmae.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		
		JLabel lbGroup = new JLabel("\u0413\u0440\u0443\u043F\u043F\u0430");
		lbGroup.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		GroupLayout gl_panelRegistration = new GroupLayout(panelRegistration);
		gl_panelRegistration.setHorizontalGroup(
			gl_panelRegistration.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelRegistration.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRegistration.createParallelGroup(Alignment.TRAILING)
						.addComponent(lbGroup)
						.addComponent(lbLNmae)
						.addComponent(lbFName))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelRegistration.createParallelGroup(Alignment.LEADING)
						.addComponent(ok, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
						.addComponent(fName, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(lName, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(group))
					.addContainerGap())
		);
		gl_panelRegistration.setVerticalGroup(
			gl_panelRegistration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelRegistration.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelRegistration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbFName)
						.addComponent(fName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRegistration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbLNmae)
						.addComponent(lName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelRegistration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lbGroup)
						.addComponent(group, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(ok)
					.addContainerGap())
		);
		panelRegistration.setLayout(gl_panelRegistration);
		contentPane.setLayout(gl_contentPane);
	}
	public void Substring(String s) {
		answer1.setText(s.substring(3, s.indexOf("\n")));
		int n = s.indexOf("\n");
		answer2.setText(s.substring(s.indexOf("b)", n + 1) + 3,
				s.indexOf("\n", n + 1)));
		n = s.indexOf("\n", n + 1);
		answer3.setText(s.substring(s.indexOf("c)", n + 1) + 3,
				s.indexOf("\n", n + 1)));
		answer4.setText(s.substring(s.indexOf("d") + 3, s.length()));

	}
	private void NextQuestion(JButton b , JTextArea questionTxt_1,long after) {
		if (next < 4) {
			next++;
			int r = random.get(next);
			long diff = after - before;
			listTrueAnswersStudet.add(b.getText());
			questionTxt_1.setText(listQuestions.get(r));
			before = System.currentTimeMillis();

			resultList.add("Ваш ответ:"
					+ listTrueAnswersStudet.get(next - 1)
					+ "\t"
					+ work.CompareString(listTrueAnswersStudet.get(next - 1),
							listTrueAnswers.get(random.get(next - 1)))
					+ " Затраченное время: " + (double) diff / 1000 + " сек "
					+ "\n" + listQuestions.get(r) + "\n");
			Substring(listAnswers.get(r));

		} else {

			long diff = after - before;
			listTrueAnswersStudet.add(b.getText());
			resultList
					.add("Ваш ответ:"
							+ listTrueAnswersStudet.get(next)
							+ "\t"
							+ work.CompareString(listTrueAnswersStudet.get(next),
									listTrueAnswers.get(random.get(next)))
							+ " Затраченное время: " + (double) diff / 1000
							+ " сек \n");
			listTrueAnswersStudet.add(answer1.getText());
			sq.write(DBname, sb.toString(), resultList, Mark());
			questionTxt_1.setText(sq.ReadResult(DBname, sb.toString()));
			answer1.setVisible(false);
			answer2.setVisible(false);
			answer3.setVisible(false);
			answer4.setVisible(false);
			start.setVisible(false);
			lbA.setVisible(false);
			lblB.setVisible(false);
			lblC.setVisible(false);
			lblD.setVisible(false);
			setSize(553, 360);
		}

	}
}


