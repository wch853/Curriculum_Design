package CD_Tab3;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import CD_DataBase.DataManagement;

public class GoodsQuarter3 extends JPanel{
	
	public GoodsQuarter3() {
		int[] sales = new DataManagement().getValues_3();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		for(int i = 0; i <= 5; ++i) {
			defaultcategorydataset.addValue(sales[i], "��Ʒ"+(i+1), "��Ʒ"+(i+1));
		}
		JFreeChart chart=ChartFactory.createBarChart3D("�������Ȳ�Ʒ����ͼ", "��Ʒ", "����",
				defaultcategorydataset, PlotOrientation.VERTICAL, true, false, false);
		ChartFrame  frame=new ChartFrame ("�������Ȳ�Ʒ����ͼ ",chart,true);      
		frame.pack();
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
}