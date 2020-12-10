package Business.Plot;

import Business.DrawArea.AreaUnit;
import Business.Simulation.OnePathogenSimu;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;

public class PlotAbstract {

    public static void saveChartAsJPG(JFreeChart chart, String chartTitle, String fileName) {
        Font font = new Font("Arial",Font.CENTER_BASELINE,20);
        TextTitle title = new TextTitle(chartTitle);
        title.setFont(font);
        chart.setTitle(title);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.getHSBColor(34, 14, 98));
        plot.setDomainGridlinePaint(Color.lightGray); // set color of line for horizontal as white
        plot.setDomainGridlinesVisible(true); // set visible
        plot.setRangeGridlinePaint(Color.lightGray); //set color of line for vertical as white

        FileOutputStream fos_jpg = null;
        try {
            fos_jpg = new FileOutputStream("src/main/resources/" + fileName + ".jpg");
            ChartUtils.writeChartAsJPEG(fos_jpg,0.99f,chart,640,480,null);
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
     * @param infectNumList
     * @return
     */

    public static XYSeriesCollection getXYSeriesDataSetInfectNum(List<Double> infectNumList, double totalPopulation){
        XYSeriesCollection dataset = new XYSeriesCollection();
        int abTime = 1; // Absolute time of the simulaiton

        XYSeries series = new XYSeries("Total Population is " + (int)totalPopulation);

        for (Double infectNum : infectNumList) {
            series.add(abTime++, infectNum);
        }
        dataset.addSeries(series);

        return dataset;
    }

    /**
     * XY Series for line chart
     * @param infectUnitsList
     * @return
     */
    public static XYSeriesCollection getXYSeriesDataSetInfectUnits(List<Integer> infectUnitsList){
        XYSeriesCollection dataset = new XYSeriesCollection();
        int seriesNum = 0;
        int abTime = 0; // Absolute time of the simulaiton

        XYSeries series = new XYSeries("Total Number of Areas is 600");

        for (Integer unitCnt : infectUnitsList) {
            series.add(abTime++, unitCnt);
        }
        dataset.addSeries(series);

        return dataset;
    }


    /**
     * CategoryDataset for bar chart
     * @param pathogenSimus
     * @return
     */
//    public static CategoryDataset getDataSetInfectNum(List<OnePathogenSimu> pathogenSimus) {
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//        int seriesNum = 0;
//        int abTime = 0; // Absolute time of the simulaiton
//
//        // TODO: construct the dataset
//        for (OnePathogenSimu pathogenSimu : pathogenSimus) {
//            String series = "XYSeries" + seriesNum;
//            seriesNum++;
//
//            for (OnePathogenSimu patheSim : pathogenSimu.getdataSetForPlot()) {
//                dataset.addValue(patheSim.calcInfectedNum(), series, Integer.toString(abTime++));
//            }
//        }
//
//        return dataset;
//    }

    /**
     * XY Series for Scatter Plot to draw the Area for
     * which is under serious epidemic
     * @param pathSimu
     * @return
     */
    public static DefaultXYDataset getDatasetEpiArea(OnePathogenSimu pathSimu){
        DefaultXYDataset dataset = new DefaultXYDataset();
        int seriesNum = 0;

        AreaUnit[][] areaUnits = pathSimu.getAreaUnitArray();

        double[][] data = new double[2][pathSimu.AREA_WIDTH * pathSimu.AREA_LENGTH];
        int k = 0;
        for (int i = 0; i < pathSimu.AREA_WIDTH; i++) {
            for (int j = 0; j < pathSimu.AREA_LENGTH; j++) {
                if(areaUnits[i][j].getInfectNum() / areaUnits[i][j].getHeadcount() > 0.3) {
                    data[0][k] = j + 1;
                    data[1][k] = i + 1;
                    k++;
//                        XYSeries serie = new XYSeries("XYSeries" + seriesNum++);
//                        int x = j;
//                        int y = i;
//                        serie.add(x, y);
//                        dataset.addSeries(serie);
                }
            }
        }
        dataset.addSeries("Epidemic Area (> 30% infection)", data);

        return dataset;
    }

}
