package Business.Plot;

import Business.Simulation.OnePathogenSimu;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.Font;
import java.util.Date;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;

public class PlotAbstract {

    public static void saveChartAsJPG(JFreeChart chart, String chartTitle, String fileName) {
        Font font = new Font("Arial",Font.CENTER_BASELINE,20);
        TextTitle title = new TextTitle(chartTitle);
        title.setFont(font);
        chart.setTitle(title);

        // TODO: if need to set legend font

        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream(fileName + ".jpg");
            ChartUtils.writeChartAsJPEG(fos_jpg,0.7f,chart,640,480,null);
            fos_jpg.close();
        } catch (Exception e) {
            try {
                fos_jpg.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static XYSeriesCollection getXYSeriesDataSet(List<OnePathogenSimu> pathogenSimus){
        XYSeriesCollection dataset = new XYSeriesCollection();
        int seriesNum = 0;
        int abTime = 0; // Absolute time of the simulaiton

        for (OnePathogenSimu pathogenSimu : pathogenSimus) {
            XYSeries series = new XYSeries("XYSeries" + seriesNum);
            // TODO: for loop to add x and y to the xyseries
            /*
            for (pathogenSimus.someData) {
                y =
                series.add(abTime++, y);
            }
            abTime = 0; // reset absolute Time

            dataset.addSeries(series);
             */
        }

        return dataset;
    }

    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // TODO: construct the dataset

        return dataset;
    }

}
