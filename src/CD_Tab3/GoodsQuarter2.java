package CD_Tab3;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import CD_DataBase.DataManagement;

public class GoodsQuarter2 extends JPanel{
	
	public GoodsQuarter2() {
		int[] sales = new DataManagement().getValues_2();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		for(int i = 0; i <= 5; ++i) {
			defaultcategorydataset.addValue(sales[i], "商品"+(i+1), "商品"+(i+1));
		}
		JFreeChart chart=ChartFactory.createBarChart3D("第二季度产品销量图", "商品", "销量",
				defaultcategorydataset, PlotOrientation.VERTICAL, true, false, false);
		ChartFrame  frame=new ChartFrame ("第二季度产品销量图 ",chart,true);      
		frame.pack();
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
}