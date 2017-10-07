package CD_Login;

import java.awt.*;
import javax.swing.*;

/**
 * ��¼���汳�����
 * ���Ƶ�¼����ͼƬ��ͼƬ��CD��Ŀ¼��
 */
public class BackgroundPanel extends JPanel {
	Image img = new ImageIcon("登录背景.png").getImage();
	public void paintComponent(Graphics g) {	//��дpaintComponent(Graphics g)
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(img, 0, 0, null);
	}
}

