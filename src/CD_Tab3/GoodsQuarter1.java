package CD_Tab3;

import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import CD_DataBase.DataManagement;

public class GoodsQuarter1 extends JPanel{
	public GoodsQuarter1() {
		int[] sales = new DataManagement().getValues_1();		//获取第一季度的数据
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();		//创建柱状图对象
		for(int i = 0; i <= 5; ++i) {
			defaultcategorydataset.addValue(sales[i], "商品"+(i+1), "商品"+(i+1));
		}
		JFreeChart chart=ChartFactory.createBarChart3D("第一季度产品销量图", "商品", "销量",
				defaultcategorydataset, PlotOrientation.VERTICAL, true, false, false);		//设置柱状图属性
		ChartFrame  frame=new ChartFrame ("第一季度产品销量图 ",chart,true);      
		frame.pack();
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
}
