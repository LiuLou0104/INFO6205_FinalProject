package Business.Plot;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;

public class TimeSeriesChart extends PlotAbstract {

    private String title;
    private String xAxisLable;
    private String yAxisLable;
    private boolean legend = false;
    private boolean tooltips = false;
    private boolean urls = false;

    public String getTitle() {
        return title;
    }

    public TimeSeriesChart() {
        super();
    }

    public TimeSeriesChart(String title, String xLable, String yLable) {
        this.title = title;
        this.xAxisLable = xLable;
        this.yAxisLable = yLable;
    }

    public JFreeChart Plot(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(this.title,
                this.xAxisLable,  this.yAxisLable,
                dataset,
                this.legend, this.tooltips, this.urls);

        return chart;
    }
}
