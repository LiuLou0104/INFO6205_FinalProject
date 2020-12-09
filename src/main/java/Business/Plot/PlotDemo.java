package Business.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.FileOutputStream;

public class PlotDemo {

    public static void main(String[] args) {

        XYSeries series1 = new XYSeries("xySeries1");
        XYSeries series2 = new XYSeries("xySeries2");
        for (int x = -10; x < 10; x++) {
            int y = x * x;
            series1.add(x, y);
        }

        for (int x = -10; x < 10; x++) {
            int y = x * x * x;
            series2.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "y = x^2", // chart title
                "x", // x axis label
                "x^2", // y axis label
                dataset, // data
                PlotOrientation.VERTICAL,
                true, // include legend
                false, // tooltips
                false // urls
        );

        // draw Bar Chart
        CategoryDataset dataset2 = createDataset();
        JFreeChart chart2 = createChart(dataset2);

        Font font = new Font("Arial",Font.CENTER_BASELINE,20);
        TextTitle title = new TextTitle("test");
        title.setFont(font);
        chart.setTitle(title);
        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("src/main/resources/test.jpg");
            ChartUtils.writeChartAsJPEG(fos_jpg,0.7f,chart,640,480,null);
            fos_jpg.close();
        } catch (Exception e) {
            System.out.println("error shows");
        }

        chart2.setTitle(title);
        try {
            fos_jpg = new FileOutputStream("src/main/resources/testbarchart.jpg");
            ChartUtils.writeChartAsJPEG(fos_jpg,0.7f,chart2,640,480,null);
            fos_jpg.close();
        } catch (Exception e) {
            System.out.println("error shows");
        }
        String[] a = {"7","2","4"};
        String[] b = {"5","2","4"};
        data("scattertest",a, b);
    }

    private static CategoryDataset createDataset()
    {
        String series1 = "First";
        String series2 = "Second";
        String category1 = "Category 1";
        String category2 = "Category 2";
        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
        defaultcategorydataset.addValue(1.0D, series1, category1);
        defaultcategorydataset.addValue(4D, series1, category2);

        defaultcategorydataset.addValue(5D, series2, category1);
        defaultcategorydataset.addValue(7D, series2, category2);
        return defaultcategorydataset;
    }

    private static JFreeChart createChart(CategoryDataset categorydataset)
    {
        JFreeChart jfreechart = ChartFactory.createBarChart(
                "Bar Chart Demo",
                "Category",
                "Value",
                categorydataset, // dataset
                PlotOrientation.VERTICAL,
                true,
                true, // tooltips?
                false); //URLs?
        jfreechart.setBackgroundPaint(Color.white);
        CategoryPlot categoryplot = jfreechart.getCategoryPlot();
        categoryplot.setBackgroundPaint(Color.lightGray);
        categoryplot.setDomainGridlinePaint(Color.white);
        categoryplot.setDomainGridlinesVisible(true);
        categoryplot.setRangeGridlinePaint(Color.white);
        categoryplot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
        categoryplot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
        numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer barrenderer = (BarRenderer)categoryplot.getRenderer();
        barrenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barrenderer.setDefaultItemLabelFont(new Font("Arial",Font.PLAIN,12));
        barrenderer.setDefaultItemLabelsVisible(true);

        barrenderer.setDrawBarOutline(false);
        GradientPaint gradientpaint = new GradientPaint(0.0F, 0.0F, Color.blue,0.0F, 0.0F, new Color(0, 0, 64));
        GradientPaint gradientpaint1 = new GradientPaint(0.0F, 0.0F, Color.green,0.0F, 0.0F, new Color(0, 64, 0));
        GradientPaint gradientpaint2 = new GradientPaint(0.0F, 0.0F, Color.red,0.0F, 0.0F, new Color(64, 0, 0));
        barrenderer.setSeriesPaint(0, gradientpaint);
        barrenderer.setSeriesPaint(1, gradientpaint1);
        barrenderer.setSeriesPaint(2, gradientpaint2);
        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        return jfreechart;
    }

    public static void data(String title,String[] a,String[] b)
    {
        DefaultXYDataset xydataset = new DefaultXYDataset ();

        double[][] data=new double[2][a.length];
        for(int i=0;i<a.length;i++)
        {
            data[0][i]=Double.parseDouble(a[i]);
            data[1][i]=Double.parseDouble(b[i]);
        }
        xydataset.addSeries("123456", data);

        XYTextAnnotation text1 = new XYTextAnnotation("1sss",2, 2);
        XYTextAnnotation text2 = new XYTextAnnotation("2aaa", 4, 4);
        XYTextAnnotation text3 = new XYTextAnnotation("3bbb", 7, 5);

        final JFreeChart chart =ChartFactory.createScatterPlot("",
                "","",xydataset,PlotOrientation.VERTICAL,
                true,true,false);

        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.addAnnotation(text1);
        xyplot.addAnnotation(text2);
        xyplot.addAnnotation(text3);

        Font font = new Font("Arial",Font.CENTER_BASELINE,20);
        TextTitle titleforPlot = new TextTitle("test");
        titleforPlot.setFont(font);
        chart.setTitle(titleforPlot);
        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("src/main/resources/testforScatter.jpg");
            ChartUtils.writeChartAsJPEG(fos_jpg,0.7f,chart,640,480,null);
            fos_jpg.close();
        } catch (Exception e) {
            System.out.println("error shows");
        }
    }
}
