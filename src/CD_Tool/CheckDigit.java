package CD_Tool;

import java.util.Scanner;

/**
 * ���ڻ�ȡ������Уλ������Ա������
 *
 */
public class CheckDigit {
	public String getCheckDigit(String s) {
		Scanner in = new Scanner(s);
		Long c = in.nextLong();
		Long a = c;
		int b = 0;
		int sum = 0;
		int i =1;
		while(a>0) {
			b = (int)(a % 10);
			sum += b*i;
			a = a / 10;
			i *= 2;
		}
		String str = c+""+sum%11;
		return str;
	}
}
