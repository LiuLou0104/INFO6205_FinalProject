package Business.Plot;

import Business.DrawArea.AreaUnit;
import Business.Simulation.OnePathogenSimu;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeriesCollection;
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

    /**
     * XY Series for line chart
     * @param pathogenSimus
     * @return
     */
    public static XYSeriesCollection getXYSeriesDataSetInfectNum(List<OnePathogenSimu> pathogenSimus){
        XYSeriesCollection dataset = new XYSeriesCollection();
        int seriesNum = 0;
        int abTime = 0; // Absolute time of the simulaiton

        for (OnePathogenSimu pathogenSimu : pathogenSimus) {
            XYSeries series = new XYSeries("XYSeries" + seriesNum);

            for (OnePathogenSimu patheSim : pathogenSimu.getInfectNum()) {
                series.add(abTime++, patheSim.calcInfectedNum());
            }
        }

        return dataset;
    }

    /**
     * XY Series for line chart
     * @param pathogenSimus
     * @return
     */
    public static XYSeriesCollection getXYSeriesDataSetInfectUnits(List<OnePathogenSimu> pathogenSimus){
        XYSeriesCollection dataset = new XYSeriesCollection();
        int seriesNum = 0;
        int abTime = 0; // Absolute time of the simulaiton

        for (OnePathogenSimu pathogenSimu : pathogenSimus) {
            XYSeries series = new XYSeries("XYSeries" + seriesNum);

            for (OnePathogenSimu patheSim : pathogenSimu.getInfectNum()) {
                series.add(abTime++, patheSim.calcInfectedUnits());
            }
        }

        return dataset;
    }

    public static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // TODO: construct the dataset

        return dataset;
    }

    /**
     * XY Series for Scatter Plot to draw the Area for
     * which is under serious epidemic
     * @param pathogenSimus
     * @return
     */
    public static XYSeriesCollection getXYSeriesDataSetEpiArea(List<OnePathogenSimu> pathogenSimus){
        XYSeriesCollection dataset = new XYSeriesCollection();
        int seriesNum = 0;

        for (OnePathogenSimu pathogenSimu : pathogenSimus) {
            AreaUnit[][] areaUnits = pathogenSimu.getArea();
            for (int i = 0; i < pathogenSimu.AREA_WIDTH; i++) {
                for (int j = 0; j < pathogenSimu.AREA_LENGTH;i++) {
                    if(areaUnits[i][j].getInfectNum() / areaUnits[i][j].getHeadcount() > 0.3) {
                        XYSeries serie = new XYSeries("XYSeries" + seriesNum++);
                        int x = j;
                        int y = i;
                        serie.add(x, y);
                        dataset.addSeries(serie);
                    }
                }
            }
        }

        return dataset;
    }

    public static TimeSeriesCollection getTimeSeries() {
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

        // TODO: construct the dataset

        return timeSeriesCollection;
    }

}
