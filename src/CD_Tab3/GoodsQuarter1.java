package CD_Tab3;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import CD_DataBase.DataManagement;

public class GoodsQuarter1 extends JPanel{
	public GoodsQuarter1() {
		int[] sales = new DataManagement().getValues_1();		//��ȡ��һ���ȵ�����
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();		//������״ͼ����
		for(int i = 0; i <= 5; ++i) {
			defaultcategorydataset.addValue(sales[i], "��Ʒ"+(i+1), "��Ʒ"+(i+1));
		}
		JFreeChart chart=ChartFactory.createBarChart3D("��һ���Ȳ�Ʒ����ͼ", "��Ʒ", "����",
				defaultcategorydataset, PlotOrientation.VERTICAL, true, false, false);		//������״ͼ����
		ChartFrame  frame=new ChartFrame ("��һ���Ȳ�Ʒ����ͼ ",chart,true);      
		frame.pack();
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
}
