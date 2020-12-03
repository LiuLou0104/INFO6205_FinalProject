package Business.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
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

//        ChartFrame frame = new ChartFrame("my picture", chart);
//        frame.pack();
//        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
