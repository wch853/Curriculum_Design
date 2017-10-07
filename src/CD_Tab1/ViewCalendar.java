package CD_Tab1;

import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.*;

/**
 * ������->��������->��ʾ�������
 *
 */
public class ViewCalendar extends JPanel implements ActionListener {
	JLabel day[] = new JLabel[42]; 
	JButton weekday[] = new JButton[7];  
	String weekdays[] = { "��", "һ", "��", "��", "��", "��", "��" };
	JTextField yeartxt, monthtxt; 
	JButton last, next, query; 
	JLabel yearlb, monthlb;  
	Calendar c = Calendar.getInstance(); 
	int year = c.get(Calendar.YEAR);	//��õ�ǰ��
	int month = c.get(Calendar.MONTH) + 1;	//��õ�ǰ��
	CalendarBean calendar;
	JPanel p1, p2;
	JLabel showtime = new JLabel("", JLabel.CENTER);
	ViewCalendar() {    
		setLayout(new BorderLayout());
		p1 = new JPanel();  
		p1.setLayout(new GridLayout(7, 7));
		for (int i = 0; i < 7; i++) {      
			weekday[i] = new JButton(weekdays[i]);
			weekday[i].setEnabled(false);
			p1.add(weekday[i]);
		}
		for (int i = 0; i < 42; i++) {      
			day[i] = new JLabel("", JLabel.CENTER);
			p1.add(day[i]);
		}
		calendar = new CalendarBean();
		calendar.setYear(year);     
		calendar.setMonth(month);
		String days[] = calendar.getCalendar();   
		for (int i = 0; i < 42; i++) {      
			day[i].setText(days[i]);
		}
		yearlb = new JLabel("���");
		monthlb = new JLabel("�·�");
		query = new JButton("ȷ��");
		yeartxt = new JTextField(5);
		monthtxt = new JTextField(5);
		next = new JButton("����");
		last = new JButton("����");
		query.addActionListener(this);  
		next.addActionListener(this);
		last.addActionListener(this);
		showtime.setText("ʱ�䣺" + calendar.getYear() + "��" + calendar.getMonth() + "��");
		p2 = new JPanel();
		p2.add(yearlb);
		p2.add(yeartxt);
		p2.add(monthlb);
		p2.add(monthtxt);
		p2.add(query);
		p2.add(last);
		p2.add(next);
		p2.add(showtime);
		add(p1, BorderLayout.CENTER); 
		add(p2, BorderLayout.NORTH);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == next) {  
			month = month + 1;
			if (month > 12) {
				month = 1;
				year += 1;
			}
			calendar.setMonth(month);
			calendar.setYear(year);
			String days[] = calendar.getCalendar();
			for (int i = 0; i < 42; i++) {
				day[i].setText(days[i]);
			}
		} else if (e.getSource() == last) {
			month = month - 1;
			if (month < 1) {
				month = 12;
				year -= 1;
			}
			calendar.setMonth(month);
			calendar.setYear(year);
			String days[] = calendar.getCalendar();
			for (int i = 0; i < 42; i++) {
				day[i].setText(days[i]);
			}
		} else {
			String yea = yeartxt.getText();
			String mon = monthtxt.getText();
			try {
				year = Integer.parseInt(yea);  
				month = Integer.parseInt(mon);
				if (month > 12 || month < 1 || year < 1) {   
					JOptionPane.showMessageDialog(null, "��������ȷ��ݼ��·�"); 
					return;
				} else {
					calendar.setYear(year);
					calendar.setMonth(month);
				}
				String days[] = calendar.getCalendar();
				for (int i = 0; i < 42; i++) {
					day[i].setText(days[i]);
				}
			} catch (NumberFormatException ee) {
				JOptionPane.showMessageDialog(null, "��������ȷ��ݼ��·�");
			}
		}
		showtime.setText("������" + calendar.getYear() + "��" + calendar.getMonth() + "��");
	}
}
class CalendarBean {  //����һ��������
	String day[];  
	int year, month;	
	public void setYear(int year) { 
		this.year = year;
	}
	public int getYear() {
		return year;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getMonth() {
		return month;
	}
	public String[] getCalendar() {  
		String a[] = new String[42];  
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(year, month - 1, 1);
		int weekname = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int day = 0;
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			day = 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			day = 30;
		}
		if (month == 2) {
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
				day = 29;
			} else {
				day = 28;
			}
		}
		for (int i = weekname, n = 1; i < weekname + day; i++) {
			a[i] = String.valueOf(n);
			n++;
		}
		return a;
	}
}

